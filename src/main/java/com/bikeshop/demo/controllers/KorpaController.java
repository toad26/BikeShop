package com.bikeshop.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.ArrayUtils;

import com.bikeshop.demo.entities.Bicikl;
import com.bikeshop.demo.service.BicikleService;
import com.bikeshop.demo.utils.BikeUtils;

@Controller
public class KorpaController {

	@Autowired
	private HttpSession session;

	@Autowired
	private BicikleService bicikleService;

	@GetMapping("/korpa")
	public String korpa(Model model) {

		String proizvodi = (String) session.getAttribute("proizvodi");
		List<Bicikl> bicikle = new ArrayList<>();

		if (!BikeUtils.isEmpty(proizvodi)) {
			String p[] = proizvodi.split(",");

			for (String pr : p) {
				if (!BikeUtils.isEmpty(pr)) {
					Bicikl bic = bicikleService.readById(Integer.parseInt(pr));

					bicikle.add(bic);
				}
			}
		}

		model.addAttribute("ids", proizvodi);
		model.addAttribute("proizvodi", bicikle);

		return "korpa/korpa";
	}

	@PostMapping("/dodajKorpu")
	public String dodaj(HttpServletRequest request, Model model) {
		String proizvodi = "";

		if (session.getAttribute("proizvodi") != null) {
			proizvodi = (String) session.getAttribute("proizvodi");

			proizvodi += "," + request.getParameter("bid");
		} else {
			proizvodi += request.getParameter("bid");
		}

		System.out.println(proizvodi);
		session.setAttribute("proizvodi", proizvodi);

		return "redirect:" + request.getParameter("path");
	}

	@PostMapping("/ukloniKorpu")
	public String ukloni(HttpServletRequest request, Model model) {
		String proizvodi[];
		String pro = "";

		List<Bicikl> l = new ArrayList<>();

		if (session.getAttribute("proizvodi") != null) {
			proizvodi = ((String) session.getAttribute("proizvodi")).split(",");

			for (String p : proizvodi) {
				if (!BikeUtils.isEmpty(p)) {
					Bicikl bic = bicikleService.readById(Integer.parseInt(p));

					if (!p.contains(request.getParameter("bid"))) {
						l.add(bic);
					}
				}
			}

			for (Bicikl ls : l) {
				pro += "," + ls.getBiciklId();
			}

		}

		System.out.println(pro);
		session.setAttribute("proizvodi", pro);

		return "redirect:/korpa";
	}
}
