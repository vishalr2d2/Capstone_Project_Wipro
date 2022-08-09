package com.farm.scheme.rest;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "*")
@RequestMapping(path = "/bidder")
public interface BidderRest {

	@PostMapping(path = "/register")
	ResponseEntity<?> register(@RequestBody Map<String, String> requestMap);

	@GetMapping(path = "/getAllBidder")
	ResponseEntity<?> getAllBidder();

}
