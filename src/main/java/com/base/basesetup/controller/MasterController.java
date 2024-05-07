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
import com.base.basesetup.dto.GroupLedgerDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.dto.TcsMasterDTO;
import com.base.basesetup.dto.TdsMasterDTO;
import com.base.basesetup.entity.AccountVO;
import com.base.basesetup.entity.GroupLedgerVO;
import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.entity.TcsMasterVO;
import com.base.basesetup.entity.TdsMasterVO;
import com.base.basesetup.service.MasterService;

@CrossOrigin
@RestController
@RequestMapping("api/master")
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

	@GetMapping("/getAllSetTaxRate")
	public ResponseEntity<ResponseDTO> getAllSetTaxRate() {
		String methodName = "getAllSetTaxRate()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		try {
			setTaxRateVO = masterService.getAllSetTaxRate();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "SetTaxRate information getAll successfully");
			responseObjectsMap.put("setTaxRateVO", setTaxRateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "SetTaxRate information receive getAll failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("updateCreateSetTaxRate")
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

	@GetMapping("/getAllTaxMaster")
	public ResponseEntity<ResponseDTO> getAllTaxMaster() {
		String methodName = "getAllTaxMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		try {
			taxMasterVO = masterService.getAllTaxMaster();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxMaster information getAll successfully");
			responseObjectsMap.put("taxMasterVO", taxMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TaxMaster information receive failedAll",
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

	@GetMapping("/getAllTcsMaster")
	public ResponseEntity<ResponseDTO> getAllTcsMaster() {
		String methodName = "getAllTcsMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		try {
			tcsMasterVO = masterService.getAllTcsMaster();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TcsMaster information getAll successfully");
			responseObjectsMap.put("tcsMasterVO", tcsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TcsMaster information receive failedAll",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("updateCreateTcsMaster")
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

	@GetMapping("/getAllTdsMaster")
	public ResponseEntity<ResponseDTO> getAllTdsMaster() {
		String methodName = "getAllTdsMaster()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		try {
			tdsMasterVO = masterService.getAllTdsMaster();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TdsMaster information getAll successfully");
			responseObjectsMap.put("tdsMasterVO", tdsMasterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "TdsMaster information receive failedAll",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("updateCreateTdsMaster")
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

			LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
			return ResponseEntity.ok().body(responseDTO);

		}
		
		 @PutMapping("updateCreateTdsMaster")
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
		   
	//Account 
		 
		 @GetMapping("/getAllAccountByOrgId")
			public ResponseEntity<ResponseDTO> getAllAccountByOrgId( @RequestParam(required = false) Long orgId) {
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
					responseDTO = createServiceResponseError(responseObjectsMap, "Account information receive failedByOrgId", errorMsg);
				}
				LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
				return ResponseEntity.ok().body(responseDTO);


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
	public ResponseEntity<ResponseDTO> getAllAccountByaccountId(@RequestParam(required = false) Long accountId) {
		String methodName = "getAllAccountByaccountId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<AccountVO> accountVO = new ArrayList<>();
		try {
			accountVO = masterService.getAllAccountByaccountId(accountId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Account information get successfully By AccountID");
			responseObjectsMap.put("accountVO", accountVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Account information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}


			}
	
		 @PutMapping("updateCreateAccount")
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
						errorMsg = "Account not found for ID: " + accountDTO.getAccountId();
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
	
		 //Group Ledger
		 @GetMapping("/getAllGroupLedgerByOrgId")
			public ResponseEntity<ResponseDTO> getAllGroupLedgerByOrgId( @RequestParam(required = false) Long orgId) {
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
					responseDTO = createServiceResponseError(responseObjectsMap, "Group Ledger information receive failedByOrgId", errorMsg);
				}
				LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
				return ResponseEntity.ok().body(responseDTO);

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

	@PutMapping("updateCreateGroupLedger")
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
				errorMsg = "Group Ledger not found for ID: " + groupLedgerDTO.getGLId();
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

}
