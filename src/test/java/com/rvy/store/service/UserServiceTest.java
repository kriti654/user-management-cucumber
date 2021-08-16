package com.rvy.store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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

import com.rvy.store.dao.IUserRepository;
import com.rvy.store.entity.User;
import com.rvy.store.exceptions.UserException;



@ExtendWith(SpringExtension.class)
public class UserServiceTest {
	@TestConfiguration
	static class UserTestContextConfiguration {

		@Bean
		public IUserService userService() {
			return new UserService();
		}
	}
	
	@Autowired
	IUserService userService;
	
	@MockBean
	IUserRepository userRepo;
	
	List<User> userList;

	private User user1;
	private User user2;
	
	@BeforeEach
	public void setUp() {
		
		user1 = new User(null,"BPIPC5522M","user01",LocalDate.of(2019,8,9),"user01@rvy.com",
				7114411223L,"sec 121","near statue luffy","Lucknow","Uttar Pradesh",
				110110,"India",null,null);
		user2 = new User(null,"BPIPK5522M","user02",LocalDate.of(2020,8,9),"user02@rvy.com",
				7114410023L,"sec 123","near statue","Kanpur","Uttar Pradesh",
				110110,"India",null,null);
		userList = Arrays.asList(user1,user2);

		Mockito.when(userRepo.findAll()).thenReturn(userList);
		Mockito.when(userRepo.findById(user1.getUserId())).thenReturn(Optional.of(user1));
		Mockito.when(userRepo.findById(-11)).thenReturn(Optional.empty());
		Mockito.when(userRepo.save(user1)).thenReturn(user1);

	}

	@Test
	public void whenValidId_UserDetailsBeFound(){
		try {
			User user = userService.getUserById(user1.getUserId());
			assertThat(user).isEqualTo(user1);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenInValidId_ReturnNull() {
		try {
			User user = userService.getUserById(null);
			assertThat(user).isNull();
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenRegionToAddShouldReturnAddedRegion() {
		try {
			User user = userService.addUser(user1);
			assertThat(user).isEqualTo(user1);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIdTODeleteThenShouldDeleteTheRegion() {
		try {
			Boolean user = userService.deleteUser(user1.getUserId());
			assertThat(user).isEqualTo(true);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}


}
