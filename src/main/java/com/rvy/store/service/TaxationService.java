package com.rvy.store.service;

import java.util.Optional;
import javax.persistence.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rvy.store.dao.ITaxationRepository;
import com.rvy.store.entity.Taxation;
import com.rvy.store.exceptions.StoreException;
import com.rvy.store.exceptions.TaxationException;

@Service
@Transactional
public class TaxationService implements ITaxationService {
	@Autowired
    private ITaxationRepository taxRepo;
	@Override
	public Taxation getTaxById(Integer taxId) throws TaxationException {
		try {
			Optional<Taxation> optional = 
									taxRepo.findById(taxId);
			if(optional.isPresent()) {
				return optional.get();
			}else {
				throw new PersistenceException("Invalid Tax ID");
			}			
			
		}catch(DataAccessException e) {
			throw new TaxationException(e.getMessage(),e);
		}
	}
	@Override
	public Taxation addTax(Taxation tax) throws TaxationException {
		try {
			 tax.setTaxId(null);
			 return taxRepo.save(tax);
		}catch(DataAccessException e){
			throw new TaxationException(e.getMessage(),e);
		}
	}
	

	@Override
	public Boolean deleteTax(Integer taxId) throws TaxationException {
		try {
			taxRepo.deleteById(taxId);
			return true;
		}catch(DataAccessException e) {
			throw new TaxationException(e.getMessage(),e);
		}
	}


}
