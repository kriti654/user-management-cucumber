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

import com.rvy.store.dao.IRegionRepository;
import com.rvy.store.entity.Region;
import com.rvy.store.exceptions.RegionException;

@ExtendWith(SpringExtension.class)
public class RegionServiceTest {
	
	@TestConfiguration
	static class RegionTestContextConfiguration {

		@Bean
		public IRegionService regionService() {
			return new RegionService();
		}
	}
	
	@Autowired
	IRegionService regionService;
	
	@MockBean
	IRegionRepository regionRepo;
	
	List<Region> regionList;

	private Region region1;
	private Region region2;
	
	@BeforeEach
	public void setUp() {
		region1=new Region(null,"north delhi","delhi","new delhi","india",null,null);
		region2=new Region(null,"jaipur","jaipur","rajasthan","india",null,null);
		regionList = Arrays.asList(region1,region2);

		Mockito.when(regionRepo.findAll()).thenReturn(regionList);
		Mockito.when(regionRepo.findById(region1.getRegionId())).thenReturn(Optional.of(region1));
		Mockito.when(regionRepo.findById(-11)).thenReturn(Optional.empty());
		Mockito.when(regionRepo.save(region1)).thenReturn(region1);

	}

	@Test
	public void whenValidId_RegionDetailsBeFound(){
		try {
			Region region= regionService.getRegionById(region1.getRegionId());
			assertThat(region).isEqualTo(region1);
		} catch (RegionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenInValidId_ReturnNull() {
		try {
			Region region = regionService.getRegionById(null);
			assertThat(region).isNull();
		} catch (RegionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenRegionToAddShouldReturnAddedRegion() {
		try {
			Region region = regionService.addRegion(region1);
			assertThat(region).isEqualTo(region1);
		} catch (RegionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIdTODeleteThenShouldDeleteTheRegion() {
		try {
			Boolean region = regionService.deleteRegion(region1.getRegionId());
			assertThat(region).isEqualTo(true);
		} catch (RegionException e) {
			e.printStackTrace();
		}
	}

}
