package com.bikeshop.demo.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bikeshop.demo.dao.GenericDAO;
import com.bikeshop.demo.entities.Korisnik;

@Repository
public class KorisnikServiceImpl implements KorisnikService {

	@Autowired
	private GenericDAO<Korisnik> genericDAO;

	@Override
	public List<Korisnik> findAll() {
		return genericDAO.findAll(Korisnik.class);
	}

	@Override
	public Korisnik readById(Integer id) {
		return genericDAO.readById(Korisnik.class, "id", id);
	}

	@Override
	public boolean delete(Korisnik obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Korisnik save(Korisnik obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Korisnik update(Korisnik obj) {
		return genericDAO.update(obj);
	}

	public Korisnik findByName(String username, String password) {
		try {
			return (Korisnik) genericDAO.getManager()
					.createNativeQuery("SELECT * FROM korisnik WHERE korisnicko_ime=:username AND sifra=:sifra",
							Korisnik.class)
					.setParameter("username", username).setParameter("sifra", password).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public Korisnik findByField(String field, String value) {
		try {
			return (Korisnik) genericDAO.getManager()
					.createNativeQuery("SELECT * FROM korisnik WHERE " + field + "=:value", Korisnik.class)
					.setParameter("value", value).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Override
	public Korisnik findByUsername(String username) {

		try {
			return (Korisnik) genericDAO.getManager()
					.createNativeQuery("SELECT * FROM korisnik WHERE korisnicko_ime=:korisnicko_ime", Korisnik.class)
					.setParameter("korisnicko_ime", username).getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
