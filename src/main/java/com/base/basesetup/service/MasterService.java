package com.base.basesetup.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.entity.SetTaxRateVO;

import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface MasterService {

	List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId);


	TaxMasterVO updateCreateTaxMaster(TaxMasterDTO taxMasterDTO) throws ApplicationException;


}
