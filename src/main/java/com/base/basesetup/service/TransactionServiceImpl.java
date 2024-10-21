package com.base.basesetup.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.dto.ArApAdjustmentOffSetDTO;
import com.base.basesetup.dto.ArApOffSetInvoiceDetailsDTO;
import com.base.basesetup.dto.BrsOpeningDTO;
import com.base.basesetup.dto.ChargerDebitNoteDTO;
import com.base.basesetup.dto.ChargerIrnCreditDTO;
import com.base.basesetup.dto.ChartCostCenterDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDtlDTO;
import com.base.basesetup.dto.DebitNoteDTO;
import com.base.basesetup.dto.FundTransferDTO;
import com.base.basesetup.dto.GeneralJournalDTO;
import com.base.basesetup.dto.GlOpeningBalanceDTO;
import com.base.basesetup.dto.GstDebitNoteDTO;
import com.base.basesetup.dto.GstIrnCreditDTO;
import com.base.basesetup.dto.GstSalesVoucherDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.ParticularsDebitNoteDTO;
import com.base.basesetup.dto.ParticularsGlOpeningBalanceDTO;
import com.base.basesetup.dto.ParticularsGstVoucherDTO;
import com.base.basesetup.dto.ParticularsJournalDTO;
import com.base.basesetup.dto.ParticularsPaymentVoucherDTO;
import com.base.basesetup.dto.ParticularsReconcileCorpBankDTO;
import com.base.basesetup.dto.ParticularsReconcileDTO;
import com.base.basesetup.dto.PaymentInvoiceDTO;
import com.base.basesetup.dto.PaymentOtherAccountDTO;
import com.base.basesetup.dto.PaymentReversalDTO;
import com.base.basesetup.dto.PaymentVoucherDTO;
import com.base.basesetup.dto.ReceiptInvoiceDTO;
import com.base.basesetup.dto.ReceiptOtherAccountDTO;
import com.base.basesetup.dto.ReceiptReversalDTO;
import com.base.basesetup.dto.ReconcileBankDTO;
import com.base.basesetup.dto.ReconcileCashDTO;
import com.base.basesetup.dto.ReconcileCorpBankDTO;
import com.base.basesetup.entity.ArApAdjustmentOffSetVO;
import com.base.basesetup.entity.ArApOffSetInvoiceDetailsVO;
import com.base.basesetup.entity.BrsExcelUploadVO;
import com.base.basesetup.entity.BrsOpeningVO;
import com.base.basesetup.entity.ChargerDebitNoteVO;
import com.base.basesetup.entity.ChargerIrnCreditVO;
import com.base.basesetup.entity.ChartCostCenterVO;
import com.base.basesetup.entity.DailyMonthlyExRatesDtlVO;
import com.base.basesetup.entity.DailyMonthlyExRatesVO;
import com.base.basesetup.entity.DebitNoteVO;
import com.base.basesetup.entity.DocumentTypeMappingDetailsVO;
import com.base.basesetup.entity.FundTransferVO;
import com.base.basesetup.entity.GeneralJournalVO;
import com.base.basesetup.entity.GlOpeningBalanceVO;
import com.base.basesetup.entity.GstDebitNoteVO;
import com.base.basesetup.entity.GstIrnCreditVO;
import com.base.basesetup.entity.GstSalesVoucherVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.ParticularsDebitNoteVO;
import com.base.basesetup.entity.ParticularsGlOpeningBalanceVO;
import com.base.basesetup.entity.ParticularsGstVoucherVO;
import com.base.basesetup.entity.ParticularsJournalVO;
import com.base.basesetup.entity.ParticularsPaymentVoucherVO;
import com.base.basesetup.entity.ParticularsReconcileCorpBankVO;
import com.base.basesetup.entity.ParticularsReconcileVO;
import com.base.basesetup.entity.PaymentInvoiceVO;
import com.base.basesetup.entity.PaymentOtherAccountVO;
import com.base.basesetup.entity.PaymentReversalVO;
import com.base.basesetup.entity.PaymentVoucherVO;
import com.base.basesetup.entity.ReceiptInvoiceVO;
import com.base.basesetup.entity.ReceiptOtherAccountVO;
import com.base.basesetup.entity.ReceiptReversalVO;
import com.base.basesetup.entity.ReconcileBankVO;
import com.base.basesetup.entity.ReconcileCashVO;
import com.base.basesetup.entity.ReconcileCorpBankVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ArApAdjustmentOffSetRepo;
import com.base.basesetup.repo.ArApOffSetInvoiceDetailsRepo;
import com.base.basesetup.repo.ArapAdjustmentsRepo;
import com.base.basesetup.repo.ArapDetailsRepo;
import com.base.basesetup.repo.BranchRepo;
import com.base.basesetup.repo.BrsExcelUploadRepo;
import com.base.basesetup.repo.BrsOpeningRepo;
import com.base.basesetup.repo.ChargerCostInvoiceRepo;
import com.base.basesetup.repo.ChargerDebitNoteRepo;
import com.base.basesetup.repo.ChargerIrnCreditRepo;
import com.base.basesetup.repo.ChartCostCenterRepo;
import com.base.basesetup.repo.CostInvoiceRepo;
import com.base.basesetup.repo.DailyMonthlyExRatesDtlRepo;
import com.base.basesetup.repo.DailyMonthlyExRatesRepo;
import com.base.basesetup.repo.DebitNoteRepo;
import com.base.basesetup.repo.DocumentTypeMappingDetailsRepo;
import com.base.basesetup.repo.FundTransferRepo;
import com.base.basesetup.repo.GeneralJournalRepo;
import com.base.basesetup.repo.GlOpeningBalanceRepo;
import com.base.basesetup.repo.GstDebitNoteRepo;
import com.base.basesetup.repo.GstIrnCreditRepo;
import com.base.basesetup.repo.GstSalesVoucherRepo;
import com.base.basesetup.repo.IrnCreditRepo;
import com.base.basesetup.repo.ParticularsDebitNoteRepo;
import com.base.basesetup.repo.ParticularsGlOpeningBalanceRepo;
import com.base.basesetup.repo.ParticularsGstVoucherRepo;
import com.base.basesetup.repo.ParticularsJournalRepo;
import com.base.basesetup.repo.ParticularsPaymentVoucherRepo;
import com.base.basesetup.repo.ParticularsReconcileCorpBankRepo;
import com.base.basesetup.repo.ParticularsReconcileRepo;
import com.base.basesetup.repo.PaymentInvoiceRepo;
import com.base.basesetup.repo.PaymentOtherAccountRepo;
import com.base.basesetup.repo.PaymentReversalRepo;
import com.base.basesetup.repo.PaymentVoucherRepo;
import com.base.basesetup.repo.ReceiptInvoiceRepo;
import com.base.basesetup.repo.ReceiptOtherAccountRepo;
import com.base.basesetup.repo.ReceiptReversalRepo;
import com.base.basesetup.repo.ReconcileBankRepo;
import com.base.basesetup.repo.ReconcileCashRepo;
import com.base.basesetup.repo.ReconcileCorpBankRepo;
import com.base.basesetup.repo.TaxInvoiceDetailsRepo;
import com.base.basesetup.repo.TaxInvoiceGstRepo;
import com.base.basesetup.repo.TaxInvoiceRepo;
import com.base.basesetup.repo.TdsCostInvoiceRepo;

@Service
public class TransactionServiceImpl implements TransactionService {
	public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
	@Autowired
	TaxInvoiceRepo taxInvoiceRepo;

	@Autowired
	TaxInvoiceDetailsRepo taxInvoiceDetailsRepo;

	@Autowired
	TaxInvoiceGstRepo gstTaxInvoiceRepo;

	@Autowired
	IrnCreditRepo irnCreditRepo;

	@Autowired
	ReconcileBankRepo reconcileBankRepo;

	@Autowired
	ParticularsReconcileRepo particularsReconcileRepo;

	@Autowired
	ParticularsReconcileCorpBankRepo particularsReconcileCorpBankRepo;

	@Autowired
	ReconcileCorpBankRepo reconcileCorpBankRepo;

	@Autowired
	ChargerIrnCreditRepo chargerIrnCreditRepo;

	@Autowired
	GstIrnCreditRepo gstIrnCreditRepo;

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
	CostInvoiceRepo costInvoiceRepo;

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
	GstSalesVoucherRepo gstSalesVoucherRepo;

	@Autowired
	ParticularsGstVoucherRepo particularsGstVoucherRepo;

	@Autowired
	PaymentVoucherRepo paymentVoucherRepo;

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
	PaymentReversalRepo paymentReversalRepo;

	@Autowired
	PaymentInvoiceRepo paymentInvoiceRepo;

	@Autowired
	PaymentOtherAccountRepo paymentOtherAccountRepo;

	@Autowired
	ArApAdjustmentOffSetRepo arApAdjustmentOffSetRepo;

	@Autowired
	ArApOffSetInvoiceDetailsRepo arApOffSetInvoiceDetailsRepo;

	@Autowired
	BranchRepo branchRepo;

	@Autowired
	GlOpeningBalanceRepo glOpeningBalanceRepo;

	@Autowired
	ParticularsGlOpeningBalanceRepo particularsGlOpeningBalanceRepo;

	@Autowired
	BrsExcelUploadRepo brsExcelUploadRepo;

//	@Autowired
//	ReconciliationSummaryRepo reconciliationSummaryRepo;

	@Autowired
	ReconcileCashRepo reconcileCashRepo;

	@Autowired
	DocumentTypeMappingDetailsRepo documentTypeMappingDetailsRepo;

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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(irnCreditDTO.getId())) {
			isUpdate = true;
			irnCreditVO = irnCreditRepo.findById(irnCreditDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid IrnCredit details"));
			irnCreditVO.setUpdatedBy(irnCreditDTO.getCreatedBy());
		} else {
			if (irnCreditRepo.existsByDocIdAndOrgId(irnCreditVO.getDocId(), irnCreditVO.getOrgId())) {
				throw new ApplicationException("The given doc id already exists.");
			}
			if (irnCreditRepo.existsByInvoiceNoAndOrgId(irnCreditVO.getInvoiceNo(), irnCreditVO.getOrgId())) {
				throw new ApplicationException("The given invoice number already exists.");
			}
			irnCreditVO.setUpdatedBy(irnCreditDTO.getCreatedBy());
			irnCreditVO.setCreatedBy(irnCreditDTO.getCreatedBy());
		}
		if (isUpdate) {
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
		irnCreditVO.setDocDate(irnCreditDTO.getDocDate());
		irnCreditVO.setInvoiceDate(irnCreditDTO.getInvoiceDate());
		irnCreditVO.setAmountInwords(irnCreditDTO.getAmountInwords());
		irnCreditVO.setBillingRemarks(irnCreditDTO.getBillingRemarks());
		irnCreditVO.setBillInvAmount(irnCreditDTO.getBillInvAmount());
		irnCreditVO.setBilllcChargeAmount(irnCreditDTO.getBilllcChargeAmount());
		irnCreditVO.setBillTaxAmount(irnCreditDTO.getBillTaxAmount());
		irnCreditVO.setLcChargeAmount(irnCreditDTO.getLcChargeAmount());
		irnCreditVO.setLcInvAmount(irnCreditDTO.getLcInvAmount());
		irnCreditVO.setLcRoundOffAmount(irnCreditDTO.getLcRoundOffAmount());
		irnCreditVO.setLcTaxableAmount(irnCreditDTO.getLcTaxableAmount());
		irnCreditVO.setLcTaxAmount(irnCreditDTO.getLcTaxAmount());
	}

	@Override
	public List<IrnCreditVO> getIrnCreditByActive() {
		return irnCreditRepo.findIrnCreditByActive();

	}

	// DailyMonthlyExRates
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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(dailyMonthlyExRatesDTO.getId())) {
			isUpdate = true;
			dailyMonthlyExRatesVO = dailyMonthlyExRatesRepo.findById(dailyMonthlyExRatesDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid DailyMonthlyExRates details"));
			dailyMonthlyExRatesVO.setUpdatedBy(dailyMonthlyExRatesDTO.getCreatedBy());
		} else {
			dailyMonthlyExRatesVO.setUpdatedBy(dailyMonthlyExRatesDTO.getCreatedBy());
			dailyMonthlyExRatesVO.setCreatedBy(dailyMonthlyExRatesDTO.getCreatedBy());
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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(brsOpeningDTO.getId())) {
			isUpdate = true;
			brsOpeningVO = brsOpeningRepo.findById(brsOpeningDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid BrsOpening details"));
			brsOpeningVO.setUpdatedBy(brsOpeningDTO.getCreatedBy());
		} else {
			brsOpeningVO.setUpdatedBy(brsOpeningDTO.getCreatedBy());
			brsOpeningVO.setCreatedBy(brsOpeningDTO.getCreatedBy());
		}

		getBrsOpeningVOFromBrsOpeningDTO(brsOpeningDTO, brsOpeningVO);
		return brsOpeningRepo.save(brsOpeningVO);
	}

	private void getBrsOpeningVOFromBrsOpeningDTO(@Valid BrsOpeningDTO brsOpeningDTO, BrsOpeningVO brsOpeningVO) {
		brsOpeningVO.setBillNo(brsOpeningDTO.getBillNo());
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
	}

	@Override
	public List<BrsOpeningVO> getBrsOpeningByActive() {
		return brsOpeningRepo.findBrsOpeningByActive();
	}

	@Override
	public List<Map<String, Object>> getBranchForBrsOpening(Long orgId) {
		Set<Object[]> result = branchRepo.findBranchForBrsOpening(orgId);
		return getBranch(result);
	}

	private List<Map<String, Object>> getBranch(Set<Object[]> result) {
		List<Map<String, Object>> details = new ArrayList<>();
		for (Object[] fs : result) {
			Map<String, Object> object = new HashMap<>();
			object.put("branch", fs[0] != null ? fs[0].toString() : "");
		}
		return details;
	}

	// BRS file upload
	private int totalRows = 0; // Instance variable to keep track of total rows
	private int successfulUploads = 0; // Instance variable to keep track of successful uploads

	private final DataFormatter dataFormatter = new DataFormatter();

	@Transactional
	public void ExcelUploadForBrs(MultipartFile[] files, Long orgId, String createdBy, String customer, String client,
			String finYear, String branch, String branchCode) throws ApplicationException {
		List<BrsExcelUploadVO> brsExcelUploadVOsToSave = new ArrayList<>();
		totalRows = 0;
		successfulUploads = 0;

		for (MultipartFile file : files) {
			if (file.isEmpty()) {
				throw new ApplicationException(
						"The supplied file '" + file.getOriginalFilename() + "' is empty (zero bytes long).");
			}

			try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
				Sheet sheet = workbook.getSheetAt(0);
				List<String> errorMessages = new ArrayList<>();
				System.out.println("Processing file: " + file.getOriginalFilename());

				Row headerRow = sheet.getRow(0);
				if (!isHeaderValid(headerRow)) {
					throw new ApplicationException("Invalid Excel format in file '" + file.getOriginalFilename()
							+ "'. Expected headers are: Type, From location, From location type, Location pick, partno, partdesc, sku, Grn No, GRN date, Batch No, Exp date, Entry no, From Status, To Status");
				}

				for (Row row : sheet) {
					if (row.getRowNum() == 0 || isRowEmpty(row)) {
						continue;
					}

					totalRows++;
					System.out.println("Validating row: " + (row.getRowNum() + 1));

					try {
						String billNo = getStringCellValue(row.getCell(0));
						LocalDate billDate = parseDate(row.getCell(1));
						String chqNo = getStringCellValue(row.getCell(2));
						LocalDate chqDate = parseDate(row.getCell(3));
						String bank = getStringCellValue(row.getCell(4));
						String currency = getStringCellValue(row.getCell(5));
						String exRate = getStringCellValue(row.getCell(6));
						BigDecimal receiptAmount = getBigDecimalCellValue(row.getCell(7));
						BigDecimal paymentAmount = getBigDecimalCellValue(row.getCell(8));
						boolean reconcile = getBooleanCellValue(row.getCell(9));

						// Create and populate SrsExcelUploadVO object
						BrsExcelUploadVO brsExcelUploadVO = new BrsExcelUploadVO();
						brsExcelUploadVO.setBillNo(billNo);
						brsExcelUploadVO.setBillDate(billDate);
						brsExcelUploadVO.setChqNo(chqNo);
						brsExcelUploadVO.setChqDate(chqDate);
						brsExcelUploadVO.setBank(bank);
						brsExcelUploadVO.setCurrency(currency);
						brsExcelUploadVO.setExRate(exRate);
						brsExcelUploadVO.setReceiptAmount(receiptAmount);
						brsExcelUploadVO.setPaymentAmount(paymentAmount);
						brsExcelUploadVO.setReconcile(reconcile);
						brsExcelUploadVO.setOrgId(orgId);
						brsExcelUploadVO.setCustomer(customer);
						brsExcelUploadVO.setClient(client);
						brsExcelUploadVO.setFinYear(finYear);
						brsExcelUploadVO.setBranch(branch);
						brsExcelUploadVO.setBranchCode(branchCode);
						brsExcelUploadVO.setCreatedBy(createdBy);
						brsExcelUploadVO.setUpdatedBy(createdBy);
						brsExcelUploadVO.setActive(true);
						brsExcelUploadVO.setCancel(false);
						brsExcelUploadVO.setCancelRemarks("");

						brsExcelUploadVOsToSave.add(brsExcelUploadVO);
						successfulUploads++;
					} catch (Exception e) {
						// Optionally handle specific row processing exceptions here
					}
				}

				brsExcelUploadRepo.saveAll(brsExcelUploadVOsToSave);
			} catch (IOException e) {
				throw new ApplicationException(
						"Failed to process file: " + file.getOriginalFilename() + " - " + e.getMessage());
			}
		}
	}

	private boolean getBooleanCellValue(Cell cell) {
		if (cell == null) {
			return false; // Return false if the cell is null
		}

		switch (cell.getCellType()) {
		case BOOLEAN:
			return cell.getBooleanCellValue(); // If the cell type is BOOLEAN, return its value
		case STRING:
			// For string values, treat "true" (case-insensitive) as true, anything else as
			// false
			return "true".equalsIgnoreCase(cell.getStringCellValue().trim());
		case NUMERIC:
			// If numeric and non-zero, treat as true (e.g., 1 = true, 0 = false)
			return cell.getNumericCellValue() != 0;
		default:
			throw new RuntimeException("Cell type is not BOOLEAN, STRING, or NUMERIC.");
		}
	}

	private BigDecimal getBigDecimalCellValue(Cell cell) {
		if (cell == null) {
			return BigDecimal.ZERO; // Return 0 if the cell is null
		}

		switch (cell.getCellType()) {
		case NUMERIC:
			return BigDecimal.valueOf(cell.getNumericCellValue()); // Convert numeric value to BigDecimal
		case STRING:
			try {
				return new BigDecimal(cell.getStringCellValue().trim()); // Convert string value to BigDecimal
			} catch (NumberFormatException e) {
				throw new RuntimeException("Invalid number format for BigDecimal: " + cell.getStringCellValue());
			}
		default:
			throw new RuntimeException("Cell type is not numeric or string convertible to BigDecimal.");
		}
	}

	private LocalDate parseDate(Cell cell) {
		if (cell == null) {
			return null;
		}

		try {
			if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
				return cell.getLocalDateTimeCellValue().toLocalDate();
			} else if (cell.getCellType() == CellType.STRING) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Adjusted to dd/MM/yyyy
				return LocalDate.parse(cell.getStringCellValue(), formatter);
			}
		} catch (Exception e) {
			System.err.println("Date parsing error for cell value: " + getStringCellValue(cell));
		}
		return null;
	}

	private String getStringCellValue(Cell cell) {
		if (cell == null) {
			return ""; // Return empty string if cell is null
		}

		// Use DataFormatter to get the cell value as a string
		return dataFormatter.formatCellValue(cell);
	}

	private boolean isRowEmpty(Row row) {
		for (Cell cell : row) {
			if (cell.getCellType() != CellType.BLANK) {
				return false;
			}
		}
		return true;
	}

	private boolean isHeaderValid(Row headerRow) throws ApplicationException {
		if (headerRow == null) {
			return false;
		}
		List<String> expectedHeaders = Arrays.asList("Bill No", "Bill Date", "Cheque No", "Cheque Date", "Bank",
				"Currency", "Ex Rate", "Receipt Amount", "Payment Amount", "Reconcile");

		List<String> actualHeaders = new ArrayList<>();
		for (Cell cell : headerRow) {
			actualHeaders.add(getStringCellValue(cell).trim());
		}

		if (!expectedHeaders.equals(actualHeaders)) {
			String errorDetails = "Expected headers: " + expectedHeaders.toString() + ", Found headers: "
					+ actualHeaders.toString();
			throw new ApplicationException("Invalid Excel format. " + errorDetails);
		}
		return true;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getSuccessfulUploads() {
		return successfulUploads;
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
	public List<ChartCostCenterVO> updateCreateChartCostCenter(@Valid List<ChartCostCenterDTO> chartCostCenterDTOList)
			throws ApplicationException {

		// Iterate through each DTO in the list
		for (ChartCostCenterDTO chartCostCenterDTO : chartCostCenterDTOList) {
			ChartCostCenterVO chartCostCenterVO = new ChartCostCenterVO();
			boolean isUpdate = false;

			// Check if the DTO contains an ID for update operation
			if (ObjectUtils.isNotEmpty(chartCostCenterDTO.getId())) {
				// Update operation
				isUpdate = true;
				chartCostCenterVO = chartCostCenterRepo.findById(chartCostCenterDTO.getId())
						.orElseThrow(() -> new ApplicationException(
								"Invalid ChartCostCenter details with ID: " + chartCostCenterDTO.getId()));
				chartCostCenterVO.setUpdatedBy(chartCostCenterDTO.getCreatedBy());
			} else {
				// Create operation (new entity)
				chartCostCenterVO = new ChartCostCenterVO();
				chartCostCenterVO.setCreatedBy(chartCostCenterDTO.getCreatedBy());
				chartCostCenterVO.setUpdatedBy(chartCostCenterDTO.getCreatedBy());
			}

			// Map fields from DTO to VO
			getChartCostCenterVOFromChartCostCenterDTO(chartCostCenterDTO, chartCostCenterVO);

			// Save the entity and add it to the list
			chartCostCenterRepo.save(chartCostCenterVO);
		}

		// Return the list of saved entities
		return chartCostCenterRepo.findAll();
	}

	private void getChartCostCenterVOFromChartCostCenterDTO(@Valid ChartCostCenterDTO chartCostCenterDTO,
			ChartCostCenterVO chartCostCenterVO) {
		chartCostCenterVO.setCostCenterCode(chartCostCenterDTO.getCostCenterCode());
		chartCostCenterVO.setCostCenterName(chartCostCenterDTO.getCostCenterName());
		chartCostCenterVO.setCredit(chartCostCenterDTO.getCredit());
		chartCostCenterVO.setDebit(chartCostCenterDTO.getDebit());
		chartCostCenterVO.setOrgId(chartCostCenterDTO.getOrgId());
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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(fundTransferDTO.getId())) {
			isUpdate = true;
			fundTransferVO = fundTransferRepo.findById(fundTransferDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid FundTransfer details"));
			fundTransferVO.setUpdatedBy(fundTransferDTO.getCreatedBy());
		} else {
			fundTransferVO.setUpdatedBy(fundTransferDTO.getCreatedBy());
			fundTransferVO.setCreatedBy(fundTransferDTO.getCreatedBy());
		}

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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(generalJournalDTO.getId())) {
			isUpdate = true;
			generalJournalVO = generalJournalRepo.findById(generalJournalDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GeneralJournal details"));
			generalJournalVO.setUpdatedBy(generalJournalDTO.getCreatedBy());
		} else {
			generalJournalVO.setUpdatedBy(generalJournalDTO.getCreatedBy());
			generalJournalVO.setCreatedBy(generalJournalDTO.getCreatedBy());
		}

		List<ParticularsJournalVO> particularsJournalVOs = new ArrayList<>();
		if (generalJournalDTO.getParticularsJournalDTO() != null) {
			for (ParticularsJournalDTO particularsJournalDTO : generalJournalDTO.getParticularsJournalDTO()) {
				ParticularsJournalVO particularsJournalVO;
				if (particularsJournalDTO.getId() != null & ObjectUtils.isEmpty(particularsJournalDTO.getId())) {
					particularsJournalVO = particularsJournalRepo.findById(particularsJournalDTO.getId())
							.orElse(new ParticularsJournalVO());
				} else {
					particularsJournalVO = new ParticularsJournalVO();
				}
				particularsJournalVO.setAccountsName(particularsJournalDTO.getAccountsName());
				particularsJournalVO.setSubledgerName(particularsJournalDTO.getSubledgerName());
				particularsJournalVO.setSubLedgerCode(particularsJournalDTO.getSubLedgerCode());
				particularsJournalVO.setDebitAmount(particularsJournalDTO.getDebitAmount());
				particularsJournalVO.setCreditAmount(particularsJournalDTO.getCreditAmount());
				particularsJournalVO.setNarration(particularsJournalDTO.getNarration());
				particularsJournalVO.setGeneralJournalVO(generalJournalVO);
				particularsJournalVOs.add(particularsJournalVO);
			}
		}
		generalJournalVO.setParticularsJournalVO(particularsJournalVOs);
		getGeneralJournalVOFromGeneralJournalDTO(generalJournalDTO, generalJournalVO);
		return generalJournalRepo.save(generalJournalVO);
	}

	private void getGeneralJournalVOFromGeneralJournalDTO(@Valid GeneralJournalDTO generalJournalDTO,
			GeneralJournalVO generalJournalVO) {
		generalJournalVO.setVoucherSubType(generalJournalDTO.getVoucherSubType());
		generalJournalVO.setDocDate(generalJournalDTO.getDocDate());
		generalJournalVO.setDocId(generalJournalDTO.getDocId());
		generalJournalVO.setRemarks(generalJournalDTO.getRemarks());
		generalJournalVO.setCurrency(generalJournalDTO.getCurrency());
		generalJournalVO.setExRate(generalJournalDTO.getExRate());

		generalJournalVO.setRefNo(generalJournalDTO.getRefNo());
		generalJournalVO.setRefDate(generalJournalDTO.getRefDate());
		generalJournalVO.setOrgId(generalJournalDTO.getOrgId());
		generalJournalVO.setActive(generalJournalDTO.isActive());
		generalJournalVO.setCancel(generalJournalDTO.isCancel());
		generalJournalVO.setCancelRemarks(generalJournalDTO.getCancelRemarks());
		generalJournalVO.setTotalCreditAmount(generalJournalDTO.getTotalCreditAmount());
		generalJournalVO.setTotalDebitAmount(generalJournalDTO.getTotalDebitAmount());
	}

	@Override
	public List<GeneralJournalVO> getGeneralJournalByActive() {
		return generalJournalRepo.findGeneralJournalByActive();

	}

	// DebitNote

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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(debitNoteDTO.getId())) {
			isUpdate = true;
			debitNoteVO = debitNoteRepo.findById(debitNoteDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid DebitNote details"));
			debitNoteVO.setUpdatedBy(debitNoteDTO.getCreatedBy());
		} else {
			debitNoteVO.setUpdatedBy(debitNoteDTO.getCreatedBy());
			debitNoteVO.setCreatedBy(debitNoteDTO.getCreatedBy());
		}

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
		debitNoteVO.setGstDebitNoteVO(gstDebitNoteVOs);
		return debitNoteRepo.save(debitNoteVO);
	}

	private void getDebitNoteVOFromDebitNoteDTO(@Valid DebitNoteDTO debitNoteDTO, DebitNoteVO debitNoteVO) {
		// // Finyr
		// int finyr = taxInvoiceRepo.findFinyr();
		// // DocId
		// String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		// taxInvoiceVO.setDocId(taxInvoice);
		// taxInvoiceRepo.nextSeq();
		// // InvoiceNo
		// String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		// taxInvoiceVO.setInvoiceNo(invoiceNo);
		// taxInvoiceRepo.nextSeqInvoice();
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
		debitNoteVO.setBillCurrTotChargeAmount(debitNoteDTO.getBillCurrTotChargeAmount());
		debitNoteVO.setBillCurrTotGrossAmount(debitNoteDTO.getBillCurrTotGrossAmount());
		debitNoteVO.setBillCurrNetAmount(debitNoteDTO.getBillCurrNetAmount());
		debitNoteVO.setAmountInWords(debitNoteDTO.getAmountInWords());
		debitNoteVO.setRoundOff(debitNoteDTO.getRoundOff());
		debitNoteVO.setLctotChargeAmount(debitNoteDTO.getLctotChargeAmount());
		debitNoteVO.setLctotGrossAmount(debitNoteDTO.getLctotGrossAmount());
		debitNoteVO.setLcNetAmount(debitNoteDTO.getLcNetAmount());
	}

	@Override
	public List<DebitNoteVO> getDebitNoteByActive() {
		return debitNoteRepo.findDebitNoteByActive();
	}

	// GstSalesVoucher

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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(gstSalesVoucherDTO.getId())) {
			isUpdate = true;
			gstSalesVoucherVO = gstSalesVoucherRepo.findById(gstSalesVoucherDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GstSalesVoucher details"));
			gstSalesVoucherVO.setUpdatedBy(gstSalesVoucherDTO.getCreatedBy());
		} else {
			gstSalesVoucherVO.setUpdatedBy(gstSalesVoucherDTO.getCreatedBy());
			gstSalesVoucherVO.setCreatedBy(gstSalesVoucherDTO.getCreatedBy());
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
		gstSalesVoucherVO.setTotalDebitAmount(gstSalesVoucherDTO.getTotalDebitAmount());
		gstSalesVoucherVO.setTotalCreditAmount(gstSalesVoucherDTO.getTotalCreditAmount());
		gstSalesVoucherVO.setStTaxAmount(gstSalesVoucherDTO.getStTaxAmount());
		gstSalesVoucherVO.setBasAmount(gstSalesVoucherDTO.getBasAmount());
		gstSalesVoucherVO.setBssAmount(gstSalesVoucherDTO.getBssAmount());
		gstSalesVoucherVO.setChaAmount(gstSalesVoucherDTO.getChaAmount());
	}

	@Override
	public List<GstSalesVoucherVO> getGstSalesVoucherByActive() {
		return gstSalesVoucherRepo.findGstSalesVoucherByActive();
	}

	// PaymentVoucher

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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(paymentVoucherDTO.getId())) {
			isUpdate = true;
			paymentVoucherVO = paymentVoucherRepo.findById(paymentVoucherDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid PaymentVoucher details"));
			paymentVoucherVO.setUpdatedBy(paymentVoucherDTO.getCreatedBy());
		} else {
			paymentVoucherVO.setUpdatedBy(paymentVoucherDTO.getCreatedBy());
			paymentVoucherVO.setCreatedBy(paymentVoucherDTO.getCreatedBy());
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
				particularsPaymentVoucherVO.setSubLedgerCode(particularsPaymentVoucherDTO.getSubLedgerCode());
				particularsPaymentVoucherVO.setSubLedgerName(particularsPaymentVoucherDTO.getSubLedgerName());
				particularsPaymentVoucherVO.setDebit(particularsPaymentVoucherDTO.getDebit());
				particularsPaymentVoucherVO.setCredit(particularsPaymentVoucherDTO.getCredit());
				particularsPaymentVoucherVO.setNarration(particularsPaymentVoucherDTO.getNarration());
				particularsPaymentVoucherVO.setPaymentVoucherVO(paymentVoucherVO);
				particularsPaymentVoucherVO.setPaymentVoucherVO(paymentVoucherVO);

				particularsPaymentVoucherVOs.add(particularsPaymentVoucherVO);
			}
		}

		getPaymentVoucherVOFromPaymentVoucherDTO(paymentVoucherDTO, paymentVoucherVO);
		paymentVoucherVO.setParticularsPaymentVoucherVO(particularsPaymentVoucherVOs);
		return paymentVoucherRepo.save(paymentVoucherVO);
	}

	private void getPaymentVoucherVOFromPaymentVoucherDTO(@Valid PaymentVoucherDTO paymentVoucherDTO,
			PaymentVoucherVO paymentVoucherVO) {

		paymentVoucherVO.setVehicleSubType(paymentVoucherDTO.getVehicleSubType());
		paymentVoucherVO.setReferenceNo(paymentVoucherDTO.getReferenceNo());
		paymentVoucherVO.setCurrency(paymentVoucherDTO.getCurrency());
		paymentVoucherVO.setReferenceDate(paymentVoucherDTO.getReferenceDate());

		paymentVoucherVO.setExRate(paymentVoucherDTO.getExRate());
		paymentVoucherVO.setChequeNo(paymentVoucherDTO.getChequeNo());
		paymentVoucherVO.setChequeDate(paymentVoucherDTO.getChequeDate());
		paymentVoucherVO.setChequeBank(paymentVoucherDTO.getChequeBank());
		paymentVoucherVO.setOrgId(paymentVoucherDTO.getOrgId());
		paymentVoucherVO.setActive(paymentVoucherDTO.isActive());
		paymentVoucherVO.setCancel(paymentVoucherDTO.isCancel());
		paymentVoucherVO.setBranch(paymentVoucherDTO.getBranch());
		paymentVoucherVO.setBranchCode(paymentVoucherDTO.getBranchCode());
		paymentVoucherVO.setCancelRemarks(paymentVoucherDTO.getCancelRemarks());
		paymentVoucherVO.setIpNo(paymentVoucherDTO.getIpNo());
		paymentVoucherVO.setLatitude(paymentVoucherDTO.getLatitude());
		paymentVoucherVO.setRemarks(paymentVoucherDTO.getRemarks().toUpperCase());
		paymentVoucherVO.setTotalDebitAmount(paymentVoucherDTO.getTotalDebitAmount());
		paymentVoucherVO.setTotalCreditAmount(paymentVoucherDTO.getTotalCreditAmount());

		paymentVoucherVO.setRemarks(paymentVoucherDTO.getRemarks());

	}

	@Override
	public List<PaymentVoucherVO> getPaymentVoucherByActive() {
		return paymentVoucherRepo.findPaymentVoucherByActive();
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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(receiptReversalDTO.getId())) {
			isUpdate = true;
			receiptReversalVO = receiptReversalRepo.findById(receiptReversalDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReceiptReversal details"));
			receiptReversalVO.setUpdatedBy(receiptReversalDTO.getCreatedBy());
		} else {
			receiptReversalVO.setUpdatedBy(receiptReversalDTO.getCreatedBy());
			receiptReversalVO.setCreatedBy(receiptReversalDTO.getCreatedBy());
		}

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

		getReceiptReversalVOFromReceiptReversalDTO(receiptReversalDTO, receiptReversalVO);
		receiptReversalVO.setReceiptOtherAccountVO(receiptOtherAccountVOs);
		receiptReversalVO.setReceiptInvoiceVO(receiptInvoiceVOs);
		return receiptReversalRepo.save(receiptReversalVO);
	}

	private void getReceiptReversalVOFromReceiptReversalDTO(@Valid ReceiptReversalDTO receiptReversalDTO,
			ReceiptReversalVO receiptReversalVO) {
		// // Finyr
		// int finyr = taxInvoiceRepo.findFinyr();
		// // DocId
		// String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		// taxInvoiceVO.setDocId(taxInvoice);
		// taxInvoiceRepo.nextSeq();bbbbb
		// // InvoiceNo
		// String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		// taxInvoiceVO.setInvoiceNo(invoiceNo);
		// taxInvoiceRepo.nextSeqInvoice();
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
		receiptReversalVO.setFoxenGainOrLoss(receiptReversalDTO.getFoxenGainOrLoss());
		receiptReversalVO.setRoundOffAmount(receiptReversalDTO.getRoundOffAmount());
		receiptReversalVO.setTotalSettled(receiptReversalDTO.getTotalSettled());
		receiptReversalVO.setOtherAccNetAmt(receiptReversalDTO.getOtherAccNetAmt());
		receiptReversalVO.setOnAccount(receiptReversalDTO.getOnAccount());
		receiptReversalVO.setNarration(receiptReversalDTO.getNarration());
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
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(paymentReversalDTO.getId())) {
			isUpdate = true;
			paymentReversalVO = paymentReversalRepo.findById(paymentReversalDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid PaymentReversal details"));
			paymentReversalVO.setUpdatedBy(paymentReversalDTO.getCreatedBy());
		} else {
			paymentReversalVO.setUpdatedBy(paymentReversalDTO.getCreatedBy());
			paymentReversalVO.setCreatedBy(paymentReversalDTO.getCreatedBy());
		}

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

		getPaymentReversalVOFromPaymentReversalDTO(paymentReversalDTO, paymentReversalVO);
		paymentReversalVO.setPaymentOtherAccountVO(paymentOtherAccountVOs);
		paymentReversalVO.setPaymentInvoiceVO(paymentInvoiceVOs);
		return paymentReversalRepo.save(paymentReversalVO);
	}

	private void getPaymentReversalVOFromPaymentReversalDTO(@Valid PaymentReversalDTO paymentReversalDTO,
			PaymentReversalVO paymentReversalVO) {
		// // Finyr
		// int finyr = taxInvoiceRepo.findFinyr();
		// // DocId
		// String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		// taxInvoiceVO.setDocId(taxInvoice);
		// taxInvoiceRepo.nextSeq();
		// // InvoiceNo
		// String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		// taxInvoiceVO.setInvoiceNo(invoiceNo);
		// taxInvoiceRepo.nextSeqInvoice();
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
		paymentReversalVO.setFoxenGainOrLoss(paymentReversalDTO.getFoxenGainOrLoss());
		paymentReversalVO.setRoundOffAmount(paymentReversalDTO.getRoundOffAmount());
		paymentReversalVO.setTotalSettled(paymentReversalDTO.getTotalSettled());
		paymentReversalVO.setOtherAccNetAmt(paymentReversalDTO.getOtherAccNetAmt());
		paymentReversalVO.setOnAccount(paymentReversalDTO.getOnAccount());
		paymentReversalVO.setNarration(paymentReversalDTO.getNarration());
	}

	@Override
	public List<PaymentReversalVO> getPaymentReversalByActive() {
		return paymentReversalRepo.findPaymentReversalByActive();
	}

	// ArApAdjustmentOffSet

	@Override
	public List<ArApAdjustmentOffSetVO> getAllArApAdjustmentOffSetByOrgId(Long orgId) {
		List<ArApAdjustmentOffSetVO> arApAdjustmentOffSetVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ArApAdjustmentOffSet BY OrgId : {}", orgId);
			arApAdjustmentOffSetVO = arApAdjustmentOffSetRepo.getAllArApAdjustmentOffSetByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ArApAdjustmentOffSet For All OrgId.");
			arApAdjustmentOffSetVO = arApAdjustmentOffSetRepo.findAll();
		}
		return arApAdjustmentOffSetVO;
	}

	@Override
	public List<ArApAdjustmentOffSetVO> getAllArApAdjustmentOffSetById(Long id) {
		List<ArApAdjustmentOffSetVO> arApAdjustmentOffSetVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ArApAdjustmentOffSet BY Id : {}", id);
			arApAdjustmentOffSetVO = arApAdjustmentOffSetRepo.getAllArApAdjustmentOffSetById(id);
		} else {
			LOGGER.info("Successfully Received ArApAdjustmentOffSet For All Id.");
			arApAdjustmentOffSetVO = arApAdjustmentOffSetRepo.findAll();
		}
		return arApAdjustmentOffSetVO;
	}

	@Override
	public ArApAdjustmentOffSetVO updateCreateArApAdjustmentOffSet(
			@Valid ArApAdjustmentOffSetDTO arApAdjustmentOffSetDTO) throws ApplicationException {
		ArApAdjustmentOffSetVO arApAdjustmentOffSetVO = new ArApAdjustmentOffSetVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(arApAdjustmentOffSetDTO.getId())) {
			isUpdate = true;
			arApAdjustmentOffSetVO = arApAdjustmentOffSetRepo.findById(arApAdjustmentOffSetDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ArApAdjustmentOffSet details"));
			arApAdjustmentOffSetVO.setUpdatedBy(arApAdjustmentOffSetDTO.getCreatedBy());
		} else {
			arApAdjustmentOffSetVO.setUpdatedBy(arApAdjustmentOffSetDTO.getCreatedBy());
			arApAdjustmentOffSetVO.setCreatedBy(arApAdjustmentOffSetDTO.getCreatedBy());
		}

		List<ArApOffSetInvoiceDetailsVO> arApOffSetInvoiceDetailsVOs = new ArrayList<>();
		if (arApAdjustmentOffSetDTO.getArApOffSetInvoiceDetailsDTO() != null) {
			for (ArApOffSetInvoiceDetailsDTO arApOffSetInvoiceDetailsDTO : arApAdjustmentOffSetDTO
					.getArApOffSetInvoiceDetailsDTO()) {
				ArApOffSetInvoiceDetailsVO arApOffSetInvoiceDetailsVO;
				if (arApOffSetInvoiceDetailsDTO.getId() != null
						&& ObjectUtils.isNotEmpty(arApOffSetInvoiceDetailsDTO.getId())) {
					arApOffSetInvoiceDetailsVO = arApOffSetInvoiceDetailsRepo
							.findById(arApOffSetInvoiceDetailsDTO.getId()).orElse(new ArApOffSetInvoiceDetailsVO());
				} else {
					arApOffSetInvoiceDetailsVO = new ArApOffSetInvoiceDetailsVO();
				}
				arApOffSetInvoiceDetailsVO.setInvoiceNo(arApOffSetInvoiceDetailsDTO.getInvoiceNo());
				arApOffSetInvoiceDetailsVO.setInvoiceDate(arApOffSetInvoiceDetailsDTO.getInvoiceDate());
				arApOffSetInvoiceDetailsVO.setRefNo(arApOffSetInvoiceDetailsDTO.getRefNo());
				arApOffSetInvoiceDetailsVO.setRefDate(arApOffSetInvoiceDetailsDTO.getRefDate());
				arApOffSetInvoiceDetailsVO.setCurr(arApOffSetInvoiceDetailsDTO.getCurr());
				arApOffSetInvoiceDetailsVO.setExRate(arApOffSetInvoiceDetailsDTO.getExRate());
				arApOffSetInvoiceDetailsVO.setSetExRate(arApOffSetInvoiceDetailsDTO.getSetExRate());
				arApOffSetInvoiceDetailsVO.setInvAmount(arApOffSetInvoiceDetailsDTO.getInvAmount());
				arApOffSetInvoiceDetailsVO.setOutStanding(arApOffSetInvoiceDetailsDTO.getOutStanding());
				arApOffSetInvoiceDetailsVO.setSettled(arApOffSetInvoiceDetailsDTO.getSettled());
				arApOffSetInvoiceDetailsVO.setTnxSettled(arApOffSetInvoiceDetailsDTO.getTnxSettled());
				arApOffSetInvoiceDetailsVO.setGainOrLoss(arApOffSetInvoiceDetailsDTO.getGainOrLoss());
				arApOffSetInvoiceDetailsVO.setRemarks(arApOffSetInvoiceDetailsDTO.getRemarks());
				arApOffSetInvoiceDetailsVO.setArapadjustmentoffsetVO(arApAdjustmentOffSetVO);
				arApOffSetInvoiceDetailsVOs.add(arApOffSetInvoiceDetailsVO);
			}
		}

		getArApAdjustmentOffSetVOFromArApAdjustmentOffSetDTO(arApAdjustmentOffSetDTO, arApAdjustmentOffSetVO);
		arApAdjustmentOffSetVO.setArApAdjustmentInvoiceDetailsVO(arApOffSetInvoiceDetailsVOs);
		return arApAdjustmentOffSetRepo.save(arApAdjustmentOffSetVO);
	}

	private void getArApAdjustmentOffSetVOFromArApAdjustmentOffSetDTO(
			@Valid ArApAdjustmentOffSetDTO arApAdjustmentOffSetDTO, ArApAdjustmentOffSetVO arApAdjustmentOffSetVO) {
		// // Finyr
		// int finyr = taxInvoiceRepo.findFinyr();
		// // DocId
		// String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		// taxInvoiceVO.setDocId(taxInvoice);
		// taxInvoiceRepo.nextSeq();
		// // InvoiceNo
		// String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		// taxInvoiceVO.setInvoiceNo(invoiceNo);
		// taxInvoiceRepo.nextSeqInvoice();
		arApAdjustmentOffSetVO.setDocId(arApAdjustmentOffSetDTO.getDocId());
		arApAdjustmentOffSetVO.setDocDate(arApAdjustmentOffSetDTO.getDocDate());
		arApAdjustmentOffSetVO.setSubLedgerType(arApAdjustmentOffSetDTO.getSubLedgerType());
		arApAdjustmentOffSetVO.setSubLedgerName(arApAdjustmentOffSetDTO.getSubLedgerName());
		arApAdjustmentOffSetVO.setSubLedgerCode(arApAdjustmentOffSetDTO.getSubLedgerCode());
		arApAdjustmentOffSetVO.setReceiptPaymentDocId(arApAdjustmentOffSetDTO.getReceiptPaymentDocId());
		arApAdjustmentOffSetVO.setReceiptPaymentDocDate(arApAdjustmentOffSetDTO.getReceiptPaymentDocDate());
		arApAdjustmentOffSetVO.setCurrency(arApAdjustmentOffSetDTO.getCurrency());
		arApAdjustmentOffSetVO.setExRate(arApAdjustmentOffSetDTO.getExRate());
		arApAdjustmentOffSetVO.setAmount(arApAdjustmentOffSetDTO.getAmount());
		arApAdjustmentOffSetVO.setSupplierRefNo(arApAdjustmentOffSetDTO.getSupplierRefNo());
		arApAdjustmentOffSetVO.setForexGainOrLoss(arApAdjustmentOffSetDTO.getForexGainOrLoss());
		arApAdjustmentOffSetVO.setTotalSettled(arApAdjustmentOffSetDTO.getTotalSettled());
		arApAdjustmentOffSetVO.setRoundOffAmount(arApAdjustmentOffSetDTO.getRoundOffAmount());
		arApAdjustmentOffSetVO.setOnAccount(arApAdjustmentOffSetDTO.getOnAccount());
		arApAdjustmentOffSetVO.setOrgId(arApAdjustmentOffSetDTO.getOrgId());
		arApAdjustmentOffSetVO.setActive(arApAdjustmentOffSetDTO.isActive());
		arApAdjustmentOffSetVO.setNarration(arApAdjustmentOffSetDTO.getNarration());
	}

	@Override
	public List<ArApAdjustmentOffSetVO> getArApAdjustmentOffSetByActive() {
		return arApAdjustmentOffSetRepo.findArApAdjustmentOffSetByActive();
	}

	//// GlOpeningBalance

	@Override
	public List<GlOpeningBalanceVO> getAllGlOpeningBalanceByOrgId(Long orgId) {
		List<GlOpeningBalanceVO> glOpeningBalanceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received GlOpeningBalance BY OrgId : {}", orgId);
			glOpeningBalanceVO = glOpeningBalanceRepo.getAllGlOpeningBalanceByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  GlOpeningBalance For All OrgId.");
			glOpeningBalanceVO = glOpeningBalanceRepo.findAll();
		}
		return glOpeningBalanceVO;
	}

	@Override
	public List<GlOpeningBalanceVO> getAllGlOpeningBalanceById(Long id) {
		List<GlOpeningBalanceVO> glOpeningBalanceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received GlOpeningBalance BY Id : {}", id);
			glOpeningBalanceVO = glOpeningBalanceRepo.getAllGlOpeningBalanceById(id);
		} else {
			LOGGER.info("Successfully Received GlOpeningBalance For All Id.");
			glOpeningBalanceVO = glOpeningBalanceRepo.findAll();
		}
		return glOpeningBalanceVO;
	}

	@Override
	public Map<String, Object> updateCreateGlOpeningBalance(@Valid GlOpeningBalanceDTO glOpeningBalanceDTO)
			throws ApplicationException {
		String screenCode = "GOB";
		GlOpeningBalanceVO glOpeningBalanceVO = new GlOpeningBalanceVO();
		String message;
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(glOpeningBalanceDTO.getId())) {
			isUpdate = true;
			glOpeningBalanceVO = glOpeningBalanceRepo.findById(glOpeningBalanceDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GlOpeningBalance details"));
			glOpeningBalanceVO.setUpdatedBy(glOpeningBalanceDTO.getCreatedBy());
			getGlOpeningBalanceVOFromGlOpeningBalanceDTO(glOpeningBalanceDTO, glOpeningBalanceVO);
			message = "GlOpeningBalance Updated Successfully";
		} else {
			String docId = glOpeningBalanceRepo.getGlOpeningBalanceDocId(glOpeningBalanceDTO.getOrgId(),
					glOpeningBalanceDTO.getFinYear(), glOpeningBalanceDTO.getBranchCode(), screenCode);
			glOpeningBalanceVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(glOpeningBalanceDTO.getOrgId(),
							glOpeningBalanceDTO.getFinYear(), glOpeningBalanceDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			glOpeningBalanceVO.setUpdatedBy(glOpeningBalanceDTO.getCreatedBy());
			glOpeningBalanceVO.setCreatedBy(glOpeningBalanceDTO.getCreatedBy());
			message = "GlOpeningBalance Created Successfully";
		}

		List<ParticularsGlOpeningBalanceVO> particularsGlOpeningBalanceVOs = new ArrayList<>();
		if (glOpeningBalanceDTO.getParticularsGlOpeningBalanceDTO() != null) {
			for (ParticularsGlOpeningBalanceDTO particularsGlOpeningBalanceDTO : glOpeningBalanceDTO
					.getParticularsGlOpeningBalanceDTO()) {
				ParticularsGlOpeningBalanceVO particularsGlOpeningBalanceVO;
				if (particularsGlOpeningBalanceDTO.getId() != null
						& ObjectUtils.isEmpty(particularsGlOpeningBalanceDTO.getId())) {
					particularsGlOpeningBalanceVO = particularsGlOpeningBalanceRepo
							.findById(particularsGlOpeningBalanceDTO.getId())
							.orElse(new ParticularsGlOpeningBalanceVO());
				} else {
					particularsGlOpeningBalanceVO = new ParticularsGlOpeningBalanceVO();
				}
				particularsGlOpeningBalanceVO.setAccountName(particularsGlOpeningBalanceDTO.getAccountName());
				particularsGlOpeningBalanceVO.setSubLedgerCode(particularsGlOpeningBalanceDTO.getSubLedgerCode());
				particularsGlOpeningBalanceVO.setDebitAmount(particularsGlOpeningBalanceDTO.getDebitAmount());
				particularsGlOpeningBalanceVO.setCreditAmount(particularsGlOpeningBalanceDTO.getCreditAmount());
				particularsGlOpeningBalanceVO.setCreditBase(particularsGlOpeningBalanceDTO.getCreditBase());
				particularsGlOpeningBalanceVO.setDebitBase(particularsGlOpeningBalanceDTO.getDebitBase());
				particularsGlOpeningBalanceVO.setGlOpeningBalanceVO(glOpeningBalanceVO);
				particularsGlOpeningBalanceVOs.add(particularsGlOpeningBalanceVO);
			}
		}
		glOpeningBalanceVO.setParticularsGlOpeningBalanceVO(particularsGlOpeningBalanceVOs);
		getGlOpeningBalanceVOFromGlOpeningBalanceDTO(glOpeningBalanceDTO, glOpeningBalanceVO);
		glOpeningBalanceRepo.save(glOpeningBalanceVO);
		Map<String, Object> response = new HashMap<>();
		response.put("glOpeningBalanceVO", glOpeningBalanceVO);
		response.put("message", message);
		return response;
	}

	private void getGlOpeningBalanceVOFromGlOpeningBalanceDTO(@Valid GlOpeningBalanceDTO glOpeningBalanceDTO,
			GlOpeningBalanceVO glOpeningBalanceVO) {
		glOpeningBalanceVO.setBranch(glOpeningBalanceDTO.getBranch());
		glOpeningBalanceVO.setCurrency(glOpeningBalanceDTO.getCurrency());
		glOpeningBalanceVO.setExRate(glOpeningBalanceDTO.getExRate());
		glOpeningBalanceVO.setRefNo(glOpeningBalanceDTO.getRefNo());
		glOpeningBalanceVO.setRefDate(glOpeningBalanceDTO.getRefDate());
		glOpeningBalanceVO.setSuppRefNo(glOpeningBalanceDTO.getSuppRefNo());
		glOpeningBalanceVO.setSuppRefDate(glOpeningBalanceDTO.getSuppRefDate());
		glOpeningBalanceVO.setOrgId(glOpeningBalanceDTO.getOrgId());
		glOpeningBalanceVO.setActive(true);
		glOpeningBalanceVO.setBranchCode(glOpeningBalanceDTO.getBranchCode());
		glOpeningBalanceVO.setRemarks(glOpeningBalanceDTO.getRemarks());
		glOpeningBalanceVO.setFinYear(glOpeningBalanceDTO.getFinYear());
		glOpeningBalanceVO.setTotalCreditAmount(glOpeningBalanceDTO.getTotalCreditAmount());
		glOpeningBalanceVO.setTotalDebitAmount(glOpeningBalanceDTO.getTotalDebitAmount());
	}

	@Override
	public List<GlOpeningBalanceVO> getGlOpeningBalanceByActive() {
		return glOpeningBalanceRepo.findGlOpeningBalanceByActive();
	}
	
	@Override
	public String getGlOpeningBalanceDocId(Long orgId, String finYear, String branchCode, String branch) {
		String ScreenCode = "GOB";
		String result = glOpeningBalanceRepo.getGlOpeningBalanceDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

	@Override
	public GlOpeningBalanceVO getGlOpeningBalanceByDocId(Long orgId, String docId) {
		
		return glOpeningBalanceRepo.findAllGlOpeningBalanceByDocId(orgId, docId);
	}
	

	/// ReconcileBank

	@Override
	public List<ReconcileBankVO> getAllReconcileBankByOrgId(Long orgId) {
		List<ReconcileBankVO> reconcileBankVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ReconcileBank BY OrgId : {}", orgId);
			reconcileBankVO = reconcileBankRepo.getAllReconcileBankByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ReconcileBank For All OrgId.");
			reconcileBankVO = reconcileBankRepo.findAll();
		}
		return reconcileBankVO;
	}

	@Override
	public List<ReconcileBankVO> getAllReconcileBankById(Long id) {
		List<ReconcileBankVO> reconcileBankVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ReconcileBank BY Id : {}", id);
			reconcileBankVO = reconcileBankRepo.getAllReconcileBankById(id);
		} else {
			LOGGER.info("Successfully Received ReconcileBank For All Id.");
			reconcileBankVO = reconcileBankRepo.findAll();
		}
		return reconcileBankVO;
	}

	@Override
	public Map<String, Object> updateCreateReconcileBank(@Valid ReconcileBankDTO reconcileBankDTO)
			throws ApplicationException {
		String screenCode = "RB";
		ReconcileBankVO reconcileBankVO = new ReconcileBankVO();
		String message;
		// boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(reconcileBankDTO.getId())) {
			boolean isUpdate = true;
			reconcileBankVO = reconcileBankRepo.findById(reconcileBankDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReconcileBank details"));
			reconcileBankVO.setUpdatedBy(reconcileBankDTO.getCreatedBy());
			getReconcileBankVOFromReconcileBankDTO(reconcileBankDTO, reconcileBankVO);
			message = "ReconcileBank Updated Successfully";
		} else {
			String docId = reconcileBankRepo.getReconcileBankDocId(reconcileBankDTO.getOrgId(),
					reconcileBankDTO.getFinYear(), reconcileBankDTO.getBranchCode(), screenCode);
			reconcileBankVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(reconcileBankDTO.getOrgId(),
							reconcileBankDTO.getFinYear(), reconcileBankDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			reconcileBankVO.setUpdatedBy(reconcileBankDTO.getCreatedBy());
			reconcileBankVO.setCreatedBy(reconcileBankDTO.getCreatedBy());
			getReconcileBankVOFromReconcileBankDTO(reconcileBankDTO, reconcileBankVO);
			message = "ReconcileBank Created Successfully";
		}

		List<ParticularsReconcileVO> particularsReconcileVOs = new ArrayList<>();
		if (reconcileBankDTO.getParticularsReconcileDTO() != null) {
			for (ParticularsReconcileDTO particularsReconcileDTO : reconcileBankDTO.getParticularsReconcileDTO()) {
				ParticularsReconcileVO particularsReconcileVO;
				if (particularsReconcileDTO.getId() != null
						&& ObjectUtils.isNotEmpty(particularsReconcileDTO.getId())) {
					particularsReconcileVO = particularsReconcileRepo.findById(particularsReconcileDTO.getId())
							.orElse(new ParticularsReconcileVO());
				} else {
					particularsReconcileVO = new ParticularsReconcileVO();
				}
				particularsReconcileVO.setVoucherNo(particularsReconcileDTO.getVoucherNo());
				particularsReconcileVO.setVoucherDate(particularsReconcileDTO.getVoucherDate());
				particularsReconcileVO.setChequeNo(particularsReconcileDTO.getChequeNo());
				particularsReconcileVO.setChequeDate(particularsReconcileDTO.getChequeDate());
				particularsReconcileVO.setDeposit(particularsReconcileDTO.getDeposit());
				particularsReconcileVO.setWithdrawal(particularsReconcileDTO.getWithdrawal());
				particularsReconcileVO.setBankRef(particularsReconcileDTO.getBankRef());
				particularsReconcileVO.setReconcileBankVO(reconcileBankVO);
				particularsReconcileVOs.add(particularsReconcileVO);
			}
		}
		getReconcileBankVOFromReconcileBankDTO(reconcileBankDTO, reconcileBankVO);
		reconcileBankVO.setParticularsReconcileVO(particularsReconcileVOs);
		reconcileBankRepo.save(reconcileBankVO);

		Map<String, Object> response = new HashMap<>();
		response.put("reconcileBankVO", reconcileBankVO);
		response.put("message", message);

		return response;
	}

	private void getReconcileBankVOFromReconcileBankDTO(@Valid ReconcileBankDTO reconcileBankDTO,
			ReconcileBankVO reconcileBankVO) {
		// // Finyr
		// int finyr = taxInvoiceRepo.findFinyr();
		// // DocId
		// String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		// taxInvoiceVO.setDocId(taxInvoice);
		// taxInvoiceRepo.nextSeq();
		// // InvoiceNo
		// String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		// taxInvoiceVO.setInvoiceNo(invoiceNo);
		// taxInvoiceRepo.nextSeqInvoice();

		reconcileBankVO.setBankStmtDate(reconcileBankDTO.getBankStmtDate());
		reconcileBankVO.setBankAccount(reconcileBankDTO.getBankAccount());
		reconcileBankVO.setRemarks(reconcileBankDTO.getRemarks());
		reconcileBankVO.setTotalWithdrawal(reconcileBankDTO.getTotalWithdrawal());
		reconcileBankVO.setTotalDeposit(reconcileBankDTO.getTotalDeposit());
		reconcileBankVO.setOrgId(reconcileBankDTO.getOrgId());
		reconcileBankVO.setBranch(reconcileBankDTO.getBranch());
		reconcileBankVO.setBranchCode(reconcileBankDTO.getBranchCode());
		reconcileBankVO.setFinYear(reconcileBankDTO.getFinYear());
		reconcileBankVO.setRemarks(reconcileBankDTO.getRemarks());
	}

	@Override
	public List<ReconcileBankVO> getReconcileBankByActive() {

		return reconcileBankRepo.findReconcileBankByActive();

	}

	@Override
	public String getReconcileBankDocId(Long orgId, String finYear, String branch, String branchCode) {
		String ScreenCode = "RB";
		String result = reconcileBankRepo.getReconcileBankDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

	@Override
	public ReconcileBankVO getReconcileBankByDocId(Long orgId, String docId) {

		return reconcileBankRepo.findAllReconcileBankByDocId(orgId, docId);
	}

	// ReconcileCorpBank

	@Override
	public List<ReconcileCorpBankVO> getAllReconcileCorpBankByOrgId(Long orgId) {
		List<ReconcileCorpBankVO> reconcileCorpBankVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ReconcileCorpBank BY OrgId : {}", orgId);
			reconcileCorpBankVO = reconcileCorpBankRepo.getAllReconcileCorpBankByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ReconcileCorpBank For All OrgId.");
			reconcileCorpBankVO = reconcileCorpBankRepo.findAll();
		}
		return reconcileCorpBankVO;
	}

	@Override
	public List<ReconcileCorpBankVO> getAllReconcileCorpBankById(Long id) {
		List<ReconcileCorpBankVO> reconcileCorpBankVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ReconcileCorpBank BY Id : {}", id);
			reconcileCorpBankVO = reconcileCorpBankRepo.getAllReconcileCorpBankById(id);
		} else {
			LOGGER.info("Successfully Received ReconcileCorpBank For All Id.");
			reconcileCorpBankVO = reconcileCorpBankRepo.findAll();
		}
		return reconcileCorpBankVO;
	}

	@Override
	public Map<String, Object> updateCreateReconcileCorpBank(@Valid ReconcileCorpBankDTO reconcileCorpBankDTO)
			throws ApplicationException {
		String screenCode = "RC";
		ReconcileCorpBankVO reconcileCorpBankVO = new ReconcileCorpBankVO();
		String message;
		// boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(reconcileCorpBankDTO.getId())) {
			boolean isUpdate = true;
			reconcileCorpBankVO = reconcileCorpBankRepo.findById(reconcileCorpBankDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReconcileCorpBank details"));
			reconcileCorpBankVO.setUpdatedBy(reconcileCorpBankDTO.getCreatedBy());
			getReconcileCorpBankVOFromReconcileCorpBankDTO(reconcileCorpBankDTO, reconcileCorpBankVO);
			message = "ReconcileCorpBank Updated Successfully";
		} else {
			String docId = reconcileCorpBankRepo.getReconcileCorpBankDocId(reconcileCorpBankDTO.getOrgId(),
					reconcileCorpBankDTO.getFinYear(), reconcileCorpBankDTO.getBranchCode(), screenCode);
			reconcileCorpBankVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(reconcileCorpBankDTO.getOrgId(),
							reconcileCorpBankDTO.getFinYear(), reconcileCorpBankDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			reconcileCorpBankVO.setUpdatedBy(reconcileCorpBankDTO.getCreatedBy());
			reconcileCorpBankVO.setCreatedBy(reconcileCorpBankDTO.getCreatedBy());
			getReconcileCorpBankVOFromReconcileCorpBankDTO(reconcileCorpBankDTO, reconcileCorpBankVO);
			message = "ReconcileCorpBank Created Successfully";
		}

		List<ParticularsReconcileCorpBankVO> particularsReconcileCorpBankVOs = new ArrayList<>();
		if (reconcileCorpBankDTO.getParticularsReconcileCorpBankDTO() != null) {
			for (ParticularsReconcileCorpBankDTO particularsReconcileCorpBankDTO : reconcileCorpBankDTO
					.getParticularsReconcileCorpBankDTO()) {
				ParticularsReconcileCorpBankVO particularsReconcileCorpBankVO;
				if (particularsReconcileCorpBankDTO.getId() != null
						&& ObjectUtils.isNotEmpty(particularsReconcileCorpBankDTO.getId())) {
					particularsReconcileCorpBankVO = particularsReconcileCorpBankRepo
							.findById(particularsReconcileCorpBankDTO.getId())
							.orElse(new ParticularsReconcileCorpBankVO());
				} else {
					particularsReconcileCorpBankVO = new ParticularsReconcileCorpBankVO();
				}
				particularsReconcileCorpBankVO.setVoucherNo(particularsReconcileCorpBankDTO.getVoucherNo());
				particularsReconcileCorpBankVO.setVoucherDate(particularsReconcileCorpBankDTO.getVoucherDate());
				particularsReconcileCorpBankVO.setChequeNo(particularsReconcileCorpBankDTO.getChequeNo());
				particularsReconcileCorpBankVO.setChequeDate(particularsReconcileCorpBankDTO.getChequeDate());
				particularsReconcileCorpBankVO.setDeposit(particularsReconcileCorpBankDTO.getDeposit());
				particularsReconcileCorpBankVO.setWithdrawal(particularsReconcileCorpBankDTO.getWithdrawal());
				particularsReconcileCorpBankVO.setBankRef(particularsReconcileCorpBankDTO.getBankRef());
				particularsReconcileCorpBankVO.setReconcileCorpBankVO(reconcileCorpBankVO);
				particularsReconcileCorpBankVOs.add(particularsReconcileCorpBankVO);
			}
		}

		// getReconcileCorpBankVOFromReconcileCorpBankDTO(reconcileCorpBankDTO,
		// reconcileCorpBankVO);
		getReconcileCorpBankVOFromReconcileCorpBankDTO(reconcileCorpBankDTO, reconcileCorpBankVO);
		reconcileCorpBankVO.setParticularsReconcileCorpBankVO(particularsReconcileCorpBankVOs);
		reconcileCorpBankRepo.save(reconcileCorpBankVO);

		Map<String, Object> response = new HashMap<>();
		response.put("reconcileCorpBankVO", reconcileCorpBankVO);
		response.put("message", message);
		return response;
	}

	private void getReconcileCorpBankVOFromReconcileCorpBankDTO(@Valid ReconcileCorpBankDTO reconcileCorpBankDTO,
			ReconcileCorpBankVO reconcileCorpBankVO) {
		// // Finyr
		// int finyr = taxInvoiceRepo.findFinyr();
		// // DocId
		// String taxInvoice = "AI" + finyr + taxInvoiceRepo.findDocId();
		// taxInvoiceVO.setDocId(taxInvoice);
		// taxInvoiceRepo.nextSeq();
		// // InvoiceNo
		// String invoiceNo = "AI" + finyr + "INV" + taxInvoiceRepo.findInvoiceNo();
		// taxInvoiceVO.setInvoiceNo(invoiceNo);
		// taxInvoiceRepo.nextSeqInvoice();

		reconcileCorpBankVO.setBankStmtDate(reconcileCorpBankDTO.getBankStmtDate());
		reconcileCorpBankVO.setBankAccount(reconcileCorpBankDTO.getBankAccount());
		reconcileCorpBankVO.setRemarks(reconcileCorpBankDTO.getRemarks());
		reconcileCorpBankVO.setOrgId(reconcileCorpBankDTO.getOrgId());
		reconcileCorpBankVO.setBranch(reconcileCorpBankDTO.getBranch());
		reconcileCorpBankVO.setBranchCode(reconcileCorpBankDTO.getBranchCode());
		reconcileCorpBankVO.setFinYear(reconcileCorpBankDTO.getFinYear());
		reconcileCorpBankVO.setRemarks(reconcileCorpBankDTO.getRemarks());
	}

	@Override
	public List<ReconcileCorpBankVO> getReconcileCorpBankByActive() {

		return reconcileCorpBankRepo.findReconcileCorpBankByActive();
	}

	@Override
	public String getReconcileCorpBankDocId(Long orgId, String finYear, String branchCode, String screenCode) {
		String ScreenCode = "RC";
		String result = reconcileCorpBankRepo.getReconcileCorpBankDocId(orgId, finYear, branchCode, ScreenCode);
		return result;

	}

	@Override
	public ReconcileCorpBankVO getReconcileCorpBankByDocId(Long orgId, String docId) {
		
		return reconcileCorpBankRepo.findAllReconcileCorpBankByDocId(orgId, docId);
	}
	
	
	// ReconcileCash

	@Override
	public List<ReconcileCashVO> getAllReconcileCashByOrgId(Long orgId) {
		List<ReconcileCashVO> reconcileCashVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ReconcileCash BY OrgId : {}", orgId);
			reconcileCashVO = reconcileCashRepo.getAllReconcileCashByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ReconcileCash For All OrgId.");
			reconcileCashVO = reconcileCashRepo.findAll();
		}
		return reconcileCashVO;
	}

	@Override
	public List<ReconcileCashVO> getAllReconcileCashById(Long id) {
		List<ReconcileCashVO> reconcileCashVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ReconcileCash BY Id : {}", id);
			reconcileCashVO = reconcileCashRepo.getAllReconcileCashById(id);
		} else {
			LOGGER.info("Successfully Received ReconcileCash For All Id.");
			reconcileCashVO = reconcileCashRepo.findAll();
		}
		return reconcileCashVO;
	}

	@Override
	public Map<String, Object> updateCreateReconcileCash(@Valid ReconcileCashDTO reconcileCashDTO)
			throws ApplicationException {
		 String screenCode = "RCH";
		ReconcileCashVO reconcileCashVO = new ReconcileCashVO();
		boolean isUpdate = false;
           String message;
		if (ObjectUtils.isNotEmpty(reconcileCashDTO.getId())) {
			isUpdate = true;
			reconcileCashVO = reconcileCashRepo.findById(reconcileCashDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReconcileCash details"));
			reconcileCashVO.setUpdatedBy(reconcileCashDTO.getCreatedBy());
			getReconcileCashVOFromReconcileCashDTO(reconcileCashDTO, reconcileCashVO);
			message = "ReconcileCash Updated Successfully";
		} else {
			String docId = reconcileCashRepo.getReconcileCashDocId(reconcileCashDTO.getOrgId(),
					reconcileCashDTO.getFinYear(), reconcileCashDTO.getBranchCode(), screenCode);
			reconcileCashVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(reconcileCashDTO.getOrgId(),
							reconcileCashDTO.getFinYear(), reconcileCashDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			reconcileCashVO.setUpdatedBy(reconcileCashDTO.getCreatedBy());
			reconcileCashVO.setCreatedBy(reconcileCashDTO.getCreatedBy());
			message = "ReconcileCash Created Successfully";
		}
      
		getReconcileCashVOFromReconcileCashDTO(reconcileCashDTO, reconcileCashVO);
		 reconcileCashRepo.save(reconcileCashVO);
		 Map<String, Object> response = new HashMap<>();
			response.put("reconcileCashVO", reconcileCashVO);
		     response.put("message", message);
		   return response;
	}

	private void getReconcileCashVOFromReconcileCashDTO(@Valid ReconcileCashDTO reconcileCashDTO,
			ReconcileCashVO reconcileCashVO) {
		reconcileCashVO.setCashAccount(reconcileCashDTO.getCashAccount());
		reconcileCashVO.setBalanceAsPerBooks(reconcileCashDTO.getBalanceAsPerBooks());
		reconcileCashVO.setDn1(reconcileCashDTO.getDn1());
		reconcileCashVO.setDn2(reconcileCashDTO.getDn2());
		reconcileCashVO.setDn3(reconcileCashDTO.getDn3());
		reconcileCashVO.setDn4(reconcileCashDTO.getDn4());
		reconcileCashVO.setDn5(reconcileCashDTO.getDn5());
		reconcileCashVO.setDn6(reconcileCashDTO.getDn6());
		reconcileCashVO.setDn7(reconcileCashDTO.getDn7());
		reconcileCashVO.setDn8(reconcileCashDTO.getDn8());
		reconcileCashVO.setRemarks(reconcileCashDTO.getRemarks());
		reconcileCashVO.setOrgId(reconcileCashDTO.getOrgId());
		reconcileCashVO.setActive(true);
		reconcileCashVO.setCancel(false);
		reconcileCashVO.setBranch(reconcileCashDTO.getBranch());
		reconcileCashVO.setBranchCode(reconcileCashDTO.getBranchCode());
		reconcileCashVO.setFinYear(reconcileCashDTO.getFinYear());

		// Map counts from DTO
		int[] counts = { reconcileCashDTO.getDn1(), reconcileCashDTO.getDn2(), reconcileCashDTO.getDn3(),
				reconcileCashDTO.getDn4(), reconcileCashDTO.getDn5(), reconcileCashDTO.getDn6(),
				reconcileCashDTO.getDn7(), reconcileCashDTO.getDn8() };
		BigDecimal[] amounts = { BigDecimal.valueOf(2000), BigDecimal.valueOf(500), BigDecimal.valueOf(200),
				BigDecimal.valueOf(100), BigDecimal.valueOf(50), BigDecimal.valueOf(20), BigDecimal.valueOf(10),
				BigDecimal.valueOf(1) };
		BigDecimal totalPhysicalAmount = calculateTotalPhysicalAmount(counts, amounts);
		reconcileCashVO.setTotalPhyAmount(totalPhysicalAmount);
		reconcileCashVO.setDifferenceAmount(totalPhysicalAmount);

		// Set individual dn amounts based on calculations
		reconcileCashVO.setDn1Amt(amounts[0].multiply(BigDecimal.valueOf(counts[0])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn2Amt(amounts[1].multiply(BigDecimal.valueOf(counts[1])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn3Amt(amounts[2].multiply(BigDecimal.valueOf(counts[2])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn4Amt(amounts[3].multiply(BigDecimal.valueOf(counts[3])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn5Amt(amounts[4].multiply(BigDecimal.valueOf(counts[4])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn6Amt(amounts[5].multiply(BigDecimal.valueOf(counts[5])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn7Amt(amounts[6].multiply(BigDecimal.valueOf(counts[6])).setScale(2, RoundingMode.HALF_UP));
		reconcileCashVO.setDn8Amt(amounts[7].multiply(BigDecimal.valueOf(counts[7])).setScale(2, RoundingMode.HALF_UP));
	}

	private BigDecimal calculateTotalPhysicalAmount(int[] counts, BigDecimal[] amounts) {
		BigDecimal total = BigDecimal.ZERO;

		for (int i = 0; i < counts.length; i++) {
			total = total.add(amounts[i].multiply(BigDecimal.valueOf(counts[i])));
		}

		return total.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public List<ReconcileCashVO> getReconcileCashByActive() {
		return reconcileCashRepo.findReconcileCashByActive();
	}

	@Override
	public String getReconcileCashDocId(Long orgId, String finYear,String branch, String branchCode) {
		String ScreenCode = "RCH";
		String result = reconcileCashRepo.getReconcileCashDocId(orgId, finYear, branchCode,ScreenCode);
		return result;

	}

	@Override
	public ReconcileCashVO getReconcileCashByDocId(Long orgId, String docId) {
		return reconcileCashRepo.findAllReconcileCashByDocId(orgId, docId);
	}

	

	

}
