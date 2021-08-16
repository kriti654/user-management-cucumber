package com.rvy.store.service;

import com.rvy.store.entity.Taxation;
import com.rvy.store.exceptions.TaxationException;

public interface ITaxationService {
         public abstract Taxation getTaxById(Integer taxId) throws TaxationException;
         public abstract Taxation addTax(Taxation tax) throws TaxationException;
         public abstract Boolean deleteTax(Integer taxId) throws TaxationException;
}
