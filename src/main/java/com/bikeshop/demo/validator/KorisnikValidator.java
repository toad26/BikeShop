package com.bikeshop.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.bikeshop.demo.annotations.UniqueKorisnik;
import com.bikeshop.demo.service.KorisnikService;


public class KorisnikValidator implements ConstraintValidator<UniqueKorisnik, String> {   
  
    @Autowired
    private KorisnikService korisnikService;
    
    private String field;
    
    @Override
	public void initialize(UniqueKorisnik valid) {
    	this.field = valid.field();
	}
    
    @Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
    	
    	try {
    		if( korisnikService.findByField(field, value) == null)
        		return true;
		} catch (Exception e) {
			return true;
		}

		return false;
	}   
}