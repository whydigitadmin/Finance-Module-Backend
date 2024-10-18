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

import com.base.basesetup.dto.ArApBillBalanceReceivableDTO;
import com.base.basesetup.dto.ReceiptInvDetailsDTO;
import com.base.basesetup.dto.ReceiptDTO;
import com.base.basesetup.entity.ArApBillBalanceReceivableVO;
import com.base.basesetup.entity.ReceiptInvDetailsVO;
import com.base.basesetup.entity.ReceiptVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ArApBillBalanceRecievableRepo;
import com.base.basesetup.repo.ReceiptInvDetailsRepo;
import com.base.basesetup.repo.ReceiptRepo;

@Service
public class ARServiceImpl implements ARService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ARServiceImpl.class);

	@Autowired
	ReceiptRepo receiptReceivableRepo;

	@Autowired
	ReceiptInvDetailsRepo receiptInvDetailsRepo;

	@Autowired
	ArApBillBalanceRecievableRepo arApBillBalanceReceivableRepo;

	// Receipt
	@Override
	public List<ReceiptVO> getAllReceiptReceivableByOrgId(Long orgId) {
		List<ReceiptVO> receiptReceivableVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ReceiptReceivable BY OrgId : {}", orgId);
			receiptReceivableVO = receiptReceivableRepo.getAllReceiptReceivableByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ReceiptReceivable For All OrgId.");
			receiptReceivableVO = receiptReceivableRepo.findAll();
		}
		return receiptReceivableVO;
	}

	@Override
	public List<ReceiptVO> getAllReceiptReceivableById(Long id) {
		List<ReceiptVO> receiptReceivableVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ReceiptReceivable BY Id : {}", id);
			receiptReceivableVO = receiptReceivableRepo.getAllReceiptReceivableById(id);
		} else {
			LOGGER.info("Successfully Received ReceiptReceivable For All Id.");
			receiptReceivableVO = receiptReceivableRepo.findAll();
		}
		return receiptReceivableVO;
	}

	@Override
	public ReceiptVO updateCreateReceiptReceivable(@Valid ReceiptDTO receiptDTO)
			throws ApplicationException {
		ReceiptVO receiptVO = new ReceiptVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(receiptDTO.getId())) {
			isUpdate = true;
			receiptVO = receiptReceivableRepo.findById(receiptDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReceiptReceivable details"));
			receiptVO.setUpdatedBy(receiptDTO.getCreatedBy());
		} else {
			receiptVO.setUpdatedBy(receiptDTO.getCreatedBy());
			receiptVO.setCreatedBy(receiptDTO.getCreatedBy());
		}

		List<ReceiptInvDetailsVO> particularsAccountReceiptVOs = new ArrayList<>();
		if (receiptDTO.getReceiptInvDetailaDTO() != null) {
			for (ReceiptInvDetailsDTO particularsAccountReceiptDTO : receiptDTO.getReceiptInvDetailaDTO()) {
				ReceiptInvDetailsVO particularsAccountReceiptVO;
				if (particularsAccountReceiptDTO.getId() != null
						& ObjectUtils.isEmpty(particularsAccountReceiptDTO.getId())) {
					particularsAccountReceiptVO = receiptInvDetailsRepo.findById(particularsAccountReceiptDTO.getId())
							.orElse(new ReceiptInvDetailsVO());
				} else {
					particularsAccountReceiptVO = new ReceiptInvDetailsVO();
				}
				particularsAccountReceiptVO.setInvNo(particularsAccountReceiptDTO.getInvNo());
				particularsAccountReceiptVO.setInvDate(particularsAccountReceiptDTO.getInvDate());
				particularsAccountReceiptVO.setRefNo(particularsAccountReceiptDTO.getRefNo());
				particularsAccountReceiptVO.setRefDate(particularsAccountReceiptDTO.getRefDate());
				particularsAccountReceiptVO.setMasterRef(particularsAccountReceiptDTO.getMasterRef());
				particularsAccountReceiptVO.setHouseRef(particularsAccountReceiptDTO.getHouseRef());
				particularsAccountReceiptVO.setCurrency(particularsAccountReceiptDTO.getCurrency());
				particularsAccountReceiptVO.setExRate(particularsAccountReceiptDTO.getExRate());
				particularsAccountReceiptVO.setAmount(particularsAccountReceiptDTO.getAmount());
				particularsAccountReceiptVO.setChargeAmt(particularsAccountReceiptDTO.getChargeAmt());
				particularsAccountReceiptVO.setOutstanding(particularsAccountReceiptDTO.getOutstanding());
				particularsAccountReceiptVO.setSettled(particularsAccountReceiptDTO.getSettled());
				particularsAccountReceiptVO.setRecExRate(particularsAccountReceiptDTO.getRecExRate());
				particularsAccountReceiptVO.setTxnSettled(particularsAccountReceiptDTO.getTxnSettled());
				particularsAccountReceiptVO.setGainAmt(particularsAccountReceiptDTO.getGainAmt());
				particularsAccountReceiptVO.setFromDate(particularsAccountReceiptDTO.getFromDate());
				particularsAccountReceiptVO.setToDate(particularsAccountReceiptDTO.getToDate());
				particularsAccountReceiptVO.setReceiptVO(receiptVO);
				particularsAccountReceiptVOs.add(particularsAccountReceiptVO);
			}
		}
		receiptVO.setReceiptInvDetailsVO(particularsAccountReceiptVOs);
		getReceiptReceivableVOFromReceiptReceivableDTO(receiptDTO, receiptVO);
		return receiptReceivableRepo.save(receiptVO);
	}

	private void getReceiptReceivableVOFromReceiptReceivableDTO(@Valid ReceiptDTO receiptDTO,
			ReceiptVO receiptVO) {
		receiptVO.setBranch(receiptDTO.getBranch());
		receiptVO.setBranchCode(receiptDTO.getBranchCode());
		receiptVO.setCustomer(receiptDTO.getCustomer());
		receiptVO.setClient(receiptDTO.getClient());
		receiptVO.setCreatedBy(receiptDTO.getCreatedBy());
		receiptVO.setActive(receiptDTO.isActive());
		receiptVO.setCancel(receiptDTO.isCancel());
		receiptVO.setCancelRemarks(receiptDTO.getCancelRemarks());
		receiptVO.setFinYear(receiptDTO.getFinYear());
		receiptVO.setScreenCode("AR");
		receiptVO.setScreenName("RECEIPT");
		receiptVO.setIpNo(receiptDTO.getIpNo());
		receiptVO.setLatitude(receiptDTO.getLatitude());
		receiptVO.setType(receiptDTO.getType());
		receiptVO.setCustomerName(receiptDTO.getCustomerName());
		receiptVO.setCustomerCode(receiptDTO.getCustomerCode());
		receiptVO.setBankCashAcc(receiptDTO.getBankCashAcc());
		receiptVO.setReceiptAmt(receiptDTO.getReceiptAmt());
		receiptVO.setBankChargeAcc(receiptDTO.getBankChargeAcc());
		receiptVO.setBankCharges(receiptDTO.getBankCharges());
		receiptVO.setInCurrencyBnkChargs(receiptDTO.getInCurrencyBnkChargs());
		receiptVO.setTdsAmt(receiptDTO.getTdsAmt());
		receiptVO.setInCurrencyTdsAmt(receiptDTO.getInCurrencyTdsAmt());
		receiptVO.setChequeBank(receiptDTO.getChequeBank());
		receiptVO.setReceiptType(receiptDTO.getReceiptType());
		receiptVO.setChequeUtiNo(receiptDTO.getChequeUtiNo());
		receiptVO.setChequeUtiDt(receiptDTO.getChequeUtiDt());
		receiptVO.setReceivedFrom(receiptDTO.getReceivedFrom());
		receiptVO.setReceiptType1(receiptDTO.getReceiptType1());
		receiptVO.setCurrency(receiptDTO.getCurrency());
		receiptVO.setCurrencyAmount(receiptDTO.getCurrencyAmount());
		receiptVO.setTaxAmt(receiptDTO.getTaxAmt());
		receiptVO.setBranchCode(receiptDTO.getBranchCode());
		receiptVO.setOrgId(receiptDTO.getOrgId());
		receiptVO.setNetAmount(receiptDTO.getNetAmount());
		receiptVO.setRemarks(receiptDTO.getRemarks());

	}

	@Override
	public List<ReceiptVO> getReceiptReceivableByActive() {
		return receiptReceivableRepo.findReceiptReceivablesByActive();
	}

	@Override
	public List<Map<String, Object>> getCustomerNameAndCodeForReceipt(Long orgId, String branch, String branchCode,
			String finYear) {
		Set<Object[]> customerName = receiptReceivableRepo.getCustomerNameAndCodeForReceipt(orgId, branch, branchCode,
				finYear);
		return getCustomerName(customerName);
	}

	private List<Map<String, Object>> getCustomerName(Set<Object[]> customer) {
		List<Map<String, Object>> doctypeMappingDetails = new ArrayList<>();
		for (Object[] sup : customer) {
			Map<String, Object> doctype = new HashMap<>();
			doctype.put("customerName", sup[0] != null ? sup[0].toString() : "");
			doctype.put("customerCode", sup[1] != null ? sup[1].toString() : "");
			doctypeMappingDetails.add(doctype);
		}

		return doctypeMappingDetails;
	}

	// ArApBillBalance
	@Override
	public List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableByOrgId(Long orgId) {
		List<ArApBillBalanceReceivableVO> arApBillBalanceReceivableVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received ArApBillBalance BY OrgId : {}", orgId);
			arApBillBalanceReceivableVO = arApBillBalanceReceivableRepo.getAllArApBillBalanceReceivableByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ReceiptReceivable For All OrgId.");
			arApBillBalanceReceivableVO = arApBillBalanceReceivableRepo.findAll();
		}
		return arApBillBalanceReceivableVO;
	}

	@Override
	public List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableById(Long id) {
		List<ArApBillBalanceReceivableVO> arApBillBalanceReceivableVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received ArApBillBalance BY Id : {}", id);
			arApBillBalanceReceivableVO = arApBillBalanceReceivableRepo.getAllArApBillBalanceReceivableById(id);
		} else {
			LOGGER.info("Successfully Received ArApBillBalance For All Id.");
			arApBillBalanceReceivableVO = arApBillBalanceReceivableRepo.findAll();
		}
		return arApBillBalanceReceivableVO;
	}

	@Override
	public ArApBillBalanceReceivableVO updateCreateArApBillBalanceReceivable(
			@Valid ArApBillBalanceReceivableDTO arApBillBalanceReceivableDTO) throws ApplicationException {
		ArApBillBalanceReceivableVO arApBillBalanceReceivableVO = new ArApBillBalanceReceivableVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(arApBillBalanceReceivableDTO.getId())) {
			isUpdate = true;
			arApBillBalanceReceivableVO = arApBillBalanceReceivableRepo.findById(arApBillBalanceReceivableDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ArApBillBalance details"));
			arApBillBalanceReceivableVO.setUpdatedBy(arApBillBalanceReceivableDTO.getCreatedBy());
		} else {
			arApBillBalanceReceivableVO.setUpdatedBy(arApBillBalanceReceivableDTO.getCreatedBy());
			arApBillBalanceReceivableVO.setCreatedBy(arApBillBalanceReceivableDTO.getCreatedBy());
		}

		getArApBillBalanceReceivableVOFromArApBillBalanceReceivableDTO(arApBillBalanceReceivableDTO,
				arApBillBalanceReceivableVO);
		return arApBillBalanceReceivableRepo.save(arApBillBalanceReceivableVO);
	}

	private void getArApBillBalanceReceivableVOFromArApBillBalanceReceivableDTO(
			@Valid ArApBillBalanceReceivableDTO arApBillBalanceReceivableDTO,
			ArApBillBalanceReceivableVO arApBillBalanceReceivableVO) {
		arApBillBalanceReceivableVO.setAccName(arApBillBalanceReceivableDTO.getAccName());
		arApBillBalanceReceivableVO.setPartyName(arApBillBalanceReceivableDTO.getPartyName());
		arApBillBalanceReceivableVO.setPartyCode(arApBillBalanceReceivableDTO.getPartyCode());
		arApBillBalanceReceivableVO.setCreditDays(arApBillBalanceReceivableDTO.getCreditDays());
		arApBillBalanceReceivableVO.setDocType(arApBillBalanceReceivableDTO.getDocType());
		arApBillBalanceReceivableVO.setCurrency(arApBillBalanceReceivableDTO.getCurrency());
		arApBillBalanceReceivableVO.setYearEndExRate(arApBillBalanceReceivableDTO.getYearEndExRate());
		arApBillBalanceReceivableVO.setBillExRate(arApBillBalanceReceivableDTO.getBillExRate());
		arApBillBalanceReceivableVO.setPostBillExRate(arApBillBalanceReceivableDTO.isPostBillExRate());
		arApBillBalanceReceivableVO.setBillNo(arApBillBalanceReceivableDTO.getBillNo());
		arApBillBalanceReceivableVO.setBillDate(arApBillBalanceReceivableDTO.getBillDate());
		arApBillBalanceReceivableVO.setSuppRefNo(arApBillBalanceReceivableDTO.getSuppRefNo());
		arApBillBalanceReceivableVO.setSuppRefDate(arApBillBalanceReceivableDTO.getSuppRefDate());
		arApBillBalanceReceivableVO.setDueDate(arApBillBalanceReceivableDTO.getDueDate());
		arApBillBalanceReceivableVO.setDebitAmt(arApBillBalanceReceivableDTO.getDebitAmt());
		arApBillBalanceReceivableVO.setCreditAmt(arApBillBalanceReceivableDTO.getCreditAmt());
		arApBillBalanceReceivableVO.setVoucherNo(arApBillBalanceReceivableDTO.getVoucherNo());
		arApBillBalanceReceivableVO.setAdjustmentDone(arApBillBalanceReceivableDTO.isAdjustmentDone());
		arApBillBalanceReceivableVO.setActive(arApBillBalanceReceivableDTO.isActive());
		arApBillBalanceReceivableVO.setBranch(arApBillBalanceReceivableDTO.getBranch());
		arApBillBalanceReceivableVO.setBranchCode(arApBillBalanceReceivableDTO.getBranchCode());
		arApBillBalanceReceivableVO.setCreatedBy(arApBillBalanceReceivableDTO.getCreatedBy());
		arApBillBalanceReceivableVO.setCancel(arApBillBalanceReceivableDTO.isCancel());
		arApBillBalanceReceivableVO.setCancelRemarks(arApBillBalanceReceivableDTO.getCancelRemarks());
		arApBillBalanceReceivableVO.setFinYear(arApBillBalanceReceivableDTO.getFinYear());
		arApBillBalanceReceivableVO.setScreenCode(arApBillBalanceReceivableDTO.getScreenCode());
		arApBillBalanceReceivableVO.setScreenName(arApBillBalanceReceivableDTO.getScreenName());
		arApBillBalanceReceivableVO.setIpNo(arApBillBalanceReceivableDTO.getIpNo());
		arApBillBalanceReceivableVO.setLatitude(arApBillBalanceReceivableDTO.getLatitude());
		arApBillBalanceReceivableVO.setOrgId(arApBillBalanceReceivableDTO.getOrgId());
	}

	@Override
	public List<ArApBillBalanceReceivableVO> getArApBillBalanceReceivableByActive() {
		return arApBillBalanceReceivableRepo.findArApBillBalanceReceivableByActive();
	}

	@Override
	public List<Map<String, Object>> getAllReceiptRegister(Long orgId, String branch, String branchCode, String finYear,
			String fromDate, String toDate, String subLedgerName) {
		Set<Object[]> register = receiptReceivableRepo.findAllReceiptRegister(orgId, branch, branchCode,
				finYear, fromDate, toDate, subLedgerName);
		return getRegister(register);
	}

	private List<Map<String, Object>> getRegister(Set<Object[]> getRegister) {
		List<Map<String, Object>> doctypeMappingDetails = new ArrayList<>();
		for (Object[] sup : getRegister) {
			Map<String, Object> doctype = new HashMap<>();
			doctype.put("docId", sup[0] != null ? sup[0].toString() : "");
			doctype.put("docDate", sup[1] != null ? sup[1].toString() : "");
			doctype.put("subLedgerName", sup[2] != null ? sup[2].toString() : "");
			doctype.put("bankCash", sup[3] != null ? sup[3].toString() : "");
			doctype.put("receiptAmount", sup[4] != null ? sup[4].toString() : "");
			doctype.put("bankCharges", sup[5] != null ? sup[5].toString() : "");
			doctype.put("taxAmount", sup[6] != null ? sup[6].toString() : "");
			doctype.put("tdsAmount", sup[7] != null ? sup[7].toString() : "");
			doctype.put("invoiceNo", sup[8] != null ? sup[8].toString() : "");
			doctype.put("invoiceDate", sup[9] != null ? sup[9].toString() : "");
			doctype.put("refNo", sup[10] != null ? sup[10].toString() : "");
			doctype.put("refDate", sup[11] != null ? sup[11].toString() : "");
			doctype.put("chequeBank", sup[12] != null ? sup[12].toString() : "");
			doctype.put("chequeNo", sup[13] != null ? sup[13].toString() : "");
			doctype.put("amount", sup[14] != null ? sup[14].toString() : "");
			doctype.put("outstanding", sup[15] != null ? sup[15].toString() : "");
			doctype.put("setteled", sup[16] != null ? sup[16].toString() : "");
			doctype.put("createdOn", sup[17] != null ? sup[17].toString() : "");
			doctype.put("createdBy", sup[18] != null ? sup[18].toString() : "");
			
			
			doctypeMappingDetails.add(doctype);
		}

		return doctypeMappingDetails;
	}

}
