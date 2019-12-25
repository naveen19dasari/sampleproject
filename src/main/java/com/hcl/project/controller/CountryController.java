package com.hcl.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.project.model.Country;
import com.hcl.project.service.CountryService;

/**
 * This CountryController is having list CRUD operation implementations
 * 
 * @author tamburia
 *
 */
@Controller
public class CountryController {

	@Autowired
	CountryService countryService;

	/**
	 * This method retrieve the all countries which is available in DB
	 * 
	 * @param model
	 * @return countryDetails
	 */
	@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, headers = "Accept=application/json")
	public String getCountries(Model model) {

		List<Country> listOfCountries = countryService.getAllCountries();
		
		model.addAttribute("country", new Country());
		
		model.addAttribute("listOfCountries", listOfCountries);
		
		return "countryDetails";
	}

	/**
	 * This method retrieve the country data depends on ID
	 * 
	 * @param id
	 * @return id
	 */
	@RequestMapping(value = "/getCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	
	public Country getCountryById(@PathVariable int id) {
		
		return countryService.getCountry(id);
	}

	/**
	 * this method to insert or add the records in country table If ID not
	 * existing in country table we will insert the record into DB Otherwise we
	 * will update the table
	 * 
	 * @param country
	 * @return getAllCountries
	 */
	@RequestMapping(value = "/addCountry", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addCountry(@ModelAttribute("country") Country country) {
		
		if (country.getId() == 0) {
			
			countryService.addCountry(country);
			
		} 
		else {
			
			countryService.updateCountry(country);
		}

		return "redirect:/getAllCountries";
	}

	/**
	 * This method is used to update the records in Country table
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public String updateCountry(@PathVariable("id") int id, Model model) {
		
		model.addAttribute("country", this.countryService.getCountry(id));
		
		model.addAttribute("listOfCountries", this.countryService.getAllCountries());
		
		return "countryDetails";
	}

	/**
	 * This method is used to delete the records from country table
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/deleteCountry/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	
	public String deleteCountry(@PathVariable("id") int id) {
		
		countryService.deleteCountry(id);
		
		return "redirect:/getAllCountries";

	}
}
