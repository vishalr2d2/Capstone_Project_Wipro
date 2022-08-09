package com.farm.scheme.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.farm.scheme.POJO.Farmer;

public interface FarmerDao extends MongoRepository<Farmer, String> {

	@Query("{emailId: ?0, password: ?1}")
	Farmer login(String emailId, String password);

}
