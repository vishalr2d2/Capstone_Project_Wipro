package com.farm.scheme.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.farm.scheme.POJO.Insurance;

public interface InsuranceDao extends MongoRepository<Insurance, String> {

	@Query("{id: ?0}")
	Insurance getInsuranceById(String id);

	@Query("{farmerId: ?0}")
	List<Insurance> getInsuranceByFarmerId(String id);

}
