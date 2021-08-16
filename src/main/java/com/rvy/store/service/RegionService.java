package com.rvy.store.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rvy.store.dao.IRegionRepository;
import com.rvy.store.entity.Region;
import com.rvy.store.entity.Taxation;
import com.rvy.store.exceptions.RegionException;
import com.rvy.store.exceptions.TaxationException;

@Service
@Transactional
public class RegionService implements IRegionService {
	@Autowired
    private IRegionRepository regionRepo;
	
	@Override
	public Region addRegion(Region region) throws RegionException {
		try {
			 region.setRegionId(null);
			 return regionRepo.save(region);
		}catch(DataAccessException e){
			throw new RegionException(e.getMessage(),e);
		}
	}

	@Override
	public Region getRegionById(Integer regionId) throws RegionException {
		try {
			Optional<Region> optional = 
									regionRepo.findById(regionId);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new PersistenceException("Invalid Region ID");
			}			
			
		}catch(DataAccessException e) {
			throw new RegionException(e.getMessage(),e);
		}
	}

	@Override
	public Boolean deleteRegion(Integer regionId) throws RegionException {
		try {
			regionRepo.deleteById(regionId);
			return true;
		}catch(DataAccessException e) {
			throw new RegionException(e.getMessage(),e);
		}
	}
	
}
