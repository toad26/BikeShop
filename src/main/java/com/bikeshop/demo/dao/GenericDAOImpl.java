package com.bikeshop.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public EntityManager getManager() {
		return manager;
	}

	@Override
	public List<T> findAll(Class<T> c) {
		List<T> kategorije = manager.createNamedQuery("getAll" + c.getSimpleName(), c).getResultList();
		return kategorije;
	}

	@Override
	public T readById(Class<T> c, String columnName, Integer valueColumn) {
		T kategorija = manager.createNamedQuery("getAllById" + c.getSimpleName(), c)
				.setParameter(columnName, valueColumn).getSingleResult();
		return kategorija;
	}

	@Override
	public T update(T obj) {
		try {
			manager.merge(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return obj;
	}

	@Override
	public boolean delete(Object obj) {
		try {
			manager.remove(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public T save(T obj) {
		try {
			manager.persist(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return obj;
	}

}
