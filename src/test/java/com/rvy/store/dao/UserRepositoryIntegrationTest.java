package com.rvy.store.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.rvy.store.app.StoreDemoApplication;
import com.rvy.store.entity.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StoreDemoApplication.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class UserRepositoryIntegrationTest {
	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private IUserRepository userRepo ;
	
	@Test
	public void whenFindById_thenReturnType() {
		
		User user = new User(null,"BPIPC5522M","user01",LocalDate.of(2019,8,9),"user01@rvy.com",
				7114411223L,"sec 121","near statue luffy","Lucknow","Uttar Pradesh",
				110110,"India",null,null);
		entityManager.persistAndFlush(user);		
		User userDb = userRepo.findById(user.getUserId()).orElse(null);		
		assertThat(userDb.getUserId()).isEqualTo(user.getUserId());
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		User userDb = userRepo.findById(-11).orElse(null);	
        assertThat(userDb).isNull();
	}
	
	
	@Test
	public void whenFindAllUser_thenReturnUserList() {
		
		User user1 = new User(null,"BPIPC5522M","user01",LocalDate.of(2019,8,9),"user01@rvy.com",
				7114411223L,"sec 121","near statue luffy","Lucknow","Uttar Pradesh",
				110110,"India",null,null);
		entityManager.persistAndFlush(user1);
		User user2 = new User(null,"BPIPK5522M","user02",LocalDate.of(2020,8,9),"user02@rvy.com",
				7114410023L,"sec 123","near statue","Kanpur","Uttar Pradesh",
				110110,"India",null,null);
		entityManager.persistAndFlush(user2);
		List<User> userList = userRepo.findAll();
		if(userList.size()!=0) {
        	assertThat(userList.get(0).getName()).isEqualTo(user1.getName());
        }else {
        	assertThat(userList.get(0).getName()).isNotEqualTo(user1.getName());
        }
	}
	
	@Test
	public void whenAddUser_thenReturnAddedUser() {
		User user = new User(null,"BPIPC5522M","user01",LocalDate.of(2019,8,9),"user01@rvy.com",
				7114411223L,"sec 121","near statue luffy","Lucknow","Uttar Pradesh",
				110110,"India",null,null);
		entityManager.persistAndFlush(user);
		User userDb = userRepo.getById(user.getUserId());
		assertThat(userDb.getUserId()).isEqualTo(user.getUserId());
	}
	
}
