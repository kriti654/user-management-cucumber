package com.rvy.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rvy.store.exceptions.RoleException;
import com.rvy.store.entity.Role;
import com.rvy.store.service.IRoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@Api(tags = "Role Controller")
public class RoleController {

	@Autowired
	private IRoleService roleService;
	
	
	@ApiOperation(value = "Add Role",
			consumes = "Role object",
			produces = "Role object",
			response = Role.class,
			notes = "http://localhost:8081/rvy/um/v1/role"
			)
	@PostMapping("/role")
	public ResponseEntity<Role> addRole(@Valid @RequestBody Role role,
			BindingResult bindingResult) {
		try {
		if(bindingResult.hasErrors()) {
			throw new RoleException(bindingResult.getAllErrors().toString());
		}
	      return ResponseEntity.ok().body(roleService.addRole(role));	
	   } catch(RoleException e) {
		   throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
				   e.getMessage());
	   }
	}
}
