package com.rvy.store.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.rvy.store.entity.User;
import com.rvy.store.exceptions.UserException;
import com.rvy.store.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@Api(tags = "User Controller")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(
			value = "Find User By ID",
			consumes = "User ID",
			produces = "User object",
			response = User.class,
			notes = "http://localhost:8081/rvy/um/v1/user/1"
			)
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable("userId") 
	                                           Integer userId){
		try {
			return new ResponseEntity<>(
					 userService.getUserById(userId)
					,HttpStatus.OK);
		} catch(UserException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@ApiOperation(value = "Add User",
			consumes = "User object",
			produces = "User object",
			response = User.class,
			notes = "http://localhost:8081/rvy/um/v1/user"
			)
	@PostMapping("/user")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user,
			BindingResult bindingResult) {
		try {
		if(bindingResult.hasErrors()) {
			throw new UserException(bindingResult.getAllErrors().toString());
		}
	      return ResponseEntity.ok().body(userService.addUser(user));	
	   } catch(UserException e) {
		   throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
				   e.getMessage());
	   }
	}
	
	@ApiOperation(value = "Find All users",			
			produces = "List of User objects",
			response =User.class,
			notes = "http://localhost:8081/rvy/um/v1/users"
			)
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAllUsers() {
		try {
			return new ResponseEntity<>(userService.getUsers(),
					HttpStatus.OK);
		}catch(UserException e) {
		   throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@ApiOperation(value = "Update User",
			consumes = "User object",
			produces = "User object",
			response = User.class,
			notes = "http://localhost:8081/rvy/v1/um/user"
			)
	@PutMapping("/user")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,
			BindingResult bindingResult) throws UserException{
		try {
		if(bindingResult.hasErrors()) {
			throw new UserException(bindingResult.getAllErrors().toString());
		}
		 return ResponseEntity.ok(userService.updateUser(user));
	  
		} catch(UserException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,e.getMessage());
		}
	}
	

	
	@ApiOperation(value = "Delete User",			
			produces = "Boolean indication of deleted object",
			response = Boolean.class,
			notes = "http://localhost:8081/rvy/um/v1/user/1"
			)
	@DeleteMapping("/user/{userId}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("userId")
	                                           Integer userId){
		try {
			return new ResponseEntity<>(userService.deleteUser(userId),
					HttpStatus.OK);
		} catch(UserException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					e.getMessage());
		}
	}		
	

}
