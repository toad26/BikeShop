package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the permisije database table.
 * 
 */
@Entity
@Table(name = "permisije")
@NamedNativeQueries({
		@NamedNativeQuery(name = "getAllPermisije", query = "SELECT * "
				+ "FROM permisije", resultClass = Permisije.class),
		@NamedNativeQuery(name = "getAllByIdPermisije", query = "SELECT * " + "FROM permisije "
				+ "WHERE id = :id", resultClass = Permisije.class) })
public class Permisije implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String naziv;

	// bi-directional many-to-one association to Korisnik
	@OneToMany(mappedBy = "permisije")
	private List<Korisnik> korisniks;

	public Permisije() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Korisnik> getKorisniks() {
		return this.korisniks;
	}

	public void setKorisniks(List<Korisnik> korisniks) {
		this.korisniks = korisniks;
	}

	public Korisnik addKorisnik(Korisnik korisnik) {
		getKorisniks().add(korisnik);
		korisnik.setPermisije(this);

		return korisnik;
	}

	public Korisnik removeKorisnik(Korisnik korisnik) {
		getKorisniks().remove(korisnik);
		korisnik.setPermisije(null);

		return korisnik;
	}

	public boolean isSelectedPermisija(Integer permisijeId) {
		if (permisijeId != null) {
			return permisijeId.equals(id);
		}
		return false;
	}
}