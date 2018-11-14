package com.bikeshop.demo.service;

import java.util.List;

import com.bikeshop.demo.entities.Narudzbenica;

public interface NarudzbenicaService {

	List<Narudzbenica> findAll();

	Narudzbenica readById(Integer id);

	public boolean delete(Narudzbenica obj);

	public Narudzbenica save(Narudzbenica obj);

	public Narudzbenica update(Narudzbenica obj);

	public Narudzbenica findByField(String field, String value);
}
