package com.base.basesetup.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.BranchDTO;
import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.EmployeeDTO;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.BranchVO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.EmployeeVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface BasicMasterService {

//	Currency
	List<CurrencyVO> getCurrencyById(Long id);

	List<CurrencyVO> getCurrencyByOrgId(Long orgid);

	CurrencyVO updateCreateCurrency(@Valid CurrencyDTO currencyDTO) throws ApplicationException;

//	Company
	List<CompanyVO> getCompanyById(Long id);

	List<CompanyVO> getCompanyByOrgId(Long orgid);

	CompanyVO updateCreateCompany(@Valid CompanyDTO companyDTO) throws Exception;

//	Employee
	List<EmployeeVO> getEmployeeById(Long id);

	List<EmployeeVO> getEmployeeByOrgId(Long orgid);

	EmployeeVO updateCreateEmployee(@Valid EmployeeDTO employeeDTO) throws ApplicationException, Exception;

//	Country
	List<CountryVO> getCountryById(Long id);

	List<CountryVO> getCountryByOrgId(Long orgid);

	CountryVO updateCreateCountry(@Valid CountryDTO countryDTO) throws ApplicationException;

//	State
	List<StateVO> getStateById(Long id);

	List<StateVO> getStateByOrgId(Long orgid);

	StateVO updateCreateState(@Valid StateDTO stateDTO) throws ApplicationException;

	List<StateVO> getAllStateByCountry(Long orgId, String country);

//	City
	List<CityVO> getCityById(Long id);

	List<CityVO> getCityByOrgId(Long orgid);

	CityVO updateCreateCity(@Valid CityDTO cityDTO) throws ApplicationException;

	List<CityVO> getAllCityByState(Long orgId, String state);
	
//	Financial Year
	List<FinancialYearVO> getFinancialYearById(Long id);

	List<FinancialYearVO> getFinancialYearByOrgId(Long orgid);
	
	FinancialYearVO createFinancial(FinancialYearVO finyr);
	
	Optional<FinancialYearVO> updateFinancial(FinancialYearVO finyr);
	
//	Branch
	List<BranchVO> getBranchById(Long id);

	List<BranchVO> getBranchByOrgId(Long orgid);

	BranchVO updateCreateBranch(@Valid BranchDTO branchDTO) throws ApplicationException;
}
