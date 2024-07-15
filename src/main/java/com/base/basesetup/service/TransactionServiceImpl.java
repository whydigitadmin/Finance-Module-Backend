package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ArapAdjustmentsDTO;
import com.base.basesetup.dto.ArapDetailsDTO;
import com.base.basesetup.dto.BrsOpeningDTO;
import com.base.basesetup.dto.ChargerCostInvoiceDTO;
import com.base.basesetup.dto.ChargerDebitNoteDTO;
import com.base.basesetup.dto.ChargerIrnCreditDTO;
import com.base.basesetup.dto.ChargerTaxInvoiceDTO;
import com.base.basesetup.dto.ChartCostCenterDTO;
import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDtlDTO;
import com.base.basesetup.dto.DebitNoteDTO;
import com.base.basesetup.dto.FundTransferDTO;
import com.base.basesetup.dto.GeneralJournalDTO;
import com.base.basesetup.dto.GstDebitNoteDTO;
import com.base.basesetup.dto.GstIrnCreditDTO;
import com.base.basesetup.dto.GstSalesVoucherDTO;
import com.base.basesetup.dto.GstTaxInvoiceDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.ParticularsDebitNoteDTO;
import com.base.basesetup.dto.ParticularsGstVoucherDTO;
import com.base.basesetup.dto.ParticularsPaymentVoucherDTO;
import com.base.basesetup.dto.PaymentInvoiceDTO;
import com.base.basesetup.dto.PaymentOtherAccountDTO;
import com.base.basesetup.dto.PaymentReversalDTO;
import com.base.basesetup.dto.PaymentSummaryDTO;
import com.base.basesetup.dto.PaymentVoucherDTO;
import com.base.basesetup.dto.ReceiptInvoiceDTO;
import com.base.basesetup.dto.ReceiptOtherAccountDTO;
import com.base.basesetup.dto.ReceiptReversalDTO;
import com.base.basesetup.dto.ReceiptSummaryDTO;
import com.base.basesetup.dto.SummaryCostInvoiceDTO;
import com.base.basesetup.dto.SummaryDebitNoteDTO;
import com.base.basesetup.dto.SummaryGstVoucherDTO;
import com.base.basesetup.dto.SummaryIrnCreditDTO;
import com.base.basesetup.dto.SummaryPaymentVoucherDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.dto.TdsCostInvoiceDTO;
import com.base.basesetup.entity.ArapAdjustmentsVO;
import com.base.basesetup.entity.ArapDetailsVO;
import com.base.basesetup.entity.BrsOpeningVO;
import com.base.basesetup.entity.ChargerCostInvoiceVO;
import com.base.basesetup.entity.ChargerDebitNoteVO;
import com.base.basesetup.entity.ChargerIrnCreditVO;
import com.base.basesetup.entity.ChargerTaxInvoiceVO;
import com.base.basesetup.entity.ChartCostCenterVO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.DailyMonthlyExRatesDtlVO;
import com.base.basesetup.entity.DailyMonthlyExRatesVO;
import com.base.basesetup.entity.DebitNoteVO;
import com.base.basesetup.entity.FundTransferVO;
import com.base.basesetup.entity.GeneralJournalVO;
import com.base.basesetup.entity.GstDebitNoteVO;
import com.base.basesetup.entity.GstIrnCreditVO;
import com.base.basesetup.entity.GstSalesVoucherVO;
import com.base.basesetup.entity.GstTaxInvoiceVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.ParticularsDebitNoteVO;
import com.base.basesetup.entity.ParticularsGstVoucherVO;
import com.base.basesetup.entity.ParticularsPaymentVoucherVO;
import com.base.basesetup.entity.PaymentInvoiceVO;
import com.base.basesetup.entity.PaymentOtherAccountVO;
import com.base.basesetup.entity.PaymentReversalVO;
import com.base.basesetup.entity.PaymentSummaryVO;
import com.base.basesetup.entity.PaymentVoucherVO;
import com.base.basesetup.entity.ReceiptInvoiceVO;
import com.base.basesetup.entity.ReceiptOtherAccountVO;
import com.base.basesetup.entity.ReceiptReversalVO;
import com.base.basesetup.entity.ReceiptSummaryVO;
import com.base.basesetup.entity.SummaryCostInvoiceVO;
import com.base.basesetup.entity.SummaryDebitNoteVO;
import com.base.basesetup.entity.SummaryGstVoucherVO;
import com.base.basesetup.entity.SummaryIrnCreditVO;
import com.base.basesetup.entity.SummaryPaymentVoucherVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.entity.TdsCostInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ArapAdjustmentsRepo;
import com.base.basesetup.repo.ArapDetailsRepo;
import com.base.basesetup.repo.BrsOpeningRepo;
import com.base.basesetup.repo.ChargerCostInvoiceRepo;
import com.base.basesetup.repo.ChargerDebitNoteRepo;
import com.base.basesetup.repo.ChargerIrnCreditRepo;
import com.base.basesetup.repo.ChargerTaxInvoiceRepo;
import com.base.basesetup.repo.ChartCostCenterRepo;
import com.base.basesetup.repo.CostInvoiceRepo;
import com.base.basesetup.repo.DailyMonthlyExRatesDtlRepo;
import com.base.basesetup.repo.DailyMonthlyExRatesRepo;
import com.base.basesetup.repo.DebitNoteRepo;
import com.base.basesetup.repo.FundTransferRepo;
import com.base.basesetup.repo.GeneralJournalRepo;
import com.base.basesetup.repo.GstDebitNoteRepo;
import com.base.basesetup.repo.GstIrnCreditRepo;
import com.base.basesetup.repo.GstSalesVoucherRepo;
import com.base.basesetup.repo.GstTaxInvoiceRepo;
import com.base.basesetup.repo.IrnCreditRepo;
import com.base.basesetup.repo.ParticularsDebitNoteRepo;
import com.base.basesetup.repo.ParticularsGstVoucherRepo;
import com.base.basesetup.repo.ParticularsJournalRepo;
import com.base.basesetup.repo.ParticularsPaymentVoucherRepo;
import com.base.basesetup.repo.PaymentInvoiceRepo;
import com.base.basesetup.repo.PaymentOtherAccountRepo;
import com.base.basesetup.repo.PaymentReversalRepo;
import com.base.basesetup.repo.PaymentSummaryRepo;
import com.base.basesetup.repo.PaymentVoucherRepo;
import com.base.basesetup.repo.ReceiptInvoiceRepo;
import com.base.basesetup.repo.ReceiptOtherAccountRepo;
import com.base.basesetup.repo.ReceiptReversalRepo;
import com.base.basesetup.repo.ReceiptSummaryRepo;
import com.base.basesetup.repo.SummaryCostInvoiceRepo;
import com.base.basesetup.repo.SummaryDebitNoteRepo;
import com.base.basesetup.repo.SummaryGstVoucherRepo;
import com.base.basesetup.repo.SummaryIrnCreditRepo;
import com.base.basesetup.repo.SummaryJournalRepo;
import com.base.basesetup.repo.SummaryPaymentVoucherRepo;
import com.base.basesetup.repo.TaxInvoiceRepo;
import com.base.basesetup.repo.TdsCostInvoiceRepo;

@Service
public class TransactionServiceImpl implements TransactionService {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	TaxInvoiceRepo taxInvoiceRepo;

	@Autowired
	ChargerTaxInvoiceRepo chargerTaxInvoiceRepo;

	@Autowired
	GstTaxInvoiceRepo gstTaxInvoiceRepo;

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

	@Autowired
	CostInvoiceRepo costInvoiceRepo;

	@Autowired
	SummaryCostInvoiceRepo summaryCostInvoiceRepo;

	@Autowired
	TdsCostInvoiceRepo tdsCostInvoiceRepo;

	@Autowired
	ChargerCostInvoiceRepo chargerCostInvoiceRepo;

	@Autowired
	DebitNoteRepo debitNoteRepo;

	@Autowired
	ChargerDebitNoteRepo chargerDebitNoteRepo;

	@Autowired
	ParticularsDebitNoteRepo particularsDebitNoteRepo;

	@Autowired
	GstDebitNoteRepo gstDebitNoteRepo;

	@Autowired
	SummaryDebitNoteRepo summaryDebitNoteRepo;

	@Autowired
	GstSalesVoucherRepo gstSalesVoucherRepo;

	@Autowired
	ParticularsGstVoucherRepo particularsGstVoucherRepo;

	@Autowired
	SummaryGstVoucherRepo summaryGstVoucherRepo;

	@Autowired
	PaymentVoucherRepo paymentVoucherRepo;

	@Autowired
	SummaryPaymentVoucherRepo summaryPaymentVoucherRepo;

	@Autowired
	ParticularsPaymentVoucherRepo particularsPaymentVoucherRepo;

	@Autowired
	ArapDetailsRepo arapDetailsRepo;

	@Autowired
	ArapAdjustmentsRepo arapAdjustmentsRepo;

	@Autowired
	ReceiptReversalRepo receiptReversalRepo;

	@Autowired
	ReceiptInvoiceRepo receiptInvoiceRepo;

	@Autowired
	ReceiptOtherAccountRepo receiptOtherAccountRepo;

	@Autowired
	ReceiptSummaryRepo receiptSummaryRepo;

	@Autowired
	PaymentReversalRepo paymentReversalRepo;

	@Autowired
	PaymentInvoiceRepo paymentInvoiceRepo;

	@Autowired
	PaymentOtherAccountRepo paymentOtherAccountRepo;

	@Autowired
	PaymentSummaryRepo paymentSummaryRepo;

// TaxInvoice
	@Override
	public List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId) {
		List<TaxInvoiceVO> taxInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TaxInvoice BY OrgId: {}", orgId);
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
		taxInvoiceVO.setAmountInwords(taxInvoiceDTO.getAmountInwords());
		taxInvoiceVO.setBillingRemarks(taxInvoiceDTO.getBillingRemarks());
		taxInvoiceVO.setBillInvAmount(taxInvoiceDTO.getBillInvAmount());
		taxInvoiceVO.setBilllcChargeAmount(taxInvoiceDTO.getBilllcChargeAmount());
		taxInvoiceVO.setBillTaxAmount(taxInvoiceDTO.getBillTaxAmount());
		taxInvoiceVO.setLcChargeAmount(taxInvoiceDTO.getLcChargeAmount());
		taxInvoiceVO.setLcInvAmount(taxInvoiceDTO.getLcInvAmount());
		taxInvoiceVO.setLcRoundOffAmount(taxInvoiceDTO.getLcRoundOffAmount());
		taxInvoiceVO.setLcTaxableAmount(taxInvoiceDTO.getLcTaxableAmount());
		taxInvoiceVO.setLcTaxAmount(taxInvoiceDTO.getLcTaxAmount());
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

	// CostInvoice

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
	public CostInvoiceVO updateCreateCostInvoice(@Valid CostInvoiceDTO costInvoiceDTO) throws ApplicationException {
		CostInvoiceVO costInvoiceVO = new CostInvoiceVO();
		if (ObjectUtils.isNotEmpty(costInvoiceDTO.getId())) {
			costInvoiceVO = costInvoiceRepo.findById(costInvoiceDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid CostInvoice details"));
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

		List<ChargerCostInvoiceVO> chargerCostInvoiceVOs = new ArrayList<>();
		if (costInvoiceDTO.getChargerCostInvoiceDTO() != null) {
			for (ChargerCostInvoiceDTO chargerCostInvoiceDTO : costInvoiceDTO.getChargerCostInvoiceDTO()) {
				ChargerCostInvoiceVO chargerCostInvoiceVO;
				if (chargerCostInvoiceDTO.getId() != null && ObjectUtils.isNotEmpty(chargerCostInvoiceDTO.getId())) {
					chargerCostInvoiceVO = chargerCostInvoiceRepo.findById(chargerCostInvoiceDTO.getId())
							.orElse(new ChargerCostInvoiceVO());
				} else {
					chargerCostInvoiceVO = new ChargerCostInvoiceVO();
				}
				chargerCostInvoiceVO.setChargeName(chargerCostInvoiceDTO.getChargeName());
				chargerCostInvoiceVO.setChargeCode(chargerCostInvoiceDTO.getChargeCode());
				chargerCostInvoiceVO.setChargeLedger(chargerCostInvoiceDTO.getChargeLedger());
				chargerCostInvoiceVO.setGsac(chargerCostInvoiceDTO.getGsac());
				chargerCostInvoiceVO.setContType(chargerCostInvoiceDTO.getContType());
				chargerCostInvoiceVO.setCurrency(chargerCostInvoiceDTO.getCurrency());
				chargerCostInvoiceVO.setRate(chargerCostInvoiceDTO.getRate());
				chargerCostInvoiceVO.setGstPercentage(chargerCostInvoiceDTO.getGstPercentage());
				chargerCostInvoiceVO.setExRate(chargerCostInvoiceDTO.getExRate());
				chargerCostInvoiceVO.setFcAmount(chargerCostInvoiceDTO.getFcAmount());
				chargerCostInvoiceVO.setLcAmount(chargerCostInvoiceDTO.getLcAmount());
				chargerCostInvoiceVO.setBillAmount(chargerCostInvoiceDTO.getBillAmount());
				chargerCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
				chargerCostInvoiceVOs.add(chargerCostInvoiceVO);
			}
		}

		List<SummaryCostInvoiceVO> summaryCostInvoiceVOs = new ArrayList<>();
		if (costInvoiceDTO.getSummaryCostInvoiceDTO() != null) {
			for (SummaryCostInvoiceDTO summaryCostInvoiceDTO : costInvoiceDTO.getSummaryCostInvoiceDTO()) {
				SummaryCostInvoiceVO summaryCostInvoiceVO;
				if (summaryCostInvoiceDTO.getId() != null & ObjectUtils.isNotEmpty(summaryCostInvoiceDTO.getId())) {
					summaryCostInvoiceVO = summaryCostInvoiceRepo.findById(summaryCostInvoiceDTO.getId())
							.orElse(new SummaryCostInvoiceVO());
				} else {
					summaryCostInvoiceVO = new SummaryCostInvoiceVO();
				}
				summaryCostInvoiceVO.setBillCurrTotChargeAmt(summaryCostInvoiceDTO.getBillCurrTotChargeAmt());
				summaryCostInvoiceVO.setBillCurrActBillAmt(summaryCostInvoiceDTO.getBillCurrActBillAmt());
				summaryCostInvoiceVO.setBillCurrNetAmt(summaryCostInvoiceDTO.getBillCurrNetAmt());
				summaryCostInvoiceVO.setLcTotChargeAmt(summaryCostInvoiceDTO.getLcTotChargeAmt());
				summaryCostInvoiceVO.setLcActBillAmt(summaryCostInvoiceDTO.getLcActBillAmt());
				summaryCostInvoiceVO.setLcNetAmt(summaryCostInvoiceDTO.getLcNetAmt());
				summaryCostInvoiceVO.setRoundOff(summaryCostInvoiceDTO.getRoundOff());
				summaryCostInvoiceVO.setLcGstInputAmt(summaryCostInvoiceDTO.getLcGstInputAmt());
				summaryCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
				summaryCostInvoiceVOs.add(summaryCostInvoiceVO);
			}
		}

		List<TdsCostInvoiceVO> tdsCostInvoiceVOs = new ArrayList<>();
		if (costInvoiceDTO.getTdsCostInvoiceDTO() != null) {
			for (TdsCostInvoiceDTO tdsCostInvoiceDTO : costInvoiceDTO.getTdsCostInvoiceDTO()) {
				TdsCostInvoiceVO tdsCostInvoiceVO;
				if (tdsCostInvoiceDTO.getId() != null & ObjectUtils.isEmpty(tdsCostInvoiceDTO.getId())) {
					tdsCostInvoiceVO = tdsCostInvoiceRepo.findById(tdsCostInvoiceDTO.getId())
							.orElse(new TdsCostInvoiceVO());
				} else {
					tdsCostInvoiceVO = new TdsCostInvoiceVO();
				}
				tdsCostInvoiceVO.setTdsWh(tdsCostInvoiceDTO.getTdsWh());
				tdsCostInvoiceVO.setTdsWhPercent(tdsCostInvoiceDTO.getTdsWhPercent());
				tdsCostInvoiceVO.setSection(tdsCostInvoiceDTO.getSection());
				tdsCostInvoiceVO.setTotalTds(tdsCostInvoiceDTO.getTotalTds());
				tdsCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
				tdsCostInvoiceVOs.add(tdsCostInvoiceVO);
			}
		}

		getCostInvoiceVOFromCostInvoiceDTO(costInvoiceDTO, costInvoiceVO);
		costInvoiceVO.setChargerCostInvoiceVO(chargerCostInvoiceVOs);
		costInvoiceVO.setSummaryCostInvoiceVO(summaryCostInvoiceVOs);
		costInvoiceVO.setTdsCostInvoiceVO(tdsCostInvoiceVOs);
		return costInvoiceRepo.save(costInvoiceVO);
	}

	private void getCostInvoiceVOFromCostInvoiceDTO(@Valid CostInvoiceDTO costInvoiceDTO, CostInvoiceVO costInvoiceVO) {
//			// Finyr
//			int finyr = taxInvoiceRepo.findFinyr();
//			// DocId
//			String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
//			taxInvoiceVO.setDocId(taxInvoice);
//			taxInvoiceRepo.nextSeq();
//			// InvoiceNo
//			String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
//			taxInvoiceVO.setInvoiceNo(invoiceNo);
//			taxInvoiceRepo.nextSeqInvoice();
		costInvoiceVO.setMode(costInvoiceDTO.getMode());
		costInvoiceVO.setProduct(costInvoiceDTO.getProduct());
		costInvoiceVO.setPurVchNo(costInvoiceDTO.getPurVchNo());
		costInvoiceVO.setPurVchDt(costInvoiceDTO.getPurVchDt());
		costInvoiceVO.setCostInvoiceNo(costInvoiceDTO.getCostInvoiceNo());
		costInvoiceVO.setDate(costInvoiceDTO.getDate());
		costInvoiceVO.setSupplierBillNo(costInvoiceDTO.getSupplierBillNo());
		costInvoiceVO.setSuppliertType(costInvoiceDTO.getSuppliertType());
		costInvoiceVO.setSupplierCode(costInvoiceDTO.getSupplierCode());
		costInvoiceVO.setCreditDays(costInvoiceDTO.getCreditDays());
		costInvoiceVO.setDueDate(costInvoiceDTO.getDueDate());
		costInvoiceVO.setSupplierName(costInvoiceDTO.getSupplierName());
		costInvoiceVO.setCurrency(costInvoiceDTO.getCurrency());
		costInvoiceVO.setExRate(costInvoiceDTO.getExRate());
		costInvoiceVO.setSupplierGstIn(costInvoiceDTO.getSupplierGstIn());
		costInvoiceVO.setRemarks(costInvoiceDTO.getRemarks());
		costInvoiceVO.setAddress(costInvoiceDTO.getAddress());
		costInvoiceVO.setOtherInfo(costInvoiceDTO.getOtherInfo());
		costInvoiceVO.setShipperRefNo(costInvoiceDTO.getShipperRefNo());
		costInvoiceVO.setGstType(costInvoiceDTO.getGstType());
		costInvoiceVO.setPayment(costInvoiceDTO.getPayment());
		costInvoiceVO.setAccrualId(costInvoiceDTO.getAccrualId());
		costInvoiceVO.setUtrReference(costInvoiceDTO.getUtrReference());
		costInvoiceVO.setCostType(costInvoiceDTO.getCostType());
		costInvoiceVO.setJobStatus(costInvoiceDTO.getJobStatus());
		costInvoiceVO.setOrgId(costInvoiceDTO.getOrgId());
		costInvoiceVO.setActive(costInvoiceDTO.isActive());
		costInvoiceVO.setUpdatedBy(costInvoiceDTO.getUpdatedBy());
		costInvoiceVO.setCreatedBy(costInvoiceDTO.getCreatedBy());
	}

	@Override
	public List<CostInvoiceVO> getCostInvoiceByActive() {
		return costInvoiceRepo.findCostInvoiceByActive();
	}

//DebitNote

	@Override
	public List<DebitNoteVO> getAllDebitNoteByOrgId(Long orgId) {
		List<DebitNoteVO> debitNoteVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received DebitNote BY OrgId : {}", orgId);
			debitNoteVO = debitNoteRepo.getAllDebitNoteByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received DebitNote For All OrgId.");
			debitNoteVO = debitNoteRepo.findAll();
		}
		return debitNoteVO;
	}

	@Override
	public List<DebitNoteVO> getAllDebitNoteById(Long id) {
		List<DebitNoteVO> debitNoteVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received DebitNote BY Id : {}", id);
			debitNoteVO = debitNoteRepo.getAllDebitNoteById(id);
		} else {
			LOGGER.info("Successfully Received DebitNote For All Id.");
			debitNoteVO = debitNoteRepo.findAll();
		}
		return debitNoteVO;
	}

	@Override
	public DebitNoteVO updateCreateDebitNote(@Valid DebitNoteDTO debitNoteDTO) throws ApplicationException {
		DebitNoteVO debitNoteVO = new DebitNoteVO();
		if (ObjectUtils.isNotEmpty(debitNoteDTO.getId())) {
			debitNoteVO = debitNoteRepo.findById(debitNoteDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid DebitNote details"));
		}
//					else {
//						if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//							throw new ApplicationException("The given doc id already exists.");
//						}
//						if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//							throw new ApplicationException("The given invoice number already exists.");
//						}
//					}
//					if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//						if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//								taxInvoiceVO.getId())) {
//							throw new ApplicationException("The given doc id already exists.");
//						}
//						if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//								taxInvoiceVO.getId())) {
//							throw new ApplicationException("The given invoice number already exists.");
//						}
//					}

		List<ChargerDebitNoteVO> chargerDebitNoteVOs = new ArrayList<>();
		if (debitNoteDTO.getChargerDebitNoteDTO() != null) {
			for (ChargerDebitNoteDTO chargerDebitNoteDTO : debitNoteDTO.getChargerDebitNoteDTO()) {
				ChargerDebitNoteVO chargerDebitNoteVO;
				if (chargerDebitNoteDTO.getId() != null && ObjectUtils.isNotEmpty(chargerDebitNoteDTO.getId())) {
					chargerDebitNoteVO = chargerDebitNoteRepo.findById(chargerDebitNoteDTO.getId())
							.orElse(new ChargerDebitNoteVO());
				} else {
					chargerDebitNoteVO = new ChargerDebitNoteVO();
				}
				chargerDebitNoteVO.setGChargeCode(chargerDebitNoteDTO.getGChargeCode());
				chargerDebitNoteVO.setGsac(chargerDebitNoteDTO.getGsac());
				chargerDebitNoteVO.setChargeName(chargerDebitNoteDTO.getChargeName());
				chargerDebitNoteVO.setApplyOn(chargerDebitNoteDTO.getApplyOn());
				chargerDebitNoteVO.setTax(chargerDebitNoteDTO.getTax());
				chargerDebitNoteVO.setCurrency(chargerDebitNoteDTO.getCurrency());
				chargerDebitNoteVO.setExRate(chargerDebitNoteDTO.getExRate());
				chargerDebitNoteVO.setRate(chargerDebitNoteDTO.getRate());
				chargerDebitNoteVO.setExempted(chargerDebitNoteDTO.getExempted());
				chargerDebitNoteVO.setFcAmount(chargerDebitNoteDTO.getFcAmount());
				chargerDebitNoteVO.setLcAmount(chargerDebitNoteDTO.getLcAmount());
				chargerDebitNoteVO.setTaxablePercentage(chargerDebitNoteDTO.getTaxablePercentage());
				chargerDebitNoteVO.setTlcAmount(chargerDebitNoteDTO.getTlcAmount());
				chargerDebitNoteVO.setBillAmount(chargerDebitNoteDTO.getBillAmount());
				chargerDebitNoteVO.setGstPercentage(chargerDebitNoteDTO.getGstPercentage());
				chargerDebitNoteVO.setGst(chargerDebitNoteDTO.getGst());
				chargerDebitNoteVO.setDebitNoteVO(debitNoteVO);
				chargerDebitNoteVOs.add(chargerDebitNoteVO);
			}
		}

		List<SummaryDebitNoteVO> summaryDebitNoteVOs = new ArrayList<>();
		if (debitNoteDTO.getSummaryDebitNoteDTO() != null) {
			for (SummaryDebitNoteDTO summaryDebitNoteDTO : debitNoteDTO.getSummaryDebitNoteDTO()) {
				SummaryDebitNoteVO summaryDebitNoteVO;
				if (summaryDebitNoteDTO.getId() != null & ObjectUtils.isNotEmpty(summaryDebitNoteDTO.getId())) {
					summaryDebitNoteVO = summaryDebitNoteRepo.findById(summaryDebitNoteDTO.getId())
							.orElse(new SummaryDebitNoteVO());
				} else {
					summaryDebitNoteVO = new SummaryDebitNoteVO();
				}
				summaryDebitNoteVO.setBillCurrTotChargeAmount(summaryDebitNoteDTO.getBillCurrTotChargeAmount());
				summaryDebitNoteVO.setBillCurrTotGrossAmount(summaryDebitNoteDTO.getBillCurrTotGrossAmount());
				summaryDebitNoteVO.setBillCurrNetAmount(summaryDebitNoteDTO.getBillCurrNetAmount());
				summaryDebitNoteVO.setAmountInWords(summaryDebitNoteDTO.getAmountInWords());
				summaryDebitNoteVO.setRoundOff(summaryDebitNoteDTO.getRoundOff());
				summaryDebitNoteVO.setLctotChargeAmount(summaryDebitNoteDTO.getLctotChargeAmount());
				summaryDebitNoteVO.setLctotGrossAmount(summaryDebitNoteDTO.getLctotGrossAmount());
				summaryDebitNoteVO.setLcNetAmount(summaryDebitNoteDTO.getLcNetAmount());
				summaryDebitNoteVO.setDebitNoteVO(debitNoteVO);
				summaryDebitNoteVOs.add(summaryDebitNoteVO);
			}
		}

		List<GstDebitNoteVO> gstDebitNoteVOs = new ArrayList<>();
		if (debitNoteDTO.getGstDebitNoteDTO() != null) {
			for (GstDebitNoteDTO gstDebitNoteDTO : debitNoteDTO.getGstDebitNoteDTO()) {
				GstDebitNoteVO gstDebitNoteVO;
				if (gstDebitNoteDTO.getId() != null & ObjectUtils.isNotEmpty(gstDebitNoteDTO.getId())) {
					gstDebitNoteVO = gstDebitNoteRepo.findById(gstDebitNoteDTO.getId()).orElse(new GstDebitNoteVO());
				} else {
					gstDebitNoteVO = new GstDebitNoteVO();
				}
				gstDebitNoteVO.setChargeAccount(gstDebitNoteDTO.getChargeAccount());
				gstDebitNoteVO.setSubLedgerCode(gstDebitNoteDTO.getSubLedgerCode());
				gstDebitNoteVO.setDbBillAmount(gstDebitNoteDTO.getDbBillAmount());
				gstDebitNoteVO.setCrBillAmount(gstDebitNoteDTO.getCrBillAmount());
				gstDebitNoteVO.setDblcAmt(gstDebitNoteDTO.getDblcAmt());
				gstDebitNoteVO.setCrlcamt(gstDebitNoteDTO.getCrlcamt());
				gstDebitNoteVO.setDebitNoteVO(debitNoteVO);
				gstDebitNoteVOs.add(gstDebitNoteVO);
			}
		}

		List<ParticularsDebitNoteVO> particularsDebitNoteVOs = new ArrayList<>();
		if (debitNoteDTO.getParticularsDebitNoteDTO() != null) {
			for (ParticularsDebitNoteDTO particularsDebitNoteDTO : debitNoteDTO.getParticularsDebitNoteDTO()) {
				ParticularsDebitNoteVO particularsDebitNoteVO;
				if (particularsDebitNoteDTO.getId() != null & ObjectUtils.isEmpty(particularsDebitNoteDTO.getId())) {
					particularsDebitNoteVO = particularsDebitNoteRepo.findById(particularsDebitNoteDTO.getId())
							.orElse(new ParticularsDebitNoteVO());
				} else {
					particularsDebitNoteVO = new ParticularsDebitNoteVO();
				}
				particularsDebitNoteVO.setTds(particularsDebitNoteDTO.getTds());
				particularsDebitNoteVO.setTdsPercent(particularsDebitNoteDTO.getTdsPercent());
				particularsDebitNoteVO.setSection(particularsDebitNoteDTO.getSection());
				particularsDebitNoteVO.setTotalTdsAmount(particularsDebitNoteDTO.getTotalTdsAmount());
				particularsDebitNoteVO.setDebitNoteVO(debitNoteVO);
				particularsDebitNoteVOs.add(particularsDebitNoteVO);
			}
		}

		getDebitNoteVOFromDebitNoteDTO(debitNoteDTO, debitNoteVO);
		debitNoteVO.setChargerDebitNoteVO(chargerDebitNoteVOs);
		debitNoteVO.setParticularsDebitNoteVO(particularsDebitNoteVOs);
		debitNoteVO.setSummaryDebitNoteVO(summaryDebitNoteVOs);
		debitNoteVO.setGstDebitNoteVO(gstDebitNoteVOs);
		return debitNoteRepo.save(debitNoteVO);
	}

	private void getDebitNoteVOFromDebitNoteDTO(@Valid DebitNoteDTO debitNoteDTO, DebitNoteVO debitNoteVO) {
//					// Finyr
//					int finyr = taxInvoiceRepo.findFinyr();
//					// DocId
//					String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
//					taxInvoiceVO.setDocId(taxInvoice);
//					taxInvoiceRepo.nextSeq();
//					// InvoiceNo
//					String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
//					taxInvoiceVO.setInvoiceNo(invoiceNo);
//					taxInvoiceRepo.nextSeqInvoice();
		debitNoteVO.setDocNo(debitNoteDTO.getDocNo());
		debitNoteVO.setSubType(debitNoteDTO.getSubType());
		debitNoteVO.setProduct(debitNoteDTO.getProduct());
		debitNoteVO.setDocDate(debitNoteDTO.getDocDate());
		debitNoteVO.setPartyType(debitNoteDTO.getPartyType());
		debitNoteVO.setPartyCode(debitNoteDTO.getPartyCode());
		debitNoteVO.setPartyName(debitNoteDTO.getPartyName());
		debitNoteVO.setAddress(debitNoteDTO.getAddress());
		debitNoteVO.setOtherInfo(debitNoteDTO.getOtherInfo());
		debitNoteVO.setStatus(debitNoteDTO.getStatus());
		debitNoteVO.setOriginBill(debitNoteDTO.getOriginBill());
		debitNoteVO.setVchNo(debitNoteDTO.getVchNo());
		debitNoteVO.setVchDate(debitNoteDTO.getVchDate());
		debitNoteVO.setCreditDays(debitNoteDTO.getCreditDays());
		debitNoteVO.setSupplierRefNo(debitNoteDTO.getSupplierRefNo());
		debitNoteVO.setDate(debitNoteDTO.getDate());
		debitNoteVO.setDueDate(debitNoteDTO.getDueDate());
		debitNoteVO.setExRate(debitNoteDTO.getExRate());
		debitNoteVO.setTaxExempt(debitNoteDTO.isTaxExempt());
		debitNoteVO.setCurrency(debitNoteDTO.getCurrency());
		debitNoteVO.setRemarks(debitNoteDTO.getRemarks());
		debitNoteVO.setShipperRefNo(debitNoteDTO.getShipperRefNo());
		debitNoteVO.setGstType(debitNoteDTO.getGstType());
		debitNoteVO.setMode(debitNoteDTO.getMode());
		debitNoteVO.setOrgId(debitNoteDTO.getOrgId());
		debitNoteVO.setActive(debitNoteDTO.isActive());
		debitNoteVO.setUpdatedBy(debitNoteDTO.getUpdatedBy());
		debitNoteVO.setCreatedBy(debitNoteDTO.getCreatedBy());
	}

	@Override
	public List<DebitNoteVO> getDebitNoteByActive() {
		return debitNoteRepo.findDebitNoteByActive();
	}

//	GstSalesVoucher

	@Override
	public List<GstSalesVoucherVO> getAllGstSalesVoucherByOrgId(Long orgId) {
		List<GstSalesVoucherVO> gstSalesVoucherVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received GstSalesVoucher BY OrgId : {}", orgId);
			gstSalesVoucherVO = gstSalesVoucherRepo.getAllGstSalesVoucherByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received GstSalesVoucher For All OrgId.");
			gstSalesVoucherVO = gstSalesVoucherRepo.findAll();
		}
		return gstSalesVoucherVO;
	}

	@Override
	public List<GstSalesVoucherVO> getAllGstSalesVoucherById(Long id) {
		List<GstSalesVoucherVO> gstSalesVoucherVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received GstSalesVoucher BY Id : {}", id);
			gstSalesVoucherVO = gstSalesVoucherRepo.getAllGstSalesVoucherById(id);
		} else {
			LOGGER.info("Successfully Received GstSalesVoucher For All Id.");
			gstSalesVoucherVO = gstSalesVoucherRepo.findAll();
		}
		return gstSalesVoucherVO;
	}

	@Override
	public GstSalesVoucherVO updateCreateGstSalesVoucher(@Valid GstSalesVoucherDTO gstSalesVoucherDTO)
			throws ApplicationException {
		GstSalesVoucherVO gstSalesVoucherVO = new GstSalesVoucherVO();
		if (ObjectUtils.isNotEmpty(gstSalesVoucherDTO.getId())) {
			gstSalesVoucherVO = gstSalesVoucherRepo.findById(gstSalesVoucherDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GstSalesVoucher details"));
		}
//					else {
//						if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//							throw new ApplicationException("The given doc id already exists.");
//						}
//						if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//							throw new ApplicationException("The given invoice number already exists.");
//						}
//					}
//					if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//						if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//								taxInvoiceVO.getId())) {
//							throw new ApplicationException("The given doc id already exists.");
//						}
//						if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//								taxInvoiceVO.getId())) {
//							throw new ApplicationException("The given invoice number already exists.");
//						}
//					}

		List<SummaryGstVoucherVO> summaryGstVoucherVOs = new ArrayList<>();
		if (gstSalesVoucherDTO.getSummaryGstVoucherDTO() != null) {
			for (SummaryGstVoucherDTO summaryGstVoucherDTO : gstSalesVoucherDTO.getSummaryGstVoucherDTO()) {
				SummaryGstVoucherVO summaryGstVoucherVO;
				if (summaryGstVoucherDTO.getId() != null & ObjectUtils.isNotEmpty(summaryGstVoucherDTO.getId())) {
					summaryGstVoucherVO = summaryGstVoucherRepo.findById(summaryGstVoucherDTO.getId())
							.orElse(new SummaryGstVoucherVO());
				} else {
					summaryGstVoucherVO = new SummaryGstVoucherVO();
				}
				summaryGstVoucherVO.setTotalDebitAmount(summaryGstVoucherDTO.getTotalDebitAmount());
				summaryGstVoucherVO.setTotalCreditAmount(summaryGstVoucherDTO.getTotalCreditAmount());
				summaryGstVoucherVO.setStTaxAmount(summaryGstVoucherDTO.getStTaxAmount());
				summaryGstVoucherVO.setBasAmount(summaryGstVoucherDTO.getBasAmount());
				summaryGstVoucherVO.setBssAmount(summaryGstVoucherDTO.getBssAmount());
				summaryGstVoucherVO.setChaAmount(summaryGstVoucherDTO.getChaAmount());
				summaryGstVoucherVO.setGstSalesVoucherVO(gstSalesVoucherVO);
				summaryGstVoucherVOs.add(summaryGstVoucherVO);
			}
		}

		List<ParticularsGstVoucherVO> particularsGstVoucherVOs = new ArrayList<>();
		if (gstSalesVoucherDTO.getParticularsGstVoucherDTO() != null) {
			for (ParticularsGstVoucherDTO particularsGstVoucherDTO : gstSalesVoucherDTO.getParticularsGstVoucherDTO()) {
				ParticularsGstVoucherVO particularsGstVoucherVO;
				if (particularsGstVoucherDTO.getId() != null & ObjectUtils.isEmpty(particularsGstVoucherDTO.getId())) {
					particularsGstVoucherVO = particularsGstVoucherRepo.findById(particularsGstVoucherDTO.getId())
							.orElse(new ParticularsGstVoucherVO());
				} else {
					particularsGstVoucherVO = new ParticularsGstVoucherVO();
				}
				particularsGstVoucherVO.setAccountName(particularsGstVoucherDTO.getAccountName());
				particularsGstVoucherVO.setSubLedgerCode(particularsGstVoucherDTO.getSubLedgerCode());
				particularsGstVoucherVO.setSubLedgerName(particularsGstVoucherDTO.getSubLedgerName());
				particularsGstVoucherVO.setDebit(particularsGstVoucherDTO.getDebit());
				particularsGstVoucherVO.setBaseDbAmount(particularsGstVoucherDTO.getBaseDbAmount());
				particularsGstVoucherVO.setCredit(particularsGstVoucherDTO.getCredit());
				particularsGstVoucherVO.setBaseCrAmount(particularsGstVoucherDTO.getBaseCrAmount());
				particularsGstVoucherVO.setNarration(particularsGstVoucherDTO.getNarration());
				particularsGstVoucherVO.setGstSalesVoucherVO(gstSalesVoucherVO);
				particularsGstVoucherVOs.add(particularsGstVoucherVO);
			}
		}

		getGstSalesVoucherVOFromGstSalesVoucherDTO(gstSalesVoucherDTO, gstSalesVoucherVO);
		gstSalesVoucherVO.setParticularsGstVoucherVO(particularsGstVoucherVOs);
		gstSalesVoucherVO.setSummaryGstVoucherVO(summaryGstVoucherVOs);
		return gstSalesVoucherRepo.save(gstSalesVoucherVO);
	}

	private void getGstSalesVoucherVOFromGstSalesVoucherDTO(@Valid GstSalesVoucherDTO gstSalesVoucherDTO,
			GstSalesVoucherVO gstSalesVoucherVO) {
		// Finyr
		int finyr = gstSalesVoucherRepo.findFinyr();
		// DocId
		String salesVoucher = "SV" + finyr + gstSalesVoucherRepo.findDocId();
		gstSalesVoucherVO.setDocId(salesVoucher);
		gstSalesVoucherRepo.nextSeq();
		// InvoiceNo
		gstSalesVoucherVO.setDocId(gstSalesVoucherDTO.getDocId());
		gstSalesVoucherVO.setReferenceNo(gstSalesVoucherDTO.getReferenceNo());
		gstSalesVoucherVO.setCurrency(gstSalesVoucherDTO.getCurrency());
		gstSalesVoucherVO.setDocDate(gstSalesVoucherDTO.getDocDate());
		gstSalesVoucherVO.setReferenceDate(gstSalesVoucherDTO.getReferenceDate());
		gstSalesVoucherVO.setExRate(gstSalesVoucherDTO.getExRate());
		gstSalesVoucherVO.setRemarks(gstSalesVoucherDTO.getRemarks());
	}

	@Override
	public List<GstSalesVoucherVO> getGstSalesVoucherByActive() {
		return gstSalesVoucherRepo.findGstSalesVoucherByActive();
	}

//	PaymentVoucher

	@Override
	public List<PaymentVoucherVO> getAllPaymentVoucherByOrgId(Long orgId) {
		List<PaymentVoucherVO> paymentVoucherVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received PaymentVoucher BY OrgId : {}", orgId);
			paymentVoucherVO = paymentVoucherRepo.getAllPaymentVoucherByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received PaymentVoucher For All OrgId.");
			paymentVoucherVO = paymentVoucherRepo.findAll();
		}
		return paymentVoucherVO;
	}

	@Override
	public List<PaymentVoucherVO> getAllPaymentVoucherById(Long id) {
		List<PaymentVoucherVO> paymentVoucherVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received PaymentVoucher BY Id : {}", id);
			paymentVoucherVO = paymentVoucherRepo.getAllPaymentVoucherById(id);
		} else {
			LOGGER.info("Successfully Received PaymentVoucher For All Id.");
			paymentVoucherVO = paymentVoucherRepo.findAll();
		}
		return paymentVoucherVO;
	}

	@Override
	public PaymentVoucherVO updateCreatePaymentVoucher(@Valid PaymentVoucherDTO paymentVoucherDTO)
			throws ApplicationException {
		PaymentVoucherVO paymentVoucherVO = new PaymentVoucherVO();
		if (ObjectUtils.isNotEmpty(paymentVoucherDTO.getId())) {
			paymentVoucherVO = paymentVoucherRepo.findById(paymentVoucherDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid PaymentVoucher details"));
		}
//					else {
//						if (taxInvoiceRepo.existsByDocIdAndOrgId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId())) {
//							throw new ApplicationException("The given doc id already exists.");
//						}
//						if (taxInvoiceRepo.existsByInvoiceNoAndOrgId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId())) {
//							throw new ApplicationException("The given invoice number already exists.");
//						}
//					}
//					if (ObjectUtils.isNotEmpty(taxInvoiceVO.getId())) {
//						if (taxInvoiceRepo.existsByDocIdAndOrgIdAndId(taxInvoiceVO.getDocId(), taxInvoiceVO.getOrgId(),
//								taxInvoiceVO.getId())) {
//							throw new ApplicationException("The given doc id already exists.");
//						}
//						if (taxInvoiceRepo.existsByInvoiceNoAndOrgIdAndId(taxInvoiceVO.getInvoiceNo(), taxInvoiceVO.getOrgId(),
//								taxInvoiceVO.getId())) {
//							throw new ApplicationException("The given invoice number already exists.");
//						}
//					}

		List<SummaryPaymentVoucherVO> summaryPaymentVoucherVOs = new ArrayList<>();
		if (paymentVoucherDTO.getSummaryPaymentVoucherDTO() != null) {
			for (SummaryPaymentVoucherDTO summaryPaymentVoucherDTO : paymentVoucherDTO.getSummaryPaymentVoucherDTO()) {
				SummaryPaymentVoucherVO summaryPaymentVoucherVO;
				if (summaryPaymentVoucherDTO.getId() != null
						& ObjectUtils.isNotEmpty(summaryPaymentVoucherDTO.getId())) {
					summaryPaymentVoucherVO = summaryPaymentVoucherRepo.findById(summaryPaymentVoucherDTO.getId())
							.orElse(new SummaryPaymentVoucherVO());
				} else {
					summaryPaymentVoucherVO = new SummaryPaymentVoucherVO();
				}
				summaryPaymentVoucherVO.setTotalDebitAmount(summaryPaymentVoucherDTO.getTotalDebitAmount());
				summaryPaymentVoucherVO.setTotalCreditAmount(summaryPaymentVoucherDTO.getTotalCreditAmount());
				summaryPaymentVoucherVO.setPaymentVoucherVO(paymentVoucherVO);
				summaryPaymentVoucherVOs.add(summaryPaymentVoucherVO);
			}
		}

		List<ParticularsPaymentVoucherVO> particularsPaymentVoucherVOs = new ArrayList<>();
		if (paymentVoucherDTO.getParticularsPaymentVoucherDTO() != null) {
			for (ParticularsPaymentVoucherDTO particularsPaymentVoucherDTO : paymentVoucherDTO
					.getParticularsPaymentVoucherDTO()) {
				ParticularsPaymentVoucherVO particularsPaymentVoucherVO;
				if (particularsPaymentVoucherDTO.getId() != null
						& ObjectUtils.isEmpty(particularsPaymentVoucherDTO.getId())) {
					particularsPaymentVoucherVO = particularsPaymentVoucherRepo
							.findById(particularsPaymentVoucherDTO.getId()).orElse(new ParticularsPaymentVoucherVO());
				} else {
					particularsPaymentVoucherVO = new ParticularsPaymentVoucherVO();
				}
				particularsPaymentVoucherVO.setAccountName(particularsPaymentVoucherDTO.getAccountName());
				particularsPaymentVoucherVO.setSubLedgerName(particularsPaymentVoucherDTO.getSubLedgerCode());
				particularsPaymentVoucherVO.setSubLedgerName(particularsPaymentVoucherDTO.getSubLedgerName());
				particularsPaymentVoucherVO.setDebit(particularsPaymentVoucherDTO.getDebit());
				particularsPaymentVoucherVO.setCredit(particularsPaymentVoucherDTO.getCredit());
				particularsPaymentVoucherVO.setNarration(particularsPaymentVoucherDTO.getNarration());
				particularsPaymentVoucherVO.setPaymentVoucherVO(paymentVoucherVO);
				particularsPaymentVoucherVOs.add(particularsPaymentVoucherVO);
			}
		}

		getPaymentVoucherVOFromPaymentVoucherDTO(paymentVoucherDTO, paymentVoucherVO);
		paymentVoucherVO.setParticularsPaymentVoucherVO(particularsPaymentVoucherVOs);
		paymentVoucherVO.setSummaryPaymentVoucherVO(summaryPaymentVoucherVOs);
		return paymentVoucherRepo.save(paymentVoucherVO);
	}

	private void getPaymentVoucherVOFromPaymentVoucherDTO(@Valid PaymentVoucherDTO paymentVoucherDTO,
			PaymentVoucherVO paymentVoucherVO) {
//		// Finyr
		int finyr = paymentVoucherRepo.findFinyr();
		// DocId
		String paymentVoucher = "PV" + finyr + paymentVoucherRepo.findDocId();
		paymentVoucherVO.setDocid(paymentVoucher);
		paymentVoucherRepo.nextSeq();
		paymentVoucherVO.setDocid(paymentVoucherDTO.getDocid());
		paymentVoucherVO.setVehicleSubType(paymentVoucherDTO.getVehicleSubType());
		paymentVoucherVO.setReferenceNo(paymentVoucherDTO.getReferenceNo());
		paymentVoucherVO.setCurrency(paymentVoucherDTO.getCurrency());
		paymentVoucherVO.setDocDate(paymentVoucherDTO.getDocDate());
		paymentVoucherVO.setReferenceDate(paymentVoucherDTO.getReferenceDate());
		paymentVoucherVO.setExRate(paymentVoucherDTO.getExRate());
		paymentVoucherVO.setRemarks(paymentVoucherDTO.getRemarks());
	}

	@Override

	public List<PaymentVoucherVO> getPaymentVoucherByActive() {
		return paymentVoucherRepo.findPaymentVoucherByActive();
	}

//	ArapDetails

	@Override
	public List<ArapDetailsVO> getAllArapDetailsByOrgId(Long orgId) {
		List<ArapDetailsVO> arapDetailsVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ArapDetails BY OrgId : {}", orgId);
			arapDetailsVO = arapDetailsRepo.getAllArapDetailsByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ArapDetails For All OrgId.");
			arapDetailsVO = arapDetailsRepo.findAll();
		}
		return arapDetailsVO;
	}

	@Override
	public List<ArapDetailsVO> getAllArapDetailsById(Long id) {
		List<ArapDetailsVO> arapDetailsVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ArapDetails BY Id : {}", id);
			arapDetailsVO = arapDetailsRepo.getAllArapDetailsById(id);
		} else {
			LOGGER.info("Successfully Received ArapDetails For All Id.");
			arapDetailsVO = arapDetailsRepo.findAll();
		}
		return arapDetailsVO;
	}

	@Override
	public ArapDetailsVO updateCreateArapDetails(@Valid ArapDetailsDTO arapDetailsDTO) throws ApplicationException {
		ArapDetailsVO arapDetailsVO = new ArapDetailsVO();
		if (ObjectUtils.isNotEmpty(arapDetailsDTO.getId())) {
			arapDetailsVO = arapDetailsRepo.findById(arapDetailsDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ARAP Details details"));
		}
		getArapDetailsVOFromArapDetailsDTO(arapDetailsDTO, arapDetailsVO);
		return arapDetailsRepo.save(arapDetailsVO);
	}

	private void getArapDetailsVOFromArapDetailsDTO(@Valid ArapDetailsDTO arapDetailsDTO, ArapDetailsVO arapDetailsVO) {
//		// Finyr
//		int finyr = paymentVoucherRepo.findFinyr();
//		// DocId
//		String paymentVoucher = "PV" + finyr + paymentVoucherRepo.findDocId();
//		paymentVoucherRepo.setDocId(paymentVoucher);
//		paymentVoucherRepo.nextSeq();
		arapDetailsVO.setBranch(arapDetailsDTO.getBranch());
		arapDetailsVO.setFinyr(arapDetailsDTO.getFinyr());
		arapDetailsVO.setSourceTransid(arapDetailsDTO.getSourceTransid());
		arapDetailsVO.setDocId(arapDetailsDTO.getDocId());
		arapDetailsVO.setRefNo(arapDetailsDTO.getRefNo());
		arapDetailsVO.setAccountName(arapDetailsDTO.getAccountName());
		arapDetailsVO.setCurrency(arapDetailsDTO.getCurrency());
		arapDetailsVO.setAccountCurrency(arapDetailsDTO.getAccountCurrency());
		arapDetailsVO.setAccount(arapDetailsDTO.getAccount());
		arapDetailsVO.setExRate(arapDetailsDTO.getExRate());
		arapDetailsVO.setAmount(arapDetailsDTO.getAmount());
		arapDetailsVO.setBaseAmount(arapDetailsDTO.getBaseAmount());
		arapDetailsVO.setNativeAmount(arapDetailsDTO.getNativeAmount());
		arapDetailsVO.setMno(arapDetailsDTO.getMno());
		arapDetailsVO.setChargableAmount(arapDetailsDTO.getChargableAmount());
		arapDetailsVO.setGstFlag(arapDetailsDTO.getGstFlag());
		arapDetailsVO.setDocTypeCode(arapDetailsDTO.getDocTypeCode());
		arapDetailsVO.setSubTypeCode(arapDetailsDTO.getSubTypeCode());
		arapDetailsVO.setSubLedgerDivision(arapDetailsDTO.getSubLedgerDivision());
		arapDetailsVO.setDocDate(arapDetailsDTO.getDocDate());
		arapDetailsVO.setSuppRefNo(arapDetailsDTO.getSuppRefNo());
		arapDetailsVO.setRefDate(arapDetailsDTO.getRefDate());
		arapDetailsVO.setSuppRefDate(arapDetailsDTO.getSuppRefDate());
		arapDetailsVO.setSubLedgerCode(arapDetailsDTO.getSubLedgerCode());
		arapDetailsVO.setCreditDays(arapDetailsDTO.getCreditDays());
		arapDetailsVO.setDueDate(arapDetailsDTO.getDueDate());
		arapDetailsVO.setTdsAmount(arapDetailsDTO.getTdsAmount());
		arapDetailsVO.setHno(arapDetailsDTO.getHno());
		arapDetailsVO.setOrgId(arapDetailsDTO.getOrgId());
		arapDetailsVO.setActive(arapDetailsDTO.isActive());
		arapDetailsVO.setCreatedBy(arapDetailsDTO.getCreatedBy());
		arapDetailsVO.setUpdatedBy(arapDetailsDTO.getUpdatedBy());
	}

	@Override
	public List<ArapDetailsVO> getArapDetailsByActive() {
		return arapDetailsRepo.findArapDetailsByActive();
	}

//	ArapAdjustments

	@Override
	public List<ArapAdjustmentsVO> getAllArapAdjustmentsByOrgId(Long orgId) {
		List<ArapAdjustmentsVO> arapAdjustmentsVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ArapAdjustments BY OrgId : {}", orgId);
			arapAdjustmentsVO = arapAdjustmentsRepo.getAllArapAdjustmentsByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ArapAdjustments For All OrgId.");
			arapAdjustmentsVO = arapAdjustmentsRepo.findAll();
		}
		return arapAdjustmentsVO;
	}

	@Override
	public List<ArapAdjustmentsVO> getAllArapAdjustmentsById(Long id) {
		List<ArapAdjustmentsVO> arapAdjustmentsVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ArapAdjustments BY Id : {}", id);
			arapAdjustmentsVO = arapAdjustmentsRepo.getAllArapAdjustmentsById(id);
		} else {
			LOGGER.info("Successfully Received ArapAdjustments For All Id.");
			arapAdjustmentsVO = arapAdjustmentsRepo.findAll();
		}
		return arapAdjustmentsVO;
	}

	@Override
	public ArapAdjustmentsVO updateCreateArapAdjustments(@Valid ArapAdjustmentsDTO arapAdjustmentsDTO)
			throws ApplicationException {
		ArapAdjustmentsVO arapAdjustmentsVO = new ArapAdjustmentsVO();
		if (ObjectUtils.isNotEmpty(arapAdjustmentsDTO.getId())) {
			arapAdjustmentsVO = arapAdjustmentsRepo.findById(arapAdjustmentsDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ARAP adjustments details"));
		}

		getArapAdjustmentsVOFromArapAdjustmentsDTO(arapAdjustmentsDTO, arapAdjustmentsVO);
		return arapAdjustmentsRepo.save(arapAdjustmentsVO);
	}

	private void getArapAdjustmentsVOFromArapAdjustmentsDTO(@Valid ArapAdjustmentsDTO arapAdjustmentsDTO,
			ArapAdjustmentsVO arapAdjustmentsVO) {
//		// Finyr
//		int finyr = paymentVoucherRepo.findFinyr();
//		// DocId
//		String paymentVoucher = "PV" + finyr + paymentVoucherRepo.findDocId();
//		paymentVoucherRepo.setDocId(paymentVoucher);
//		paymentVoucherRepo.nextSeq();
		arapAdjustmentsVO.setBranch(arapAdjustmentsDTO.getBranch());
		arapAdjustmentsVO.setFinyr(arapAdjustmentsDTO.getFinyr());
		arapAdjustmentsVO.setSourceTransId(arapAdjustmentsDTO.getSourceTransId());
		arapAdjustmentsVO.setDocId(arapAdjustmentsDTO.getDocId());
		arapAdjustmentsVO.setRefNo(arapAdjustmentsDTO.getRefNo());
		arapAdjustmentsVO.setAccountName(arapAdjustmentsDTO.getAccountName());
		arapAdjustmentsVO.setCurrency(arapAdjustmentsDTO.getCurrency());
		arapAdjustmentsVO.setAccountCurrency(arapAdjustmentsDTO.getAccountCurrency());
		arapAdjustmentsVO.setExRate(arapAdjustmentsDTO.getExRate());
		arapAdjustmentsVO.setAmount(arapAdjustmentsDTO.getAmount());
		arapAdjustmentsVO.setBaseAmount(arapAdjustmentsDTO.getBaseAmount());
		arapAdjustmentsVO.setNativeAmount(arapAdjustmentsDTO.getNativeAmount());
		arapAdjustmentsVO.setOffDocId(arapAdjustmentsDTO.getOffDocId());
		arapAdjustmentsVO.setVoucherType(arapAdjustmentsDTO.getVoucherType());
		arapAdjustmentsVO.setSubTypeCode(arapAdjustmentsDTO.getSubTypeCode());
		arapAdjustmentsVO.setDocDate(arapAdjustmentsDTO.getDocDate());
		arapAdjustmentsVO.setRefDate(arapAdjustmentsDTO.getRefDate());
		arapAdjustmentsVO.setSubLedgerCode(arapAdjustmentsDTO.getSubLedgerCode());
		arapAdjustmentsVO.setCreditDays(arapAdjustmentsDTO.getCreditDays());
		arapAdjustmentsVO.setDueDate(arapAdjustmentsDTO.getDueDate());
		arapAdjustmentsVO.setAexRate(arapAdjustmentsDTO.getAexRate());
		arapAdjustmentsVO.setOrgId(arapAdjustmentsDTO.getOrgId());
		arapAdjustmentsVO.setActive(arapAdjustmentsDTO.isActive());
		arapAdjustmentsVO.setCreatedBy(arapAdjustmentsDTO.getCreatedBy());
		arapAdjustmentsVO.setUpdatedBy(arapAdjustmentsDTO.getUpdatedBy());
	}

	@Override
	public List<ArapAdjustmentsVO> getArapAdjustmentsByActive() {
		return arapAdjustmentsRepo.findArapAdjustmentsByActive();
	}

	// ReceiptReversal

	@Override
	public List<ReceiptReversalVO> getAllReceiptReversalByOrgId(Long orgId) {
		List<ReceiptReversalVO> receiptReversalVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ReceiptReversal BY OrgId : {}", orgId);
			receiptReversalVO = receiptReversalRepo.getAllReceiptReversalByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ReceiptReversal For All OrgId.");
			receiptReversalVO = receiptReversalRepo.findAll();
		}
		return receiptReversalVO;
	}

	@Override
	public List<ReceiptReversalVO> getAllReceiptReversalById(Long id) {
		List<ReceiptReversalVO> receiptReversalVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ReceiptReversal BY Id : {}", id);
			receiptReversalVO = receiptReversalRepo.getAllReceiptReversalById(id);
		} else {
			LOGGER.info("Successfully Received ReceiptReversal For All Id.");
			receiptReversalVO = receiptReversalRepo.findAll();
		}
		return receiptReversalVO;
	}

	@Override
	public ReceiptReversalVO updateCreateReceiptReversal(@Valid ReceiptReversalDTO receiptReversalDTO)
			throws ApplicationException {
		ReceiptReversalVO receiptReversalVO = new ReceiptReversalVO();
		if (ObjectUtils.isNotEmpty(receiptReversalDTO.getId())) {
			receiptReversalVO = receiptReversalRepo.findById(receiptReversalDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReceiptReversal details"));
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

		List<ReceiptInvoiceVO> receiptInvoiceVOs = new ArrayList<>();
		if (receiptReversalDTO.getReceiptInvoiceDTO() != null) {
			for (ReceiptInvoiceDTO receiptInvoiceDTO : receiptReversalDTO.getReceiptInvoiceDTO()) {
				ReceiptInvoiceVO receiptInvoiceVO;
				if (receiptInvoiceDTO.getId() != null && ObjectUtils.isNotEmpty(receiptInvoiceDTO.getId())) {
					receiptInvoiceVO = receiptInvoiceRepo.findById(receiptInvoiceDTO.getId())
							.orElse(new ReceiptInvoiceVO());
				} else {
					receiptInvoiceVO = new ReceiptInvoiceVO();
				}
				receiptInvoiceVO.setInvoiceNo(receiptInvoiceDTO.getInvoiceNo());
				receiptInvoiceVO.setInvoiceDt(receiptInvoiceDTO.getInvoiceDt());
				receiptInvoiceVO.setRefNo(receiptInvoiceDTO.getRefNo());
				receiptInvoiceVO.setRefDate(receiptInvoiceDTO.getRefDate());
				receiptInvoiceVO.setMasterRef(receiptInvoiceDTO.getMasterRef());
				receiptInvoiceVO.setHouseRef(receiptInvoiceDTO.getHouseRef());
				receiptInvoiceVO.setCurr(receiptInvoiceDTO.getCurr());
				receiptInvoiceVO.setExRate(receiptInvoiceDTO.getExRate());
				receiptInvoiceVO.setAmount(receiptInvoiceDTO.getAmount());
				receiptInvoiceVO.setChargableAmount(receiptInvoiceDTO.getChargableAmount());
				receiptInvoiceVO.setOutStanding(receiptInvoiceDTO.getOutStanding());
				receiptInvoiceVO.setSettled(receiptInvoiceDTO.getSettled());
				receiptInvoiceVO.setRecExRate(receiptInvoiceDTO.getRecExRate());
				receiptInvoiceVO.setTxnSettled(receiptInvoiceDTO.getTxnSettled());
				receiptInvoiceVO.setGainOrLoss(receiptInvoiceDTO.getGainOrLoss());
				receiptInvoiceVO.setReceiptReversalVO(receiptReversalVO);
				receiptInvoiceVOs.add(receiptInvoiceVO);
			}
		}

		List<ReceiptOtherAccountVO> receiptOtherAccountVOs = new ArrayList<>();
		if (receiptReversalDTO.getReceiptOtherAccountDTO() != null) {
			for (ReceiptOtherAccountDTO receiptOtherAccountDTO : receiptReversalDTO.getReceiptOtherAccountDTO()) {
				ReceiptOtherAccountVO receiptOtherAccountVO;
				if (receiptOtherAccountDTO.getId() != null & ObjectUtils.isNotEmpty(receiptOtherAccountDTO.getId())) {
					receiptOtherAccountVO = receiptOtherAccountRepo.findById(receiptOtherAccountDTO.getId())
							.orElse(new ReceiptOtherAccountVO());
				} else {
					receiptOtherAccountVO = new ReceiptOtherAccountVO();
				}
				receiptOtherAccountVO.setAccountName(receiptOtherAccountDTO.getAccountName());
				receiptOtherAccountVO.setSubLedgerName(receiptOtherAccountDTO.getSubLedgerName());
				receiptOtherAccountVO.setCredit(receiptOtherAccountDTO.getCredit());
				receiptOtherAccountVO.setRemarks(receiptOtherAccountDTO.getRemarks());
				receiptOtherAccountVO.setReceiptReversalVO(receiptReversalVO);
				receiptOtherAccountVOs.add(receiptOtherAccountVO);
			}
		}

		List<ReceiptSummaryVO> receiptSummaryVOs = new ArrayList<>();
		if (receiptReversalDTO.getReceiptSummaryDTO() != null) {
			for (ReceiptSummaryDTO receiptSummaryDTO : receiptReversalDTO.getReceiptSummaryDTO()) {
				ReceiptSummaryVO receiptSummaryVO;
				if (receiptSummaryDTO.getId() != null & ObjectUtils.isEmpty(receiptSummaryDTO.getId())) {
					receiptSummaryVO = receiptSummaryRepo.findById(receiptSummaryDTO.getId())
							.orElse(new ReceiptSummaryVO());
				} else {
					receiptSummaryVO = new ReceiptSummaryVO();
				}
				receiptSummaryVO.setFoxenGainOrLoss(receiptSummaryDTO.getFoxenGainOrLoss());
				receiptSummaryVO.setRoundOffAmount(receiptSummaryDTO.getRoundOffAmount());
				receiptSummaryVO.setTotalSettled(receiptSummaryDTO.getTotalSettled());
				receiptSummaryVO.setOtherAccNetAmt(receiptSummaryDTO.getOtherAccNetAmt());
				receiptSummaryVO.setOnAccount(receiptSummaryDTO.getOnAccount());
				receiptSummaryVO.setNarration(receiptSummaryDTO.getNarration());
				receiptSummaryVO.setReceiptReversalVO(receiptReversalVO);
				receiptSummaryVOs.add(receiptSummaryVO);
			}
		}

		getReceiptReversalVOFromReceiptReversalDTO(receiptReversalDTO, receiptReversalVO);
		receiptReversalVO.setReceiptOtherAccountVO(receiptOtherAccountVOs);
		receiptReversalVO.setReceiptInvoiceVO(receiptInvoiceVOs);
		receiptReversalVO.setReceiptSummaryVO(receiptSummaryVOs);
		return receiptReversalRepo.save(receiptReversalVO);
	}

	private void getReceiptReversalVOFromReceiptReversalDTO(@Valid ReceiptReversalDTO receiptReversalDTO,
			ReceiptReversalVO receiptReversalVO) {
//				// Finyr
//				int finyr = taxInvoiceRepo.findFinyr();
//				// DocId
//				String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
//				taxInvoiceVO.setDocId(taxInvoice);
//				taxInvoiceRepo.nextSeq();
//				// InvoiceNo
//				String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
//				taxInvoiceVO.setInvoiceNo(invoiceNo);
//				taxInvoiceRepo.nextSeqInvoice();
		receiptReversalVO.setDocId(receiptReversalDTO.getDocId());
		receiptReversalVO.setDocDate(receiptReversalDTO.getDocDate());
		receiptReversalVO.setOriginBill(receiptReversalDTO.getOriginBill());
		receiptReversalVO.setOriginDate(receiptReversalDTO.getOriginDate());
		receiptReversalVO.setType(receiptReversalDTO.getType());
		receiptReversalVO.setReceiptType(receiptReversalDTO.getReceiptType());
		receiptReversalVO.setCustomerName(receiptReversalDTO.getCustomerName());
		receiptReversalVO.setCustomerCode(receiptReversalDTO.getCustomerCode());
		receiptReversalVO.setBankCash(receiptReversalDTO.getBankCash());
		receiptReversalVO.setReceiptAmount(receiptReversalDTO.getReceiptAmount());
		receiptReversalVO.setCurrency(receiptReversalDTO.getCurrency());
		receiptReversalVO.setBankChargesAc(receiptReversalDTO.getBankChargesAc());
		receiptReversalVO.setBankCharges(receiptReversalDTO.getBankCharges());
		receiptReversalVO.setBcInCurrency(receiptReversalDTO.getBcInCurrency());
		receiptReversalVO.setTdsAmount(receiptReversalDTO.getTdsAmount());
		receiptReversalVO.setTdsInCurrency(receiptReversalDTO.getTdsInCurrency());
		receiptReversalVO.setChequeBank(receiptReversalDTO.getChequeBank());
		receiptReversalVO.setReceiptType2(receiptReversalDTO.getReceiptType2());
		receiptReversalVO.setChqNo(receiptReversalDTO.getChqNo());
		receiptReversalVO.setChqDt(receiptReversalDTO.getChqDt());
		receiptReversalVO.setReceivedFrom(receiptReversalDTO.getReceivedFrom());
		receiptReversalVO.setOffSetStatus(receiptReversalDTO.getOffSetStatus());
		receiptReversalVO.setOrgId(receiptReversalDTO.getOrgId());
		receiptReversalVO.setActive(receiptReversalDTO.isActive());
		receiptReversalVO.setCreatedBy(receiptReversalDTO.getCreatedBy());
		receiptReversalVO.setUpdatedBy(receiptReversalDTO.getUpdatedBy());
	}

	@Override
	public List<ReceiptReversalVO> getReceiptReversalByActive() {
		return receiptReversalRepo.findReceiptReversalByActive();
	}

	// PaymentReversal

	@Override
	public List<PaymentReversalVO> getAllPaymentReversalByOrgId(Long orgId) {
		List<PaymentReversalVO> paymentReversalVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  PaymentReversal BY OrgId : {}", orgId);
			paymentReversalVO = paymentReversalRepo.getAllPaymentReversalByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  PaymentReversal For All OrgId.");
			paymentReversalVO = paymentReversalRepo.findAll();
		}
		return paymentReversalVO;
	}

	@Override
	public List<PaymentReversalVO> getAllPaymentReversalById(Long id) {
		List<PaymentReversalVO> paymentReversalVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received PaymentReversal BY Id : {}", id);
			paymentReversalVO = paymentReversalRepo.getAllPaymentReversalById(id);
		} else {
			LOGGER.info("Successfully Received PaymentReversal For All Id.");
			paymentReversalVO = paymentReversalRepo.findAll();
		}
		return paymentReversalVO;
	}

	@Override
	public PaymentReversalVO updateCreatePaymentReversal(@Valid PaymentReversalDTO paymentReversalDTO)
			throws ApplicationException {
		PaymentReversalVO paymentReversalVO = new PaymentReversalVO();
		if (ObjectUtils.isNotEmpty(paymentReversalDTO.getId())) {
			paymentReversalVO = paymentReversalRepo.findById(paymentReversalDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid PaymentReversal details"));
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

		List<PaymentInvoiceVO> paymentInvoiceVOs = new ArrayList<>();
		if (paymentReversalDTO.getPaymentInvoiceDTO() != null) {
			for (PaymentInvoiceDTO paymentInvoiceDTO : paymentReversalDTO.getPaymentInvoiceDTO()) {
				PaymentInvoiceVO paymentInvoiceVO;
				if (paymentInvoiceDTO.getId() != null && ObjectUtils.isNotEmpty(paymentInvoiceDTO.getId())) {
					paymentInvoiceVO = paymentInvoiceRepo.findById(paymentInvoiceDTO.getId())
							.orElse(new PaymentInvoiceVO());
				} else {
					paymentInvoiceVO = new PaymentInvoiceVO();
				}
				paymentInvoiceVO.setInvoiceNumber(paymentInvoiceDTO.getInvoiceNumber());
				paymentInvoiceVO.setInvoiceDate(paymentInvoiceDTO.getInvoiceDate());
				paymentInvoiceVO.setRefNo(paymentInvoiceDTO.getRefNo());
				paymentInvoiceVO.setRefDate(paymentInvoiceDTO.getRefDate());
				paymentInvoiceVO.setSuppRefNo(paymentInvoiceDTO.getSuppRefNo());
				paymentInvoiceVO.setSuppRefDate(paymentInvoiceDTO.getSuppRefDate());
				paymentInvoiceVO.setCurr(paymentInvoiceDTO.getCurr());
				paymentInvoiceVO.setExRate(paymentInvoiceDTO.getExRate());
				paymentInvoiceVO.setAmount(paymentInvoiceDTO.getAmount());
				paymentInvoiceVO.setOutStanding(paymentInvoiceDTO.getOutStanding());
				paymentInvoiceVO.setSettled(paymentInvoiceDTO.getSettled());
				paymentInvoiceVO.setPayExRate(paymentInvoiceDTO.getPayExRate());
				paymentInvoiceVO.setTxnSettled(paymentInvoiceDTO.getTxnSettled());
				paymentInvoiceVO.setGainOrLoss(paymentInvoiceDTO.getGainOrLoss());
				paymentInvoiceVO.setRemarks(paymentInvoiceDTO.getRemarks());
				paymentInvoiceVO.setPaymentReversalVO(paymentReversalVO);
				paymentInvoiceVOs.add(paymentInvoiceVO);
			}
		}

		List<PaymentOtherAccountVO> paymentOtherAccountVOs = new ArrayList<>();
		if (paymentReversalDTO.getPaymentOtherAccountDTO() != null) {
			for (PaymentOtherAccountDTO paymentOtherAccountDTO : paymentReversalDTO.getPaymentOtherAccountDTO()) {
				PaymentOtherAccountVO paymentOtherAccountVO;
				if (paymentOtherAccountDTO.getId() != null & ObjectUtils.isNotEmpty(paymentOtherAccountDTO.getId())) {
					paymentOtherAccountVO = paymentOtherAccountRepo.findById(paymentOtherAccountDTO.getId())
							.orElse(new PaymentOtherAccountVO());
				} else {
					paymentOtherAccountVO = new PaymentOtherAccountVO();
				}
				paymentOtherAccountVO.setAccountName(paymentOtherAccountDTO.getAccountName());
				paymentOtherAccountVO.setSubLedgerName(paymentOtherAccountDTO.getSubLedgerName());
				paymentOtherAccountVO.setDebitAmount(paymentOtherAccountDTO.getDebitAmount());
				paymentOtherAccountVO.setRemarks(paymentOtherAccountDTO.getRemarks());
				paymentOtherAccountVO.setPaymentReversalVO(paymentReversalVO);
				paymentOtherAccountVOs.add(paymentOtherAccountVO);
			}
		}

		List<PaymentSummaryVO> paymentSummaryVOs = new ArrayList<>();
		if (paymentReversalDTO.getPaymentSummaryDTO() != null) {
			for (PaymentSummaryDTO paymentSummaryDTO : paymentReversalDTO.getPaymentSummaryDTO()) {
				PaymentSummaryVO paymentSummaryVO;
				if (paymentSummaryDTO.getId() != null & ObjectUtils.isEmpty(paymentSummaryDTO.getId())) {
					paymentSummaryVO = paymentSummaryRepo.findById(paymentSummaryDTO.getId())
							.orElse(new PaymentSummaryVO());
				} else {
					paymentSummaryVO = new PaymentSummaryVO();
				}
				paymentSummaryVO.setFoxenGainOrLoss(paymentSummaryDTO.getFoxenGainOrLoss());
				paymentSummaryVO.setRoundOffAmount(paymentSummaryDTO.getRoundOffAmount());
				paymentSummaryVO.setTotalSettled(paymentSummaryDTO.getTotalSettled());
				paymentSummaryVO.setOtherAccNetAmt(paymentSummaryDTO.getOtherAccNetAmt());
				paymentSummaryVO.setOnAccount(paymentSummaryDTO.getOnAccount());
				paymentSummaryVO.setNarration(paymentSummaryDTO.getNarration());
				paymentSummaryVO.setPaymentReversalVO(paymentReversalVO);
				paymentSummaryVOs.add(paymentSummaryVO);
			}
		}

		getPaymentReversalVOFromPaymentReversalDTO(paymentReversalDTO, paymentReversalVO);
		paymentReversalVO.setPaymentOtherAccountVO(paymentOtherAccountVOs);
		paymentReversalVO.setPaymentInvoiceVO(paymentInvoiceVOs);
		paymentReversalVO.setPaymentSummaryVO(paymentSummaryVOs);
		return paymentReversalRepo.save(paymentReversalVO);
	}

	private void getPaymentReversalVOFromPaymentReversalDTO(@Valid PaymentReversalDTO paymentReversalDTO,
			PaymentReversalVO paymentReversalVO) {
//				// Finyr
//				int finyr = taxInvoiceRepo.findFinyr();
//				// DocId
//				String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
//				taxInvoiceVO.setDocId(taxInvoice);
//				taxInvoiceRepo.nextSeq();
//				// InvoiceNo
//				String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
//				taxInvoiceVO.setInvoiceNo(invoiceNo);
//				taxInvoiceRepo.nextSeqInvoice();
		paymentReversalVO.setDocId(paymentReversalDTO.getDocId());
		paymentReversalVO.setDocDate(paymentReversalDTO.getDocDate());
		paymentReversalVO.setOriginBill(paymentReversalDTO.getOriginBill());
		paymentReversalVO.setOrigin(paymentReversalDTO.getOrigin());
		paymentReversalVO.setType(paymentReversalDTO.getType());
		paymentReversalVO.setPartyCode(paymentReversalDTO.getPartyCode());
		paymentReversalVO.setPartyName(paymentReversalDTO.getPartyName());
		paymentReversalVO.setGstState(paymentReversalDTO.getGstState());
		paymentReversalVO.setGstIn(paymentReversalDTO.getGstIn());
		paymentReversalVO.setBankCash(paymentReversalDTO.getBankCash());
		paymentReversalVO.setPaymentAmount(paymentReversalDTO.getPaymentAmount());
		paymentReversalVO.setTdsAc(paymentReversalDTO.getTdsAc());
		paymentReversalVO.setSTaxAmount(paymentReversalDTO.getSTaxAmount());
		paymentReversalVO.setCurrency(paymentReversalDTO.getCurrency());
		paymentReversalVO.setBankChargesAc(paymentReversalDTO.getBankChargesAc());
		paymentReversalVO.setBankCharges(paymentReversalDTO.getBankCharges());
		paymentReversalVO.setBcInCurrency(paymentReversalDTO.getBcInCurrency());
		paymentReversalVO.setTdsAmount(paymentReversalDTO.getTdsAmount());
		paymentReversalVO.setPaymentTime(paymentReversalDTO.getPaymentTime());
		paymentReversalVO.setTaInCurrency(paymentReversalDTO.getTaInCurrency());
		paymentReversalVO.setChequeBank(paymentReversalDTO.getChequeBank());
		paymentReversalVO.setPayTo(paymentReversalDTO.getPayTo());
		paymentReversalVO.setChqDt(paymentReversalDTO.getChqDt());
		paymentReversalVO.setOffSetStatus(paymentReversalDTO.getOffSetStatus());
		paymentReversalVO.setOrgId(paymentReversalDTO.getOrgId());
		paymentReversalVO.setActive(paymentReversalDTO.isActive());
		paymentReversalVO.setCreatedBy(paymentReversalDTO.getCreatedBy());
		paymentReversalVO.setUpdatedBy(paymentReversalDTO.getUpdatedBy());
	}

	@Override
	public List<PaymentReversalVO> getPaymentReversalByActive() {
		return paymentReversalRepo.findPaymentReversalByActive();
	}

}
