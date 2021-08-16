package com.rvy.store.service;


import com.rvy.store.entity.Region;
import com.rvy.store.exceptions.RegionException;

public interface IRegionService {
	  public abstract Region addRegion(Region region) throws RegionException;
	  public abstract Region getRegionById(Integer regionId) throws RegionException;
	  public abstract Boolean deleteRegion(Integer regionId) throws RegionException;
}
