package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

import com.bikeshop.demo.annotations.UniqueKorisnik;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@Table(name = "korisnik")
@NamedNativeQueries({
		@NamedNativeQuery(name = "getAllKorisnik", query = "SELECT * " + "FROM korisnik", resultClass = Korisnik.class),
		@NamedNativeQuery(name = "getAllByIdKorisnik", query = "SELECT * " + "FROM korisnik "
				+ "WHERE id = :id", resultClass = Korisnik.class) })
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@NotNull
	@Size(min = 3, max = 32, message = "Adresa mora imati vise od 3 slova.")
	private String adresa;

	@NotNull(message = "Datum je prazan ili pogresno upisan.")
	@Temporal(TemporalType.DATE)
	private Date datum;

	@UniqueKorisnik(field = "email")
	@Email(message = "Neispravan email.")
	@NotNull(message = "Email je prazan.")
	private String email;

	@NotNull
	@Size(min = 3, max = 32, message = "Ime mora imati vise od 3 slova.")
	private String ime;

	@UniqueKorisnik(field = "korisnicko_ime", message = "Korisnicko ime nije jedinstveno.")
	@NotNull(message = "Korisnicko ime je prazno.")
	@Size(min = 3, max = 32, message = "Korisnicko ime mora imati vise od 3 slova.")
	@Column(name = "korisnicko_ime")
	private String korisnickoIme;

	@NotNull(message = "Prezime je prazno.")
	@Size(min = 3, max = 32, message = "Prezime mora imati vise od 3 slova.")
	private String prezime;

	@NotNull(message = "Sifra ime je prazna.")
	@Size(min = 6, max = 32, message = "Sifra mora imati vise od 6 slova.")
	@Transient
	private String sifra;

	@NotNull(message = "Telefon ime je prazan.")
	@Size(min = 3, max = 32, message = "Ime mora imati vise od 3 slova.")
	private String telefon;

	// bi-directional many-to-one association to CustomSnimi
	@OneToMany(mappedBy = "korisnik")
	private List<CustomSnimi> customSnimis;

	// bi-directional many-to-one association to Permisije
	@ManyToOne
	private Permisije permisije;

	// bi-directional many-to-one association to Narudzbenica
	@OneToMany(mappedBy = "korisnik")
	private List<Narudzbenica> narudzbenicas;

	public Korisnik() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdresa() {
		return this.adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getSifra() {
		return this.sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getTelefon() {
		return this.telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public List<CustomSnimi> getCustomSnimis() {
		return this.customSnimis;
	}

	public void setCustomSnimis(List<CustomSnimi> customSnimis) {
		this.customSnimis = customSnimis;
	}

	public CustomSnimi addCustomSnimi(CustomSnimi customSnimi) {
		getCustomSnimis().add(customSnimi);
		customSnimi.setKorisnik(this);

		return customSnimi;
	}

	public CustomSnimi removeCustomSnimi(CustomSnimi customSnimi) {
		getCustomSnimis().remove(customSnimi);
		customSnimi.setKorisnik(null);

		return customSnimi;
	}

	public Permisije getPermisije() {
		return this.permisije;
	}

	public void setPermisije(Permisije permisije) {
		this.permisije = permisije;
	}

	public List<Narudzbenica> getNarudzbenicas() {
		return this.narudzbenicas;
	}

	public void setNarudzbenicas(List<Narudzbenica> narudzbenicas) {
		this.narudzbenicas = narudzbenicas;
	}

	public Narudzbenica addNarudzbenica(Narudzbenica narudzbenica) {
		getNarudzbenicas().add(narudzbenica);
		narudzbenica.setKorisnik(this);

		return narudzbenica;
	}

	public Narudzbenica removeNarudzbenica(Narudzbenica narudzbenica) {
		getNarudzbenicas().remove(narudzbenica);
		narudzbenica.setKorisnik(null);

		return narudzbenica;
	}
}