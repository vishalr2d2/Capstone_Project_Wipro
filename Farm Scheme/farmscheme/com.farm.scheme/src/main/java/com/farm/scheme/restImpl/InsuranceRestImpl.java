package com.farm.scheme.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.farm.scheme.rest.InsuranceRest;
import com.farm.scheme.service.InsuranceService;

@RestController
public class InsuranceRestImpl implements InsuranceRest {

	@Autowired
	InsuranceService insuranceService;

	@Override
	public ResponseEntity<?> calculate(Map<String, String> requestMap) {
		try {
			return insuranceService.calculate(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> applyForInsurance(Map<String, String> requestMap) {
		try {
			return insuranceService.applyForInsurance(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getInsuranceById(String id) {
		try {
			return insuranceService.getInsuranceById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> claimInsurance(Map<String, String> requestMap) {
		try {
			return insuranceService.claimInsurance(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
