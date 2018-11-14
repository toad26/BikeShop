package com.bikeshop.demo.service;

import java.util.List;

import com.bikeshop.demo.entities.Korisnik;

public interface KorisnikService {

	List<Korisnik> findAll();

	Korisnik readById(Integer id);

	public boolean delete(Korisnik obj);

	public Korisnik save(Korisnik obj);

	public Korisnik update(Korisnik obj);

	public Korisnik findByName(String username, String password);

	public Korisnik findByField(String field, String value);

	Korisnik findByUsername(String username);
}
