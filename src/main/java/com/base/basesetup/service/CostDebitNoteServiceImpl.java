package com.base.basesetup.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CostDebitChargesDTO;
import com.base.basesetup.dto.CostDebitNoteDTO;
import com.base.basesetup.dto.CostDebitNoteTaxPrtculDTO;
import com.base.basesetup.entity.CostDebitChargesVO;
import com.base.basesetup.entity.CostDebitNoteGstVO;
import com.base.basesetup.entity.CostDebitNoteTaxPrtculVO;
import com.base.basesetup.entity.CostDebitNoteVO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.DocumentTypeMappingDetailsVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.CostDebitChargesRepo;
import com.base.basesetup.repo.CostDebitNoteGstRepo;
import com.base.basesetup.repo.CostDebitNoteRepo;
import com.base.basesetup.repo.CostDebitNoteTaxPrtculRepo;
import com.base.basesetup.repo.CostInvoiceRepo;
import com.base.basesetup.repo.DocumentTypeMappingDetailsRepo;

@Service
public class CostDebitNoteServiceImpl implements CostDebitNoteService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CostDebitNoteServiceImpl.class);

	@Autowired
	CostDebitNoteRepo costDebitNoteRepo;

	@Autowired
	CostDebitChargesRepo costDebitChargesRepo;

	@Autowired
	CostDebitNoteGstRepo costDebitNoteGstRepo;

	@Autowired
	CostDebitNoteTaxPrtculRepo costDebitNoteTaxPrtculRepo;

	@Autowired
	DocumentTypeMappingDetailsRepo documentTypeMappingDetailsRepo;

	@Autowired
	CostInvoiceRepo costInvoiceRepo;

	@Override
	public Map<String, Object> updateCreateCostDebitNote(@Valid CostDebitNoteDTO costDebitNoteDTO)
			throws ApplicationException {

		String message = null;
		String screenCode = "CDN";
		CostDebitNoteVO costDebitNoteVO;

		if (ObjectUtils.isEmpty(costDebitNoteDTO.getId())) {
			costDebitNoteVO = new CostDebitNoteVO();

			// GETDOCID API
			String docId = costDebitNoteRepo.getCostDebitNoteDocId(costDebitNoteDTO.getOrgId(),
					costDebitNoteDTO.getFinYear(), costDebitNoteDTO.getBranchCode(), screenCode);
			costDebitNoteVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(costDebitNoteDTO.getOrgId(),
							costDebitNoteDTO.getFinYear(), costDebitNoteDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			costDebitNoteVO.setCreatedBy(costDebitNoteDTO.getCreatedBy());
			costDebitNoteVO.setModifyBy(costDebitNoteDTO.getCreatedBy());

			message = "CostDebitNote Creation Successfully";

		} else {
			costDebitNoteVO = costDebitNoteRepo.findById(costDebitNoteDTO.getId()).orElseThrow(
					() -> new ApplicationException("Cost DebitNote Not Found with id: " + costDebitNoteDTO.getId()));
			costDebitNoteVO.setModifyBy(costDebitNoteDTO.getCreatedBy());

			message = "CostDebitNote Updation Successfully";
		}

		costDebitNoteVO = getCostDebitNoteVOFromCostDebitNoteDTO(costDebitNoteVO, costDebitNoteDTO);
		costDebitNoteRepo.save(costDebitNoteVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("costDebitNoteVO", costDebitNoteVO);
		return response;
	}

	private CostDebitNoteVO getCostDebitNoteVOFromCostDebitNoteDTO(CostDebitNoteVO costDebitNoteVO,
			@Valid CostDebitNoteDTO costDebitNoteDTO) {

		// Set individual fields from DTO to VO
		costDebitNoteVO.setBranch(costDebitNoteDTO.getBranch());
		costDebitNoteVO.setBranchCode(costDebitNoteDTO.getBranchCode());
		costDebitNoteVO.setCreatedBy(costDebitNoteDTO.getCreatedBy());
		costDebitNoteVO.setCancelRemarks(costDebitNoteDTO.getCancelRemarks());
		costDebitNoteVO.setFinYear(costDebitNoteDTO.getFinYear());
		costDebitNoteVO.setTaxExampt(costDebitNoteDTO.isTaxExampt());
		costDebitNoteVO.setOrgId(costDebitNoteDTO.getOrgId());
		costDebitNoteVO.setSubType(costDebitNoteDTO.getSubType());
		costDebitNoteVO.setProduct(costDebitNoteDTO.getProduct());
		costDebitNoteVO.setVohNo(costDebitNoteDTO.getVohNo());
		costDebitNoteVO.setVohDate(costDebitNoteDTO.getVohDate());
		costDebitNoteVO.setPartyType(costDebitNoteDTO.getPartyType());
		costDebitNoteVO.setSuppRefNo(costDebitNoteDTO.getSuppRefNo());
		costDebitNoteVO.setSuppDate(costDebitNoteDTO.getSuppDate());
		costDebitNoteVO.setPartyName(costDebitNoteDTO.getPartyName());
		costDebitNoteVO.setPartyCode(costDebitNoteDTO.getPartyCode());
		costDebitNoteVO.setCreditDays(costDebitNoteDTO.getCreditDays());
		costDebitNoteVO.setDueDate(costDebitNoteDTO.getDueDate());
		costDebitNoteVO.setAddress(costDebitNoteDTO.getAddress());
		costDebitNoteVO.setCurrency(costDebitNoteDTO.getCurrency());
		costDebitNoteVO.setExRate(costDebitNoteDTO.getExRate());
		costDebitNoteVO.setOtherInfo(costDebitNoteDTO.getOtherInfo());
		costDebitNoteVO.setRemarks(costDebitNoteDTO.getRemarks());
		costDebitNoteVO.setShipRefNo(costDebitNoteDTO.getShipRefNo());
		costDebitNoteVO.setStatus(costDebitNoteDTO.getStatus());
		costDebitNoteVO.setOrginBill(costDebitNoteDTO.getOrginBill());
		costDebitNoteVO.setGstType(costDebitNoteDTO.getGstType());
		costDebitNoteVO.setCurrentDate(costDebitNoteDTO.getCurrentDate());
		costDebitNoteVO.setCurrentDateValue(costDebitNoteDTO.getCurrentDateValue());

		// Deleting existing entries if updating
		if (costDebitNoteDTO.getId() != null) {
			List<CostDebitNoteGstVO> costDebitNoteGstVOs = costDebitNoteGstRepo.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteGstRepo.deleteAll(costDebitNoteGstVOs);

			List<CostDebitNoteTaxPrtculVO> costDebitNoteTaxPrtculVOs = costDebitNoteTaxPrtculRepo
					.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteTaxPrtculRepo.deleteAll(costDebitNoteTaxPrtculVOs);

			List<CostDebitChargesVO> costDebitChargesVOs = costDebitChargesRepo.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitChargesRepo.deleteAll(costDebitChargesVOs);
		}

		BigDecimal sumBillAmount = BigDecimal.ZERO;
		BigDecimal sumLcAmount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;
		BigDecimal tdsAmount = BigDecimal.ZERO;

		List<CostDebitChargesVO> chargerCostDebitVOs = new ArrayList<>();

		// Map to store IGST sums by GST percentage
		Map<String, BigDecimal> igstCategorySumMap = new HashMap<>();
		Map<String, BigDecimal> cgstCategorySumMap = new HashMap<>();

		for (CostDebitChargesDTO chargerCostDebitDTO : costDebitNoteDTO.getCostDebitChargesDTO()) {
			CostDebitChargesVO chargerCostDebitVO = new CostDebitChargesVO();

			chargerCostDebitVO.setRate(chargerCostDebitDTO.getRate());
			chargerCostDebitVO.setJobNo(chargerCostDebitDTO.getJobNo());
			chargerCostDebitVO.setChargeName(chargerCostDebitDTO.getChargeName());
			chargerCostDebitVO.setChargeCode(chargerCostDebitDTO.getChargeCode());
			chargerCostDebitVO.setChargeLedger(chargerCostDebitDTO.getChargeLedger());
			chargerCostDebitVO.setLedger(chargerCostDebitDTO.getLedger());
			chargerCostDebitVO.setSac(chargerCostDebitDTO.getSac());
			chargerCostDebitVO.setCurrency(chargerCostDebitDTO.getCurrency());
			chargerCostDebitVO.setExRate(chargerCostDebitDTO.getExRate());
			chargerCostDebitVO.setGst(chargerCostDebitDTO.getGst());
			chargerCostDebitVO.setGovChargeCode(chargerCostDebitDTO.getGovChargeCode());
			chargerCostDebitVO.setExempted(chargerCostDebitDTO.getExempted());
			chargerCostDebitVO.setTaxable(chargerCostDebitDTO.getTaxable());
			chargerCostDebitVO.setGSTPercent(chargerCostDebitDTO.getGstPercent());

//			FIELD DECLARATION
			BigDecimal fcAmount = BigDecimal.ZERO;
			BigDecimal lcAmount;
			BigDecimal billAmount;
			BigDecimal gstAmount = BigDecimal.ZERO;

//			TO CHECK THE CURRENCY 
			if (!chargerCostDebitDTO.getCurrency().equals("INR")) {
				BigDecimal rate = chargerCostDebitDTO.getRate(); // BigDecimal type is expected here
				BigDecimal qty = BigDecimal.valueOf(chargerCostDebitDTO.getQty()); // Convert qty to BigDecimal
				fcAmount = rate.multiply(qty);
				chargerCostDebitVO.setFcAmt(fcAmount);
			} else {
				fcAmount = BigDecimal.valueOf(0.00);
				chargerCostDebitVO.setFcAmt(fcAmount);
			}

//			FIELD DECLARATION
			BigDecimal exRate = chargerCostDebitDTO.getExRate();
			BigDecimal qty = BigDecimal.valueOf(chargerCostDebitDTO.getQty());
			BigDecimal rate = chargerCostDebitDTO.getRate();
			BigDecimal gstPercent = BigDecimal.valueOf(chargerCostDebitDTO.getGstPercent());

//			LC AMOUNT CALCULATION
			lcAmount = exRate.multiply(qty.multiply(rate));
			chargerCostDebitVO.setLcAmt(lcAmount);
			sumLcAmount = sumLcAmount.add(lcAmount); // TDS Purpose

//			BILL AMOUNT CALCULATION
			billAmount = lcAmount.divide(exRate, RoundingMode.HALF_UP);
			chargerCostDebitVO.setBillAmt(billAmount);
			sumBillAmount = sumBillAmount.add(billAmount);

//			GST AMOUNT CALCULATION
			gstAmount = lcAmount.multiply(gstPercent).divide(BigDecimal.valueOf(100));
			chargerCostDebitVO.setGstAmount(gstAmount);

//			AGGREGATE IGST SUMS BY GST PERCENTAGE
			if (costDebitNoteDTO.getGstType().equalsIgnoreCase("INTER") && gstPercent.compareTo(BigDecimal.ZERO) > 0) {
				String igstCategoryKey = gstPercent.toString();
				igstCategorySumMap.put(igstCategoryKey,
						igstCategorySumMap.getOrDefault(igstCategoryKey, BigDecimal.ZERO).add(gstAmount));
			}
			if (costDebitNoteDTO.getGstType().equalsIgnoreCase("INTRA") && gstPercent.compareTo(BigDecimal.ZERO) > 0) {
				String gstCategoryKey = gstPercent.toString();
				cgstCategorySumMap.put(gstCategoryKey,
						cgstCategorySumMap.getOrDefault(gstCategoryKey, BigDecimal.ZERO).add(gstAmount));
			}

			chargerCostDebitVO.setCostDebitNoteVO(costDebitNoteVO);
			chargerCostDebitVOs.add(chargerCostDebitVO);
		}

//		ADD IGST ROWS FOR EACH IGST PERCENTAGE IN igstCategorySumMap
		if ("INTER".equalsIgnoreCase(costDebitNoteDTO.getGstType())) {
			for (Map.Entry<String, BigDecimal> entry : igstCategorySumMap.entrySet()) {
				CostDebitChargesVO igstSummaryVO = new CostDebitChargesVO();

				String gstPercent = entry.getKey();
				BigDecimal igstLcAmount = entry.getValue();
				taxAmount = taxAmount.add(igstLcAmount);

				Set<Object[]> chargeVO = costInvoiceRepo
						.findChargeNameAndChargeCodeForIgstPosting(costDebitNoteDTO.getOrgId(), gstPercent);

				if (!chargeVO.isEmpty()) {
					Object[] chargeVOSet = chargeVO.iterator().next(); // Get the first element in the set
					String chargeDesc = (String) chargeVOSet[0];
					String chargeCode = (String) chargeVOSet[1];
					String gChargeCode = (String) chargeVOSet[2];
					String taxable = (String) chargeVOSet[3];
					String sac = (String) chargeVOSet[4];
					Float gstPer = (Float) chargeVOSet[5];
					igstSummaryVO.setChargeName(chargeDesc);
					igstSummaryVO.setChargeCode(chargeCode);
					igstSummaryVO.setGovChargeCode(gChargeCode);
					igstSummaryVO.setTaxable(taxable);
					igstSummaryVO.setSac(sac);
					igstSummaryVO.setGSTPercent(gstPer);

				}
				igstSummaryVO.setQty(Integer.valueOf(1));
				igstSummaryVO.setRate(BigDecimal.ONE);
				igstSummaryVO.setExRate(BigDecimal.ONE);
				igstSummaryVO.setFcAmt(BigDecimal.ONE);
				igstSummaryVO.setLcAmt(igstLcAmount);
				igstSummaryVO.setFcAmt(BigDecimal.ONE);
				igstSummaryVO.setBillAmt(BigDecimal.ONE);
				igstSummaryVO.setGstAmount(BigDecimal.ONE);
				igstSummaryVO.setCostDebitNoteVO(costDebitNoteVO);

				chargerCostDebitVOs.add(igstSummaryVO);
			}
		}

//		ADD CGST and SGST ROWS FOR EACH GST PERCENTAGE IN cgstCategorySumMap
		if ("INTRA".equalsIgnoreCase(costDebitNoteDTO.getGstType())) {
			for (Map.Entry<String, BigDecimal> entry : cgstCategorySumMap.entrySet()) {

				String gstPercent = entry.getKey();
				BigDecimal intraPercent = new BigDecimal(gstPercent).divide(BigDecimal.valueOf(2));
				System.out.println("PARAM" + intraPercent);
				BigDecimal totalTaxAmount = entry.getValue();

				BigDecimal cgstAmount = totalTaxAmount.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
				taxAmount = taxAmount.add(cgstAmount);
				BigDecimal sgstAmount = totalTaxAmount.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
				taxAmount = taxAmount.add(sgstAmount);

				Set<Object[]> chargeVO = costInvoiceRepo
						.findChargeNameAndChargeCodeForCgstAndSgtsPosting(costDebitNoteDTO.getOrgId(), intraPercent);

				if (!chargeVO.isEmpty()) {

//					TO HOLD THE FIRST TWO LIST
					Object[] cgstRecord = null;
					Object[] sgstRecord = null;

					// Iterate through the query results
					for (Object[] chargeVOSet : chargeVO) {
						String chargeCode = (String) chargeVOSet[1];

						// Determine CGST and SGST records based on the charge code
						if (chargeCode.contains("CGST")) {
							cgstRecord = chargeVOSet; // Set CGST record
						} else if (chargeCode.contains("SGST")) {
							sgstRecord = chargeVOSet; // Set SGST record
						}

						if (cgstRecord != null && sgstRecord != null) {

							CostDebitChargesVO cgstSummaryVO = new CostDebitChargesVO();
							cgstSummaryVO.setChargeName((String) cgstRecord[0]);
							cgstSummaryVO.setChargeCode((String) cgstRecord[1]);
							cgstSummaryVO.setGovChargeCode((String) cgstRecord[2]);
							cgstSummaryVO.setTaxable((String) cgstRecord[3]);
							cgstSummaryVO.setSac((String) cgstRecord[4]);
							cgstSummaryVO.setGSTPercent((Float) cgstRecord[5]);
							cgstSummaryVO.setQty(Integer.valueOf(1));
							cgstSummaryVO.setRate(BigDecimal.ONE);
							cgstSummaryVO.setExRate(BigDecimal.ONE);
							cgstSummaryVO.setFcAmt(BigDecimal.ONE);
							cgstSummaryVO.setLcAmt(cgstAmount);
							cgstSummaryVO.setBillAmt(BigDecimal.ONE);
							cgstSummaryVO.setGstAmount(BigDecimal.ONE);
							cgstSummaryVO.setCostDebitNoteVO(costDebitNoteVO);
							chargerCostDebitVOs.add(cgstSummaryVO);

							CostDebitChargesVO sgstSummaryVO = new CostDebitChargesVO();
							sgstSummaryVO.setChargeName((String) sgstRecord[0]);
							sgstSummaryVO.setChargeCode((String) sgstRecord[1]);
							sgstSummaryVO.setGovChargeCode((String) sgstRecord[2]);
							sgstSummaryVO.setTaxable((String) sgstRecord[3]);
							sgstSummaryVO.setSac((String) sgstRecord[4]);
							sgstSummaryVO.setGSTPercent((Float) sgstRecord[5]);
							sgstSummaryVO.setQty(Integer.valueOf(1));
							sgstSummaryVO.setRate(BigDecimal.ONE);
							sgstSummaryVO.setExRate(BigDecimal.ONE);
							sgstSummaryVO.setFcAmt(BigDecimal.ONE);
							sgstSummaryVO.setLcAmt(sgstAmount);
							sgstSummaryVO.setBillAmt(BigDecimal.ONE);
							sgstSummaryVO.setGstAmount(BigDecimal.ONE);
							sgstSummaryVO.setCostDebitNoteVO(costDebitNoteVO);

							chargerCostDebitVOs.add(sgstSummaryVO);

							break;
						}
					}
				}
			}
		}

		Map<String, BigDecimal> ledgerSumMap = new HashMap<>();
		for (CostDebitChargesVO detailsVO : chargerCostDebitVOs) {
			String ledger = detailsVO.getLedger();
			BigDecimal lcAmount = detailsVO.getLcAmt();
			ledgerSumMap.put(ledger, ledgerSumMap.getOrDefault(ledger, BigDecimal.ZERO).add(lcAmount));
		}

		costDebitNoteVO.setCostDebitChargesVO(chargerCostDebitVOs);

		// TDS table
		List<CostDebitNoteTaxPrtculVO> tdsCostDebitVOs = new ArrayList<>();
		for (CostDebitNoteTaxPrtculDTO tdsCostDebitDTO : costDebitNoteDTO.getCostDebitNoteTaxPrtculDTO()) {
			CostDebitNoteTaxPrtculVO tdsCostDebitVO = new CostDebitNoteTaxPrtculVO();

			tdsCostDebitVO.setTdsWithHolding(tdsCostDebitDTO.getTdsWithHolding());
			tdsCostDebitVO.setTdsWithHoldingPer(tdsCostDebitDTO.getTdsWithHoldingPer());
			tdsCostDebitVO.setSection(tdsCostDebitDTO.getSection());
//			tdsCostInvoiceVO.setTotTdsWhAmnt(tdsCostInvoiceDTO.getTotTdsWhAmnt());

			BigDecimal totTdsWhAmt = BigDecimal.ZERO;
			BigDecimal tdsWhPercent = tdsCostDebitDTO.getTdsWithHoldingPer();
			System.out.println("TOTAL LC AMOUNT IS :" + sumLcAmount);
			totTdsWhAmt = sumLcAmount.multiply(tdsWhPercent.divide(BigDecimal.valueOf(100)));
			tdsCostDebitVO.setTotTdsWhAmnt(totTdsWhAmt);
			tdsAmount = totTdsWhAmt;

			tdsCostDebitVO.setCostDebitNoteVO(costDebitNoteVO);
			tdsCostDebitVOs.add(tdsCostDebitVO);
		}
		costDebitNoteVO.setCostDebitNoteTaxPrtculVO(tdsCostDebitVOs);

		// SUMMARY CALCULATION
		BigDecimal totChargeAmtBillCurr = sumBillAmount;
		BigDecimal totChargeAmtLc = sumLcAmount;
		BigDecimal netAmountBillCurr = sumBillAmount.subtract(tdsAmount).add(taxAmount);
		BigDecimal netAmountLc = taxAmount.subtract(tdsAmount).add(sumLcAmount);
		BigDecimal actBillAmtLc = sumLcAmount.subtract(tdsAmount).add(taxAmount);
		BigDecimal actBillAmtBillCurr = sumBillAmount.add(taxAmount);

		costDebitNoteVO.setTotChargesBillCurrAmt(totChargeAmtBillCurr);
		costDebitNoteVO.setTotChargesLcAmt(totChargeAmtLc);
		costDebitNoteVO.setNetBillCurrAmt(netAmountBillCurr);
		costDebitNoteVO.setNetBillLcAmt(netAmountLc);
		costDebitNoteVO.setActBillCurrAmt(actBillAmtBillCurr);
		costDebitNoteVO.setActBillLcAmt(actBillAmtLc);

		List<CostDebitNoteGstVO> costDebitNoteGstVOs = new ArrayList<>();
		for (Map.Entry<String, BigDecimal> entry : ledgerSumMap.entrySet()) {
			CostDebitNoteGstVO costDebitNoteGstVO = new CostDebitNoteGstVO();

			costDebitNoteGstVO.setChargeAcc(entry.getKey());
			costDebitNoteGstVO.setSubLodgerCode("None");
			costDebitNoteGstVO.setDBillAmt(BigDecimal.ZERO);
			costDebitNoteGstVO.setCrBillAmt(entry.getValue());
			costDebitNoteGstVO.setDBLCAmt(BigDecimal.ZERO);
			costDebitNoteGstVO.setCrLCAmt(entry.getValue());
//			costDebitNoteGstVO.setRemarks(costDebitNoteGstDTO.getRemarks());

			costDebitNoteGstVO.setCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteGstVOs.add(costDebitNoteGstVO);
		}
		costDebitNoteVO.setCostDebitNoteGstVO(costDebitNoteGstVOs);
		costDebitNoteVO.setCostDebitChargesVO(chargerCostDebitVOs);

		return costDebitNoteVO;
	}

	@Override
	public List<CostDebitNoteVO> getCostDebitNoteByOrgId(Long orgId) {

		return costDebitNoteRepo.getByCostDebitNoteByOrgId(orgId);
	}

	@Override
	public List<CostDebitNoteVO> getCostDebitNoteById(Long id) {
		return costDebitNoteRepo.getByCostDebitNoteById(id);
	}

	@Override
	public List<CostDebitNoteVO> getActiveCostDebitNote() {
		return costDebitNoteRepo.getActiveCostDebitNote();
	}

	@Override
	public CostDebitNoteVO getAllCostDebitNoteByDocId(Long orgId, String docId) {
		return costDebitNoteRepo.findAllCostDebitNoteByDocId(orgId, docId);
	}

	// 8778426636
	@Override
	public String getCostDebitNoteDocId(Long orgId, String finYear, String branch, String branchCode) {
		String ScreenCode = "CDN";
		String result = costDebitNoteRepo.getCostDebitNoteDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

	@Override
	public List<Map<String, Object>> chargeTypeDetailsForCostDebitNote(Long orgId) {
		Set<Object[]> getChargeTypeDetails = costDebitNoteRepo.getChareDetails(orgId);
		return getChargeType(getChargeTypeDetails); // Call the correct method
	}

	private List<Map<String, Object>> getChargeType(Set<Object[]> getCharge) {
		List<Map<String, Object>> gridDetails = new ArrayList<>();
		for (Object[] grid : getCharge) {
			Map<String, Object> details = new HashMap<>();
			details.put("chargeType", grid[0] != null ? grid[0].toString() : "");
			details.put("chargeCode", grid[1] != null ? grid[1].toString() : "");
			details.put("govtSac", grid[2] != null ? grid[2].toString() : "");
			details.put("serviceAccountCode", grid[3] != null ? grid[3].toString() : ""); // Updated to match
																							// "serviceAccountCode"

			gridDetails.add(details);
		}
		return gridDetails;
	}

	@Override
	public List<Map<String, Object>> partyDetailsForCostDebitNote(Long orgId, String branch, String finYear) {
		// Fetch party details from the repository
		Set<Object[]> getPartyTypeDetails = costDebitNoteRepo.getParty(orgId, branch, finYear);
		// Return processed party details
		return getPartyDetails(getPartyTypeDetails);
	}

	private List<Map<String, Object>> getPartyDetails(Set<Object[]> getPartyInformation) {
		List<Map<String, Object>> gridDetails = new ArrayList<>();
		// Iterate through the result set
		for (Object[] grid : getPartyInformation) {

			Map<String, Object> details = new HashMap<>();
			details.put("partyName", grid[0] != null ? grid[0].toString() : "");
			details.put("partyCode", grid[1] != null ? grid[1].toString() : "");
			details.put("addressType", grid[2] != null ? grid[2].toString() : "");

			gridDetails.add(details);

		}
		return gridDetails; // Return the list of party details
	}

	@Override
	public List<Map<String, Object>> getAllDocIdForCostInvoice(Long orgId) {
		Set<Object[]> getAllDocId = costDebitNoteRepo.getDocIdForCI(orgId);
		// Return processed party details
		return getAllCIDocId(getAllDocId);
	}

	private List<Map<String, Object>> getAllCIDocId(Set<Object[]> getDocIdInformation) {
		List<Map<String, Object>> gridDetails = new ArrayList<>();
		// Iterate through the result set
		for (Object[] grid : getDocIdInformation) {

			Map<String, Object> details = new HashMap<>();
			details.put("docId", grid[0] != null ? grid[0].toString() : "");
			gridDetails.add(details);

		}
		return gridDetails; // Return the list of party details
	}

	@Override
	public List<Map<String, Object>> getCurrencyAndExrates(Long orgId) {
		Set<Object[]> currency = costDebitNoteRepo.getCurrencyAndExrateDetails(orgId);
		return getCurrency(currency);
	}

	private List<Map<String, Object>> getCurrency(Set<Object[]> currency) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : currency) {
			Map<String, Object> map = new HashMap<>();
			map.put("currency", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("currencyDescription", ch[1] != null ? ch[1].toString() : "");
			map.put("buyingExRate", ch[2] != null ? ch[2].toString() : "");
			map.put("sellingExRate", ch[3] != null ? ch[3].toString() : "");
			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<CostInvoiceVO> getOrginBillNoByParty(Long orgId, String party, String branchCode) {
		return costInvoiceRepo.findOrginBillNoByParty(orgId, party, branchCode);
	}

	@Override
	public List<Map<String, Object>> partyTypeForCostDebitNote(Long orgId, String branch, String finYear) {
		// Fetch party details from the repository
		Set<Object[]> getAllPartyType = costDebitNoteRepo.partyTypeForCostDebitNote(orgId, branch, finYear);
		// Return processed party details
		return getPartyType(getAllPartyType);
	}

	private List<Map<String, Object>> getPartyType(Set<Object[]> getPartyInformation) {
		List<Map<String, Object>> gridDetails = new ArrayList<>();
		// Iterate through the result set
		for (Object[] grid : getPartyInformation) {

			Map<String, Object> details = new HashMap<>();
			details.put("partyType", grid[0] != null ? grid[0].toString() : "");
			gridDetails.add(details);

		}
		return gridDetails; // Return the list of party details
	}

}
