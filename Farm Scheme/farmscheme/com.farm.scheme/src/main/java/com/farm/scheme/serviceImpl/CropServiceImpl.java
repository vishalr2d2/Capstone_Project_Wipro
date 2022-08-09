package com.farm.scheme.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.scheme.FarmSchemeUtils;
import com.farm.scheme.POJO.Crop;
import com.farm.scheme.constant.FarmSchemeConstants;
import com.farm.scheme.dao.CropDao;
import com.farm.scheme.service.CropService;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	CropDao cropDao;

	@Override
	public ResponseEntity<?> addCrop(Map<String, String> requestMap) {
		try {
			Crop crop = getCropFromMap(requestMap);
			cropDao.insert(crop);
			return new ResponseEntity<>("{\"message\":\"Crop Details Added Successfully.\"}", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Crop getCropFromMap(Map<String, String> requestMap) {
		Crop crop = new Crop();
		crop.setId(FarmSchemeUtils.getId(FarmSchemeConstants.CROP_PREFIX));
		crop.setCropName(requestMap.get(FarmSchemeConstants.CROP_NAME));
		crop.setFarmerShare(requestMap.get(FarmSchemeConstants.FARMER_SHARE));
		crop.setSumInsured(requestMap.get(FarmSchemeConstants.SUM_INSURED));
		return crop;
	}

	@Override
	public ResponseEntity<?> getAllCrop() {
		try {
			List<Crop> cropList = cropDao.findAll();
			List<String> nameList = cropList.stream().map(Crop::getCropName).collect(Collectors.toList());
			return new ResponseEntity<>(nameList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
