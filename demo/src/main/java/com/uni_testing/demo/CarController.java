package com.uni_testing.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
	List<Car> carsList = new ArrayList<>();

	@RequestMapping
	public List<Car> getAllCars() {
		return carsList;
	}

	@PostMapping
	public ResponseEntity<Object> createCar(@RequestBody Car car) {
		car.setId(System.currentTimeMillis());
		carsList.add(car);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
