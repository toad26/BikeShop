package com.bikeshop.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bikeshop.demo.dao.GenericDAO;
import com.bikeshop.demo.entities.Kategorija;

@Repository
@Transactional
public class KategorijaServiceImpl implements KategorijaService {

	@Autowired
	private GenericDAO<Kategorija> genericDAO;

	@Override
	public List<Kategorija> findAll() {
		return genericDAO.findAll(Kategorija.class);
	}

	@Override
	public Kategorija readById(Integer id) {
		return genericDAO.readById(Kategorija.class, "kategorija_id", id);
	}

	@Override
	public boolean delete(Kategorija obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Kategorija save(Kategorija obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Kategorija update(Kategorija obj) {
		return genericDAO.update(obj);
	}

}
