package com.base.basesetup.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.DocumentTypeDTO;
import com.base.basesetup.dto.DocumentTypesMappingDTO;
import com.base.basesetup.dto.EmployeeDTO;
import com.base.basesetup.dto.FinScreenDTO;
import com.base.basesetup.dto.FinancialYearDTO;
import com.base.basesetup.dto.RegionDTO;
import com.base.basesetup.dto.ResponsibilitiesDTO;
import com.base.basesetup.dto.RoleMasterDTO;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.DocumentTypeVO;
import com.base.basesetup.entity.DocumentTypesMappingVO;
import com.base.basesetup.entity.EmployeeVO;
import com.base.basesetup.entity.FinScreenVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.RegionVO;
import com.base.basesetup.entity.ResponsibilitiesVO;
import com.base.basesetup.entity.RoleMasterVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface CommonMasterService {

	// Country

	List<CountryVO> getAllCountry(Long orgid); // Method names should be in camelCase

	Optional<CountryVO> getCountryById(Long countryid);

	Map<String, Object> createUpdateCountry(CountryDTO countryDTO) throws ApplicationException; // Return the created
																								// entity

	void deleteCountry(Long countryid);

	// State

	List<StateVO> getAllgetAllStates(Long orgid);

	Optional<StateVO> getStateById(Long stateid);

	List<StateVO> getStatesByCountry(Long orgid, String country);

	Map<String, Object> createUpdateState(StateDTO stateDTO) throws ApplicationException;

	void deleteState(Long stateid);

	// city

	List<CityVO> getAllgetAllCities(Long orgid);

	List<CityVO> getAllCitiesByState(Long orgid, String state);

	Optional<CityVO> getCityById(Long cityid);

	Map<String, Object> createUpdateCity(CityDTO cityDTO) throws ApplicationException;

	void deleteCity(Long cityid);

	// Currency

	List<CurrencyVO> getAllCurrency(Long orgid);

	Optional<CurrencyVO> getCurrencyById(Long currencyid);

	Map<String, Object> createUpdateCurrency(CurrencyDTO currencyDTO) throws ApplicationException;

	void deleteCurrency(Long currencyid);

	// region

	List<RegionVO> getAllRegios();

	List<RegionVO> getAllRegionsByOrgId(Long orgId);

	Optional<RegionVO> getRegionById(Long regionid);

	Map<String, Object> createUpdateRegion(RegionDTO regionDTO) throws ApplicationException;

	void deleteRegion(Long regionid);

	// Company

	List<CompanyVO> getAllCompany();

	List<CompanyVO> getCompanyById(Long companyid);

	CompanyVO createCompany(CompanyDTO companyDTO) throws Exception;

	CompanyVO updateCompany(CompanyDTO companyDTO) throws ApplicationException;

	void deleteCompany(Long companyid);

//	Employee
	List<EmployeeVO> getEmployeeById(Long id);

	List<EmployeeVO> getEmployeeByOrgId(Long orgid);

	EmployeeVO updateCreateEmployee(@Valid EmployeeDTO employeeDTO) throws ApplicationException, Exception;

	List<EmployeeVO> getEmployeeByActive();

//	Financial Year
	List<FinancialYearVO> getFinancialYearById(Long id);

	List<FinancialYearVO> getFinancialYearByOrgId(Long orgid);

	FinancialYearVO updateCreateFinancialYear(@Valid FinancialYearDTO financialYearDTO) throws ApplicationException;

	List<Map<String, Object>> getFinYrAndFinYrIdByOrgId(Long orgId);


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

	DocumentTypesMappingVO updateCreateDocumentTypesMapping(@Valid DocumentTypesMappingDTO documentTypesMappingDTO)
			throws ApplicationException;

	List<Map<String, Object>> getAllDocumentTypesMappingDetailsByDocumentType(String branch, String branchCode,
			String finYr, Long orgId, String finyrId);

}
