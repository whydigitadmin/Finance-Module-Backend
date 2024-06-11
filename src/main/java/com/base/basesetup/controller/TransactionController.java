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
import com.base.basesetup.dto.BrsOpeningDTO;
import com.base.basesetup.dto.ChartCostCenterDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.BrsOpeningVO;
import com.base.basesetup.entity.ChartCostCenterVO;
import com.base.basesetup.entity.DailyMonthlyExRatesVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.service.TransactionService;

@CrossOrigin
@RestController
@RequestMapping("/api/transaction")
public class TransactionController extends BaseController {

	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	TransactionService transactionService;

	// TaxInvoice

	@GetMapping("/getAllTaxInvoiceByOrgId")
	public ResponseEntity<ResponseDTO> getAllTaxInvoiceByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllTaxInvoiceByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		try {
			taxInvoiceVO = transactionService.getAllTaxInvoiceByOrgId(orgId);
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

	@GetMapping("/getAllTaxInvoiceById")
	public ResponseEntity<ResponseDTO> getAllTaxInvoiceById(@RequestParam(required = false) Long id) {
		String methodName = "getAllTaxInvoiceById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		try {
			taxInvoiceVO = transactionService.getAllTaxInvoiceById(id);
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
	public ResponseEntity<ResponseDTO> updateCreateTaxInvoice(@Valid @RequestBody TaxInvoiceDTO taxInvoiceDTO) {
		String methodName = "updateCreateTaxInvoice()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			TaxInvoiceVO taxInvoiceVO = transactionService.updateCreateTaxInvoice(taxInvoiceDTO);
			if (taxInvoiceVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxInvoice updated successfully");
				responseObjectsMap.put("taxInvoiceVO", taxInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "TaxInvoice not found for ID: " + taxInvoiceDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "TaxInvoice update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "TaxInvoice update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getTaxInvoiceByActive")
	public ResponseEntity<ResponseDTO> getTaxInvoiceByActive() {
		String methodName = "getTaxInvoiceByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		try {
			taxInvoiceVO = transactionService.getTaxInvoiceByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "TaxInvoice information get successfully By Active");
			responseObjectsMap.put("taxInvoiceVO", taxInvoiceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"TaxInvoice information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllTaxInvoiceDocIdByOrgId")
	public ResponseEntity<ResponseDTO> getAllTaxInvoiceDocIdByOrgId(@RequestParam Long orgId) {
		String methodName = "getAllTaxInvoiceDocIdByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		try {
			taxInvoiceVO = transactionService.getAllTaxInvoiceDocIdByOrgId(orgId);
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

	@GetMapping("/getAllTaxInvoiceByDocIdAndOrgId")
	public ResponseEntity<ResponseDTO> getAllTaxInvoiceByDocId(@RequestParam Long orgId, @RequestParam String docId) {
		String methodName = "getAllTaxInvoiceByDocId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		try {
			taxInvoiceVO = transactionService.getAllTaxInvoiceByDocId(orgId, docId);
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
	// IrnCredit

	@GetMapping("/getAllIrnCreditByOrgId")
	public ResponseEntity<ResponseDTO> getAllIrnCreditByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllIrnCreditByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<IrnCreditVO> irnCreditVO = new ArrayList<>();
		try {
			irnCreditVO = transactionService.getAllIrnCreditByOrgId(orgId);
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
			irnCreditVO = transactionService.getAllIrnCreditById(id);
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
			IrnCreditVO irnCreditVO = transactionService.updateCreateIrnCredit(irnCreditDTO);
			if (irnCreditVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "IrnCredit updated successfully");
				responseObjectsMap.put("irnCreditVO", irnCreditVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "IrnCredit not found for ID: " + irnCreditDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "IrnCredit update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "IrnCredit update failed", errorMsg);
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
			irnCreditVO = transactionService.getIrnCreditByActive();
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

//	DailyMonthlyExRatesVO
	@GetMapping("/getAllDailyMonthlyExRatesByOrgId")
	public ResponseEntity<ResponseDTO> getAllDailyMonthlyExRatesByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllDailyMonthlyExRatesByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DailyMonthlyExRatesVO> dailyMonthlyExRatesVO = new ArrayList<>();
		try {
			dailyMonthlyExRatesVO = transactionService.getAllDailyMonthlyExRatesByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"DailyMonthlyExRates information get successfully ByOrgId");
			responseObjectsMap.put("dailyMonthlyExRatesVO", dailyMonthlyExRatesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DailyMonthlyExRates information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllDailyMonthlyExRatesById")
	public ResponseEntity<ResponseDTO> getAllDailyMonthlyExRatesById(@RequestParam(required = false) Long id) {
		String methodName = "getAllDailyMonthlyExRatesById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DailyMonthlyExRatesVO> dailyMonthlyExRatesVO = new ArrayList<>();
		try {
			dailyMonthlyExRatesVO = transactionService.getAllDailyMonthlyExRatesById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"DailyMonthlyExRates information get successfully By id");
			responseObjectsMap.put("dailyMonthlyExRatesVO", dailyMonthlyExRatesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DailyMonthlyExRates information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateDailyMonthlyExRates")
	public ResponseEntity<ResponseDTO> updateCreateDailyMonthlyExRates(
			@Valid @RequestBody DailyMonthlyExRatesDTO dailyMonthlyExRatesDTO) {
		String methodName = "updateCreateDailyMonthlyExRates()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			DailyMonthlyExRatesVO dailyMonthlyExRatesVO = transactionService
					.updateCreateDailyMonthlyExRates(dailyMonthlyExRatesDTO);
			if (dailyMonthlyExRatesVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "DailyMonthlyExRates updated successfully");
				responseObjectsMap.put("dailyMonthlyExRatesVO", dailyMonthlyExRatesVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "DailyMonthlyExRates not found for ID: " + dailyMonthlyExRatesDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "DailyMonthlyExRates update failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "DailyMonthlyExRates update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	BrsOpening
	@GetMapping("/getAllBrsOpeningByOrgId")
	public ResponseEntity<ResponseDTO> getAllBrsOpeningByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllBrsOpeningByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BrsOpeningVO> brsOpeningVO = new ArrayList<>();
		try {
			brsOpeningVO = transactionService.getAllBrsOpeningByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "BrsOpening information get successfully ByOrgId");
			responseObjectsMap.put("brsOpeningVO", brsOpeningVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "BrsOpening information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllBrsOpeningById")
	public ResponseEntity<ResponseDTO> getAllBrsOpeningById(@RequestParam(required = false) Long id) {
		String methodName = "getAllBrsOpeningById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BrsOpeningVO> brsOpeningVO = new ArrayList<>();
		try {
			brsOpeningVO = transactionService.getAllBrsOpeningById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "BrsOpening information get successfully By id");
			responseObjectsMap.put("brsOpeningVO", brsOpeningVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "BrsOpening information receive failedByOrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateBrsOpening")
	public ResponseEntity<ResponseDTO> updateCreateBrsOpening(@Valid @RequestBody BrsOpeningDTO brsOpeningDTO) {
		String methodName = "updateCreateBrsOpening()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			BrsOpeningVO brsOpeningVO = transactionService.updateCreateBrsOpening(brsOpeningDTO);
			if (brsOpeningVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "BrsOpening updated successfully");
				responseObjectsMap.put("brsOpeningVO", brsOpeningVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "BrsOpening not found for ID: " + brsOpeningDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "BrsOpening update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "BrsOpening update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getBrsOpeningByActive")
	public ResponseEntity<ResponseDTO> getBrsOpeningByActive() {
		String methodName = "getBrsOpeningByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BrsOpeningVO> brsOpeningVO = new ArrayList<>();
		try {
			brsOpeningVO = transactionService.getBrsOpeningByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "BrsOpening information get successfully By Active");
			responseObjectsMap.put("brsOpeningVO", brsOpeningVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"BrsOpening information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

//	ChartCostCenter
	@GetMapping("/getAllChartCostCenterByOrgId")
	public ResponseEntity<ResponseDTO> getAllChartCostCenterByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllChartCostCenterByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ChartCostCenterVO> chartCostCenterVO = new ArrayList<>();
		try {
			chartCostCenterVO = transactionService.getAllChartCostCenterByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ChartCostCenter information get successfully ByOrgId");
			responseObjectsMap.put("chartCostCenterVO", chartCostCenterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ChartCostCenter information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllChartCostCenterById")
	public ResponseEntity<ResponseDTO> getAllChartCostCenterById(@RequestParam(required = false) Long id) {
		String methodName = "getAllChartCostCenterById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ChartCostCenterVO> chartCostCenterVO = new ArrayList<>();
		try {
			chartCostCenterVO = transactionService.getAllChartCostCenterById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChartCostCenter information get successfully By id");
			responseObjectsMap.put("chartCostCenterVO", chartCostCenterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ChartCostCenter information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateChartCostCenter")
	public ResponseEntity<ResponseDTO> updateChartCostCenter(
			@Valid @RequestBody ChartCostCenterDTO chartCostCenterDTO) {
		String methodName = "updateCreateChartCostCenter()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ChartCostCenterVO chartCostCenterVO = transactionService.updateCreateChartCostCenter(chartCostCenterDTO);
			if (chartCostCenterVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChartCostCenter updated successfully");
				responseObjectsMap.put("chartCostCenterVO", chartCostCenterVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "ChartCostCenter not found for ID: " + chartCostCenterDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "ChartCostCenter update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "ChartCostCenter update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getChartCostCenterByActive")
	public ResponseEntity<ResponseDTO> getChartCostCenterByActive() {
		String methodName = "getChartCostCenterByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ChartCostCenterVO> chartCostCenterVO = new ArrayList<>();
		try {
			chartCostCenterVO = transactionService.getChartCostCenterByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ChartCostCenter information get successfully By Active");
			responseObjectsMap.put("chartCostCenterVO", chartCostCenterVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ChartCostCenter information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}
