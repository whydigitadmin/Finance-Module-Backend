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

import com.base.basesetup.dto.ArapDetailsDTO;
import com.base.basesetup.entity.ArapDetailsVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ArapDetailsRepo;

@Service
public class ArApDetailsServiceImpl implements ArApDetailsService {

	public static final Logger LOGGER = LoggerFactory.getLogger(ArApDetailsService.class);

	@Autowired
	ArapDetailsRepo arapDetailsRepo;

	// ArapDetails

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
	public List<ArapDetailsVO> getArapDetailsByActive() {
		return arapDetailsRepo.findArapDetailsByActive();
	}

	@Override 
	public Map<String, Object> createupdateArapDetails(@Valid ArapDetailsDTO arapDetailsDTO)
			throws ApplicationException {

		ArapDetailsVO arapDetailsVO;

		String message = null;

		if (ObjectUtils.isEmpty(arapDetailsDTO.getId())) {

			arapDetailsVO = new ArapDetailsVO();

			arapDetailsVO.setCreatedBy(arapDetailsDTO.getCreatedBy());
			arapDetailsVO.setUpdatedBy(arapDetailsDTO.getCreatedBy());

			message = "ArapDetails Creation Succesfull";

		} else {

			arapDetailsVO = arapDetailsRepo.findById(arapDetailsDTO.getId()).orElseThrow(
					() -> new ApplicationException("ArAP Details Not Found with id: " + arapDetailsDTO.getId()));
			arapDetailsVO.setUpdatedBy(arapDetailsDTO.getCreatedBy());

			message = "ArapDetails Updation Successfully";

		}

		arapDetailsVO = getArapDetailsVOFromArapDetailsDTO(arapDetailsVO, arapDetailsDTO);
		arapDetailsRepo.save(arapDetailsVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("arapDetailsVO", arapDetailsVO);
		return response;

	}

	private ArapDetailsVO getArapDetailsVOFromArapDetailsDTO(ArapDetailsVO arapDetailsVO,
			@Valid ArapDetailsDTO arapDetailsDTO) {

		arapDetailsVO.setBranch(arapDetailsDTO.getBranch());
		arapDetailsVO.setFinYear(arapDetailsDTO.getFinYear());
		arapDetailsVO.setSourceTransid(arapDetailsDTO.getSourceTransid());
		arapDetailsVO.setRefNo(arapDetailsDTO.getRefNo());
		arapDetailsVO.setAccName(arapDetailsDTO.getAccName());
		arapDetailsVO.setCurrency(arapDetailsDTO.getCurrency());
		arapDetailsVO.setAccCurrency(arapDetailsDTO.getAccCurrency());
		arapDetailsVO.setExRate(arapDetailsDTO.getExRate());
		arapDetailsVO.setAmount(arapDetailsDTO.getAmount());
		arapDetailsVO.setBaseAmt(arapDetailsDTO.getBaseAmt());
		arapDetailsVO.setNativeAmt(arapDetailsDTO.getNativeAmt());
		arapDetailsVO.setChargableAmt(arapDetailsDTO.getChargableAmt());
		arapDetailsVO.setGstFlag(arapDetailsDTO.isGstFlag());
		arapDetailsVO.setDocTypeCode(arapDetailsDTO.getDocTypeCode());
		arapDetailsVO.setSubTypeCode(arapDetailsDTO.getSubTypeCode());
		arapDetailsVO.setSubLedgerDivision(arapDetailsDTO.getSubLedgerDivision());
		arapDetailsVO.setDocDate(arapDetailsDTO.getDocDate());
		arapDetailsVO.setSuppRefNo(arapDetailsDTO.getSuppRefNo());
		arapDetailsVO.setRefDate(arapDetailsDTO.getRefDate());
		arapDetailsVO.setSupRefDate(arapDetailsDTO.getSupRefDate());
		arapDetailsVO.setSubLedgerCode(arapDetailsDTO.getSubLedgerCode());
		arapDetailsVO.setCreditDays(arapDetailsDTO.getCreditDays());
		arapDetailsVO.setDueDate(arapDetailsDTO.getDueDate());
		arapDetailsVO.setTDSAmt(arapDetailsDTO.getTDSAmt());
		arapDetailsVO.setHno(arapDetailsDTO.getHno());
		arapDetailsVO.setOrgId(arapDetailsDTO.getOrgId());
		arapDetailsVO.setCanelRemarks(arapDetailsDTO.getCanelRemarks());
		arapDetailsVO.setCreatedBy(arapDetailsDTO.getCreatedBy());
		arapDetailsVO.setIpNo(arapDetailsDTO.getIpNo());
		arapDetailsVO.setLatitude(arapDetailsDTO.getLatitude());
		arapDetailsVO.setSubLedgerName(arapDetailsDTO.getSubLedgerName());
		return arapDetailsVO;

	}

}
