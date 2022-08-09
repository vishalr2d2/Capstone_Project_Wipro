package com.farm.scheme.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.farm.scheme.POJO.PurchaseRequest;

public interface PurchaseRequestDao extends MongoRepository<PurchaseRequest, String> {

	@Query("{farmerId: ?0, status: ?1}")
	List<PurchaseRequest> getSellRequestByFarmerIdAndStatus(String farmerId, String status);

	@Query("{bidderId: ?0, status: ?1}")
	List<PurchaseRequest> getSellRequestByBidderIdAndStatus(String bidderId, String status);

	@Query("{sellRequestId: ?0}")
	List<PurchaseRequest> getAllPurchaseRequestByRequestId(String sellRequestId);

}
