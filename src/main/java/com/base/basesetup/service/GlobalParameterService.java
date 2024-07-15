package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.GlobalParameterDTO;
import com.base.basesetup.entity.GlobalParameterVO;

@Service
public interface GlobalParameterService {

	GlobalParameterVO createUpdateGlobalParameter(GlobalParameterDTO globalParameterDTO);

	GlobalParameterVO getGlobalParameterByUserId(Long userId);

	List<Map<String,Object>> getUserAccessBranchByUserId(Long userId);

	List<Map<String,Object>> getUserCompanyByUserId(Long userId);
	
	List<Map<String, Object>> getFinYearforGlobalParam(Long orgId);

}