package com.bikeshop.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bikeshop.demo.entities.Korisnik;
import com.bikeshop.demo.entities.Permisije;
import com.bikeshop.demo.service.KorisnikService;
import com.bikeshop.demo.service.PermisijeService;
import com.bikeshop.demo.utils.BikeUtils;

@Controller
public class KorisniciController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private KorisnikService korisnikService;
	
	@Autowired
    private PermisijeService permisijeService;
	
	@Autowired
    private HttpSession session;
	
	@GetMapping("korisnici")
	public String korisnici(Model model) {
		List<Korisnik> korisnici = korisnikService.findAll();
		model.addAttribute("korisnici", korisnici);
		return "korisnici/korisnici";
	}
	
	@RequestMapping("korisnik/{id}")
	public String korisnik(@PathVariable("id") int id, Model model) {
		Korisnik k = korisnikService.readById(id);
		model.addAttribute("korisnik", k);
		
		return "korisnici/korisnik";
	}
	
	@GetMapping("korisnik-form")
	public String korisnikForma() {
		return "korisnici/korisnik-form";
	}
	
	@GetMapping("korisnik/update/{id}")
	public String korisnikUpdate(@PathVariable("id") int id, Model model) {
		Korisnik k = korisnikService.readById(id);
		List<Permisije> roles = permisijeService.findAll();

		model.addAttribute("roles", roles);
		model.addAttribute("korisnik", k);
		model.addAttribute("path", "/korisnik/update/"+id);
		
		return "korisnici/korisnik-form";
	}
	
	@PostMapping("korisnik/update/{id}")
	public String update(@Valid @ModelAttribute Korisnik kor, BindingResult bd, @PathVariable("id") int id, Model model) {
		List<Permisije> roles = permisijeService.findAll();
		model.addAttribute("roles", roles);
			
		if (bd.hasErrors()) {
			Korisnik k = korisnikService.readById(id);
			kor.setDatum(k.getDatum());
			model.addAttribute("korisnik", kor);
			model.addAttribute("path", "/korisnik/update/"+id);
            return "/korisnici/korisnik-form";
        }
		korisnikService.update(kor);
		model.addAttribute("korisnik", kor);
		
		return "redirect:/korisnik/"+id;
	}
	
	@GetMapping("korisnik/create")
	public String korisnikCreate(Model model) {

		Korisnik k = new Korisnik();
		List<Permisije> roles = permisijeService.findAll();

		model.addAttribute("roles", roles);
		model.addAttribute("korisnik", k);
		model.addAttribute("path", "/korisnik/create");
		
		return "korisnici/korisnik-form";
	}
	
	@PostMapping("korisnik/create")
	public String create(@Valid @ModelAttribute Korisnik kor, BindingResult bd, Model model) {
		
		kor.setSifra(bCryptPasswordEncoder.encode(kor.getSifra()));
		
		List<Permisije> roles = permisijeService.findAll();
		model.addAttribute("roles", roles);
				
		if (bd.hasErrors()) {
			model.addAttribute("korisnik", kor);
			model.addAttribute("path", "/korisnik/create");
            return "/korisnici/korisnik-form";
        }
		korisnikService.save(kor);
		model.addAttribute("korisnik", kor);
		
		return "redirect:/korisnici";
	}
	
	@GetMapping("korisnik/delete/{id}")
	public String snimiKorisnika(@PathVariable("id") int id, Model model) {

		Korisnik k = korisnikService.readById(id);
		korisnikService.delete(k);
		return "redirect:/korisnici";
	}
}
