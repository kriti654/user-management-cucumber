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

import com.rvy.store.dao.IStoreRepository;
import com.rvy.store.entity.Store;
import com.rvy.store.exceptions.StoreException;


@ExtendWith(SpringExtension.class)
public class StoreServiceTest{
	
	@TestConfiguration
	static class StoreTestContextConfiguration {

		@Bean
		public IStoreService storeService() {
			return new StoreService();
		}
	}

	@Autowired
	private IStoreService storeService;

	@MockBean
	private IStoreRepository storeRepo;
	List<Store> storeList;

	private Store store1;
	private Store store2;
	@BeforeEach
	public void setUp() {
		
		store1 = new Store(null, "Walmart", "Gurgaon, India", 903836L, "wal@gmail.com", null,null);
		store2 = new Store(null, "RVY ", "sec 123 delhi", 9038361034L, "rvy@gmail.com", null,null);
		storeList = Arrays.asList(store1,store2);

		Mockito.when(storeRepo.findAll()).thenReturn(storeList);
		Mockito.when(storeRepo.findById(store1.getStoreId())).thenReturn(Optional.of(store1));
		Mockito.when(storeRepo.findById(-11)).thenReturn(Optional.empty());
		Mockito.when(storeRepo.save(store1)).thenReturn(store1);

	}

	@Test
	public void whenValidId_StoreDetailsBeFound() {
		try {
			Store store = storeService.getStoreById(store1.getStoreId());
			assertThat(store).isEqualTo(store1);
		} catch (StoreException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void whenInValidId_ReturnNull() {
		try {
			Store store = storeService.getStoreById(null);
			assertThat(store).isNull();
		} catch (StoreException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenStoreToAddShouldReturnAddedStore() {
		try {
			Store store = storeService.addStore(store1);
			assertThat(store).isEqualTo(store1);
		} catch (StoreException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIdTODeleteThenShouldDeleteTheStore() {
		try {
			Boolean store = storeService.deleteStore(store1.getStoreId());
			assertThat(store).isEqualTo(true);
		} catch (StoreException e) {
			e.printStackTrace();
		}
	}
	

}
