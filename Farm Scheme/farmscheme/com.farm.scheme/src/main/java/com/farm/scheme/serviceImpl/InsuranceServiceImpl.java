package com.farm.scheme.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm.scheme.FarmSchemeUtils;
import com.farm.scheme.POJO.Crop;
import com.farm.scheme.POJO.Insurance;
import com.farm.scheme.constant.FarmSchemeConstants;
import com.farm.scheme.dao.CropDao;
import com.farm.scheme.dao.InsuranceDao;
import com.farm.scheme.service.InsuranceService;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	InsuranceDao insuranceDao;

	@Autowired
	CropDao cropDao;

	@Override
	public ResponseEntity<?> calculate(Map<String, String> requestMap) {
		try {
			if (validateRequestMap(requestMap)) {
				Crop crop = cropDao.getCropByName(requestMap.get(FarmSchemeConstants.CROP_NAME));
				if (!java.util.Objects.isNull(crop)) {
					Map<String, String> returnMap = new HashMap<>();
					calculateWithData(requestMap, crop, returnMap);
					return new ResponseEntity<>(returnMap, HttpStatus.OK);
				} else
					return new ResponseEntity<>("{\"message\":\"Invalid crop name.\"}", HttpStatus.BAD_REQUEST);
			} else
				return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateRequestMap(Map<String, String> requestMap) {
		return requestMap.containsKey(FarmSchemeConstants.CROP_NAME)
				&& requestMap.containsKey(FarmSchemeConstants.AREA);
	}

	private void calculateWithData(Map<String, String> requestMap, Crop crop, Map<String, String> returnMap) {

		float sumInsuredPerHecter = Float.parseFloat(crop.getSumInsured());
		float totalSumInsured = Float.parseFloat(requestMap.get(FarmSchemeConstants.AREA)) * sumInsuredPerHecter;
		float farmerSharePercent = Float.parseFloat(crop.getFarmerShare());
		float farmerShare = (farmerSharePercent / 100) * totalSumInsured;

		returnMap.put(FarmSchemeConstants.INSURANCE_COMPANY, "AGRICULTURE INSURANCE COMPANY");
		returnMap.put(FarmSchemeConstants.SUM_INSURED_PER_HECTARE, String.valueOf(sumInsuredPerHecter));
		returnMap.put(FarmSchemeConstants.SHARE_PREMIUM, String.valueOf(farmerSharePercent) + " %");
		returnMap.put(FarmSchemeConstants.PREMIUM_AMOUNT, String.valueOf(farmerShare));
		returnMap.put(FarmSchemeConstants.CROP_NAME, crop.getCropName());
		returnMap.put(FarmSchemeConstants.AREA, requestMap.get(FarmSchemeConstants.AREA));
		returnMap.put(FarmSchemeConstants.SUM_INSURED, String.valueOf(totalSumInsured));
	}

	@Override
	public ResponseEntity<?> applyForInsurance(Map<String, String> requestMap) {
		try {
			if (validateInsuranceMap(requestMap)) {
				Insurance insurance = getInsuranceFromMap(requestMap);
				Insurance insur = insuranceDao.insert(insurance);
				return new ResponseEntity<>(
						"{\"message\":\"Applied for Insurance Successful.Your Policy no is :- " + insur.getId() + "\"}",
						HttpStatus.OK);
			} else
				return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateInsuranceMap(Map<String, String> requestMap) {
		return requestMap.containsKey(FarmSchemeConstants.AREA)
				&& requestMap.containsKey(FarmSchemeConstants.INSURANCE_COMPANY)
				&& requestMap.containsKey(FarmSchemeConstants.SUM_INSURED_PER_HECTARE)
				&& requestMap.containsKey(FarmSchemeConstants.SHARE_PREMIUM)
				&& requestMap.containsKey(FarmSchemeConstants.PREMIUM_AMOUNT)
				&& requestMap.containsKey(FarmSchemeConstants.CROP_NAME)
				&& requestMap.containsKey(FarmSchemeConstants.SUM_INSURED)
				&& requestMap.containsKey(FarmSchemeConstants.FULL_NAME);
	}

	private Insurance getInsuranceFromMap(Map<String, String> requestMap) {
		Insurance insurance = new Insurance();
		insurance.setId(FarmSchemeUtils.getId(FarmSchemeConstants.POLICY_PREFIX));
		insurance.setArea(requestMap.get(FarmSchemeConstants.AREA));
		insurance.setInsuranceCompany(requestMap.get(FarmSchemeConstants.INSURANCE_COMPANY));
		insurance.setSumInsuredPerHectare(requestMap.get(FarmSchemeConstants.SUM_INSURED_PER_HECTARE));
		insurance.setSharePremium(requestMap.get(FarmSchemeConstants.SHARE_PREMIUM));
		insurance.setPremiumAmount(requestMap.get(FarmSchemeConstants.PREMIUM_AMOUNT));
		insurance.setCropName(requestMap.get(FarmSchemeConstants.CROP_NAME));
		insurance.setSumInsured(requestMap.get(FarmSchemeConstants.SUM_INSURED));
		insurance.setInsuredName(requestMap.get(FarmSchemeConstants.FULL_NAME));
		insurance.setFarmerId(requestMap.get(FarmSchemeConstants.FARMER_ID));
		return insurance;
	}

	@Override
	public ResponseEntity<?> getInsuranceById(String id) {
		try {
			if (id.contains(FarmSchemeConstants.FARMER_ID_PREFIX))
				return new ResponseEntity<>(insuranceDao.getInsuranceByFarmerId(id), HttpStatus.OK);
			else
				return new ResponseEntity<>(insuranceDao.getInsuranceById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<?> claimInsurance(Map<String, String> requestMap) {
		try {
			if (validateClaimMap(requestMap)) {
				Insurance insurance = insuranceDao.getInsuranceById(requestMap.get(FarmSchemeConstants.ID));
				insurance.setStatus(FarmSchemeConstants.CLAIMED);
				insurance.setCauseOfLoss(requestMap.get(FarmSchemeConstants.CAUSE_OF_LOSS));
				insurance.setDateOfLoss(requestMap.get(FarmSchemeConstants.DATE_OF_LOSS));
				insurance.setSupportingDocument(requestMap.get(FarmSchemeConstants.SUPPORTING_DOCUMENT));
				insuranceDao.save(insurance);
				return new ResponseEntity<>("{\"message\":\"Application Submitted\"}", HttpStatus.OK);
			} else
				return new ResponseEntity<>("{\"message\":\"Invalid Data.\"}", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("{\"message\":\"Something Went Wrong.\"}", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private boolean validateClaimMap(Map<String, String> requestMap) {
		return requestMap.containsKey(FarmSchemeConstants.ID)
				&& requestMap.containsKey(FarmSchemeConstants.CAUSE_OF_LOSS)
				&& requestMap.containsKey(FarmSchemeConstants.DATE_OF_LOSS)
				&& requestMap.containsKey(FarmSchemeConstants.SUPPORTING_DOCUMENT);
	}

}
