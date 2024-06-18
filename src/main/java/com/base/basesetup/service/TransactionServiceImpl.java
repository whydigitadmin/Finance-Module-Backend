package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.BrsOpeningDTO;
import com.base.basesetup.dto.ChargerIrnCreditDTO;
import com.base.basesetup.dto.ChargerTaxInvoiceDTO;
import com.base.basesetup.dto.ChartCostCenterDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDtlDTO;
import com.base.basesetup.dto.FundTransferDTO;
import com.base.basesetup.dto.GeneralJournalDTO;
import com.base.basesetup.dto.GstIrnCreditDTO;
import com.base.basesetup.dto.GstTaxInvoiceDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.SummaryIrnCreditDTO;
import com.base.basesetup.dto.SummaryTaxInvoiceDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.BrsOpeningVO;
import com.base.basesetup.entity.ChargerIrnCreditVO;
import com.base.basesetup.entity.ChargerTaxInvoiceVO;
import com.base.basesetup.entity.ChartCostCenterVO;
import com.base.basesetup.entity.DailyMonthlyExRatesDtlVO;
import com.base.basesetup.entity.DailyMonthlyExRatesVO;
import com.base.basesetup.entity.FundTransferVO;
import com.base.basesetup.entity.GeneralJournalVO;
import com.base.basesetup.entity.GstIrnCreditVO;
import com.base.basesetup.entity.GstTaxInvoiceVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.SummaryIrnCreditVO;
import com.base.basesetup.entity.SummaryTaxInvoiceVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.BrsOpeningRepo;
import com.base.basesetup.repo.ChargerIrnCreditRepo;
import com.base.basesetup.repo.ChargerTaxInvoiceRepo;
import com.base.basesetup.repo.ChartCostCenterRepo;
import com.base.basesetup.repo.DailyMonthlyExRatesDtlRepo;
import com.base.basesetup.repo.DailyMonthlyExRatesRepo;
import com.base.basesetup.repo.FundTransferRepo;
import com.base.basesetup.repo.GeneralJournalRepo;
import com.base.basesetup.repo.GstIrnCreditRepo;
import com.base.basesetup.repo.GstTaxInvoiceRepo;
import com.base.basesetup.repo.IrnCreditRepo;
import com.base.basesetup.repo.ParticularsJournalRepo;
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

	@Autowired
	DailyMonthlyExRatesRepo dailyMonthlyExRatesRepo;

	@Autowired
	DailyMonthlyExRatesDtlRepo dailyMonthlyExRatesDtlRepo;

	@Autowired
	BrsOpeningRepo brsOpeningRepo;

	@Autowired
	ChartCostCenterRepo chartCostCenterRepo;

	@Autowired
	FundTransferRepo fundTransferRepo;

	@Autowired
	GeneralJournalRepo generalJournalRepo;

	@Autowired
	ParticularsJournalRepo particularsJournalRepo;

	@Autowired
	SummaryJournalRepo summaryJournalRepo;

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
		return taxInvoiceRepo.findAllTaxInvoiceByDocId(orgId, docId);
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

//	DailyMonthlyExRates
	@Override
	public List<DailyMonthlyExRatesVO> getAllDailyMonthlyExRatesById(Long id) {
		List<DailyMonthlyExRatesVO> dailyMonthlyExRatesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received DailyMonthlyExRates BY Id : {}", id);
			dailyMonthlyExRatesVO = dailyMonthlyExRatesRepo.findDailyMonthlyExRatesById(id);
		} else {
			LOGGER.info("Successfully Received DailyMonthlyExRates For All Id.");
			dailyMonthlyExRatesVO = dailyMonthlyExRatesRepo.findAll();
		}
		return dailyMonthlyExRatesVO;
	}

	@Override
	public List<DailyMonthlyExRatesVO> getAllDailyMonthlyExRatesByOrgId(Long orgId) {
		List<DailyMonthlyExRatesVO> dailyMonthlyExRatesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received DailyMonthlyExRates BY OrgId : {}", orgId);
			dailyMonthlyExRatesVO = dailyMonthlyExRatesRepo.findDailyMonthlyExRatesByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received DailyMonthlyExRates For All OrgId.");
			dailyMonthlyExRatesVO = dailyMonthlyExRatesRepo.findAll();
		}
		return dailyMonthlyExRatesVO;
	}

	@Override
	public DailyMonthlyExRatesVO updateCreateDailyMonthlyExRates(@Valid DailyMonthlyExRatesDTO dailyMonthlyExRatesDTO)
			throws ApplicationException {
		DailyMonthlyExRatesVO dailyMonthlyExRatesVO = new DailyMonthlyExRatesVO();
		if (ObjectUtils.isNotEmpty(dailyMonthlyExRatesDTO.getId())) {
			dailyMonthlyExRatesVO = dailyMonthlyExRatesRepo.findById(dailyMonthlyExRatesDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid DailyMonthlyExRates details"));
		}
		List<DailyMonthlyExRatesDtlVO> dailyMonthlyExRatesDtlVOs = new ArrayList<>();
		if (dailyMonthlyExRatesDTO.getDailyMonthlyExRatesDtlDTO() != null) {
			for (DailyMonthlyExRatesDtlDTO dailyMonthlyExRatesDtlDTO : dailyMonthlyExRatesDTO
					.getDailyMonthlyExRatesDtlDTO()) {
				DailyMonthlyExRatesDtlVO dailyMonthlyExRatesDtlVO;
				if (dailyMonthlyExRatesDtlDTO.getId() != null
						&& ObjectUtils.isNotEmpty(dailyMonthlyExRatesDtlDTO.getId())) {
					dailyMonthlyExRatesDtlVO = dailyMonthlyExRatesDtlRepo.findById(dailyMonthlyExRatesDtlDTO.getId())
							.orElse(new DailyMonthlyExRatesDtlVO());
				} else {
					dailyMonthlyExRatesDtlVO = new DailyMonthlyExRatesDtlVO();
				}
				dailyMonthlyExRatesDtlVO.setBuyingExrate(dailyMonthlyExRatesDtlDTO.getBuyingExrate());
				dailyMonthlyExRatesDtlVO.setCurrency(dailyMonthlyExRatesDtlDTO.getCurrency());
				dailyMonthlyExRatesDtlVO.setCurrencyDescripition(dailyMonthlyExRatesDtlDTO.getCurrencyDescripition());
				dailyMonthlyExRatesDtlVO.setSellingExRate(dailyMonthlyExRatesDtlDTO.getSellingExRate());
				dailyMonthlyExRatesDtlVO.setDailyMonthlyExRatesVO(dailyMonthlyExRatesVO);
				dailyMonthlyExRatesDtlVOs.add(dailyMonthlyExRatesDtlVO);
			}
		}
		dailyMonthlyExRatesVO.setDailyMonthlyExRatesDtlVO(dailyMonthlyExRatesDtlVOs); // Assuming you have this setter
		getDailyMonthlyExRatesVOFromDailyMonthlyExRatesDTO(dailyMonthlyExRatesDTO, dailyMonthlyExRatesVO);
		return dailyMonthlyExRatesRepo.save(dailyMonthlyExRatesVO);
	}

	private void getDailyMonthlyExRatesVOFromDailyMonthlyExRatesDTO(
			@Valid DailyMonthlyExRatesDTO dailyMonthlyExRatesDTO, DailyMonthlyExRatesVO dailyMonthlyExRatesVO) {
		dailyMonthlyExRatesVO.setDate(dailyMonthlyExRatesDTO.getDate());
		dailyMonthlyExRatesVO.setMonth(dailyMonthlyExRatesDTO.getMonth());
		dailyMonthlyExRatesVO.setActive(dailyMonthlyExRatesDTO.isActive());
		dailyMonthlyExRatesVO.setOrgId(dailyMonthlyExRatesDTO.getOrgId());
		dailyMonthlyExRatesVO.setCreatedBy(dailyMonthlyExRatesDTO.getCreatedBy());
		dailyMonthlyExRatesVO.setUpdatedBy(dailyMonthlyExRatesDTO.getUpdatedBy());
	}

	@Override
	public List<DailyMonthlyExRatesVO> getDailyMonthlyExRatesByActive() {
		return dailyMonthlyExRatesRepo.findDailyMonthlyExRatesByActive();

	}
	// BrsOpening

	@Override
	public List<BrsOpeningVO> getAllBrsOpeningByOrgId(Long orgId) {
		List<BrsOpeningVO> brsOpeningVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  BrsOpening BY OrgId : {}", orgId);
			brsOpeningVO = brsOpeningRepo.getAllBrsOpeningByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  BrsOpening For All OrgId.");
			brsOpeningVO = brsOpeningRepo.findAll();
		}
		return brsOpeningVO;
	}

	@Override
	public List<BrsOpeningVO> getAllBrsOpeningById(Long id) {
		List<BrsOpeningVO> brsOpeningVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received BrsOpening BY Id : {}", id);
			brsOpeningVO = brsOpeningRepo.getAllBrsOpeningById(id);
		} else {
			LOGGER.info("Successfully Received BrsOpening For All Id.");
			brsOpeningVO = brsOpeningRepo.findAll();
		}
		return brsOpeningVO;
	}

	@Override
	public BrsOpeningVO updateCreateBrsOpening(@Valid BrsOpeningDTO brsOpeningDTO) throws ApplicationException {
		BrsOpeningVO brsOpeningVO = new BrsOpeningVO();
		if (ObjectUtils.isNotEmpty(brsOpeningDTO.getId())) {
			brsOpeningVO = brsOpeningRepo.findById(brsOpeningDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid BrsOpening details"));
		}
//		else {
//			if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//				throw new ApplicationException("The given doc id already exists.");
//			}
//			if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//				throw new ApplicationException("The given invoice number already exists.");
//			}
//		}
//		if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//			if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//					taxInvoiceVO.getId())) {
//				throw new ApplicationException("The given doc id already exists.");
//			}
//			if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//					taxInvoiceVO.getId())) {
//				throw new ApplicationException("The given invoice number already exists.");
//			}
//		}

		getBrsOpeningVOFromBrsOpeningDTO(brsOpeningDTO, brsOpeningVO);
		return brsOpeningRepo.save(brsOpeningVO);
	}

	private void getBrsOpeningVOFromBrsOpeningDTO(@Valid BrsOpeningDTO brsOpeningDTO, BrsOpeningVO brsOpeningVO) {
		brsOpeningVO.setBillno(brsOpeningDTO.getBillNo());
		brsOpeningVO.setBillDate(brsOpeningDTO.getBillDate());
		brsOpeningVO.setChqNo(brsOpeningDTO.getChqNo());
		brsOpeningVO.setChqDate(brsOpeningDTO.getChqDate());
		brsOpeningVO.setBank(brsOpeningDTO.getBank());
		brsOpeningVO.setCurrency(brsOpeningDTO.getCurrency());
		brsOpeningVO.setExRate(brsOpeningDTO.getExRate());
		brsOpeningVO.setReceiptAmount(brsOpeningDTO.getReceiptAmount());
		brsOpeningVO.setPaymentAmount(brsOpeningDTO.getPaymentAmount());
		brsOpeningVO.setReconcile(brsOpeningDTO.isReconcile());
		brsOpeningVO.setOrgId(brsOpeningDTO.getOrgId());
		brsOpeningVO.setActive(brsOpeningDTO.isActive());
		brsOpeningVO.setUpdatedBy(brsOpeningDTO.getUpdatedBy());
		brsOpeningVO.setCreatedBy(brsOpeningDTO.getCreatedBy());
	}

	@Override
	public List<BrsOpeningVO> getBrsOpeningByActive() {
		return brsOpeningRepo.findBrsOpeningByActive();
	}
	// ChartCostCenter

	@Override
	public List<ChartCostCenterVO> getAllChartCostCenterByOrgId(Long orgId) {
		List<ChartCostCenterVO> chartCostCenterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ChartCostCenter BY OrgId : {}", orgId);
			chartCostCenterVO = chartCostCenterRepo.getAllChartCostCenterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ChartCostCenter For All OrgId.");
			chartCostCenterVO = chartCostCenterRepo.findAll();
		}
		return chartCostCenterVO;
	}

	@Override
	public List<ChartCostCenterVO> getAllChartCostCenterById(Long id) {
		List<ChartCostCenterVO> chartCostCenterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ChartCostCenter BY Id : {}", id);
			chartCostCenterVO = chartCostCenterRepo.getAllChartCostCenterById(id);
		} else {
			LOGGER.info("Successfully Received ChartCostCenter For All Id.");
			chartCostCenterVO = chartCostCenterRepo.findAll();
		}
		return chartCostCenterVO;
	}

	@Override
	public ChartCostCenterVO updateCreateChartCostCenter(@Valid ChartCostCenterDTO chartCostCenterDTO)
			throws ApplicationException {
		ChartCostCenterVO chartCostCenterVO = new ChartCostCenterVO();
		if (ObjectUtils.isNotEmpty(chartCostCenterDTO.getId())) {
			chartCostCenterVO = chartCostCenterRepo.findById(chartCostCenterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ChartCostCenter details"));
		}
//			else {
//				if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//					throw new ApplicationException("The given doc id already exists.");
//				}
//				if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//					throw new ApplicationException("The given invoice number already exists.");
//				}
//			}
//			if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//				if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//						taxInvoiceVO.getId())) {
//					throw new ApplicationException("The given doc id already exists.");
//				}
//				if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//						taxInvoiceVO.getId())) {
//					throw new ApplicationException("The given invoice number already exists.");
//				}
//			}

		getChartCostCenterVOFromChartCostCenterDTO(chartCostCenterDTO, chartCostCenterVO);
		return chartCostCenterRepo.save(chartCostCenterVO);
	}

	private void getChartCostCenterVOFromChartCostCenterDTO(@Valid ChartCostCenterDTO chartCostCenterDTO,
			ChartCostCenterVO chartCostCenterVO) {
		chartCostCenterVO.setCostCenterCode(chartCostCenterDTO.getCostCenterCode());
		chartCostCenterVO.setCostCenterName(chartCostCenterDTO.getCostCenterName());
		chartCostCenterVO.setCredit(chartCostCenterDTO.getCredit());
		chartCostCenterVO.setDebit(chartCostCenterDTO.getDebit());
		chartCostCenterVO.setOrgId(chartCostCenterDTO.getOrgId());
		chartCostCenterVO.setUpdatedBy(chartCostCenterDTO.getUpdatedBy());
		chartCostCenterVO.setCreatedBy(chartCostCenterDTO.getCreatedBy());
		chartCostCenterVO.setActive(chartCostCenterDTO.isActive());
	}

	@Override
	public List<ChartCostCenterVO> getChartCostCenterByActive() {
		return chartCostCenterRepo.findChartCostCenterByActive();
	}

	// FundTransfer

	@Override
	public List<FundTransferVO> getAllFundTransferByOrgId(Long orgId) {
		List<FundTransferVO> fundTransferVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  FundTransfer BY OrgId : {}", orgId);
			fundTransferVO = fundTransferRepo.getAllFundTransferByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  FundTransfer For All OrgId.");
			fundTransferVO = fundTransferRepo.findAll();
		}
		return fundTransferVO;
	}

	@Override
	public List<FundTransferVO> getAllFundTransferById(Long id) {
		List<FundTransferVO> fundTransferVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received FundTransfer BY Id : {}", id);
			fundTransferVO = fundTransferRepo.getAllFundTransferById(id);
		} else {
			LOGGER.info("Successfully Received FundTransfer For All Id.");
			fundTransferVO = fundTransferRepo.findAll();
		}
		return fundTransferVO;
	}

	@Override
	public FundTransferVO updateCreateFundTransfer(@Valid FundTransferDTO fundTransferDTO) throws ApplicationException {
		FundTransferVO fundTransferVO = new FundTransferVO();
		if (ObjectUtils.isNotEmpty(fundTransferDTO.getId())) {
			fundTransferVO = fundTransferRepo.findById(fundTransferDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid FundTransfer details"));
		}
//			else {
//				if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//					throw new ApplicationException("The given doc id already exists.");
//				}
//				if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//					throw new ApplicationException("The given invoice number already exists.");
//				}
//			}
//			if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//				if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//						taxInvoiceVO.getId())) {
//					throw new ApplicationException("The given doc id already exists.");
//				}
//				if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//						taxInvoiceVO.getId())) {
//					throw new ApplicationException("The given invoice number already exists.");
//				}
//			}

		getFundTransferVOFromFundTransferDTO(fundTransferDTO, fundTransferVO);
		return fundTransferRepo.save(fundTransferVO);
	}

	private void getFundTransferVOFromFundTransferDTO(@Valid FundTransferDTO fundTransferDTO,
			FundTransferVO fundTransferVO) {
		fundTransferVO.setBranch(fundTransferDTO.getBranch());
		fundTransferVO.setDocId(fundTransferDTO.getDocId());
		fundTransferVO.setPaymentType(fundTransferDTO.getPaymentType());
		fundTransferVO.setDocDate(fundTransferDTO.getDocDate());
		fundTransferVO.setReferenceNo(fundTransferDTO.getReferenceNo());
		fundTransferVO.setReferenceDate(fundTransferDTO.getReferenceDate());
		fundTransferVO.setFromAccount(fundTransferDTO.getFromAccount());
		fundTransferVO.setBalance(fundTransferDTO.getBalance());
		fundTransferVO.setCurrency(fundTransferDTO.getCurrency());
		fundTransferVO.setExRate(fundTransferDTO.getExRate());
		fundTransferVO.setToBranch(fundTransferDTO.getToBranch());
		fundTransferVO.setToBank(fundTransferDTO.getToBank());
		fundTransferVO.setChequeBook(fundTransferDTO.getChequeBook());
		fundTransferVO.setChequeNo(fundTransferDTO.getChequeNo());
		fundTransferVO.setChequeDate(fundTransferDTO.getChequeDate());
		fundTransferVO.setPaymentAmount(fundTransferDTO.getPaymentAmount());
		fundTransferVO.setConversionRate(fundTransferDTO.getConversionRate());
		fundTransferVO.setReceiptAmount(fundTransferDTO.getReceiptAmount());
		fundTransferVO.setGainLoss(fundTransferDTO.getGainLoss());
		fundTransferVO.setRemarks(fundTransferDTO.getRemarks());
		fundTransferVO.setActive(fundTransferDTO.isActive());
		fundTransferVO.setOrgId(fundTransferDTO.getOrgId());
		fundTransferVO.setCreatedBy(fundTransferDTO.getCreatedBy());
		fundTransferVO.setUpdatedBy(fundTransferDTO.getUpdatedBy());

	}

	@Override
	public List<FundTransferVO> getFundTransferByActive() {
		return fundTransferRepo.findFundTransferByActive();
	}

	// GeneralJournal

	@Override
	public List<GeneralJournalVO> getAllGeneralJournalByOrgId(Long orgId) {
		List<GeneralJournalVO> generalJournalVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  GeneralJournal BY OrgId : {}", orgId);
			generalJournalVO = generalJournalRepo.getAllGeneralJournalByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  GeneralJournal For All OrgId.");
			generalJournalVO = generalJournalRepo.findAll();
		}
		return generalJournalVO;
	}

	@Override
	public List<GeneralJournalVO> getAllGeneralJournalById(Long id) {
		List<GeneralJournalVO> generalJournalVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received GeneralJournal BY Id : {}", id);
			generalJournalVO = generalJournalRepo.getAllGeneralJournalById(id);
		} else {
			LOGGER.info("Successfully Received GeneralJournal For All Id.");
			generalJournalVO = generalJournalRepo.findAll();
		}
		return generalJournalVO;
	}

	@Override
	public GeneralJournalVO updateCreateGeneralJournal(@Valid GeneralJournalDTO generalJournalDTO)
			throws ApplicationException {
		GeneralJournalVO generalJournalVO = new GeneralJournalVO();
		if (ObjectUtils.isNotEmpty(generalJournalDTO.getId())) {
			generalJournalVO = generalJournalRepo.findById(generalJournalDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GeneralJournal details"));
		}
//				else {
//					if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//						throw new ApplicationException("The given doc id already exists.");
//					}
//					if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//						throw new ApplicationException("The given invoice number already exists.");
//					}
//				}
//				if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//					if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//							taxInvoiceVO.getId())) {
//						throw new ApplicationException("The given doc id already exists.");
//					}
//					if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//							taxInvoiceVO.getId())) {
//						throw new ApplicationException("The given invoice number already exists.");
//					}
//				}

		getGeneralJournalVOFromGeneralJournalDTO(generalJournalDTO, generalJournalVO);
		return generalJournalRepo.save(generalJournalVO);
	}

	private void getGeneralJournalVOFromGeneralJournalDTO(@Valid GeneralJournalDTO generalJournalDTO,
			GeneralJournalVO generalJournalVO) {
		generalJournalVO.setBranch(generalJournalDTO.getBranch());
		generalJournalVO.setVoucherType(generalJournalDTO.getVoucherType());
		generalJournalVO.setDocDate(generalJournalDTO.getDocDate());
		generalJournalVO.setDocId(generalJournalDTO.getDocId());
		generalJournalVO.setTemplate(generalJournalDTO.getTemplate());
		generalJournalVO.setCurrency(generalJournalDTO.getCurrency());
		generalJournalVO.setExRate(generalJournalDTO.getExRate());
		generalJournalVO.setRefNo(generalJournalDTO.getRefNo());
		generalJournalVO.setRefDate(generalJournalDTO.getRefDate());
		generalJournalVO.setReverseOn(generalJournalDTO.getReverseOn());
		generalJournalVO.setNarration(generalJournalDTO.getNarration());
		generalJournalVO.setOrgId(generalJournalDTO.getOrgId());
		generalJournalVO.setCreatedBy(generalJournalDTO.getCreatedBy());
		generalJournalVO.setUpdatedBy(generalJournalDTO.getUpdatedBy());
		generalJournalVO.setActive(generalJournalDTO.isActive());

	}

	@Override
	public List<GeneralJournalVO> getGeneralJournalByActive() {
		return generalJournalRepo.findGeneralJournalByActive();

	}

}
