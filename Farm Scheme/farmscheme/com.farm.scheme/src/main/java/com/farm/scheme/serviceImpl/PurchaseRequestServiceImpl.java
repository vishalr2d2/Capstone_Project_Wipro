package com.farm.scheme.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.scheme.FarmSchemeUtils;
import com.farm.scheme.POJO.PurchaseRequest;
import com.farm.scheme.POJO.SellRequest;
import com.farm.scheme.constant.FarmSchemeConstants;
import com.farm.scheme.dao.PurchaseRequestDao;
import com.farm.scheme.dao.SellRequestDao;
import com.farm.scheme.service.PurchaseRequestService;
import com.google.common.base.Strings;

@Service
public class PurchaseRequestServiceImpl implements PurchaseRequestService {

	@Autowired
	PurchaseRequestDao purchaseRequestDao;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	SellRequestDao sellRequestDao;

	@Override
	public ResponseEntity<?> purchaseRequest(Map<String, String> requestMap) {
		try {
			PurchaseRequest purchaseRequest = getPurchaseRequestFromMap(requestMap);
			purchaseRequestDao.insert(purchaseRequest);
			return new ResponseEntity<>("{\"message\":\"Sell Request Submmited.\"}", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private PurchaseRequest getPurchaseRequestFromMap(Map<String, String> requestMap) {
		PurchaseRequest purchaseRequest = new PurchaseRequest();
		purchaseRequest.setId(FarmSchemeUtils.getId(FarmSchemeConstants.PURCHASE_REQUEST_PREFIX));
		purchaseRequest.setFarmerId(requestMap.get(FarmSchemeConstants.FARMER_ID));
		purchaseRequest.setCropType(requestMap.get(FarmSchemeConstants.CROP_TYPE));
		purchaseRequest.setCropName(requestMap.get(FarmSchemeConstants.CROP_NAME));
		purchaseRequest.setFertilizerType(requestMap.get(FarmSchemeConstants.FERTILIZER_TYPE));
		purchaseRequest.setQuantity(Integer.parseInt(requestMap.get(FarmSchemeConstants.QUANTITY)));
		purchaseRequest.setSoilPhCertificate(requestMap.get(FarmSchemeConstants.SOIL_PH_CERTIFICATE));
		purchaseRequest.setBidderId(requestMap.get(FarmSchemeConstants.BIDDER_ID));
		purchaseRequest.setAmount(requestMap.get(FarmSchemeConstants.AMOUNT));
		purchaseRequest.setStatus(FarmSchemeConstants.REQUESTED);
		purchaseRequest.setSellRequestId(requestMap.get(FarmSchemeConstants.SELL_REQUEST_ID));
		purchaseRequest.setFullName(requestMap.get(FarmSchemeConstants.FULL_NAME));
		return purchaseRequest;
	}

	@Override
	public ResponseEntity<?> approvePurchaseRequest(String sellRequestId, String bidderId) {
		try {
			updateAllPurchaseRequest(sellRequestId, FarmSchemeConstants.REJECTED, null);
			updateAllPurchaseRequest(sellRequestId, FarmSchemeConstants.APPROVED, bidderId);
			Optional<SellRequest> sellRequest = sellRequestDao.findById(sellRequestId);
			SellRequest sellReq = sellRequest.get();
			sellReq.setStauts("Sold");
			sellRequestDao.save(sellReq);
			return new ResponseEntity<>("{\"message\":\"Purchase Approved.\"}", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void updateAllPurchaseRequest(String sellRequestId, String status, String bidderId) {
		Query query = new Query();
		if (!Strings.isNullOrEmpty(bidderId))
			query.addCriteria(Criteria.where(FarmSchemeConstants.BIDDER_ID).in(bidderId));
		query.addCriteria(Criteria.where(FarmSchemeConstants.SELL_REQUEST_ID).in(sellRequestId));
		Update update = new Update();
		update.set(FarmSchemeConstants.STATUS, status);
		mongoTemplate.updateMulti(query, update, PurchaseRequest.class);
	}

	@Override
	public ResponseEntity<?> getSellRequestByIdAndStatus(String id, String status) {
		try {
			if (id.contains(FarmSchemeConstants.FARMER_ID_PREFIX)) {
				return new ResponseEntity<>(purchaseRequestDao.getSellRequestByFarmerIdAndStatus(id, status),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(purchaseRequestDao.getSellRequestByBidderIdAndStatus(id, status),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> getMaxBid(String sellRequestId) {
		final Query query = new Query().limit(2).with(Sort.by(Sort.Direction.DESC, FarmSchemeConstants.AMOUNT));
		List<PurchaseRequest> pur = mongoTemplate.find(query, PurchaseRequest.class);
		return new ResponseEntity<>(pur, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getAllPurchaseRequestByRequestId(String sellRequestId) {
		return new ResponseEntity<>(purchaseRequestDao.getAllPurchaseRequestByRequestId(sellRequestId), HttpStatus.OK);
	}

}
