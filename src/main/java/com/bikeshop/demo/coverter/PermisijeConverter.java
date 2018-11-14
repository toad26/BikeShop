package com.bikeshop.demo.coverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.bikeshop.demo.entities.Permisije;
import com.bikeshop.demo.service.PermisijeService;

@Component
public class PermisijeConverter implements Converter<String, Permisije> {

@Autowired
private PermisijeService permisijeService;

	@Override
	public Permisije convert(String arg0) {
	    Integer id = new Integer(arg0);
	    return permisijeService.readById(id);
	}
}
