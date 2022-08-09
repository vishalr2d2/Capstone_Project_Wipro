package com.farm.scheme.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface PurchaseRequestService {

	ResponseEntity<?> purchaseRequest(Map<String, String> requestMap);

	ResponseEntity<?> approvePurchaseRequest(String sellRequestId, String bidderId);

	ResponseEntity<?> getSellRequestByIdAndStatus(String id, String status);

	ResponseEntity<?> getMaxBid(String sellRequestId);

	ResponseEntity<?> getAllPurchaseRequestByRequestId(String sellRequestId);

}
