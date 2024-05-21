package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.Account1DTO;
import com.base.basesetup.dto.Account2DTO;
import com.base.basesetup.dto.Account3DTO;
import com.base.basesetup.dto.AccountDTO;
import com.base.basesetup.dto.GroupLedgerDTO;
import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.TaxMaster2DTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.dto.TcsMaster2DTO;
import com.base.basesetup.dto.TcsMasterDTO;
import com.base.basesetup.dto.TdsMaster2DTO;
import com.base.basesetup.dto.TdsMasterDTO;
import com.base.basesetup.entity.Account1VO;
import com.base.basesetup.entity.Account2VO;
import com.base.basesetup.entity.Account3VO;
import com.base.basesetup.entity.AccountVO;
import com.base.basesetup.entity.GroupLedgerVO;
import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.TaxMaster2VO;
import com.base.basesetup.entity.TaxMasterVO;
import com.base.basesetup.entity.TcsMaster2VO;
import com.base.basesetup.entity.TcsMasterVO;
import com.base.basesetup.entity.TdsMaster2VO;
import com.base.basesetup.entity.TdsMasterVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.Account1Repo;
import com.base.basesetup.repo.Account2Repo;
import com.base.basesetup.repo.Account3Repo;
import com.base.basesetup.repo.AccountRepo;
import com.base.basesetup.repo.GroupLedgerRepo;
import com.base.basesetup.repo.SetTaxRateRepo;
import com.base.basesetup.repo.TaxMaster2Repo;
import com.base.basesetup.repo.TaxMasterRepo;
import com.base.basesetup.repo.TcsMaster2Repo;
import com.base.basesetup.repo.TcsMasterRepo;
import com.base.basesetup.repo.TdsMaster2Repo;
import com.base.basesetup.repo.TdsMasterRepo;

@Service
public class MasterServiceImpl implements MasterService {
	public static final Logger LOGGER = LoggerFactory.getLogger(MasterServiceImpl.class);
	@Autowired
	SetTaxRateRepo setTaxRateRepo;

	@Autowired
	TaxMasterRepo taxMasterRepo;

	@Autowired
	TaxMaster2Repo taxMaster2Repo;

	@Autowired
	TcsMasterRepo tcsMasterRepo;

	@Autowired
	TcsMaster2Repo tcsMaster2Repo;

	@Autowired
	TdsMasterRepo tdsMasterRepo;

	@Autowired
	TdsMaster2Repo tdsMaster2Repo;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	Account1Repo account1Repo;

	@Autowired
	Account2Repo account2Repo;

	@Autowired
	Account3Repo account3Repo;

	@Autowired
	GroupLedgerRepo groupLedgerRepo;

	@Override
	public List<SetTaxRateVO> getAllSetTaxRateByOrgId(Long orgId) {
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
			setTaxRateVO = setTaxRateRepo.getAllSetTaxRateByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
			setTaxRateVO = setTaxRateRepo.findAll();
		}
		return setTaxRateVO;
	}

	@Override
	public List<SetTaxRateVO> getAllSetTaxRateById(Long id) {
		List<SetTaxRateVO> setTaxRateVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			setTaxRateVO = setTaxRateRepo.getAllSetTaxRateById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			setTaxRateVO = setTaxRateRepo.findAll();
		}
		return setTaxRateVO;
	}

	@Override
	public List<SetTaxRateVO> getAllSetTaxRate() {
		return setTaxRateRepo.findAll();
	}

	@Override
	public SetTaxRateVO updateCreateSetTaxRate(@Valid SetTaxRateDTO setTaxRateDTO) throws ApplicationException {
		SetTaxRateVO setTaxRateVO = new SetTaxRateVO();
		if (ObjectUtils.isNotEmpty(setTaxRateDTO.getSetTaxRateId())) {
			setTaxRateVO = setTaxRateRepo.findById(setTaxRateDTO.getSetTaxRateId())
					.orElseThrow(() -> new ApplicationException("Invalid SetTaxRate details"));
		}
		getSetTaxRateVOFromSetTaxRateDTO(setTaxRateDTO, setTaxRateVO);
		return setTaxRateRepo.save(setTaxRateVO);
	}

	private void getSetTaxRateVOFromSetTaxRateDTO(@Valid SetTaxRateDTO setTaxRateDTO, SetTaxRateVO setTaxRateVO) {
		
		setTaxRateVO.setOrgId(setTaxRateDTO.getOrgId());
		setTaxRateVO.setChapter(setTaxRateDTO.getChapter());
		setTaxRateVO.setSubChapter(setTaxRateDTO.getSubChapter());
		setTaxRateVO.setHsnCode(setTaxRateDTO.getHsnCode());
		setTaxRateVO.setBranch(setTaxRateDTO.getBranch());
		setTaxRateVO.setNewRate(setTaxRateDTO.getNewRate());
		setTaxRateVO.setExcepmted("e");
		setTaxRateVO.setActive(setTaxRateDTO.isActive());
	}

	// TAXMASTER

	@Override

	public TaxMasterVO updateCreateTaxMaster(TaxMasterDTO taxMasterDTO) throws ApplicationException {
		TaxMasterVO taxMasterVO = new TaxMasterVO();
		if (ObjectUtils.isNotEmpty(taxMasterDTO.getTaxMasterId())) {
			taxMasterVO = taxMasterRepo.findById(taxMasterDTO.getTaxMasterId())
					.orElseThrow(() -> new ApplicationException("Invalid Tax Master details"));
		}

		List<TaxMaster2VO> taxMaster2VOs = new ArrayList<>();
		if (taxMasterDTO.getTaxMaster2DTO() != null) {
			for (TaxMaster2DTO taxMaster2DTO : taxMasterDTO.getTaxMaster2DTO()) {
				if (taxMaster2DTO.getTaxMaster2Id() != null & ObjectUtils.isNotEmpty(taxMaster2DTO.getTaxMaster2Id())) {
					TaxMaster2VO taxMaster2VO = taxMaster2Repo.findById(taxMaster2DTO.getTaxMaster2Id()).get();
					taxMaster2VO.setInputAccount(taxMaster2DTO.getInputAccount());
					taxMaster2VO.setOutputAccount(taxMaster2DTO.getOutputAccount());
					taxMaster2VO.setSgstRcmPayable(true);
					taxMaster2VO.setTaxMasterVO(taxMasterVO);
					taxMaster2VOs.add(taxMaster2VO);

				} else {
					TaxMaster2VO taxMaster2VO = new TaxMaster2VO();
					taxMaster2VO.setInputAccount(taxMaster2DTO.getInputAccount());
					taxMaster2VO.setOutputAccount(taxMaster2DTO.getOutputAccount());
					taxMaster2VO.setSgstRcmPayable(true);
					taxMaster2VO.setTaxMasterVO(taxMasterVO);
					taxMaster2VOs.add(taxMaster2VO);
				}
			}
		}

		getTaxMasterVOFromTaxMasterDTO(taxMasterDTO, taxMasterVO);

		taxMasterVO.setTaxMaster2VO(taxMaster2VOs);
		return taxMasterRepo.save(taxMasterVO);

	}

	private void getTaxMasterVOFromTaxMasterDTO(TaxMasterDTO taxMasterDTO, TaxMasterVO taxMasterVO) {
		taxMasterVO.setOrgId(taxMasterDTO.getOrgId());
		taxMasterVO.setTaxType(taxMasterDTO.getTaxType());
		taxMasterVO.setTaxPercentage(taxMasterDTO.getTaxPercentage());
		taxMasterVO.setTaxDescription(taxMasterDTO.getTaxDescription());
		taxMasterVO.setActive(taxMasterDTO.isActive());

	}

	@Override
	public List<TaxMasterVO> getAllTaxMasterByOrgId(Long orgId) {
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TaxMasterInformation BY OrgId : {}", orgId);
			taxMasterVO = taxMasterRepo.getAllTaxMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TaxMasterInformation For All OrgId.");
			taxMasterVO = taxMasterRepo.findAll();
		}
		return taxMasterVO;
	}

	@Override
	public List<TaxMasterVO> getAllTaxMasterById(Long id) {
		List<TaxMasterVO> taxMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TaxMasterInformation BY Id : {}", id);
			taxMasterVO = taxMasterRepo.getAllTaxMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TaxMasterInformation For All Id.");
			taxMasterVO = taxMasterRepo.findAll();
		}
		return taxMasterVO;
	}

	@Override
	public List<TaxMasterVO> getAllTaxMaster() {
		return taxMasterRepo.findAll();
	}

	// TCSMASTER

	@Override
	public List<TcsMasterVO> getAllTcsMasterByOrgId(Long orgId) {
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TcsMasterInformation BY OrgId : {}", orgId);
			tcsMasterVO = tcsMasterRepo.getAllTcsMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TcsMasterInformation For All OrgId.");
			tcsMasterVO = tcsMasterRepo.findAll();
		}
		return tcsMasterVO;
	}

	@Override
	public List<TcsMasterVO> getAllTcsMasterById(Long id) {
		List<TcsMasterVO> tcsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TcsMasterInformation BY Id : {}", id);
			tcsMasterVO = tcsMasterRepo.getAllTcsMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TcsMasterInformation For All Id.");
			tcsMasterVO = tcsMasterRepo.findAll();
		}
		return tcsMasterVO;
	}

	@Override
	public List<TcsMasterVO> getAllTcsMaster() {
		return tcsMasterRepo.findAll();
	}

	@Override
	public TcsMasterVO updateCreateTcsMaster(@Valid TcsMasterDTO tcsMasterDTO) throws ApplicationException {
		TcsMasterVO tcsMasterVO = new TcsMasterVO();
		if (ObjectUtils.isNotEmpty(tcsMasterDTO.getTcsMasterId())) {
			tcsMasterVO = tcsMasterRepo.findById(tcsMasterDTO.getTcsMasterId())
					.orElseThrow(() -> new ApplicationException("Invalid Tcs Master details"));
		}

		List<TcsMaster2VO> tcsMaster2VOs = new ArrayList<>();
		if (tcsMasterDTO.getTcsMaster2DTO() != null) {
			for (TcsMaster2DTO tcsMaster2DTO : tcsMasterDTO.getTcsMaster2DTO()) {
				if (tcsMaster2DTO.getTcsMaster2Id() != null & ObjectUtils.isNotEmpty(tcsMaster2DTO.getTcsMaster2Id())) {
					TcsMaster2VO tcsMaster2VO = tcsMaster2Repo.findById(tcsMaster2DTO.getTcsMaster2Id()).get();
					tcsMaster2VO.setFromDate(tcsMaster2DTO.getFromDate());
					tcsMaster2VO.setToDate(tcsMaster2DTO.getToDate());
					tcsMaster2VO.setSerialNo(tcsMaster2DTO.getSerialNo());
					tcsMaster2VO.setTcsPercentage(tcsMaster2DTO.getTcsPercentage());
					tcsMaster2VO.setTcsMasterVO(tcsMasterVO);
					tcsMaster2VOs.add(tcsMaster2VO);

				} else {
					TcsMaster2VO tcsMaster2VO = new TcsMaster2VO();
					tcsMaster2VO.setFromDate(tcsMaster2DTO.getFromDate());
					tcsMaster2VO.setToDate(tcsMaster2DTO.getToDate());
					tcsMaster2VO.setSerialNo(tcsMaster2DTO.getSerialNo());
					tcsMaster2VO.setTcsPercentage(tcsMaster2DTO.getTcsPercentage());
					tcsMaster2VO.setTcsMasterVO(tcsMasterVO);
					tcsMaster2VOs.add(tcsMaster2VO);
				}
			}
		}

		getTcsMasterVOFromTcsMasterDTO(tcsMasterDTO, tcsMasterVO);

		tcsMasterVO.setTcsMaster2VO(tcsMaster2VOs);
		return tcsMasterRepo.save(tcsMasterVO);

	}

	private void getTcsMasterVOFromTcsMasterDTO(@Valid TcsMasterDTO tcsMasterDTO, TcsMasterVO tcsMasterVO) {
		tcsMasterVO.setOrgId(tcsMasterDTO.getOrgId());
		tcsMasterVO.setSection(tcsMasterDTO.getSection());
		tcsMasterVO.setSectionName(tcsMasterDTO.getSectionName());
		tcsMasterVO.setActive(tcsMasterDTO.isActive());
	}

	// TDSMaster

	@Override
	public List<TdsMasterVO> getAllTdsMasterByOrgId(Long orgId) {
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  TdsMasterInformation BY OrgId : {}", orgId);
			tdsMasterVO = tdsMasterRepo.getAllTdsMasterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  TdsMasterInformation For All OrgId.");
			tdsMasterVO = tdsMasterRepo.findAll();
		}
		return tdsMasterVO;
	}

	@Override
	public List<TdsMasterVO> getAllTdsMasterById(Long id) {
		List<TdsMasterVO> tdsMasterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  TdsMasterInformation BY Id : {}", id);
			tdsMasterVO = tdsMasterRepo.getAllTdsMasterById(id);
		} else {
			LOGGER.info("Successfully Received  TdsMasterInformation For All Id.");
			tdsMasterVO = tdsMasterRepo.findAll();
		}
		return tdsMasterVO;
	}

	@Override
	public List<TdsMasterVO> getAllTdsMaster() {
		return tdsMasterRepo.findAll();
	}

	@Override
	public TdsMasterVO updateCreateTdsMaster(@Valid TdsMasterDTO tdsMasterDTO) throws ApplicationException {
		TdsMasterVO tdsMasterVO = new TdsMasterVO();
		if (ObjectUtils.isNotEmpty(tdsMasterDTO.getTdsMasterId())) {
			tdsMasterVO = tdsMasterRepo.findById(tdsMasterDTO.getTdsMasterId())
					.orElseThrow(() -> new ApplicationException("Invalid Tds Master details"));
		}

		List<TdsMaster2VO> tdsMaster2VOs = new ArrayList<>();
		if (tdsMasterDTO.getTdsMaster2DTO() != null) {
			for (TdsMaster2DTO tdsMaster2DTO : tdsMasterDTO.getTdsMaster2DTO()) {
				if (tdsMaster2DTO.getTdsMaster2Id() != null & ObjectUtils.isNotEmpty(tdsMaster2DTO.getTdsMaster2Id())) {
					TdsMaster2VO tdsMaster2VO = tdsMaster2Repo.findById(tdsMaster2DTO.getTdsMaster2Id()).get();
					tdsMaster2VO.setFromDate(tdsMaster2DTO.getFromDate());
					tdsMaster2VO.setToDate(tdsMaster2DTO.getToDate());
					tdsMaster2VO.setSurPercentage(tdsMaster2DTO.getSurPercentage());
					tdsMaster2VO.setEdcessPercentage(tdsMaster2DTO.getEdcessPercentage());
					tdsMaster2VO.setTcsPercentage(tdsMaster2DTO.getTcsPercentage());
					tdsMaster2VO.setTdsMasterVO(tdsMasterVO);
					tdsMaster2VOs.add(tdsMaster2VO);

				} else {
					TdsMaster2VO tdsMaster2VO = new TdsMaster2VO();
					tdsMaster2VO.setFromDate(tdsMaster2DTO.getFromDate());
					tdsMaster2VO.setToDate(tdsMaster2DTO.getToDate());
					tdsMaster2VO.setSurPercentage(tdsMaster2DTO.getSurPercentage());
					tdsMaster2VO.setEdcessPercentage(tdsMaster2DTO.getEdcessPercentage());
					tdsMaster2VO.setTcsPercentage(tdsMaster2DTO.getTcsPercentage());
					tdsMaster2VO.setTdsMasterVO(tdsMasterVO);
					tdsMaster2VOs.add(tdsMaster2VO);
				}
			}
		}

		getTdsMasterVOFromTdsMasterDTO(tdsMasterDTO, tdsMasterVO);

		tdsMasterVO.setTdsMaster2VO(tdsMaster2VOs);
		return tdsMasterRepo.save(tdsMasterVO);

	}

	private void getTdsMasterVOFromTdsMasterDTO(@Valid TdsMasterDTO tdsMasterDTO, TdsMasterVO tdsMasterVO) {
		tdsMasterVO.setOrgId(tdsMasterDTO.getOrgId());
		tdsMasterVO.setSection(tdsMasterDTO.getSection());
		tdsMasterVO.setSectionName(tdsMasterDTO.getSectionName());
		tdsMasterVO.setActive(tdsMasterDTO.isActive());
	}

	// AccountVO

	@Override
	public List<AccountVO> getAllAccountByOrgId(Long orgId) {
		List<AccountVO> accountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
			accountVO = accountRepo.getAllAccountByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
			accountVO = accountRepo.findAll();
		}
		return accountVO;
	}

	@Override
	public List<AccountVO> getAllAccountByaccountId(Long accountId) {
		List<AccountVO> accountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(accountId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", accountId);
			accountVO = accountRepo.getAllAccountByaccountId(accountId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			accountVO = accountRepo.findAll();
		}
		return accountVO;
	}

	@Override
	public AccountVO updateCreateAccount(@Valid AccountDTO accountDTO) throws ApplicationException {
		AccountVO accountVO = new AccountVO();
		if (ObjectUtils.isNotEmpty(accountDTO.getAccountId())) {
			accountVO = accountRepo.findById(accountDTO.getAccountId())
					.orElseThrow(() -> new ApplicationException("Invalid account details"));
		}
		List<Account1VO> account1VOs = new ArrayList<>();
		if (accountDTO.getAccount1DTO() != null) {
			for (Account1DTO account1DTO : accountDTO.getAccount1DTO()) {
				if (account1DTO.getAccount1Id() != null & ObjectUtils.isNotEmpty(account1DTO.getAccount1Id())) {
					Account1VO account1VO = account1Repo.findById(account1DTO.getAccount1Id()).get();
					account1VO.setBalanceSheet(account1DTO.getBalanceSheet());
					account1VO.setIncomeStatement(account1DTO.getIncomeStatement());
					account1VO.setCashFlowStatement(account1DTO.getCashFlowStatement());
					account1VO.setAccountVO(accountVO);
					account1VOs.add(account1VO);

				} else {
					Account1VO account1VO = new Account1VO();
					account1VO.setBalanceSheet(account1DTO.getBalanceSheet());
					account1VO.setIncomeStatement(account1DTO.getIncomeStatement());
					account1VO.setCashFlowStatement(account1DTO.getCashFlowStatement());
					account1VO.setAccountVO(accountVO);
					account1VOs.add(account1VO);
				}
			}
		}
		List<Account2VO> account2VOs = new ArrayList<>();
		if (accountDTO.getAccount2DTO() != null) {
			for (Account2DTO account2DTO : accountDTO.getAccount2DTO()) {
				if (account2DTO.getAccount2Id() != null & ObjectUtils.isNotEmpty(account2DTO.getAccount2Id())) {
					Account2VO account2VO = account2Repo.findById(account2DTO.getAccount2Id()).get();
					account2VO.setBankType(account2DTO.getBankType());
					account2VO.setOverDraftLimit(account2DTO.getOverDraftLimit());
					account2VO.setAccountNo(account2DTO.getAccountNo());
					account2VO.setAccountVO(accountVO);
					account2VOs.add(account2VO);

				} else {
					Account2VO account2VO = new Account2VO();
					account2VO.setBankType(account2DTO.getBankType());
					account2VO.setOverDraftLimit(account2DTO.getOverDraftLimit());
					account2VO.setAccountNo(account2DTO.getAccountNo());
					account2VO.setAccountVO(accountVO);
					account2VOs.add(account2VO);

				}
			}
		}
		List<Account3VO> account3VOs = new ArrayList<>();
		if (accountDTO.getAccount3DTO() != null) {
			for (Account3DTO account3DTO : accountDTO.getAccount3DTO()) {
				if (account3DTO.getAccount3Id() != null & ObjectUtils.isNotEmpty(account3DTO.getAccount3Id())) {
					Account3VO account3VO = account3Repo.findById(account3DTO.getAccount3Id()).get();
					account3VO.setCompany(account3DTO.getCompany());
					account3VO.setBranchLocation(account3DTO.getBranchLocation());
					account3VO.setAccountVO(accountVO);
					account3VOs.add(account3VO);

				} else {
					Account3VO account3VO = new Account3VO();
					account3VO.setCompany(account3DTO.getCompany());
					account3VO.setBranchLocation(account3DTO.getBranchLocation());
					account3VO.setAccountVO(accountVO);
					account3VOs.add(account3VO);

				}
			}
		}

		getAccountVOFromAccountDTO(accountDTO, accountVO);

		accountVO.setAccount1VO(account1VOs);
		accountVO.setAccount2VO(account2VOs);
		accountVO.setAccount3VO(account3VOs);
		return accountRepo.save(accountVO);

	}

	private void getAccountVOFromAccountDTO(@Valid AccountDTO accountDTO, AccountVO accountVO) {
		accountVO.setOrgId(accountDTO.getOrgId());
		accountVO.setAccountGroup(accountDTO.getAccountGroup());
		accountVO.setBranchLocation(accountDTO.getBranchLocation());
		accountVO.setAccountType(accountDTO.getAccountType());
		accountVO.setGroupName(accountDTO.getGroupName());
		accountVO.setAccountCode(accountDTO.getAccountCode());
		accountVO.setAccountGroupName(accountDTO.getBranchLocation());
		accountVO.setCurrency(accountDTO.getCurrency());
		accountVO.setCategory(accountDTO.getCategory());
		accountVO.setBlock(accountDTO.isBlock());
		accountVO.setItcApplicable(accountDTO.isItcApplicable());
		accountVO.setActive(accountDTO.isActive());

	}

	// groupledgerVO

	@Override
	public List<GroupLedgerVO> getAllGroupLedgerById(Long id) {
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			groupLedgerVO = groupLedgerRepo.getAllGroupLedgerById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			groupLedgerVO = groupLedgerRepo.findAll();
		}
		return groupLedgerVO;
	}

	@Override
	public List<GroupLedgerVO> getAllGroupLedgerByOrgId(Long orgId) {
		List<GroupLedgerVO> groupLedgerVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY OrgId : {}", orgId);
			groupLedgerVO = groupLedgerRepo.getAllGroupLedgerByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All OrgId.");
			groupLedgerVO = groupLedgerRepo.findAll();
		}
		return groupLedgerVO;
	}

	@Override
	public GroupLedgerVO updateCreateGroupLedger(@Valid GroupLedgerDTO groupLedgerDTO) throws ApplicationException {
		GroupLedgerVO groupLedgerVO = new GroupLedgerVO();
		if (ObjectUtils.isNotEmpty(groupLedgerDTO.getGLId())) {
			groupLedgerVO = groupLedgerRepo.findById(groupLedgerDTO.getGLId())
					.orElseThrow(() -> new ApplicationException("Invalid GroupLedger details"));
		}
		getGroupLedgerVOFromGroupLedgerDTO(groupLedgerDTO, groupLedgerVO);
		return groupLedgerRepo.save(groupLedgerVO);
	}

	private void getGroupLedgerVOFromGroupLedgerDTO(@Valid GroupLedgerDTO groupLedgerDTO, GroupLedgerVO groupLedgerVO) {
		groupLedgerVO.setGroupName(groupLedgerDTO.getGroupName());
		groupLedgerVO.setOrgId(groupLedgerDTO.getOrgId());
		groupLedgerVO.setAccountCode(groupLedgerDTO.getAccountCode());
		groupLedgerVO.setCoaList(groupLedgerDTO.getCoaList());
		groupLedgerVO.setGstTaxFlag(true);
		groupLedgerVO.setType(groupLedgerDTO.getType());
		groupLedgerVO.setCategory(groupLedgerDTO.getCategory());
		groupLedgerVO.setCurrency(groupLedgerDTO.getCurrency());
		groupLedgerVO.setBranch(groupLedgerDTO.getBranch());
		groupLedgerVO.setActive(groupLedgerDTO.isActive());
		groupLedgerVO.setInterBranchAc(true);
		groupLedgerVO.setControllAc(true);
		groupLedgerVO.setAccountGroupName(groupLedgerDTO.getAccountGroupName());

	}

}
