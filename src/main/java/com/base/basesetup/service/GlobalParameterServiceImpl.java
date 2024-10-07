package com.base.basesetup.service;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.entity.GlobalParameterVO;
import com.base.basesetup.repo.BranchAccessRepo;
import com.base.basesetup.repo.ClientRepo;
import com.base.basesetup.repo.CustomerRepo;
import com.base.basesetup.repo.FinancialYearRepo;
import com.base.basesetup.repo.GlobalParameterRepo;
import com.base.basesetup.repo.UserBranchAccessRepo;
import com.base.basesetup.repo.UserRepo;
import com.base.basesetup.repo.UserRoleRepo;

@Service
public class GlobalParameterServiceImpl implements GlobalParameterService {

	public static final Logger LOGGER = LoggerFactory.getLogger(GlobalParameterServiceImpl.class);

	@Autowired
	GlobalParameterRepo globalParameterRepo;

	@Autowired
	UserRepo userRepo;

	@Autowired
	UserBranchAccessRepo userBranchAccessRepo;

	@Autowired
	UserRoleRepo userRoleRepo;

	@Autowired
	BranchAccessRepo branchAccessRepo;

	@Autowired
	FinancialYearRepo financialRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	ClientRepo clientRepo;

	// Global Parametre

	@Override
	public Set<Object[]> getWarehouseNameByOrgIdAndBranchAndClient(Long orgid, String branch, String client) {
		return globalParameterRepo.findWarehouseNameByOrgIdAndBranchAndClient(orgid, branch, client);
	}

	
	@Override
	public Optional<GlobalParameterVO> getGlobalParamByOrgIdAndUserName(Long orgid, String username) {

		return globalParameterRepo.findGlobalParamByOrgIdAndUserName(orgid, username);
	}

	// Change Global Parameter or update Parameters
	@Override

	public GlobalParameterVO updateGlobaParameter(GlobalParameterVO globalParameterVO) {

		GlobalParameterVO existingRecord = globalParameterRepo.findGlobalParam(globalParameterVO.getOrgId(),
				globalParameterVO.getUserid());

		if (existingRecord != null) {
			// If the record exists, it's a PUT operation
			existingRecord.setBranch(globalParameterVO.getBranch());
			existingRecord.setBranchcode(globalParameterVO.getBranchcode());
			existingRecord.setCustomer(globalParameterVO.getCustomer());
			existingRecord.setClient(globalParameterVO.getClient());
			existingRecord.setOrgId(globalParameterVO.getOrgId());

			return globalParameterRepo.save(existingRecord);
		} else {
			// If the record doesn't exist, it's a POST operation
			return globalParameterRepo.save(globalParameterVO);
		}

	}

	// get access Branch
	@Override
	public Set<Object[]> getGlobalParametersBranchAndBranchCodeByOrgIdAndUserName(Long orgid, String userName) {

		return userBranchAccessRepo.findGlobalParametersBranchByUserName(orgid, userName);
	}

	@Override
	public Set<Object[]> getAllAccessCustomerForLogin(Long orgid, String userName, String branchcode) {

		return customerRepo.findAllAccessCustomerByUserName(orgid, userName, branchcode);
	}

	@Override
	public Set<Object[]> getAllAccessClientForLogin(Long orgid, String userName, String branchcode, String customer) {
		// TODO Auto-generated method stub
		return clientRepo.findAllAccessClientByUserName(orgid, userName, branchcode, customer);
	}
}