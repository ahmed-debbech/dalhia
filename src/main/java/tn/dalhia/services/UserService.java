package tn.dalhia.services;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetailsService;

import tn.dalhia.entities.User;
import tn.dalhia.shared.dto.UserDto;

public interface UserService extends UserDetailsService  {
	UserDto createUser(UserDto user) throws UnsupportedEncodingException, MessagingException;
	UserDto getUser(String email);
	UserDto getUserByUserId(String id);
	UserDto updateUser(String id, UserDto userDto);
	void deleteUser(String userId);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email) throws Exception;
	boolean resetPasswordMail(String firstName, String email, String token)
			throws UnsupportedEncodingException, MessagingException;
	boolean resetPassword(String token, String password);
	List<User> getUsers();
	void checktokenExpiration() throws UnsupportedEncodingException, MessagingException;
	
}
