package tn.dalhia.services;

import tn.dalhia.shared.dto.UserDto;

public interface UserService  {
	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByUserId(String id);
	UserDto updateUser(String id, UserDto userDto);
	void deleteUser(String userId);
}
