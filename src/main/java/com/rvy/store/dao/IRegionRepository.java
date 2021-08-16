package com.rvy.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rvy.store.entity.Region;
import com.rvy.store.entity.Store;

public interface IRegionRepository extends JpaRepository<Region, Integer>{
	List<Store> findStoresByRegionId(Integer regionId);
}
