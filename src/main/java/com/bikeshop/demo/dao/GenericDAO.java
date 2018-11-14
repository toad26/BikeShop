package com.bikeshop.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;

public interface GenericDAO<T> {

	EntityManager getManager();

	// get all from db table
	List<T> findAll(Class<T> c);

	// get by id
	T readById(Class<T> c, String columnName, Integer valueColumn);

	// update row in db
	T update(T obj);

	// delete row from db
	boolean delete(T obj);

	// save row to db
	T save(T obj);
}
