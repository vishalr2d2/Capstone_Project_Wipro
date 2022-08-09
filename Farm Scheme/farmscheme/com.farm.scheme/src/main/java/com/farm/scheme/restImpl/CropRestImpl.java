package com.farm.scheme.restImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.farm.scheme.rest.CropRest;
import com.farm.scheme.service.CropService;

@RestController
public class CropRestImpl implements CropRest {

	@Autowired
	CropService cropService;

	@Override
	public ResponseEntity<?> addCrop(Map<String, String> requestMap) {
		try {
			return cropService.addCrop(requestMap);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getAllCrop() {
		try {
			return cropService.getAllCrop();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
