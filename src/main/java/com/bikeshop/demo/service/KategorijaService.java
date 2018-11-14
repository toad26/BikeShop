package com.bikeshop.demo.service;

import java.util.List;

import com.bikeshop.demo.entities.Kategorija;

public interface KategorijaService {

	List<Kategorija> findAll();

	Kategorija readById(Integer id);

	public boolean delete(Kategorija obj);

	public Kategorija save(Kategorija obj);

	public Kategorija update(Kategorija obj);
}
