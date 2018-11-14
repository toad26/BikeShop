package com.bikeshop.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bikeshop.demo.entities.Bicikl;
import com.bikeshop.demo.entities.Kategorija;
import com.bikeshop.demo.service.BicikleService;
import com.bikeshop.demo.service.KategorijaService;
import com.bikeshop.demo.service.StorageService;
import com.bikeshop.demo.utils.BikeUtils;

@Controller
public class BicikleController {
	
	@Autowired
    private BicikleService bicikleService;
	
	@Autowired
    private KategorijaService kategorijeService;
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
    private HttpSession session;
	
	@GetMapping("bicikle")
	public String bicikle(Model model) {
		
		List<Bicikl> bicikle = bicikleService.findAll();
		List<Kategorija> kategorije = kategorijeService.findAll();
		
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("kategorije", kategorije);
		return "bicikle/bicikle";
	}
	
	@GetMapping("bicikle/kategorija/{id}")
	public String bicikleKategorija(@PathVariable("id") String id, Model model) {

		List<Bicikl> bicikle = bicikleService.findByFieldList("kategorija_id", id);
		
		List<Kategorija> kategorije = kategorijeService.findAll();
		
		model.addAttribute("activeId", id);
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("kategorije", kategorije);
				
		return "bicikle/bicikle";
	}
	
	@GetMapping("bicikle/sort")
	public String bicikleSort( Model model, HttpServletRequest request) {

		List<Bicikl> bicikle = new ArrayList<>();

		if (BikeUtils.isEmpty(request.getParameter("kategorija")) == false) {
			 bicikle = bicikleService.findByFieldListSort("kategorija_id", request.getParameter("kategorija"), request.getParameter("sort"), request.getParameter("order"));
		} else {
			bicikle = bicikleService.findAllSort(request.getParameter("sort"), request.getParameter("order"));
		}
		
		List<Kategorija> kategorije = kategorijeService.findAll();
		
		
		model.addAttribute("sort", request.getParameter("sort"));
		model.addAttribute("order", request.getParameter("order"));
		
		model.addAttribute("activeId", request.getParameter("kategorija"));
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("kategorije", kategorije);
		return "bicikle/bicikle";
	}
	
	@GetMapping("bicikle/search")
	public String bicikleSearch( Model model, HttpServletRequest request) {
//		if (!BikeUtils.getRole(session).equalsIgnoreCase("admin"))
//			return "login/login";
		
		List<Bicikl> bicikle = new ArrayList<>();

		if (BikeUtils.isEmpty(request.getParameter("kategorija")) == false) {
			 bicikle = bicikleService.findByFieldListSearch("kategorija_id", request.getParameter("kategorija"), request.getParameter("search"));
		} else {
			bicikle = bicikleService.findAllSearch(request.getParameter("search"));
		}
		
		List<Kategorija> kategorije = kategorijeService.findAll();
		
		model.addAttribute("activeId", request.getParameter("kategorija"));
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("kategorije", kategorije);
		return "bicikle/bicikle";
	}
	
	@RequestMapping("bicikl/{id}")
	public String bicikl(@PathVariable("id") int id, Model model) {

		Bicikl k = bicikleService.readById(id);
		String[] slike = k.getSlika().split(",");
		
		model.addAttribute("slike", slike);
		model.addAttribute("bicikl", k);
		
		return "bicikle/bicikl";
	}
	
	@GetMapping("bicikle-form")
	public String bicikleForma() {
	
		return "bicikle/bicikle-form";
	}
	
	@GetMapping("bicikle/update/{id}")
	public String bicikleUpdate(@PathVariable("id") int id, Model model) {
//		if (!BikeUtils.getRole(session).equalsIgnoreCase("admin"))
//			return "login/login";
		
		Bicikl k = bicikleService.readById(id);
		List<Kategorija> kategorije = kategorijeService.findAll();

		model.addAttribute("kategorije", kategorije);
		model.addAttribute("bicikl", k);
		model.addAttribute("path", "/bicikle/update/"+id);
		
		return "bicikle/bicikle-form";
	}
	
	@PostMapping("bicikle/update/{id}")
	public String update(@Valid @ModelAttribute Bicikl kor, BindingResult bd, @PathVariable("id") int id, @RequestParam("slika") MultipartFile[] file, Model model) {

		List<Kategorija> kategorije = kategorijeService.findAll();

		model.addAttribute("kategorije", kategorije);
			
		if (bd.hasErrors()) {
			model.addAttribute("bicikl", kor);
			model.addAttribute("path", "/bicikle/update/"+id);
            return "/bicikle/bicikle-form";
        }
		
		storageService.setLocation("/bicikle/"+id);
		
		for (MultipartFile f:file) {
			storageService.store(f);
		}

		kor.setBiciklId(id);
		bicikleService.update(kor);
		model.addAttribute("bicikl", kor);
		
		return "redirect:/bicikl/"+id;
	}
	
	@GetMapping("bicikle/create")
	public String bicikleCreate(Model model) {
//		if (!BikeUtils.getRole(session).equalsIgnoreCase("admin"))
//			return "login/login";
		
		Bicikl k = new Bicikl();
		List<Kategorija> kategorije = kategorijeService.findAll();

		model.addAttribute("kategorije", kategorije);
		model.addAttribute("bicikl", k);
		model.addAttribute("path", "/bicikle/create");
		
		return "bicikle/bicikle-form";
	}
	
	@PostMapping("bicikle/create")
	public String create(@Valid @ModelAttribute Bicikl kor, BindingResult bd, Model model) {
	
		List<Kategorija> kategorije = kategorijeService.findAll();

		model.addAttribute("kategorije", kategorije);
		
		
		if (bd.hasErrors()) {
			model.addAttribute("bicikl", kor);
			model.addAttribute("path", "/bicikle/create");
            return "/bicikle/bicikle-form";
        }
		

		
		bicikleService.save(kor);
		model.addAttribute("bicikl", kor);
		
		return "redirect:/bicikle";
	}
	
	@GetMapping("bicikle/delete/{id}")
	public String bicikleDelete(@PathVariable("id") int id, Model model) {
		
		Bicikl k = bicikleService.readById(id);
		bicikleService.delete(k);
		
		return "redirect:/bicikle";
	}
	
	
	@GetMapping("bicikle/list")	
	public String bicikleList(Model model) {

		List<Bicikl> bicikle = bicikleService.findAll();
		List<Kategorija> kategorije = kategorijeService.findAll();
		
		model.addAttribute("bicikle", bicikle);
		model.addAttribute("kategorije", kategorije);
		return "bicikle/biciklList";
	}
}
