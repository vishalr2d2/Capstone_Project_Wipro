package com.farm.scheme.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface BidderService {

	ResponseEntity<?> register(Map<String, String> requestMap);

	ResponseEntity<?> getAllBidder();

}
