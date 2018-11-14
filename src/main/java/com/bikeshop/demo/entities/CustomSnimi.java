package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the custom_snimi database table.
 * 
 */
@Entity
@Table(name = "custom_snimi")
@NamedQuery(name = "CustomSnimi.findAll", query = "SELECT c FROM CustomSnimi c")
public class CustomSnimi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "custom_snimi_id")
	private int customSnimiId;

	@Column(name = "delovi_id")
	private String deloviId;

	private String ime;

	// bi-directional many-to-one association to Korisnik
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Korisnik korisnik;

	public CustomSnimi() {
	}

	public int getCustomSnimiId() {
		return this.customSnimiId;
	}

	public void setCustomSnimiId(int customSnimiId) {
		this.customSnimiId = customSnimiId;
	}

	public String getDeloviId() {
		return this.deloviId;
	}

	public void setDeloviId(String deloviId) {
		this.deloviId = deloviId;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

}