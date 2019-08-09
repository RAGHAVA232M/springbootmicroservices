package com.photo.users.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.photo.users.shared.UserDto;

public interface UsersService extends UserDetailsService{
	UserDto createUser(UserDto useDetails);
	UserDto getUserDetailsByEmail(String email);
	UserDto getUserByUserId(String userId);

}
