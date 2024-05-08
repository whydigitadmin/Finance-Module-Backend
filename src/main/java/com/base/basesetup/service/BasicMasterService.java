package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface BasicMasterService {

//	Currency
	List<CurrencyVO> getCurrencyById(Long id);

	List<CurrencyVO> getCurrencyByOrgId(Long orgid);

	CurrencyVO updateCreateCurrency(@Valid CurrencyDTO currencyDTO) throws ApplicationException;

//	Country
	List<CompanyVO> getCompanyById(Long id);

	List<CompanyVO> getCompanyByOrgId(Long orgid);

	CompanyVO updateCreateCompany(@Valid CompanyDTO companyDTO) throws ApplicationException;
}
