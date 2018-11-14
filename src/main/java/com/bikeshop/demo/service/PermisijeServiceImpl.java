package com.bikeshop.demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bikeshop.demo.dao.GenericDAO;
import com.bikeshop.demo.entities.Permisije;

@Repository
@Transactional
public class PermisijeServiceImpl implements PermisijeService {
	
	@Autowired
	private GenericDAO<Permisije> genericDAO;
	
	@Override
	public List<Permisije> findAll() {
		return genericDAO.findAll(Permisije.class);
	}

	@Override
	public Permisije readById(Integer id) {
		return genericDAO.readById(Permisije.class, "id", id);
	}

	@Override
	public boolean delete(Permisije obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Permisije save(Permisije obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Permisije update(Permisije obj) {
		return genericDAO.update(obj);
	}

}
