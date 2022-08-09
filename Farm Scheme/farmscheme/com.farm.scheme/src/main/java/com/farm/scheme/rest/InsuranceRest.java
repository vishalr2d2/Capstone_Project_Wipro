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
@RequestMapping(path = "/insurance")
public interface InsuranceRest {

	@PostMapping(path = "/calculate")
	ResponseEntity<?> calculate(@RequestBody Map<String, String> requestMap);

	@PostMapping(path = "/applyForInsurance")
	ResponseEntity<?> applyForInsurance(@RequestBody Map<String, String> requestMap);

	@GetMapping(path = "/getInsuranceById/{id}")
	ResponseEntity<?> getInsuranceById(@PathVariable String id);

	@PostMapping(path = "/claimInsurance")
	ResponseEntity<?> claimInsurance(@RequestBody Map<String, String> requestMap);

}
