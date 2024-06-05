package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ChargerTaxInvoiceDTO;
import com.base.basesetup.dto.GstTaxInvoiceDTO;
import com.base.basesetup.dto.SummaryTaxInvoiceDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.ChargerTaxInvoiceVO;
import com.base.basesetup.entity.GstTaxInvoiceVO;
import com.base.basesetup.entity.SummaryTaxInvoiceVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ChargerTaxInvoiceRepo;
import com.base.basesetup.repo.GstTaxInvoiceRepo;
import com.base.basesetup.repo.SummaryTaxInvoiceRepo;
import com.base.basesetup.repo.TaxInvocieRepo;

@Service
public class TransactionServiceImpl implements TransactionService {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	TaxInvocieRepo taxInvoiceRepo;

	@Autowired
	ChargerTaxInvoiceRepo chargerTaxInvoiceRepo;

	@Autowired
	GstTaxInvoiceRepo gstTaxInvoiceRepo;

	@Autowired
	SummaryTaxInvoiceRepo summaryTaxInvoiceRepo;

	// TaxInvoice

	@Override
	public List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId) {
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TaxInvoice BY OrgId : {}", orgId);
			taxInvoiceVO = taxInvoiceRepo.getAllTaxInvoiceByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TaxInvoice For All OrgId.");
			taxInvoiceVO = taxInvoiceRepo.findAll();
		}
		return taxInvoiceVO;
	}

	@Override
	public List<TaxInvoiceVO> getAllTaxInvoiceById(Long id) {
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TaxInvoice BY Id : {}", id);
			taxInvoiceVO = taxInvoiceRepo.getAllTaxInvoiceById(id);
		} else {
			LOGGER.info("Successfully Received  TaxInvoice For All Id.");
			taxInvoiceVO = taxInvoiceRepo.findAll();
		}
		return taxInvoiceVO;
	}

	@Override
	public TaxInvoiceVO updateCreateTaxInvoice(@Valid TaxInvoiceDTO taxInvoiceDTO) throws ApplicationException {
		TaxInvoiceVO taxInvoiceVO = new TaxInvoiceVO();
		if (ObjectUtils.isNotEmpty(taxInvoiceDTO.getId())) {
			taxInvoiceVO = taxInvoiceRepo.findById(taxInvoiceDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid TaxInvoice details"));
		} else {
			if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
				throw new ApplicationException("The given doc id already exists.");
			}
			if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
				throw new ApplicationException("The given invoice number already exists.");
			}
		}
		if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
			if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
					taxInvoiceVO.getId())) {
				throw new ApplicationException("The given doc id already exists.");
			}
			if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
					taxInvoiceVO.getId())) {
				throw new ApplicationException("The given invoice number already exists.");
			}
		}

		List<ChargerTaxInvoiceVO> chargerTaxInvoiceVOs = new ArrayList<>();
		if (taxInvoiceDTO.getChargerTaxInvoiceDTO() != null) {
			for (ChargerTaxInvoiceDTO chargerTaxInvoiceDTO : taxInvoiceDTO.getChargerTaxInvoiceDTO()) {
				ChargerTaxInvoiceVO chargerTaxInvoiceVO;
				if (chargerTaxInvoiceDTO.getId() != null && ObjectUtils.isNotEmpty(chargerTaxInvoiceDTO.getId())) {
					chargerTaxInvoiceVO = chargerTaxInvoiceRepo.findById(chargerTaxInvoiceDTO.getId())
							.orElse(new ChargerTaxInvoiceVO());
				} else {
					chargerTaxInvoiceVO = new ChargerTaxInvoiceVO();
				}
				chargerTaxInvoiceVO.setType(chargerTaxInvoiceDTO.getType());
				chargerTaxInvoiceVO.setChargeCode(chargerTaxInvoiceDTO.getChargeCode());
				chargerTaxInvoiceVO.setGChargeCode(chargerTaxInvoiceDTO.getGChargeCode());
				chargerTaxInvoiceVO.setChargeName(chargerTaxInvoiceDTO.getChargeName());
				chargerTaxInvoiceVO.setTaxable(chargerTaxInvoiceDTO.getTaxable());
				chargerTaxInvoiceVO.setQty(chargerTaxInvoiceDTO.getQty());
				chargerTaxInvoiceVO.setRate(chargerTaxInvoiceDTO.getRate());
				chargerTaxInvoiceVO.setCurrency(chargerTaxInvoiceDTO.getCurrency());
				chargerTaxInvoiceVO.setExRate(chargerTaxInvoiceDTO.getExRate());
				chargerTaxInvoiceVO.setQty(chargerTaxInvoiceDTO.getQty());
				chargerTaxInvoiceVO.setFcAmount(chargerTaxInvoiceDTO.getFcAmount());
				chargerTaxInvoiceVO.setLcAmount(chargerTaxInvoiceDTO.getLcAmount());
				chargerTaxInvoiceVO.setBillAmount(chargerTaxInvoiceDTO.getBillAmount());
				chargerTaxInvoiceVO.setSac(chargerTaxInvoiceDTO.getSac());
				chargerTaxInvoiceVO.setGST(chargerTaxInvoiceDTO.getGST());
				chargerTaxInvoiceVO.setGSTPercent(chargerTaxInvoiceDTO.getGSTPercent());
				chargerTaxInvoiceVO.setTaxInvoiceVO(taxInvoiceVO);
				chargerTaxInvoiceVOs.add(chargerTaxInvoiceVO);
			}
		}

		List<SummaryTaxInvoiceVO> summaryTaxInvoiceVOs = new ArrayList<>();
		if (taxInvoiceDTO.getSummaryTaxInvoiceDTO() != null) {
			for (SummaryTaxInvoiceDTO summaryTaxInvoiceDTO : taxInvoiceDTO.getSummaryTaxInvoiceDTO()) {
				SummaryTaxInvoiceVO summaryTaxInvoiceVO;
				if (summaryTaxInvoiceDTO.getId() != null & ObjectUtils.isNotEmpty(summaryTaxInvoiceDTO.getId())) {
					summaryTaxInvoiceVO = summaryTaxInvoiceRepo.findById(summaryTaxInvoiceDTO.getId())
							.orElse(new SummaryTaxInvoiceVO());
				} else {
					summaryTaxInvoiceVO = new SummaryTaxInvoiceVO();
				}
				summaryTaxInvoiceVO.setAmountInwords(summaryTaxInvoiceDTO.getAmountInwords());
				summaryTaxInvoiceVO.setBillingRemarks(summaryTaxInvoiceDTO.getBillingRemarks());
				summaryTaxInvoiceVO.setBillInvAmount(summaryTaxInvoiceDTO.getBillInvAmount());
				summaryTaxInvoiceVO.setBilllcChargeAmount(summaryTaxInvoiceDTO.getBilllcChargeAmount());
				summaryTaxInvoiceVO.setBillTaxAmount(summaryTaxInvoiceDTO.getBillTaxAmount());
				summaryTaxInvoiceVO.setLcChargeAmount(summaryTaxInvoiceDTO.getLcChargeAmount());
				summaryTaxInvoiceVO.setLcInvAmount(summaryTaxInvoiceDTO.getLcInvAmount());
				summaryTaxInvoiceVO.setLcRoundOffAmount(summaryTaxInvoiceDTO.getLcRoundOffAmount());
				summaryTaxInvoiceVO.setLcTaxableAmount(summaryTaxInvoiceDTO.getLcTaxableAmount());
				summaryTaxInvoiceVO.setLcTaxAmount(summaryTaxInvoiceDTO.getLcTaxAmount());
				summaryTaxInvoiceVO.setTaxInvoiceVO(taxInvoiceVO);
				summaryTaxInvoiceVOs.add(summaryTaxInvoiceVO);
			}
		}

		List<GstTaxInvoiceVO> gstTaxInvoiceVOs = new ArrayList<>();
		if (taxInvoiceDTO.getGstTaxInvoiceDTO() != null) {
			for (GstTaxInvoiceDTO gstTaxInvoiceDTO : taxInvoiceDTO.getGstTaxInvoiceDTO()) {
				GstTaxInvoiceVO gstTaxInvoiceVO;
				if (gstTaxInvoiceDTO.getId() != null & ObjectUtils.isEmpty(gstTaxInvoiceDTO.getId())) {
					gstTaxInvoiceVO = gstTaxInvoiceRepo.findById(gstTaxInvoiceDTO.getId())
							.orElse(new GstTaxInvoiceVO());
				} else {
					gstTaxInvoiceVO = new GstTaxInvoiceVO();
				}
				gstTaxInvoiceVO.setGstBdBillAmount(gstTaxInvoiceDTO.getGstBdBillAmount());
				gstTaxInvoiceVO.setGstCrLcAmount(gstTaxInvoiceDTO.getGstCrLcAmount());
				gstTaxInvoiceVO.setGstCrBillAmount(gstTaxInvoiceDTO.getGstCrBillAmount());
				gstTaxInvoiceVO.setGstDbLcAmount(gstTaxInvoiceDTO.getGstDbLcAmount());
				gstTaxInvoiceVO.setGstChargeAcc(gstTaxInvoiceDTO.getGstChargeAcc());
				gstTaxInvoiceVO.setGstSubledgerCode(gstTaxInvoiceDTO.getGstSubledgerCode());
				gstTaxInvoiceVO.setTaxInvoiceVO(taxInvoiceVO);
				gstTaxInvoiceVOs.add(gstTaxInvoiceVO);
			}
		}

		getTaxInvoiceVOFromTaxInvoiceDTO(taxInvoiceDTO, taxInvoiceVO);
		taxInvoiceVO.setChargerTaxInvoiceVO(chargerTaxInvoiceVOs);
		taxInvoiceVO.setSummaryTaxInvoiceVO(summaryTaxInvoiceVOs);
		taxInvoiceVO.setGstTaxInvoiceVO(gstTaxInvoiceVOs);
		return taxInvoiceRepo.save(taxInvoiceVO);
	}

	private void getTaxInvoiceVOFromTaxInvoiceDTO(@Valid TaxInvoiceDTO taxInvoiceDTO, TaxInvoiceVO taxInvoiceVO) {
		// Finyr
		int finyr = taxInvoiceRepo.findFinyr();
		// DocId
		String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		taxInvoiceVO.setDocId(taxInvoice);
		taxInvoiceRepo.nextSeq();
		// InvoiceNo
		String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		taxInvoiceVO.setInvoiceNo(invoiceNo);
		taxInvoiceRepo.nextSeqInvoice();
		taxInvoiceVO.setOrgId(taxInvoiceDTO.getOrgId());
		taxInvoiceVO.setAddress(taxInvoiceDTO.getAddress());
		taxInvoiceVO.setAddressType(taxInvoiceDTO.getAddressType());
		taxInvoiceVO.setBillCurr(taxInvoiceDTO.getBillCurr());
		taxInvoiceVO.setCreatedBy(taxInvoiceDTO.getCreatedBy());
		taxInvoiceVO.setDueDate(taxInvoiceDTO.getDueDate());
		taxInvoiceVO.setGSTType(taxInvoiceDTO.getGSTType());
		taxInvoiceVO.setHeaderColumns(taxInvoiceDTO.getHeaderColumns());
		taxInvoiceVO.setPartyCode(taxInvoiceDTO.getPartyCode());
		taxInvoiceVO.setPartyName(taxInvoiceDTO.getPartyName());
		taxInvoiceVO.setPartyType(taxInvoiceDTO.getPartyType());
		taxInvoiceVO.setPincode(taxInvoiceDTO.getPincode());
		taxInvoiceVO.setPlaceOfSupply(taxInvoiceDTO.getPlaceOfSupply());
		taxInvoiceVO.setRecipientGSTIN(taxInvoiceDTO.getRecipientGSTIN());
		taxInvoiceVO.setSalesType(taxInvoiceDTO.getSalesType());
		taxInvoiceVO.setStatus(taxInvoiceDTO.getStatus());
		taxInvoiceVO.setUpdatedBy(taxInvoiceDTO.getUpdatedBy());
		taxInvoiceVO.setDocDate(taxInvoiceDTO.getDocDate());
		taxInvoiceVO.setInvoiceDate(taxInvoiceDTO.getInvoiceDate());
	}

	@Override
	public List<TaxInvoiceVO> getTaxInvoiceByActive() {
		return taxInvoiceRepo.findTaxInvoiceByActive();

	}
}
