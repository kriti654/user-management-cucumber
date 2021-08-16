package com.rvy.store.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rvy.store.entity.Store;

public interface IStoreRepository extends JpaRepository<Store, Integer> {
	
      
}
