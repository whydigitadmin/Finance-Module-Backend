package com.base.basesetup.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.dto.TaxInvoiceDetailsDTO;
import com.base.basesetup.entity.TaxInvoiceDetailsVO;
import com.base.basesetup.entity.TaxInvoiceGstVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.TaxInvoiceDetailsRepo;
import com.base.basesetup.repo.TaxInvoiceGstRepo;
import com.base.basesetup.repo.TaxInvoiceRepo;

public class TaxInvoiceServiceImpl implements TaxInvoiceService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(TaxInvoiceServiceImpl.class);
	
	@Autowired
	TaxInvoiceRepo taxInvoiceRepo;

	@Autowired
	TaxInvoiceDetailsRepo taxInvoiceDetailsRepo;
	
	@Autowired
	TaxInvoiceGstRepo taxInvoiceGstRepo;

	
	// TaxInvoice
		@Override
		public List<TaxInvoiceVO> getAllTaxInvoiceByFinYearAndOrgId(Long orgId, String finYear, String branchCode) {
			List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
				taxInvoiceVO = taxInvoiceRepo.getAllTaxInvoiceByOrgId(orgId,finYear,branchCode);
			
			return taxInvoiceVO;
		}

		@Override
		public TaxInvoiceVO getTaxInvoiceById(Long id) {
			TaxInvoiceVO taxInvoiceVO = new TaxInvoiceVO();
			
				taxInvoiceVO = taxInvoiceRepo.getTaxInvoiceById(id);
			
			return taxInvoiceVO;
		}

		@Override
		public Map<String, Object> updateCreateTaxInvoice(TaxInvoiceDTO taxInvoiceDTO) throws ApplicationException {
			TaxInvoiceVO taxInvoiceVO = new TaxInvoiceVO();
			String message;
			if (ObjectUtils.isNotEmpty(taxInvoiceDTO.getId())) {
				taxInvoiceVO = taxInvoiceRepo.findById(taxInvoiceDTO.getId())
						.orElseThrow(() -> new ApplicationException("Tax Invoice not found"));

				taxInvoiceVO.setModifiedBy(taxInvoiceDTO.getCreatedBy());
				createUpdateTaxInvoiceVOByTaxInvoiceDTO(taxInvoiceDTO, taxInvoiceVO);
				message = "Tax Invoice Updated Successfully";
			} else {

				taxInvoiceVO.setCreatedBy(taxInvoiceDTO.getCreatedBy());
				taxInvoiceVO.setModifiedBy(taxInvoiceDTO.getCreatedBy());
				createUpdateTaxInvoiceVOByTaxInvoiceDTO(taxInvoiceDTO, taxInvoiceVO);
				message = "Tax Invoice Created Successfully";
			}

			
			taxInvoiceRepo.save(taxInvoiceVO);
			Map<String, Object> response = new HashMap<>();
			response.put("taxInvoiceVO", taxInvoiceVO);
			response.put("message", message);
			return response;
		}

		private void createUpdateTaxInvoiceVOByTaxInvoiceDTO(TaxInvoiceDTO taxInvoiceDTO, TaxInvoiceVO taxInvoiceVO) {

			// Map fields from DTO to VO
			taxInvoiceVO.setOrgId(taxInvoiceDTO.getOrgId());
			taxInvoiceVO.setBranch(taxInvoiceDTO.getBranch());
			taxInvoiceVO.setBranchCode(taxInvoiceDTO.getBranchCode());
			taxInvoiceVO.setFinYear(taxInvoiceDTO.getFinYear());
			taxInvoiceVO.setCreatedBy(taxInvoiceDTO.getCreatedBy());
			taxInvoiceVO.setBizType(taxInvoiceDTO.getBizType());
			taxInvoiceVO.setBizMode(taxInvoiceDTO.getBizMode());
			taxInvoiceVO.setPartyName(taxInvoiceDTO.getPartyName());
			taxInvoiceVO.setPartyCode(taxInvoiceDTO.getPartyCode());
			taxInvoiceVO.setPartyType(taxInvoiceDTO.getPartyType());
			taxInvoiceVO.setStateNo(taxInvoiceDTO.getStateNo());
			taxInvoiceVO.setStateCode(taxInvoiceDTO.getStateCode());
			taxInvoiceVO.setRecipientGSTIN(taxInvoiceDTO.getRecipientGSTIN());
			taxInvoiceVO.setPlaceOfSupply(taxInvoiceDTO.getPlaceOfSupply());
			taxInvoiceVO.setAddressType(taxInvoiceDTO.getAddressType());
			taxInvoiceVO.setAddress(taxInvoiceDTO.getAddress());
			taxInvoiceVO.setPinCode(taxInvoiceDTO.getPinCode());
			taxInvoiceVO.setStatus(taxInvoiceDTO.getStatus());
			taxInvoiceVO.setGstType(taxInvoiceDTO.getGstType());
			taxInvoiceVO.setSupplierBillNo(taxInvoiceDTO.getSupplierBillNo());
			taxInvoiceVO.setSupplierBillDate(taxInvoiceDTO.getSupplierBillDate());
			taxInvoiceVO.setBillCurr(taxInvoiceDTO.getBillCurr());
			taxInvoiceVO.setBillCurrRate(taxInvoiceDTO.getBillCurrRate());
			taxInvoiceVO.setExAmount(taxInvoiceDTO.getExAmount());
			taxInvoiceVO.setCreditDays(taxInvoiceDTO.getCreditDays());
			taxInvoiceVO.setContactPerson(taxInvoiceDTO.getContactPerson());
			taxInvoiceVO.setShipperInvoiceNo(taxInvoiceDTO.getShipperInvoiceNo());
			taxInvoiceVO.setBillOfEntry(taxInvoiceDTO.getBillOfEntry());
			taxInvoiceVO.setBillMonth(taxInvoiceDTO.getBillMonth());
			taxInvoiceVO.setInvoiceNo(taxInvoiceDTO.getInvoiceNo());
			taxInvoiceVO.setInvoiceDate(taxInvoiceDTO.getInvoiceDate());
			taxInvoiceVO.setSalesType(taxInvoiceDTO.getSalesType());
			taxInvoiceVO.setModifiedBy(taxInvoiceDTO.getCreatedBy());

			if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
				List<TaxInvoiceDetailsVO> taxInvoiceDetailsVO1 = taxInvoiceDetailsRepo.findByTaxInvoiceVO(taxInvoiceVO);
				taxInvoiceDetailsRepo.deleteAll(taxInvoiceDetailsVO1);
			}
			BigDecimal totalChargeAmountLC = BigDecimal.ZERO;
			BigDecimal totalChargeAmountBC = BigDecimal.ZERO;
			BigDecimal totalTaxAmountLC = BigDecimal.ZERO;
			BigDecimal totalTaxAmountBC = BigDecimal.ZERO;
			BigDecimal totalInvAmountLC = BigDecimal.ZERO;
			BigDecimal totalInvAmountBC = BigDecimal.ZERO;

			List<TaxInvoiceDetailsVO> taxInvoiceDetailsVOs = new ArrayList<>();
			for (TaxInvoiceDetailsDTO taxInvoiceDetailsDTO : taxInvoiceDTO.getTaxInvoiceDetailsDTO()) {

				TaxInvoiceDetailsVO taxInvoiceDetailsVO = new TaxInvoiceDetailsVO();
				taxInvoiceDetailsVO.setChargeType(taxInvoiceDetailsDTO.getChargeType());
				taxInvoiceDetailsVO.setChargeCode(taxInvoiceDetailsDTO.getChargeCode());
				taxInvoiceDetailsVO.setGovChargeCode(taxInvoiceDetailsDTO.getGovChargeCode());
				taxInvoiceDetailsVO.setLedger(taxInvoiceDetailsDTO.getLedger());
				taxInvoiceDetailsVO.setChargeName(taxInvoiceDetailsDTO.getChargeName());
				taxInvoiceDetailsVO.setTaxable(taxInvoiceDetailsDTO.getTaxable());
				taxInvoiceDetailsVO.setQty(taxInvoiceDetailsDTO.getQty());
				taxInvoiceDetailsVO.setRate(taxInvoiceDetailsDTO.getRate());
				taxInvoiceDetailsVO.setCurrency(taxInvoiceDetailsDTO.getCurrency());
				taxInvoiceDetailsVO.setExRate(taxInvoiceDetailsDTO.getExRate());
				taxInvoiceDetailsVO.setExempted(taxInvoiceDetailsDTO.getExempted());
				taxInvoiceDetailsVO.setSac(taxInvoiceDetailsDTO.getSac());
				taxInvoiceDetailsVO.setGSTPercent(taxInvoiceDetailsDTO.getGSTPercent());

				BigDecimal fcAmount;
				BigDecimal lcAmount;
				BigDecimal tlcAmount;
				BigDecimal billAmount;
				BigDecimal gstAmount;

				if (!taxInvoiceDetailsDTO.getCurrency().equals("INR")) {
					BigDecimal rate = taxInvoiceDetailsDTO.getRate(); // BigDecimal type is expected here
					BigDecimal qty = BigDecimal.valueOf(taxInvoiceDetailsDTO.getQty()); // Convert qty to BigDecimal

					fcAmount = rate.multiply(qty);

					taxInvoiceDetailsVO.setFcAmount(fcAmount);

				} else {
					fcAmount = BigDecimal.valueOf(0.00);
					taxInvoiceDetailsVO.setFcAmount(fcAmount);
				}

				BigDecimal exRate = taxInvoiceDetailsDTO.getExRate();// Assuming getExRate() returns BigDecimal
				BigDecimal qty = BigDecimal.valueOf(taxInvoiceDetailsDTO.getQty());
				BigDecimal rate = taxInvoiceDetailsDTO.getRate();
				lcAmount = exRate.multiply(qty.multiply(rate));
				taxInvoiceDetailsVO.setLcAmount(lcAmount);
				totalChargeAmountLC = totalChargeAmountLC.add(lcAmount);

				BigDecimal gstPercent = BigDecimal.valueOf(taxInvoiceDetailsDTO.getGSTPercent()); // Convert GSTPercent to
																									// BigDecimal
				tlcAmount = lcAmount.multiply(gstPercent).divide(BigDecimal.valueOf(100));
				taxInvoiceDetailsVO.setTlcAmount(tlcAmount);

				billAmount = lcAmount.divide(exRate, RoundingMode.HALF_UP); // Ensure you specify a RoundingMode when
																			// dividing
				taxInvoiceDetailsVO.setBillAmount(billAmount);
				totalChargeAmountBC = totalChargeAmountBC.add(billAmount);

				gstAmount = lcAmount.multiply(gstPercent).divide(BigDecimal.valueOf(100));
				taxInvoiceDetailsVO.setGstAmount(gstAmount);
				totalTaxAmountLC = totalTaxAmountLC.add(gstAmount);
				totalTaxAmountBC = totalTaxAmountBC.add(gstAmount);

				taxInvoiceDetailsVO.setTaxInvoiceVO(taxInvoiceVO);
				taxInvoiceDetailsVOs.add(taxInvoiceDetailsVO);
			}

			Map<String, BigDecimal> ledgerSumMap = new HashMap<>();
			for (TaxInvoiceDetailsVO detailsVO : taxInvoiceDetailsVOs) {
				String ledger = detailsVO.getLedger();
				BigDecimal lcAmount = detailsVO.getLcAmount();

				ledgerSumMap.put(ledger, ledgerSumMap.getOrDefault(ledger, BigDecimal.ZERO).add(lcAmount));
			}
			if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
				List<TaxInvoiceGstVO> taxInvoiceGstVO = taxInvoiceGstRepo.findByTaxInvoiceVO(taxInvoiceVO);
				taxInvoiceGstRepo.deleteAll(taxInvoiceGstVO);
			}
			List<TaxInvoiceGstVO> taxInvoiceGstVOList = new ArrayList<>();
			for (Map.Entry<String, BigDecimal> entry : ledgerSumMap.entrySet()) {
				TaxInvoiceGstVO taxInvoiceGstVO = new TaxInvoiceGstVO();
				taxInvoiceGstVO.setGstChargeAcc(entry.getKey());
				taxInvoiceGstVO.setGstCrLcAmount(entry.getValue());
				taxInvoiceGstVO.setGstDbBillAmount(BigDecimal.ZERO);
				taxInvoiceGstVO.setGstDbLcAmount(BigDecimal.ZERO);
				taxInvoiceGstVO.setGstSubledgerCode("None");
				taxInvoiceGstVO.setGstCrBillAmount(entry.getValue());
				taxInvoiceGstVO.setTaxInvoiceVO(taxInvoiceVO);
				taxInvoiceGstVOList.add(taxInvoiceGstVO);
			}
			taxInvoiceVO.setTaxInvoiceGstVO(taxInvoiceGstVOList);
			
			totalInvAmountLC = totalChargeAmountLC.add(totalTaxAmountLC);
			totalInvAmountBC = totalChargeAmountBC.add(totalTaxAmountBC);

			taxInvoiceVO.setTotalChargeAmountLc(totalChargeAmountLC);
			taxInvoiceVO.setTotalChargeAmountBc(totalChargeAmountBC);
			taxInvoiceVO.setTotalTaxAmountLc(totalTaxAmountLC);
			taxInvoiceVO.setTotalTaxAmountBc(totalTaxAmountBC);

			BigDecimal originalTotalInvAmountLC = totalChargeAmountLC.add(totalTaxAmountLC);
			BigDecimal roundedTotalInvAmountLC = totalInvAmountLC.setScale(0, RoundingMode.HALF_UP);
			BigDecimal roundOffAmountLC = roundedTotalInvAmountLC.subtract(originalTotalInvAmountLC);
			taxInvoiceVO.setTotalInvAmountLc(roundedTotalInvAmountLC);
			taxInvoiceVO.setRoundOffAmountLc(roundOffAmountLC);

			BigDecimal roundedTotalInvAmountBC = totalInvAmountBC.setScale(0, RoundingMode.HALF_UP);
			taxInvoiceVO.setTotalInvAmountBc(roundedTotalInvAmountBC);

			taxInvoiceVO.setTaxInvoiceDetailsVO(taxInvoiceDetailsVOs);

		}


		@Override
		public TaxInvoiceVO getTaxInvoiceByDocId(Long orgId, String docId) {
			return taxInvoiceRepo.findAllTaxInvoiceByDocId(orgId, docId);
		}

}
