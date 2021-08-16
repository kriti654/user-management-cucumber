package com.rvy.store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.store.dao.IRoleRepository;
import com.rvy.store.entity.Role;
import com.rvy.store.exceptions.RoleException;



@ExtendWith(SpringExtension.class)
public class RoleServiceTest {
	@TestConfiguration
	static class RoleTestContextConfiguration {

		@Bean
		public IRoleService roleService() {
			return new RoleService();
		}
	}
	
	@Autowired
	IRoleService roleService;
	
	@MockBean
	IRoleRepository roleRepo;
	
	List<Role> roleList;

	private Role role1;
	private Role role2;
	
	@BeforeEach
	public void setUp() {
		
		role1 = new Role(null,"Manager",null);
		role2 = new Role(null,"Cashier",null);
		roleList = Arrays.asList(role1,role2);

		Mockito.when(roleRepo.findAll()).thenReturn(roleList);
		Mockito.when(roleRepo.findById(role1.getRoleId())).thenReturn(Optional.of(role1));
		Mockito.when(roleRepo.findById(-11)).thenReturn(Optional.empty());
		Mockito.when(roleRepo.save(role1)).thenReturn(role1);
	}

	@Test
	public void whenValidId_RoleDetailsBeFound(){
		try {
			Role role = roleService.getRoleById(role1.getRoleId());
			assertThat(role).isEqualTo(role1);
		} catch (RoleException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenInValidId_ReturnNull() {
		try {
			Role role  = roleService.getRoleById(null);
			assertThat(role).isNull();
		} catch (RoleException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void givenRoleToAddShouldReturnAddedRole() {
		try {
			Role role = roleService.addRole(role1);
			assertThat(role).isEqualTo(role1);
		} catch (RoleException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIdTODeleteThenShouldDeleteTheRole() {
		try {
			Boolean role = roleService.deleteRole(role1.getRoleId());
			assertThat(role).isEqualTo(true);
		} catch (RoleException e) {
			e.printStackTrace();
		}
	}

}
