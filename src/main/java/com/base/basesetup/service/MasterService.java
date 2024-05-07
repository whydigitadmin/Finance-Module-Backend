package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.AccountDTO;
import com.base.basesetup.dto.GroupLedgerDTO;
import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.dto.TcsMasterDTO;
import com.base.basesetup.dto.TdsMasterDTO;
import com.base.basesetup.entity.AccountVO;
import com.base.basesetup.entity.GroupLedgerVO;
import com.base.basesetup.entity.SetTaxRateVO;

import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.entity.TcsMasterVO;
import com.base.basesetup.entity.TdsMasterVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface MasterService {

	List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId);


	TaxMasterVO updateCreateTaxMaster(TaxMasterDTO taxMasterDTO) throws ApplicationException;


	List<SetTaxRateVO> getAllSetTaxRateById(Long id);


	List<SetTaxRateVO> getAllSetTaxRate();



	List<TaxMasterVO> getAllTaxMasterByOrgId(Long orgId);


	List<TaxMasterVO> getAllTaxMasterById(Long id);


	List<TaxMasterVO> getAllTaxMaster();


	List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId);


	List<TcsMasterVO> getAllTcsMasterById(Long id);


	List<TcsMasterVO> getAllTcsMaster();


	TcsMasterVO updateCreateTcsMaster(@Valid TcsMasterDTO tcsMasterDTO) throws ApplicationException;


	List<TdsMasterVO> getAllTdsMasterByOrgId(Long orgId);


	List<TdsMasterVO> getAllTdsMasterById(Long id);


	List<TdsMasterVO> getAllTdsMaster();


	TdsMasterVO updateCreateTdsMaster(@Valid TdsMasterDTO tdsMasterDTO) throws ApplicationException;


	List<AccountVO> getAllAccountByOrgId(Long orgId);


	List<AccountVO> getAllAccountByaccountId(Long accountId);


	List<GroupLedgerVO> getAllGroupLedgerById(Long id);


	List<GroupLedgerVO> getAllGroupLedgerByOrgId(Long orgId);


	SetTaxRateVO updateCreateSetTaxRate(@Valid SetTaxRateDTO setTaxRateDTO) throws ApplicationException;


	GroupLedgerVO updateCreateGroupLedger(@Valid GroupLedgerDTO groupLedgerDTO) throws ApplicationException;


	AccountVO updateCreateAccount(@Valid AccountDTO accountDTO) throws ApplicationException;




	 
	
	

}
