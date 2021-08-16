package com.rvy.store.dao;

import static org.assertj.core.api.Assertions.assertThat;

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
import com.rvy.store.entity.Store;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StoreDemoApplication.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class StoreRepositoryIntegrationTest {
	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private IStoreRepository storeRepo ;
	
	@Test
	public void whenFindById_thenReturnType() {
		
		Store store = new Store(null, "Walmart", "sec 121 Gurgaon", 9038361234L, "wal@gmail.com", null,null);
		entityManager.persistAndFlush(store);		
		Store storeDb = storeRepo.findById(store.getStoreId()).orElse(null);		
		assertThat(storeDb.getStoreId()).isEqualTo(store.getStoreId());
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		Store storeDb = storeRepo.findById(-11).orElse(null);		
        assertThat(storeDb).isNull();
	}
	
	
	@Test
	public void whenFindAllStore_thenReturnStoreList() {
		
		Store store1 = new Store(null, "Walmart", "sec 121 Gurgaon", 9038361234L, "wal@gmail.com", null,null);
		entityManager.persistAndFlush(store1);
		Store store2 = new Store(null, "RVY ", "sec 123 delhi", 9038361034L, "rvy@gmail.com", null,null);
		entityManager.persistAndFlush(store2);
		List<Store> storeList = storeRepo.findAll();
		if(storeList.size()!=0) {
        	assertThat(storeList.get(0).getName()).isEqualTo(store1.getName());
        }else {
        	assertThat(storeList.get(0).getName()).isNotEqualTo(store1.getName());
        }
	}
	
	@Test
	public void whenAddStore_thenReturnAddedStore() {
		Store store = new Store(null, "Walmart", "sec 121 Gurgaon", 9038361234L, "wal@gmail.com", null,null);
		entityManager.persistAndFlush(store);
		Store storeDb = storeRepo.getById(store.getStoreId());
		assertThat(storeDb.getStoreId()).isEqualTo(store.getStoreId());
	}
}
