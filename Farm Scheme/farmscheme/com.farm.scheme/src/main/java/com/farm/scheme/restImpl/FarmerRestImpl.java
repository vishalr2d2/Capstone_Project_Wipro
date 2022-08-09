package com.farm.scheme.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.farm.scheme.rest.FarmerRest;
import com.farm.scheme.service.FarmerService;

@RestController
public class FarmerRestImpl implements FarmerRest {

	@Autowired
	FarmerService farmerService;

	@Override
	public ResponseEntity<?> register(Map<String, String> requestMap) {
		try {
			return farmerService.register(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getAllFarmer() {
		try {
			return farmerService.getAllFarmer();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> deleteFarmer(String id) {
		try {
			return farmerService.deleteFarmer(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> login(Map<String, String> requestMap) {
		try {
			return farmerService.login(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
