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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.GlobalParameterDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.entity.GlobalParameterVO;
import com.base.basesetup.service.GlobalParameterService;

@RestController
@RequestMapping("/api/GlobalParam")
public class GloblParemeterController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(GloblParemeterController.class);


	@Autowired
	GlobalParameterService globalParameterService;

	@GetMapping("/getGlobalParametrByUserId")
	public ResponseEntity<ResponseDTO> getGlobalParametrByUserId(@RequestParam(required = false) Long userId) {
		String methodName = "getGlobalParametrByUserId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		GlobalParameterVO globalParameterVO =null;
		try {
			globalParameterVO = globalParameterService.getGlobalParameterByUserId(userId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "GlobalParameter information get successfully By userId");
			responseObjectsMap.put("globalParameterVO", globalParameterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "GlobalParameter information receive failed By userId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/createUpdateGlobalParameter")
	public ResponseEntity<ResponseDTO> createUpdateGlobalParameter(
			@Valid @RequestBody GlobalParameterDTO globalParameterDTO) {
		String methodName = "createUpdateGlobalParameter()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			GlobalParameterVO globalParameterVO = globalParameterService.createUpdateGlobalParameter(globalParameterDTO);
			if (globalParameterVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Global Parameter Create or Updated Successfully");
				responseObjectsMap.put("globalParameterVO", globalParameterVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Global Parameter not found for ID: " + globalParameterDTO.getUserId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Global Parameter Create or Updated Failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Global Parameter Create or Updated Failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAccessBranchByUserId")
	public ResponseEntity<ResponseDTO> getAccessBranchByUserId(@RequestParam(required = false) Long userId) {
		String methodName = "getAccessBranchByUserId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String,Object>> userAccessBranch=new ArrayList<>();
		try {
			userAccessBranch = globalParameterService.getUserAccessBranchByUserId(userId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "User Access Branch information get successfully By userId");
			responseObjectsMap.put("userAccessBranch", userAccessBranch);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "User Access Branch information receive failed By userId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getFinYearforGlobalParam")
	public ResponseEntity<ResponseDTO> getFinYearforGlobalParam(@RequestParam(required = false) Long orgId) {
		String methodName = "getFinYearforGlobalParam()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String,Object>> finYear=new ArrayList<>();
		try {
			finYear = globalParameterService.getFinYearforGlobalParam(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Finyear information get successfully By userId");
			responseObjectsMap.put("finYear", finYear);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Finyear information receive failed By userId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
  }

	@GetMapping("/getCompanyByUserID")
	public ResponseEntity<ResponseDTO> getCompanyByUserID(@RequestParam(required = false) Long userId) {
		String methodName = "getCompanyByUserID()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String,Object>> company=new ArrayList<>();
		try {
			company = globalParameterService.getUserCompanyByUserId(userId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company information get successfully By userId");
			responseObjectsMap.put("company", company);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Company information receive failed By userId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
  }

	
}