package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * The persistent class for the bicikl database table.
 * 
 */
@Entity
@Table(name = "bicikl")
@NamedNativeQueries({
		@NamedNativeQuery(name = "getAllBicikl", query = "SELECT * " + "FROM bicikl", resultClass = Bicikl.class),
		@NamedNativeQuery(name = "getAllByIdBicikl", query = "SELECT * " + "FROM bicikl "
				+ "WHERE bicikl_id = :id", resultClass = Bicikl.class) })
public class Bicikl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "bicikl_id")
	private int biciklId;

	@NotNull(message = "Brend je prazan.")
	@Size(min = 3, max = 20, message = "Brend mora imati vise od 3 slova.")
	private String brend;

	@Positive(message = "Mora biti vece od 0.")
	@NotNull(message = "Cena je prazna.")
	@Max(value = 10000, message = "Najveca cena.")
	private double cena;

	@Pattern(regexp = "[1-9]\\d*|0\\d+", message = "Mora biti broj.")
	@NotNull(message = "Godina je prazan.")
	@Size(min = 4, max = 4, message = "Godina mora imati tacno 4 karaktera.")
	@Column(name = "godina_proizvodnje")
	private String godinaProizvodnje;

	@NotNull(message = "Model je prazan.")
	@Size(min = 3, max = 20, message = "Model mora imati vise od 3 slova.")
	private String model;

	@NotNull(message = "Naziv je prazan.")
	@Size(min = 3, max = 20, message = "Naziv mora imati vise od 3 slova.")
	private String naziv;

	@Pattern(regexp = "([^\\s]+(\\.(?i)(jpeg|jpg|png|gif|bmp))$)", message = "Ekstenzija nije dozvoljena.")
	@NotNull(message = "Slika je prazna.")
	private String slika;

	@Lob
	@NotNull(message = "Specifikacija je prazna.")
	@Size(min = 3, message = "Specifikacija mora imati vise od 3 slova.")
	private String specifikacija;

	@NotNull(message = "Velicina je prazna.")
	@Size(min = 1, max = 3, message = "Velicina mora imati vise od 3 slova.")
	private String velicina;

	// bi-directional many-to-one association to Kategorija
	@ManyToOne
	@JoinColumn(name = "kategorija_id")
	private Kategorija kategorija;

	@Pattern(regexp = "([^\\s]+(\\.(?i)(jpeg|jpg|png|gif|bmp))$)", message = "Ekstenzija nije dozvoljena.")
	@NotNull(message = "Slika je prazna.")
	private String naslovnaSlika;

	public Bicikl() {
	}

	public int getBiciklId() {
		return this.biciklId;
	}

	public void setBiciklId(int biciklId) {
		this.biciklId = biciklId;
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

	public String getGodinaProizvodnje() {
		return this.godinaProizvodnje;
	}

	public void setGodinaProizvodnje(String godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}

	public String getModel() {
		return this.model;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
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

	public String getSpecifikacija() {
		return this.specifikacija;
	}

	public void setSpecifikacija(String specifikacija) {
		this.specifikacija = specifikacija;
	}

	public String getVelicina() {
		return this.velicina;
	}

	public void setVelicina(String velicina) {
		this.velicina = velicina;
	}

	public Kategorija getKategorija() {
		return this.kategorija;
	}

	public void setKategorija(Kategorija kategorija) {
		this.kategorija = kategorija;
	}

	public String getNaslovnaSlika() {
		return this.naslovnaSlika;
	}

	public void setNaslovnaSlika(String naslovnaSlika) {
		this.naslovnaSlika = naslovnaSlika;
	}
}