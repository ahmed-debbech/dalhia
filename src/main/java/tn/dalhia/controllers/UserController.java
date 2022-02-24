 package tn.dalhia.controllers;

 import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.request.UserDetailsRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.response.OperationStatusModel;
import tn.dalhia.response.RequestOperationName;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.response.UserRest;
import tn.dalhia.services.UserService;
import tn.dalhia.shared.dto.UserDto;


@RestController
@RequestMapping("users")
@Api(tags ="Gestion des users")
public class UserController {
	
	@Autowired
	UserService userService;

	@PostMapping()
	public UserRest createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {
		UserRest returnValue = new UserRest();
		
		//UserDto userDto = new UserDto();
		//BeanUtils.copyProperties(userDetails,userDto);
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		
		UserDto createdUser = userService.createUser(userDto);
		returnValue = modelMapper.map(createdUser, UserRest.class);
		
		return returnValue;
		
	}
	@GetMapping(path="/{id}")
	public UserRest getUserByUserId(@PathVariable String id ) {
		UserRest returnValue = new UserRest();
		UserDto userDto = userService.getUserByUserId(id);
		
		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}
	
	@PutMapping(path="/{id}")
	public UserRest updateUser (@PathVariable String id , @RequestBody UserDetailsRequestModel userDetails) {
		UserRest returnValue = new UserRest();
		
		if(userDetails.getFirst_name().isEmpty()) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FILED.getErrorMessage());
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails,userDto);
		
		UserDto updateUser = userService.updateUser(id ,userDto);
		BeanUtils.copyProperties(updateUser,returnValue);
		
		return returnValue;
	}
	
	@DeleteMapping(path="/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		userService.deleteUser(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
}
