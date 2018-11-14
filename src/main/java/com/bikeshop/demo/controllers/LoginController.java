package com.bikeshop.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bikeshop.demo.entities.Korisnik;
import com.bikeshop.demo.entities.Permisije;
import com.bikeshop.demo.service.KorisnikService;
import com.bikeshop.demo.service.PermisijeService;
import com.bikeshop.demo.utils.BikeUtils;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private PermisijeService permisijeService;

	@Autowired
	private HttpSession session;

	@GetMapping("login")
	public String getLogin() {
		return "login/login";
	}

	@PostMapping("login")
	public String processLogin(HttpServletRequest request, Model model, Korisnik korisnik) {
		korisnik = korisnikService.findByName(request.getParameter("username"), request.getParameter("password"));

		if (!BikeUtils.isEmpty(korisnik)) {
			session.setAttribute("user", korisnik);
			session.setAttribute("role", korisnik.getPermisije().getNaziv());
			return "redirect:/";
		}

		model.addAttribute("error", "Wrong Credentials");
		return "login/login";
	}

	@GetMapping("logout")
	public String logout(Model model) {
		session.invalidate();
		model.addAttribute("logout", "Logut");
		return "login/login";
	}

	@GetMapping("registracija")
	public String registracija(Model model) {

		Korisnik k = new Korisnik();
		List<Permisije> roles = new ArrayList<>();
		roles.add(permisijeService.readById(2));
		model.addAttribute("roles", roles);
		model.addAttribute("korisnik", k);
		model.addAttribute("path", "/korisnik/create");

		return "login/register";

	}

	@PostMapping("registracija")
	public String registracijaPost(@Valid @ModelAttribute Korisnik kor, BindingResult bd, Model model) {

		kor.setSifra(bCryptPasswordEncoder.encode(kor.getSifra()));
		List<Permisije> roles = permisijeService.findAll();
		model.addAttribute("roles", roles);

		if (bd.hasErrors()) {
			model.addAttribute("korisnik", kor);
			model.addAttribute("path", "/korisnik/create");
			return "/login/register";
		}
		korisnikService.save(kor);
		model.addAttribute("korisnik", kor);

		return "redirect:/korisnici";
	}

}
