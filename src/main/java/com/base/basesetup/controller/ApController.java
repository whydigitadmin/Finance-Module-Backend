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
import com.base.basesetup.dto.PaymentDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.entity.PaymentVO;
import com.base.basesetup.service.APService;

@CrossOrigin
@RestController
@RequestMapping("/api/payable")
public class ApController extends BaseController {

	@Autowired
	APService payableService;

	public static final Logger LOGGER = LoggerFactory.getLogger(ApController.class);

	@GetMapping("/getAllPaymentByOrgId")
	public ResponseEntity<ResponseDTO> getAllPaymentByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllPaymentByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentVO> paymentVO = new ArrayList<>();
		try {
			paymentVO = payableService.getAllPaymentByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Payment information get successfully By OrgId");
			responseObjectsMap.put("paymentVO", paymentVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Payment information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllPaymentById")
	public ResponseEntity<ResponseDTO> getAllPaymentById(@RequestParam(required = false) Long id) {
		String methodName = "getAllPaymentById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentVO> paymentVO = new ArrayList<>();
		try {
			paymentVO = payableService.getAllPaymentById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Payment information get successfully By id");
			responseObjectsMap.put("paymentVO", paymentVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Payment information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreatePayment")
	public ResponseEntity<ResponseDTO> updateCreatePayment(@Valid @RequestBody PaymentDTO paymentDTO) {
		String methodName = "updateCreatePayment()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			PaymentVO paymentVO = payableService.updateCreatePayment(paymentDTO);
			boolean isUpdate = paymentDTO.getId() != null;

			if (paymentVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Payment updated successfully" : "Payment created successfully");
				responseObjectsMap.put("paymentVO", paymentVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "Payment not found for ID: " + paymentDTO.getId() : "Payment creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Payment update failed" : "Payment creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = paymentDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Payment update failed" : "Payment creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// PaymentRegister
	@GetMapping("/getAllPaymentRegister")
	public ResponseEntity<ResponseDTO> getAllPaymentRegister(@RequestParam Long orgId, @RequestParam String branch,
			@RequestParam String branchCode, @RequestParam String finYear, @RequestParam String fromDate,
			@RequestParam String toDate, @RequestParam String subLedgerName) {
		String methodName = "getAllPaymentRegister()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> customer = new ArrayList<>();
		try {
			customer = payableService.getAllPaymentRegister(orgId, branch, branchCode, finYear, fromDate, toDate,
					subLedgerName);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Payment Register information get successfully");
			responseObjectsMap.put("PartyMasterVO", customer);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Payment Register information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}
