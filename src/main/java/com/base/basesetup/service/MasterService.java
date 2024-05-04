package com.base.basesetup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.basesetup.entity.SetTaxRateVO;
@Service
public interface MasterService {

	List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId);

}
