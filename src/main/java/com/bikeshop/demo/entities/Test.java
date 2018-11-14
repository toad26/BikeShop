package com.bikeshop.demo.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the permisije database table.
 * 
 */
@Entity
@Table(name="test")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String ime;


	public Test() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

}