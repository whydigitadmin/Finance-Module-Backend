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
import com.base.basesetup.dto.ArapDetailsDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.entity.ArapDetailsVO;
import com.base.basesetup.service.ArApDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/api/costInvoice")
public class ArApDetailsController extends BaseController{

	public static final Logger LOGGER = LoggerFactory.getLogger(ArApDetailsController.class);

	@Autowired
	ArApDetailsService arApDetailsService;
	
	
	// ArapDetails

		@GetMapping("/getAllArapDetailsByOrgId")
		public ResponseEntity<ResponseDTO> getAllArapDetailsByOrgId(@RequestParam(required = false) Long orgId) {
			String methodName = "getAllArapDetailsByOrgId()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<ArapDetailsVO> arapDetailsVO = new ArrayList<>();
			try {
				arapDetailsVO = arApDetailsService.getAllArapDetailsByOrgId(orgId);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ArapDetails information get successfully By OrgId");
				responseObjectsMap.put("arapDetailsVO", arapDetailsVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"ArapDetails information receive failed By OrgId", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);

		}

		@GetMapping("/getAllArapDetailsById")
		public ResponseEntity<ResponseDTO> getAllArapDetailsById(@RequestParam(required = false) Long id) {
			String methodName = "getAllArapDetailsById()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<ArapDetailsVO> arapDetailsVO = new ArrayList<>();
			try {
				arapDetailsVO = arApDetailsService.getAllArapDetailsById(id);
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ArapDetails information get successfully By id");
				responseObjectsMap.put("arapDetailsVO", arapDetailsVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"ArapDetails information receive failed By OrgId", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);
		}

		@PutMapping("/createupdateArapDetails")
		public ResponseEntity<ResponseDTO> createupdateArapDetails(@Valid @RequestBody ArapDetailsDTO arapDetailsDTO) {
			String methodName = "createupdateArapDetails()";

			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;

			try {
		        Map<String, Object> arapDetailsVO = arApDetailsService.createupdateArapDetails(arapDetailsDTO);
		        responseObjectsMap.put(CommonConstant.STRING_MESSAGE, arapDetailsVO.get("message"));
		        responseObjectsMap.put("arapDetailsVO", arapDetailsVO.get("arapDetailsVO")); // Corrected key
		        responseDTO = createServiceResponse(responseObjectsMap);
		    } catch (Exception e) {
		        errorMsg = e.getMessage();
		        LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		        responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		    }
		    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		    return ResponseEntity.ok().body(responseDTO);
		}

		@GetMapping("/getArapDetailsByActive")
		public ResponseEntity<ResponseDTO> getArapDetailsByActive() {
			String methodName = "getArapDetailsByActive()";
			LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
			String errorMsg = null;
			Map<String, Object> responseObjectsMap = new HashMap<>();
			ResponseDTO responseDTO = null;
			List<ArapDetailsVO> arapDetailsVO = new ArrayList<>();
			try {
				arapDetailsVO = arApDetailsService.getArapDetailsByActive();
			} catch (Exception e) {
				errorMsg = e.getMessage();
				LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			}
			if (StringUtils.isBlank(errorMsg)) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ArapDetails information get successfully By Active");
				responseObjectsMap.put("arapDetailsVO", arapDetailsVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap,
						"ArapDetails information receive failed By Active", errorMsg);
			}
			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);

		}
}
