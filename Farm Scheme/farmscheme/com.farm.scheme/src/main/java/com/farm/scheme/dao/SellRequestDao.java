package com.farm.scheme.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.farm.scheme.POJO.SellRequest;

public interface SellRequestDao extends MongoRepository<SellRequest, String> {

	@Query("{farmerId: ?0}")
	List<SellRequest> getSellRequestByFarmer(String farmerId);

	@Query(value = "{ status : ?0}", fields = "{ cropName : 1 }")
	List<SellRequest> findByStatus(String stauts);

}
