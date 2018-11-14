package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the delovi database table.
 * 
 */
@Entity
@Table(name = "delovi")
@NamedQuery(name = "Delovi.findAll", query = "SELECT d FROM Delovi d")
public class Delovi implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "delovi_id")
	private int deloviId;

	private String brend;

	private double cena;

	@Column(name = "korisnik_id")
	private int korisnikId;

	private String model;

	private String naziv;

	@Lob
	private String opis;

	private String slika;

	private String velicina;

	// bi-directional many-to-one association to GrupaDelova
	@ManyToOne
	@JoinColumn(name = "grupa_delova_id")
	private GrupaDelova grupaDelova;

	public Delovi() {
	}

	public int getDeloviId() {
		return this.deloviId;
	}

	public void setDeloviId(int deloviId) {
		this.deloviId = deloviId;
	}

	public String getBrend() {
		return this.brend;
	}

	public void setBrend(String brend) {
		this.brend = brend;
	}

	public double getCena() {
		return this.cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public int getKorisnikId() {
		return this.korisnikId;
	}

	public void setKorisnikId(int korisnikId) {
		this.korisnikId = korisnikId;
	}

	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return this.opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getSlika() {
		return this.slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getVelicina() {
		return this.velicina;
	}

	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}

	public GrupaDelova getGrupaDelova() {
		return this.grupaDelova;
	}

	public void setGrupaDelova(GrupaDelova grupaDelova) {
		this.grupaDelova = grupaDelova;
	}

}