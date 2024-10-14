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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.CityDTO;
import com.base.basesetup.dto.CompanyDTO;
import com.base.basesetup.dto.CountryDTO;
import com.base.basesetup.dto.CurrencyDTO;
import com.base.basesetup.dto.DocumentTypeDTO;
import com.base.basesetup.dto.DocumentTypesMappingDTO;
import com.base.basesetup.dto.FinScreenDTO;
import com.base.basesetup.dto.FinancialYearDTO;
import com.base.basesetup.dto.RegionDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.dto.ScreenNamesDTO;
import com.base.basesetup.dto.StateDTO;
import com.base.basesetup.entity.CityVO;
import com.base.basesetup.entity.CompanyVO;
import com.base.basesetup.entity.CountryVO;
import com.base.basesetup.entity.CurrencyVO;
import com.base.basesetup.entity.DocumentTypeVO;
import com.base.basesetup.entity.DocumentTypesMappingVO;
import com.base.basesetup.entity.FinScreenVO;
import com.base.basesetup.entity.FinancialYearVO;
import com.base.basesetup.entity.RegionVO;
import com.base.basesetup.entity.ScreenNamesVO;
import com.base.basesetup.entity.StateVO;
import com.base.basesetup.service.CommonMasterService;

@CrossOrigin
@RestController
@RequestMapping("/api/commonmaster")
public class CommonMasterController extends BaseController {

	@Autowired
	CommonMasterService commonMasterService;

	public static final Logger LOGGER = LoggerFactory.getLogger(CommonMasterController.class);

	@GetMapping("/country")
	public ResponseEntity<ResponseDTO> getAllcountries(@RequestParam Long orgid) {
		String methodName = "getAllCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CountryVO> countryVO = new ArrayList<>();
		try {
			countryVO = commonMasterService.getAllCountry(orgid);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "countries information get successfully");
			responseObjectsMap.put("countryVO", countryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "countries information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/country/{countryid}")
	public ResponseEntity<ResponseDTO> getCountryById(@PathVariable Long countryid) {
		String methodName = "getCountryById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		CountryVO CountryVO = null;
		try {
			CountryVO = commonMasterService.getCountryById(countryid).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Country found by ID");
			responseObjectsMap.put("Country", CountryVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Country not found for ID: " + countryid;
			responseDTO = createServiceResponseError(responseObjectsMap, "Country not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/createUpdateCountry")
	public ResponseEntity<ResponseDTO> createUpdateCountry(@RequestBody CountryDTO countryDTO) {
		String methodName = "createCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		Map<String, Object> responseObjectsMap = new HashMap<String, Object>();
		String errorMsg = null;
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> createdCountryVO = commonMasterService.createUpdateCountry(countryDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, createdCountryVO.get("message"));
			responseObjectsMap.put("countryVO", createdCountryVO.get("countryVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// State

	@GetMapping("/state")
	public ResponseEntity<ResponseDTO> getAllStates(@RequestParam Long orgid) {
		String methodName = "getAllStates()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = new ArrayList<>();
		try {
			stateVO = commonMasterService.getAllgetAllStates(orgid);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "State information get successfully");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "States information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// getStateByStateId

	@GetMapping("/state/{stateid}")
	public ResponseEntity<ResponseDTO> getStateById(@PathVariable Long stateid) {
		String methodName = "getStateById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		StateVO stateVO = null;
		try {
			stateVO = commonMasterService.getStateById(stateid).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "States found by State ID");
			responseObjectsMap.put("stateVO", stateVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "State not found for State ID: " + stateid;
			responseDTO = createServiceResponseError(responseObjectsMap, "States not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/state/country")
	public ResponseEntity<ResponseDTO> getStateByCountry(@RequestParam Long orgid, @RequestParam String country) {
		String methodName = "getStateByCountry()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<StateVO> stateVO = null;
		try {
			stateVO = commonMasterService.getStatesByCountry(orgid, country);
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

	@PostMapping("/state")
	public ResponseEntity<ResponseDTO> createUpdateState(@RequestBody StateDTO stateDTO) {
		String methodName = "createUpdateState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> stateVO = commonMasterService.createUpdateState(stateDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, stateVO.get("message"));
			responseObjectsMap.put("stateVO", stateVO.get("stateVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}
	// city

	@GetMapping("/city")
	public ResponseEntity<ResponseDTO> getAllCities(@RequestParam Long orgid) {
		String methodName = "getAllCities()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = new ArrayList<>();
		try {
			cityVO = commonMasterService.getAllgetAllCities(orgid);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "city information get successfully");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "city information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/city/state")
	public ResponseEntity<ResponseDTO> getAllCitiesByState(@RequestParam Long orgid, @RequestParam String state) {
		String methodName = "getAllCitiesByState()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CityVO> cityVO = new ArrayList<>();
		try {
			cityVO = commonMasterService.getAllCitiesByState(orgid, state);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "city information get successfully");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "city information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/city/{cityid}")
	public ResponseEntity<ResponseDTO> getCityById(@PathVariable Long cityid) {
		String methodName = "getCityById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		CityVO cityVO = null;
		try {
			cityVO = commonMasterService.getCityById(cityid).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "City found by City ID");
			responseObjectsMap.put("cityVO", cityVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "City not found for City ID: " + cityid;
			responseDTO = createServiceResponseError(responseObjectsMap, "City not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/createUpdateCity")
	public ResponseEntity<ResponseDTO> createUpdateCity(@RequestBody CityDTO cityDTO) {
		String methodName = "createCity()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> createdCityVO = commonMasterService.createUpdateCity(cityDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, createdCityVO.get("message"));
			responseObjectsMap.put("cityVO", createdCityVO.get("cityVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Region

	@GetMapping("/getAllRegion")
	public ResponseEntity<ResponseDTO> getAllRegion() {
		String methodName = "getAllRegios()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<RegionVO> regionVO = new ArrayList<>();
		try {
			regionVO = commonMasterService.getAllRegios();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Region information get successfully");
			responseObjectsMap.put("regionVO", regionVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Region information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllRegionsByOrgId")
	public ResponseEntity<ResponseDTO> getAllRegionsByOrgId(@RequestParam Long orgId) {
		String methodName = "getAllRegionsByOrgId()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<RegionVO> regionVO = new ArrayList<>();
		try {
			regionVO = commonMasterService.getAllRegionsByOrgId(orgId);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Region information get successfully");
			responseObjectsMap.put("regionVO", regionVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Region information receive failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/region/{regionid}")
	public ResponseEntity<ResponseDTO> getRegionById(@PathVariable Long regionid) {
		String methodName = "getRegionById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		RegionVO regionVO = null;
		try {
			regionVO = commonMasterService.getRegionById(regionid).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Region found by Region ID");
			responseObjectsMap.put("regionVO", regionVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Region not found for Region ID: " + regionid;
			responseDTO = createServiceResponseError(responseObjectsMap, "Region not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/createUpdateRegion")
	public ResponseEntity<ResponseDTO> createUpdateRegion(@RequestBody RegionDTO regionDTO) {
		String methodName = "createUpdateRegion()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> regionvo = commonMasterService.createUpdateRegion(regionDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, regionvo.get("message"));
			responseObjectsMap.put("regionvo", regionvo.get("regionVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Currency

	@GetMapping("/currency")
	public ResponseEntity<ResponseDTO> getAllCurrency(@RequestParam Long orgid) {
		String methodName = "getAllCurrency()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CurrencyVO> currencyVO = new ArrayList<>();
		try {
			currencyVO = commonMasterService.getAllCurrency(orgid);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency" + " information get successfully");
			responseObjectsMap.put("currencyVO", currencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Currency information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// getCUrrencyById

	@GetMapping("/currency/{currencyid}")
	public ResponseEntity<ResponseDTO> getCurrencyById(@PathVariable Long currencyid) {
		String methodName = "getCurrencyById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		CurrencyVO currencyVO = null;
		try {
			currencyVO = commonMasterService.getCurrencyById(currencyid).orElse(null);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Currency found by Currency ID");
			responseObjectsMap.put("currencyVO", currencyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "Currency not found for Currency ID: " + currencyid;
			responseDTO = createServiceResponseError(responseObjectsMap, "Currency not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/createUpdateCurrency")
	public ResponseEntity<ResponseDTO> createUpdateCurrency(@RequestBody CurrencyDTO currencyDTO) {
		String methodName = "createUpdateCurrency()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> currency = commonMasterService.createUpdateCurrency(currencyDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, currency.get("message"));
			responseObjectsMap.put("currency", currency.get("currencyVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	// Company

	@GetMapping("/company")
	public ResponseEntity<ResponseDTO> getAllCompany() {
		String methodName = "getAllCompany()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CompanyVO> companyVO = new ArrayList<>();
		try {
			companyVO = commonMasterService.getAllCompany();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company information get successfully");
			responseObjectsMap.put("companyVO", companyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "Company information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/company/{companyid}")
	public ResponseEntity<ResponseDTO> getcompanyById(@PathVariable Long companyid) {
		String methodName = "getCompanyById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<CompanyVO> companyVO = new ArrayList<CompanyVO>();
		try {
			companyVO = commonMasterService.getCompanyById(companyid);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}

		if (StringUtils.isEmpty(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company found by Company ID");
			responseObjectsMap.put("companyVO", companyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			errorMsg = "company not found for companyID: " + companyid;
			responseDTO = createServiceResponseError(responseObjectsMap, "company not found", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PostMapping("/company")
	public ResponseEntity<ResponseDTO> createCompany(@RequestBody CompanyDTO companyDTO) {
		String methodName = "createCompany()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CompanyVO createdCompanyVO = commonMasterService.createCompany(companyDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Company created successfully");
			responseObjectsMap.put("createdCompanyVO", createdCompanyVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, "Company creation failed", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@PutMapping("/updateCompany")
	public ResponseEntity<ResponseDTO> updateCompany(@RequestBody CompanyDTO companyDTO) {
		String methodName = "updateCompany()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			CompanyVO updatedCompanyVO = commonMasterService.updateCompany(companyDTO);
			if (updatedCompanyVO != null) {
				responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Customers updated successfully");
				responseObjectsMap.put("CompanyVO", updatedCompanyVO);
				responseDTO = createServiceResponse(responseObjectsMap);
			} else {
				errorMsg = "Organization not found for ID: " + companyDTO.getId();
				responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
			}
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
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
			financialYearVO = commonMasterService.getFinancialYearById(id);
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
			financialYearVO = commonMasterService.getFinancialYearByOrgId(orgId);
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
			FinancialYearVO financialYearVO = commonMasterService.updateCreateFinancialYear(financialYearDTO);
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
			finYear = commonMasterService.getFinYrAndFinYrIdByOrgId(orgId);
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
			finScreenVO = commonMasterService.getFinScreenById(id);
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
			finScreenVO = commonMasterService.getFinScreenByOrgId(orgId);
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
			FinScreenVO finScreenVO = commonMasterService.updateCreateFinScreen(finScreenDTO);
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
		List<Map<String, Object>> finScreen = new ArrayList<>();
		try {
			finScreen = commonMasterService.getAllScreenCode();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "Fin Screen information get successfully By userId");
			responseObjectsMap.put("finScreen", finScreen);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap,
					"Fin Screen information receive failed By userId", errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
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
			docCodeVO = commonMasterService.getDocCodeById(id);
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
			docCodeVO = commonMasterService.getDocCodeByOrgId(orgId);
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
			DocumentTypeVO docCodeVO = commonMasterService.updateCreateDocCode(docCodeDTO);
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
			documentTypesMappingVO = commonMasterService.getDocumentTypesMappingById(id);
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
			documentTypesMappingVO = commonMasterService.getDocumentTypesMappingByOrgId(orgid);
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
			DocumentTypesMappingVO documentTypesMappingVO = commonMasterService
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
			mapp = commonMasterService.getAllDocumentTypesMappingDetailsByDocumentType(branch, branchCode, finYr, orgId,
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
	
	
	// Screen Names
	@PutMapping("/createUpdateScreenNames")
	public ResponseEntity<ResponseDTO> createUpdateScreenNames(@RequestBody ScreenNamesDTO screenNamesDTO) {
		String methodName = "createUpdateScreenNames()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		try {
			Map<String, Object> screenNamesVO = commonMasterService.createUpdateScreenNames(screenNamesDTO);
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, screenNamesVO.get("message"));
			responseObjectsMap.put("screenNamesVO", screenNamesVO.get("screenNamesVO"));
			responseDTO = createServiceResponse(responseObjectsMap);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
			responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/getAllScreenNames")
	public ResponseEntity<ResponseDTO> getAllScreenNames() {
		String methodName = "getAllScreenNames()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		List<ScreenNamesVO> screenNamesVO = new ArrayList<>();
		try {
			screenNamesVO = commonMasterService.getAllScreenNames();
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "screenNames information get successfully");
			responseObjectsMap.put("screenNamesVO", screenNamesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "screenNames information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

	@GetMapping("/screenNamesById")
	public ResponseEntity<ResponseDTO> getScreenNamesById(@RequestParam Long id) {
		String methodName = "getScreenNamesById()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;
		ScreenNamesVO screenNamesVO = new ScreenNamesVO();
		try {
			screenNamesVO = commonMasterService.getScreenNamesById(id);
		} catch (Exception e) {
			errorMsg = e.getMessage();
			LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
		}
		if (StringUtils.isBlank(errorMsg)) {
			responseObjectsMap.put(CommonConstant.STRING_MESSAGE, "screenNames information get successfully");
			responseObjectsMap.put("screenNamesVO", screenNamesVO);
			responseDTO = createServiceResponse(responseObjectsMap);
		} else {
			responseDTO = createServiceResponseError(responseObjectsMap, "screenNames information receive failed",
					errorMsg);
		}
		LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
		return ResponseEntity.ok().body(responseDTO);
	}

}