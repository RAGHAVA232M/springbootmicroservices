package com.photo.users.ui.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photo.users.service.UsersService;
import com.photo.users.shared.UserDto;
import com.photo.users.ui.model.CreateUserResponseModel;
import com.photo.users.ui.model.UserRequestModel;
import com.photo.users.ui.model.UserResponseModel;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private Environment env;
	@Autowired
	UsersService usersService;
	@GetMapping("/status/check")
	public String status() {
		return "Working on port "+env.getProperty("local.server.port");
	}
	
	@PostMapping("/create")
	public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody UserRequestModel createUserRequestModel) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		UserDto userDto = modelMapper.map(createUserRequestModel, UserDto.class);
		UserDto createdUser = usersService.createUser(userDto);
		CreateUserResponseModel returnValue =  modelMapper.map(createdUser, CreateUserResponseModel.class);
		return 	 ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
	}
	
	  @GetMapping(value="/{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	    public ResponseEntity<UserResponseModel> getUser(@PathVariable("userId") String userId) {
	       
	        UserDto userDto = usersService.getUserByUserId(userId); 
	        UserResponseModel returnValue = new ModelMapper().map(userDto, UserResponseModel.class);
	        
	        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
	    }
}
