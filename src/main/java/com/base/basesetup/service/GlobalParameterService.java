package com.base.basesetup.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.base.basesetup.entity.GlobalParameterVO;

@Service
public interface GlobalParameterService {
	// Global Parameter
	Set<Object[]> getWarehouseNameByOrgIdAndBranchAndClient(Long orgid, String branch, String client);

	Optional<GlobalParameterVO> getGlobalParamByOrgIdAndUserName(Long orgid, String userId);

	GlobalParameterVO updateGlobaParameter(GlobalParameterVO globalParameterVO);

	// to getAcces Global Param Dteails

	Set<Object[]> getGlobalParametersBranchAndBranchCodeByOrgIdAndUserName(Long orgid, String userName);

	Set<Object[]> getAllAccessCustomerForLogin(Long orgid, String userName, String branchcode);

	Set<Object[]> getAllAccessClientForLogin(Long orgid, String userName, String branchcode, String customer);

}