package com.farm.scheme.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface FarmerService {

	ResponseEntity<?> register(Map<String, String> requestMap);

	ResponseEntity<?> getAllFarmer();

	ResponseEntity<?> deleteFarmer(String id);

	ResponseEntity<?> login(Map<String, String> requestMap);

}
