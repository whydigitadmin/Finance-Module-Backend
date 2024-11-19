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

import com.base.basesetup.dto.ChargerCostInvoiceDTO;
import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.dto.TdsCostInvoiceDTO;
import com.base.basesetup.entity.ChargerCostInvoiceVO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.DocumentTypeMappingDetailsVO;
import com.base.basesetup.entity.PartyMasterVO;
import com.base.basesetup.entity.TdsCostInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ChargeTypeRequestRepo;
import com.base.basesetup.repo.ChargerCostInvoiceRepo;
import com.base.basesetup.repo.CostInvoiceRepo;
import com.base.basesetup.repo.DocumentTypeMappingDetailsRepo;
import com.base.basesetup.repo.TdsCostInvoiceRepo;

@Service
public class CostInvoiceServiceImpl implements CostInvoiceService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CostInvoiceServiceImpl.class);

	@Autowired
	CostInvoiceRepo costInvoiceRepo;

	@Autowired
	TdsCostInvoiceRepo tdsCostInvoiceRepo;

	@Autowired
	ChargerCostInvoiceRepo chargerCostInvoiceRepo;

	@Autowired
	DocumentTypeMappingDetailsRepo documentTypeMappingDetailsRepo;

	@Autowired
	ChargeTypeRequestRepo chargeTypeRequestRepo;

	// costInvoice

	@Override
	public List<CostInvoiceVO> getAllCostInvoiceByOrgId(Long orgId) {
		List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  CostInvoice BY OrgId : {}", orgId);
			costInvoiceVO = costInvoiceRepo.getAllCostInvoiceByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  CostInvoice For All OrgId.");
			costInvoiceVO = costInvoiceRepo.findAll();
		}
		return costInvoiceVO;
	}

	@Override
	public List<CostInvoiceVO> getAllCostInvoiceById(Long id) {
		List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  CostInvoice BY Id : {}", id);
			costInvoiceVO = costInvoiceRepo.getAllCostInvoiceById(id);
		} else {
			LOGGER.info("Successfully Received CostInvoice For All Id.");
			costInvoiceVO = costInvoiceRepo.findAll();
		}
		return costInvoiceVO;
	}

	@Override
	public List<CostInvoiceVO> getCostInvoiceByActive() {
		return costInvoiceRepo.findCostInvoiceByActive();
	}

	@Override
	public Map<String, Object> updateCreateCostInvoice(@Valid CostInvoiceDTO costInvoiceDTO)
			throws ApplicationException {
		String screenCode = "CI";
		CostInvoiceVO costInvoiceVO;
		String message = null;

		if (ObjectUtils.isEmpty(costInvoiceDTO.getId())) {

			costInvoiceVO = new CostInvoiceVO();

			// GETDOCID API
			String docId = costInvoiceRepo.getCostInvoiceDocId(costInvoiceDTO.getOrgId(), costInvoiceDTO.getFinYear(),
					costInvoiceDTO.getBranchCode(), screenCode);
			costInvoiceVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(costInvoiceDTO.getOrgId(),
							costInvoiceDTO.getFinYear(), costInvoiceDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			costInvoiceVO.setCreatedBy(costInvoiceDTO.getCreatedBy());
			costInvoiceVO.setUpdatedBy(costInvoiceDTO.getCreatedBy());

			message = "CostInvoice Creation Successfully";
		}

		else {
			costInvoiceVO = costInvoiceRepo.findById(costInvoiceDTO.getId()).orElseThrow(
					() -> new ApplicationException("Cost Invoice Not Found with id: " + costInvoiceDTO.getId()));
			costInvoiceVO.setUpdatedBy(costInvoiceDTO.getCreatedBy());

			message = "CostInvoice Updation Successfully";
		}

		costInvoiceVO = getCostInvoiceVOFromCostInvoiceDTO(costInvoiceVO, costInvoiceDTO);
		costInvoiceRepo.save(costInvoiceVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("costInvoiceVO", costInvoiceVO);
		return response;

	}

	private CostInvoiceVO getCostInvoiceVOFromCostInvoiceDTO(CostInvoiceVO costInvoiceVO,
			@Valid CostInvoiceDTO costInvoiceDTO) {

		costInvoiceVO.setMode(costInvoiceDTO.getMode());
		costInvoiceVO.setProduct(costInvoiceDTO.getProduct());
		costInvoiceVO.setPurVoucherNo(costInvoiceDTO.getPurVoucherNo());
		costInvoiceVO.setPurVoucherDate(costInvoiceDTO.getPurVoucherDate());
		costInvoiceVO.setCostInvoiceNo(costInvoiceDTO.getCostInvoiceNo());
		costInvoiceVO.setCostInvoiceDate(costInvoiceDTO.getCostInvoiceDate());
		costInvoiceVO.setSupplierBillNo(costInvoiceDTO.getSupplierBillNo());
		costInvoiceVO.setSupplierType(costInvoiceDTO.getSupplierType());
		costInvoiceVO.setSupplierCode(costInvoiceDTO.getSupplierCode());
		costInvoiceVO.setCreditDays(costInvoiceDTO.getCreditDays());
		costInvoiceVO.setDueDate(costInvoiceDTO.getDueDate());
		costInvoiceVO.setSupplierName(costInvoiceDTO.getSupplierName());
		costInvoiceVO.setSupplierPlace(costInvoiceDTO.getSupplierPlace());
		costInvoiceVO.setCurrency(costInvoiceDTO.getCurrency());
		costInvoiceVO.setExRate(costInvoiceDTO.getExRate());
		costInvoiceVO.setSupplierGstIn(costInvoiceDTO.getSupplierGstIn());
		costInvoiceVO.setSupplierGstInCode(costInvoiceDTO.getSupplierGstInCode());
		costInvoiceVO.setRemarks(costInvoiceDTO.getRemarks());
		costInvoiceVO.setAddress(costInvoiceDTO.getAddress());
		costInvoiceVO.setOtherInfo(costInvoiceDTO.getOtherInfo());
		costInvoiceVO.setShipperRefNo(costInvoiceDTO.getShipperRefNo());
		costInvoiceVO.setGstType(costInvoiceDTO.getGstType());
		costInvoiceVO.setOrgId(costInvoiceDTO.getOrgId());
		costInvoiceVO.setCreatedBy(costInvoiceDTO.getCreatedBy());
		costInvoiceVO.setCancelRemarks(costInvoiceDTO.getCancelRemarks());
		costInvoiceVO.setBranch(costInvoiceDTO.getBranch());
		costInvoiceVO.setBranchCode(costInvoiceDTO.getBranchCode());
		costInvoiceVO.setCustomer(costInvoiceDTO.getCustomer());
		costInvoiceVO.setClient(costInvoiceDTO.getClient());
		costInvoiceVO.setFinYear(costInvoiceDTO.getFinYear());
		costInvoiceVO.setIpNo(costInvoiceDTO.getIpNo());
		costInvoiceVO.setLatitude(costInvoiceDTO.getLatitude());
		costInvoiceVO.setPayment(costInvoiceDTO.getPayment());
		costInvoiceVO.setAccuralid(costInvoiceDTO.getAccuralid());
		costInvoiceVO.setUtrRef(costInvoiceDTO.getUtrRef());
		costInvoiceVO.setCostType(costInvoiceDTO.getCostType());

		if (costInvoiceDTO.getId() != null) {

			List<ChargerCostInvoiceVO> chargerCostInvoiceVOs = chargerCostInvoiceRepo
					.findByCostInvoiceVO(costInvoiceVO);
			chargerCostInvoiceRepo.deleteAll(chargerCostInvoiceVOs);

			List<TdsCostInvoiceVO> tdsCostInvoiceVOs = tdsCostInvoiceRepo.findByCostInvoiceVO(costInvoiceVO);
			tdsCostInvoiceRepo.deleteAll(tdsCostInvoiceVOs);

		}

		BigDecimal sumBillAmount = BigDecimal.ZERO;
		BigDecimal sumLcAmount = BigDecimal.ZERO;
		BigDecimal taxAmount = BigDecimal.ZERO;
		BigDecimal tdsAmount = BigDecimal.ZERO;

		List<ChargerCostInvoiceVO> chargerCostInvoiceVOs = new ArrayList<>();

		// Map to store IGST sums by GST percentage
		Map<String, BigDecimal> igstCategorySumMap = new HashMap<>();
		Map<String, BigDecimal> cgstCategorySumMap = new HashMap<>();

		for (ChargerCostInvoiceDTO chargerCostInvoiceDTO : costInvoiceDTO.getChargerCostInvoiceDTO()) {
			ChargerCostInvoiceVO chargerCostInvoiceVO = new ChargerCostInvoiceVO();

			chargerCostInvoiceVO.setRate(chargerCostInvoiceDTO.getRate());
			chargerCostInvoiceVO.setJobNo(chargerCostInvoiceDTO.getJobNo());
			chargerCostInvoiceVO.setChargeName(chargerCostInvoiceDTO.getChargeName());
			chargerCostInvoiceVO.setChargeCode(chargerCostInvoiceDTO.getChargeCode());
			chargerCostInvoiceVO.setChargeLedger(chargerCostInvoiceDTO.getChargeLedger());
			chargerCostInvoiceVO.setLedger(chargerCostInvoiceDTO.getLedger());
			chargerCostInvoiceVO.setSac(chargerCostInvoiceDTO.getSac());
			chargerCostInvoiceVO.setCurrency(chargerCostInvoiceDTO.getCurrency());
			chargerCostInvoiceVO.setExRate(chargerCostInvoiceDTO.getExRate());
			chargerCostInvoiceVO.setGst(chargerCostInvoiceDTO.getGst());
			chargerCostInvoiceVO.setGovChargeCode(chargerCostInvoiceDTO.getGovChargeCode());
			chargerCostInvoiceVO.setExempted(chargerCostInvoiceDTO.getExempted());
			chargerCostInvoiceVO.setTaxable(chargerCostInvoiceDTO.getTaxable());
			chargerCostInvoiceVO.setGSTPercent(chargerCostInvoiceDTO.getGstPercent());

//			FIELD DECLARATION
			BigDecimal fcAmount = BigDecimal.ZERO;
			BigDecimal lcAmount;
			BigDecimal billAmount;
			BigDecimal gstAmount = BigDecimal.ZERO;

//			TO CHECK THE CURRENCY 
			if (!chargerCostInvoiceDTO.getCurrency().equals("INR")) {
				BigDecimal rate = chargerCostInvoiceDTO.getRate(); // BigDecimal type is expected here
				BigDecimal qty = BigDecimal.valueOf(chargerCostInvoiceDTO.getQty()); // Convert qty to BigDecimal
				fcAmount = rate.multiply(qty);
				chargerCostInvoiceVO.setFcAmt(fcAmount);
			} else {
				fcAmount = BigDecimal.valueOf(0.00);
				chargerCostInvoiceVO.setFcAmt(fcAmount);
			}

//			FIELD DECLARATION
			BigDecimal exRate = chargerCostInvoiceDTO.getExRate();
			BigDecimal qty = BigDecimal.valueOf(chargerCostInvoiceDTO.getQty());
			BigDecimal rate = chargerCostInvoiceDTO.getRate();
			BigDecimal gstPercent = BigDecimal.valueOf(chargerCostInvoiceDTO.getGstPercent());

//			LC AMOUNT CALCULATION
			lcAmount = exRate.multiply(qty.multiply(rate));
			chargerCostInvoiceVO.setLcAmt(lcAmount);
			sumLcAmount = sumLcAmount.add(lcAmount); // TDS Purpose

			if (chargerCostInvoiceDTO.getGstPercent() != 0) {
				taxAmount = taxAmount.add(lcAmount);
			}

//			BILL AMOUNT CALCULATION
			billAmount = lcAmount.divide(exRate, RoundingMode.HALF_UP);
			chargerCostInvoiceVO.setBillAmt(billAmount);
			sumBillAmount = sumBillAmount.add(billAmount);

//			GST AMOUNT CALCULATION
			gstAmount = lcAmount.multiply(gstPercent).divide(BigDecimal.valueOf(100));
			chargerCostInvoiceVO.setGstAmount(gstAmount);

//			AGGREGATE IGST SUMS BY GST PERCENTAGE
			if (costInvoiceDTO.getGstType().equalsIgnoreCase("INTER") && gstPercent.compareTo(BigDecimal.ZERO) > 0) {
				String igstCategoryKey = gstPercent.toString();
				igstCategorySumMap.put(igstCategoryKey,
						igstCategorySumMap.getOrDefault(igstCategoryKey, BigDecimal.ZERO).add(gstAmount));
			}
			if (costInvoiceDTO.getGstType().equalsIgnoreCase("INTRA") && gstPercent.compareTo(BigDecimal.ZERO) > 0) {
				String gstCategoryKey = gstPercent.toString();
				cgstCategorySumMap.put(gstCategoryKey,
						cgstCategorySumMap.getOrDefault(gstCategoryKey, BigDecimal.ZERO).add(gstAmount));
			}

			chargerCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
			chargerCostInvoiceVOs.add(chargerCostInvoiceVO);
		}

//		ADD IGST ROWS FOR EACH IGST PERCENTAGE IN igstCategorySumMap
		if ("INTER".equalsIgnoreCase(costInvoiceDTO.getGstType())) {
			for (Map.Entry<String, BigDecimal> entry : igstCategorySumMap.entrySet()) {
				ChargerCostInvoiceVO igstSummaryVO = new ChargerCostInvoiceVO();

				String gstPercent = entry.getKey();
				BigDecimal igstLcAmount = entry.getValue();

				Set<Object[]> chargeVO = costInvoiceRepo
						.findChargeNameAndChargeCodeForIgstPosting(costInvoiceDTO.getOrgId(), gstPercent);

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
				igstSummaryVO.setCostInvoiceVO(costInvoiceVO);

				chargerCostInvoiceVOs.add(igstSummaryVO);
			}
		}

//		ADD CGST and SGST ROWS FOR EACH GST PERCENTAGE IN cgstCategorySumMap
		if ("INTRA".equalsIgnoreCase(costInvoiceDTO.getGstType())) {
			for (Map.Entry<String, BigDecimal> entry : cgstCategorySumMap.entrySet()) {

				String gstPercent = entry.getKey();
				BigDecimal intraPercent = new BigDecimal(gstPercent).divide(BigDecimal.valueOf(2));
				System.out.println("PARAM" + intraPercent);
				BigDecimal totalTaxAmount = entry.getValue();

				BigDecimal cgstAmount = totalTaxAmount.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);
				BigDecimal sgstAmount = totalTaxAmount.divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);

				Set<Object[]> chargeVO = costInvoiceRepo
						.findChargeNameAndChargeCodeForCgstAndSgtsPosting(costInvoiceDTO.getOrgId(), intraPercent);

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

							ChargerCostInvoiceVO cgstSummaryVO = new ChargerCostInvoiceVO();
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
							cgstSummaryVO.setCostInvoiceVO(costInvoiceVO);
							chargerCostInvoiceVOs.add(cgstSummaryVO);

							ChargerCostInvoiceVO sgstSummaryVO = new ChargerCostInvoiceVO();
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
							sgstSummaryVO.setCostInvoiceVO(costInvoiceVO);

							chargerCostInvoiceVOs.add(sgstSummaryVO);

							break;
						}
					}
				}
			}
		}

		Map<String, BigDecimal> ledgerSumMap = new HashMap<>();
		for (ChargerCostInvoiceVO detailsVO : chargerCostInvoiceVOs) {
			String ledger = detailsVO.getLedger();
			BigDecimal lcAmount = detailsVO.getLcAmt();
			ledgerSumMap.put(ledger, ledgerSumMap.getOrDefault(ledger, BigDecimal.ZERO).add(lcAmount));
		}

		// TDS table
		costInvoiceVO.setChargerCostInvoiceVO(chargerCostInvoiceVOs);

		List<TdsCostInvoiceVO> tdsCostInvoiceVOs = new ArrayList<>();
		for (TdsCostInvoiceDTO tdsCostInvoiceDTO : costInvoiceDTO.getTdsCostInvoiceDTO()) {
			TdsCostInvoiceVO tdsCostInvoiceVO = new TdsCostInvoiceVO();

			tdsCostInvoiceVO.setTdsWithHolding(tdsCostInvoiceDTO.getTdsWithHolding());
			tdsCostInvoiceVO.setTdsWithHoldingPer(tdsCostInvoiceDTO.getTdsWithHoldingPer());
			tdsCostInvoiceVO.setSection(tdsCostInvoiceDTO.getSection());
//			tdsCostInvoiceVO.setTotTdsWhAmnt(tdsCostInvoiceDTO.getTotTdsWhAmnt());

			BigDecimal totTdsWhAmt = BigDecimal.ZERO;
			BigDecimal tdsWhPercent = tdsCostInvoiceDTO.getTdsWithHoldingPer();
			System.out.println("TOTAL LC AMOUNT IS :" + sumLcAmount);

			totTdsWhAmt = sumLcAmount.multiply(tdsWhPercent.divide(BigDecimal.valueOf(100)));
			tdsCostInvoiceVO.setTotTdsWhAmnt(totTdsWhAmt);
			tdsAmount = totTdsWhAmt;

			tdsCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
			tdsCostInvoiceVOs.add(tdsCostInvoiceVO);
		}
		costInvoiceVO.setTdsCostInvoiceVO(tdsCostInvoiceVOs);

		// Summary Calculation
		BigDecimal totChargeAmtBillCurr = sumBillAmount;
		BigDecimal totChargeAmtLc = sumLcAmount;
		BigDecimal netAmountBillCurr = sumBillAmount.add(tdsAmount).add(taxAmount);
		BigDecimal netAmountLc = taxAmount.add(sumLcAmount);
		BigDecimal actBillAmtLc = sumLcAmount.add(tdsAmount).add(taxAmount);
		BigDecimal actBillAmtBillCurr = sumBillAmount.add(tdsAmount).add(taxAmount);

		costInvoiceVO.setTotChargesBillCurrAmt(totChargeAmtBillCurr);
		costInvoiceVO.setTotChargesLcAmt(totChargeAmtLc);
		costInvoiceVO.setNetBillCurrAmt(netAmountBillCurr);
		costInvoiceVO.setNetBillLcAmt(netAmountLc);
		costInvoiceVO.setActBillCurrAmt(actBillAmtBillCurr);
		costInvoiceVO.setActBillLcAmt(actBillAmtLc);
		;
		return costInvoiceVO;

	}

	@Override
	public CostInvoiceVO getCostInvoiceByDocId(Long orgId, String docId) {
		return costInvoiceRepo.findAllCostInvoiceByDocId(orgId, docId);
	}

	@Override
	public String getCostInvoiceDocId(Long orgId, String finYear, String branch, String branchCode) {
		String ScreenCode = "CI";
		String result = costInvoiceRepo.getCostInvoiceDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

	@Override
	public List<Map<String, Object>> getChargeType(Long orgId) {
		Set<Object[]> chType = costInvoiceRepo.getActiveChargType(orgId);
		return getChargeType(chType);
	}

	private List<Map<String, Object>> getChargeType(Set<Object[]> chType) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : chType) {
			Map<String, Object> map = new HashMap<>();
			map.put("chargeType", ch[0].toString());
			List1.add(map);
		}
		return List1;

	}

	@Override
	public List<Map<String, Object>> getChargeCodeByChargeType(Long orgId, String chargeType) {
		Set<Object[]> chCode = costInvoiceRepo.getActiveChargCodeByOrgIdAndChargeTypeIgnoreCase(orgId, chargeType);
		return getChargeCode(chCode);
	}

	private List<Map<String, Object>> getChargeCode(Set<Object[]> chCode) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : chCode) {
			Map<String, Object> map = new HashMap<>();
			map.put("chargeCode", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("govChargeCode", ch[1] != null ? ch[1].toString() : "");
			map.put("chargeName", ch[2] != null ? ch[2].toString() : "");
			map.put("taxable", ch[3] != null ? ch[3].toString() : "");
			map.put("ccFeeApplicable", ch[4] != null ? ch[4].toString() : "");
			map.put("exempted", ch[5] != null ? ch[5].toString() : "");
			map.put("sac", ch[6] != null ? ch[6].toString() : "");
			map.put("GSTPercent", ch[7] != null ? ch[7].toString() : ""); // Handle as string, empty if null
			map.put("ledger", ch[8] != null ? ch[8].toString() : "");

			List1.add(map);
		}
		return List1;

	}

	@Override
	public List<Map<String, Object>> getCurrencyAndExratesForMatchingParties(Long orgId, String partyCode) {
		Set<Object[]> currency = costInvoiceRepo.getCurrencyAndExratesForMatchingParties(orgId, partyCode);
		return getCurrency(currency);
	}

	private List<Map<String, Object>> getCurrency(Set<Object[]> currency) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : currency) {
			Map<String, Object> map = new HashMap<>();
			map.put("orgId", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("date", ch[1] != null ? ch[1].toString() : "");
			map.put("month", ch[2] != null ? ch[2].toString() : "");
			map.put("currency", ch[3] != null ? ch[3].toString() : "");
			map.put("currencyDescripition", ch[4] != null ? ch[4].toString() : "");
			map.put("buyingExRate", ch[5] != null ? ch[5].toString() : "");
			map.put("sellingExRate", ch[6] != null ? ch[6].toString() : "");

			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<PartyMasterVO> getAllPartyByPartyType(Long orgId, String partyType) {

		return costInvoiceRepo.findByOrgIdAndPartyTypeIgnoreCase(orgId, partyType);

	}

	@Override
	public List<Map<String, Object>> getPartyStateCodeDetails(Long orgId, Long id) {
		Set<Object[]> getStateDetails = costInvoiceRepo.getStateCodeDetails(orgId, id);
		return getState(getStateDetails);
	}

	private List<Map<String, Object>> getState(Set<Object[]> getStateDetails) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : getStateDetails) {
			Map<String, Object> map = new HashMap<>();
			map.put("stateCode", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("recipientGSTIN", ch[1] != null ? ch[1].toString() : "");
			map.put("stateNo", ch[2] != null ? ch[2].toString() : "");
			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getPartyAddressDetails(Long orgId, Long id, String stateCode,
			String placeOfSupply) {
		Set<Object[]> getAddressDetails = costInvoiceRepo.getAddressDetails(orgId, id, stateCode, placeOfSupply);
		return getAddress(getAddressDetails);
	}

	private List<Map<String, Object>> getAddress(Set<Object[]> getAddressDetails) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : getAddressDetails) {
			Map<String, Object> map = new HashMap<>();
			map.put("addressType", ch[0] != null ? ch[0].toString() : "");
			map.put("address", ch[1] != null ? ch[1].toString() : ""); // Empty string if null
			map.put("pinCode", ch[2] != null ? ch[2].toString() : "");
			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getGstTypeDetails(Long orgId, String branchCode, String stateCode) {
		Set<Object[]> getGSTTypeDetails = costInvoiceRepo.getGstType(orgId, branchCode, stateCode);
		return getGstType(getGSTTypeDetails);
	}

	private List<Map<String, Object>> getGstType(Set<Object[]> getGSTTypeDetails) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : getGSTTypeDetails) {
			Map<String, Object> map = new HashMap<>();
			map.put("gstType", ch[0] != null ? ch[0].toString() : "");
			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getPlaceOfSupplyDetails(Long orgId, Long id, String stateCode) {
		Set<Object[]> getPOSDetails = costInvoiceRepo.getPlaceOfSupplyDetails(orgId, id, stateCode);
		return getPOS(getPOSDetails);
	}

	private List<Map<String, Object>> getPOS(Set<Object[]> getPOSDetails) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : getPOSDetails) {
			Map<String, Object> map = new HashMap<>();
			map.put("placeOfSupply", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getJobNoFromTmsJobCard(Long orgId) {
		Set<Object[]> getJobNo = costInvoiceRepo.getJobNoFromTmsJobCard(orgId);
		return getJobDetails(getJobNo);
	}

	private List<Map<String, Object>> getJobDetails(Set<Object[]> getJob) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : getJob) {
			Map<String, Object> map = new HashMap<>();
			map.put("jobNo", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			List1.add(map);
		}
		return List1;
	}

	@Override
	public List<Map<String, Object>> getChargeDetailsFromChargeType(Long orgId) {
		Set<Object[]> chDetails = chargeTypeRequestRepo.getActiveChargeDetailsFromChargeType(orgId);
		return getChargeDetails(chDetails);
	}

	private List<Map<String, Object>> getChargeDetails(Set<Object[]> chCode) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : chCode) {
			Map<String, Object> map = new HashMap<>();
			map.put("chargeCode", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("govChargeCode", ch[1] != null ? ch[1].toString() : "");
			map.put("chargeName", ch[2] != null ? ch[2].toString() : "");
			map.put("taxable", ch[3] != null ? ch[3].toString() : "");
			map.put("ccFeeApplicable", ch[4] != null ? ch[4].toString() : "");
			map.put("exempted", ch[5] != null ? ch[5].toString() : "");
			map.put("sac", ch[6] != null ? ch[6].toString() : "");
			map.put("GSTPercent", ch[7] != null ? ch[7].toString() : ""); // Handle as string, empty if null
			map.put("ledger", ch[8] != null ? ch[8].toString() : "");

			List1.add(map);
		}
		return List1;

	}

	@Override
	public List<Map<String, Object>> getTdsDetailsFromPartyMasterSpecialTDS(Long orgId, String partyCode) {
		Set<Object[]> specialTds = costInvoiceRepo.findTdsDetailsFromPartyMasterSpecialTDS(orgId, partyCode);
		return getTds(specialTds);
	}

	private List<Map<String, Object>> getTds(Set<Object[]> chCode) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : chCode) {
			Map<String, Object> map = new HashMap<>();
			map.put("section", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("tdsWhPercent", ch[1] != null ? ch[1].toString() : "");

			List1.add(map);
		}
		return List1;

	}

	@Override
	public List<Map<String, Object>> getChargeNameAndChargeCodeForIgst(Long orgId, List<String> gstTax) {
		Set<Object[]> chargeDetails = costInvoiceRepo.findChargeNameAndChargeCodeForIgst(orgId, gstTax);
		return getChargeDe(chargeDetails);
	}

	private List<Map<String, Object>> getChargeDe(Set<Object[]> chDetails) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : chDetails) {
			Map<String, Object> map = new HashMap<>();
			map.put("chargeDesc", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("chargeCode", ch[1] != null ? ch[1].toString() : "");
			map.put("gChargeCode", ch[2] != null ? ch[2].toString() : "");
			map.put("taxable", ch[3] != null ? ch[3].toString() : "");
			map.put("sac", ch[4] != null ? ch[4].toString() : "");
			map.put("gstPercent", ch[5] != null ? ch[5].toString() : "");

			List1.add(map);
		}
		return List1;

	}

	@Override
	public List<Map<String, Object>> getChargeNameAndChargeCodeForCgstAndSgst(Long orgId, List<String> gstTax) {
		Set<Object[]> chargeDetails = costInvoiceRepo.findChargeNameAndChargeCodeForCgstAndIgst(orgId, gstTax);
		return getChargeIntra(chargeDetails);
	}

	private List<Map<String, Object>> getChargeIntra(Set<Object[]> chDetails) {
		List<Map<String, Object>> List1 = new ArrayList<>();
		for (Object[] ch : chDetails) {
			Map<String, Object> map = new HashMap<>();
			map.put("chargeDesc", ch[0] != null ? ch[0].toString() : ""); // Empty string if null
			map.put("chargeCode", ch[1] != null ? ch[1].toString() : "");
			map.put("gChargeCode", ch[2] != null ? ch[2].toString() : "");
			map.put("taxable", ch[3] != null ? ch[3].toString() : "");
			map.put("sac", ch[4] != null ? ch[4].toString() : "");
			map.put("gstPercent", ch[5] != null ? ch[5].toString() : "");

			List1.add(map);
		}
		return List1;

	}

}
