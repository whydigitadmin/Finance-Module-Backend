package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ArApBillBalanceReceivableDTO;
import com.base.basesetup.dto.ReceiptInvDetailsDTO;
import com.base.basesetup.dto.ReceiptReceivableDTO;
import com.base.basesetup.entity.ArApBillBalanceReceivableVO;
import com.base.basesetup.entity.ReceiptInvDetailsVO;
import com.base.basesetup.entity.ReceiptReceivableVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ArApBillBalanceRecievableRepo;
import com.base.basesetup.repo.ReceiptInvDetailsRepo;
import com.base.basesetup.repo.ReceiptReceivableRepo;

@Service
public class ARReceivableServiceImpl implements ARReceivableService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ARReceivableServiceImpl.class);

	@Autowired
	ReceiptReceivableRepo receiptReceivableRepo;

	@Autowired
	ReceiptInvDetailsRepo receiptInvDetailsRepo;

	@Autowired
	ArApBillBalanceRecievableRepo arApBillBalanceReceivableRepo;

	// Receipt
	@Override
	public List<ReceiptReceivableVO> getAllReceiptReceivableByOrgId(Long orgId) {
		List<ReceiptReceivableVO> receiptReceivableVO = new ArrayList<>();
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
	public List<ReceiptReceivableVO> getAllReceiptReceivableById(Long id) {
		List<ReceiptReceivableVO> receiptReceivableVO = new ArrayList<>();
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
	public ReceiptReceivableVO updateCreateReceiptReceivable(@Valid ReceiptReceivableDTO receiptReceivableDTO)
			throws ApplicationException {
		ReceiptReceivableVO receiptReceivableVO = new ReceiptReceivableVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(receiptReceivableDTO.getId())) {
			isUpdate = true;
			receiptReceivableVO = receiptReceivableRepo.findById(receiptReceivableDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ReceiptReceivable details"));
			receiptReceivableVO.setUpdatedBy(receiptReceivableDTO.getCreatedBy());
		} else {
			receiptReceivableVO.setUpdatedBy(receiptReceivableDTO.getCreatedBy());
			receiptReceivableVO.setCreatedBy(receiptReceivableDTO.getCreatedBy());
		}

		List<ReceiptInvDetailsVO> particularsAccountReceiptVOs = new ArrayList<>();
		if (receiptReceivableDTO.getReceiptInvDetailaDTO() != null) {
			for (ReceiptInvDetailsDTO particularsAccountReceiptDTO : receiptReceivableDTO.getReceiptInvDetailaDTO()) {
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
				particularsAccountReceiptVO.setReceiptReceivableVO(receiptReceivableVO);
				particularsAccountReceiptVOs.add(particularsAccountReceiptVO);
			}
		}
		receiptReceivableVO.setReceiptInvDetailsVO(particularsAccountReceiptVOs);
		getReceiptReceivableVOFromReceiptReceivableDTO(receiptReceivableDTO, receiptReceivableVO);
		return receiptReceivableRepo.save(receiptReceivableVO);
	}

	private void getReceiptReceivableVOFromReceiptReceivableDTO(@Valid ReceiptReceivableDTO receiptReceivableDTO,
			ReceiptReceivableVO receiptReceivableVO) {
		receiptReceivableVO.setBranch(receiptReceivableDTO.getBranch());
		receiptReceivableVO.setBranchCode(receiptReceivableDTO.getBranchCode());
		receiptReceivableVO.setCustomer(receiptReceivableDTO.getCustomer());
		receiptReceivableVO.setClient(receiptReceivableDTO.getClient());
		receiptReceivableVO.setCreatedBy(receiptReceivableDTO.getCreatedBy());
		receiptReceivableVO.setActive(receiptReceivableDTO.isActive());
		receiptReceivableVO.setCancel(receiptReceivableDTO.isCancel());
		receiptReceivableVO.setCancelRemarks(receiptReceivableDTO.getCancelRemarks());
		receiptReceivableVO.setFinYear(receiptReceivableDTO.getFinYear());
		receiptReceivableVO.setScreenCode("AR");
		receiptReceivableVO.setScreenName("RECEIPT");
		receiptReceivableVO.setIpNo(receiptReceivableDTO.getIpNo());
		receiptReceivableVO.setLatitude(receiptReceivableDTO.getLatitude());
		receiptReceivableVO.setType(receiptReceivableDTO.getType());
		receiptReceivableVO.setCustomerName(receiptReceivableDTO.getCustomerName());
		receiptReceivableVO.setCustomerCode(receiptReceivableDTO.getCustomerCode());
		receiptReceivableVO.setBankCashAcc(receiptReceivableDTO.getBankCashAcc());
		receiptReceivableVO.setReceiptAmt(receiptReceivableDTO.getReceiptAmt());
		receiptReceivableVO.setBankChargeAcc(receiptReceivableDTO.getBankChargeAcc());
		receiptReceivableVO.setBankCharges(receiptReceivableDTO.getBankCharges());
		receiptReceivableVO.setInCurrencyBnkChargs(receiptReceivableDTO.getInCurrencyBnkChargs());
		receiptReceivableVO.setTdsAmt(receiptReceivableDTO.getTdsAmt());
		receiptReceivableVO.setInCurrencyTdsAmt(receiptReceivableDTO.getInCurrencyTdsAmt());
		receiptReceivableVO.setChequeBank(receiptReceivableDTO.getChequeBank());
		receiptReceivableVO.setReceiptType(receiptReceivableDTO.getReceiptType());
		receiptReceivableVO.setChequeUtiNo(receiptReceivableDTO.getChequeUtiNo());
		receiptReceivableVO.setChequeUtiDt(receiptReceivableDTO.getChequeUtiDt());
		receiptReceivableVO.setReceivedFrom(receiptReceivableDTO.getReceivedFrom());
	}

	@Override
	public List<ReceiptReceivableVO> getReceiptReceivableByActive() {
		return receiptReceivableRepo.findReceiptReceivablesByActive();
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
		arApBillBalanceReceivableVO.setBranch(arApBillBalanceReceivableDTO.getBranch());
		arApBillBalanceReceivableVO.setPartyName(arApBillBalanceReceivableDTO.getPartyName());
		arApBillBalanceReceivableVO.setPartyCode(arApBillBalanceReceivableDTO.getPartyCode());
		arApBillBalanceReceivableVO.setCurrency(arApBillBalanceReceivableDTO.getCurrency());
		arApBillBalanceReceivableVO.setCreditDays(arApBillBalanceReceivableDTO.getCreditDays());
		arApBillBalanceReceivableVO.setBillNo(arApBillBalanceReceivableDTO.getBillNo());
		arApBillBalanceReceivableVO.setBillDate(arApBillBalanceReceivableDTO.getBillDate());
		arApBillBalanceReceivableVO.setBillExRate(arApBillBalanceReceivableDTO.getBillExRate());
		arApBillBalanceReceivableVO.setSupplierRefNo(arApBillBalanceReceivableDTO.getSupplierRefNo());
		arApBillBalanceReceivableVO.setSupplierRefDate(arApBillBalanceReceivableDTO.getSupplierRefDate());
		arApBillBalanceReceivableVO.setDueDate(arApBillBalanceReceivableDTO.getDueDate());
		arApBillBalanceReceivableVO.setDebitAmount(arApBillBalanceReceivableDTO.getDebitAmount());
		arApBillBalanceReceivableVO.setCreditAmount(arApBillBalanceReceivableDTO.getCreditAmount());
		arApBillBalanceReceivableVO.setActive(arApBillBalanceReceivableDTO.isActive());
		arApBillBalanceReceivableVO.setOrgId(arApBillBalanceReceivableDTO.getOrgId());
	}

	@Override
	public List<ArApBillBalanceReceivableVO> getArApBillBalanceReceivableByActive() {
		return arApBillBalanceReceivableRepo.findArApBillBalanceReceivableByActive();
	}

}
