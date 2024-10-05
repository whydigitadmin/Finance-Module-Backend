package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.AccountParticularsDTO;
import com.base.basesetup.dto.PaymentDTO;
import com.base.basesetup.entity.AccountParticularsVO;
import com.base.basesetup.entity.PaymentVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.AccountParticularsRepo;
import com.base.basesetup.repo.PaymentRepo;

@Service
public class PayableServiceImpl implements PayableService{

	public static final Logger LOGGER = LoggerFactory.getLogger(PayableServiceImpl.class);


	@Autowired
	PaymentRepo paymentRepo;
	
	@Autowired
	AccountParticularsRepo accountParticularsRepo;
	
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
				List<AccountParticularsVO> accountParticularsVOList = accountParticularsRepo.findByPaymentVO(paymentVO);
				accountParticularsRepo.deleteAll(accountParticularsVOList);
				 }
				List<AccountParticularsVO> accountParticularsVOs= new ArrayList<>();
				if(paymentDTO.getAccountParticularsDTO()!=null) {
					for(AccountParticularsDTO accountParticularsDTO : paymentDTO.getAccountParticularsDTO()) {
						AccountParticularsVO accountParticularsVO = new AccountParticularsVO();
						accountParticularsVO.setAccountName(accountParticularsDTO.getAccountName());
						accountParticularsVO.setSubledger(accountParticularsDTO.getSubledger());
						accountParticularsVO.setCrAmount(accountParticularsDTO.getCrAmount());
						accountParticularsVO.setDbAmount(accountParticularsDTO.getDbAmount());
						accountParticularsVO.setNarration(accountParticularsDTO.getNarration());
						accountParticularsVO.setAccountName(accountParticularsDTO.getAccountName());

						accountParticularsVO.setPaymentVO(paymentVO);;
						accountParticularsVOs.add(accountParticularsVO);
						
					}
				}
		getPaymentVOFromPaymentDTO(paymentDTO,paymentVO);
		paymentVO.setAccountParticularsVO(accountParticularsVOs);;
		return paymentRepo.save(paymentVO);
	}

	private void getPaymentVOFromPaymentDTO(
			@Valid PaymentDTO paymentDTO, PaymentVO paymentVO) {

		paymentVO.setBranch(paymentDTO.getBranch());
		paymentVO.setPaymentType(paymentDTO.getPaymentType());
		paymentVO.setDocdate(paymentDTO.getDocdate());
		paymentVO.setDocId(paymentDTO.getDocId());
		paymentVO.setBank(paymentDTO.getBank());
		paymentVO.setBalance(paymentDTO.getBalance());
		paymentVO.setCurrency(paymentDTO.getCurrency());
		paymentVO.setExRate(paymentDTO.getExRate());
		paymentVO.setRefDate(paymentDTO.getRefDate());
		paymentVO.setRefNo(paymentDTO.getRefNo());
		paymentVO.setReconciled(paymentDTO.isReconciled());
		paymentVO.setPayeeType(paymentDTO.getPayeeType());
		paymentVO.setPayeeName(paymentDTO.getPayeeName());
		paymentVO.setModeOfPayment(paymentDTO.getModeOfPayment());
		paymentVO.setChqBook(paymentDTO.getChqBook());
		paymentVO.setChqCardNo(paymentDTO.getChqCardNo());
		paymentVO.setChqDdDt(paymentDTO.getChqDdDt());
		paymentVO.setNetAmount(paymentDTO.getNetAmount());
		paymentVO.setRemarks(paymentDTO.getRemarks());
		paymentVO.setOrgId(paymentDTO.getOrgId());
		paymentVO.setCancel(paymentDTO.isCancel());
		paymentVO.setCancelRemarks(paymentDTO.getCancelRemarks());
		paymentVO.setActive(paymentDTO.isActive());


	}


}
