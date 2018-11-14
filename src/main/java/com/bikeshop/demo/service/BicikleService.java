package com.bikeshop.demo.service;

import java.util.List;

import com.bikeshop.demo.entities.Bicikl;

public interface BicikleService {
	
	List<Bicikl> findAll();
	
	public List<Bicikl> findAllSort(String sort, String order);
	
	Bicikl readById(Integer id);
	
	public boolean delete(Bicikl obj);
	
	public Bicikl save(Bicikl obj);
	
	public Bicikl update(Bicikl obj);
	
	public Bicikl findByField(String field, String value);
	
	public List<Bicikl> findByFieldList(String field, String value);
	
	public List<Bicikl> findByFieldListSort(String field, String value, String sort, String order);
	
	public List<Bicikl> findAllSearch(String sort);
	
	public List<Bicikl> findByFieldListSearch(String field, String value, String sort);
}
