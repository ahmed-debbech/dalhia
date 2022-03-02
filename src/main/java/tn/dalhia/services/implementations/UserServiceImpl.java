package tn.dalhia.services.implementations;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.UserEntity;
import tn.dalhia.exceptions.UserServiceException;
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
	
	@Override
	public UserDto createUser(UserDto user) throws UnsupportedEncodingException, MessagingException {
		if(userRepo.findByEmail(user.getEmail()) !=null) throw new RuntimeException("Record already exists");
		
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user,userEntity);

		
		String publicUserId = utils.generateUserId(30);
	    
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPaswword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setEmailVerificationToken(utils.generateEmailVerificationToken(publicUserId));
		
		UserEntity storedUserDetails =userRepo.save(userEntity);
		
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
		UserEntity userEntity = userRepo.findByEmail(email);
		
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity,returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepo.findByUserId(userId);
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		BeanUtils.copyProperties(userEntity,returnValue);
		return returnValue;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepo.findByUserId(id);
		
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
			UserEntity updatedUserDetails = userRepo.save(userEntity);
		
		BeanUtils.copyProperties(updatedUserDetails,returnValue);
		return returnValue;
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepo.findByUserId(userId);
		
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		userRepo.delete(userEntity);
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByEmail(email);
		if (userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPaswword(), userEntity.getEmailVerificationStatus(),true,
				true, true,
				new ArrayList<>());
		
		//return new User(userEntity.getEmail(), userEntity.getEncryptedPaswword() , new ArrayList<>());
	}

	@Override
	public boolean verifyEmailToken(String token) {
		boolean returnValue = false;
		// find user by token
		UserEntity userEntity = userRepo.findUserByEmailVerificationToken(token);
		
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

}
