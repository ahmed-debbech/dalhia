package tn.dalhia.services;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetailsService;

import tn.dalhia.shared.dto.UserDto;

public interface UserService extends UserDetailsService  {
	UserDto createUser(UserDto user) throws UnsupportedEncodingException, MessagingException;
	UserDto getUser(String email);
	UserDto getUserByUserId(String id);
	UserDto updateUser(String id, UserDto userDto);
	void deleteUser(String userId);
	boolean verifyEmailToken(String token);
}
