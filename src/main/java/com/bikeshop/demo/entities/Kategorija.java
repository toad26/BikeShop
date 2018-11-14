package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.List;

/**
 * The persistent class for the kategorija database table.
 * 
 */
@Entity
@Table(name = "kategorija")
@NamedNativeQueries({
		@NamedNativeQuery(name = "getAllKategorija", query = "SELECT * "
				+ "FROM kategorija", resultClass = Kategorija.class),
		@NamedNativeQuery(name = "getAllByIdKategorija", query = "SELECT * " + "FROM kategorija "
				+ "WHERE kategorija_id = :kategorija_id", resultClass = Kategorija.class) })

@SqlResultSetMapping(name = "KategorijaMapping", entities = @EntityResult(entityClass = Kategorija.class, fields = {
		@FieldResult(name = "kategorija_id", column = "kategorijaId"),
		@FieldResult(name = "naziv", column = "naziv") }))

public class Kategorija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "kategorija_id")
	private int kategorijaId;

	@NotNull(message = "Name is a required field")
	private String naziv;

	// bi-directional many-to-one association to Bicikl
	@OneToMany(mappedBy = "kategorija")
	private List<Bicikl> bicikls;

	public Kategorija() {
	}

	public int getKategorijaId() {
		return this.kategorijaId;
	}

	public void setKategorijaId(int kategorijaId) {
		this.kategorijaId = kategorijaId;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Bicikl> getBicikls() {
		return this.bicikls;
	}

	public void setBicikls(List<Bicikl> bicikls) {
		this.bicikls = bicikls;
	}

	public Bicikl addBicikl(Bicikl bicikl) {
		getBicikls().add(bicikl);
		bicikl.setKategorija(this);

		return bicikl;
	}

	public Bicikl removeBicikl(Bicikl bicikl) {
		getBicikls().remove(bicikl);
		bicikl.setKategorija(null);

		return bicikl;
	}
}