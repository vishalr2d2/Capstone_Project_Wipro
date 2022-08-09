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
@RequestMapping(path = "/sell")
public interface SellRequestRest {

	@PostMapping(path = "/sellRequest")
	ResponseEntity<?> sellRequest(@RequestBody Map<String, String> requestMap);

	@GetMapping(path = "/getSellRequestByFarmer/{farmerId}")
	ResponseEntity<?> getSellRequestByFarmer(@PathVariable String farmerId);

	@GetMapping(path = "/getAllSellRequest")
	ResponseEntity<?> getAllSellRequest();

	@GetMapping(path = "/getSoldCropDetails/{farmerId}")
	ResponseEntity<?> getSoldCropDetails(@PathVariable String farmerId);
}
