package com.farm.scheme.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.farm.scheme.rest.SellRequestRest;
import com.farm.scheme.service.SellRequestService;

@RestController
public class SellRequestRestImpl implements SellRequestRest {

	@Autowired
	SellRequestService sellRequestService;

	@Override
	public ResponseEntity<?> sellRequest(Map<String, String> requestMap) {
		try {
			return sellRequestService.sellRequest(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getSellRequestByFarmer(String farmerId) {
		try {
			return sellRequestService.getSellRequestByFarmer(farmerId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getAllSellRequest() {
		try {
			return sellRequestService.getAllSellRequest();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getSoldCropDetails(String farmerId) {
		try {
			return sellRequestService.getSoldCropDetails(farmerId);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
