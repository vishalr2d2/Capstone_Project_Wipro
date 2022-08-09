package com.farm.scheme.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.farm.scheme.POJO.Bidder;

public interface BidderDao extends MongoRepository<Bidder, String> {

	@Query("{emailId: ?0, password: ?1}")
	Bidder login(String emailId, String password);
	
}
