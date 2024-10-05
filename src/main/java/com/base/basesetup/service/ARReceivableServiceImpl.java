package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ParticularsAccountReceiptDTO;
import com.base.basesetup.dto.ReceiptReceivableDTO;
import com.base.basesetup.entity.ParticularsAccountReceiptVO;
import com.base.basesetup.entity.ReceiptReceivableVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ParticularsAccountReceiptRepo;
import com.base.basesetup.repo.ReceiptReceivableRepo;

@Service
public class ARReceivableServiceImpl implements ARReceivableService {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ARReceivableServiceImpl.class);
	
	@Autowired
	ReceiptReceivableRepo receiptReceivableRepo;
	
	@Autowired
	ParticularsAccountReceiptRepo particularsAccountReceiptRepo;

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

		List<ParticularsAccountReceiptVO> particularsAccountReceiptVOs = new ArrayList<>();
		if (receiptReceivableDTO.getParticularsAccountReceiptDTO() != null) {
			for (ParticularsAccountReceiptDTO particularsAccountReceiptDTO : receiptReceivableDTO
					.getParticularsAccountReceiptDTO()) {
				ParticularsAccountReceiptVO particularsAccountReceiptVO;
				if (particularsAccountReceiptDTO.getId() != null
						& ObjectUtils.isEmpty(particularsAccountReceiptDTO.getId())) {
					particularsAccountReceiptVO = particularsAccountReceiptRepo
							.findById(particularsAccountReceiptDTO.getId())
							.orElse(new ParticularsAccountReceiptVO());
				} else {
					particularsAccountReceiptVO = new ParticularsAccountReceiptVO();
				}
				particularsAccountReceiptVO.setFromDate(particularsAccountReceiptDTO.getFromDate());
				particularsAccountReceiptVO.setToDate(particularsAccountReceiptDTO.getToDate());
				particularsAccountReceiptVO.setTcs(particularsAccountReceiptDTO.getTcs());
				particularsAccountReceiptVO.setReceiptReceivableVO(receiptReceivableVO);
				particularsAccountReceiptVOs.add(particularsAccountReceiptVO);
			}
		}
		receiptReceivableVO.setParticularsAccountReceiptVO(particularsAccountReceiptVOs);
		getReceiptReceivableVOFromReceiptReceivableDTO(receiptReceivableDTO, receiptReceivableVO);
		return receiptReceivableRepo.save(receiptReceivableVO);
	}

	private void getReceiptReceivableVOFromReceiptReceivableDTO(@Valid ReceiptReceivableDTO receiptReceivableDTO,
			ReceiptReceivableVO receiptReceivableVO) {
		receiptReceivableVO.setBranch(receiptReceivableDTO.getBranch());
		receiptReceivableVO.setReceiptType(receiptReceivableDTO.getReceiptType());
		receiptReceivableVO.setDocDate(receiptReceivableDTO.getDocDate());
		receiptReceivableVO.setDocId(receiptReceivableDTO.getDocId());
		receiptReceivableVO.setModeOfPayment(receiptReceivableDTO.getModeOfPayment());
		receiptReceivableVO.setBankCashAc(receiptReceivableDTO.getBankCashAc());
		receiptReceivableVO.setCurrency(receiptReceivableDTO.getCurrency());
		receiptReceivableVO.setExRates(receiptReceivableDTO.getExRates());
		receiptReceivableVO.setBalance(receiptReceivableDTO.getBalance());
		receiptReceivableVO.setReceivedFrom(receiptReceivableDTO.getReceivedFrom());
		receiptReceivableVO.setCheqDdCardBank(receiptReceivableDTO.getCheqDdCardBank());
		receiptReceivableVO.setCheqDdCardNo(receiptReceivableDTO.getCheqDdCardNo());
		receiptReceivableVO.setCheqDdDate(receiptReceivableDTO.getCheqDdDate());
		receiptReceivableVO.setNetAmount(receiptReceivableDTO.getNetAmount());
		receiptReceivableVO.setRemarks(receiptReceivableDTO.getRemarks());
		receiptReceivableVO.setReconciled(receiptReceivableDTO.isReconciled());
		receiptReceivableVO.setActive(receiptReceivableDTO.isActive());
		receiptReceivableVO.setOrgId(receiptReceivableDTO.getOrgId());
	}

	@Override
	public List<ReceiptReceivableVO> getReceiptReceivableByActive() {
		return receiptReceivableRepo.findReceiptReceivablesByActive();
	}

	
	
}
