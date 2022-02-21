package tn.dalhia.services.implementations;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.User;
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
	
	
	@Override
	public UserDto createUser(UserDto user) {
		User userEntity = new User();
		BeanUtils.copyProperties(user,userEntity);
		
		
		String publicUserId = utils.generateUserId(30);
	    
		userEntity.setUserId(publicUserId);
		User storedUserDetails =userRepo.save(userEntity);
		
		
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails,returnValue);
		
		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		// TODO Auto-generated method stub
		return null;
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
			userEntity.setPassword(userDto.getPassword());
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

}
