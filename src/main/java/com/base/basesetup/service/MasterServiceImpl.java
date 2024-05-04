package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.TcsMasterVO;
import com.base.basesetup.repo.SetTaxRateRepo;
import com.base.basesetup.repo.TcsMasterRepo;
@Service
public class MasterServiceImpl implements MasterService{
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);
	@Autowired
	SetTaxRateRepo setTaxRateRepo;
	@Autowired
	TcsMasterRepo tcsMasterRepo;

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
	
	//TcsMaster

	@Override
	public List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId) {
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TcsMasterInformation BY OrgId : {}", orgId);
			tcsMasterVO = tcsMasterRepo.getAllTcsMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TcsMasterInformation For All OrgId.");
			tcsMasterVO = tcsMasterRepo.findAll();
		}
		return tcsMasterVO;
		}

	@Override
	public List<TcsMasterVO> getAllTcsMasterById(Long id) {
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TcsMasterInformation BY Id : {}", id);
			tcsMasterVO = tcsMasterRepo.getAllTcsMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TcsMasterInformation For All Id.");
			tcsMasterVO = tcsMasterRepo.findAll();
		}
		return tcsMasterVO;
		}
	}
