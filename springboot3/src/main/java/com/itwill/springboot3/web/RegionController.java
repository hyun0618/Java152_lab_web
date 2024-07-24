package com.itwill.springboot3.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itwill.springboot3.domain.Region;
import com.itwill.springboot3.service.RegionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/region")
public class RegionController {
	
	private final RegionService regSvc;
	
	@GetMapping("/list")
	public void list(Model model) {
		List<Region> list = regSvc.read();
		model.addAttribute("regions", list);
	}
	
	@GetMapping("/details/{id}")
	public String details(@PathVariable Integer id, Model model) {
		Region reg = regSvc.read(id);
		model.addAttribute("region", reg);
		return "region/details";
	}
	
}
