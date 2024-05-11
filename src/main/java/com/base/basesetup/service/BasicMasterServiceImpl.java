package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.BranchDTO;
import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.EmployeeDTO;
import com.base.basesetup.dto.Role;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.BranchVO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.EmployeeVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.entity.UserVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.BranchRepo;
import com.base.basesetup.repo.CityRepo;
import com.base.basesetup.repo.CompanyRepo;
import com.base.basesetup.repo.CountryRepo;
import com.base.basesetup.repo.CurrencyRepo;
import com.base.basesetup.repo.EmployeeRepo;
import com.base.basesetup.repo.FinancialRepo;
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
	FinancialRepo finRepo;
	
	@Autowired
	BranchRepo branchRepo;

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
		currencyVO.setUserId(currencyDTO.getUserid());
		currencyVO.setCountry(currencyDTO.getCountry());
		currencyVO.setCurrency(currencyDTO.getCurrency());
		currencyVO.setSubCurrency(currencyDTO.getSubCurrency());
		currencyVO.setCurrencySymbol(currencyDTO.getCurrencySymbol());
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
	public CompanyVO updateCreateCompany(@Valid CompanyDTO companyDTO) throws Exception {
		CompanyVO companyVO = new CompanyVO();
		if (ObjectUtils.isNotEmpty(companyDTO.getId())) {
			companyVO = companyRepo.findById(companyDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Company Details"));
		}
		getCompanyVOFromCompanyDTO(companyDTO, companyVO);
		companyRepo.save(companyVO);
		EmployeeVO emp = new EmployeeVO();
		emp.setEmployeeCode(companyVO.getEmployeeCode());
		emp.setEmployeeName(companyVO.getEmployeeName());
		emp.setOrgId(companyVO.getId());
		employeeRepo.save(emp);
		UserVO userVO = new UserVO();
		userVO.setEmail(companyVO.getEmail());
		userVO.setRole(Role.ROLE_ADMIN);
		userVO.setUserName(companyVO.getEmployeeCode());
		userVO.setOrgId(companyVO.getId());
		userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(companyVO.getPassword())));
		userRepo.save(userVO);
		return companyVO;

	}

	private void getCompanyVOFromCompanyDTO(@Valid CompanyDTO companyDTO, CompanyVO companyVO) throws Exception {
		companyVO.setCompanyCode(companyDTO.getCompanyCode());
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
		companyVO.setCreatedBy(companyDTO.getCreatedBy());
		companyVO.setUpdatedBy(companyDTO.getUpdatedBy());

	}

//	Employee -----------------------------------------------------------------------------------

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
	public EmployeeVO updateCreateEmployee(@Valid EmployeeDTO employeeDTO) throws ApplicationException {
		EmployeeVO employeeVO = new EmployeeVO();
		if (ObjectUtils.isNotEmpty(employeeDTO.getId())) {
			employeeVO = employeeRepo.findById(employeeDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Employee Details"));
		}
		getEmployeeVOFromEmployeeDTO(employeeDTO, employeeVO);
		return employeeRepo.save(employeeVO);
	}

	private void getEmployeeVOFromEmployeeDTO(@Valid EmployeeDTO employeeDTO, EmployeeVO employeeVO) {
		employeeVO.setOrgId(employeeDTO.getOrgId());
		employeeVO.setEmployeeCode(employeeDTO.getEmployeeCode());
		employeeVO.setGender(employeeDTO.getGender());
		employeeVO.setBranch(employeeDTO.getBranch());
		employeeVO.setBranchCode(employeeDTO.getBranchCode());
		employeeVO.setDepartment(employeeDTO.getDepartment());
		employeeVO.setDesignation(employeeDTO.getDesignation());
		employeeVO.setJoiningDate(employeeDTO.getJoiningDate());
		employeeVO.setDateOfBirth(employeeDTO.getDateOfBirth());
		employeeVO.setUserId(employeeDTO.getUserId());
		employeeVO.setCreatedBy(employeeDTO.getCreatedBy());
		employeeVO.setUpdateBy(employeeDTO.getUpdatedBy());

	}

//	Country -----------------------------------------------------------------------------------

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
		}
		getCountryVOFromCountryDTO(countryDTO, countryVO);
		return countryRepo.save(countryVO);
	}

	private void getCountryVOFromCountryDTO(@Valid CountryDTO countryDTO, CountryVO countryVO) {
		countryVO.setOrgId(countryDTO.getOrgId());
		countryVO.setCountryCode(countryDTO.getCountryCode());
		countryVO.setCountryName(countryDTO.getCountryName());
		countryVO.setUserId(countryDTO.getUserId());

	}

//State -----------------------------------------------------------------------------------
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
		if (ObjectUtils.isNotEmpty(stateDTO.getId())) {
			stateVO = stateRepo.findById(stateDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid State Details"));
		}
		getStateVOFromStateDTO(stateDTO, stateVO);
		return stateRepo.save(stateVO);
	}

	private void getStateVOFromStateDTO(@Valid StateDTO stateDTO, StateVO stateVO) {
		stateVO.setOrgId(stateDTO.getOrgId());
		stateVO.setStateCode(stateDTO.getStateCode());
		stateVO.setStateName(stateDTO.getStateName());
		stateVO.setUserId(stateDTO.getUserId());
		stateVO.setCountry(stateDTO.getCountry());
		stateVO.setRegion(stateDTO.getRegion());
		stateVO.setStateNumber(stateDTO.getStateNumber());
	}

//	private String country;
//	private String region;
//	private int stateNumber;
	@Override
	public List<StateVO> getAllStateByCountry(Long orgId, String country) {
		return stateRepo.findAllStateByCountry(orgId, country);
	}

//City -----------------------------------------------------------------------------------
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
		if (ObjectUtils.isNotEmpty(cityDTO.getId())) {
			cityVO = cityRepo.findById(cityDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid City Details"));
		}
		getCityVOFromCityDTO(cityDTO, cityVO);
		return cityRepo.save(cityVO);
	}

	private void getCityVOFromCityDTO(@Valid CityDTO cityDTO, CityVO cityVO) {
		cityVO.setOrgId(cityDTO.getOrgId());
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

//Financial Year-----------------------------------------------------------------------------------
	@Override
	public List<FinancialYearVO> getAllFinancial() {
		return finRepo.findAll();
	}

	@Override
	public FinancialYearVO createFinancial(FinancialYearVO financialYearVO) {
		return finRepo.save(financialYearVO);
	}

	@Override
	public Optional<FinancialYearVO> updateFinancial(FinancialYearVO financialYearVO) {
		if (finRepo.existsById(financialYearVO.getId())) {
			return Optional.of(finRepo.save(financialYearVO));
		} else {
			return Optional.empty();
		}

	}

//Branch-----------------------------------------------------------------------------------
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
		List<BranchVO> branchVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received Branch BY OrgId : {}", orgId);
			branchVO = branchRepo.findBranchByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received Branch For All OrgId.");
			branchVO = branchRepo.findAll();
		}
		return branchVO;
	}

	@Override
	public BranchVO updateCreateBranch(@Valid BranchDTO branchDTO) throws ApplicationException {
		BranchVO branchVO = new BranchVO();
		if (ObjectUtils.isNotEmpty(branchDTO.getId())) {
			branchVO = branchRepo.findById(branchDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Branch Details"));
		}
		getBranchVOFromBranchDTO(branchDTO, branchVO);
		return branchRepo.save(branchVO);
	}

	private void getBranchVOFromBranchDTO(@Valid BranchDTO branchDTO, BranchVO branchVO) {
		branchVO.setOrgId(branchDTO.getOrgId());
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
		branchVO.setCreatedBy(branchDTO.getCreatedBy());
		branchVO.setUpdatedBy(branchDTO.getUpdatedBy());
		branchVO.setUserId(branchDTO.getUserId());

	}
}
