package com.accenture.cvt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.accenture.cvt.modules.LocationStats;
import com.accenture.cvt.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;
	@GetMapping("/")
	public String Home(Model model) {
		List<LocationStats> allStats=coronaVirusDataService.getAllStats();
		int totalReportedCases=allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases=allStats.stream().mapToInt(stat -> stat.getDiffFromPreviousDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases",totalNewCases);
		return "home";
	}
}
