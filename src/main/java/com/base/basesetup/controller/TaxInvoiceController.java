package com.base.basesetup.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.service.TaxInvoiceService;

public class TaxInvoiceController extends BaseController {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TaxInvoiceController.class);

	@Autowired
	TaxInvoiceService taxInvoiceService;

	// TaxInvoice

	@GetMapping("/getAllTaxInvoiceByFinYearAndBranchCode")
	public ResponseEntity<ResponseDTO> getAllTaxInvoiceByFinYearAndBranchCode(@RequestParam Long orgId,@RequestParam String finYear,@RequestParam String branchCode) {
		String methodName = "getAllTaxInvoiceByFinYearAndBranchCode()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		try {
			taxInvoiceVO = taxInvoiceService.getAllTaxInvoiceByFinYearAndOrgId(orgId,finYear,branchCode);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxInvoice information get successfully ByOrgId");
			responseObjectsMap.put("taxInvoiceVO", taxInvoiceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TaxInvoice information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getTaxInvoiceById")
	public ResponseEntity<ResponseDTO> getTaxInvoiceById(@RequestParam Long id) {
		String methodName = "getTaxInvoiceById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		TaxInvoiceVO taxInvoiceVO = new TaxInvoiceVO();
		try {
			taxInvoiceVO = taxInvoiceService.getTaxInvoiceById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxInvoice information get successfully By id");
			responseObjectsMap.put("taxInvoiceVO", taxInvoiceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TaxInvoice information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateTaxInvoice")
	public ResponseEntity<ResponseDTO> updateCreateTaxInvoice(@RequestBody TaxInvoiceDTO taxInvoiceDTO) {
		String methodName = "updateCreateTaxInvoice()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> taxInvoiceVO = taxInvoiceService.updateCreateTaxInvoice(taxInvoiceDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, taxInvoiceVO.get("message"));
			responseObjectsMap.put("taxVO", taxInvoiceVO.get("taxInvoiceVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}


	@GetMapping("/getTaxInvoiceByDocId")
	public ResponseEntity<ResponseDTO> getTaxInvoiceByDocId(@RequestParam Long orgId, @RequestParam String docId) {
		String methodName = "getTaxInvoiceByDocId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		TaxInvoiceVO taxInvoiceVO = new TaxInvoiceVO();
		try {
			taxInvoiceVO = taxInvoiceService.getTaxInvoiceByDocId(orgId, docId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "OriginBill information get successfully By docid");
			responseObjectsMap.put("taxInvoiceVO", taxInvoiceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"OriginBill information receive failed By docid", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

}
