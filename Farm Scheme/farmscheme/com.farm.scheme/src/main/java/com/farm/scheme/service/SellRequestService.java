package com.farm.scheme.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface SellRequestService {

	ResponseEntity<?> sellRequest(Map<String, String> requestMap);

	ResponseEntity<?> getSellRequestByFarmer(String farmerId);

	ResponseEntity<?> getAllSellRequest();

	ResponseEntity<?> getSoldCropDetails(String farmerId);
}
