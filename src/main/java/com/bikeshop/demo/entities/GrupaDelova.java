package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the grupa_delova database table.
 * 
 */
@Entity
@Table(name = "grupa_delova")
@NamedQuery(name = "GrupaDelova.findAll", query = "SELECT g FROM GrupaDelova g")
public class GrupaDelova implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "grupa_delova_id")
	private int grupaDelovaId;

	private String naziv;

	// bi-directional many-to-one association to Delovi
	@OneToMany(mappedBy = "grupaDelova")
	private List<Delovi> delovis;

	public GrupaDelova() {
	}

	public int getGrupaDelovaId() {
		return this.grupaDelovaId;
	}

	public void setGrupaDelovaId(int grupaDelovaId) {
		this.grupaDelovaId = grupaDelovaId;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Delovi> getDelovis() {
		return this.delovis;
	}

	public void setDelovis(List<Delovi> delovis) {
		this.delovis = delovis;
	}

	public Delovi addDelovi(Delovi delovi) {
		getDelovis().add(delovi);
		delovi.setGrupaDelova(this);

		return delovi;
	}

	public Delovi removeDelovi(Delovi delovi) {
		getDelovis().remove(delovi);
		delovi.setGrupaDelova(null);

		return delovi;
	}

}