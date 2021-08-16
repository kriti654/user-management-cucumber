package com.rvy.store.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvy.store.entity.Taxation;

public interface ITaxationRepository extends JpaRepository<Taxation, Integer>{
	List<Taxation> findByName(String name);
}
