package com.base.basesetup.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.BranchDTO;
import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.EmployeeDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.BranchVO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.EmployeeVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.service.BasicMasterService;

@CrossOrigin
@RestController
@RequestMapping("/api/basicMaster")
public class BasicMasterController extends BaseController {

	@Autowired
	BasicMasterService basicMasterService;

	public static final Logger LOGGER = LoggerFactory.getLogger(BasicMasterController.class);

//	Currency -----------------------------------------------------------------------------------

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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency updated successfully");
				responseObjectsMap.put("currencyVO", currencyVO);

				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Currency not found for ID: " + currencyDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Currency update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Currency update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	Company -----------------------------------------------------------------------------------
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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company updated successfully");
				responseObjectsMap.put("companyVO", companyVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Company not found for ID: " + companyDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Company update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Company update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	Employee -----------------------------------------------------------------------------------
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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Employee updated successfully");
				responseObjectsMap.put("employeeVO", employeeVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Employee not found for ID: " + employeeDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Employee update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Employee update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	Country -----------------------------------------------------------------------------------
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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country updated successfully");
				responseObjectsMap.put("countryVO", countryVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Country not found for ID: " + countryDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Country update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Country update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	State -----------------------------------------------------------------------------------
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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "State updated successfully");
				responseObjectsMap.put("stateVO", stateVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "State not found for ID: " + stateDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "State update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "State update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	City -----------------------------------------------------------------------------------
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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "City updated successfully");
				responseObjectsMap.put("cityVO", cityVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "City not found for ID: " + cityDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "City update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "City update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//Finacial Year--------------------------------------------------------------------------
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
			responseDTO = createServiceResponseError(responseObjectsMap, "FinancialYear information receive failed By Id",
					errorMsg);
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
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FinancialYear information get successfully By OrgId");
			responseObjectsMap.put("financialYearVO", financialYearVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "FinancialYear information receive failed By OrgId",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);

	}

	@PostMapping("/financial")
	public ResponseEntity<ResponseDTO> createFinancialYear(@RequestBody FinancialYearVO financialYearVO) {
		String methodName = "createFinancialYear()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			FinancialYearVO createdFinancialYearVO = basicMasterService.createFinancial(financialYearVO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "financialYear created successfully");
			responseObjectsMap.put("financialYearVO", createdFinancialYearVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "financial Year creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/financial")
	public ResponseEntity<ResponseDTO> updateFinancialYearVO(@RequestBody FinancialYearVO financialYearVO) {
		String methodName = "updateFinancialYear()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			FinancialYearVO updatedFinancialYearVO = basicMasterService.updateFinancial(financialYearVO).orElse(null);
			if (updatedFinancialYearVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "FinancialYear updated successfully");
				responseObjectsMap.put("financialYearVO", updatedFinancialYearVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "FinancialYear not found for ID: " + financialYearVO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Employee update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "FinancialYear update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

//	Branch -----------------------------------------------------------------------------------
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
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Branch updated successfully");
				responseObjectsMap.put("branchVO", branchVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Branch not found for ID: " + branchDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, "Branch update failed", errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Branch update failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
}