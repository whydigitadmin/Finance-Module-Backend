package com.base.basesetup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.TcsMasterVO;
@Service
public interface MasterService {

	List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId);

	List<SetTaxRateVO> getAllSetTaxRateById(Long id);

	List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId);

}
