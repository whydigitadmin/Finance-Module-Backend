package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ArApAdjustmentOffSetDTO;
import com.base.basesetup.dto.ArapAdjustmentsDTO;
import com.base.basesetup.dto.ArapDetailsDTO;
import com.base.basesetup.dto.BrsOpeningDTO;
import com.base.basesetup.dto.ChartCostCenterDTO;
import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.dto.DailyMonthlyExRatesDTO;
import com.base.basesetup.dto.DebitNoteDTO;
import com.base.basesetup.dto.FundTransferDTO;
import com.base.basesetup.dto.GeneralJournalDTO;
import com.base.basesetup.dto.GstSalesVoucherDTO;
import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.PaymentReversalDTO;
import com.base.basesetup.dto.PaymentVoucherDTO;
import com.base.basesetup.dto.ReceiptReversalDTO;
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
import com.base.basesetup.entity.GstSalesVoucherVO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.PaymentReversalVO;
import com.base.basesetup.entity.PaymentVoucherVO;
import com.base.basesetup.entity.ReceiptReversalVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface TransactionService {

//	TaxInvoice
	List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId);

	TaxInvoiceVO updateCreateTaxInvoice(@Valid TaxInvoiceDTO taxInvoiceDTO) throws ApplicationException;

	List<TaxInvoiceVO> getAllTaxInvoiceById(Long id);

	List<TaxInvoiceVO> getTaxInvoiceByActive();

	List<TaxInvoiceVO> getAllTaxInvoiceDocIdByOrgId(Long orgId);

	List<TaxInvoiceVO> getAllTaxInvoiceByDocId(Long orgId, String docId);

//	IrnCredit
	List<IrnCreditVO> getAllIrnCreditByOrgId(Long orgId);

	IrnCreditVO updateCreateIrnCredit(@Valid IrnCreditDTO irnCreditDTO) throws ApplicationException;

	List<IrnCreditVO> getAllIrnCreditById(Long id);

	List<IrnCreditVO> getIrnCreditByActive();

//	DailyMonthlyExRatesVO
	List<DailyMonthlyExRatesVO> getAllDailyMonthlyExRatesByOrgId(Long orgId);

	DailyMonthlyExRatesVO updateCreateDailyMonthlyExRates(@Valid DailyMonthlyExRatesDTO irnCreditDTO)
			throws ApplicationException;

	List<DailyMonthlyExRatesVO> getAllDailyMonthlyExRatesById(Long id);

	List<DailyMonthlyExRatesVO> getDailyMonthlyExRatesByActive();

//	BrsOpening
	List<BrsOpeningVO> getAllBrsOpeningByOrgId(Long orgId);

	BrsOpeningVO updateCreateBrsOpening(@Valid BrsOpeningDTO brsOpeningDTO) throws ApplicationException;

	List<BrsOpeningVO> getAllBrsOpeningById(Long id);

	List<BrsOpeningVO> getBrsOpeningByActive();

//	ChartCostCenter
	List<ChartCostCenterVO> getAllChartCostCenterByOrgId(Long orgId);

	ChartCostCenterVO updateCreateChartCostCenter(@Valid ChartCostCenterDTO chartCostCenterDTO)
			throws ApplicationException;

	List<ChartCostCenterVO> getAllChartCostCenterById(Long id);

	List<ChartCostCenterVO> getChartCostCenterByActive();

//	FundTransfer
	List<FundTransferVO> getAllFundTransferByOrgId(Long orgId);

	FundTransferVO updateCreateFundTransfer(@Valid FundTransferDTO fundTransferDTO) throws ApplicationException;

	List<FundTransferVO> getAllFundTransferById(Long id);

	List<FundTransferVO> getFundTransferByActive();

// GeneralJournal
	List<GeneralJournalVO> getAllGeneralJournalByOrgId(Long orgId);

	GeneralJournalVO updateCreateGeneralJournal(@Valid GeneralJournalDTO generalJournalDTO) throws ApplicationException;

	List<GeneralJournalVO> getAllGeneralJournalById(Long id);

	List<GeneralJournalVO> getGeneralJournalByActive();

// CostInvoice
	List<CostInvoiceVO> getAllCostInvoiceByOrgId(Long orgId);

	CostInvoiceVO updateCreateCostInvoice(@Valid CostInvoiceDTO costInvoiceDTO) throws ApplicationException;

	List<CostInvoiceVO> getAllCostInvoiceById(Long id);

	List<CostInvoiceVO> getCostInvoiceByActive();

// DebitNote
	List<DebitNoteVO> getAllDebitNoteByOrgId(Long orgId);

	DebitNoteVO updateCreateDebitNote(@Valid DebitNoteDTO debitNoteDTO) throws ApplicationException;

	List<DebitNoteVO> getAllDebitNoteById(Long id);

	List<DebitNoteVO> getDebitNoteByActive();

// GstSalesVoucher
	List<GstSalesVoucherVO> getAllGstSalesVoucherByOrgId(Long orgId);

	GstSalesVoucherVO updateCreateGstSalesVoucher(@Valid GstSalesVoucherDTO gstSalesVoucherDTO)
			throws ApplicationException;

	List<GstSalesVoucherVO> getAllGstSalesVoucherById(Long id);

	List<GstSalesVoucherVO> getGstSalesVoucherByActive();

// PaymentVoucher
	List<PaymentVoucherVO> getAllPaymentVoucherByOrgId(Long orgId);

	PaymentVoucherVO updateCreatePaymentVoucher(@Valid PaymentVoucherDTO paymentVoucherDTO) throws ApplicationException;

	List<PaymentVoucherVO> getAllPaymentVoucherById(Long id);

	List<PaymentVoucherVO> getPaymentVoucherByActive();

//	ArapDetails
	List<ArapDetailsVO> getAllArapDetailsByOrgId(Long orgId);

	ArapDetailsVO updateCreateArapDetails(@Valid ArapDetailsDTO arapDetailsDTO) throws ApplicationException;

	List<ArapDetailsVO> getAllArapDetailsById(Long id);

	List<ArapDetailsVO> getArapDetailsByActive();

//	ArapAdjustments
	List<ArapAdjustmentsVO> getAllArapAdjustmentsByOrgId(Long orgId);

	ArapAdjustmentsVO updateCreateArapAdjustments(@Valid ArapAdjustmentsDTO arapAdjustmentsDTO)
			throws ApplicationException;

	List<ArapAdjustmentsVO> getAllArapAdjustmentsById(Long id);

	List<ArapAdjustmentsVO> getArapAdjustmentsByActive();

//	ReceiptReversal
	List<ReceiptReversalVO> getAllReceiptReversalByOrgId(Long orgId);

	ReceiptReversalVO updateCreateReceiptReversal(@Valid ReceiptReversalDTO receiptReversalDTO)
			throws ApplicationException;

	List<ReceiptReversalVO> getAllReceiptReversalById(Long id);

	List<ReceiptReversalVO> getReceiptReversalByActive();

//	PaymentReversal
	List<PaymentReversalVO> getAllPaymentReversalByOrgId(Long orgId);

	PaymentReversalVO updateCreatePaymentReversal(@Valid PaymentReversalDTO paymentReversalDTO)
			throws ApplicationException;

	List<PaymentReversalVO> getAllPaymentReversalById(Long id);

	List<PaymentReversalVO> getPaymentReversalByActive();

//	ArApAdjustmentOffSet
	List<ArApAdjustmentOffSetVO> getAllArApAdjustmentOffSetByOrgId(Long orgId);

	ArApAdjustmentOffSetVO updateCreateArApAdjustmentOffSet(@Valid ArApAdjustmentOffSetDTO arApAdjustmentOffSetDTO)
			throws ApplicationException;

	List<ArApAdjustmentOffSetVO> getAllArApAdjustmentOffSetById(Long id);

	List<ArApAdjustmentOffSetVO> getArApAdjustmentOffSetByActive();
}