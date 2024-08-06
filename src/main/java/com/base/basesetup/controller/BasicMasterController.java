package com.base.basesetup.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
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
import com.base.basesetup.dto.ResponseDTO;
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
import com.base.basesetup.service.BasicMasterService;

import io.jsonwebtoken.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/api/basicMaster")
public class BasicMasterController extends BaseController {

	@Autowired
	BasicMasterService basicMasterService;

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterController.class);

	// Currency
	@GetMapping("/getCurrencyById")
	public ResponseEntity<ResponseDTO> getCurrencyById(@RequestParam(required = false) Long id) {
		String methodName = "getCurrenctById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CurrencyVO> currencyVO = new ArrayList<>();
		try {
			currencyVO = basicMasterService.getCurrencyById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency information get successfully By Id");
			responseObjectsMap.put("currencyVO", currencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Currency information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCurrencyByOrgId")
	public ResponseEntity<ResponseDTO> getCurrencyByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getCurrencyByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CurrencyVO> currencyVO = new ArrayList<>();
		try {
			currencyVO = basicMasterService.getCurrencyByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency information get successfully By OrgId");
			responseObjectsMap.put("currencyVO", currencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Currency information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateCurrency")
	public ResponseEntity<ResponseDTO> updateCreateCurrency(@Valid @RequestBody CurrencyDTO currencyDTO) {
		String methodName = "updateCreateCurrency()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CurrencyVO currencyVO = basicMasterService.updateCreateCurrency(currencyDTO);
			if (currencyVO != null) {
				boolean isUpdate = currencyDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Currency updated successfully" : "Currency created successfully");
				responseObjectsMap.put("currencyVO", currencyVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = currencyDTO.getId() != null;
				errorMsg = isUpdate ? "Currency not found for ID: " + currencyDTO.getId() : "Country creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Currency update failed" : "country creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = currencyDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Currency update failed" : "Currency creation failed	", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCurrencyByActive")
	public ResponseEntity<ResponseDTO> getCurrencyByActive() {
		String methodName = "getCurrencyByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CurrencyVO> currencyVO = new ArrayList<>();
		try {
			currencyVO = basicMasterService.getCurrencyByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency information get successfully By Active");
			responseObjectsMap.put("currencyVO", currencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Currency information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Company-----------------------------------------------------------------------------------
	@GetMapping("/getCompanyById")
	public ResponseEntity<ResponseDTO> getCompanyById(@RequestParam(required = false) Long id) {
		String methodName = "getCompanyById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CompanyVO> companyVO = new ArrayList<>();
		try {
			companyVO = basicMasterService.getCompanyById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company information get successfully By Id");
			responseObjectsMap.put("companyVO", companyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Company information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCompanyByOrgId")
	public ResponseEntity<ResponseDTO> getCompanyByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getCompanyByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CompanyVO> companyVO = new ArrayList<>();
		try {
			companyVO = basicMasterService.getCompanyByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company information get successfully By OrgId");
			responseObjectsMap.put("companyVO", companyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Company information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateCompany")
	public ResponseEntity<ResponseDTO> updateCreateCompany(@Valid @RequestBody CompanyDTO companyDTO) {
		String methodName = "updateCreateCompany()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CompanyVO companyVO = basicMasterService.updateCreateCompany(companyDTO);
			if (companyVO != null) {
				boolean isUpdate = companyDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Company updated successfully" : "Company created successfully");
				responseObjectsMap.put("companyVO", companyVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = companyDTO.getId() != null;
				errorMsg = isUpdate ? "Company not found for ID: " + companyDTO.getId() : "Company creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Company update failed" : "Company creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = companyDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Company update failed" : "Company creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCompanyByActive")
	public ResponseEntity<ResponseDTO> getCompanyByActive() {
		String methodName = "getCompanyByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CompanyVO> companyVO = new ArrayList<>();
		try {
			companyVO = basicMasterService.getCompanyByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company information get successfully By Active");
			responseObjectsMap.put("companyVO", companyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Company information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/uploadCompanyLogo")
	public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id)
			throws IOException, java.io.IOException, ApplicationException {
		CompanyVO image = basicMasterService.saveImage(file, id);
		return ResponseEntity.status(HttpStatus.OK).body("Company Logo uploaded successfully: " + image.getId());
	}

	@GetMapping("/getCompanyLogo/{id}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
		Optional<CompanyVO> imageOptional = basicMasterService.getImage(id);
		if (imageOptional.isPresent()) {
			CompanyVO image = imageOptional.get();
			return ResponseEntity.ok().header("attachment; filename=\"" + image.getImageName() + "\"")
					.body(image.getData());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	// Employee-----------------------------------------------------------------------------------
	@GetMapping("/getEmployeeById")
	public ResponseEntity<ResponseDTO> getEmployeeById(@RequestParam(required = false) Long id) {
		String methodName = "getEmployeeById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeVO> employeeVO = new ArrayList<>();
		try {
			employeeVO = basicMasterService.getEmployeeById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee information get successfully By Id");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getEmployeeByOrgId")
	public ResponseEntity<ResponseDTO> getEmployeeByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getEmployeeByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeVO> employeeVO = new ArrayList<>();
		try {
			employeeVO = basicMasterService.getEmployeeByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee information get successfully By OrgId");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateEmployee")
	public ResponseEntity<ResponseDTO> updateCreateEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		String methodName = "updateCreateEmployee()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			EmployeeVO employeeVO = basicMasterService.updateCreateEmployee(employeeDTO);
			if (employeeVO != null) {
				boolean isUpdate = employeeDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Employee updated successfully" : "Employee created successfully");
				responseObjectsMap.put("employeeVO", employeeVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = employeeDTO.getId() != null;
				errorMsg = isUpdate ? "Employee not found for ID: " + employeeDTO.getId() : "Employee creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Employee update failed" : "Employee creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = employeeDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Employee update failed" : "Employee creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getEmployeeByActive")
	public ResponseEntity<ResponseDTO> getEmployeeByActive() {
		String methodName = "getEmployeeByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<EmployeeVO> employeeVO = new ArrayList<>();
		try {
			employeeVO = basicMasterService.getEmployeeByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee information get successfully By Active");
			responseObjectsMap.put("employeeVO", employeeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Employee information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	// Country
	@GetMapping("/getCountryById")
	public ResponseEntity<ResponseDTO> getCountryById(@RequestParam(required = false) Long id) {
		String methodName = "getCountryById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CountryVO> countryVO = new ArrayList<>();
		try {
			countryVO = basicMasterService.getCountryById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country information get successfully By Id");
			responseObjectsMap.put("countryVO", countryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Country information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCountryByOrgId")
	public ResponseEntity<ResponseDTO> getCountryByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getCountryByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CountryVO> countryVO = new ArrayList<>();
		try {
			countryVO = basicMasterService.getCountryByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country information get successfully By OrgId");
			responseObjectsMap.put("countryVO", countryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Country information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateCountry")
	public ResponseEntity<ResponseDTO> updateCreateCountry(@Valid @RequestBody CountryDTO countryDTO) {
		String methodName = "updateCreateCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CountryVO countryVO = basicMasterService.updateCreateCountry(countryDTO);
			if (countryVO != null) {
				boolean isUpdate = countryDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Country updated successfully" : "Country created successfully");
				responseObjectsMap.put("countryVO", countryVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = countryDTO.getId() != null;
				errorMsg = isUpdate ? "Country not found for ID: " + countryDTO.getId() : "Country creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Country update failed" : "Country creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = countryDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Country update failed" : "Country creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCountryByActive")
	public ResponseEntity<ResponseDTO> getCountryByActive() {
		String methodName = "getCountryByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CountryVO> countryVO = new ArrayList<>();
		try {
			countryVO = basicMasterService.getCountryByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country information get successfully By Active");
			responseObjectsMap.put("countryVO", countryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Country information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// State
	@GetMapping("/getStateById")
	public ResponseEntity<ResponseDTO> getStateById(@RequestParam(required = false) Long id) {
		String methodName = "getStateById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = new ArrayList<>();
		try {
			stateVO = basicMasterService.getStateById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "State information get successfully By Id");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "State information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getStateByOrgId")
	public ResponseEntity<ResponseDTO> getStateByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getStateByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = new ArrayList<>();
		try {
			stateVO = basicMasterService.getStateByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "State information get successfully By OrgId");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "State information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllStateByCountry")
	public ResponseEntity<ResponseDTO> getAllStateByCountry(@RequestParam Long orgId, @RequestParam String country) {
		String methodName = "getAllStateByCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = null;
		try {
			stateVO = basicMasterService.getAllStateByCountry(orgId, country);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "States found by Country");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "State not found for country: " + country;
			responseDTO = createServiceResponseError(responseObjectsMap, "States not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getStateByActive")
	public ResponseEntity<ResponseDTO> getStateByActive() {
		String methodName = "getStateByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = new ArrayList<>();
		try {
			stateVO = basicMasterService.getStateByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "State information get successfully By Active");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "State information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateState")
	public ResponseEntity<ResponseDTO> updateCreateState(@Valid @RequestBody StateDTO stateDTO) {
		String methodName = "updateCreateState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			StateVO stateVO = basicMasterService.updateCreateState(stateDTO);
			if (stateVO != null) {
				boolean isUpdate = stateDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "State updated successfully" : "State created successfully");
				responseObjectsMap.put("stateVO", stateVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = stateDTO.getId() != null;
				errorMsg = isUpdate ? "State not found for ID: " + stateDTO.getId() : "State creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "State update failed" : "State creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = stateDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "State update failed" : "State creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// City
	@GetMapping("/getCityById")
	public ResponseEntity<ResponseDTO> getCityById(@RequestParam(required = false) Long id) {
		String methodName = "getCityById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = new ArrayList<>();
		try {
			cityVO = basicMasterService.getCityById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "City information get successfully By Id");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "City information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCityByOrgId")
	public ResponseEntity<ResponseDTO> getCityByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getCityByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = new ArrayList<>();
		try {
			cityVO = basicMasterService.getCityByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "City information get successfully By OrgId");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "City information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@GetMapping("/getAllCityByState")
	public ResponseEntity<ResponseDTO> getAllCityByState(@RequestParam Long orgid, @RequestParam String state) {
		String methodName = "getAllCityByState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = null;
		try {
			cityVO = basicMasterService.getAllCityByState(orgid, state);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Cities found by State");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "City not found for State: " + state;
			responseDTO = createServiceResponseError(responseObjectsMap, "Cities not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateCity")
	public ResponseEntity<ResponseDTO> updateCreateCity(@Valid @RequestBody CityDTO cityDTO) {
		String methodName = "updateCreateCity()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CityVO cityVO = basicMasterService.updateCreateCity(cityDTO);
			if (cityVO != null) {
				boolean isUpdate = cityDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "City updated successfully" : "City created successfully");
				responseObjectsMap.put("cityVO", cityVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = cityDTO.getId() != null;
				errorMsg = isUpdate ? "City not found for ID: " + cityDTO.getId() : "City creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "City update failed" : "City creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = cityDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "City update failed" : "City creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getCityByActive")
	public ResponseEntity<ResponseDTO> getCityByActive() {
		String methodName = "getCityByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = new ArrayList<>();
		try {
			cityVO = basicMasterService.getCityByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "City information get successfully By Active");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "City information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// FinacialYear
	@GetMapping("/getFinancialYearById")
	public ResponseEntity<ResponseDTO> getFinancialYearById(@RequestParam(required = false) Long id) {
		String methodName = "getFinancialYearById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		try {
			financialYearVO = basicMasterService.getFinancialYearById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FinancialYear information get successfully By Id");
			responseObjectsMap.put("financialYearVO", financialYearVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"FinancialYear information receive failed By Id", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getFinancialYearByOrgId")
	public ResponseEntity<ResponseDTO> getFinancialYearByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getFinancialYearByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FinancialYearVO> financialYearVO = new ArrayList<>();
		try {
			financialYearVO = basicMasterService.getFinancialYearByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"FinancialYear information get successfully By OrgId");
			responseObjectsMap.put("financialYearVO", financialYearVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"FinancialYear information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateFinancialYear")
	public ResponseEntity<ResponseDTO> updateCreateFinancialYear(
			@Valid @RequestBody FinancialYearDTO financialYearDTO) {
		String methodName = "updateCreateFinancialYear()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			FinancialYearVO financialYearVO = basicMasterService.updateCreateFinancialYear(financialYearDTO);
			if (financialYearVO != null) {
				boolean isUpdate = financialYearDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? " FinancialYear updated successfully" : " FinancialYear created successfully");
				responseObjectsMap.put("financialYearVO", financialYearVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = financialYearDTO.getId() != null;
				errorMsg = isUpdate ? " FinancialYear not found for ID: " + financialYearDTO.getId()
						: " FinancialYear created failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? " FinancialYear update failed" : " FinancialYear created failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = financialYearDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? " FinancialYear update failed" : " FinancialYear created failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getFinYrAndFinYrIdByOrgId")
	public ResponseEntity<ResponseDTO> getFinYrAndFinYrIdByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getFinYrAndFinYrIdByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> finYear = new ArrayList<>();
		try {
			finYear = basicMasterService.getFinYrAndFinYrIdByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Finyear information get successfully By userId");
			responseObjectsMap.put("finYear", finYear);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Finyear information receive failed By userId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Branch
	@GetMapping("/getBranchById")
	public ResponseEntity<ResponseDTO> getBranchById(@RequestParam(required = false) Long id) {
		String methodName = "getBranchById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BranchVO> branchVO = new ArrayList<>();
		try {
			branchVO = basicMasterService.getBranchById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch information get successfully By Id");
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Branch information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getBranchByOrgId")
	public ResponseEntity<ResponseDTO> getBranchByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getBranchByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BranchVO> branchVO = new ArrayList<>();
		try {
			branchVO = basicMasterService.getBranchByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch information get successfully By OrgId");
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Branch information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateBranch")
	public ResponseEntity<ResponseDTO> updateCreateBranch(@Valid @RequestBody BranchDTO branchDTO) {
		String methodName = "updateCreateBranch()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			BranchVO branchVO = basicMasterService.updateCreateBranch(branchDTO);
			if (branchVO != null) {
				boolean isUpdate = branchDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Branch updated successfully" : "Branch created successfully");
				responseObjectsMap.put("branchVO", branchVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = branchDTO.getId() != null;
				errorMsg = isUpdate ? "Branch not found for ID: " + branchDTO.getId() : "Branch creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Branch update failed" : "Branch creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = branchDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Branch update failed" : "Branch creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getBranchByActive")
	public ResponseEntity<ResponseDTO> getBranchByActive() {
		String methodName = "getBranchByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<BranchVO> branchVO = new ArrayList<>();
		try {
			branchVO = basicMasterService.getBranchByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch information get successfully By Active");
			responseObjectsMap.put("branchVO", branchVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Branch information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Roles-----------------------------------------------------------------
	@GetMapping("/getRoleMasterById")
	public ResponseEntity<ResponseDTO> getRoleById(@RequestParam(required = false) Long id) {
		String methodName = "getRoleById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<RoleMasterVO> roleVO = new ArrayList<>();
		try {
			roleVO = basicMasterService.getRoleById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Role information get successfully By Id");
			responseObjectsMap.put("roleVO", roleVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Role information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getRoleMasterByOrgId")
	public ResponseEntity<ResponseDTO> getRoleByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getRoleByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<RoleMasterVO> roleVO = new ArrayList<>();
		try {
			roleVO = basicMasterService.getRoleByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Role information get successfully By OrgId");
			responseObjectsMap.put("roleVO", roleVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Role information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateRoleMaster")
	public ResponseEntity<ResponseDTO> updateCreateRole(@Valid @RequestBody RoleMasterDTO roleDTO) {
		String methodName = "updateCreateRole()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			RoleMasterVO roleVO = basicMasterService.updateCreateRole(roleDTO);
			if (roleVO != null) {
				boolean isUpdate = roleDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Role updated successfully" : "Role created successfully");
				responseObjectsMap.put("roleVO", roleVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = roleDTO.getId() != null;
				errorMsg = isUpdate ? "Role not found for ID: " + roleDTO.getId() : "Role creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "Role update failed" : "Role creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = roleDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Role update failed" : "Role creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getRoleMasterByActive")
	public ResponseEntity<ResponseDTO> getRoleByActive() {
		String methodName = "getRoleByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<RoleMasterVO> roleVO = new ArrayList<>();
		try {
			roleVO = basicMasterService.getRoleByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Role information get successfully By Active");
			responseObjectsMap.put("roleVO", roleVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Role information receive failed By Active",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Responsibilities-----------------------------------------------------------------
	@GetMapping("/getResponsibilitiesById")
	public ResponseEntity<ResponseDTO> getResponsibilitiesById(@RequestParam(required = false) Long id) {
		String methodName = "getResponsibilitiesById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ResponsibilitiesVO> responsibilitiesVO = new ArrayList<>();
		try {
			responsibilitiesVO = basicMasterService.getResponsibilitiesById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"Responsibilities information get successfully By Id");
			responseObjectsMap.put("responsibilitiesVO", responsibilitiesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Responsibilities information receive failed By Id", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getResponsibilitiesByOrgId")
	public ResponseEntity<ResponseDTO> getResponsibilitiesByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getResponsibilitiesByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ResponsibilitiesVO> responsibilitiesVO = new ArrayList<>();
		try {
			responsibilitiesVO = basicMasterService.getResponsibilitiesByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"Responsibilities information get successfully By OrgId");
			responseObjectsMap.put("responsibilitiesVO", responsibilitiesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Responsibilities information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateCreateResponsibilities")
	public ResponseEntity<ResponseDTO> updateCreateResponsibilities(
			@Valid @RequestBody ResponsibilitiesDTO responsibilitiesDTO) {
		String methodName = "updateCreateResponsibilities()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			ResponsibilitiesVO responsibilitiesVO = basicMasterService
					.updateCreateResponsibilities(responsibilitiesDTO);
			if (responsibilitiesVO != null) {
				boolean isUpdate = responsibilitiesDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "Responsibilities updated successfully" : "Responsibilities created successfully");
				responseObjectsMap.put("responsibilitiesVO", responsibilitiesVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = responsibilitiesDTO.getId() != null;
				errorMsg = isUpdate ? "Responsibilities not found for ID: " + responsibilitiesDTO.getId()
						: "Responsibilities creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap, isUpdate ? "Responsibilities update failed"
						: "Responsibilities creation" + ""
								+ "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = responsibilitiesDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "Responsibilities update failed" : "Responsibilities creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getResponsibilitiesByActive")
	public ResponseEntity<ResponseDTO> getResponsibilitiesByActive() {
		String methodName = "getResponsibilitiesByActive()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ResponsibilitiesVO> responsibilitiesVO = new ArrayList<>();
		try {
			responsibilitiesVO = basicMasterService.getResponsibilitiesByActive();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"Responsibilities information get successfully By Active");
			responseObjectsMap.put("responsibilitiesVO", responsibilitiesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Responsibilities information receive failed By Active", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	 FinScreen
	@GetMapping("/getFinScreenById")
	public ResponseEntity<ResponseDTO> getFinScreenById(@RequestParam(required = false) Long id) {
		String methodName = "getFinScreenById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FinScreenVO> finScreenVO = new ArrayList<>();
		try {
			finScreenVO = basicMasterService.getFinScreenById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FinScreen information get successfully By Id");
			responseObjectsMap.put("finScreenVO", finScreenVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "FinScreen information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getFinScreenByOrgId")
	public ResponseEntity<ResponseDTO> getFinScreenByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getFinScreenByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<FinScreenVO> finScreenVO = new ArrayList<>();
		try {
			finScreenVO = basicMasterService.getFinScreenByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FinScreen information get successfully By OrgId");
			responseObjectsMap.put("finScreenVO", finScreenVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"FinScreen information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateFinScreen")
	public ResponseEntity<ResponseDTO> updateFinScreen(@Valid @RequestBody FinScreenDTO finScreenDTO) {
		String methodName = "updateCreateFinScreen()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			FinScreenVO finScreenVO = basicMasterService.updateCreateFinScreen(finScreenDTO);
			if (finScreenVO != null) {
				boolean isUpdate = finScreenDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "FinScreen updated successfully" : "FinScreen created successfully");
				responseObjectsMap.put("finScreenVO", finScreenVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = finScreenDTO.getId() != null;
				errorMsg = isUpdate ? "FinScreen not found for ID: " + finScreenDTO.getId()
						: "FinScreen creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "FinScreen update failed" : "FinScreen creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = finScreenDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "FinScreen update failed" : "FinScreen creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllScreenCode")
	public ResponseEntity<ResponseDTO> getAllScreenCode() {
		String methodName = "getAllScreenCode()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		Set<Object[]> finScreenVO = new HashSet<>();
		try {
			finScreenVO = basicMasterService.getAllScreenCode();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			List<Map<String, Object>> screen = getScreen(finScreenVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "All ScreenCode information get successfully");
			responseObjectsMap.put("finScreenVO", screen);
//			responseObjectsMap.put("finScreenVO", finScreenVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "All screenCode information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	private List<Map<String, Object>> getScreen(Set<Object[]> finScreenVO) {
		List<Map<String, Object>> finScreen = new ArrayList<>();
		for (Object[] fs : finScreenVO) {
			Map<String, Object> part = new HashMap<>();
			part.put("Screen Name", fs[0] != null ? fs[0].toString() : "");
			part.put("Screen Code", fs[1] != null ? fs[1].toString() : "");
			finScreen.add(part);
		}
		return finScreen;
	}

//	 DocCode
	@GetMapping("/getDocumentTypeById")
	public ResponseEntity<ResponseDTO> getDocCodeById(@RequestParam(required = false) Long id) {
		String methodName = "getDocCodeById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DocumentTypeVO> docCodeVO = new ArrayList<>();
		try {
			docCodeVO = basicMasterService.getDocCodeById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "DocCode information get successfully By Id");
			responseObjectsMap.put("docCodeVO", docCodeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "DocCode information receive failed By Id",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getDocumentTypeByOrgId")
	public ResponseEntity<ResponseDTO> getDocCodeByOrgId(@RequestParam(required = false) Long orgId) {
		String methodName = "getDocCodeByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DocumentTypeVO> docCodeVO = new ArrayList<>();
		try {
			docCodeVO = basicMasterService.getDocCodeByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "DocCode information get successfully By OrgId");
			responseObjectsMap.put("docCodeVO", docCodeVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "DocCode information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PutMapping("/updateDocumentType")
	public ResponseEntity<ResponseDTO> updateDocumentType(@Valid @RequestBody DocumentTypeDTO docCodeDTO) {
		String methodName = "updateDocumentType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			DocumentTypeVO docCodeVO = basicMasterService.updateCreateDocCode(docCodeDTO);
			if (docCodeVO != null) {
				boolean isUpdate = docCodeDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "DocCode updated successfully" : "DocCode created successfully");
				responseObjectsMap.put("docCodeVO", docCodeVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = docCodeDTO.getId() != null;
				errorMsg = isUpdate ? "DocCode not found for ID: " + docCodeDTO.getId() : "DocCode creation failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "DocCode update failed" : "DocCode creation failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = docCodeDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "DocCode update failed" : "DocCode creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	DocumentTypeMapping
	@GetMapping("/getDocumentTypesMappingById")
	public ResponseEntity<ResponseDTO> getDocumentTypesMappingById(@RequestParam(required = false) Long id) {
		String methodName = "getDocumentTypesMappingById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DocumentTypesMappingVO> documentTypesMappingVO = new ArrayList<>();
		try {
			documentTypesMappingVO = basicMasterService.getDocumentTypesMappingById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"DocumentTypesMapping information get successfully By Id");
			responseObjectsMap.put("documentTypesMappingVO", documentTypesMappingVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DocumentTypesMapping information receive failed By Id", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getDocumentTypesMappingByOrgId")
	public ResponseEntity<ResponseDTO> getDocumentTypesMappingByOrgId(@RequestParam(required = false) Long orgid) {
		String methodName = "getDocumentTypesMappingByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<DocumentTypesMappingVO> documentTypesMappingVO = new ArrayList<>();
		try {
			documentTypesMappingVO = basicMasterService.getDocumentTypesMappingByOrgId(orgid);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"DocumentTypesMapping information get successfully By OrgId");
			responseObjectsMap.put("documentTypesMappingVO", documentTypesMappingVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"DocumentTypesMapping information receive failed By OrgId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCreateDocumentTypesMapping")
	public ResponseEntity<ResponseDTO> updateCreateDocumentTypesMapping(
			@Valid @RequestBody DocumentTypesMappingDTO documentTypesMappingDTO) {
		String methodName = "updateCreateDocumentTypesMapping()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			DocumentTypesMappingVO documentTypesMappingVO = basicMasterService
					.updateCreateDocumentTypesMapping(documentTypesMappingDTO);
			if (documentTypesMappingVO != null) {
				boolean isUpdate = documentTypesMappingDTO.getId() != null;
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
						isUpdate ? "DocumentTypesMapping updated successfully"
								: "DocumentTypesMapping created successfully");
				responseObjectsMap.put("documentTypesMappingVO", documentTypesMappingVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				boolean isUpdate = documentTypesMappingDTO.getId() != null;
				errorMsg = isUpdate ? "DocumentTypesMapping not found for ID: " + documentTypesMappingDTO.getId()
						: "DocumentTypesMapping created failed";
				responseDTO = createServiceResponseError(responseObjectsMap,
						isUpdate ? "DocumentTypesMapping update failed" : "DocumentTypesMapping created failed",
						errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			boolean isUpdate = documentTypesMappingDTO.getId() != null;
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap,
					isUpdate ? "DocumentTypesMapping update failed" : "DocumentTypesMapping created failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllDocumentTypesMappingDetailsByDocumentType")
	public ResponseEntity<ResponseDTO> getAllDocumentTypesMappingDetailsByDocumentType(@RequestParam String branch,
			@RequestParam String branchCode, @RequestParam String finYr, @RequestParam Long orgId,
			@RequestParam String finYrId) {

		String methodName = "getAllDocumentTypesMappingDetailsByDocumentType()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<Map<String, Object>> mapp = new ArrayList<>();

		try {
			mapp = basicMasterService.getAllDocumentTypesMappingDetailsByDocumentType(branch, branchCode, finYr, orgId,
					finYrId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE,
					"All DocumentTypesMappingDetails information retrieved successfully");
			responseObjectsMap.put("mappingDetailsVO", mapp);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Failed to retrieve DocumentTypesMappingDetails information", errorMsg);
		}

		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}