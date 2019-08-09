package com.basic.example.ui.controller;

import org.apache.catalina.loader.ResourceEntry;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basic.example.mobel.request.userDetailsRequestModel;
import com.basic.example.mobel.response.userRest;

@RestController
@RequestMapping("users")
public class userController {
	
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue="1") int page,
			@RequestParam(value="limit", defaultValue="50") int limit,
			@RequestParam(value="sort", required = false) String sort)
	{
		if(sort == null) sort = "asc";
		return "get user called with userId page = "+page+" limit = "+limit+ " sort= "+sort;
	}
	 
	
	/*
	 * @GetMapping(path="/{userId}", produces = {MediaType.APPLICATION_XML_VALUE,
	 * MediaType.APPLICATION_JSON_VALUE}) public userRest getUser(@PathVariable
	 * String userId) { userRest userRestValue = new userRest();
	 * userRestValue.setEmail("Gasgas@gmail.com");
	 * userRestValue.setFirstName("ASASAS"); userRestValue.setLastName("LastName");
	 * return userRestValue; }
	 */
	
	@GetMapping(path="/{userId}",
			produces = {MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<userRest> getUser(@PathVariable String userId)
	{
		userRest userRestValue = new userRest();
		userRestValue.setEmail("Gasgas@gmail.com");
		userRestValue.setFirstName("ASASAS");
		userRestValue.setLastName("LastName");
		//return new ResponseEntity<userRest>(userRestValue,HttpStatus.OK);
		return new ResponseEntity<userRest>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(consumes =
		{MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE},
		produces =
	{MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<userRest> createUser(@RequestBody userDetailsRequestModel userDetails) {
		userRest userRestValue = new userRest();
		userRestValue.setEmail(userDetails.getEmail());
		userRestValue.setFirstName(userDetails.getFirstName());
		userRestValue.setLastName(userDetails.getLastName());
		return new ResponseEntity<userRest>(userRestValue,HttpStatus.OK);
		
	}
	
	@PutMapping
	public String updateUser() {
		return "update user called";
	}
	
	@DeleteMapping
	public String deleteUser() {
		return "delete user called";
	}

}
