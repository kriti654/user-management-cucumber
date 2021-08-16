package com.rvy.store.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rvy.store.dao.IStoreRepository;
import com.rvy.store.entity.Store;
import com.rvy.store.exceptions.StoreException;

@Service
@Transactional
public class StoreService implements IStoreService{

	@Autowired
	private IStoreRepository storeRepo;


	@Override
	public Store getStoreById(Integer storeId) throws StoreException {
		try {
			Optional<Store> optional =
					storeRepo.findById(storeId);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new PersistenceException("Invalid Store ID");
			}

		}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}
	}

	@Override
	public List<Store> getStores() throws StoreException {
		try {
			List<Store> storeList = storeRepo.findAll();
			return storeList;
		} catch (DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}

	}

	@Override
	public Store addStore(Store store) throws StoreException {
		try {
			store.setStoreId(null);
			return storeRepo.save(store);
		}catch(DataAccessException e){
			throw new StoreException(e.getMessage(),e);
		}
	}

	@Override
	public Store updateStore(Store store) throws StoreException {
		try {
			return storeRepo.save(store);
		} catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}
	}

	@Override
	public boolean deleteStore(Integer storeId) throws StoreException {
		try {
			storeRepo.deleteById(storeId);
			return true;
		}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}
	}

//		@Override
//		public List<Store> getByRegionId(Integer regionId) throws StoreException {
//			try {
//				return storeRepo.findByRegionId(regionId);
//			} catch(DataAccessException e) {
//				throw new StoreException(e.getMessage(),e);
//			}
//	
//	   }

}
