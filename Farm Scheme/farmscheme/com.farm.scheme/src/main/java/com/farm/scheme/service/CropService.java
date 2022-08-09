package com.farm.scheme.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface CropService {

	ResponseEntity<?> addCrop(Map<String, String> requestMap);

	ResponseEntity<?> getAllCrop();

}
