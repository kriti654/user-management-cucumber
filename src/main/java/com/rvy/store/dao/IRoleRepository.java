package com.rvy.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvy.store.entity.Role;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
	

}
