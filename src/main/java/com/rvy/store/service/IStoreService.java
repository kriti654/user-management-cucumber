package com.rvy.store.service;

import java.util.List;

import com.rvy.store.entity.Store;
import com.rvy.store.exceptions.StoreException;

public interface IStoreService {
      public abstract Store getStoreById(Integer storeId) throws StoreException;
      public abstract List<Store> getStores() throws StoreException;
      public abstract Store addStore(Store store) throws StoreException;
      public abstract Store updateStore(Store store) throws StoreException;
      public abstract boolean deleteStore(Integer storeId) throws StoreException;
}


