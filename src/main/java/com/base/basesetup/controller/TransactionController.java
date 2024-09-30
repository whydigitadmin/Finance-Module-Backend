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
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.ArApAdjustmentOffSetDTO;
import com.base.basesetup.dto.ArapAdjustmentsDTO;
import com.base.basesetup.dto.ArapDetailsDTO;
import com.base.basesetup.dto.BrsOpeningDTO;
import com.base.basesetup.dto.ChartCostCenterDTO;
import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.dto.CustomerAttachmentType;
import com.base.basesetup.dto.DailyMonthlyExRatesDTO;
import com.base.basesetup.dto.DebitNoteDTO;
import com.base.basesetup.dto.FundTransferDTO;
import com.base.basesetup.dto.GeneralJournalDTO;
import com.base.basesetup.dto.GlOpeningBalanceDTO;
import com.base.basesetup.dto.GstSalesVoucherDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.PaymentReversalDTO;
import com.base.basesetup.dto.PaymentVoucherDTO;
import com.base.basesetup.dto.ReceiptReversalDTO;
import com.base.basesetup.dto.ReconcileDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.ArApAdjustmentOffSetVO;
import com.base.basesetup.entity.ArapAdjustmentsVO;
import com.base.basesetup.entity.ArapDetailsVO;
import com.base.basesetup.entity.BrsOpeningVO;
import com.base.basesetup.entity.ChartCostCenterVO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.DailyMonthlyExRatesVO;
import com.base.basesetup.entity.DebitNoteVO;
import com.base.basesetup.entity.FundTransferVO;
import com.base.basesetup.entity.GeneralJournalVO;
import com.base.basesetup.entity.GlOpeningBalanceVO;
import com.base.basesetup.entity.GstSalesVoucherVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.PaymentReversalVO;
import com.base.basesetup.entity.PaymentVoucherVO;
import com.base.basesetup.entity.ReceiptReversalVO;
import com.base.basesetup.entity.ReconcileVO;
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
			boolean isUpdate = taxInvoiceDTO.getId() != null;

			if (taxInvoiceVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "TaxInvoice updated successfully" : "TaxInvoice created successfully");
				responseObjectsMap.put("taxInvoiceVO", taxInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "TaxInvoice not found for ID: " + taxInvoiceDTO.getId()
						: "TaxInvoice creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "TaxInvoice update failed" : "TaxInvoice creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = taxInvoiceDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "TaxInvoice update failed" : "TaxInvoice creation failed", errorMsg);
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

//	DailyMonthlyExRates
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
			boolean isUpdate = dailyMonthlyExRatesDTO.getId() != null;

			if (dailyMonthlyExRatesVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "DailyMonthlyExRates updated successfully"
								: "DailyMonthlyExRates created successfully");
				responseObjectsMap.put("dailyMonthlyExRatesVO", dailyMonthlyExRatesVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "DailyMonthlyExRates not found for ID: " + dailyMonthlyExRatesDTO.getId()
						: "DailyMonthlyExRates creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "DailyMonthlyExRates update failed" : "DailyMonthlyExRates creation failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = dailyMonthlyExRatesDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "DailyMonthlyExRates update failed" : "DailyMonthlyExRates creation failed", errorMsg);
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
			boolean isUpdate = brsOpeningDTO.getId() != null;

			if (brsOpeningVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "BrsOpening updated successfully" : "BrsOpening created successfully");
				responseObjectsMap.put("brsOpeningVO", brsOpeningVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "BrsOpening not found for ID: " + brsOpeningDTO.getId()
						: "BrsOpening creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "BrsOpening update failed" : "BrsOpening creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = brsOpeningDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "BrsOpening update failed" : "BrsOpening creation failed", errorMsg);
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

	@GetMapping("/getBranchForBrsOpening")
	public ResponseEntity<ResponseDTO> getBranchForBrsOpening(@RequestParam(required = false) Long orgId) {

		String methodName = "getBranchForBrsOpening()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> branch = new ArrayList<>();

		try {
			branch = transactionService.getBranchForBrsOpening(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch retrieved successfully");
			responseObjectsMap.put("branch", branch);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Failed to retrieve Branch information",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	@PostMapping("/brsExcelUpload")
//	public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) throws Exception {
//		transactionService.saveBrsExcelData(file);
//		return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
//	}

	
	@PostMapping("/excelUploadForBrs")
	public ResponseEntity<ResponseDTO> ExcelUploadForBrs(@RequestParam MultipartFile[] files,
		 @RequestParam(required = false) Long orgId,
			@RequestParam(required = false) String createdBy, String customer, String client, String finYear, String branch, String branchCode) {
		String methodName = "ExcelUploadForBrs()";
		int totalRows = 0;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		int successfulUploads = 0;
		ResponseDTO responseDTO = null;
		try {
			// Call service method to process Excel upload
			transactionService.ExcelUploadForBrs(files, orgId, createdBy, customer,  client,  finYear,  branch,  branchCode);

			// Retrieve the counts after processing
			totalRows = transactionService.getTotalRows(); // Get total rows processed
			successfulUploads = transactionService.getSuccessfulUploads(); // Get successful uploads count
			// Construct success response
			responseObjectsMap.put("statusFlag", "Ok");
			responseObjectsMap.put("status", true);
			responseObjectsMap.put("totalRows", totalRows);
			responseObjectsMap.put("successfulUploads", successfulUploads);
			Map<String, Object> paramObjectsMap = new HashMap<>();
			paramObjectsMap.put("message", "Excel Upload For brs successful");
			responseObjectsMap.put("paramObjectsMap", paramObjectsMap);
			responseDTO = createServiceResponse(responseObjectsMap);

		} catch (Exception e) {

			String errorMsg = e.getMessage();
			LOGGER.error(CommonConstant.EXCEPTION, methodName, e);
			responseObjectsMap.put("statusFlag", "Error");
			responseObjectsMap.put("status", false);
			responseObjectsMap.put("errorMessage", errorMsg);

			responseDTO = createServiceResponseError(responseObjectsMap, "Excel Upload For Brs Failed", errorMsg);
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
			@RequestBody List<ChartCostCenterDTO> chartCostCenterDTOList) {
		String methodName = "updateCreateChartCostCenter()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			// Process the list (both update and create operations)
			List<ChartCostCenterVO> chartCostCenterVOList = transactionService
					.updateCreateChartCostCenter(chartCostCenterDTOList);

			if (!chartCostCenterVOList.isEmpty()) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ChartCostCenters processed successfully");
				responseObjectsMap.put("chartCostCenterVOList", chartCostCenterVOList);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				responseDTO = createServiceResponseError(responseObjectsMap, "Processing failed",
						"No records processed");
			}
		} catch (Exception e) {
			String errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Processing failed", errorMsg);
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

//	FundTransfer
	@GetMapping("/getAllFundTransferByOrgId")
	public ResponseEntity<ResponseDTO> getAllFundTransferByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllFundTransferByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FundTransferVO> fundTransferVO = new ArrayList<>();
		try {
			fundTransferVO = transactionService.getAllFundTransferByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FundTransfer information get successfully ByOrgId");
			responseObjectsMap.put("fundTransferVO", fundTransferVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"FundTransfer information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllFundTransferById")
	public ResponseEntity<ResponseDTO> getAllFundTransferById(@RequestParam(required = false) Long id) {
		String methodName = "getAllFundTransferById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FundTransferVO> fundTransferVO = new ArrayList<>();
		try {
			fundTransferVO = transactionService.getAllFundTransferById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FundTransfer information get successfully By id");
			responseObjectsMap.put("fundTransferVO", fundTransferVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"FundTransfer information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateFundTransfer")
	public ResponseEntity<ResponseDTO> updateCreateFundTransfer(@Valid @RequestBody FundTransferDTO fundTransferDTO) {
		String methodName = "updateCreateFundTransfer()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			FundTransferVO fundTransferVO = transactionService.updateCreateFundTransfer(fundTransferDTO);
			boolean isUpdate = fundTransferDTO.getId() != null;

			if (fundTransferVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "FundTransfer updated successfully" : "FundTransfer created successfully");
				responseObjectsMap.put("fundTransferVO", fundTransferVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "FundTransfer not found for ID: " + fundTransferDTO.getId()
						: "FundTransfer creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "FundTransfer update failed" : "FundTransfer creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = fundTransferDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "FundTransfer update failed" : "FundTransfer creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getFundTransferByActive")
	public ResponseEntity<ResponseDTO> getFundTransferByActive() {
		String methodName = "getFundTransferByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FundTransferVO> fundTransferVO = new ArrayList<>();
		try {
			fundTransferVO = transactionService.getFundTransferByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"FundTransfer information get successfully By Active");
			responseObjectsMap.put("fundTransferVO", fundTransferVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"FundTransfer information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	GeneralJournal
	@GetMapping("/getAllGeneralJournalByOrgId")
	public ResponseEntity<ResponseDTO> getAllGeneralJournalByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllGeneralJournalByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GeneralJournalVO> generalJournalVO = new ArrayList<>();
		try {
			generalJournalVO = transactionService.getAllGeneralJournalByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GeneralJournal information get successfully ByOrgId");
			responseObjectsMap.put("generalJournalVO", generalJournalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GeneralJournal information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllGeneralJournalById")
	public ResponseEntity<ResponseDTO> getAllGeneralJournalById(@RequestParam(required = false) Long id) {
		String methodName = "getAllGeneralJournalById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GeneralJournalVO> generalJournalVO = new ArrayList<>();
		try {
			generalJournalVO = transactionService.getAllGeneralJournalById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "GeneralJournal information get successfully By id");
			responseObjectsMap.put("generalJournalVO", generalJournalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GeneralJournal information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateGeneralJournal")
	public ResponseEntity<ResponseDTO> updateCreateGeneralJournal(
			@Valid @RequestBody GeneralJournalDTO generalJournalDTO) {
		String methodName = "updateCreateGeneralJournal()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			GeneralJournalVO generalJournalVO = transactionService.updateCreateGeneralJournal(generalJournalDTO);
			boolean isUpdate = generalJournalDTO.getId() != null;

			if (generalJournalVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "GeneralJournal updated successfully" : "GeneralJournal created successfully");
				responseObjectsMap.put("generalJournalVO", generalJournalVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "GeneralJournal not found for ID: " + generalJournalDTO.getId()
						: "GeneralJournal creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "GeneralJournal update failed" : "GeneralJournal creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = generalJournalDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "GeneralJournal update failed" : "GeneralJournal creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getGeneralJournalByActive")
	public ResponseEntity<ResponseDTO> getGeneralJournalByActive() {
		String methodName = "getGeneralJournalByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GeneralJournalVO> generalJournalVO = new ArrayList<>();
		try {
			generalJournalVO = transactionService.getGeneralJournalByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GeneralJournal information get successfully By Active");
			responseObjectsMap.put("generalJournalVO", generalJournalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GeneralJournal information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

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
			costInvoiceVO = transactionService.getAllCostInvoiceByOrgId(orgId);
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
			costInvoiceVO = transactionService.getAllCostInvoiceById(id);
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
			CostInvoiceVO costInvoiceVO = transactionService.updateCreateCostInvoice(costInvoiceDTO);
			boolean isUpdate = costInvoiceDTO.getId() != null;

			if (costInvoiceVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "CostInvoice updated successfully" : "CostInvoice created successfully");
				responseObjectsMap.put("costInvoiceVO", costInvoiceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "CostInvoice not found for ID: " + costInvoiceDTO.getId()
						: "CostInvoice creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "CostInvoice update failed" : "CostInvoice creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = costInvoiceDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "CostInvoice update failed" : "CostInvoice creation failed", errorMsg);
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
			costInvoiceVO = transactionService.getCostInvoiceByActive();
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

	// DebitNote

	@GetMapping("/getAllDebitNoteByOrgId")
	public ResponseEntity<ResponseDTO> getAllDebitNoteByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllDebitNoteByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DebitNoteVO> debitNoteVO = new ArrayList<>();
		try {
			debitNoteVO = transactionService.getAllDebitNoteByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "DebitNote information get successfully By OrgId");
			responseObjectsMap.put("debitNoteVO", debitNoteVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DebitNote information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllDebitNoteById")
	public ResponseEntity<ResponseDTO> getAllDebitNoteById(@RequestParam(required = false) Long id) {
		String methodName = "getAllDebitNoteById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DebitNoteVO> debitNoteVO = new ArrayList<>();
		try {
			debitNoteVO = transactionService.getAllDebitNoteById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "DebitNote information get successfully By id");
			responseObjectsMap.put("debitNoteVO", debitNoteVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DebitNote information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateDebitNote")
	public ResponseEntity<ResponseDTO> updateCreateDebitNote(@Valid @RequestBody DebitNoteDTO debitNoteDTO) {
		String methodName = "updateCreateDebitNote()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			DebitNoteVO debitNoteVO = transactionService.updateCreateDebitNote(debitNoteDTO);
			boolean isUpdate = debitNoteDTO.getId() != null;

			if (debitNoteVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "DebitNote updated successfully" : "DebitNote created successfully");
				responseObjectsMap.put("debitNoteVO", debitNoteVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "DebitNote not found for ID: " + debitNoteDTO.getId()
						: "DebitNote creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "DebitNote update failed" : "DebitNote creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = debitNoteDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "DebitNote update failed" : "DebitNote creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getDebitNoteByActive")
	public ResponseEntity<ResponseDTO> getDebitNoteByActive() {
		String methodName = "getDebitNoteByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DebitNoteVO> debitNoteVO = new ArrayList<>();
		try {
			debitNoteVO = transactionService.getDebitNoteByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "DebitNote information get successfully By Active");
			responseObjectsMap.put("debitNoteVO", debitNoteVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DebitNote information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// GstSalesVoucher

	@GetMapping("/getAllGstSalesVoucherByOrgId")
	public ResponseEntity<ResponseDTO> getAllGstSalesVoucherByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllGstSalesVoucherByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GstSalesVoucherVO> gstSalesVoucherVO = new ArrayList<>();
		try {
			gstSalesVoucherVO = transactionService.getAllGstSalesVoucherByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GstSalesVoucher information get successfully By OrgId");
			responseObjectsMap.put("gstSalesVoucherVO", gstSalesVoucherVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GstSalesVoucher information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllGstSalesVoucherById")
	public ResponseEntity<ResponseDTO> getAllGstSalesVoucherById(@RequestParam(required = false) Long id) {
		String methodName = "getAllGstSalesVoucherById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GstSalesVoucherVO> gstSalesVoucherVO = new ArrayList<>();
		try {
			gstSalesVoucherVO = transactionService.getAllGstSalesVoucherById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "GstSalesVoucher information get successfully By id");
			responseObjectsMap.put("gstSalesVoucherVO", gstSalesVoucherVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GstSalesVoucher information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateGstSalesVoucher")
	public ResponseEntity<ResponseDTO> updateCreateGstSalesVoucher(
			@Valid @RequestBody GstSalesVoucherDTO gstSalesVoucherDTO) {
		String methodName = "updateCreateGstSalesVoucher()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			GstSalesVoucherVO gstSalesVoucherVO = transactionService.updateCreateGstSalesVoucher(gstSalesVoucherDTO);
			boolean isUpdate = gstSalesVoucherDTO.getId() != null;

			if (gstSalesVoucherVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "GstSalesVoucher updated successfully" : "GstSalesVoucher created successfully");
				responseObjectsMap.put("gstSalesVoucherVO", gstSalesVoucherVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "GstSalesVoucher not found for ID: " + gstSalesVoucherDTO.getId()
						: "GstSalesVoucher creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "GstSalesVoucher update failed" : "GstSalesVoucher creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = gstSalesVoucherDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "GstSalesVoucher update failed" : "GstSalesVoucher creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getGstSalesVoucherByActive")
	public ResponseEntity<ResponseDTO> getGstSalesVoucherByActive() {
		String methodName = "getGstSalesVoucherByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GstSalesVoucherVO> gstSalesVoucherVO = new ArrayList<>();
		try {
			gstSalesVoucherVO = transactionService.getGstSalesVoucherByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GstSalesVoucher information get successfully By Active");
			responseObjectsMap.put("gstSalesVoucherVO", gstSalesVoucherVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GstSalesVoucher information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// PaymentVoucher

	@GetMapping("/getAllPaymentVoucherByOrgId")
	public ResponseEntity<ResponseDTO> getAllPaymentVoucherByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllPaymentVoucherByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentVoucherVO> paymentVoucherVO = new ArrayList<>();
		try {
			paymentVoucherVO = transactionService.getAllPaymentVoucherByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"PaymentVoucher information get successfully By OrgId");
			responseObjectsMap.put("paymentVoucherVO", paymentVoucherVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"PaymentVoucher information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllPaymentVoucherById")
	public ResponseEntity<ResponseDTO> getAllPaymentVoucherById(@RequestParam(required = false) Long id) {
		String methodName = "getAllPaymentVoucherById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentVoucherVO> paymentVoucherVO = new ArrayList<>();
		try {
			paymentVoucherVO = transactionService.getAllPaymentVoucherById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "PaymentVoucher information get successfully By id");
			responseObjectsMap.put("paymentVoucherVO", paymentVoucherVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"PaymentVoucher information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreatePaymentVoucher")
	public ResponseEntity<ResponseDTO> updateCreatePaymentVoucher(
			@Valid @RequestBody PaymentVoucherDTO paymentVoucherDTO) {
		String methodName = "updateCreatePaymentVoucher()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			PaymentVoucherVO paymentVoucherVO = transactionService.updateCreatePaymentVoucher(paymentVoucherDTO);
			boolean isUpdate = paymentVoucherDTO.getId() != null;

			if (paymentVoucherVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "PaymentVoucher updated successfully" : "PaymentVoucher created successfully");
				responseObjectsMap.put("paymentVoucherVO", paymentVoucherVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "PaymentVoucher not found for ID: " + paymentVoucherDTO.getId()
						: "PaymentVoucher creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "PaymentVoucher update failed" : "PaymentVoucher creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = paymentVoucherDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "PaymentVoucher update failed" : "PaymentVoucher creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getPaymentVoucherByActive")
	public ResponseEntity<ResponseDTO> getPaymentVoucherByActive() {
		String methodName = "getPaymentVoucherByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentVoucherVO> paymentVoucherVO = new ArrayList<>();
		try {
			paymentVoucherVO = transactionService.getPaymentVoucherByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"PaymentVoucher information get successfully By Active");
			responseObjectsMap.put("paymentVoucherVO", paymentVoucherVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"PaymentVoucher information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

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
			arapDetailsVO = transactionService.getAllArapDetailsByOrgId(orgId);
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
			arapDetailsVO = transactionService.getAllArapDetailsById(id);
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

	@PutMapping("/updateCreateArapDetails")
	public ResponseEntity<ResponseDTO> updateCreateArapDetails(@Valid @RequestBody ArapDetailsDTO arapDetailsDTO) {
		String methodName = "updateCreateArapDetails()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ArapDetailsVO arapDetailsVO = transactionService.updateCreateArapDetails(arapDetailsDTO);
			boolean isUpdate = arapDetailsDTO.getId() != null;

			if (arapDetailsVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "ArapDetails updated successfully" : "ArapDetails created successfully");
				responseObjectsMap.put("arapDetailsVO", arapDetailsVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "ArapDetails not found for ID: " + arapDetailsDTO.getId()
						: "ArapDetails creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "ArapDetails update failed" : "ArapDetails creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = arapDetailsDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "ArapDetails update failed" : "ArapDetails creation failed", errorMsg);
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
			arapDetailsVO = transactionService.getArapDetailsByActive();
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

	// ArapAdjustments

	@GetMapping("/getAllArapAdjustmentsByOrgId")
	public ResponseEntity<ResponseDTO> getAllArapAdjustmentsByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllArapAdjustmentsByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ArapAdjustmentsVO> arapAdjustmentsVO = new ArrayList<>();
		try {
			arapAdjustmentsVO = transactionService.getAllArapAdjustmentsByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ArapAdjustments information get successfully By OrgId");
			responseObjectsMap.put("arapAdjustmentsVO", arapAdjustmentsVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ArapAdjustments information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllArapAdjustmentsById")
	public ResponseEntity<ResponseDTO> getAllArapAdjustmentsById(@RequestParam(required = false) Long id) {
		String methodName = "getAllArapAdjustmentsById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ArapAdjustmentsVO> arapAdjustmentsVO = new ArrayList<>();
		try {
			arapAdjustmentsVO = transactionService.getAllArapAdjustmentsById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ArapAdjustments information get successfully By id");
			responseObjectsMap.put("arapAdjustmentsVO", arapAdjustmentsVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ArapAdjustments information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateArapAdjustments")
	public ResponseEntity<ResponseDTO> updateCreateArapAdjustments(
			@Valid @RequestBody ArapAdjustmentsDTO arapAdjustmentsDTO) {
		String methodName = "updateCreateArapAdjustments()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ArapAdjustmentsVO arapAdjustmentsVO = transactionService.updateCreateArapAdjustments(arapAdjustmentsDTO);
			boolean isUpdate = arapAdjustmentsDTO.getId() != null;

			if (arapAdjustmentsVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "ArapAdjustments updated successfully" : "ArapAdjustments created successfully");
				responseObjectsMap.put("arapAdjustmentsVO", arapAdjustmentsVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "ArapAdjustments not found for ID: " + arapAdjustmentsDTO.getId()
						: "ArapAdjustments creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "ArapAdjustments update failed" : "ArapAdjustments creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = arapAdjustmentsDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "ArapAdjustments update failed" : "ArapAdjustments creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getArapAdjustmentsByActive")
	public ResponseEntity<ResponseDTO> getArapAdjustmentsByActive() {
		String methodName = "getArapAdjustmentsByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ArapAdjustmentsVO> arapAdjustmentsVO = new ArrayList<>();
		try {
			arapAdjustmentsVO = transactionService.getArapAdjustmentsByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ArapAdjustments information get successfully By Active");
			responseObjectsMap.put("arapAdjustmentsVO", arapAdjustmentsVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ArapAdjustments information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// ReceiptReversal

	@GetMapping("/getAllReceiptReversalByOrgId")
	public ResponseEntity<ResponseDTO> getAllReceiptReversalByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllReceiptReversalByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReceiptReversalVO> receiptReversalVO = new ArrayList<>();
		try {
			receiptReversalVO = transactionService.getAllReceiptReversalByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ReceiptReversal information get successfully By OrgId");
			responseObjectsMap.put("receiptReversalVO", receiptReversalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReceiptReversal information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllReceiptReversalById")
	public ResponseEntity<ResponseDTO> getAllReceiptReversalById(@RequestParam(required = false) Long id) {
		String methodName = "getAllReceiptReversalById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReceiptReversalVO> receiptReversalVO = new ArrayList<>();
		try {
			receiptReversalVO = transactionService.getAllReceiptReversalById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "ReceiptReversal information get successfully By id");
			responseObjectsMap.put("receiptReversalVO", receiptReversalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReceiptReversal information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateReceiptReversal")
	public ResponseEntity<ResponseDTO> updateCreateReceiptReversal(
			@Valid @RequestBody ReceiptReversalDTO receiptReversalDTO) {
		String methodName = "updateCreateReceiptReversal()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ReceiptReversalVO receiptReversalVO = transactionService.updateCreateReceiptReversal(receiptReversalDTO);
			boolean isUpdate = receiptReversalDTO.getId() != null;

			if (receiptReversalVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "ReceiptReversal updated successfully" : "ReceiptReversal created successfully");
				responseObjectsMap.put("receiptReversalVO", receiptReversalVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "ReceiptReversal not found for ID: " + receiptReversalDTO.getId()
						: "ReceiptReversal creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "ReceiptReversal update failed" : "ReceiptReversal creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = receiptReversalDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "ReceiptReversal update failed" : "ReceiptReversal creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getReceiptReversalByActive")
	public ResponseEntity<ResponseDTO> getReceiptReversalByActive() {
		String methodName = "getReceiptReversalByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReceiptReversalVO> receiptReversalVO = new ArrayList<>();
		try {
			receiptReversalVO = transactionService.getReceiptReversalByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ReceiptReversal information get successfully By Active");
			responseObjectsMap.put("receiptReversalVO", receiptReversalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReceiptReversal information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// PaymentReversal

	@GetMapping("/getAllPaymentReversalByOrgId")
	public ResponseEntity<ResponseDTO> getAllPaymentReversalByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllPaymentReversalByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentReversalVO> paymentReversalVO = new ArrayList<>();
		try {
			paymentReversalVO = transactionService.getAllPaymentReversalByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"PaymentReversal information get successfully By OrgId");
			responseObjectsMap.put("PaymentReversalVO", paymentReversalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"PaymentReversal information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllPaymentReversalById")
	public ResponseEntity<ResponseDTO> getAllPaymentReversalById(@RequestParam(required = false) Long id) {
		String methodName = "getAllPaymentReversalById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentReversalVO> paymentReversalVO = new ArrayList<>();
		try {
			paymentReversalVO = transactionService.getAllPaymentReversalById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "PaymentReversal information get successfully By id");
			responseObjectsMap.put("paymentReversalVO", paymentReversalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"PaymentReversal information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreatePaymentReversal")
	public ResponseEntity<ResponseDTO> updateCreatePaymentReversal(
			@Valid @RequestBody PaymentReversalDTO paymentReversalDTO) {
		String methodName = "updateCreatePaymentReversal()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			PaymentReversalVO paymentReversalVO = transactionService.updateCreatePaymentReversal(paymentReversalDTO);
			boolean isUpdate = paymentReversalDTO.getId() != null;

			if (paymentReversalVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "PaymentReversal updated successfully" : "PaymentReversal created successfully");
				responseObjectsMap.put("paymentReversalVO", paymentReversalVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "PaymentReversal not found for ID: " + paymentReversalDTO.getId()
						: "PaymentReversal creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "PaymentReversal update failed" : "PaymentReversal creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = paymentReversalDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "PaymentReversal update failed" : "PaymentReversal creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getPaymentReversalByActive")
	public ResponseEntity<ResponseDTO> getPaymentReversalByActive() {
		String methodName = "getPaymentReversalByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<PaymentReversalVO> paymentReversalVO = new ArrayList<>();
		try {
			paymentReversalVO = transactionService.getPaymentReversalByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"PaymentReversal information get successfully By Active");
			responseObjectsMap.put("paymentReversalVO", paymentReversalVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"PaymentReversal information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// ArApAdjustmentOffSet

	@GetMapping("/getAllArApAdjustmentOffSetByOrgId")
	public ResponseEntity<ResponseDTO> getAllArApAdjustmentOffSetByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllArApAdjustmentOffSetByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ArApAdjustmentOffSetVO> arApAdjustmentOffSetVO = new ArrayList<>();
		try {
			arApAdjustmentOffSetVO = transactionService.getAllArApAdjustmentOffSetByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ArApAdjustmentOffSet information get successfully By OrgId");
			responseObjectsMap.put("ArApAdjustmentOffSetVO", arApAdjustmentOffSetVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ArApAdjustmentOffSet information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllArApAdjustmentOffSetById")
	public ResponseEntity<ResponseDTO> getAllArApAdjustmentOffSetById(@RequestParam(required = false) Long id) {
		String methodName = "getAllArApAdjustmentOffSetById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ArApAdjustmentOffSetVO> arApAdjustmentOffSetVO = new ArrayList<>();
		try {
			arApAdjustmentOffSetVO = transactionService.getAllArApAdjustmentOffSetById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ArApAdjustmentOffSet information get successfully By id");
			responseObjectsMap.put("arApAdjustmentOffSetVO", arApAdjustmentOffSetVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ArApAdjustmentOffSet information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateArApAdjustmentOffSet")
	public ResponseEntity<ResponseDTO> updateCreateArApAdjustmentOffSet(
			@Valid @RequestBody ArApAdjustmentOffSetDTO arApAdjustmentOffSetDTO) {
		String methodName = "updateCreateArApAdjustmentOffSet()";

		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ArApAdjustmentOffSetVO arApAdjustmentOffSetVO = transactionService
					.updateCreateArApAdjustmentOffSet(arApAdjustmentOffSetDTO);
			boolean isUpdate = arApAdjustmentOffSetDTO.getId() != null;

			if (arApAdjustmentOffSetVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "ArApAdjustmentOffSet updated successfully"
								: "ArApAdjustmentOffSet created successfully");
				responseObjectsMap.put("arApAdjustmentOffSetVO", arApAdjustmentOffSetVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "ArApAdjustmentOffSet not found for ID: " + arApAdjustmentOffSetDTO.getId()
						: "ArApAdjustmentOffSet creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "ArApAdjustmentOffSet update failed" : "ArApAdjustmentOffSet creation failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = arApAdjustmentOffSetDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "ArApAdjustmentOffSet update failed" : "ArApAdjustmentOffSet creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getArApAdjustmentOffSetByActive")
	public ResponseEntity<ResponseDTO> getArApAdjustmentOffSetByActive() {
		String methodName = "getArApAdjustmentOffSetByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ArApAdjustmentOffSetVO> arApAdjustmentOffSetVO = new ArrayList<>();
		try {
			arApAdjustmentOffSetVO = transactionService.getArApAdjustmentOffSetByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"ArApAdjustmentOffSet information get successfully By Active");
			responseObjectsMap.put("arApAdjustmentOffSetVO", arApAdjustmentOffSetVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ArApAdjustmentOffSet information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// GlOpeningBalance

	@GetMapping("/getAllGlOpeningBalanceByOrgId")
	public ResponseEntity<ResponseDTO> getAllGlOpeningBalanceByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllGlOpeningBalanceByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GlOpeningBalanceVO> glOpeningBalanceVO = new ArrayList<>();
		try {
			glOpeningBalanceVO = transactionService.getAllGlOpeningBalanceByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GlOpeningBalance information get successfully By OrgId");
			responseObjectsMap.put("GlOpeningBalanceVO", glOpeningBalanceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GlOpeningBalance information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllGlOpeningBalanceById")
	public ResponseEntity<ResponseDTO> getAllGlOpeningBalanceById(@RequestParam(required = false) Long id) {
		String methodName = "getAllGlOpeningBalanceById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GlOpeningBalanceVO> glOpeningBalanceVO = new ArrayList<>();
		try {
			glOpeningBalanceVO = transactionService.getAllGlOpeningBalanceById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GlOpeningBalance information get successfully By id");
			responseObjectsMap.put("glOpeningBalanceVO", glOpeningBalanceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GlOpeningBalance information receive failedByOrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateGlOpeningBalance")
	public ResponseEntity<ResponseDTO> updateCreateGlOpeningBalance(
			@Valid @RequestBody GlOpeningBalanceDTO glOpeningBalanceDTO) {
		String methodName = "updateCreateGlOpeningBalance()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			GlOpeningBalanceVO glOpeningBalanceVO = transactionService
					.updateCreateGlOpeningBalance(glOpeningBalanceDTO);
			boolean isUpdate = glOpeningBalanceDTO.getId() != null;

			if (glOpeningBalanceVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "GlOpeningBalance updated successfully" : "GlOpeningBalance created successfully");
				responseObjectsMap.put("glOpeningBalanceVO", glOpeningBalanceVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "GlOpeningBalance not found for ID: " + glOpeningBalanceDTO.getId()
						: "GlOpeningBalance creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "GlOpeningBalance update failed" : "GlOpeningBalance creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = glOpeningBalanceDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "GlOpeningBalance update failed" : "GlOpeningBalance creation failed", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getGlOpeningBalanceByActive")
	public ResponseEntity<ResponseDTO> getGlOpeningBalanceByActive() {
		String methodName = "getGlOpeningBalanceByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<GlOpeningBalanceVO> glOpeningBalanceVO = new ArrayList<>();
		try {
			glOpeningBalanceVO = transactionService.getGlOpeningBalanceByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"GlOpeningBalance information get successfully By Active");
			responseObjectsMap.put("glOpeningBalanceVO", glOpeningBalanceVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"GlOpeningBalance information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	   
	//Reconcile
	
	@GetMapping("/getAllReconcileByOrgId")
	public ResponseEntity<ResponseDTO> getAllReconcileByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getAllReconcileByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReconcileVO> reconcileVO = new ArrayList<>();
		try {
			reconcileVO = transactionService.getAllReconcileByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Reconcile information get successfully By OrgId");
			responseObjectsMap.put("reconcileVO", reconcileVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Reconcile information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}
	
	@GetMapping("/getAllReconcileById")
	public ResponseEntity<ResponseDTO> getAllReconcileById(@RequestParam(required = false) Long id) {
		String methodName = "getAllCostInvoiceById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReconcileVO> reconcileVO = new ArrayList<>();
		try {
			reconcileVO = transactionService.getAllReconcileById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Reconcile information get successfully By id");
			responseObjectsMap.put("reconcileVO", reconcileVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Reconcile information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	
	
	@PutMapping("/updateCreateReconcile")
	public ResponseEntity<ResponseDTO> updateCreateReconcile(@Valid @RequestBody ReconcileDTO reconcileDTO) {
		String methodName = "updateCreateReconcile()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
			ReconcileVO reconcileVO = transactionService.updateCreateReconcile(reconcileDTO);
			boolean isUpdate = reconcileDTO.getId() != null;

			if (reconcileVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Reconcile updated successfully" : "Reconcile created successfully");
				responseObjectsMap.put("reconcileVO", reconcileVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = isUpdate ? "Reconcile not found for ID: " + reconcileDTO.getId()
						: "Reconcile creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Reconcile update failed" : "Reconcile creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = reconcileDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Reconcile update failed" : "Reconcile creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getReconcileByActive")
	public ResponseEntity<ResponseDTO> getReconcileByActive() {
		String methodName = "getReconcileByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ReconcileVO> reconcileVO = new ArrayList<>();
		try {
			reconcileVO = transactionService.getReconcileByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"Reconcile information get successfully By Active");
			responseObjectsMap.put("reconcileVO", reconcileVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"ReconcileVO information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}
	
}