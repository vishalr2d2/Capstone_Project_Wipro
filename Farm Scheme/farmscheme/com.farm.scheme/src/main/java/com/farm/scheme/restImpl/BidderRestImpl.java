package com.farm.scheme.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.farm.scheme.rest.BidderRest;
import com.farm.scheme.service.BidderService;

@RestController
public class BidderRestImpl implements BidderRest {

	@Autowired
	BidderService bidderService;

	@Override
	public ResponseEntity<?> register(Map<String, String> requestMap) {
		try {
			return bidderService.register(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getAllBidder() {
		try {
			return bidderService.getAllBidder();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
