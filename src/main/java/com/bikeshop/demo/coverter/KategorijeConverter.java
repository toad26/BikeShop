package com.bikeshop.demo.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bikeshop.demo.entities.Kategorija;
import com.bikeshop.demo.service.KategorijaService;

@Component
public class KategorijeConverter implements Converter<String, Kategorija> {

@Autowired
private KategorijaService kategorijaService;

	@Override
	public Kategorija convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return kategorijaService.readById(id);
	}
}

