package tn.dalhia.services.implementations;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Override
	public UserDto createUser(UserDto user) {
		if(userRepo.findByEmail(user.getEmail()) !=null) throw new RuntimeException("Record already exists");
		
		
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user,userEntity);

		
		String publicUserId = utils.generateUserId(30);
	    
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPaswword(bCryptPasswordEncoder.encode(user.getPassword()));
		UserEntity storedUserDetails =userRepo.save(userEntity);
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails,returnValue);
		
		return returnValue;
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
		
		return new User(userEntity.getEmail(), userEntity.getEncryptedPaswword() , new ArrayList<>());
	}

}
