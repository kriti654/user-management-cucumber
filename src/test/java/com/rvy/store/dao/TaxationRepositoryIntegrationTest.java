package com.rvy.store.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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
import com.rvy.store.entity.Taxation;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { StoreDemoApplication.class })



@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class TaxationRepositoryIntegrationTest {

	@Autowired 
	private TestEntityManager entityManager;
	
	@Autowired
	private ITaxationRepository taxationRepo ;
	
	
	
	@Test
	public void whenFindById_thenReturnType() {
		
		Taxation taxation  =   new Taxation(null,1100010002,"GST",18.0f,LocalDate.of(2021, 1, 12),null);
		entityManager.persistAndFlush(taxation);		
		Taxation taxationDb = taxationRepo.findById(taxation.getTaxId()).orElse(null);		
		 assertThat(taxationDb.getTaxId()).isEqualTo(taxation.getTaxId());
	}
	
	@Test
	public void whenInvalidid_thenReturnNull() {
		Taxation taxationDb = taxationRepo.findById((int) -11l).orElse(null);
        assertThat(taxationDb).isNull();
	}
	
	

	
	@Test
	public void whenFindByTaxName_thenReturnTaxName() {
		Taxation tax  = new Taxation(null,1100010003,"GST",21.0f,LocalDate.of(2018, 2, 11),null);
		entityManager.persistAndFlush(tax);
		
		List<Taxation> taxList = taxationRepo.findByName(tax.getName());
		if(taxList.size()!=0) {
        	assertThat(taxList.get(0).getName()).isEqualTo(tax.getName());
        }else {
        	assertThat(taxList.get(0).getName()).isNotEqualTo(tax.getName());
        }
	}
	
	@Test
	public void whenFindByInvalidTaxName_thenReturnNull() {
		Taxation tax  = new Taxation(null,1100010004,"GST",15.0f,LocalDate.of(2019, 1, 8),null);
		entityManager.persistAndFlush(tax);
		
		List<Taxation> taxList = taxationRepo.findByName("Invalid Name");
		
		assertThat(taxList).isEmpty();
	}

	
}
	
