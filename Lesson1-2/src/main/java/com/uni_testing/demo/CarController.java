package com.uni_testing.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// url: http://localhost:8080/cars
@RestController
@RequestMapping("/cars")
public class CarController {
	List<Car> carList = new ArrayList<>();

	/*@RequestMapping
	public List<Car> getAllCars() {
		return carList;
	}*/

	/*@GetMapping
	public List<Car> getAllCars() {
		return carList;
	}*/

	@GetMapping
	public Object getAllCars() {
		return (carList.isEmpty()) ? "NoCarFound" : carList;
	}


	@PostMapping
	public ResponseEntity<Object> createCar(@RequestBody Car car) {
		car.setId(System.currentTimeMillis());
		carList.add(car);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	 // Postman (GET) -> URL: http://localhost:8080/cars/{id}, {id}: a létrehozott autó egyedi azonosítója (ID)
	 @GetMapping("/{id}")
	 public ResponseEntity<?> getCarById(@PathVariable("id") Long id) {
		 // Hagyományos módszer
		 /*Car foundCar = null;
		 for (Car car : carList) {
			 if (car.getId().equals(id)) {
				 foundCar = car;
				 break;
			 }
		 }
 
		 if (foundCar != null) {
			 return ResponseEntity.ok(foundCar);
		 } else {
			 return ResponseEntity.notFound().build();
		 }*/
 
		 // Modern változat
		 Optional<Car> optionalCar = carList.stream().filter(car -> car.getId().equals(id)).findFirst();
		 //return optionalCar.map(body -> ResponseEntity.ok(body)).orElse(ResponseEntity.notFound().build());
		 return optionalCar.<ResponseEntity<?>>map(ResponseEntity::ok)
				 .orElseGet(() -> new ResponseEntity<>("Nem található ID: " + id + " autó.", HttpStatus.NOT_FOUND));
	 }
	  @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") Long id) {
        Optional<Car> optionalCar = carList.stream().filter(car -> car.getId().equals(id)).findFirst();

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            carList.remove(car);
            return ResponseEntity.noContent().build();
        } else {
            //return ResponseEntity.notFound().build(); // deleteCar metódus: ResponseEntity<Void> típusú
            return new ResponseEntity<>("Nem található ID: " + id + " autó.", HttpStatus.NOT_FOUND); // deleteCar metódus: ResponseEntity<?> típusú
        }
    }
}
