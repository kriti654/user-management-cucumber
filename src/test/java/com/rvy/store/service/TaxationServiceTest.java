package com.rvy.store.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
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

import com.rvy.store.dao.ITaxationRepository;
import com.rvy.store.entity.Taxation;
import com.rvy.store.exceptions.TaxationException;



@ExtendWith(SpringExtension.class)
public class TaxationServiceTest {
	
	@TestConfiguration
	static class TaxationTestContextConfiguration {
		
		@Bean
		public ITaxationService taxationService() {
			return new TaxationService();
		}
	}

	@Autowired
	private ITaxationService taxationService;
	
	@MockBean
	private ITaxationRepository taxationRepo;
	List<Taxation> taxList;
	
	private Taxation tax1;
	private Taxation tax2;
	@BeforeEach
	public void setUp() {
		 tax1=new Taxation(null,1100010001,"GST",18.0f,LocalDate.of(2021, 1, 12),null);
		 tax1=new Taxation(null,1100010002,"GST",18.0f,LocalDate.of(2020, 1, 12),null);
		 taxList = Arrays.asList(tax1,tax2);
		
		Mockito.when(taxationRepo.findAll()).thenReturn(taxList);
		Mockito.when(taxationRepo.findById(tax1.getTaxId())).thenReturn(Optional.of(tax1));
		Mockito.when(taxationRepo.save(tax1)).thenReturn(tax1);

	}
	
	@Test
	public void whenValidId_TaxDetailsBeFound() {
		try {
			Taxation tax = taxationService.getTaxById(tax1.getTaxId());
			assertThat(tax).isEqualTo(tax1);
		} catch (TaxationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenTaxToAddShouldReturnAddedTax() {
		try {
			Taxation tax = taxationService.addTax(tax1);
			assertThat(tax).isEqualTo(tax1);
		} catch (TaxationException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void givenIdTODeleteThenShouldDeleteTheTax() {
		try {
			Boolean tax = taxationService.deleteTax(tax1.getTaxId());
			assertThat(tax).isEqualTo(true);
		} catch (TaxationException e) {
			e.printStackTrace();
		}
	}
	
}