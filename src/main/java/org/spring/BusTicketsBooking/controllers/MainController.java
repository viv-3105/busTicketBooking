package org.spring.BusTicketsBooking.controllers;

import org.spring.BusTicketsBooking.models.PassengerModel;
import org.spring.BusTicketsBooking.respositories.CityRepository;
import org.spring.BusTicketsBooking.respositories.PassengerRespository;
import org.spring.BusTicketsBooking.services.BusService;
import org.spring.BusTicketsBooking.services.CityService;
import org.spring.BusTicketsBooking.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

	final PassengerRespository passengerRespository;
	final BusService busService;
	final CityRepository cityRepository;
	final CityService cityService;
	final PassengerService passengerService;

	@Autowired
	public MainController(PassengerRespository passengerRespository, BusService busService,
			CityRepository cityRepository, CityService cityService, PassengerService passengerService) {
		this.passengerRespository = passengerRespository;
		this.busService = busService;
		this.cityRepository = cityRepository;
		this.cityService = cityService;
		this.passengerService = passengerService;
	}

	@GetMapping("/index")
	public Model indexGet(Model model) {
		cityService.setCities(cityRepository.findAll());
		model.addAttribute("cities", cityService.getCities());
		return model;
	}

	@PostMapping("/index")
	public Model indexPost(@RequestParam("fromCity") String fromCity, @RequestParam("toCity") String toCity,
			Model model) {
		if (!cityService.isUserChoseCorrectly(fromCity, toCity)) {
			model.addAttribute("warning", "No more passenger");
			model.addAttribute("cities", cityService.getCities());
			return model;
		}
		model.addAttribute("cities", cityService.getCities());
		model.addAttribute("freeSeats",
				busService.numberOfFreePlaces(fromCity, toCity, cityService.getGrantedNumberForTheCity()));

		return model;
	}

	@GetMapping("/addPassenger")
	public Model addUserGet(Model model) {
		model.addAttribute("passengerModel", new PassengerModel());
		model.addAttribute("cities", cityService.getCities());
		return model;
	}

	@PostMapping("/addPassenger")
	public Model addUserPost(@ModelAttribute("passengerModel") PassengerModel passengerModel, Model model) {
		if (!cityService.isUserChoseCorrectly(passengerModel.getFromCity(), passengerModel.getToCity())) {
			model.addAttribute("warning", "No more passenger");
			model.addAttribute("cities", cityService.getCities());
			return model;
		}

		if (busService.numberOfFreePlaces(passengerModel.getFromCity(), passengerModel.getToCity(),
				cityService.getGrantedNumberForTheCity()) < 1) {
			model.addAttribute("info", "false");
			model.addAttribute("cities", cityService.getCities());
			return model;
		}
		model.addAttribute("info", "true");
		busService.awardingPlace(passengerModel, cityService.getGrantedNumberForTheCity());
		passengerService.giveNumberOfPassenger(passengerModel, passengerRespository.findAll());

		passengerRespository.save(passengerModel);
		busService.getListOfPassengers().add(passengerModel);

		model.addAttribute("cities", cityService.getCities());
		return model;
	}

	@GetMapping("/listOfPassengers")
	public String listOfPassengersGet(Model model) {
		return passengerRespository.findAll().toString();
	}

	@GetMapping("/delete/{number}")
	public String deleteGet(@PathVariable("number") int numberPassenger) {
		passengerRespository.delete(numberPassenger);
		return passengerRespository.findAll().toString();
	}

	@GetMapping("/status")
	public Model statusGet(Model model) {
		busService.clearSeats();
		passengerService.addSomeUser();

		model.addAttribute("seats", busService.getSeats());
		return model;
	}

}
