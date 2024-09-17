package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.BranchDTO;
import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.DocumentTypeDTO;
import com.base.basesetup.dto.DocumentTypesMappingDTO;
import com.base.basesetup.dto.DocumentTypesMappingDetailsDTO;
import com.base.basesetup.dto.EmployeeDTO;
import com.base.basesetup.dto.FinScreenDTO;
import com.base.basesetup.dto.FinancialYearDTO;
import com.base.basesetup.dto.ResponsibilitiesDTO;
import com.base.basesetup.dto.RoleMasterDTO;
import com.base.basesetup.dto.ScreenDTO;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.BranchVO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.DocumentTypeVO;
import com.base.basesetup.entity.DocumentTypesMappingDetailsVO;
import com.base.basesetup.entity.DocumentTypesMappingVO;
import com.base.basesetup.entity.EmployeeVO;
import com.base.basesetup.entity.FinScreenVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.ResponsibilitiesVO;
import com.base.basesetup.entity.RoleMasterVO;
import com.base.basesetup.entity.ScreenVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.entity.UserVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.BranchRepo;
import com.base.basesetup.repo.CityRepo;
import com.base.basesetup.repo.CompanyRepo;
import com.base.basesetup.repo.CountryRepo;
import com.base.basesetup.repo.CurrencyRepo;
import com.base.basesetup.repo.DocCodeRepo;
import com.base.basesetup.repo.DocumentTypesMappingDetailsRepo;
import com.base.basesetup.repo.DocumentTypesMappingRepo;
import com.base.basesetup.repo.EmployeeRepo;
import com.base.basesetup.repo.FinScreenRepo;
import com.base.basesetup.repo.FinancialYearRepo;
import com.base.basesetup.repo.ResponsibilitiesRepo;
import com.base.basesetup.repo.RoleRepo;
import com.base.basesetup.repo.ScreenRepo;
import com.base.basesetup.repo.StateRepo;
import com.base.basesetup.repo.UserRepo;
import com.base.basesetup.util.CryptoUtils;

@Service
public class BasicMasterServiceImpl implements BasicMasterService {

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterServiceImpl.class);

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CurrencyRepo currencyRepo;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	CountryRepo countryRepo;

	@Autowired
	StateRepo stateRepo;

	@Autowired
	CityRepo cityRepo;

	@Autowired
	FinancialYearRepo finRepo;

	@Autowired
	BranchRepo branchRepo;

	@Autowired
	RoleRepo roleRepo;

	@Autowired
	ResponsibilitiesRepo responsibilitiesRepo;

	@Autowired
	ScreenRepo screenRepo;

	@Autowired
	FinScreenRepo finScreenRepo;

	@Autowired
	DocCodeRepo docCodeRepo;

	@Autowired
	DocumentTypesMappingRepo documentTypesMappingRepo;

	@Autowired
	DocumentTypesMappingDetailsRepo documentTypesMappingDetailsRepo;
	// Currency-----------------------------------------------------------------------------------

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
	public CurrencyVO updateCreateCurrency(@Valid CurrencyDTO currencyDTO) throws Exception {
		CurrencyVO currencyVO = new CurrencyVO();
		boolean isUpdate = false;

		if (ObjectUtils.isNotEmpty(currencyDTO.getId())) {
			isUpdate = true;
			currencyVO = currencyRepo.findById(currencyDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Currency details"));
			currencyVO.setUpdatedBy(currencyDTO.getCreatedBy());
		} else {
			// Check for duplicates when creating a new record
			if (currencyRepo.existsByCurrencyAndOrgId(currencyDTO.getCurrency(), currencyDTO.getOrgId())) {
				throw new ApplicationException("Currency already exists");
			}
			if (currencyRepo.existsByCountryAndOrgId(currencyDTO.getCountry(), currencyDTO.getOrgId())) {
				throw new ApplicationException("Country already exists");
			}
			currencyVO.setUpdatedBy(currencyDTO.getCreatedBy());
			currencyVO.setCreatedBy(currencyDTO.getCreatedBy());
		}
		// Check for duplicates when updating a record
		if (isUpdate) {
			CurrencyVO currency = currencyRepo.findById(currencyDTO.getId()).orElse(null);

			if (!currency.getCurrency().equals(currencyDTO.getCurrency())) {
				if (currencyRepo.existsByCurrencyAndOrgId(currencyDTO.getCurrency(), currencyDTO.getOrgId())) {
					throw new ApplicationException("Currency already exists");
				}
			}
			if (!currency.getCountry().equalsIgnoreCase(currencyDTO.getCountry())) {
				if (currencyRepo.existsByCountryAndOrgId(currencyDTO.getCountry(), currencyDTO.getOrgId())) {
					throw new ApplicationException("Country already exists");
				}
			}

		} else {

		}

		getCurrencyVOFromCurrencyDTO(currencyDTO, currencyVO);
		return currencyRepo.save(currencyVO);
	}

	private void getCurrencyVOFromCurrencyDTO(@Valid CurrencyDTO currencyDTO, CurrencyVO currencyVO) throws Exception {
		currencyVO.setOrgId(currencyDTO.getOrgId());
		currencyVO.setActive(currencyDTO.isActive());
		currencyVO.setUserId(currencyDTO.getUserid());
		currencyVO.setCountry(currencyDTO.getCountry());
		currencyVO.setBaseCurrency(currencyDTO.getBaseCurrency());
		currencyVO.setCurrency(currencyDTO.getCurrency());
		currencyVO.setSubCurrency(currencyDTO.getSubCurrency());
		currencyVO.setCurrencySymbol(currencyDTO.getCurrencySymbol());
	}

	@Override
	public List<CurrencyVO> getCurrencyByActive() {
		return currencyRepo.findCurrencyByActive();
	}

	// Company-----------------------------------------------------------------------------------

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
	public CompanyVO updateCreateCompany(@Valid CompanyDTO companyDTO) throws Exception {
		CompanyVO companyVO = new CompanyVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(companyDTO.getId())) {
			isUpdate = true;
			companyVO = companyRepo.findById(companyDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Company Details"));
			companyVO.setUpdatedBy(companyDTO.getCreatedBy());

		} else {
			if (companyRepo.existsByCompanyCodeAndOrgId(companyDTO.getCompanyCode(), companyDTO.getOrgId())) {
				throw new ApplicationException("Company Code already exists");
			}
			if (companyRepo.existsByCompanyNameAndOrgId(companyDTO.getCompanyName(), companyDTO.getOrgId())) {
				throw new ApplicationException("Company Name already exists");
			}
			companyVO.setUpdatedBy(companyDTO.getCreatedBy());
			companyVO.setCreatedBy(companyDTO.getCreatedBy());
		}
		// update check
		if (isUpdate) {
			CompanyVO company = companyRepo.findById(companyDTO.getId()).orElse(null);
			if (!company.getCompanyCode().equals(companyDTO.getCompanyCode())) {
				if (companyRepo.existsByCompanyCodeAndOrgId(companyDTO.getCompanyCode(), companyDTO.getOrgId())) {
					throw new ApplicationException("Company Code already exists");
				}
			}
			if (!company.getCompanyName().equalsIgnoreCase(companyDTO.getCompanyName())) {
				if (companyRepo.existsByCompanyNameAndOrgId(companyDTO.getCompanyName(), companyDTO.getOrgId())) {
					throw new ApplicationException("Company Name already exists");
				}
			}
		}

		companyRepo.save(companyVO);
		EmployeeVO emp = new EmployeeVO();
		emp.setEmployeeCode(companyVO.getEmployeeCode());
		emp.setEmployeeName(companyVO.getEmployeeName());
		emp.setOrgId(companyVO.getOrgId());
		emp.setActive(companyVO.isActive());
		employeeRepo.save(emp);
		UserVO userVO = new UserVO();
		userVO.setEmail(companyVO.getEmail());
		userVO.setEmployeeName(companyDTO.getEmployeeName());
		if (userRepo.existsByUserName(companyDTO.getEmployeeCode())) {
			throw new ApplicationException("Employee code already existes");
		}
		userVO.setUserName(companyVO.getEmployeeCode());
		userVO.setOrgId(companyVO.getId());
		userVO.setActive(companyVO.isActive());
		userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(companyVO.getPassword())));
		userRepo.save(userVO);

		getCompanyVOFromCompanyDTO(companyDTO, companyVO);
		return companyVO;

	}

	private void getCompanyVOFromCompanyDTO(@Valid CompanyDTO companyDTO, CompanyVO companyVO) throws Exception {

		companyVO.setCompanyCode(companyDTO.getCompanyCode());
		companyVO.setActive(companyDTO.isActive());
		companyVO.setCompanyName(companyDTO.getCompanyName());
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
		companyVO.setEmployeeCode(companyDTO.getEmployeeCode());
		companyVO.setEmployeeName(companyDTO.getEmployeeName());
		companyVO.setPassword(companyDTO.getPassword());
		companyVO.setOrgId(companyDTO.getOrgId());

	}

	@Override
	public List<CompanyVO> getCompanyByActive() {
		return companyRepo.findCompanyByActive();

	}

	@Override
	public Optional<CompanyVO> getImage(Long id) {
		return companyRepo.findById(id);
	}

	@Override
	public CompanyVO saveImage(MultipartFile file, @RequestParam Long id)
			throws ApplicationException, java.io.IOException {

		CompanyVO image = companyRepo.findById(id)
				.orElseThrow(() -> new ApplicationException("Invalid company id" + id));

		image.setImageName(file.getOriginalFilename());
		image.setData(file.getBytes());
		return companyRepo.save(image);

	}

	// Employee-----------------------------------------------------------------------------------//

	@Override
	public List<EmployeeVO> getEmployeeById(Long id) {
		List<EmployeeVO> employeeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received Employee BY Id : {}", id);
			employeeVO = employeeRepo.findEmployeeById(id);
		} else {
			LOGGER.info("Successfully Received Employee For All Id.");
			employeeVO = employeeRepo.findAll();
		}
		return employeeVO;
	}

	@Override
	public List<EmployeeVO> getEmployeeByOrgId(Long orgId) {
		List<EmployeeVO> employeeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received Employee BY OrgId : {}", orgId);
			employeeVO = employeeRepo.findEmployeeByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received Employee For All OrgId.");
			employeeVO = employeeRepo.findAll();
		}
		return employeeVO;
	}

	@Override
	public EmployeeVO updateCreateEmployee(@Valid EmployeeDTO employeeDTO) throws Exception {
		EmployeeVO employeeVO = new EmployeeVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(employeeDTO.getId())) {
			isUpdate = true;
			employeeVO = employeeRepo.findById(employeeDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Employee Details"));
			employeeVO.setUpdatedBy(employeeDTO.getCreatedBy());

		} else {
			if (employeeRepo.existsByEmployeeCodeAndOrgId(employeeDTO.getEmployeeCode(), employeeDTO.getOrgId())) {
				throw new ApplicationException("Employee Code already exixts.");
			}
			employeeVO.setUpdatedBy(employeeDTO.getCreatedBy());
			employeeVO.setCreatedBy(employeeDTO.getCreatedBy());
		}

		// update check
		if (isUpdate) {
			EmployeeVO employee = employeeRepo.findById(employeeDTO.getId()).orElse(null);
			if (!employee.getEmployeeCode().equalsIgnoreCase(employeeDTO.getEmployeeCode())) {
				if (employeeRepo.existsByEmployeeCodeAndOrgId(employeeDTO.getEmployeeCode(), employeeDTO.getOrgId())) {
					throw new ApplicationException("Employee Code already exists");
				}
			}
		}
		getEmployeeVOFromEmployeeDTO(employeeDTO, employeeVO);
		employeeRepo.save(employeeVO);
		UserVO userVO = new UserVO();
		userVO.setEmployeeName(employeeDTO.getEmployeeName());
		if (userRepo.existsByUserName(employeeDTO.getEmployeeCode())) {
			throw new ApplicationException("Employee code already existes");
		}
		userVO.setUserName(employeeDTO.getEmployeeCode());
		userVO.setOrgId(employeeDTO.getOrgId());
		try {
			userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(employeeDTO.getPassword())));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationContextException(UserConstants.ERRROR_MSG_UNABLE_TO_ENCODE_USER_PASSWORD);
		}
		userVO.setActive(true);
		userVO.setLoginStatus(false);

		userRepo.save(userVO);
		return employeeVO;
		// return employeeRepo.save(employeeVO);
	}

	private void getEmployeeVOFromEmployeeDTO(@Valid EmployeeDTO employeeDTO, EmployeeVO employeeVO)
			throws ApplicationException {

		employeeVO.setOrgId(employeeDTO.getOrgId());
		employeeVO.setActive(employeeDTO.isActive());
		employeeVO.setEmployeeCode(employeeDTO.getEmployeeCode());
		employeeVO.setGender(employeeDTO.getGender());
		employeeVO.setPassword(employeeDTO.getPassword());
		employeeVO.setRole(employeeDTO.getRole());
		employeeVO.setEmployeeName(employeeDTO.getEmployeeName());
		employeeVO.setBranch(employeeDTO.getBranch());
		employeeVO.setBranchCode(employeeDTO.getBranchCode());
		employeeVO.setDepartment(employeeDTO.getDepartment());
		employeeVO.setDesignation(employeeDTO.getDesignation());
		employeeVO.setJoiningDate(employeeDTO.getJoiningDate());
		employeeVO.setDateOfBirth(employeeDTO.getDateOfBirth());
		employeeVO.setActive(employeeDTO.isActive());

	}

	@Override
	public List<EmployeeVO> getEmployeeByActive() {
		return employeeRepo.findEmployeeByActive();
	}
	// Country-----------------------------------------------------------------------------------

	@Override
	public List<CountryVO> getCountryById(Long id) {
		List<CountryVO> countryVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received Country BY Id : {}", id);
			countryVO = countryRepo.findCountryById(id);
		} else {
			LOGGER.info("Successfully Received Country For All Id.");
			countryVO = countryRepo.findAll();
		}
		return countryVO;
	}

	@Override
	public List<CountryVO> getCountryByOrgId(Long orgId) {
		List<CountryVO> countryVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received Country BY OrgId : {}", orgId);
			countryVO = countryRepo.findCountryByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received Country For All OrgId.");
			countryVO = countryRepo.findAll();
		}
		return countryVO;
	}

	@Override
	public CountryVO updateCreateCountry(@Valid CountryDTO countryDTO) throws ApplicationException {
		CountryVO countryVO = new CountryVO();
		if (ObjectUtils.isNotEmpty(countryDTO.getId())) {
			countryVO = countryRepo.findById(countryDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Country Details"));
			countryVO.setUpdatedBy(countryDTO.getCreatedBy());

		} else {
			if (countryRepo.existsByCountryNameAndOrgId(countryDTO.getCountryName(), countryDTO.getOrgId())) {
				throw new ApplicationException("The given Country Name already exists.");
			}
			if (countryRepo.existsByCountryCodeAndOrgId(countryDTO.getCountryCode(), countryDTO.getOrgId())) {
				throw new ApplicationException("The given Country Code already exists.");
			}
			countryVO.setUpdatedBy(countryDTO.getCreatedBy());
			countryVO.setCreatedBy(countryDTO.getCreatedBy());
		}
		// update check
		if (ObjectUtils.isNotEmpty(countryDTO.getId())) {
			CountryVO country = countryRepo.findById(countryDTO.getId()).orElse(null);
			if (!country.getCountryName().equalsIgnoreCase(countryDTO.getCountryName())) {
				if (countryRepo.existsByCountryNameAndOrgId(countryDTO.getCountryName(), countryDTO.getOrgId())) {
					throw new ApplicationException("The given Country Name already exists.");
				}
			}
			if (!country.getCountryCode().equals(countryDTO.getCountryCode())) {
				if (countryRepo.existsByCountryCodeAndOrgId(countryDTO.getCountryCode(), countryDTO.getOrgId())) {
					throw new ApplicationException("The given Country Code already exists");
				}
			}
		}

		getCountryVOFromCountryDTO(countryDTO, countryVO);
		return countryRepo.save(countryVO);
	}

	private void getCountryVOFromCountryDTO(@Valid CountryDTO countryDTO, CountryVO countryVO)
			throws ApplicationException {

		countryVO.setOrgId(countryDTO.getOrgId());
		countryVO.setActive(countryDTO.isActive());
		countryVO.setCountryCode(countryDTO.getCountryCode());
		countryVO.setCountryName(countryDTO.getCountryName());
		countryVO.setUserId(countryDTO.getUserId());
	}

	@Override
	public List<CountryVO> getCountryByActive() {
		return countryRepo.findCountryByActive();
	}

	// State-----------------------------------------------------------------------------------
	@Override
	public List<StateVO> getStateById(Long id) {
		List<StateVO> stateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received State BY Id : {}", id);
			stateVO = stateRepo.findStateById(id);
		} else {
			LOGGER.info("Successfully Received State For All Id.");
			stateVO = stateRepo.findAll();
		}
		return stateVO;
	}

	@Override
	public List<StateVO> getStateByOrgId(Long orgId) {
		List<StateVO> stateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received State BY OrgId : {}", orgId);
			stateVO = stateRepo.findStateByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received State For All OrgId.");
			stateVO = stateRepo.findAll();
		}
		return stateVO;
	}

	@Override
	public StateVO updateCreateState(@Valid StateDTO stateDTO) throws ApplicationException {
		StateVO stateVO = new StateVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(stateDTO.getId())) {
			isUpdate = true;
			stateVO = stateRepo.findById(stateDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid State Details"));
			stateVO.setUpdatedBy(stateDTO.getCreatedBy());

		} else {
			if (stateRepo.existsByStateCodeAndOrgId(stateDTO.getStateCode(), stateDTO.getOrgId())) {
				throw new ApplicationException("The given State Code already exists.");
			}
			if (stateRepo.existsByStateNameAndOrgId(stateDTO.getStateName(), stateDTO.getOrgId())) {
				throw new ApplicationException("The given state name already exists.");
			}
			stateVO.setUpdatedBy(stateDTO.getCreatedBy());
			stateVO.setCreatedBy(stateDTO.getCreatedBy());
		}
		// update check
		if (isUpdate) {
			StateVO state = stateRepo.findById(stateDTO.getId()).orElse(null);
			if (!state.getStateCode().equalsIgnoreCase(stateDTO.getStateCode())) {
				if (stateRepo.existsByStateCodeAndOrgId(stateDTO.getStateCode(), stateDTO.getOrgId())) {
					throw new ApplicationException("The given State Code already exists.");
				}
			}
			if (!state.getStateName().equals(stateDTO.getStateName())) {
				if (stateRepo.existsByStateNameAndOrgId(stateDTO.getStateName(), stateDTO.getOrgId())) {
					throw new ApplicationException("The given State Name already exists");
				}
			}
		}

		getStateVOFromStateDTO(stateDTO, stateVO);
		return stateRepo.save(stateVO);
	}

	private void getStateVOFromStateDTO(@Valid StateDTO stateDTO, StateVO stateVO) throws ApplicationException {

		stateVO.setOrgId(stateDTO.getOrgId());
		stateVO.setActive(stateDTO.isActive());
		stateVO.setStateCode(stateDTO.getStateCode());
		stateVO.setStateName(stateDTO.getStateName());
		stateVO.setUserId(stateDTO.getUserId());
		stateVO.setCountry(stateDTO.getCountry());
		stateVO.setRegion(stateDTO.getRegion());
		stateVO.setStateNumber(stateDTO.getStateNumber());

	}

	@Override
	public List<StateVO> getAllStateByCountry(Long orgId, String country) {
		return stateRepo.findAllStateByCountry(orgId, country);
	}

	@Override
	public List<StateVO> getStateByActive() {
		return stateRepo.findStateByActive();
	}

	// City-----------------------------------------------------------------------------------
	@Override
	public List<CityVO> getCityById(Long id) {
		List<CityVO> cityVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received City BY Id : {}", id);
			cityVO = cityRepo.findCityById(id);
		} else {
			LOGGER.info("Successfully Received City For All Id.");
			cityVO = cityRepo.findAll();
		}
		return cityVO;
	}

	@Override
	public List<CityVO> getCityByOrgId(Long orgId) {
		List<CityVO> cityVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received City BY OrgId : {}", orgId);
			cityVO = cityRepo.findCityByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received City For All OrgId.");
			cityVO = cityRepo.findAll();
		}
		return cityVO;
	}

	@Override
	public CityVO updateCreateCity(@Valid CityDTO cityDTO) throws ApplicationException {
		CityVO cityVO = new CityVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(cityDTO.getId())) {
			isUpdate = true;
			cityVO = cityRepo.findById(cityDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid City Details"));
			cityVO.setUpdatedBy(cityDTO.getCreatedBy());

		} else {
			if (cityRepo.existsByCityNameAndOrgId(cityDTO.getCityName(), cityDTO.getOrgId())) {
				throw new ApplicationException("The given city name already exists.");
			}
			if (cityRepo.existsByCityCodeAndOrgId(cityDTO.getCityCode(), cityDTO.getOrgId())) {
				throw new ApplicationException("The given city code already exists.");
			}
			cityVO.setUpdatedBy(cityDTO.getCreatedBy());
			cityVO.setCreatedBy(cityDTO.getCreatedBy());
		}

		// update check
		if (isUpdate) {
			CityVO city = cityRepo.findById(cityDTO.getId()).orElse(null);
			if (!city.getCityName().equalsIgnoreCase(cityDTO.getCityName())) {
				if (cityRepo.existsByCityNameAndOrgId(cityDTO.getCityName(), cityDTO.getOrgId())) {
					throw new ApplicationException("The given city name already exists.");
				}
			}
			if (!city.getCityCode().equals(cityDTO.getCityCode())) {
				if (cityRepo.existsByCityCodeAndOrgId(cityDTO.getCityCode(), cityDTO.getOrgId())) {
					throw new ApplicationException("The given city code already exists");
				}
			}
		}
		getCityVOFromCityDTO(cityDTO, cityVO);
		return cityRepo.save(cityVO);
	}

	private void getCityVOFromCityDTO(@Valid CityDTO cityDTO, CityVO cityVO) throws ApplicationException {

		cityVO.setOrgId(cityDTO.getOrgId());
		cityVO.setActive(cityDTO.isActive());
		cityVO.setCityCode(cityDTO.getCityCode());
		cityVO.setCityName(cityDTO.getCityName());
		cityVO.setCountry(cityDTO.getCountry());
		cityVO.setUserId(cityDTO.getUserId());
		cityVO.setState(cityDTO.getState());

	}

	@Override
	public List<CityVO> getAllCityByState(Long orgId, String state) {
		return cityRepo.findAllCityByState(orgId, state);
	}

	@Override
	public List<CityVO> getCityByActive() {
		return cityRepo.findCityByActive();
	}

	// FinancialYear-----------------------------------------------------------------------------------
	@Override
	public List<FinancialYearVO> getFinancialYearById(Long id) {
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received FinancialYear BY Id : {}", id);
			financialYearVO = finRepo.findFinancialYearById(id);
		} else {
			LOGGER.info("Successfully Received FinancialYear For All Id.");
			financialYearVO = finRepo.findAll();
		}
		return financialYearVO;
	}

	@Override
	public List<FinancialYearVO> getFinancialYearByOrgId(Long orgId) {
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received FinancialYear BY OrgId : {}", orgId);
			financialYearVO = finRepo.findFinancialYearByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received FinancialYear For All OrgId.");
			financialYearVO = finRepo.findAll();
		}
		return financialYearVO;
	}

	@Override
	public FinancialYearVO updateCreateFinancialYear(@Valid FinancialYearDTO financialYearDTO)
			throws ApplicationException {
		FinancialYearVO financialYearVO = new FinancialYearVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(financialYearDTO.getId())) {
			isUpdate = true;
			financialYearVO = finRepo.findById(financialYearDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid FinancialYear Details"));
			financialYearVO.setUpdatedBy(financialYearDTO.getCreatedBy());
		} else {
			financialYearVO.setUpdatedBy(financialYearDTO.getCreatedBy());
			financialYearVO.setCreatedBy(financialYearDTO.getCreatedBy());
		}
		getFinancialYearVOFromFinancialYearDTO(financialYearDTO, financialYearVO);
		return finRepo.save(financialYearVO);
	}

	private void getFinancialYearVOFromFinancialYearDTO(@Valid FinancialYearDTO financialYearDTO,
			FinancialYearVO financialYearVO) {
		financialYearVO.setOrgId(financialYearDTO.getOrgId());
		financialYearVO.setActive(financialYearDTO.isActive());
		financialYearVO.setSno(financialYearDTO.getSno());
		financialYearVO.setFinYr(financialYearDTO.getFinYr());
		financialYearVO.setFinYrId(financialYearDTO.getFinYrId());
		financialYearVO.setFinYrIdentifier(financialYearDTO.getFinYrIdentifier());
		financialYearVO.setStartDate(financialYearDTO.getStartDate());
		financialYearVO.setEndDate(financialYearDTO.getEndDate());
		financialYearVO.setCurrentFinYr(financialYearDTO.isCurrentFinYr());
		financialYearVO.setClosed(financialYearDTO.isClosed());
		financialYearVO.setOpen(financialYearDTO.isOpen());
		financialYearVO.setAction(financialYearDTO.isAction());
		financialYearVO.setCompany(financialYearDTO.getCompany());
		financialYearVO.setUserId(financialYearDTO.getUserId());
	}

	@Override
	public List<Map<String, Object>> getFinYrAndFinYrIdByOrgId(Long orgId) {
		Set<Object[]> getFin = finRepo.findFinYrAndFinYrByOrgId(orgId);
		return getFinyearDetails(getFin);
	}

	private List<Map<String, Object>> getFinyearDetails(Set<Object[]> getFin) {
		List<Map<String, Object>> finyrList = new ArrayList<>();

		for (Object[] finyeDate : getFin) {
			Map<String, Object> branchMap = new HashMap<>();
			branchMap.put("finYr", finyeDate[0] != null ? finyeDate[0].toString() : "");
			branchMap.put("finYrId", finyeDate[1] != null ? finyeDate[1].toString() : "");
			finyrList.add(branchMap);
		}
		return finyrList;
	}

	// Branch-----------------------------------------------------------------------------------
	@Override
	public List<BranchVO> getBranchById(Long id) {

		List<BranchVO> branchVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received Branch BY Id : {}", id);
			branchVO = branchRepo.findBranchById(id);
		} else {
			LOGGER.info("Successfully Received Branch For All Id.");
			branchVO = branchRepo.findAll();
		}
		return branchVO;
	}

	@Override
	public List<BranchVO> getBranchByOrgId(Long orgId) {
		return branchRepo.findBranchByOrgId(orgId);
	}

	@Override
	public BranchVO updateCreateBranch(@Valid BranchDTO branchDTO) throws ApplicationException {
		BranchVO branchVO = new BranchVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(branchDTO.getId())) {
			isUpdate = true;
			branchVO = branchRepo.findById(branchDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Branch Details"));
			branchVO.setUpdatedBy(branchDTO.getCreatedBy());

		} else {
			if (branchRepo.existsByBranchCodeAndOrgId(branchDTO.getBranchCode(), branchDTO.getOrgId())) {
				throw new ApplicationException("The given branch code already exists.");
			}
			if (branchRepo.existsByBranchAndOrgId(branchDTO.getBranch(), branchDTO.getOrgId())) {
				throw new ApplicationException("The given branch name already exists");
			}
			branchVO.setUpdatedBy(branchDTO.getCreatedBy());
			branchVO.setCreatedBy(branchDTO.getCreatedBy());
		}
		// update check
		if (isUpdate) {
			BranchVO branch = branchRepo.findById(branchDTO.getId()).orElse(null);
			if (!branch.getBranchCode().equalsIgnoreCase(branchDTO.getBranchCode())) {
				if (branchRepo.existsByBranchCodeAndOrgId(branchDTO.getBranchCode(), branchDTO.getOrgId())) {
					throw new ApplicationException("The given branch code already exists.");
				}
			}
			if (!branch.getBranch().equals(branchDTO.getBranch())) {
				if (branchRepo.existsByBranchAndOrgId(branchDTO.getBranch(), branchDTO.getOrgId())) {
					throw new ApplicationException("The given branch name already exists");
				}
			}
		}

		getBranchVOFromBranchDTO(branchDTO, branchVO);
		return branchRepo.save(branchVO);
	}

	private void getBranchVOFromBranchDTO(@Valid BranchDTO branchDTO, BranchVO branchVO) throws ApplicationException {

		branchVO.setOrgId(branchDTO.getOrgId());
		branchVO.setActive(branchDTO.isActive());
		branchVO.setBranch(branchDTO.getBranch());
		branchVO.setBranchCode(branchDTO.getBranchCode());
		branchVO.setAddressLine1(branchDTO.getAddressLine1());
		branchVO.setAddressLine2(branchDTO.getAddressLine2());
		branchVO.setPan(branchDTO.getPan());
		branchVO.setGstIn(branchDTO.getGstIn());
		branchVO.setPhone(branchDTO.getPhone());
		branchVO.setState(branchDTO.getState());
		branchVO.setCity(branchDTO.getCity());
		branchVO.setPinCode(branchDTO.getPinCode());
		branchVO.setCountry(branchDTO.getCountry());
		branchVO.setStateNo(branchDTO.getStateNo());
		branchVO.setStateCode(branchDTO.getStateCode());
		branchVO.setRegion(branchDTO.getRegion());
		branchVO.setLccurrency(branchDTO.getLccurrency());
		branchVO.setUserId(branchDTO.getUserId());
	}

	@Override
	public List<BranchVO> getBranchByActive() {
		return branchRepo.findBranchByActive();
	}
	// Role------------------------------------------------------------------------------------------------

	@Override
	public List<RoleMasterVO> getRoleById(Long id) {
		List<RoleMasterVO> roleVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received Role BY Id : {}", id);
			roleVO = roleRepo.findRoleById(id);
		} else {
			LOGGER.info("Successfully Received Role For All Id.");
			roleVO = roleRepo.findAll();
		}
		return roleVO;
	}

	@Override
	public List<RoleMasterVO> getRoleByOrgId(Long orgId) {
		List<RoleMasterVO> roleVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received Role BY OrgId : {}", orgId);
			roleVO = roleRepo.findRoleByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received Role For All OrgId.");
			roleVO = roleRepo.findAll();
		}
		return roleVO;
	}

	@Override
	public RoleMasterVO updateCreateRole(@Valid RoleMasterDTO roleMasterDTO) throws ApplicationException {
		RoleMasterVO roleMasterVO = new RoleMasterVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(roleMasterDTO.getId())) {
			isUpdate = true;
			roleMasterVO = roleRepo.findById(roleMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Role Details"));
			roleMasterVO.setUpdatedBy(roleMasterDTO.getCreatedBy());

		} else {
			if (roleRepo.existsByRoleAndOrgId(roleMasterDTO.getRole(), roleMasterDTO.getOrgId())) {
				throw new ApplicationException("The given role already exists.");
			}
			roleMasterVO.setUpdatedBy(roleMasterDTO.getCreatedBy());
			roleMasterVO.setCreatedBy(roleMasterDTO.getCreatedBy());
		}

		// update check
		if (isUpdate) {
			RoleMasterVO role = roleRepo.findById(roleMasterDTO.getId()).orElse(null);
			if (!role.getRole().equalsIgnoreCase(roleMasterDTO.getRole())) {
				if (roleRepo.existsByRoleAndOrgId(roleMasterDTO.getRole(), roleMasterDTO.getOrgId())) {
					throw new ApplicationException("The given role already exists.");
				}
			}
		}
		getRoleMasterVOFromRoleMasterDTO(roleMasterDTO, roleMasterVO);
		return roleRepo.save(roleMasterVO);
	}

	private void getRoleMasterVOFromRoleMasterDTO(@Valid RoleMasterDTO roleMasterDTO, RoleMasterVO roleMasterVO)
			throws ApplicationException {
		roleMasterVO.setOrgId(roleMasterDTO.getOrgId());
		roleMasterVO.setActive(roleMasterDTO.isActive());
		roleMasterVO.setRole(roleMasterDTO.getRole());
	}

	@Override
	public List<RoleMasterVO> getRoleByActive() {
		return roleRepo.findRoleByActive();
	}

	// Responsibilities-----------------------------------------------------------------------------------------
	@Override
	public List<ResponsibilitiesVO> getResponsibilitiesById(Long id) {
		List<ResponsibilitiesVO> responsibilitiesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received Responsibilities BY Id : {}", id);
			responsibilitiesVO = responsibilitiesRepo.findResponsibilitiesById(id);
		} else {
			LOGGER.info("Successfully Received Responsibilities For All Id.");
			responsibilitiesVO = responsibilitiesRepo.findAll();
		}
		return responsibilitiesVO;
	}

	@Override
	public List<ResponsibilitiesVO> getResponsibilitiesByOrgId(Long orgId) {
		List<ResponsibilitiesVO> responsibilitiesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received Responsibilities BY OrgId : {}", orgId);
			responsibilitiesVO = responsibilitiesRepo.findResponsibilitiesByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received Responsibilities For All OrgId.");
			responsibilitiesVO = responsibilitiesRepo.findAll();
		}
		return responsibilitiesVO;
	}

	@Transactional
	@Override
	public ResponsibilitiesVO updateCreateResponsibilities(ResponsibilitiesDTO responsibilitiesDTO)
			throws ApplicationException {
		Logger log = LoggerFactory.getLogger(this.getClass());
		log.info("STARTING METHOD :updateCreateResponsibilities()");
		boolean isUpdate = false;
		ResponsibilitiesVO responsibilitiesVO = new ResponsibilitiesVO();
		try {
			if (ObjectUtils.isNotEmpty(responsibilitiesDTO.getId())) {
				isUpdate = true;
				responsibilitiesVO = responsibilitiesRepo.findById(responsibilitiesDTO.getId())
						.orElseThrow(() -> new ApplicationException("Invalid Responsibilities details"));
				responsibilitiesVO.setUpdatedBy(responsibilitiesDTO.getCreatedBy());
			} else {
				responsibilitiesVO.setUpdatedBy(responsibilitiesDTO.getCreatedBy());
				responsibilitiesVO.setCreatedBy(responsibilitiesDTO.getCreatedBy());
			}
			getResponsibilitiesVOFromResponsibilitiesDTO(responsibilitiesDTO, responsibilitiesVO);

			// Clear existing ScreenVOs and add new ones
			responsibilitiesVO.getScreenVO().clear();
			if (responsibilitiesDTO.getScreenDTO() != null) {
				for (ScreenDTO screenDTO : responsibilitiesDTO.getScreenDTO()) {
					ScreenVO screenVO;
					if (screenDTO.getId() != null && ObjectUtils.isNotEmpty(screenDTO.getId())) {
						screenVO = screenRepo.findById(screenDTO.getId())
								.orElseThrow(() -> new ApplicationException("Invalid Screen details"));
					} else {
						screenVO = new ScreenVO();
					}
					screenVO.setResponsibilities(screenDTO.getResponsibilities());
					screenVO.setScreenName(screenDTO.getScreenName());
					screenVO.setResponsibilitiesVO(responsibilitiesVO);
					responsibilitiesVO.getScreenVO().add(screenVO);
				}
			}

			log.info("ENDING METHOD :updateCreateResponsibilities()");
			return responsibilitiesRepo.save(responsibilitiesVO);
		} catch (Exception e) {
			log.error("Error in method updateCreateResponsibilities: ", e);
			throw new ApplicationException("An error occurred while updating responsibilities", e);
		}
	}

	private void getResponsibilitiesVOFromResponsibilitiesDTO(ResponsibilitiesDTO responsibilitiesDTO,
			ResponsibilitiesVO responsibilitiesVO) {
		responsibilitiesVO.setOrgId(responsibilitiesDTO.getOrgId());
		responsibilitiesVO.setRole(responsibilitiesDTO.getRole());
		responsibilitiesVO.setActive(responsibilitiesDTO.isActive());
	}

	@Override
	public List<ResponsibilitiesVO> getResponsibilitiesByActive() {
		return responsibilitiesRepo.findResponsibilitiesByActive();

	}

	// FinScreen-----------------------------------------------------------------------------------
	@Override
	public List<FinScreenVO> getFinScreenById(Long id) {
		List<FinScreenVO> finScreenVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received FinScreen BY Id : {}", id);
			finScreenVO = finScreenRepo.findFinScreenById(id);
		} else {
			LOGGER.info("Successfully Received FinScreen For All Id.");
			finScreenVO = finScreenRepo.findAll();
		}
		return finScreenVO;
	}

	@Override
	public List<FinScreenVO> getFinScreenByOrgId(Long orgId) {
		List<FinScreenVO> finScreenVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received FinScreen BY OrgId : {}", orgId);
			finScreenVO = finScreenRepo.findFinScreenByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received FinScreen For All OrgId.");
			finScreenVO = finScreenRepo.findAll();
		}
		return finScreenVO;
	}

	@Override
	public FinScreenVO updateCreateFinScreen(@Valid FinScreenDTO finScreenDTO) throws ApplicationException {
		FinScreenVO finScreenVO = new FinScreenVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(finScreenDTO.getId())) {
			isUpdate = true;
			finScreenVO = finScreenRepo.findById(finScreenDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid FinScreen Details"));
			finScreenVO.setUpdatedBy(finScreenDTO.getCreatedBy());

		} else {
			if (finScreenRepo.existsByScreenName(finScreenDTO.getScreenName())) {
				throw new ApplicationException("The given Screen name already exists.");
			}
			if (finScreenRepo.existsByScreenCode(finScreenDTO.getScreenCode())) {
				throw new ApplicationException("The given Screen code already exists.");
			}
			finScreenVO.setUpdatedBy(finScreenDTO.getCreatedBy());
			finScreenVO.setCreatedBy(finScreenDTO.getCreatedBy());
		}

		// update check
		if (isUpdate) {
			FinScreenVO finScreen = finScreenRepo.findById(finScreenDTO.getId()).orElse(null);
			if (!finScreen.getScreenName().equalsIgnoreCase(finScreenDTO.getScreenName())) {
				if (finScreenRepo.existsByScreenName(finScreenDTO.getScreenName())) {
					throw new ApplicationException("The given Screen name already exists.");
				}
			}
			if (!finScreen.getScreenCode().equals(finScreenDTO.getScreenCode())) {
				if (finScreenRepo.existsByScreenCode(finScreenDTO.getScreenCode())) {
					throw new ApplicationException("The given Screen code already exists");
				}
			}
		}
		getFinScreenVOFromFinScreenDTO(finScreenDTO, finScreenVO);
		return finScreenRepo.save(finScreenVO);
	}

	private void getFinScreenVOFromFinScreenDTO(@Valid FinScreenDTO finScreenDTO, FinScreenVO finScreenVO)
			throws ApplicationException {

		finScreenVO.setActive(finScreenDTO.isActive());
		finScreenVO.setScreenCode(finScreenDTO.getScreenCode());
		finScreenVO.setScreenName(finScreenDTO.getScreenName());
	}


	@Override
	public List<Map<String, Object>> getAllScreenCode() {
		Set<Object[]> getFinScreen = finScreenRepo.findAllScreenCode();
		return getScreen(getFinScreen);
	}

	private List<Map<String, Object>> getScreen(Set<Object[]> getFinScreen) {
		List<Map<String, Object>> finScreenList = new ArrayList<>();

		for (Object[] finScreen : getFinScreen) {
			Map<String, Object> branchMap = new HashMap<>();
			branchMap.put("screenName", finScreen[0] != null ? finScreen[0].toString() : "");
			branchMap.put("screenCode", finScreen[1] != null ? finScreen[1].toString() : "");
			finScreenList.add(branchMap);
		}
		return finScreenList;
	}

	// DocumentType-----------------------------------------------------------------------------------
	@Override
	public List<DocumentTypeVO> getDocCodeById(Long id) {
		List<DocumentTypeVO> docCodeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received DocCode BY Id : {}", id);
			docCodeVO = docCodeRepo.findDocCodeById(id);
		} else {
			LOGGER.info("Successfully Received DocCode For All Id.");
			docCodeVO = docCodeRepo.findAll();
		}
		return docCodeVO;
	}

	@Override
	public List<DocumentTypeVO> getDocCodeByOrgId(Long orgId) {
		List<DocumentTypeVO> docCodeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received DocCode BY OrgId : {}", orgId);
			docCodeVO = docCodeRepo.findDocCodeByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received DocCode For All OrgId.");
			docCodeVO = docCodeRepo.findAll();
		}
		return docCodeVO;
	}

	@Override
	public DocumentTypeVO updateCreateDocCode(@Valid DocumentTypeDTO docCodeDTO) throws ApplicationException {
		DocumentTypeVO docCodeVO = new DocumentTypeVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(docCodeDTO.getId())) {
			isUpdate = true;
			docCodeVO = docCodeRepo.findById(docCodeDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid DocCode Details"));
			docCodeVO.setUpdatedBy(docCodeDTO.getCreatedBy());

		} else {
			docCodeVO.setUpdatedBy(docCodeDTO.getCreatedBy());
			docCodeVO.setCreatedBy(docCodeDTO.getCreatedBy());
		}
		getDocCodeVOFromDocCodeDTO(docCodeDTO, docCodeVO);
		return docCodeRepo.save(docCodeVO);
	}

	private void getDocCodeVOFromDocCodeDTO(@Valid DocumentTypeDTO docCodeDTO, DocumentTypeVO docCodeVO)
			throws ApplicationException {

		docCodeVO.setActive(docCodeDTO.isActive());
		docCodeVO.setScreenCode(docCodeDTO.getScreenCode());
		docCodeVO.setScreenName(docCodeDTO.getScreenName());
		docCodeVO.setDocCode(docCodeDTO.getDocCode());
		docCodeVO.setOrgId(docCodeDTO.getOrgId());
	}

//	DocumentTypesMapping
	@Override
	public List<DocumentTypesMappingVO> getDocumentTypesMappingById(Long id) {
		List<DocumentTypesMappingVO> documentTypesMappingVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  DocumentTypesMapping BY Id : {}", id);
			documentTypesMappingVO = documentTypesMappingRepo.findDocumentTypesMappingById(id);
		} else {
			LOGGER.info("Successfully Received  DocumentTypesMapping For All Id.");
			documentTypesMappingVO = documentTypesMappingRepo.findAll();
		}
		return documentTypesMappingVO;
	}

	@Override
	public List<DocumentTypesMappingVO> getDocumentTypesMappingByOrgId(Long orgid) {
		List<DocumentTypesMappingVO> documentTypesMappingVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgid)) {
			LOGGER.info("Successfully Received  DocumentTypesMapping BY OrgId : {}", orgid);
			documentTypesMappingVO = documentTypesMappingRepo.findDocumentTypesMappingByOrgId(orgid);
		} else {
			LOGGER.info("Successfully Received  DocumentTypesMapping For All OrgId.");
			documentTypesMappingVO = documentTypesMappingRepo.findAll();
		}
		return documentTypesMappingVO;
	}

	@Override
	public DocumentTypesMappingVO updateCreateDocumentTypesMapping(
			@Valid DocumentTypesMappingDTO documentTypesMappingDTO) throws ApplicationException {
		DocumentTypesMappingVO documentTypesMappingVO = new DocumentTypesMappingVO();
		boolean isUpdate = false;
		if (ObjectUtils.isNotEmpty(documentTypesMappingDTO.getId())) {
			isUpdate = true;
			documentTypesMappingVO = documentTypesMappingRepo.findById(documentTypesMappingDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid DocumentTypesMapping Details"));
			documentTypesMappingVO.setUpdatedBy(documentTypesMappingDTO.getCreatedBy());
		} else {
			documentTypesMappingVO.setUpdatedBy(documentTypesMappingDTO.getCreatedBy());
			documentTypesMappingVO.setCreatedBy(documentTypesMappingDTO.getCreatedBy());
		}

		documentTypesMappingVO = documentTypesMappingRepo.save(documentTypesMappingVO);

		List<DocumentTypesMappingDetailsVO> documentTypesMappingDetailsVOList = documentTypesMappingDetailsRepo
				.findByDocumentTypesMappingVO(documentTypesMappingVO);
		documentTypesMappingDetailsRepo.deleteAll(documentTypesMappingDetailsVOList);

		List<DocumentTypesMappingDetailsVO> documentTypesMappingDetailsVOs = new ArrayList<>();
		if (documentTypesMappingDTO.getDocumentTypeMappingDetailsDTO() != null) {
			for (DocumentTypesMappingDetailsDTO documentTypesMappingDetailsDTO : documentTypesMappingDTO
					.getDocumentTypeMappingDetailsDTO()) {

				DocumentTypesMappingDetailsVO documentTypesMappingDetailsVO = new DocumentTypesMappingDetailsVO();
				documentTypesMappingDetailsVO.setScreenName(documentTypesMappingDetailsDTO.getScreenName());
				documentTypesMappingDetailsVO.setScreenCode(documentTypesMappingDetailsDTO.getScreenCode());
				documentTypesMappingDetailsVO.setFinYear(documentTypesMappingDetailsDTO.getFinYear());
				documentTypesMappingDetailsVO.setBranch(documentTypesMappingDetailsDTO.getBranch());
				documentTypesMappingDetailsVO.setBranchCode(documentTypesMappingDetailsDTO.getBranchCode());
				documentTypesMappingDetailsVO.setFinyearId(documentTypesMappingDetailsDTO.getFinyearId());
				documentTypesMappingDetailsVO.setDocCode(documentTypesMappingDetailsDTO.getDocCode());
				documentTypesMappingDetailsVO.setPrefix(documentTypesMappingDetailsDTO.getPrefix());
				documentTypesMappingDetailsVO.setOrgId(documentTypesMappingDTO.getOrgId());
				documentTypesMappingDetailsVO.setLastNo(documentTypesMappingDetailsDTO.getLastNo());
				documentTypesMappingDetailsVO.setDocumentTypesMappingVO(documentTypesMappingVO);
				;
				documentTypesMappingDetailsVOs.add(documentTypesMappingDetailsVO);
			}
		}
		getDocumentTypesVOFromDocumentTypesDTO(documentTypesMappingDTO, documentTypesMappingVO);
		documentTypesMappingVO.setDocumentTypesMappingDetailsVO(documentTypesMappingDetailsVOs);
		return documentTypesMappingRepo.save(documentTypesMappingVO);
	}

	private void getDocumentTypesVOFromDocumentTypesDTO(@Valid DocumentTypesMappingDTO documentTypesMappingDTO,
			DocumentTypesMappingVO documentTypesMappingVO) {
		documentTypesMappingVO.setFinYear(documentTypesMappingDTO.getFinYear());
		documentTypesMappingVO.setBranch(documentTypesMappingDTO.getBranch());
		documentTypesMappingVO.setOrgId(documentTypesMappingDTO.getOrgId());
		documentTypesMappingVO.setActive(documentTypesMappingDTO.isActive());
	}

	 @Override
	    @Transactional
	    public List<Map<String,Object>> getAllDocumentTypesMappingDetailsByDocumentType(String branch, String branchCode, String finYr, Long orgId,String finyrId) {

	        Set<Object[]> result = documentTypesMappingRepo.findAllDocumentTypesMappingDetailsByDocumentType(branch, branchCode, finYr, orgId,finyrId);
	        return getresult(result);
	    }
	
	private List<Map<String, Object>> getresult(Set<Object[]> result) {
		 List<Map<String, Object>> details1 = new ArrayList<>();
	        for (Object[] fs : result) {
	            Map<String, Object> part = new HashMap<>();
	            part.put("screenCode", fs[0] != null ? fs[0].toString() : "");
	            part.put("screenName", fs[1] != null ? fs[1].toString() : "");
	            part.put("docCode", fs[2] != null ? fs[2].toString() : "");
	            part.put("finYrIdentifierId", fs[3] != null ? fs[3].toString() : "");
	            part.put("finyr", fs[4] != null ? fs[4].toString() : "");
	            part.put("branch", fs[5] != null ? fs[5].toString() : "");
	            part.put("branchCode", fs[6] != null ? fs[6].toString() : "");
	            part.put("prefix", fs[7] != null ? fs[7].toString() : "");
//	            part.put("orgId", fs[6] != null ? fs[6].toString() : "");
//	            part.put("docCode", fs[7] != null ? fs[7].toString() : "");
//	            part.put("prefix", fs[8] != null ? fs[8].toString() : "");
	            details1.add(part);
	        }
	        return details1;
	}
}