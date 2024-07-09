package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.GlobalParameterDTO;
import com.base.basesetup.entity.GlobalParameterVO;
import com.base.basesetup.repo.BranchAccessRepo;
import com.base.basesetup.repo.FinancialRepo;
import com.base.basesetup.repo.GlobalParameterRepo;
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
	UserRoleRepo userRoleRepo;
	
	@Autowired
	BranchAccessRepo branchAccessRepo;
	
	@Autowired
	FinancialRepo financialRepo;
	
	@Override
	public GlobalParameterVO createUpdateGlobalParameter(GlobalParameterDTO globalParameterDTO) {
		
		GlobalParameterVO globalParameterVO= new GlobalParameterVO();
		
		
		if(!globalParameterRepo.existsById(globalParameterDTO.getUserId()))
		{			
			globalParameterVO.setUserId(globalParameterDTO.getUserId());
			globalParameterVO.setCompany(globalParameterDTO.getCompany());
			globalParameterVO.setFinYear(globalParameterDTO.getFinYear());
			globalParameterVO.setBranch(globalParameterDTO.getBranch());
			globalParameterVO.setBranchCode(globalParameterDTO.getBranchCode());
			globalParameterRepo.save(globalParameterVO);
			
		}
		else
		{
			globalParameterVO=globalParameterRepo.findById(globalParameterDTO.getUserId()).get();
			globalParameterVO.setCompany(globalParameterDTO.getCompany());
			globalParameterVO.setFinYear(globalParameterDTO.getFinYear());
			globalParameterVO.setBranch(globalParameterDTO.getBranch());
			globalParameterVO.setBranchCode(globalParameterDTO.getBranchCode());
			globalParameterRepo.save(globalParameterVO);
		}
		return globalParameterVO;
	}

	@Override
	public GlobalParameterVO getGlobalParameterByUserId(Long userId) {
		
		return globalParameterRepo.findById(userId).get();
	}

	@Override
	public List<Map<String, Object>> getUserAccessBranchByUserId(Long userId) {
		Set<Object[]> getBranchAndBranchCode=branchAccessRepo.getUserAccessBranch(userId);
		return getBranch(getBranchAndBranchCode);
	}

	private List<Map<String, Object>> getBranch(Set<Object[]> getBranchAndBranchCode) {
		
		List<Map<String, Object>> branchList = new ArrayList<>();
	    
	    for (Object[] branchData : getBranchAndBranchCode) {
	        Map<String, Object> branchMap = new HashMap<>();
	        branchMap.put("branchName", branchData[0] != null ? branchData[0].toString() : "");
	        branchMap.put("branchCode", branchData[1]!= null ? branchData[1].toString() : "");
	        branchList.add(branchMap);
	    }
	    return branchList;
	}

	@Override
	public List<Map<String, Object>> getFinYearforGlobalParam(Long orgId) {
		Set<Object[]>getFinyer=financialRepo.getFinyer(orgId);
		return getFinyearDetails(getFinyer);
	}

	private List<Map<String, Object>> getFinyearDetails(Set<Object[]> getFinyer) {
		List<Map<String, Object>> finyrList = new ArrayList<>();
	    
	    for (Object[] finyeDate : getFinyer) {
	        Map<String, Object> branchMap = new HashMap<>();
	        branchMap.put("finYear", finyeDate[0] != null ? finyeDate[0].toString() : "");
	        finyrList.add(branchMap);
	    }
	    return finyrList;
	}

	

}
