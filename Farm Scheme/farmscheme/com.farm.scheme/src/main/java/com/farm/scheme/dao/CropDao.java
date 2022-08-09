package com.farm.scheme.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.farm.scheme.POJO.Bidder;
import com.farm.scheme.POJO.Crop;

public interface CropDao extends MongoRepository<Crop, String> {

	@Query("{cropName: ?0}")
	Crop getCropByName(String cropName);

}
