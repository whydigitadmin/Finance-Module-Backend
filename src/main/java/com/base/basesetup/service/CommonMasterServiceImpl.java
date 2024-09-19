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
import com.base.basesetup.dto.RegionDTO;
import com.base.basesetup.dto.ResponsibilitiesDTO;
import com.base.basesetup.dto.Role;
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
import com.base.basesetup.entity.RegionVO;
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
import com.base.basesetup.repo.RegionRepo;
import com.base.basesetup.repo.ResponsibilitiesRepo;
import com.base.basesetup.repo.RoleRepo;
import com.base.basesetup.repo.ScreenRepo;
import com.base.basesetup.repo.StateRepo;
import com.base.basesetup.repo.UserRepo;
import com.base.basesetup.util.CryptoUtils;

@Service
public class CommonMasterServiceImpl implements CommonMasterService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CommonMasterServiceImpl.class);

	@Autowired
	CountryRepo countryRepo;

	@Autowired
	CurrencyRepo currencyRepo;

	@Autowired
	StateRepo stateRepo;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	CityRepo cityRepo;

	@Autowired
	RegionRepo regionRepo;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	EmployeeRepo employeeRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	FinancialYearRepo finRepo;

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

	// Company

	@Override
	public List<CompanyVO> getAllCompany() {
		return companyRepo.findAll();
	}

	@Override
	public List<CompanyVO> getCompanyById(Long companyid) {
		return companyRepo.findByCompany(companyid);
	}

	@Override
	@Transactional
	public CompanyVO createCompany(CompanyDTO companyDTO) throws Exception {

		if (companyRepo.existsByCompanyCodeAndCompanyNameAndEmployeeCodeAndEmailAndPhoneAndId(
				companyDTO.getCompanyCode(), companyDTO.getCompanyName(), companyDTO.getEmployeeCode(),
				companyDTO.getEmail(), companyDTO.getPhone(), companyDTO.getId())) {

			String errorMessage = String.format(
					"The CompanyCode : %s And CompanyName : %s And EmployeeCode : %s And Email : %s And PhoneNumber : %s Already Exists"
							+ "This Organization",
					companyDTO.getCompanyCode(), companyDTO.getCompanyName(), companyDTO.getEmployeeCode(),
					companyDTO.getEmail(), companyDTO.getPhone());
			throw new ApplicationException(errorMessage);
		}

		if (companyRepo.existsByCompanyCodeAndId(companyDTO.getCompanyCode(), companyDTO.getId())) {

			String errorMessage = String.format("The CompanyCode : %s Already Exists This Organization",
					companyDTO.getCompanyCode());
			throw new ApplicationException(errorMessage);
		}

		if (companyRepo.existsByCompanyNameAndId(companyDTO.getCompanyName(), companyDTO.getId())) {
			String errorMessage = String.format("The CompanyName : %s Already Exists This Organization",
					companyDTO.getCompanyName());
			throw new ApplicationException(errorMessage);
		}

		if (companyRepo.existsByEmployeeCodeAndId(companyDTO.getEmployeeCode(), companyDTO.getId())) {
			String errorMessage = String.format("The EmployeeCode : %s Already Exists This Organization",
					companyDTO.getEmployeeCode());
			throw new ApplicationException(errorMessage);
		}

		if (companyRepo.existsByEmailAndId(companyDTO.getEmail(), companyDTO.getId())) {
			String errorMessage = String.format("The Email : %s Already Exists This Organization",
					companyDTO.getEmail());
			throw new ApplicationException(errorMessage);
		}

		if (companyRepo.existsByPhoneAndId(companyDTO.getPhone(), companyDTO.getId())) {
			String errorMessage = String.format("The PhoneNumber : %s Already Exists This Organization",
					companyDTO.getPhone());
			throw new ApplicationException(errorMessage);
		}

		CompanyVO companyVO = new CompanyVO();
		getCompanyVOFromCompanyDTO(companyVO, companyDTO);
		companyRepo.save(companyVO);

		EmployeeVO employeeVO = new EmployeeVO();
		employeeVO.setEmployeeName(companyVO.getEmployeeName());
		employeeVO.setEmployeeCode(companyVO.getEmployeeCode());
		employeeVO.setActive(true);
		employeeVO.setOrgId(companyVO.getId());
		employeeRepo.save(employeeVO);

		UserVO userVO = new UserVO();
		userVO.setUserName(companyVO.getEmployeeCode());
		userVO.setEmployeeName(companyVO.getEmployeeName());
		userVO.setEmail(companyVO.getEmail());
		userVO.setMobileNo(companyVO.getPhone());
		userVO.setRole(Role.ROLE_USER);
		userVO.setUserType("admin");
		userVO.setOrgId(companyVO.getId());
		userVO.setCreatedby(companyVO.getCreatedBy());
		userVO.setUpdatedby(companyVO.getCreatedBy());
		userVO.setActive(true);
		userVO.setLoginStatus(false);
		userVO.setCompanyVO(companyVO);

		try {
			userVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(companyDTO.getPassword())));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationContextException("Unable To Encode Password");
		}

		userRepo.save(userVO);

		return companyVO;
	}

	private void getCompanyVOFromCompanyDTO(CompanyVO companyVO, CompanyDTO companyDTO) {
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
		companyVO.setCreatedBy(companyDTO.getCreatedBy());
		companyVO.setUpdatedBy(companyDTO.getCreatedBy());
		companyVO.setActive(companyDTO.isActive());
		companyVO.setCancel(companyDTO.isCancel());
		companyVO.setGst(companyDTO.getGst());
		companyVO.setCeo(companyDTO.getCeo());

		try {
			companyVO.setPassword(encoder.encode(CryptoUtils.getDecrypt(companyDTO.getPassword())));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new ApplicationContextException("Unable To Encode Password");
		}
	}

	public CompanyVO updateCompany(CompanyDTO companyDTO) throws ApplicationException {

		if (ObjectUtils.isEmpty(companyDTO.getId())) {
			throw new ApplicationException("Invalid Company Id");
		}

		CompanyVO companyVO = companyRepo.findById(companyDTO.getId())
				.orElseThrow(() -> new ApplicationException("Company not found for Id: " + companyDTO.getId()));

		mapCompanyDTOToCompanyVO(companyVO, companyDTO);

		return companyRepo.save(companyVO);
	}

	private void mapCompanyDTOToCompanyVO(CompanyVO companyVO, CompanyDTO companyDTO) {
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
//			companyVO.setEmployeeCode(companyDTO.getEmployeeCode());
//			companyVO.setEmployeeName(companyDTO.getEmployeeName());
		companyVO.setCreatedBy(companyDTO.getCreatedBy());
		companyVO.setUpdatedBy(companyDTO.getUpdatedBy());
		companyVO.setActive(companyDTO.isActive());
		companyVO.setCancel(companyDTO.isCancel());
		companyVO.setRole(companyDTO.getRole());
		companyVO.setGst(companyDTO.getGst());
		companyVO.setCeo(companyDTO.getCeo());
	}

	@Override
	public void deleteCompany(Long companyid) {
		companyRepo.deleteById(companyid);
	}

//	@Override
//	public CompanyVO saveImage(MultipartFile file, @RequestParam Long id)
//			throws ApplicationException, java.io.IOException {
//
//		CompanyVO image = companyRepo.findById(id)
//				.orElseThrow(() -> new ApplicationException("Invalid company id" + id));
//
//		image.setImageName(file.getOriginalFilename());
//		image.setData(file.getBytes());
//		return companyRepo.save(image);
//
//	}

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
	public List<Map<String, Object>> getAllDocumentTypesMappingDetailsByDocumentType(String branch, String branchCode,
			String finYr, Long orgId, String finyrId) {

		Set<Object[]> result = documentTypesMappingRepo.findAllDocumentTypesMappingDetailsByDocumentType(branch,
				branchCode, finYr, orgId, finyrId);
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

	// Country

	@Override
	public List<CountryVO> getAllCountry(Long orgid) {
		return countryRepo.findAll(orgid);
	}

	@Override
	public Optional<CountryVO> getCountryById(Long countryid) {
		return countryRepo.findById(countryid);
	}

	@Override
	public Map<String, Object> createUpdateCountry(CountryDTO countryDTO) throws ApplicationException {

		CountryVO countryVO;
		String message = null;

		if (ObjectUtils.isEmpty(countryDTO.getId())) {
			if (countryRepo.existsByCountryNameAndCountryCodeAndOrgId(countryDTO.getCountryName(),
					countryDTO.getCountryCode(), countryDTO.getOrgId())) {
				String errorMessage = String.format(
						"The CountryName: %s and CountryCode: %s already exists This Organization.",
						countryDTO.getCountryName(), countryDTO.getCountryCode());
				throw new ApplicationException(errorMessage);
			}

			if (countryRepo.existsByCountryNameAndOrgId(countryDTO.getCountryName(), countryDTO.getOrgId())) {
				String errorMessage = String.format("The CountryName: %s already exists This Organization.",
						countryDTO.getCountryName());
				throw new ApplicationException(errorMessage);
			}

			if (countryRepo.existsByCountryCodeAndOrgId(countryDTO.getCountryCode(), countryDTO.getOrgId())) {
				String errorMessage = String.format("The CountryCode: %s already exists This Organization.",
						countryDTO.getCountryCode());
				throw new ApplicationException(errorMessage);
			}
			// Create new branch
						countryVO = new CountryVO();
						countryVO.setCreatedBy(countryDTO.getCreatedBy());
						countryVO.setUpdatedBy(countryDTO.getCreatedBy());
			message = "Country Creation SuccessFully";
		}
		else{
			// Update existing branch
			countryVO = countryRepo.findById(countryDTO.getId())
					.orElseThrow(() -> new ApplicationException("Branch not found with id: " + countryDTO.getId()));
			countryVO.setUpdatedBy(countryDTO.getCreatedBy());
			if (!countryVO.getCountryCode().equalsIgnoreCase(countryDTO.getCountryCode())) {
				if (countryRepo.existsByCountryCodeAndOrgId(countryDTO.getCountryCode(), countryDTO.getOrgId())) {
					String errorMessage = String.format("The CountryCode: %s already exists This Organization.",
							countryDTO.getCountryCode());
					throw new ApplicationException(errorMessage);
				}
				countryVO.setCountryCode(countryDTO.getCountryCode().toUpperCase());
			}
			if (!countryVO.getCountryName().equalsIgnoreCase(countryDTO.getCountryName())) {
				if (countryRepo.existsByCountryNameAndOrgId(countryDTO.getCountryName(), countryDTO.getOrgId())) {
					String errorMessage = String.format("The CountryName: %s already exists This Organization.",
							countryDTO.getCountryName());
					throw new ApplicationException(errorMessage);
				}
				countryVO.setCountryName(countryDTO.getCountryName().toUpperCase());

			}
			message = "Country Update Successfully";
		} 

		getCountryVOFromCounytryDTO(countryVO, countryDTO);
		countryRepo.save(countryVO);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", message);
		response.put("countryVO", countryVO);
		return response;

	}

	private void getCountryVOFromCounytryDTO(CountryVO countryVO, CountryDTO countryDTO) {
		countryVO.setCountryName(countryDTO.getCountryName().toUpperCase());
		countryVO.setCountryCode(countryDTO.getCountryCode().toUpperCase());
		countryVO.setActive(countryDTO.isActive());
		countryVO.setOrgId(countryDTO.getOrgId());
		countryVO.setCancel(countryDTO.isCancel());

	}

	@Override
	public void deleteCountry(Long countryid) {

		countryRepo.deleteById(countryid);
	}

	// State

	@Override
	public List<StateVO> getAllgetAllStates(Long orgid) {
		return stateRepo.findAllByOrgId(orgid);
	}

	@Override
	public Optional<StateVO> getStateById(Long stateid) {
		return stateRepo.findById(stateid);
	}

	@Override
	public List<StateVO> getStatesByCountry(Long orgid, String country) {

		return stateRepo.findByCountry(orgid, country);
	}

	@Override
	@Transactional
	public Map<String, Object> createUpdateState(StateDTO stateDTO) throws ApplicationException {
		StateVO stateVO;
		String message = null;

		if (ObjectUtils.isEmpty(stateDTO.getId())) {
			// Check for existing state by state code, state number, and state name
			if (stateRepo.existsByStateCodeAndOrgId(stateDTO.getStateCode(), stateDTO.getOrgId())) {
				String errorMessage = String.format("The StateCode: %s already exists in This Organization.",
						stateDTO.getStateCode());
				throw new ApplicationException(errorMessage);
			}
			if (stateRepo.existsByStateNumberAndOrgId(stateDTO.getStateNumber(), stateDTO.getOrgId())) {
				String errorMessage = String.format("The StateNumber: %s already exists in This Organization.",
						stateDTO.getStateNumber());
				throw new ApplicationException(errorMessage);
			}
			if (stateRepo.existsByStateNameAndOrgId(stateDTO.getStateName(), stateDTO.getOrgId())) {
				String errorMessage = String.format("The StateName: %s already exists in This Organization.",
						stateDTO.getStateName());
				throw new ApplicationException(errorMessage);
			}

			// Create new state
			stateVO = new StateVO();
			stateVO.setCreatedBy(stateDTO.getCreatedBy());
			stateVO.setUpdatedBy(stateDTO.getCreatedBy());
			message = "State Creation Successfully";
		} else {
			// Update existing state
			stateVO = stateRepo.findById(stateDTO.getId())
					.orElseThrow(() -> new ApplicationException("State not found with id: " + stateDTO.getId()));

			stateVO.setUpdatedBy(stateDTO.getCreatedBy());

			if (!stateVO.getStateCode().equalsIgnoreCase(stateDTO.getStateCode())) {
				if (stateRepo.existsByStateCodeAndOrgId(stateDTO.getStateCode(), stateDTO.getOrgId())) {
					String errorMessage = String.format("The StateCode: %s already exists in This Organization.",
							stateDTO.getStateCode());
					throw new ApplicationException(errorMessage);
				}
				stateVO.setStateCode(stateDTO.getStateCode().toUpperCase());
			}

			if (!stateVO.getStateName().equalsIgnoreCase(stateDTO.getStateName())) {
				if (stateRepo.existsByStateNameAndOrgId(stateDTO.getStateName(), stateDTO.getOrgId())) {
					String errorMessage = String.format("The StateName: %s already exists in This Organization.",
							stateDTO.getStateName());
					throw new ApplicationException(errorMessage);
				}
				stateVO.setStateName(stateDTO.getStateName().toUpperCase());
			}

			if (!stateVO.getStateNumber().equalsIgnoreCase(stateDTO.getStateNumber())) {
				if (stateRepo.existsByStateNumberAndOrgId(stateDTO.getStateNumber(), stateDTO.getOrgId())) {
					String errorMessage = String.format("The StateNumber: %s already exists in This Organization.",
							stateDTO.getStateNumber());
					throw new ApplicationException(errorMessage);
				}
				stateVO.setStateNumber(stateDTO.getStateNumber().toUpperCase());
			}

			message = "State Update Successfully";
		}

		// Map the remaining fields
		getStateVOFromStateDTO(stateVO, stateDTO);

		// Save the entity
		stateRepo.save(stateVO);

		// Prepare the response
		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("stateVO", stateVO);

		return response;
	}

	private void getStateVOFromStateDTO(StateVO stateVO, StateDTO stateDTO) {
		stateVO.setStateCode(stateDTO.getStateCode().toUpperCase());
		stateVO.setStateName(stateDTO.getStateName().toUpperCase());
		stateVO.setStateNumber(stateDTO.getStateNumber().toUpperCase());
		stateVO.setCountry(stateDTO.getCountry().toUpperCase());
		stateVO.setRegion(stateDTO.getRegion().toUpperCase());
		stateVO.setActive(stateDTO.isActive());
		stateVO.setCancel(stateDTO.isCancel());
		stateVO.setOrgId(stateDTO.getOrgId());
		// stateVO.setDupchk(stateDTO.getOrgId() + stateDTO.getStateCode() +
		// stateDTO.getStateName());
	}

	@Override
	public void deleteState(Long countryid) {
		stateRepo.deleteById(countryid);
	}

	// City

	@Override
	public List<CityVO> getAllgetAllCities(Long orgid) {
		return cityRepo.findAll(orgid);
	}

	@Override
	public List<CityVO> getAllCitiesByState(Long orgid, String state) {

		return cityRepo.findAll(orgid, state);
	}

	@Override
	public Optional<CityVO> getCityById(Long cityid) {
		return cityRepo.findById(cityid);
	}

	@Override
	@Transactional
	public Map<String, Object> createUpdateCity(CityDTO cityDTO) throws ApplicationException {
		CityVO cityVO;
		String message;

		if (ObjectUtils.isEmpty(cityDTO.getId())) {
			if (cityRepo.existsByCityCodeAndOrgId(cityDTO.getCityCode(), cityDTO.getOrgId())) {
				String errorMessage = String.format("The CityCode: %s already exists in this organization.",
						cityDTO.getCityCode());
				throw new ApplicationException(errorMessage);
			}
			if (cityRepo.existsByCityNameAndOrgId(cityDTO.getCityName(), cityDTO.getOrgId())) {
				String errorMessage = String.format("The CityName: %s already exists in this organization.",
						cityDTO.getCityName());
				throw new ApplicationException(errorMessage);
			}
			// Create new city
			cityVO = new CityVO();
			cityVO.setCreatedBy(cityDTO.getCreatedBy());
			cityVO.setUpdatedBy(cityDTO.getCreatedBy());
			message = "City Created Successfully";
		} else {
			// Update existing city
			cityVO = cityRepo.findById(cityDTO.getId())
					.orElseThrow(() -> new ApplicationException("City not found with id: " + cityDTO.getId()));
			cityVO.setUpdatedBy(cityDTO.getCreatedBy());

			if (!cityVO.getCityCode().equalsIgnoreCase(cityDTO.getCityCode())) {
				if (cityRepo.existsByCityCodeAndOrgId(cityDTO.getCityCode(), cityDTO.getOrgId())) {
					String errorMessage = String.format("The CityCode: %s already exists in this organization.",
							cityDTO.getCityCode());
					throw new ApplicationException(errorMessage);
				}
				cityVO.setCityCode(cityDTO.getCityCode().toUpperCase());
			}

			if (!cityVO.getCityName().equalsIgnoreCase(cityDTO.getCityName())) {
				if (cityRepo.existsByCityNameAndOrgId(cityDTO.getCityName(), cityDTO.getOrgId())) {
					String errorMessage = String.format("The CityName: %s already exists in this organization.",
							cityDTO.getCityName());
					throw new ApplicationException(errorMessage);
				}
				cityVO.setCityName(cityDTO.getCityName().toUpperCase());
			}
			message = "City Updated Successfully";
		}

		getCityVOFromCityDTO(cityVO, cityDTO);
		cityRepo.save(cityVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("cityVO", cityVO);
		return response;
	}

	private void getCityVOFromCityDTO(CityVO cityVO, CityDTO cityDTO) {
		cityVO.setCityCode(cityDTO.getCityCode().toUpperCase());
		cityVO.setCityName(cityDTO.getCityName().toUpperCase());
		cityVO.setCountry(cityDTO.getCountry().toUpperCase());
		cityVO.setState(cityDTO.getState().toUpperCase());
		cityVO.setActive(cityDTO.isActive());
		cityVO.setOrgId(cityDTO.getOrgId());
		cityVO.setCancel(cityDTO.isCancel());
	}

	@Override
	public void deleteCity(Long cityid) {
		cityRepo.deleteById(cityid);
	}

	// Region

	@Override
	public List<RegionVO> getAllRegios() {

		return regionRepo.findAll();
	}

	@Override
	public List<RegionVO> getAllRegionsByOrgId(Long orgId) {
		return regionRepo.findAll(orgId);
	}

	@Override
	public Optional<RegionVO> getRegionById(Long Regionid) {
		return regionRepo.findById(Regionid);
	}

	@Override
	@Transactional
	public Map<String, Object> createUpdateRegion(RegionDTO regionDTO) throws ApplicationException {
		RegionVO regionVO;
		String message;

		if (ObjectUtils.isEmpty(regionDTO.getId())) {
			if (regionRepo.existsByRegionNameAndOrgId(regionDTO.getRegionName(), regionDTO.getOrgId())) {
				String errorMessage = String.format("This RegionName:%s Already Exists in This Organization",
						regionDTO.getRegionName().toUpperCase());
				throw new ApplicationException(errorMessage);
			}
			if (regionRepo.existsByRegionCodeAndOrgId(regionDTO.getRegionCode(), regionDTO.getOrgId())) {
				String errorMessage = String.format("This RegionCode:%s Already Exists in This Organization",
						regionDTO.getRegionCode().toUpperCase());
				throw new ApplicationException(errorMessage);
			}
			// Create new region
			regionVO = new RegionVO();
			regionVO.setCreatedBy(regionDTO.getCreatedBy());
			regionVO.setUpdatedBy(regionDTO.getCreatedBy());
			message = "Region Created Successfully";
		} else {
			// Update existing region
			regionVO = regionRepo.findById(regionDTO.getId()).orElseThrow(
					() -> new ApplicationException("This Id Is Not Found Any Information: " + regionDTO.getId()));
			regionVO.setUpdatedBy(regionDTO.getCreatedBy());

			if (!regionVO.getRegionName().equalsIgnoreCase(regionDTO.getRegionName())) {
				if (regionRepo.existsByRegionNameAndOrgId(regionDTO.getRegionName(), regionDTO.getOrgId())) {
					String errorMessage = String.format("This RegionName:%s Already Exists in This Organization",
							regionDTO.getRegionName());
					throw new ApplicationException(errorMessage);
				}
				regionVO.setRegionName(regionDTO.getRegionName().toUpperCase());
			}

			if (!regionVO.getRegionCode().equalsIgnoreCase(regionDTO.getRegionCode())) {
				if (regionRepo.existsByRegionCodeAndOrgId(regionDTO.getRegionCode(), regionDTO.getOrgId())) {
					String errorMessage = String.format("This RegionCode:%s Already Exists in This Organization",
							regionDTO.getRegionCode());
					throw new ApplicationException(errorMessage);
				}
				regionVO.setRegionCode(regionDTO.getRegionCode().toUpperCase());
			}
			message = "Region Updated Successfully";
		}

		getRegionVOFromRegionDTO(regionVO, regionDTO);
		regionRepo.save(regionVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("regionVO", regionVO);
		return response;
	}

	private void getRegionVOFromRegionDTO(RegionVO regionVO, RegionDTO regionDTO) {
		regionVO.setActive(regionDTO.isActive());
		regionVO.setOrgId(regionDTO.getOrgId());
		regionVO.setCancel(regionDTO.isCancel());
		regionVO.setRegionCode(regionDTO.getRegionCode().toUpperCase());
		regionVO.setRegionName(regionDTO.getRegionName().toUpperCase());
	}

	@Override
	public void deleteRegion(Long regionid) {
		regionRepo.deleteById(regionid);
	}

	// Currency
	@Override
	public List<CurrencyVO> getAllCurrency(Long orgid) {

		return currencyRepo.findAll(orgid);
	}

	@Override
	public Optional<CurrencyVO> getCurrencyById(Long currencyid) {

		return currencyRepo.findById(currencyid);
	}

	@Override
	@Transactional
	public Map<String, Object> createUpdateCurrency(CurrencyDTO currencyDTO) throws ApplicationException {

		CurrencyVO currencyVO;
		String message = null;

		if (ObjectUtils.isEmpty(currencyDTO.getId())) {
			if (currencyRepo.existsByCurrencyAndOrgId(currencyDTO.getCurrency(), currencyDTO.getOrgId())) {
				String errorMessage = String.format("This Currency:%s Already Exists in This Organization.",
						currencyDTO.getCurrency());
				throw new ApplicationException(errorMessage);
			}
			if (currencyRepo.existsByCurrencySymbolAndOrgId(currencyDTO.getCurrencySymbol(), currencyDTO.getOrgId())) {
				String errorMessage = String.format("This CurrencySymbol:%s Already Exists in This Organization.",
						currencyDTO.getCurrencySymbol());
				throw new ApplicationException(errorMessage);
			}
			if (currencyRepo.existsBySubCurrencyAndOrgId(currencyDTO.getSubCurrency(), currencyDTO.getOrgId())) {
				String errorMessage = String.format("This SubCurrency:%s Already Exists in This Organization.",
						currencyDTO.getSubCurrency());
				throw new ApplicationException(errorMessage);
			}

			// Create new currency
			currencyVO = new CurrencyVO();
			currencyVO.setCreatedBy(currencyDTO.getCreatedBy());
			currencyVO.setUpdatedBy(currencyDTO.getCreatedBy());
			message = "Currency Created Successfully";
		} else {
			// Update existing currency
			currencyVO = currencyRepo.findById(currencyDTO.getId()).orElseThrow(
					() -> new ApplicationException("This Id Is Not Found Any Information: " + currencyDTO.getId()));
			currencyVO.setUpdatedBy(currencyDTO.getCreatedBy());

			if (!currencyVO.getCurrency().equalsIgnoreCase(currencyDTO.getCurrency())) {
				if (currencyRepo.existsByCurrencyAndOrgId(currencyDTO.getCurrency(), currencyDTO.getOrgId())) {
					String errorMessage = String.format("This Currency:%s Already Exists in This Organization.",
							currencyDTO.getCurrency());
					throw new ApplicationException(errorMessage);
				}
				currencyVO.setCurrency(currencyDTO.getCurrency().toUpperCase());
			}
			if (!currencyVO.getSubCurrency().equalsIgnoreCase(currencyDTO.getSubCurrency())) {
				if (currencyRepo.existsBySubCurrencyAndOrgId(currencyDTO.getSubCurrency(), currencyDTO.getOrgId())) {
					String errorMessage = String.format("This SubCurrency:%s Already Exists in This Organization.",
							currencyDTO.getSubCurrency());
					throw new ApplicationException(errorMessage);
				}
				currencyVO.setSubCurrency(currencyDTO.getSubCurrency().toUpperCase());
			}
			if (!currencyVO.getCurrencySymbol().equalsIgnoreCase(currencyDTO.getCurrencySymbol())) {
				if (currencyRepo.existsByCurrencySymbolAndOrgId(currencyDTO.getCurrencySymbol(),
						currencyDTO.getOrgId())) {
					String errorMessage = String.format("This CurrencySymbol:%s Already Exists in This Organization.",
							currencyDTO.getCurrencySymbol());
					throw new ApplicationException(errorMessage);
				}
				currencyVO.setCurrencySymbol(currencyDTO.getCurrencySymbol().toUpperCase());
			}
			message = "Currency Updated Successfully";
		}

		getCurrencyVOFromCurrencyDTO(currencyVO, currencyDTO);
		currencyRepo.save(currencyVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("currencyVO", currencyVO);
		return response;
	}

	private void getCurrencyVOFromCurrencyDTO(CurrencyVO currencyVO, CurrencyDTO currencyDTO) {
		currencyVO.setCurrency(currencyDTO.getCurrency().toUpperCase());
		currencyVO.setSubCurrency(currencyDTO.getSubCurrency().toUpperCase());
		currencyVO.setCurrencySymbol(currencyDTO.getCurrencySymbol().toUpperCase());
		currencyVO.setActive(currencyDTO.isActive());
		currencyVO.setCancel(currencyDTO.isCancel());
		currencyVO.setCountry(currencyDTO.getCountry().toUpperCase());
		currencyVO.setOrgId(currencyDTO.getOrgId());
	}

	@Override
	public void deleteCurrency(Long currencyid) {
		currencyRepo.deleteById(currencyid);

	}

}