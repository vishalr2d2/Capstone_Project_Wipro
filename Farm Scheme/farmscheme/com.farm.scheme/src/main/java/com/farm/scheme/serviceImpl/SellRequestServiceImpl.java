package com.farm.scheme.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.scheme.FarmSchemeUtils;
import com.farm.scheme.POJO.SellRequest;
import com.farm.scheme.constant.FarmSchemeConstants;
import com.farm.scheme.dao.SellRequestDao;
import com.farm.scheme.service.SellRequestService;

@Service
public class SellRequestServiceImpl implements SellRequestService {

	@Autowired
	SellRequestDao sellRequestDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public ResponseEntity<?> sellRequest(Map<String, String> requestMap) {
		try {
			if (validateRequestMap(requestMap)) {
				SellRequest sellRequest = getSellRequestFromMap(requestMap);
				sellRequestDao.insert(sellRequest);
				return new ResponseEntity<>("{\"message\":\"Sell Request Added.\"}", HttpStatus.OK);
			} else
				return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateRequestMap(Map<String, String> requestMap) {
		return requestMap.containsKey(FarmSchemeConstants.FARMER_ID)
				&& requestMap.containsKey(FarmSchemeConstants.CROP_TYPE)
				&& requestMap.containsKey(FarmSchemeConstants.CROP_NAME)
				&& requestMap.containsKey(FarmSchemeConstants.FERTILIZER_TYPE)
				&& requestMap.containsKey(FarmSchemeConstants.QUANTITY)
				&& requestMap.containsKey(FarmSchemeConstants.SOIL_PH_CERTIFICATE);
	}

	private SellRequest getSellRequestFromMap(Map<String, String> requestMap) {
		SellRequest sellRequest = new SellRequest();
		sellRequest.setId(FarmSchemeUtils.getId(FarmSchemeConstants.SELL_REQUEST_PREFIX));
		sellRequest.setFarmerId(requestMap.get(FarmSchemeConstants.FARMER_ID));
		sellRequest.setCropType(requestMap.get(FarmSchemeConstants.CROP_TYPE));
		sellRequest.setCropName(requestMap.get(FarmSchemeConstants.CROP_NAME));
		sellRequest.setFertilizerType(requestMap.get(FarmSchemeConstants.FERTILIZER_TYPE));
		sellRequest.setQuantity(Integer.parseInt(requestMap.get(FarmSchemeConstants.QUANTITY)));
		sellRequest.setSoilPhCertificate(requestMap.get(FarmSchemeConstants.SOIL_PH_CERTIFICATE));
		sellRequest.setStauts("Open for Bidding");
		return sellRequest;
	}

	@Override
	public ResponseEntity<?> getSellRequestByFarmer(String farmerId) {
		try {
			return new ResponseEntity<>(sellRequestDao.getSellRequestByFarmer(farmerId), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getAllSellRequest() {
		try {
			Query query = new Query();
			query.addCriteria(Criteria.where(FarmSchemeConstants.STATUS).in("Open for Bidding"));
//			return mongoTemplate.find(query, SellRequest.class);
			return new ResponseEntity<>(mongoTemplate.find(query, SellRequest.class), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getSoldCropDetails(String farmerId) {
		try {
//			return new ResponseEntity<>(getAllSellRequestByStatus("Open for Bidding"), HttpStatus.OK);
			return new ResponseEntity<>(getCropDetails(farmerId, "Sold"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private List<SellRequest> getCropDetails(String farmerId, String status) {
		Query query = new Query();
		query.addCriteria(Criteria.where(FarmSchemeConstants.FARMER_ID).in(farmerId));
		query.addCriteria(Criteria.where(FarmSchemeConstants.STATUS).in(status));
		return mongoTemplate.find(query, SellRequest.class);
	}

	public void test() {
		List<SellRequest> list = sellRequestDao.findByStatus("Sold");
		List<String> cropNameList = list.stream().map(SellRequest::getCropName).collect(Collectors.toList());
		System.out.println(cropNameList);
	}

}
