package com.base.basesetup.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.entity.SetTaxRateVO;

import com.base.basesetup.entity.TaxMasterVO;

import com.base.basesetup.service.MasterService;




@CrossOrigin
@RestController
@RequestMapping("api/master")
public class MasterController extends BaseController {

	
	@Autowired
	MasterService masterService;
	
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterController.class);
	
	@GetMapping("/getAllSetTaxRateByOrgId")
	public ResponseEntity<ResponseDTO> getAllSetTaxRateByOrgId( @RequestParam(required = false) Long orgId) {
		String methodName = "getAllSetTaxRateByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		try {
			setTaxRateVO = masterService.getAllSetTaxRateByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "SetTaxRate information get successfullyByOrgId");
			responseObjectsMap.put("setTaxRateVO", setTaxRateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "SetTaxRate information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}
	

	//TAXMASTER
	
   @PutMapping("updateCreateTaxMaster")
	public ResponseEntity<ResponseDTO> updateCreateTaxMaster(@Valid @RequestBody TaxMasterDTO taxMasterDTO) {
		String methodName = "updateCreateTaxMaster()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			TaxMasterVO taxMasterVO = masterService.updateCreateTaxMaster(taxMasterDTO);
			if (taxMasterVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Tax Master updated successfully");
				responseObjectsMap.put("taxMasterVO", taxMasterVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Tax Master not found for ID: " + taxMasterDTO.getTaxMasterId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Tax Master update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Tax Master update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	

	
	
	
}
