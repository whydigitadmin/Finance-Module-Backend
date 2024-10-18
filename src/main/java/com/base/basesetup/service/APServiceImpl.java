package com.base.basesetup.service;

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

import com.base.basesetup.dto.PaymentDTO;
import com.base.basesetup.dto.PaymentInvDtlsDTO;
import com.base.basesetup.entity.PaymentInvDtlsVO;
import com.base.basesetup.entity.PaymentVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.PaymentInvDtlsRepo;
import com.base.basesetup.repo.PaymentRepo;

@Service
public class APServiceImpl implements APService {

	public static final Logger LOGGER = LoggerFactory.getLogger(APServiceImpl.class);

	@Autowired
	PaymentRepo paymentRepo;

	@Autowired
	PaymentInvDtlsRepo paymentInvDtlsRepo;

	@Override
	public List<PaymentVO> getAllPaymentByOrgId(Long orgId) {
		List<PaymentVO> paymentVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  Payment BY OrgId: {}", orgId);
			paymentVO = paymentRepo.getAllPaymentByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  Payment For All OrgId.");
			paymentVO = paymentRepo.findAll();
		}
		return paymentVO;
	}

	@Override
	public List<PaymentVO> getAllPaymentById(Long id) {
		List<PaymentVO> paymentVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  Payment BY Id : {}", id);
			paymentVO = paymentRepo.getAllPaymentById(id);
		} else {
			LOGGER.info("Successfully Received  Payment For All Id.");
			paymentVO = paymentRepo.findAll();
		}
		return paymentVO;
	}

	@Override
	public PaymentVO updateCreatePayment(@Valid PaymentDTO paymentDTO) throws ApplicationException {
		PaymentVO paymentVO = new PaymentVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(paymentDTO.getId())) {
			isUpdate = true;
			paymentVO = paymentRepo.findById(paymentDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Payment details"));
			paymentVO.setUpdatedBy(paymentDTO.getCreatedBy());

		} else {
			paymentVO.setUpdatedBy(paymentDTO.getCreatedBy());
			paymentVO.setCreatedBy(paymentDTO.getCreatedBy());

		}

		if (ObjectUtils.isNotEmpty(paymentDTO.getId())) {
			List<PaymentInvDtlsVO> paymentInvDtlsVOList = paymentInvDtlsRepo.findByPaymentVO(paymentVO);
			paymentInvDtlsRepo.deleteAll(paymentInvDtlsVOList);
		}
		List<PaymentInvDtlsVO> paymentInvDtlsVOs = new ArrayList<>();
		if (paymentDTO.getPaymentInvDtlsDTO() != null) {
			for (PaymentInvDtlsDTO paymentInvDtlsDTO : paymentDTO.getPaymentInvDtlsDTO()) {
				PaymentInvDtlsVO paymentInvDtlsVO = new PaymentInvDtlsVO();
				paymentInvDtlsVO.setInvNo(paymentInvDtlsDTO.getInvNo());
				paymentInvDtlsVO.setInvDate(paymentInvDtlsDTO.getInvDate());
				paymentInvDtlsVO.setRefNo(paymentInvDtlsDTO.getRefNo());
				paymentInvDtlsVO.setRefDate(paymentInvDtlsDTO.getRefDate());
				paymentInvDtlsVO.setSupplierRefNo(paymentInvDtlsDTO.getSupplierRefNo());
				paymentInvDtlsVO.setSupplierRefDate(paymentInvDtlsDTO.getSupplierRefDate());
				paymentInvDtlsVO.setCurrency(paymentInvDtlsDTO.getCurrency());
				paymentInvDtlsVO.setExRate(paymentInvDtlsDTO.getExRate());
				paymentInvDtlsVO.setAmount(paymentInvDtlsDTO.getAmount());
				paymentInvDtlsVO.setChargeAmt(paymentInvDtlsDTO.getChargeAmt());
				paymentInvDtlsVO.setOutstanding(paymentInvDtlsDTO.getOutstanding());
				paymentInvDtlsVO.setSettled(paymentInvDtlsDTO.getSettled());
				paymentInvDtlsVO.setPayExRate(paymentInvDtlsDTO.getPayExRate());
				paymentInvDtlsVO.setTxnSettled(paymentInvDtlsDTO.getTxnSettled());
				paymentInvDtlsVO.setGainOrLossAmt(paymentInvDtlsDTO.getGainOrLossAmt());
				paymentInvDtlsVO.setRemarks(paymentInvDtlsDTO.getRemarks());
				paymentInvDtlsVO.setFromDate(paymentInvDtlsDTO.getFromDate());
				paymentInvDtlsVO.setToDate(paymentInvDtlsDTO.getToDate());

				paymentInvDtlsVO.setPaymentVO(paymentVO);
				;
				paymentInvDtlsVOs.add(paymentInvDtlsVO);

			}
		}
		getPaymentVOFromPaymentDTO(paymentDTO, paymentVO);
		paymentVO.setPaymentInvDtlsVO(paymentInvDtlsVOs);
		;
		return paymentRepo.save(paymentVO);
	}

	private void getPaymentVOFromPaymentDTO(@Valid PaymentDTO paymentDTO, PaymentVO paymentVO) {

		paymentVO.setPaymentType(paymentDTO.getPaymentType());
		paymentVO.setBankChargeAcc(paymentDTO.getBankChargeAcc());
		
		paymentVO.setBankCharges(paymentDTO.getBankCharges());
		paymentVO.setBankInCurrency(paymentDTO.getBankInCurrency());
		paymentVO.setType(paymentDTO.getType());
		paymentVO.setPartyCode(paymentDTO.getPartyCode());
		paymentVO.setServiceTaxAmt(paymentDTO.getServiceTaxAmt());
		paymentVO.setSTaxInCurrency(paymentDTO.getSTaxInCurrency());
		paymentVO.setPartyName(paymentDTO.getPartyName());
		paymentVO.setChequeBank(paymentDTO.getChequeBank());
		paymentVO.setGstState(paymentDTO.getGstState());
		paymentVO.setGstIn(paymentDTO.getGstIn());
		paymentVO.setChequeNo(paymentDTO.getChequeNo());
		paymentVO.setChequeDate(paymentDTO.getChequeDate());
		paymentVO.setBankCashAcc(paymentDTO.getBankCashAcc());
		paymentVO.setPayTo(paymentDTO.getPayTo());
		paymentVO.setPaymentAmt(paymentDTO.getPaymentAmt());
		paymentVO.setTdsAcc(paymentDTO.getTdsAcc());
		paymentVO.setTdsAmt(paymentDTO.getTdsAmt());
		paymentVO.setCurrency(paymentDTO.getCurrency());
		paymentVO.setCurrencyAmt(paymentDTO.getCurrencyAmt());
		paymentVO.setReceiptAmt(paymentDTO.getReceiptAmt());

		paymentVO.setBranch(paymentDTO.getBranch());
		paymentVO.setBranchCode(paymentDTO.getBranchCode());
		paymentVO.setActive(paymentDTO.isActive());
		paymentVO.setCancel(paymentDTO.isCancel());
		paymentVO.setCancelRemarks(paymentDTO.getCancelRemarks());
		paymentVO.setFinYear(paymentDTO.getFinYear());

		paymentVO.setIpNo(paymentDTO.getIpNo());
		paymentVO.setLatitude(paymentDTO.getLatitude());
		paymentVO.setOrgId(paymentDTO.getOrgId());

	}

	// Payment Service
	@Override
	public List<Map<String, Object>> getAllPaymentRegister(Long orgId, String branch, String branchCode, String finYear,
			String fromDate, String toDate, String subLedgerName) {
		Set<Object[]> payment = paymentRepo.findAllPaymentRegister(orgId, branch, branchCode, finYear, fromDate, toDate,
				subLedgerName);
		return getPayment(payment);
	}

	private List<Map<String, Object>> getPayment(Set<Object[]> getRegister) {
		List<Map<String, Object>> doctypeMappingDetails = new ArrayList<>();
		for (Object[] sup : getRegister) {
			Map<String, Object> doctype = new HashMap<>();
			doctype.put("docId", sup[0] != null ? sup[0].toString() : "");
			doctype.put("docDate", sup[1] != null ? sup[1].toString() : "");
			doctype.put("subLedgerName", sup[2] != null ? sup[2].toString() : "");
			doctype.put("bankCash", sup[3] != null ? sup[3].toString() : "");
			doctype.put("receiptAmount", sup[4] != null ? sup[4].toString() : "");
			doctype.put("bankCharges", sup[5] != null ? sup[5].toString() : "");
			doctype.put("tdsAmount", sup[6] != null ? sup[6].toString() : "");
			doctype.put("chequeBank", sup[7] != null ? sup[7].toString() : "");
			doctype.put("chequeNo", sup[8] != null ? sup[8].toString() : "");
			doctype.put("invoiceNo", sup[9] != null ? sup[9].toString() : "");
			doctype.put("invoiceDate", sup[10] != null ? sup[10].toString() : "");
			doctype.put("refNo", sup[11] != null ? sup[11].toString() : "");
			doctype.put("refDate", sup[12] != null ? sup[12].toString() : "");
			doctype.put("amount", sup[13] != null ? sup[13].toString() : "");
			doctype.put("outstanding", sup[14] != null ? sup[14].toString() : "");
			doctype.put("setteled", sup[15] != null ? sup[15].toString() : "");
			doctype.put("createdOn", sup[16] != null ? sup[16].toString() : "");
			doctype.put("createdBy", sup[18] != null ? sup[18].toString() : "");

			doctypeMappingDetails.add(doctype);
		}

		return doctypeMappingDetails;
	}

}
