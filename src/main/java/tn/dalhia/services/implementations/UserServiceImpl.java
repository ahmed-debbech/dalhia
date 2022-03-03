package tn.dalhia.services.implementations;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.PasswordResetTokenEntity;
import tn.dalhia.entities.User;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.PasswordResetTokenRepository;

import tn.dalhia.repositories.UserRepository;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.services.UserService;
import tn.dalhia.shared.dto.UserDto;
import tn.dalhia.shared.dto.Utils;



@Service 
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public UserDto createUser(UserDto user) throws UnsupportedEncodingException, MessagingException {
		if(userRepo.findByEmail(user.getEmail()) !=null) throw new RuntimeException("Record already exists");
		
		
		User userEntity = new User();
		BeanUtils.copyProperties(user,userEntity);

		
		String publicUserId = utils.generateUserId(30);
	    
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPaswword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
		User storedUserDetails =userRepo.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails,returnValue);
		
		sendVerificationEmail(returnValue);
		
		return returnValue;
	}
	
	public void sendVerificationEmail (UserDto user) throws UnsupportedEncodingException, MessagingException{
		String subject = "Please Verify your registration";
		String senderName = "Women App Team";
		String mailContent = "<p>Dear " + user.getFirst_name() + user.getLast_name() + ",</p>";
		mailContent += "<p> please check the link below to verify your email : </p>";
		String verifyURL = "http://localhost:8089/pi/users/email-verification?token=" + user.getEmailVerificationToken();
		
		mailContent += "<h2><a href=" + verifyURL + ">Verify your account</a></h2>";
		
		mailContent += "<p> Thank you<br> Women App Team</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("women.app.spring@gmail.com", senderName);
		helper.setTo(user.getEmail());
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		
		mailSender.send(message);
		System.out.println("email sent");
		
	}

	@Override
	public UserDto getUser(String email) {
		User userEntity = userRepo.findByEmail(email);
		
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity,returnValue);
		return returnValue;

	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto();
		User userEntity = userRepo.findByUserId(userId);
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		BeanUtils.copyProperties(userEntity,returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserDto returnValue = new UserDto();
		User userEntity = userRepo.findByUserId(id);
		
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()); //Exception eli aamlneha ahna
		
			userEntity.setAddress(userDto.getAddress());
			userEntity.setFirst_name(userDto.getFirst_name());
			userEntity.setLast_name(userDto.getLast_name());
			userEntity.setEmail(userDto.getEmail());
			userEntity.setCity(userDto.getCity());
			userEntity.setPhone(userDto.getPhone());
			userEntity.setState(userDto.getState());
			userEntity.setDate_birth(userDto.getDate_birth());
			userEntity.setZipCode(userDto.getZipCode());
			User updatedUserDetails = userRepo.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails,returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		User userEntity = userRepo.findByUserId(userId);
		
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userRepo.delete(userEntity);
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User userEntity = userRepo.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);
		
		return new  org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getEncryptedPaswword(), userEntity.getEmailVerificationStatus(),true,
				true, true,
				new ArrayList<>());
		
		//return new User(userEntity.getEmail(), userEntity.getEncryptedPaswword() , new ArrayList<>());
	}

	@Override
	public boolean verifyEmailToken(String token) {
		boolean returnValue = false;
		// find user by token
		User userEntity = userRepo.findUserByEmailVerificationToken(token);
		
		if(userEntity!=null) {
			boolean hastokenExpired = Utils.hastokenExpired(token);
			if (!hastokenExpired) { // netfakdou token expired wla not expired yet
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(Boolean.TRUE);
				userRepo.save(userEntity);
				returnValue= true;
			}
		}
		return returnValue;
	}

	@Override
	public boolean requestPasswordReset(String email) throws Exception{
		boolean returnValue = false;
		User userEntity = userRepo.findByEmail(email);
		
		if(userEntity==null) {
			return returnValue;
		}
		
		String token =  new Utils().generatePasswordResetToken(userEntity.getUserId()); //wala nrodhha static f utils
		
		PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
		passwordResetTokenEntity.setToken(token);
		passwordResetTokenEntity.setUserDetails(userEntity);
		passwordResetTokenRepository.save(passwordResetTokenEntity);
		
		returnValue = resetPasswordMail (userEntity.getFirst_name(),userEntity.getEmail(),token);
		return returnValue;
	}
	
	@Override
	public boolean resetPasswordMail (String firstName , String email , String token) throws UnsupportedEncodingException, MessagingException{
		String subject = "Request for reset password";
		String senderName = "Women App Team";
		String mailContent = "<p>Dear " + firstName + "</p>";
		mailContent += "<p> Someone has requested to reset your password with our project .If it were not you , please ignore it otherwise please click on the link below : </p>";
		String verifyURL = "http://localhost:8080/verification-service/password-reset.html?token=" + token;
		
		mailContent += "<h2><a href=" + verifyURL + ">Click this link to reset password</a></h2>";
		
		mailContent += "<p> thank you<br> the Women App Team</p>";
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom("women.app.spring@gmail.com", senderName);
		helper.setTo(email);
		helper.setSubject(subject);
		helper.setText(mailContent, true);
		
		mailSender.send(message);
		if(message !=null &&(message.getMessageID()!=null && !message.getMessageID().isEmpty())){
			System.out.println("email sent");
			return true;
		}
		
		return false;
		
	}

	@Override
	public boolean resetPassword(String token, String password) {
		boolean returnValue = false;
		
		if(Utils.hastokenExpired(token)){
			return returnValue;
		}
		
		PasswordResetTokenEntity passwordResetTokenEntity =passwordResetTokenRepository.findByToken(token);
		
		if(passwordResetTokenEntity==null){
			return returnValue;
		}
		
		//prepare new password
		String encodedPassword = bCryptPasswordEncoder.encode(password);
		
		// Update User password in database 
		User userEntity = passwordResetTokenEntity.getUserDetails();
		userEntity.setEncryptedPaswword(encodedPassword);
		User savedUserEntity = userRepo.save(userEntity);
		
		// verify if user password was saved successfully
		
		if(savedUserEntity!= null && savedUserEntity.getEncryptedPaswword().equalsIgnoreCase(encodedPassword)) {
			returnValue= true;
		}
		
		// Remove Password Reset token from database 
		passwordResetTokenRepository.delete(passwordResetTokenEntity);
		return returnValue;
	
	}

}
