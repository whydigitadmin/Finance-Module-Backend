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
import com.base.basesetup.dto.AccountDTO;
import com.base.basesetup.dto.ChequeBoxDTO;
import com.base.basesetup.dto.ExRatesDTO;
import com.base.basesetup.dto.GroupLedgerDTO;
import com.base.basesetup.dto.HsnSacCodeDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.SubLedgerAccountDTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.dto.TcsMasterDTO;
import com.base.basesetup.dto.TdsMasterDTO;
import com.base.basesetup.entity.AccountVO;
import com.base.basesetup.entity.ChequeBoxVO;
import com.base.basesetup.entity.CostCenterVO;
import com.base.basesetup.entity.ExRatesVO;
import com.base.basesetup.entity.GroupLedgerVO;
import com.base.basesetup.entity.HsnSacCodeVO;
import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.SubLedgerAccountVO;
import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.entity.TcsMasterVO;
import com.base.basesetup.entity.TdsMasterVO;
import com.base.basesetup.service.MasterService;

@CrossOrigin
@RestController
@RequestMapping("/api/master")
public class MasterController extends BaseController {

	@Autowired
	MasterService masterService;

	public static final Logger LOGGER = LoggerFactory.getLogger(MasterController.class);

	// SetTaxRate

	@GetMapping("/getAllSetTaxRateByOrgId")
	public ResponseEntity<ResponseDTO> getAllSetTaxRateByOrgId(@RequestParam(required = false) Long orgId) {
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
			responseDTO = createServiceResponseError(responseObjectsMap, "SetTaxRate information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllSetTaxRateById")
	public ResponseEntity<ResponseDTO> getAllSetTaxRateById(@RequestParam(required = false) Long id) {
		String methodName = "getAllSetTaxRateById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		try {
			setTaxRateVO = masterService.getAllSetTaxRateById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "SetTaxRate information get successfullyById");
			responseObjectsMap.put("setTaxRateVO", setTaxRateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "SetTaxRate information receive failedById",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateSetTaxRate")
	public ResponseEntity<ResponseDTO> updateCreateSetTaxRate(@Valid @RequestBody SetTaxRateDTO setTaxRateDTO) {
		String methodName = "updateCreateSetTaxRate()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			SetTaxRateVO setTaxRateVO = masterService.updateCreateSetTaxRate(setTaxRateDTO);
			if (setTaxRateVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "SetTaxRate updated successfully");
				responseObjectsMap.put("setTaxRateVO", setTaxRateVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "SetTaxRate not found for ID: " + setTaxRateDTO.getSetTaxRateId();
				responseDTO = createServiceResponseError(responseObjectsMap, "SetTaxRateVO update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "SetTaxRateVO update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getSetTaxRateByActive")
	public ResponseEntity<ResponseDTO> getSetTaxRateByActive() {
		String methodName = "getSetTaxRateByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		try {
			setTaxRateVO = masterService.getSetTaxRateByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "SetTaxRate information get successfully By Active");
			responseObjectsMap.put("setTaxRateVO", setTaxRateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"SetTaxRate information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// TAXMASTER

	@GetMapping("/getAllTaxMasterByOrgId")
	public ResponseEntity<ResponseDTO> getAllTaxMasterByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllTaxMasterByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		try {
			taxMasterVO = masterService.getAllTaxMasterByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxMaster information getByOrgId successfully");
			responseObjectsMap.put("taxMasterVO", taxMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TaxMaster information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllTaxMasterById")
	public ResponseEntity<ResponseDTO> getAllTaxMasterById(@RequestParam(required = false) Long id) {
		String methodName = "getAllTaxMasterById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		try {
			taxMasterVO = masterService.getAllTaxMasterById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxMaster information getById successfully");
			responseObjectsMap.put("taxMasterVO", taxMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TaxMaster information receive failedById",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

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

	@GetMapping("/getTaxMasterByActive")
	public ResponseEntity<ResponseDTO> getTaxMasterByActive() {
		String methodName = "getTaxMasterByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		try {
			taxMasterVO = masterService.getTaxMasterByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxMaster information get successfully By Active");
			responseObjectsMap.put("taxMasterVO", taxMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"TaxMaster information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// TCSMASTER

	@GetMapping("/getAllTcsMasterByOrgId")
	public ResponseEntity<ResponseDTO> getAllTcsMasterByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllTcsMasterByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		try {
			tcsMasterVO = masterService.getAllTcsMasterByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TcsMaster information getByOrgId successfully");
			responseObjectsMap.put("tcsMasterVO", tcsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TcsMaster information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllTcsMasterById")
	public ResponseEntity<ResponseDTO> getAllTcsMasterById(@RequestParam(required = false) Long id) {
		String methodName = "getAllTcsMasterById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		try {
			tcsMasterVO = masterService.getAllTcsMasterById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TcsMaster information getById successfully");
			responseObjectsMap.put("tcsMasterVO", tcsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TcsMaster information receive failedById",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateTcsMaster")
	public ResponseEntity<ResponseDTO> updateCreateTcsMaster(@Valid @RequestBody TcsMasterDTO tcsMasterDTO) {
		String methodName = "updateCreateTcsMaster()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			TcsMasterVO tcsMasterVO = masterService.updateCreateTcsMaster(tcsMasterDTO);
			if (tcsMasterVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Tcs Master updated successfully");
				responseObjectsMap.put("tcsMasterVO", tcsMasterVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Tcs Master not found for ID: " + tcsMasterDTO.getTcsMasterId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Tcs Master update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Tcs Master update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getTcsMasterByActive")
	public ResponseEntity<ResponseDTO> getTcsMasterByActive() {
		String methodName = "getTcsMasterByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		try {
			tcsMasterVO = masterService.getTcsMasterByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TcsMaster information get successfully By Active");
			responseObjectsMap.put("tcsMasterVO", tcsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"TcsMaster information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// TDSMASTER

	@GetMapping("/getAllTdsMasterByOrgId")
	public ResponseEntity<ResponseDTO> getAllTdsMasterByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllTdsMasterByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		try {
			tdsMasterVO = masterService.getAllTdsMasterByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TdsMaster information getByOrgId successfully");
			responseObjectsMap.put("tdsMasterVO", tdsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TdsMaster information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllTdsMasterById")
	public ResponseEntity<ResponseDTO> getAllTdsMasterById(@RequestParam(required = false) Long id) {
		String methodName = "getAllTdsMasterById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		try {
			tdsMasterVO = masterService.getAllTdsMasterById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TdsMaster information getById successfully");
			responseObjectsMap.put("tdsMasterVO", tdsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TdsMaster information receive failedById",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateTdsMaster")
	public ResponseEntity<ResponseDTO> updateCreateTdsMaster(@Valid @RequestBody TdsMasterDTO tdsMasterDTO) {
		String methodName = "updateCreateTdsMaster()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			TdsMasterVO tdsMasterVO = masterService.updateCreateTdsMaster(tdsMasterDTO);
			if (tdsMasterVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Tds Master updated successfully");

				responseObjectsMap.put("tdsMasterVO", tdsMasterVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Tds Master not found for ID: " + tdsMasterDTO.getTdsMasterId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Tds Master update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Tds Master update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getTdsMasterByActive")
	public ResponseEntity<ResponseDTO> getTdsMasterByActive() {
		String methodName = "getTdsMasterByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		try {
			tdsMasterVO = masterService.getTdsMasterByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TdsMaster information get successfully By Active");
			responseObjectsMap.put("tdsMasterVO", tdsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"TdsMaster information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// Account

	@GetMapping("/getAllAccountByOrgId")
	public ResponseEntity<ResponseDTO> getAllAccountByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllAccountByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<AccountVO> accountVO = new ArrayList<>();
		try {
			accountVO = masterService.getAllAccountByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Account information get successfully ByOrgId");
			responseObjectsMap.put("accountVO", accountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Account information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllAccountById")
	public ResponseEntity<ResponseDTO> getAllAccountById(@RequestParam(required = false) Long id) {
		String methodName = "getAllAccountById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<AccountVO> accountVO = new ArrayList<>();
		try {
			accountVO = masterService.getAllAccountById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Account information get successfully By id");
			responseObjectsMap.put("accountVO", accountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Account information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateAccount")
	public ResponseEntity<ResponseDTO> updateCreateAccount(@Valid @RequestBody AccountDTO accountDTO) {
		String methodName = "updateCreateAccount()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			AccountVO accountVO = masterService.updateCreateAccount(accountDTO);
			if (accountVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Account updated successfully");
				responseObjectsMap.put("accountVO", accountVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Account not found for ID: " + accountDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Account update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Account update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAccountByActive")
	public ResponseEntity<ResponseDTO> getAccountByActive() {
		String methodName = "getAccountByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<AccountVO> accountVO = new ArrayList<>();
		try {
			accountVO = masterService.getAccountByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Account information get successfully By Active");
			responseObjectsMap.put("accountVO", accountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Account information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// Group Ledger
	@GetMapping("/getAllGroupLedgerByOrgId")
	public ResponseEntity<ResponseDTO> getAllGroupLedgerByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllGroupLedgerByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		try {
			groupLedgerVO = masterService.getAllGroupLedgerByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Group Ledger information get successfully ByOrgId");
			responseObjectsMap.put("groupLedgerVO", groupLedgerVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Group Ledger information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllGroupLedgerById")
	public ResponseEntity<ResponseDTO> getAllGroupLedgerById(@RequestParam(required = false) Long id) {
		String methodName = "getAllGroupLedgerById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		try {
			groupLedgerVO = masterService.getAllGroupLedgerById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Group Ledger information get successfully ByOrgId");
			responseObjectsMap.put("groupLedgerVO", groupLedgerVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Group Ledger information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateGroupLedger")
	public ResponseEntity<ResponseDTO> updateCreateGroupLedger(@Valid @RequestBody GroupLedgerDTO groupLedgerDTO) {
		String methodName = "updateCreateGroupLedger()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			GroupLedgerVO groupLedgerVO = masterService.updateCreateGroupLedger(groupLedgerDTO);
			if (groupLedgerVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Group Ledger updated successfully");
				responseObjectsMap.put("groupLedgerVO", groupLedgerVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Group Ledger not found for ID: " + groupLedgerDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Group Ledger update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Group Ledger update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getGroupLedgerByActive")
	public ResponseEntity<ResponseDTO> getGroupLedgerByActive() {
		String methodName = "getGroupLedgerByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		try {
			groupLedgerVO = masterService.getGroupLedgerByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "GroupLedger information get successfully By Active");
			responseObjectsMap.put("groupLedgerVO", groupLedgerVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GroupLedger information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// HsnSacCode
	@GetMapping("/getAllHsnSacCodeByOrgId")
	public ResponseEntity<ResponseDTO> getAllHsnSapCodeByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllHsnSacCodeByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<HsnSacCodeVO> hsnSacCodeVO = new ArrayList<>();
		try {
			hsnSacCodeVO = masterService.getAllHsnSacCodeByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "HsnSacCode information get successfully ByOrgId");
			responseObjectsMap.put("hsnSacCodeVO", hsnSacCodeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "HsnSacCode information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllHsnSacCodeById")
	public ResponseEntity<ResponseDTO> getAllHsnSacCodeById(@RequestParam(required = false) Long id) {
		String methodName = "getAllHsnSacCodeById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<HsnSacCodeVO> hsnSacCodeVO = new ArrayList<>();
		try {
			hsnSacCodeVO = masterService.getAllHsnSacCodeById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "HsnSacCode information get successfully By OrgId");
			responseObjectsMap.put("hsnSacCodeVO", hsnSacCodeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"HsnSacCode information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateHsnSacCode")
	public ResponseEntity<ResponseDTO> updateCreateHsnSacCode(@Valid @RequestBody HsnSacCodeDTO hsnSacCodeDTO) {
		String methodName = "updateCreateHsnSacCode()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			HsnSacCodeVO hsnSacCodeVO = masterService.updateCreateHsnSacCode(hsnSacCodeDTO);
			if (hsnSacCodeVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "HsnSacCode updated successfully");
				responseObjectsMap.put("hsnSacCodeVO", hsnSacCodeVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "HsnSacCode not found for ID: " + hsnSacCodeDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "HsnSacCode update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "HsnSacCode update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getHsnSacCodeByActive")
	public ResponseEntity<ResponseDTO> getHsnSacCodeByActive() {
		String methodName = "getHsnSacCodeByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<HsnSacCodeVO> hsnSacCodeVO = new ArrayList<>();
		try {
			hsnSacCodeVO = masterService.getHsnSacCodeByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "HsnSacCode information get successfully By Active");
			responseObjectsMap.put("hsnSacCodeVO", hsnSacCodeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"HsnSacCode information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// ExRates
	@GetMapping("/getAllExRatesByOrgId")
	public ResponseEntity<ResponseDTO> getAllExRatesByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllExRatesByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ExRatesVO> exRatesVO = new ArrayList<>();
		try {
			exRatesVO = masterService.getAllExRatesByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ExRates information get successfully ByOrgId");
			responseObjectsMap.put("exRatesVO", exRatesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "ExRates information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllExRatesById")
	public ResponseEntity<ResponseDTO> getAllExRatesById(@RequestParam(required = false) Long id) {
		String methodName = "getAllExRatesById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ExRatesVO> exRatesVO = new ArrayList<>();
		try {
			exRatesVO = masterService.getAllExRatesById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ExRates information get successfully By OrgId");
			responseObjectsMap.put("exRatesVO", exRatesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "ExRates information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateExRates")
	public ResponseEntity<ResponseDTO> updateCreateExRates(@Valid @RequestBody ExRatesDTO exRatesDTO) {
		String methodName = "updateCreateExRates()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ExRatesVO exRatesVO = masterService.updateCreateExRates(exRatesDTO);
			if (exRatesVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ExRates updated successfully");
				responseObjectsMap.put("exRatesVO", exRatesVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "ExRates not found for ID: " + exRatesDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "ExRates update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "ExRates update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getExRatesByActive")
	public ResponseEntity<ResponseDTO> getExRatesByActive() {
		String methodName = "getExRatesByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ExRatesVO> exRatesVO = new ArrayList<>();
		try {
			exRatesVO = masterService.getExRatesByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ExRates information get successfully By Active");
			responseObjectsMap.put("exRatesVO", exRatesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "ExRates information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}
	// SubLedgerAccount

	@GetMapping("/getAllSubLedgerAccountByOrgId")
	public ResponseEntity<ResponseDTO> getAllSubLedgerAccountByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllSubLedgerAccountByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		try {
			subLedgerAccountVO = masterService.getAllSubLedgerAccountByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"SubLedgerAccount information get successfully ByOrgId");
			responseObjectsMap.put("subLedgerAccountVO", subLedgerAccountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"SubLedgerAccount information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllSubLedgerAccountById")
	public ResponseEntity<ResponseDTO> getAllSubLedgerAccountById(@RequestParam(required = false) Long id) {
		String methodName = "getAllSubLedgerAccountById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		try {
			subLedgerAccountVO = masterService.getAllSubLedgerAccountById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"SubLedgerAccount information get successfully By Id");
			responseObjectsMap.put("subLedgerAccountVO", subLedgerAccountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"SubLedgerAccount information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateSubLedgerAccount")
	public ResponseEntity<ResponseDTO> updateCreateSubLedgerAccount(
			@Valid @RequestBody SubLedgerAccountDTO subLedgerAccountDTO) {
		String methodName = "updateCreateSubLedgerAccount()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			SubLedgerAccountVO subLedgerAccountVO = masterService.updateCreateSubLedgerAccount(subLedgerAccountDTO);
			if (subLedgerAccountVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "SubLedgerAccount updated successfully");
				responseObjectsMap.put("subLedgerAccountVO", subLedgerAccountVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "SubLedgerAccount not found for ID: " + subLedgerAccountDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "SubLedgerAccount update failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "SubLedgerAccount update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getSubLedgerAccountByActive")
	public ResponseEntity<ResponseDTO> getSubLedgerAccountByActive() {
		String methodName = "getSubLedgerAccountByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		try {
			subLedgerAccountVO = masterService.getSubLedgerAccountByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"SubLedgerAccount information get successfully By Active");
			responseObjectsMap.put("subLedgerAccountVO", subLedgerAccountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"SubLedgerAccount information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// ChequeBox

	@GetMapping("/getAllChequeBoxByOrgId")
	public ResponseEntity<ResponseDTO> getAllChequeBoxByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllChequeBoxByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ChequeBoxVO> chequeBoxVO = new ArrayList<>();
		try {
			chequeBoxVO = masterService.getAllChequeBoxByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChequeBox information get successfully ByOrgId");
			responseObjectsMap.put("chequeBoxVO", chequeBoxVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "ChequeBox information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllChequeBoxById")
	public ResponseEntity<ResponseDTO> getAllChequeBoxById(@RequestParam(required = false) Long id) {
		String methodName = "getAllChequeBoxById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ChequeBoxVO> chequeBoxVO = new ArrayList<>();
		try {
			chequeBoxVO = masterService.getAllChequeBoxById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChequeBox information get successfully By id");
			responseObjectsMap.put("chequeBoxVO", chequeBoxVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "ChequeBox information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateChequeBox")
	public ResponseEntity<ResponseDTO> updateCreateChequeBox(@Valid @RequestBody ChequeBoxDTO chequeBoxDTO) {
		String methodName = "updateCreateChequeBox()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ChequeBoxVO chequeBoxVO = masterService.updateCreateChequeBox(chequeBoxDTO);
			if (chequeBoxVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChequeBox updated successfully");
				responseObjectsMap.put("chequeBoxVO", chequeBoxVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "ChequeBox not found for ID: " + chequeBoxDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "ChequeBox update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "ChequeBox update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getChequeBoxByActive")
	public ResponseEntity<ResponseDTO> getChequeBoxByActive() {
		String methodName = "getChequeBoxByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ChequeBoxVO> chequeBoxVO = new ArrayList<>();
		try {
			chequeBoxVO = masterService.getChequeBoxByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChequeBox information get successfully By Active");
			responseObjectsMap.put("chequeBoxVO", chequeBoxVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ChequeBox information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

}