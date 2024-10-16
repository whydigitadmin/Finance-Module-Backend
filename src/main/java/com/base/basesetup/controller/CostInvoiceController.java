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
import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.service.CostInvoiceService;

@CrossOrigin
@RestController
@RequestMapping("/api/costInvoice")
public class CostInvoiceController extends BaseController{

	public static final Logger LOGGER = LoggerFactory.getLogger(CostInvoiceController.class);
	
	@Autowired
	CostInvoiceService costInvoiceService;
	
	// CostInvoice

		@GetMapping("/getAllCostInvoiceByOrgId")
		public ResponseEntity<ResponseDTO> getAllCostInvoiceByOrgId(@RequestParam(required = false) Long orgId) {
			String methodName = "getAllCostInvoiceByOrgId()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
			try {
				costInvoiceVO = costInvoiceService.getAllCostInvoiceByOrgId(orgId);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CostInvoice information get successfully By OrgId");
				responseObjectsMap.put("costInvoiceVO", costInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"CostInvoice information receive failed By OrgId", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);

		}

		@GetMapping("/getAllCostInvoiceById")
		public ResponseEntity<ResponseDTO> getAllCostInvoiceById(@RequestParam(required = false) Long id) {
			String methodName = "getAllCostInvoiceById()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
			try {
				costInvoiceVO = costInvoiceService.getAllCostInvoiceById(id);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CostInvoice information get successfully By id");
				responseObjectsMap.put("costInvoiceVO", costInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"CostInvoice information receive failed By OrgId", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);
		}

		@PutMapping("/updateCreateCostInvoice")
		public ResponseEntity<ResponseDTO> updateCreateCostInvoice(@Valid @RequestBody CostInvoiceDTO costInvoiceDTO) {
			String methodName = "updateCreateCostInvoice()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;

			try {
		        Map<String, Object> costInvoiceVO = costInvoiceService.updateCreateCostInvoice(costInvoiceDTO);
		        responseObjectsMap.put(CommonConstant.STRING_MESSAGE, costInvoiceVO.get("message"));
		        responseObjectsMap.put("costInvoiceVO", costInvoiceVO.get("costInvoiceVO")); // Corrected key
		        responseDTO = createServiceResponse(responseObjectsMap);
		    } catch (Exception e) {
		        errorMsg = e.getMessage();
		        LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		        responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		    }
		    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		    return ResponseEntity.ok().body(responseDTO);
		}

		@GetMapping("/getCostInvoiceByActive")
		public ResponseEntity<ResponseDTO> getCostInvoiceByActive() {
			String methodName = "getCostInvoiceByActive()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
			try {
				costInvoiceVO = costInvoiceService.getCostInvoiceByActive();
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CostInvoice information get successfully By Active");
				responseObjectsMap.put("costInvoiceVO", costInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"CostInvoice information receive failed By Active", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);

		}

		@GetMapping("/getCostInvoiceByDocId")
		public ResponseEntity<ResponseDTO> getCostInvoiceByDocId(@RequestParam Long orgId, @RequestParam String docId) {
			String methodName = "getCostInvoiceByDocId()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			CostInvoiceVO costInvoiceVO = new CostInvoiceVO();
			try {
				costInvoiceVO = costInvoiceService.getCostInvoiceByDocId(orgId, docId);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "OriginBill information get successfully By docid");
				responseObjectsMap.put("costInvoiceVO", costInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"OriginBill information receive failed By docid", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);

		}
		
		@GetMapping("/getCostInvoiceDocId")
		public ResponseEntity<ResponseDTO> getCostInvoiceDocId(@RequestParam Long orgId, @RequestParam String finYear,
				@RequestParam String branch, @RequestParam String branchCode) {

			String methodName = "getCostInvoiceDocId()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			String mapp = "";

			try {
				mapp = costInvoiceService.getCostInvoiceDocId(orgId, finYear, branch, branchCode);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}

			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "CostInvoiceDocId information retrieved successfully");
				responseObjectsMap.put("taxInvoiceDocId", mapp);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"Failed to retrieve CostInvoice Docid information", errorMsg);
			}

			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);
		}

		
}
