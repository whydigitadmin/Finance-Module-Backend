package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.controller.ArapAdjustmentsController;
import com.base.basesetup.dto.ArapAdjustmentsDTO;
import com.base.basesetup.entity.ArapAdjustmentsVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ArapAdjustmentsRepo;

@Service
public class ArapAdjustmentsServiceImpl implements ArapAdjustmentsService{

	public static final Logger LOGGER = LoggerFactory.getLogger(ArapAdjustmentsController.class);
	
	@Autowired
	ArapAdjustmentsRepo arapAdjustmentsRepo;
	
	
	
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
	public List<ArapAdjustmentsVO> getArapAdjustmentsByActive() {
		return arapAdjustmentsRepo.findArapAdjustmentsByActive();
	}

	@Override
	public Map<String, Object> createUpdateArapAdjustments(@Valid ArapAdjustmentsDTO arapAdjustmentsDTO) throws ApplicationException {
		ArapAdjustmentsVO arapAdjustmentsVO;
		String message = null;
		if (ObjectUtils.isEmpty(arapAdjustmentsDTO.getId())) {

			arapAdjustmentsVO = new ArapAdjustmentsVO();
			arapAdjustmentsVO.setCreatedBy(arapAdjustmentsDTO.getCreatedBy());
			arapAdjustmentsVO.setUpdatedBy(arapAdjustmentsDTO.getCreatedBy());

			message = "ArapAdjustments Creation Successfull";
		} else {

			arapAdjustmentsVO = arapAdjustmentsRepo.findById(arapAdjustmentsDTO.getId()).orElseThrow(
					() -> new ApplicationException("Cost Invoice Not Found with id: " + arapAdjustmentsDTO.getId()));

			arapAdjustmentsVO.setUpdatedBy(arapAdjustmentsDTO.getCreatedBy());
			message = "ArapAdjustments Updation Successfull";
		}
		
		arapAdjustmentsVO=getArapAdjustmentsVOFromArapAdjustmentsDTO(arapAdjustmentsVO,arapAdjustmentsDTO);
		arapAdjustmentsRepo.save(arapAdjustmentsVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("arapAdjustmentsVO", arapAdjustmentsVO);
		return response;
		
	}

	private ArapAdjustmentsVO getArapAdjustmentsVOFromArapAdjustmentsDTO(ArapAdjustmentsVO arapAdjustmentsVO,
			@Valid ArapAdjustmentsDTO arapAdjustmentsDTO) {
		
		arapAdjustmentsVO.setBranch(arapAdjustmentsDTO.getBranch());
	    arapAdjustmentsVO.setFinYear(arapAdjustmentsDTO.getFinYear());
	    arapAdjustmentsVO.setSource(arapAdjustmentsDTO.getSource());
	    arapAdjustmentsVO.setRefNo(arapAdjustmentsDTO.getRefNo());
	    arapAdjustmentsVO.setAccountName(arapAdjustmentsDTO.getAccountName());
	    arapAdjustmentsVO.setCurrency(arapAdjustmentsDTO.getCurrency());
	    arapAdjustmentsVO.setAccCurrency(arapAdjustmentsDTO.getAccCurrency());
	    arapAdjustmentsVO.setBaseAmnt(arapAdjustmentsDTO.getBaseAmnt());
	    arapAdjustmentsVO.setNativeAmt(arapAdjustmentsDTO.getNativeAmt());
	    arapAdjustmentsVO.setOffDocId(arapAdjustmentsDTO.getOffDocId());
	    arapAdjustmentsVO.setVoucherType(arapAdjustmentsDTO.getVoucherType());
	    arapAdjustmentsVO.setDocDate(arapAdjustmentsDTO.getDocDate());
	    arapAdjustmentsVO.setRefDate(arapAdjustmentsDTO.getRefDate());
	    arapAdjustmentsVO.setSubLedgerCode(arapAdjustmentsDTO.getSubLedgerCode());
	    arapAdjustmentsVO.setExRate(arapAdjustmentsDTO.getExRate());
	    arapAdjustmentsVO.setCreditDays(arapAdjustmentsDTO.getCreditDays());
	    arapAdjustmentsVO.setDueDate(arapAdjustmentsDTO.getDueDate());
	    arapAdjustmentsVO.setOrgId(arapAdjustmentsDTO.getOrgId());
	    arapAdjustmentsVO.setCreatedBy(arapAdjustmentsDTO.getCreatedBy());
	    arapAdjustmentsVO.setBranchCode(arapAdjustmentsDTO.getBranchCode());
	    arapAdjustmentsVO.setIpNo(arapAdjustmentsDTO.getIpNo());
	    arapAdjustmentsVO.setLatitude(arapAdjustmentsDTO.getLatitude());
	    arapAdjustmentsVO.setTransId(arapAdjustmentsDTO.getTransId());
	    arapAdjustmentsVO.setChargeableAmt(arapAdjustmentsDTO.getChargeableAmt());
	    arapAdjustmentsVO.setTdsAmt(arapAdjustmentsDTO.getTdsAmt());
	    arapAdjustmentsVO.setOfficeDocId(arapAdjustmentsDTO.getOfficeDocId());
	    arapAdjustmentsVO.setSubLedgerName(arapAdjustmentsDTO.getSubLedgerName());
		
        return arapAdjustmentsVO;
	}
	
	
}