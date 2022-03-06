package tn.dalhia.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import io.swagger.annotations.Api;
import tn.dalhia.entities.User;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.request.PasswordResetModel;
import tn.dalhia.request.PasswordResetRequestModel;
import tn.dalhia.request.UserDetailsRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.response.OperationStatusModel;
import tn.dalhia.response.RequestOperationName;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.response.UserRest;
import tn.dalhia.services.UserService;
import tn.dalhia.shared.dto.UserDto;
import tn.dalhia.shared.tools.UserPDFExporter;





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

	@GetMapping(path="/email-verification")

	public OperationStatusModel verifyEmailToken(@RequestParam(value="token") String token) {

		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

		boolean isVerified = userService.verifyEmailToken(token);

		if(isVerified) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		} else {
			returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
		}

		return returnValue;
	}



	@PostMapping(path="/password-reset-request")

	public OperationStatusModel requestReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) throws Exception {

		OperationStatusModel returnValue = new OperationStatusModel();

		boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());

		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());

		if(operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}

	@PostMapping(path="/password-reset")
	public OperationStatusModel resetPassword(@RequestBody PasswordResetModel passwordResetModel)  {

		OperationStatusModel returnValue = new OperationStatusModel();

		boolean operationResult = userService.resetPassword(
				passwordResetModel.getToken(),
				passwordResetModel.getPassword());

		returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationStatus.ERROR.name());


		if(operationResult) {
			returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		}

		return returnValue;
	}

	@GetMapping(path="/get-users-pdf")
	public void getUsers (HttpServletResponse response) throws DocumentException, IOException{

		
		response.setContentType("application/pdf");
		
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=user_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		List<User> users = userService.getUsers();
		

		UserPDFExporter exporter = new UserPDFExporter(users);
		exporter.export(response);


	}
}
