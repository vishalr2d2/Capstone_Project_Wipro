package com.farm.scheme.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface InsuranceService {

	ResponseEntity<?> calculate(Map<String, String> requestMap);

	ResponseEntity<?> applyForInsurance(Map<String, String> requestMap);

	ResponseEntity<?> getInsuranceById(String id);
	
	ResponseEntity<?> claimInsurance(Map<String, String> requestMap);

}
