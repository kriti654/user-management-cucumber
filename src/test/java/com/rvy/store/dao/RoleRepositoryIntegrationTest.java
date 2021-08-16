package com.rvy.store.dao;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.rvy.store.entity.Role;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StoreDemoApplication.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class RoleRepositoryIntegrationTest {
	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private IRoleRepository roleRepo ;
	
	@Test
	public void whenFindById_thenReturnType() {
		
		Role role  =   new Role(null,"Manager",null);
		entityManager.persistAndFlush(role);		
		Role roleDb = roleRepo.findById(role.getRoleId()).orElse(null);		
		assertThat(roleDb.getRoleId()).isEqualTo(role.getRoleId());
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		Role roleDb = roleRepo.findById(-11).orElse(null);		
        assertThat(roleDb).isNull();
	}
	
	@Test
	public void whenAddRole_thenReturnAddedRole() {
		
		Role role  =   new Role(null,"Manager",null);
		entityManager.persistAndFlush(role);		
		Role roleDb = roleRepo.getById(role.getRoleId());
		assertThat(roleDb.getRoleId()).isEqualTo(role.getRoleId());
	}

}
