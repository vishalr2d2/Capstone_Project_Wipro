package com.farm.scheme.serviceImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.scheme.FarmSchemeUtils;
import com.farm.scheme.POJO.Bidder;
import com.farm.scheme.constant.FarmSchemeConstants;
import com.farm.scheme.dao.BidderDao;
import com.farm.scheme.service.BidderService;

@Service
public class BidderServiceImpl implements BidderService {

	@Autowired
	BidderDao bidderDao;

	@Override
	public ResponseEntity<?> register(Map<String, String> requestMap) {
		try {
			if (validateRequestMap(requestMap)) {
				Bidder bidder = getBidderFromMap(requestMap);
				bidderDao.insert(bidder);
				return new ResponseEntity<>("{\"message\":\"Bidder Registerd Successfully.\"}", HttpStatus.OK);
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
				&& requestMap.containsKey(FarmSchemeConstants.ACCOUNT_NO)
				&& requestMap.containsKey(FarmSchemeConstants.IFSC_CODE)
				&& requestMap.containsKey(FarmSchemeConstants.AADHAAR)
				&& requestMap.containsKey(FarmSchemeConstants.PAN)
				&& requestMap.containsKey(FarmSchemeConstants.TRADER_LICENSE)
				&& requestMap.containsKey(FarmSchemeConstants.PASSWORD);
	}

	private Bidder getBidderFromMap(Map<String, String> requestMap) {
		Bidder bidder = new Bidder();
		bidder.setId(FarmSchemeUtils.getId(FarmSchemeConstants.BIDDER_ID_PREFIX));
		bidder.setFullName(requestMap.get(FarmSchemeConstants.FULL_NAME));
		bidder.setContactNo(requestMap.get(FarmSchemeConstants.CONTACT_NO));
		bidder.setEmailId(requestMap.get(FarmSchemeConstants.EMAIL_ID));
		bidder.setAddressOne(requestMap.get(FarmSchemeConstants.ADDRESS_ONE));
		bidder.setAddressTwo(requestMap.get(FarmSchemeConstants.ADDRESS_TWO));
		bidder.setCity(requestMap.get(FarmSchemeConstants.CITY));
		bidder.setState(requestMap.get(FarmSchemeConstants.STATE));
		bidder.setPinCode(requestMap.get(FarmSchemeConstants.PINCODE));
		bidder.setAccountNo(requestMap.get(FarmSchemeConstants.ACCOUNT_NO));
		bidder.setIfscCode(requestMap.get(FarmSchemeConstants.IFSC_CODE));
		bidder.setAadhaar(requestMap.get(FarmSchemeConstants.AADHAAR));
		bidder.setPan(requestMap.get(FarmSchemeConstants.PAN));
		bidder.setTraderLicense(requestMap.get(FarmSchemeConstants.TRADER_LICENSE));
		bidder.setPassword(requestMap.get(FarmSchemeConstants.PASSWORD));
		return bidder;
	}

//	public List<Bidder> allTheSourcers() {
//		Query query = new Query().addCriteria(Criteria.where("type").is("sourcer"))
//				.with(Sort.by(Sort.Order.desc("engines"))).limit(4);
//		return mongoTemplate.find(query, Bidder.class);
//	}

	@Override
	public ResponseEntity<?> getAllBidder() {
		return new ResponseEntity<>(bidderDao.findAll(), HttpStatus.OK);
	}

}
