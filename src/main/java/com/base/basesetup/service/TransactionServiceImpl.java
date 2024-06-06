package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ChargerIrnCreditDTO;
import com.base.basesetup.dto.ChargerTaxInvoiceDTO;
import com.base.basesetup.dto.GstIrnCreditDTO;
import com.base.basesetup.dto.GstTaxInvoiceDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.SummaryIrnCreditDTO;
import com.base.basesetup.dto.SummaryTaxInvoiceDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.ChargerIrnCreditVO;
import com.base.basesetup.entity.ChargerTaxInvoiceVO;
import com.base.basesetup.entity.GstIrnCreditVO;
import com.base.basesetup.entity.GstTaxInvoiceVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.SummaryIrnCreditVO;
import com.base.basesetup.entity.SummaryTaxInvoiceVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ChargerIrnCreditRepo;
import com.base.basesetup.repo.ChargerTaxInvoiceRepo;
import com.base.basesetup.repo.GstIrnCreditRepo;
import com.base.basesetup.repo.GstTaxInvoiceRepo;
import com.base.basesetup.repo.IrnCreditRepo;
import com.base.basesetup.repo.SummaryIrnCreditRepo;
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

	@Autowired
	IrnCreditRepo irnCreditRepo;

	@Autowired
	ChargerIrnCreditRepo chargerIrnCreditRepo;

	@Autowired
	GstIrnCreditRepo gstIrnCreditRepo;

	@Autowired
	SummaryIrnCreditRepo summaryIrnCreditRepo;

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

	@Override
	public List<TaxInvoiceVO> getAllTaxInvoiceDocIdByOrgId(Long orgId) {
		return taxInvoiceRepo.findAllTaxInvoiceDocIdByOrgId(orgId);
	}

	@Override
	public List<TaxInvoiceVO> getAllTaxInvoiceByDocId(Long orgId, String docId) {
		return taxInvoiceRepo.findAllTaxInvoiceByDocId(orgId,docId);
	}
	// IrnCredit

	@Override
	public List<IrnCreditVO> getAllIrnCreditByOrgId(Long orgId) {
		List<IrnCreditVO> irnCreditVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  IrnCredit BY OrgId : {}", orgId);
			irnCreditVO = irnCreditRepo.getAllIrnCreditByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  IrnCredit For All OrgId.");
			irnCreditVO = irnCreditRepo.findAll();
		}
		return irnCreditVO;
	}

	@Override
	public List<IrnCreditVO> getAllIrnCreditById(Long id) {
		List<IrnCreditVO> irnCreditVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  IrnCredit BY Id : {}", id);
			irnCreditVO = irnCreditRepo.getAllIrnCreditById(id);
		} else {
			LOGGER.info("Successfully Received  IrnCredit For All Id.");
			irnCreditVO = irnCreditRepo.findAll();
		}
		return irnCreditVO;
	}

	@Override
	public IrnCreditVO updateCreateIrnCredit(@Valid IrnCreditDTO irnCreditDTO) throws ApplicationException {
		IrnCreditVO irnCreditVO = new IrnCreditVO();
		if (ObjectUtils.isNotEmpty(irnCreditDTO.getId())) {
			irnCreditVO = irnCreditRepo.findById(irnCreditDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid IrnCredit details"));
		} else {
			if (irnCreditRepo.existsByDocIdAndOrgId(irnCreditVO.getDocId(), irnCreditVO.getOrgId())) {
				throw new ApplicationException("The given doc id already exists.");
			}
			if (irnCreditRepo.existsByInvoiceNoAndOrgId(irnCreditVO.getInvoiceNo(), irnCreditVO.getOrgId())) {
				throw new ApplicationException("The given invoice number already exists.");
			}
		}
		if (ObjectUtils.isNotEmpty(irnCreditVO.getId())) {
			if (irnCreditRepo.existsByDocIdAndOrgIdAndId(irnCreditVO.getDocId(), irnCreditVO.getOrgId(),
					irnCreditVO.getId())) {
				throw new ApplicationException("The given doc id already exists.");
			}
			if (irnCreditRepo.existsByInvoiceNoAndOrgIdAndId(irnCreditVO.getInvoiceNo(), irnCreditVO.getOrgId(),
					irnCreditVO.getId())) {
				throw new ApplicationException("The given invoice number already exists.");
			}
		}

		List<ChargerIrnCreditVO> chargerIrnCreditVOs = new ArrayList<>();
		if (irnCreditDTO.getChargerIrnCreditDTO() != null) {
			for (ChargerIrnCreditDTO chargerIrnCreditDTO : irnCreditDTO.getChargerIrnCreditDTO()) {
				ChargerIrnCreditVO chargerIrnCreditVO;
				if (chargerIrnCreditDTO.getId() != null && ObjectUtils.isNotEmpty(chargerIrnCreditDTO.getId())) {
					chargerIrnCreditVO = chargerIrnCreditRepo.findById(chargerIrnCreditDTO.getId())
							.orElse(new ChargerIrnCreditVO());
				} else {
					chargerIrnCreditVO = new ChargerIrnCreditVO();
				}
				chargerIrnCreditVO.setType(chargerIrnCreditDTO.getType());
				chargerIrnCreditVO.setChargeCode(chargerIrnCreditDTO.getChargeCode());
				chargerIrnCreditVO.setGChargeCode(chargerIrnCreditDTO.getGChargeCode());
				chargerIrnCreditVO.setChargeName(chargerIrnCreditDTO.getChargeName());
				chargerIrnCreditVO.setTaxable(chargerIrnCreditDTO.getTaxable());
				chargerIrnCreditVO.setQty(chargerIrnCreditDTO.getQty());
				chargerIrnCreditVO.setRate(chargerIrnCreditDTO.getRate());
				chargerIrnCreditVO.setCurrency(chargerIrnCreditDTO.getCurrency());
				chargerIrnCreditVO.setExRate(chargerIrnCreditDTO.getExRate());
				chargerIrnCreditVO.setQty(chargerIrnCreditDTO.getQty());
				chargerIrnCreditVO.setFcAmount(chargerIrnCreditDTO.getFcAmount());
				chargerIrnCreditVO.setLcAmount(chargerIrnCreditDTO.getLcAmount());
				chargerIrnCreditVO.setBillAmount(chargerIrnCreditDTO.getBillAmount());
				chargerIrnCreditVO.setSac(chargerIrnCreditDTO.getSac());
				chargerIrnCreditVO.setGST(chargerIrnCreditDTO.getGST());
				chargerIrnCreditVO.setGSTPercent(chargerIrnCreditDTO.getGSTPercent());
				chargerIrnCreditVO.setIrnCreditVO(irnCreditVO);
				chargerIrnCreditVOs.add(chargerIrnCreditVO);
			}
		}

		List<SummaryIrnCreditVO> summaryIrnCreditVOs = new ArrayList<>();
		if (irnCreditDTO.getSummaryIrnCreditDTO() != null) {
			for (SummaryIrnCreditDTO summaryIrnCreditDTO : irnCreditDTO.getSummaryIrnCreditDTO()) {
				SummaryIrnCreditVO summaryIrnCreditVO;
				if (summaryIrnCreditDTO.getId() != null & ObjectUtils.isNotEmpty(summaryIrnCreditDTO.getId())) {
					summaryIrnCreditVO = summaryIrnCreditRepo.findById(summaryIrnCreditDTO.getId())
							.orElse(new SummaryIrnCreditVO());
				} else {
					summaryIrnCreditVO = new SummaryIrnCreditVO();
				}
				summaryIrnCreditVO.setAmountInwords(summaryIrnCreditDTO.getAmountInwords());
				summaryIrnCreditVO.setBillingRemarks(summaryIrnCreditDTO.getBillingRemarks());
				summaryIrnCreditVO.setBillInvAmount(summaryIrnCreditDTO.getBillInvAmount());
				summaryIrnCreditVO.setBilllcChargeAmount(summaryIrnCreditDTO.getBilllcChargeAmount());
				summaryIrnCreditVO.setBillTaxAmount(summaryIrnCreditDTO.getBillTaxAmount());
				summaryIrnCreditVO.setLcChargeAmount(summaryIrnCreditDTO.getLcChargeAmount());
				summaryIrnCreditVO.setLcInvAmount(summaryIrnCreditDTO.getLcInvAmount());
				summaryIrnCreditVO.setLcRoundOffAmount(summaryIrnCreditDTO.getLcRoundOffAmount());
				summaryIrnCreditVO.setLcTaxableAmount(summaryIrnCreditDTO.getLcTaxableAmount());
				summaryIrnCreditVO.setLcTaxAmount(summaryIrnCreditDTO.getLcTaxAmount());
				summaryIrnCreditVO.setIrnCreditVO(irnCreditVO);
				summaryIrnCreditVOs.add(summaryIrnCreditVO);
			}
		}

		List<GstIrnCreditVO> gstIrnCreditVOs = new ArrayList<>();
		if (irnCreditDTO.getGstIrnCreditDTO() != null) {
			for (GstIrnCreditDTO gstIrnCreditDTO : irnCreditDTO.getGstIrnCreditDTO()) {
				GstIrnCreditVO gstIrnCreditVO;
				if (gstIrnCreditDTO.getId() != null & ObjectUtils.isEmpty(gstIrnCreditDTO.getId())) {
					gstIrnCreditVO = gstIrnCreditRepo.findById(gstIrnCreditDTO.getId()).orElse(new GstIrnCreditVO());
				} else {
					gstIrnCreditVO = new GstIrnCreditVO();
				}
				gstIrnCreditVO.setGstBdBillAmount(gstIrnCreditDTO.getGstBdBillAmount());
				gstIrnCreditVO.setGstCrLcAmount(gstIrnCreditDTO.getGstCrLcAmount());
				gstIrnCreditVO.setGstCrBillAmount(gstIrnCreditDTO.getGstCrBillAmount());
				gstIrnCreditVO.setGstDbLcAmount(gstIrnCreditDTO.getGstDbLcAmount());
				gstIrnCreditVO.setGstChargeAcc(gstIrnCreditDTO.getGstChargeAcc());
				gstIrnCreditVO.setGstSubledgerCode(gstIrnCreditDTO.getGstSubledgerCode());
				gstIrnCreditVO.setIrnCreditVO(irnCreditVO);
				gstIrnCreditVOs.add(gstIrnCreditVO);
			}
		}
		getIrnCreditVOFromIrnCreditDTO(irnCreditDTO, irnCreditVO);
		irnCreditVO.setChargerIrnCreditVO(chargerIrnCreditVOs);
		irnCreditVO.setSummaryIrnCreditVO(summaryIrnCreditVOs);
		irnCreditVO.setGstIrnCreditVO(gstIrnCreditVOs);
		return irnCreditRepo.save(irnCreditVO);
	}

	private void getIrnCreditVOFromIrnCreditDTO(@Valid IrnCreditDTO irnCreditDTO, IrnCreditVO irnCreditVO) {
		// Finyr
		int finyr = irnCreditRepo.findFinyr();
		// DocId
		String irnCredit = "AC" + finyr + irnCreditRepo.findDocId();
		irnCreditVO.setDocId(irnCredit);
		irnCreditRepo.nextSeq();
		// InvoiceNo
		String invoiceNo = "AC" + finyr + "INV" + irnCreditRepo.findInvoiceNo();
		irnCreditVO.setInvoiceNo(invoiceNo);
		irnCreditRepo.nextSeqInvoice();
		irnCreditVO.setOriginBill(irnCreditDTO.getOriginBill());
		irnCreditVO.setOrgId(irnCreditDTO.getOrgId());
		irnCreditVO.setAddress(irnCreditDTO.getAddress());
		irnCreditVO.setAddressType(irnCreditDTO.getAddressType());
		irnCreditVO.setBillCurr(irnCreditDTO.getBillCurr());
		irnCreditVO.setCreatedBy(irnCreditDTO.getCreatedBy());
		irnCreditVO.setDueDate(irnCreditDTO.getDueDate());
		irnCreditVO.setGSTType(irnCreditDTO.getGSTType());
		irnCreditVO.setHeaderColumns(irnCreditDTO.getHeaderColumns());
		irnCreditVO.setPartyCode(irnCreditDTO.getPartyCode());
		irnCreditVO.setPartyName(irnCreditDTO.getPartyName());
		irnCreditVO.setPartyType(irnCreditDTO.getPartyType());
		irnCreditVO.setPincode(irnCreditDTO.getPincode());
		irnCreditVO.setPlaceOfSupply(irnCreditDTO.getPlaceOfSupply());
		irnCreditVO.setRecipientGSTIN(irnCreditDTO.getRecipientGSTIN());
		irnCreditVO.setSalesType(irnCreditDTO.getSalesType());
		irnCreditVO.setStatus(irnCreditDTO.getStatus());
		irnCreditVO.setUpdatedBy(irnCreditDTO.getUpdatedBy());
		irnCreditVO.setDocDate(irnCreditDTO.getDocDate());
		irnCreditVO.setInvoiceDate(irnCreditDTO.getInvoiceDate());
	}

	@Override
	public List<IrnCreditVO> getIrnCreditByActive() {
		return irnCreditRepo.findIrnCreditByActive();

	}


}
