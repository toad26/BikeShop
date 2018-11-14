package com.bikeshop.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bikeshop.demo.entities.Kategorija;
import com.bikeshop.demo.entities.Korisnik;
import com.bikeshop.demo.service.KategorijaService;
import com.bikeshop.demo.service.KorisnikService;

@Controller
public class CategoryController {

	@Autowired
	private KategorijaService kategorijaDAO;

	@Autowired
	private KorisnikService korisnikDAO;

	@GetMapping("/saveCategory")
	public String saveCategory(HttpServletRequest request, Model model, HttpSession session) {
		List<Kategorija> prod = kategorijaDAO.findAll();
		Korisnik k = korisnikDAO.findByName("kuna666", "12345");

		Korisnik o = (Korisnik) session.getAttribute("user");
		o.getPermisije().getNaziv();
		System.out.println(k.getEmail());
		model.addAttribute("prods", prod);

		return "home";
	}

	@PostMapping("/saveCategory")
	public String saveCategory(@Valid Kategorija k, HttpServletRequest request, Model model) {

		k.setNaziv("Vrsta");

		List<Kategorija> prod = kategorijaDAO.findAll();

		model.addAttribute("prod", prod);

		return "layout/base";
	}
}
