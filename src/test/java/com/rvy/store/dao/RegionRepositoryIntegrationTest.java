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
import com.rvy.store.entity.Region;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StoreDemoApplication.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class RegionRepositoryIntegrationTest {
	
	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private IRegionRepository regionRepo ;
	
	@Test
	public void whenFindById_thenReturnType() {
		
		Region region  =   new Region(null,"north delhi","delhi","new delhi","india",null,null);
		entityManager.persistAndFlush(region);		
		Region regionDb = regionRepo.findById(region.getRegionId()).orElse(null);		
		 assertThat(regionDb.getRegionId()).isEqualTo(region.getRegionId());
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		Region regionDb = regionRepo.findById(-11).orElse(null);		
        assertThat(regionDb).isNull();
	}
	
	@Test
	public void whenAddRegion_thenReturnAddedRegion() {
		Region region  =   new Region(null,"north delhi","delhi","new delhi","india",null,null);
		entityManager.persistAndFlush(region);		
		Region regionDb = regionRepo.getById(region.getRegionId());		
		assertThat(regionDb.getRegionId()).isEqualTo(region.getRegionId());
	}
}
