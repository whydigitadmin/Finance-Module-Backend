package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.repo.SetTaxRateRepo;
@Service
public class MasterServiceImpl implements MasterService{
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);
	@Autowired
	SetTaxRateRepo setTaxRateRepo;

	@Override
	public List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId) {	
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
	if (ObjectUtils.isNotEmpty(orgId)) {
		LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
		setTaxRateVO = setTaxRateRepo.getAllSetTaxRateByOrgId(orgId);
	} else {
		LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
		setTaxRateVO = setTaxRateRepo.findAll();
	}
	return setTaxRateVO;
	}

	@Override
	public List<SetTaxRateVO> getAllSetTaxRateById(Long id) {
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			setTaxRateVO = setTaxRateRepo.getAllSetTaxRateById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			setTaxRateVO = setTaxRateRepo.findAll();
		}
		return setTaxRateVO;
		}
	
	}
