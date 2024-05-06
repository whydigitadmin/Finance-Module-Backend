package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.TaxMaster2DTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.TaxMaster2VO;
import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.SetTaxRateRepo;
import com.base.basesetup.repo.TaxMaster2Repo;
import com.base.basesetup.repo.TaxMasterRepo;

@Service
public class MasterServiceImpl implements MasterService{
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);
	@Autowired
	SetTaxRateRepo setTaxRateRepo;

	
	@Autowired
	TaxMasterRepo taxMasterRepo;
	
	@Autowired
	TaxMaster2Repo taxMaster2Repo;


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
      
	
	
	@Override
	public List<SetTaxRateVO> getAllSetTaxRate() {
		return setTaxRateRepo.findAll();
		}
	
	@Override
	public SetTaxRateVO updateCreateSetTaxRate(@Valid SetTaxRateDTO setTaxRateDTO) throws ApplicationException {
		SetTaxRateVO setTaxRateVO = new SetTaxRateVO();
		if (ObjectUtils.isNotEmpty(setTaxRateDTO.getSetTaxRateId())) {
			setTaxRateVO = setTaxRateRepo.findById(setTaxRateDTO.getSetTaxRateId())
					.orElseThrow(() -> new ApplicationException("Invalid SetTaxRate details"));
		}
		getSetTaxRateVOFromSetTaxRateDTO(setTaxRateDTO, setTaxRateVO);
		return setTaxRateRepo.save(setTaxRateVO);
	}
	
	private void getSetTaxRateVOFromSetTaxRateDTO(@Valid SetTaxRateDTO setTaxRateDTO, SetTaxRateVO setTaxRateVO) {
		setTaxRateVO.setOrgId(setTaxRateDTO.getOrgId());
		setTaxRateVO.setChapter(setTaxRateDTO.getChapter());
		setTaxRateVO.setSubChapter(setTaxRateDTO.getSubChapter());
		setTaxRateVO.setHsnCode(setTaxRateDTO.getHsnCode());
		setTaxRateVO.setBranch(setTaxRateDTO.getBranch());
		setTaxRateVO.setNewRate(setTaxRateDTO.getNewRate());
		setTaxRateVO.setExcepmted("e");
		setTaxRateVO.setActive(setTaxRateDTO.isActive());
		
	}


	//TAXMASTER
	
	@Override

	public TaxMasterVO updateCreateTaxMaster(TaxMasterDTO taxMasterDTO) throws ApplicationException {
		TaxMasterVO taxMasterVO = new TaxMasterVO();
		if (ObjectUtils.isNotEmpty(taxMasterDTO.getTaxMasterId())) {
			taxMasterVO = taxMasterRepo.findById(taxMasterDTO.getTaxMasterId())
					.orElseThrow(() -> new ApplicationException("Invalid Tax Master details"));
		}

		List<TaxMaster2VO> taxMaster2VOs = new ArrayList<>();
		if (taxMasterDTO.getTaxMaster2DTO() != null) {
			for (TaxMaster2DTO taxMaster2DTO : taxMasterDTO.getTaxMaster2DTO()) {
				if (taxMaster2DTO.getTaxMaster2Id() != null & ObjectUtils.isNotEmpty(taxMaster2DTO.getTaxMaster2Id())) {
					TaxMaster2VO taxMaster2VO = taxMaster2Repo.findById(taxMaster2DTO.getTaxMaster2Id()).get();
					taxMaster2VO.setInputAccount(taxMaster2DTO.getInputAccount());
					taxMaster2VO.setOutputAccount(taxMaster2DTO.getOutputAccount());
					taxMaster2VO.setSgstRcmPayable(true);
					taxMaster2VO.setTaxMasterVO(taxMasterVO);
					taxMaster2VOs.add(taxMaster2VO);
					
				} else {
					TaxMaster2VO taxMaster2VO = new TaxMaster2VO();
					taxMaster2VO.setInputAccount(taxMaster2DTO.getInputAccount());
					taxMaster2VO.setOutputAccount(taxMaster2DTO.getOutputAccount());
					taxMaster2VO.setSgstRcmPayable(true);
					taxMaster2VO.setTaxMasterVO(taxMasterVO);
					taxMaster2VOs.add(taxMaster2VO);
				}
			}
		}

		getTaxMasterVOFromTaxMasterDTO(taxMasterDTO, taxMasterVO);

		taxMasterVO.setTaxMaster2VO(taxMaster2VOs);
		return taxMasterRepo.save(taxMasterVO);

	}

	private void getTaxMasterVOFromTaxMasterDTO(TaxMasterDTO taxMasterDTO, TaxMasterVO taxMasterVO) {
		taxMasterVO.setOrgId(taxMasterDTO.getOrgId());
		taxMasterVO.setTaxType(taxMasterDTO.getTaxType());
		taxMasterVO.setTaxPercentage(taxMasterDTO.getTaxPercentage());
		taxMasterVO.setTaxDescription(taxMasterDTO.getTaxDescription());
		taxMasterVO.setActive(taxMasterDTO.isActive());

	}


	
}
