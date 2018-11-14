package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;


/**
 * The persistent class for the narudzbenica database table.
 * 
 */
@Entity
@Table(name="narudzbenica")
@NamedNativeQueries({

		@NamedNativeQuery(name = "getAllNarudzbenica", query = "SELECT * "
				+ "FROM narudzbenica", resultClass = Narudzbenica.class),
		@NamedNativeQuery(name = "getAllByIdNarudzbenica", query = "SELECT * " + "FROM narudzbenica "
				+ "WHERE narudzbenica_id = :narudzbenica_id", resultClass = Narudzbenica.class) 
		})
public class Narudzbenica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "narudzbenica_id")
	private int narudzbenicaId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;
	
	private String bicikle;
	
	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	public Narudzbenica() {
	}
	
	public int getNarudzbenicaId() {
		return this.narudzbenicaId;
	}

	public void setNarudzbenicaId(int narudzbenicaId) {
		this.narudzbenicaId = narudzbenicaId;
	}

	
	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	
	
	public String getBicikle() {
		return this.bicikle;
	}

	public void setBicikle(String bicikle) {
		this.bicikle = bicikle;
	}
}