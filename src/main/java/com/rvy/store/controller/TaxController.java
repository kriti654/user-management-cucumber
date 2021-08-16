package com.rvy.store.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rvy.store.entity.Store;
import com.rvy.store.entity.Taxation;
import com.rvy.store.exceptions.StoreException;
import com.rvy.store.exceptions.TaxationException;
import com.rvy.store.service.ITaxationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@Api(tags = "Tax Controller")
public class TaxController {
	@Autowired
    private ITaxationService taxService;
	@ApiOperation(
			value = "Find Tax By ID",
			consumes = "Tax ID",
			produces = "Tax object",
			response = Store.class,
			notes = "http://localhost:8081/rvy/um/v1/tax/1"
			)
	@GetMapping("/tax/{taxId}")
	public ResponseEntity<Taxation> findTaxById(@PathVariable("taxId") 
	                                           Integer taxId){
		try {
			return new ResponseEntity<>(
					 taxService.getTaxById(taxId)
					,HttpStatus.OK);
		} catch(TaxationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	@ApiOperation(value = "Add Tax",
			consumes = "Tax object",
			produces = "Tax object",
			response = Taxation.class,
			notes = "http://localhost:8081/rvy/um/v1/tax"
			)
	@PostMapping("/tax")
	public ResponseEntity<Taxation> addTax(@Valid @RequestBody Taxation tax,
			BindingResult bindingResult) {
		try {
		if(bindingResult.hasErrors()) {
			throw new TaxationException(bindingResult.getAllErrors().toString());
		}
	      return ResponseEntity.ok().body(taxService.addTax(tax));	
	   } catch(TaxationException e) {
		   throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
				   e.getMessage());
	   }
	}
	
	@ApiOperation(value = "Delete Tax",
			produces = "Boolean indication of deleted object",
			response = Boolean.class,
			notes = "http://localhost:8081/rvy/um/v1/tax/1"
			)
	@DeleteMapping("/tax/{taxId}")
	public ResponseEntity<Boolean> deleteTax(@PathVariable("taxId")
	Integer taxId) {
		try {
			return new ResponseEntity<>(taxService.deleteTax(taxId), HttpStatus.OK);
		} catch(TaxationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
}
