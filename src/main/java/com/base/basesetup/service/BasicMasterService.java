package com.base.basesetup.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.dto.BranchDTO;
import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.DocumentTypeDTO;
import com.base.basesetup.dto.DocumentTypesMappingDTO;
import com.base.basesetup.dto.EmployeeDTO;
import com.base.basesetup.dto.FinScreenDTO;
import com.base.basesetup.dto.FinancialYearDTO;
import com.base.basesetup.dto.ResponsibilitiesDTO;
import com.base.basesetup.dto.RoleMasterDTO;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.BranchVO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.DocumentTypeVO;
import com.base.basesetup.entity.DocumentTypesMappingVO;
import com.base.basesetup.entity.EmployeeVO;
import com.base.basesetup.entity.FinScreenVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.ResponsibilitiesVO;
import com.base.basesetup.entity.RoleMasterVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface BasicMasterService {

//	Currency
	List<CurrencyVO> getCurrencyById(Long id);

	List<CurrencyVO> getCurrencyByOrgId(Long orgid);

	CurrencyVO updateCreateCurrency(@Valid CurrencyDTO currencyDTO) throws Exception;

	List<CurrencyVO> getCurrencyByActive();

//	Company
	List<CompanyVO> getCompanyById(Long id);

	List<CompanyVO> getCompanyByOrgId(Long orgid);

	CompanyVO updateCreateCompany(@Valid CompanyDTO companyDTO) throws Exception;

	List<CompanyVO> getCompanyByActive();

	CompanyVO saveImage(MultipartFile file, @RequestParam Long id) throws ApplicationException, java.io.IOException;

	Optional<CompanyVO> getImage(Long id);

//	Employee
	List<EmployeeVO> getEmployeeById(Long id);

	List<EmployeeVO> getEmployeeByOrgId(Long orgid);

	EmployeeVO updateCreateEmployee(@Valid EmployeeDTO employeeDTO) throws ApplicationException, Exception;

	List<EmployeeVO> getEmployeeByActive();

//	Country
	List<CountryVO> getCountryById(Long id);

	List<CountryVO> getCountryByOrgId(Long orgid);

	CountryVO updateCreateCountry(@Valid CountryDTO countryDTO) throws ApplicationException;

	List<CountryVO> getCountryByActive();

//	State
	List<StateVO> getStateById(Long id);

	List<StateVO> getStateByOrgId(Long orgid);

	StateVO updateCreateState(@Valid StateDTO stateDTO) throws ApplicationException;

	List<StateVO> getAllStateByCountry(Long orgId, String country);

	List<StateVO> getStateByActive();

//	City
	List<CityVO> getCityById(Long id);

	List<CityVO> getCityByOrgId(Long orgid);

	CityVO updateCreateCity(@Valid CityDTO cityDTO) throws ApplicationException;

	List<CityVO> getAllCityByState(Long orgId, String state);

	List<CityVO> getCityByActive();

//	Financial Year
	List<FinancialYearVO> getFinancialYearById(Long id);

	List<FinancialYearVO> getFinancialYearByOrgId(Long orgid);

	FinancialYearVO updateCreateFinancialYear(@Valid FinancialYearDTO financialYearDTO) throws ApplicationException;
	
	List<Map<String, Object>> getFinYrAndFinYrIdByOrgId(Long orgId);


//	Branch
	List<BranchVO> getBranchById(Long id);

	List<BranchVO> getBranchByOrgId(Long orgid);

	BranchVO updateCreateBranch(@Valid BranchDTO branchDTO) throws ApplicationException;

	List<BranchVO> getBranchByActive();

//	Role
	List<RoleMasterVO> getRoleById(Long id);

	List<RoleMasterVO> getRoleByOrgId(Long orgid);

	RoleMasterVO updateCreateRole(@Valid RoleMasterDTO roleDTO) throws ApplicationException;

	List<RoleMasterVO> getRoleByActive();

//	Responsibilities 
	List<ResponsibilitiesVO> getResponsibilitiesById(Long id);

	List<ResponsibilitiesVO> getResponsibilitiesByOrgId(Long orgid);

	ResponsibilitiesVO updateCreateResponsibilities(@Valid ResponsibilitiesDTO responsibilitiesDTO)
			throws ApplicationException;

	List<ResponsibilitiesVO> getResponsibilitiesByActive();

//	FinScreen
	List<FinScreenVO> getFinScreenById(Long id);

	List<FinScreenVO> getFinScreenByOrgId(Long orgid);

	FinScreenVO updateCreateFinScreen(@Valid FinScreenDTO finScreenDTO) throws ApplicationException;

	List<Map<String, Object>> getAllScreenCode();

//	DocCode
	List<DocumentTypeVO> getDocCodeById(Long id);

	List<DocumentTypeVO> getDocCodeByOrgId(Long orgid);

	DocumentTypeVO updateCreateDocCode(@Valid DocumentTypeDTO docCodeDTO) throws ApplicationException;

//	DocumentTypesMapping
	List<DocumentTypesMappingVO> getDocumentTypesMappingById(Long id);

	List<DocumentTypesMappingVO> getDocumentTypesMappingByOrgId(Long orgid);

	DocumentTypesMappingVO updateCreateDocumentTypesMapping(@Valid DocumentTypesMappingDTO documentTypesMappingDTO) throws ApplicationException;

	List<Map<String, Object>> getAllDocumentTypesMappingDetailsByDocumentType(String branch,String branchCode,String finYr,Long orgId,String finyrId);


	
}
