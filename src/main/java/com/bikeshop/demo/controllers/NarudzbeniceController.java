package com.bikeshop.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bikeshop.demo.entities.Bicikl;
import com.bikeshop.demo.entities.Korisnik;
import com.bikeshop.demo.entities.Narudzbenica;
import com.bikeshop.demo.service.BicikleService;
import com.bikeshop.demo.service.KorisnikService;
import com.bikeshop.demo.service.NarudzbenicaService;
import com.bikeshop.demo.utils.BikeUtils;

@Controller
public class NarudzbeniceController {

	@Autowired
	private NarudzbenicaService narudzbeniceService;

	@Autowired
	private KorisnikService korisnikService;

	@Autowired
	private BicikleService bicikleService;

	@Autowired
	private HttpSession session;

	@GetMapping("/narudzbenice")
	public String narudzbenice(Model model) {

		List<Narudzbenica> narudzbenice = narudzbeniceService.findAll();

		model.addAttribute("narudzbenice", narudzbenice);
		return "narudzbenice/narudzbenice";
	}

	@RequestMapping("narudzbenica/{id}")
	public String narudzbenica(@PathVariable("id") int id, Model model) {

		Narudzbenica k = narudzbeniceService.readById(id);

		String ids[] = k.getBicikle().split(",");
		List<Bicikl> bicikle = new ArrayList<>();

		Double cena = 0.0;

		if (ids.length > 0) {
			for (String i : ids) {
				Bicikl bic = bicikleService.readById(Integer.parseInt(i));
				bicikle.add(bic);
				cena += bic.getCena();
			}
		}

		model.addAttribute("cena", cena);
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("narudzbenica", k);

		return "narudzbenice/narudzbenica";
	}

	@GetMapping("narudzbenice/update/{id}")
	public String narudzbenicaUpdate(@PathVariable("id") int id, Model model) {

		Narudzbenica k = narudzbeniceService.readById(id);
		List<Korisnik> korisnici = korisnikService.findAll();

		String ids[] = k.getBicikle().split(",");
		List<Bicikl> bicikle = new ArrayList<>();

		Double cena = 0.0;

		if (ids.length > 0) {
			for (String i : ids) {
				Bicikl bic = bicikleService.readById(Integer.parseInt(i));
				bicikle.add(bic);
				cena += bic.getCena();
			}
		}

		model.addAttribute("korisnici", korisnici);
		model.addAttribute("narudzbenica", k);
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("path", "/narudzbenice/update/" + id);

		return "narudzbenice/narudzbenica-form";
	}

	@PostMapping("narudzbenice/update/{id}")
	public String update(@Valid @ModelAttribute Narudzbenica kor, BindingResult bd, @PathVariable("id") int id,
			Model model) {

		List<Korisnik> korisnici = korisnikService.findAll();

		model.addAttribute("korisnici", korisnici);

		if (bd.hasErrors()) {
			Narudzbenica k = narudzbeniceService.readById(id);
			kor.setDatum(k.getDatum());
			model.addAttribute("narudzbenica", kor);
			model.addAttribute("path", "/narudzbenice/update/" + id);
			return "/narudzbenice/narudzbenica-form";
		}

		narudzbeniceService.update(kor);
		model.addAttribute("narudzbenica", kor);

		return "redirect:/narudzbenica/" + id;
	}

	@PostMapping("narudzbenice/create")
	public String create(@Valid @ModelAttribute Narudzbenica kor, BindingResult bd, Model model) {
		List<Korisnik> korisnici = korisnikService.findAll();
		model.addAttribute("korisnici", korisnici);

		if (bd.hasErrors()) {
			model.addAttribute("narudzbenica", kor);
			model.addAttribute("path", "/korisnik/create");
			return "/narudzbenice/narudzbenica-form";
		}

		narudzbeniceService.save(kor);
		model.addAttribute("narudzbenica", kor);

		return "redirect:/narudzbenice";
	}

	@GetMapping("narudzbenice/delete/{id}")
	public String delete(@PathVariable("id") int id, Model model) {
		Narudzbenica k = narudzbeniceService.readById(id);
		narudzbeniceService.delete(k);

		return "redirect:/narudzbenice";
	}

//	@GetMapping()
//	public String deleteBiciklIzNarudzbenice(@PathVariable("id") int id, Model model) {
//		Narudzbenica n = narudzbeniceService.readById(id);
//		
//		
//		
//		
//		return null;
//		
//	}
}
