package com.bikeshop.demo.service;

import java.util.List;

import com.bikeshop.demo.entities.Permisije;

public interface PermisijeService {
	
	List<Permisije> findAll();
	
	Permisije readById(Integer id);
	
	public boolean delete(Permisije obj);
	
	public Permisije save(Permisije obj);
	
	public Permisije update(Permisije obj);
}
