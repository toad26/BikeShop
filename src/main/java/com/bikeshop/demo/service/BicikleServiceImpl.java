package com.bikeshop.demo.service;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bikeshop.demo.dao.GenericDAO;
import com.bikeshop.demo.entities.Bicikl;

@Repository
public class BicikleServiceImpl implements BicikleService {
	
	@Autowired
	private GenericDAO<Bicikl> genericDAO;
	
	@Override
	public List<Bicikl> findAll() {
		return genericDAO.findAll(Bicikl.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bicikl> findAllSort(String sort, String order) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM bicikl ORDER BY "+sort+" "+order, Bicikl.class).getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}

	@Override
	public Bicikl readById(Integer id) {
		return genericDAO.readById(Bicikl.class, "id", id);
	}

	@Override
	public boolean delete(Bicikl obj) {
		return genericDAO.delete(obj);
	}

	@Override
	public Bicikl save(Bicikl obj) {
		return genericDAO.save(obj);
	}

	@Override
	public Bicikl update(Bicikl obj) {
		return genericDAO.update(obj);
	}
	
	@Override
	public Bicikl findByField(String field, String value) {
		try {
			return (Bicikl) genericDAO.getManager().createNativeQuery(
				"SELECT * FROM bicikl WHERE "+field+"=:value", Bicikl.class)
				.setParameter("value", value).getSingleResult();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bicikl> findByFieldList(String field, String value) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM bicikl WHERE "+field+"=:value", Bicikl.class)
				.setParameter("value", value).getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bicikl> findByFieldListSort(String field, String value, String sort, String order) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM bicikl WHERE "+field+"=:value ORDER BY "+sort+" "+order, Bicikl.class)
				.setParameter("value", value).getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bicikl> findAllSearch(String sort) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM bicikl WHERE model LIKE :search OR naziv LIKE :search OR brend LIKE :search", Bicikl.class).setParameter("search", "%"+sort+"%").getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bicikl> findByFieldListSearch(String field, String value, String sort) {
		try {
			return genericDAO.getManager().createNativeQuery(
				"SELECT * FROM bicikl WHERE "+field+"=:value AND ( model LIKE :search OR naziv LIKE :search OR brend LIKE :search)" , Bicikl.class)
				.setParameter("value", value).setParameter("search", "%"+sort+"%").getResultList();
		} catch (NoResultException nre){
			return null;
		}
	}
}
