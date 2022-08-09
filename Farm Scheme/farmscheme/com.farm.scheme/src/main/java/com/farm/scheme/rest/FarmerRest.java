package com.farm.scheme.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/farmer")
public interface FarmerRest {

	@PostMapping(path = "/register")
	ResponseEntity<?> register(@RequestBody Map<String, String> requestMap);

	@GetMapping(path = "/getAllFarmer")
	ResponseEntity<?> getAllFarmer();

	@GetMapping(path = "/deleteFarmer/{id}")
	ResponseEntity<?> deleteFarmer(@PathVariable String id);

	@PostMapping(path = "/login")
	ResponseEntity<?> login(@RequestBody Map<String, String> requestMap);

}
