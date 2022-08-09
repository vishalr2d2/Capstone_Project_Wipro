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
@RequestMapping(path = "/purchase")
public interface PurchaseRequestRest {

	@PostMapping(path = "/purchaseRequest")
	ResponseEntity<?> purchaseRequest(@RequestBody Map<String, String> requestMap);

	@GetMapping(path = "/approvePurchaseRequest/{sellRequestId}/{bidderId}")
	ResponseEntity<?> approvePurchaseRequest(@PathVariable String sellRequestId, @PathVariable String bidderId);

	@GetMapping(path = "/getSellRequestByIdAndStatus/{id}/{status}")
	ResponseEntity<?> getSellRequestByIdAndStatus(@PathVariable String id, @PathVariable String status);

	@GetMapping(path = "/getMaxBid/{sellRequestId}")
	ResponseEntity<?> getMaxBid(@PathVariable String sellRequestId);

	@GetMapping(path = "/getAllPurchaseRequestByRequestId/{sellRequestId}")
	ResponseEntity<?> getAllPurchaseRequestByRequestId(@PathVariable String sellRequestId);

}
