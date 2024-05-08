package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.CompanyRepo;
import com.base.basesetup.repo.CurrencyRepo;

@Service
public class BasicMasterServiceImpl implements BasicMasterService {

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterServiceImpl.class);

	@Autowired
	CurrencyRepo currencyRepo;

	@Autowired
	CompanyRepo companyRepo;

//	Currency -----------------------------------------------------------------------------------

	@Override
	public List<CurrencyVO> getCurrencyById(Long id) {
		List<CurrencyVO> currencyVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  Currency BY Id : {}", id);
			currencyVO = currencyRepo.findCurrencyById(id);
		} else {
			LOGGER.info("Successfully Received  Currency For All Id.");
			currencyVO = currencyRepo.findAll();
		}
		return currencyVO;
	}

	@Override
	public List<CurrencyVO> getCurrencyByOrgId(Long orgId) {
		List<CurrencyVO> currencyVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  Currency BY OrgId : {}", orgId);
			currencyVO = currencyRepo.findCurrencyByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  Currency For All OrgId.");
			currencyVO = currencyRepo.findAll();
		}
		return currencyVO;
	}

	@Override
	public CurrencyVO updateCreateCurrency(@Valid CurrencyDTO currencyDTO) throws ApplicationException {
		CurrencyVO currencyVO = new CurrencyVO();
		if (ObjectUtils.isNotEmpty(currencyDTO.getId())) {
			currencyVO = currencyRepo.findById(currencyDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Currency Details"));
		}
		getCurrencyVOFromCurrencyDTO(currencyDTO, currencyVO);
		return currencyRepo.save(currencyVO);
	}

	private void getCurrencyVOFromCurrencyDTO(@Valid CurrencyDTO currencyDTO, CurrencyVO currencyVO) {
		currencyVO.setOrgId(currencyDTO.getOrgId());
		currencyVO.setUserid(currencyDTO.getUserid());
		currencyVO.setCountry(currencyDTO.getCountry());
		currencyVO.setCurrency(currencyDTO.getCurrency());
		currencyVO.setSubcurrency(currencyDTO.getSubcurrency());
		currencyVO.setCurrencysymbol(currencyDTO.getCurrencysymbol());
	}

//	Company -----------------------------------------------------------------------------------

	@Override
	public List<CompanyVO> getCompanyById(Long id) {
		List<CompanyVO> companyVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received Company BY Id : {}", id);
			companyVO = companyRepo.findCompanyById(id);
		} else {
			LOGGER.info("Successfully Received Company For All Id.");
			companyVO = companyRepo.findAll();
		}
		return companyVO;
	}

	@Override
	public List<CompanyVO> getCompanyByOrgId(Long orgId) {
		List<CompanyVO> companyVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received Company BY OrgId : {}", orgId);
			companyVO = companyRepo.findCompanyByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received Company For All OrgId.");
			companyVO = companyRepo.findAll();
		}
		return companyVO;
	}

	@Override
	public CompanyVO updateCreateCompany(@Valid CompanyDTO companyDTO) throws ApplicationException {
		CompanyVO companyVO = new CompanyVO();
		if (ObjectUtils.isNotEmpty(companyDTO.getId())) {
			companyVO = companyRepo.findById(companyDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Company Details"));
		}
		getCompanyVOFromCompanyDTO(companyDTO, companyVO);
		return companyRepo.save(companyVO);
	}

	private void getCompanyVOFromCompanyDTO(@Valid CompanyDTO companyDTO, CompanyVO companyVO) {
		companyVO.setOrgId(companyDTO.getOrgId());
		companyVO.setCompanycode(companyDTO.getCompanycode());
		companyVO.setCompanyname(companyDTO.getCompanyname());
		companyVO.setCountry(companyDTO.getCountry());
		companyVO.setCurrency(companyDTO.getCurrency());
		companyVO.setMainCurrency(companyDTO.getMainCurrency());
		companyVO.setAddress(companyDTO.getAddress());
		companyVO.setZip(companyDTO.getZip());
		companyVO.setCity(companyDTO.getCity());
		companyVO.setState(companyDTO.getState());
		companyVO.setPhone(companyDTO.getPhone());
		companyVO.setEmail(companyDTO.getEmail());
		companyVO.setWebSite(companyDTO.getWebSite());
		companyVO.setNote(companyDTO.getNote());
		companyVO.setUserid(companyDTO.getUserid());
		companyVO.setEmployeecode(companyDTO.getEmployeecode());
		companyVO.setEmployeeName(companyDTO.getEmployeeName());
		companyVO.setPassword(companyDTO.getPassword());
	}

}
