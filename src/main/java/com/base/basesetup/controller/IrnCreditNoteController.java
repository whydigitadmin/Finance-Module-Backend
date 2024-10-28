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
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.service.IrnCreditNoteService;

@CrossOrigin
@RestController
@RequestMapping("/api/irnCreditNote")
public class IrnCreditNoteController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(IrnCreditNoteController.class);

	@Autowired
	IrnCreditNoteService irnCreditService;

	@GetMapping("/getAllIrnCreditByOrgId")
	public ResponseEntity<ResponseDTO> getAllIrnCreditByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllIrnCreditByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<IrnCreditVO> irnCreditVO = new ArrayList<>();
		try {
			irnCreditVO = irnCreditService.getAllIrnCreditByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "IrnCredit information get successfully ByOrgId");
			responseObjectsMap.put("irnCreditVO", irnCreditVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "IrnCredit information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllIrnCreditById")
	public ResponseEntity<ResponseDTO> getAllIrnCreditById(@RequestParam(required = false) Long id) {
		String methodName = "getAllIrnCreditById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<IrnCreditVO> irnCreditVO = new ArrayList<>();
		try {
			irnCreditVO = irnCreditService.getAllIrnCreditById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "IrnCredit information get successfully By id");
			responseObjectsMap.put("irnCreditVO", irnCreditVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "IrnCredit information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateIrnCredit")
	public ResponseEntity<ResponseDTO> updateCreateIrnCredit(@Valid @RequestBody IrnCreditDTO irnCreditDTO) {
		String methodName = "updateCreateIrnCredit()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			Map<String, Object> irnCreditVO = irnCreditService.updateCreateIrnCredit(irnCreditDTO);
			boolean isUpdate = irnCreditDTO.getId() != null;

			if (irnCreditVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "IrnCredit updated successfully" : "IrnCredit created successfully");
				responseObjectsMap.put("irnCreditVO", irnCreditVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "IrnCredit not found for ID: " + irnCreditDTO.getId()
						: "IrnCredit creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "IrnCredit update failed" : "IrnCredit creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = irnCreditDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "IrnCredit update failed" : "IrnCredit creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getIrnCreditByActive")
	public ResponseEntity<ResponseDTO> getIrnCreditByActive() {
		String methodName = "getIrnCreditByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<IrnCreditVO> irnCreditVO = new ArrayList<>();
		try {
			irnCreditVO = irnCreditService.getIrnCreditByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "IrnCredit information get successfully By Active");
			responseObjectsMap.put("irnCreditVO", irnCreditVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"IrnCredit information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getPartyNameAndPartyCodeAndPartyTypeForIrn")
	public ResponseEntity<ResponseDTO> getPartyNameAndPartyCodeAndPartyTypeForIrn(@RequestParam Long orgId) {
		String methodName = "getPartyNameAndPartyCodeAndPartyTypeForIrn()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> customer = new ArrayList<>();
		try {
			customer = irnCreditService.getPartyNameAndPartyCodeAndPartyTypeForIrn(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Party information get successfully");
			responseObjectsMap.put("irnCreditVO", customer);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Party information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}
