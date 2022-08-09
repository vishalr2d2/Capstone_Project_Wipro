package com.farm.scheme.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.farm.scheme.rest.PurchaseRequestRest;
import com.farm.scheme.service.PurchaseRequestService;

@RestController
public class PurchaseRequestRestImpl implements PurchaseRequestRest {

	@Autowired
	PurchaseRequestService purchaseRequestService;

	@Override
	public ResponseEntity<?> purchaseRequest(Map<String, String> requestMap) {
		try {
			return purchaseRequestService.purchaseRequest(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> approvePurchaseRequest(String sellRequestId, String bidderId) {
		try {
			return purchaseRequestService.approvePurchaseRequest(sellRequestId, bidderId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getSellRequestByIdAndStatus(String id, String status) {
		try {
			return purchaseRequestService.getSellRequestByIdAndStatus(id, status);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getMaxBid(String sellRequestId) {
		try {
			return purchaseRequestService.getMaxBid(sellRequestId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getAllPurchaseRequestByRequestId(String sellRequestId) {
		try {
			return purchaseRequestService.getAllPurchaseRequestByRequestId(sellRequestId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
