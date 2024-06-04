package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.AccountDTO;
import com.base.basesetup.dto.ChargeTypeRequestDTO;
import com.base.basesetup.dto.ChequeBoxDTO;
import com.base.basesetup.dto.CostCenterDTO;
import com.base.basesetup.dto.ExRatesDTO;
import com.base.basesetup.dto.GroupLedgerDTO;
import com.base.basesetup.dto.HsnSacCodeDTO;
import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.SubLedgerAccountDTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.dto.TcsMasterDTO;
import com.base.basesetup.dto.TdsMasterDTO;
import com.base.basesetup.entity.AccountVO;
import com.base.basesetup.entity.ChargeTypeRequestVO;
import com.base.basesetup.entity.ChequeBookVO;
import com.base.basesetup.entity.CostCenterVO;
import com.base.basesetup.entity.ExRatesVO;
import com.base.basesetup.entity.GroupLedgerVO;
import com.base.basesetup.entity.HsnSacCodeVO;
import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.SubLedgerAccountVO;
import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.entity.TcsMasterVO;
import com.base.basesetup.entity.TdsMasterVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface MasterService {

//	SetTaxRateVO
	List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId);

	List<SetTaxRateVO> getAllSetTaxRateById(Long id);

	SetTaxRateVO updateCreateSetTaxRate(@Valid SetTaxRateDTO setTaxRateDTO) throws Exception;

	List<SetTaxRateVO> getSetTaxRateByActive();

//	TaxMasterVO
	TaxMasterVO updateCreateTaxMaster(TaxMasterDTO taxMasterDTO) throws ApplicationException;

	List<TaxMasterVO> getAllTaxMasterByOrgId(Long orgId);

	List<TaxMasterVO> getAllTaxMasterById(Long id);

	List<TaxMasterVO> getTaxMasterByActive();

//	TcsMasterVO
	List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId);

	List<TcsMasterVO> getAllTcsMasterById(Long id);

	TcsMasterVO updateCreateTcsMaster(@Valid TcsMasterDTO tcsMasterDTO) throws ApplicationException;

	List<TcsMasterVO> getTcsMasterByActive();

//	TdsMasterVO
	List<TdsMasterVO> getAllTdsMasterByOrgId(Long orgId);

	List<TdsMasterVO> getAllTdsMasterById(Long id);

	TdsMasterVO updateCreateTdsMaster(@Valid TdsMasterDTO tdsMasterDTO) throws ApplicationException;

	List<TdsMasterVO> getTdsMasterByActive();

//	AccountVO
	List<AccountVO> getAllAccountByOrgId(Long orgId);

	AccountVO updateCreateAccount(@Valid AccountDTO accountDTO) throws ApplicationException;

	List<AccountVO> getAllAccountById(Long id);

	List<AccountVO> getAccountByActive();

//	GroupLedgerVO
	List<GroupLedgerVO> getAllGroupLedgerById(Long id);

	List<GroupLedgerVO> getAllGroupLedgerByOrgId(Long orgId);

	GroupLedgerVO updateCreateGroupLedger(@Valid GroupLedgerDTO groupLedgerDTO) throws ApplicationException;

	List<GroupLedgerVO> getGroupLedgerByActive();

//	HsnSacCode
	List<HsnSacCodeVO> getAllHsnSacCodeById(Long id);

	List<HsnSacCodeVO> getAllHsnSacCodeByOrgId(Long orgId);

	HsnSacCodeVO updateCreateHsnSacCode(@Valid HsnSacCodeDTO hsnSacCodeDTO) throws ApplicationException;

	List<HsnSacCodeVO> getHsnSacCodeByActive();

//	ExRates
	List<ExRatesVO> getAllExRatesByOrgId(Long orgId);

	ExRatesVO updateCreateExRates(@Valid ExRatesDTO exRatesDTO) throws ApplicationException;

	List<ExRatesVO> getAllExRatesById(Long id);

	List<ExRatesVO> getExRatesByActive();

//	SubLedgerAccount
	List<SubLedgerAccountVO> getAllSubLedgerAccountByOrgId(Long orgId);

	SubLedgerAccountVO updateCreateSubLedgerAccount(@Valid SubLedgerAccountDTO subLedgerAccountDTO)
			throws ApplicationException;

	List<SubLedgerAccountVO> getAllSubLedgerAccountById(Long id);

	List<SubLedgerAccountVO> getSubLedgerAccountByActive();

//	CostCenterVO
	List<CostCenterVO> getAllCostCenterByOrgId(Long orgId);

	CostCenterVO updateCreateCostCenter(@Valid CostCenterDTO costCenterDTO) throws ApplicationException;

	List<CostCenterVO> getAllCostCenterById(Long id);

	List<CostCenterVO> getCostCenterByActive();

//	ChequeBox
	List<ChequeBookVO> getAllChequeBoxByOrgId(Long orgId);

	ChequeBookVO updateCreateChequeBox(@Valid ChequeBoxDTO chequeBoxDTO) throws ApplicationException;

	List<ChequeBookVO> getAllChequeBoxById(Long id);

	List<ChequeBookVO> getChequeBoxByActive();

//	ChargeTypeRequest
	List<ChargeTypeRequestVO> getAllChargeTypeRequestByOrgId(Long orgId);

	ChargeTypeRequestVO updateCreateChargeTypeRequest(@Valid ChargeTypeRequestDTO chargeTypeRequestDTO)
			throws ApplicationException;

	List<ChargeTypeRequestVO> getAllChargeTypeRequestById(Long id);

	List<ChargeTypeRequestVO> getChargeTypeRequestByActive();


}
