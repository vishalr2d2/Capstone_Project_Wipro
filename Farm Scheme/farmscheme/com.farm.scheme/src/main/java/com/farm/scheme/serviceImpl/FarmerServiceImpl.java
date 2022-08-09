package com.farm.scheme.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.scheme.FarmSchemeUtils;
import com.farm.scheme.POJO.Bidder;
import com.farm.scheme.POJO.Farmer;
import com.farm.scheme.constant.FarmSchemeConstants;
import com.farm.scheme.dao.BidderDao;
import com.farm.scheme.dao.FarmerDao;
import com.farm.scheme.service.FarmerService;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	FarmerDao farmerDao;

	@Autowired
	BidderDao bidderDao;

	@Override
	public ResponseEntity<?> register(Map<String, String> requestMap) {
		try {
			if (validateRequestMap(requestMap)) {
//			if (requestMap.get(FarmSchemeConstants.PASSWORD)
//					.equals(requestMap.get(FarmSchemeConstants.CONFIRM_PASSWORD))) {
				Farmer farmer = getFarmerFromMap(requestMap);
				farmerDao.insert(farmer);
				return new ResponseEntity<>("{\"message\":\"Farmer Registerd Successfully.\"}", HttpStatus.OK);
//			} else
//				return new ResponseEntity<>("{\"message\":\"Password doesnt match.\"}", HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateRequestMap(Map<String, String> requestMap) {
		return requestMap.containsKey(FarmSchemeConstants.FULL_NAME)
				&& requestMap.containsKey(FarmSchemeConstants.CONTACT_NO)
				&& requestMap.containsKey(FarmSchemeConstants.EMAIL_ID)
				&& (requestMap.containsKey(FarmSchemeConstants.ADDRESS_ONE)
						|| requestMap.containsKey(FarmSchemeConstants.ADDRESS_TWO))
				&& requestMap.containsKey(FarmSchemeConstants.CITY) && requestMap.containsKey(FarmSchemeConstants.STATE)
				&& requestMap.containsKey(FarmSchemeConstants.PINCODE)
				&& requestMap.containsKey(FarmSchemeConstants.LAND_AREA)
				&& requestMap.containsKey(FarmSchemeConstants.LAND_ADDRESS)
				&& requestMap.containsKey(FarmSchemeConstants.LAND_PIN_CODE)
				&& requestMap.containsKey(FarmSchemeConstants.ACCOUNT_NO)
				&& requestMap.containsKey(FarmSchemeConstants.IFSC_CODE)
				&& requestMap.containsKey(FarmSchemeConstants.AADHAAR)
				&& requestMap.containsKey(FarmSchemeConstants.PAN)
				&& requestMap.containsKey(FarmSchemeConstants.CERTIFICATE)
				&& requestMap.containsKey(FarmSchemeConstants.PASSWORD);
//				&& requestMap.containsKey(FarmSchemeConstants.CONFIRM_PASSWORD);
	}

	private Farmer getFarmerFromMap(Map<String, String> requestMap) {
		Farmer farmer = new Farmer();
		farmer.setId(FarmSchemeUtils.getId(FarmSchemeConstants.FARMER_ID_PREFIX));
		farmer.setFullName(requestMap.get(FarmSchemeConstants.FULL_NAME));
		farmer.setContactNo(requestMap.get(FarmSchemeConstants.CONTACT_NO));
		farmer.setEmailId(requestMap.get(FarmSchemeConstants.EMAIL_ID));
		farmer.setAddressOne(requestMap.get(FarmSchemeConstants.ADDRESS_ONE));
		farmer.setAddressTwo(requestMap.get(FarmSchemeConstants.ADDRESS_TWO));
		farmer.setCity(requestMap.get(FarmSchemeConstants.CITY));
		farmer.setState(requestMap.get(FarmSchemeConstants.STATE));
		farmer.setPinCode(requestMap.get(FarmSchemeConstants.PINCODE));
		farmer.setLandArea(requestMap.get(FarmSchemeConstants.LAND_AREA));
		farmer.setLandAddress(requestMap.get(FarmSchemeConstants.LAND_ADDRESS));
		farmer.setLandPinCode(requestMap.get(FarmSchemeConstants.LAND_PIN_CODE));
		farmer.setAccountNo(requestMap.get(FarmSchemeConstants.ACCOUNT_NO));
		farmer.setIfscCode(requestMap.get(FarmSchemeConstants.IFSC_CODE));
		farmer.setAadhaar(requestMap.get(FarmSchemeConstants.AADHAAR));
		farmer.setPan(requestMap.get(FarmSchemeConstants.PAN));
		farmer.setCertificate(requestMap.get(FarmSchemeConstants.CERTIFICATE));
		farmer.setPassword(requestMap.get(FarmSchemeConstants.PASSWORD));
		return farmer;
	}

	@Override
	public ResponseEntity<?> getAllFarmer() {
		return new ResponseEntity<>(farmerDao.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteFarmer(String id) {
		try {
			farmerDao.deleteById(id);
			return new ResponseEntity<>("Farmer Deleted Successfully.", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> login(Map<String, String> requestMap) {
		try {
			Map<String, String> map = new HashMap<>();
			if (requestMap.containsKey(FarmSchemeConstants.EMAIL_ID)
					&& requestMap.containsKey(FarmSchemeConstants.PASSWORD)
					&& requestMap.containsKey(FarmSchemeConstants.ROLE)) {
				if (FarmSchemeConstants.FARMER.equalsIgnoreCase(requestMap.get(FarmSchemeConstants.ROLE))) {
					Farmer farmer = farmerDao.login(requestMap.get(FarmSchemeConstants.EMAIL_ID),
							requestMap.get(FarmSchemeConstants.PASSWORD));
					if (!java.util.Objects.isNull(farmer)) {
						return new ResponseEntity<>(getReturnMap(farmer.getId(),
								requestMap.get(FarmSchemeConstants.ROLE), farmer.getEmailId(), farmer.getFullName()),
								HttpStatus.OK);
					} else
						return new ResponseEntity<>("{\"message\":\"Bad Credentials.\"}", HttpStatus.BAD_REQUEST);

				} else if (FarmSchemeConstants.BIDDER.equalsIgnoreCase(requestMap.get(FarmSchemeConstants.ROLE))) {
					Bidder bidder = bidderDao.login(requestMap.get(FarmSchemeConstants.EMAIL_ID),
							requestMap.get(FarmSchemeConstants.PASSWORD));
					if (!java.util.Objects.isNull(bidder)) {
						return new ResponseEntity<>(getReturnMap(bidder.getId(),
								requestMap.get(FarmSchemeConstants.ROLE), bidder.getEmailId(), bidder.getFullName()),
								HttpStatus.OK);
					} else
						return new ResponseEntity<>("{\"message\":\"Bad Credentials.\"}", HttpStatus.BAD_REQUEST);
				} else
					return new ResponseEntity<>("{\"message\":\"Invalid Role.\"}", HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Map<String, String> getReturnMap(String id, String role, String emailId, String fullName) {
		Map<String, String> map = new HashMap<>();
		map.put(FarmSchemeConstants.ID, id);
		map.put(FarmSchemeConstants.ROLE, role);
		map.put(FarmSchemeConstants.EMAIL_ID, emailId);
		map.put(FarmSchemeConstants.FULL_NAME, fullName);
		return map;
	}

}
