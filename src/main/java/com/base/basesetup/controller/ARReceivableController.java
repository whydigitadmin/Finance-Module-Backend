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
import com.base.basesetup.dto.ReceiptReceivableDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.entity.ReceiptReceivableVO;
import com.base.basesetup.service.ARReceivableService;

@CrossOrigin
@RestController
@RequestMapping("/api/arreceivable")
public class ARReceivableController extends BaseController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ARReceivableController.class);
	
	@Autowired
	ARReceivableService arReceivableService;

	//Receipt
	
	@GetMapping("/getAllReceiptReceivableByOrgId")
	public ResponseEntity<ResponseDTO> getAllReceiptReceivableByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllReceiptReceivableByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReceiptReceivableVO> receiptReceivableVO = new ArrayList<>();
		try {
			receiptReceivableVO = arReceivableService.getAllReceiptReceivableByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ReceiptReceivable information get successfully By OrgId");
			responseObjectsMap.put("receiptReceivableVO", receiptReceivableVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReceiptReceivable information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllReceiptReceivableById")
	public ResponseEntity<ResponseDTO> getAllReceiptReceivableById(@RequestParam(required = false) Long id) {
		String methodName = "getAllReceiptReceivableById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReceiptReceivableVO> receiptReceivableVO = new ArrayList<>();
		try {
			receiptReceivableVO = arReceivableService.getAllReceiptReceivableById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ReceiptReceivable information get successfully By id");
			responseObjectsMap.put("receiptReceivableVO", receiptReceivableVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReceiptReceivable information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateReceiptReceivable")
	public ResponseEntity<ResponseDTO> updateCreateReceiptReceivable(
			@Valid @RequestBody ReceiptReceivableDTO receiptReceivableDTO) {
		String methodName = "updateCreateReceiptReceivable()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ReceiptReceivableVO receiptReceivableVO = arReceivableService
					.updateCreateReceiptReceivable(receiptReceivableDTO);
			boolean isUpdate = receiptReceivableDTO.getId() != null;

			if (receiptReceivableVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "ReceiptReceivable updated successfully" : "ReceiptReceivable created successfully");
				responseObjectsMap.put("receiptReceivableVO", receiptReceivableVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "ReceiptReceivable not found for ID: " + receiptReceivableDTO.getId()
						: "ReceiptReceivable creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "ReceiptReceivable update failed" : "ReceiptReceivable creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = receiptReceivableDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "ReceiptReceivable update failed" : "ReceiptReceivable creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getReceiptReceivableByActive")
	public ResponseEntity<ResponseDTO> getReceiptReceivableByActive() {
		String methodName = "getReceiptReceivableByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReceiptReceivableVO> receiptReceivableVO = new ArrayList<>();
		try {
			receiptReceivableVO = arReceivableService.getReceiptReceivableByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ReceiptReceivable information get successfully By Active");
			responseObjectsMap.put("receiptReceivableVO", receiptReceivableVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReceiptReceivable information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

}
