package com.rvy.store.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rvy.store.dao.IStoreRepository;
import com.rvy.store.entity.Store;
import com.rvy.store.exceptions.StoreException;
import com.rvy.store.service.StoreService;

public class StoreControllerTest {

//	private IStoreRepository storeRepository = Mockito.mock(IStoreRepository.class);
//
//	@Test
//	@DisplayName("Should find store by Id")
//	void findStoreByIdTest() throws StoreException {
//		StoreService storeService = new StoreService(storeRepository);
//		Store store = new Store(123, "Walmart", "sec 121 Gurgaon", 9038361234L, "wal@gmail.com", null,null);
//		Mockito.when(storeRepository.findById(123)).thenReturn(Optional.of(store));
//		Store storeOutput = storeService.getStoreById(123);
//		Assertions.assertThat(storeOutput.getStoreId()).isEqualTo(store.getStoreId());
//	}
//
//	@Test
//	@DisplayName("Find all stores")
//	void findAllStoreTest() throws StoreException {
//		StoreService storeService = new StoreService(storeRepository);
//		List<Store> storeList = new ArrayList<Store>();
//		storeList.add(new Store(123, "Walmart", "Gurgaon, India", 903836L, "wal@gmail.com", null,null));
//		storeList.add(new Store(124, "Samsung", "Kolkata, India", 928216L, "sam@gmail.com", null,null));
//		Mockito.when(storeRepository.findAll()).thenReturn(storeList);
//		List<Store> storeOutput = storeService.getStores();
//		Assertions.assertThat(storeOutput).isEqualTo(storeList);
//	}
//	
}
