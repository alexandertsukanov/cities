package com.example.cityapp.controller;

import com.example.cityapp.entity.City;
import com.example.cityapp.repository.CityRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CityController {

	private final CityRepository cityRepository;

	public CityController(CityRepository repository) {
		this.cityRepository = repository;
	}

	@GetMapping(path = "/cities")
	public Flux<City> all() {
		return this.cityRepository.findAll();
	}

	@GetMapping(path = "/cities/ua")
	public Flux<City> allUa() {
		return this.cityRepository.findAll().filter(this::isInUkraine);
	}

	@GetMapping(path = "/cities/usa")
	public Flux<City> allUsa() {
		return this.cityRepository.findAll().filter(this::isInUsa);
	}

	@GetMapping(path = "/city/{name}")
	public Mono<City> byName(@PathVariable String name) {
		return this.cityRepository.findByNameIgnoringCase(name);
	}

	private boolean isInUkraine(City city) {
		return city.getCountry().equals("Ukraine");
	}

	private boolean isInUsa(City city) {
		return city.getCountry().equals("USA");
	}
}
