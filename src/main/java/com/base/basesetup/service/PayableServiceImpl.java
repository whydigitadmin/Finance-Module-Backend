package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

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
public class PayableServiceImpl implements PayableService{

	public static final Logger LOGGER = LoggerFactory.getLogger(PayableServiceImpl.class);


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
	public PaymentVO updateCreatePayment(
			@Valid PaymentDTO paymentDTO) throws ApplicationException {
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

		 if(ObjectUtils.isNotEmpty(paymentDTO.getId())){
				List<PaymentInvDtlsVO> paymentInvDtlsVOList = paymentInvDtlsRepo.findByPaymentVO(paymentVO);
				paymentInvDtlsRepo.deleteAll(paymentInvDtlsVOList);
				 }
				List<PaymentInvDtlsVO> paymentInvDtlsVOs= new ArrayList<>();
				if(paymentDTO.getPaymentInvDtlsDTO()!=null) {
					for(PaymentInvDtlsDTO paymentInvDtlsDTO : paymentDTO.getPaymentInvDtlsDTO()) {
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

						paymentInvDtlsVO.setPaymentVO(paymentVO);;
						paymentInvDtlsVOs.add(paymentInvDtlsVO);
						
					}
				}
		getPaymentVOFromPaymentDTO(paymentDTO,paymentVO);
		paymentVO.setPaymentInvDtlsVO(paymentInvDtlsVOs);;
		return paymentRepo.save(paymentVO);
	}

	private void getPaymentVOFromPaymentDTO(
			@Valid PaymentDTO paymentDTO, PaymentVO paymentVO) {

		paymentVO.setDocId(paymentDTO.getDocId());
		paymentVO.setDocDate(paymentDTO.getDocDate());
		paymentVO.setType(paymentDTO.getType());
		paymentVO.setPartyCode(paymentDTO.getPartyCode());
		paymentVO.setPartyName(paymentDTO.getPartyName());
		paymentVO.setGstState(paymentDTO.getGstState());
		paymentVO.setGstn(paymentDTO.getGstn());
		paymentVO.setBankCashAcc(paymentDTO.getBankCashAcc());
		paymentVO.setPaymentAmt(paymentDTO.getPaymentAmt());
		paymentVO.setTdsAcc(paymentDTO.getTdsAcc());
		paymentVO.setTdsAmt(paymentDTO.getTdsAmt());
		paymentVO.setBankChargeAcc(paymentDTO.getBankChargeAcc());
		paymentVO.setServiceTaxAmt(paymentDTO.getServiceTaxAmt());
		paymentVO.setChequeBank(paymentDTO.getChequeBank());
		paymentVO.setChequeNo(paymentDTO.getChequeNo());
		paymentVO.setPayTo(paymentDTO.getPayTo());
		paymentVO.setCurrency(paymentDTO.getCurrency());
		paymentVO.setCurrencyAmt(paymentDTO.getCurrencyAmt());
		paymentVO.setBranch(paymentDTO.getBranch());
		paymentVO.setBranchCode(paymentDTO.getBranchCode());
		paymentVO.setActive(paymentDTO.isActive());
		paymentVO.setCancel(paymentDTO.isCancel());
		paymentVO.setCancelRemarks(paymentDTO.getCancelRemarks());
		paymentVO.setFinYear(paymentDTO.getFinYear());
		paymentVO.setScreenCode("AP");
		paymentVO.setScreenName("PAYMENT");
		paymentVO.setIpNo(paymentDTO.getIpNo());
		paymentVO.setLatitude(paymentDTO.getLatitude());
		paymentVO.setOrgId(paymentDTO.getOrgId());

	}


}
