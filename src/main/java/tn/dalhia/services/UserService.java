package tn.dalhia.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import tn.dalhia.shared.dto.UserDto;

public interface UserService extends UserDetailsService  {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String id);
	UserDto updateUser(String id, UserDto userDto);
	void deleteUser(String userId);
}
