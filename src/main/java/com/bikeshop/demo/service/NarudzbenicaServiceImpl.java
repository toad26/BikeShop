package com.bikeshop.demo.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bikeshop.demo.dao.GenericDAO;
import com.bikeshop.demo.entities.Narudzbenica;

@Repository
public class NarudzbenicaServiceImpl implements NarudzbenicaService {
	
	@Autowired
	private GenericDAO<Narudzbenica> genericDAO;
	
	@Override
	public List<Narudzbenica> findAll() {
		return genericDAO.findAll(Narudzbenica.class);
	}

	@Override
	public Narudzbenica readById(Integer id) {
		return genericDAO.readById(Narudzbenica.class, "narudzbenica_id", id);
	}

	@Override
	public boolean delete(Narudzbenica obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Narudzbenica save(Narudzbenica obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Narudzbenica update(Narudzbenica obj) {
		return genericDAO.update(obj);
	}
	
	
	@Override
	public Narudzbenica findByField(String field, String value) {
		try {
			return (Narudzbenica) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM narudzbenica WHERE "+field+"=:value", Narudzbenica.class)
				.setParameter("value", value).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
}
