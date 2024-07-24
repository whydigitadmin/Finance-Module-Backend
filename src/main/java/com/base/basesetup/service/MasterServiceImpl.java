package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.base.basesetup.dto.ChargeTypeRequestDTO;
import com.base.basesetup.dto.ChequeBookDTO;
import com.base.basesetup.dto.CostCenterDTO;
import com.base.basesetup.dto.ExRatesDTO;
import com.base.basesetup.dto.GroupLedgerDTO;
import com.base.basesetup.dto.HsnSacCodeDTO;
import com.base.basesetup.dto.ListOfValues1DTO;
import com.base.basesetup.dto.ListOfValuesDTO;
import com.base.basesetup.dto.SetTaxRateDTO;
import com.base.basesetup.dto.SubLedgerAccountDTO;
import com.base.basesetup.dto.TaxMasterDTO;
import com.base.basesetup.dto.TcsMaster2DTO;
import com.base.basesetup.dto.TcsMasterDTO;
import com.base.basesetup.dto.TdsMaster2DTO;
import com.base.basesetup.dto.TdsMasterDTO;
import com.base.basesetup.entity.Account1VO;
import com.base.basesetup.entity.Account2VO;
import com.base.basesetup.entity.Account3VO;
import com.base.basesetup.entity.AccountVO;
import com.base.basesetup.entity.ChargeTypeRequestVO;
import com.base.basesetup.entity.ChequeBookDetailsVO;
import com.base.basesetup.entity.ChequeBookVO;
import com.base.basesetup.entity.CostCenterVO;
import com.base.basesetup.entity.ExRatesVO;
import com.base.basesetup.entity.GroupLedgerVO;
import com.base.basesetup.entity.HsnSacCodeVO;
import com.base.basesetup.entity.ListOfValues1VO;
import com.base.basesetup.entity.ListOfValuesVO;
import com.base.basesetup.entity.SetTaxRateVO;
import com.base.basesetup.entity.SubLedgerAccountVO;
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
import com.base.basesetup.repo.ChargeTypeRequestRepo;
import com.base.basesetup.repo.ChequeBookDetailsRepo;
import com.base.basesetup.repo.ChequeBookRepo;
import com.base.basesetup.repo.CostCenterRepo;
import com.base.basesetup.repo.ExRatesRepo;
import com.base.basesetup.repo.GroupLedgerRepo;
import com.base.basesetup.repo.HsnSacCodeRepo;
import com.base.basesetup.repo.ListOfValues1Repo;
import com.base.basesetup.repo.ListOfValuesRepo;
import com.base.basesetup.repo.SetTaxRateRepo;
import com.base.basesetup.repo.SubLedgerAccountRepo;
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

	@Autowired
	HsnSacCodeRepo hsnSacCodeRepo;

	@Autowired
	ExRatesRepo exRatesRepo;

	@Autowired
	SubLedgerAccountRepo subLedgerAccountRepo;

	@Autowired
	CostCenterRepo costCenterRepo;

	@Autowired
	ChequeBookRepo chequeBookRepo;

	@Autowired
	ChargeTypeRequestRepo chargeTypeRequestRepo;

	@Autowired
	ListOfValuesRepo listOfValuesRepo;

	@Autowired
	ListOfValues1Repo listOfValues1Repo;

	@Autowired
	ChequeBookDetailsRepo chequeBookDetailsRepo; 
	
	// setTaxesRate
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
	public SetTaxRateVO updateCreateSetTaxRate(@Valid SetTaxRateDTO setTaxRateDTO) throws Exception {
		SetTaxRateVO setTaxRateVO = new SetTaxRateVO();

		if (ObjectUtils.isNotEmpty(setTaxRateDTO.getId())) {
			setTaxRateVO = setTaxRateRepo.findById(setTaxRateDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid SetTaxRate details"));
		} else {
			if (setTaxRateRepo.existsByChapterAndOrgId(setTaxRateDTO.getChapter(), setTaxRateDTO.getOrgId())) {
				throw new ApplicationException("The given chapter already exists");
			}
			if (setTaxRateRepo.existsByHsnCodeAndOrgId(setTaxRateDTO.getHsnCode(), setTaxRateDTO.getOrgId())) {
				throw new ApplicationException("The given Hsn Code already exists.");
			}
		}

		getSetTaxRateVOFromSetTaxRateDTO(setTaxRateDTO, setTaxRateVO);

		if (ObjectUtils.isNotEmpty(setTaxRateDTO.getId())) {
			SetTaxRateVO setTaxRate = setTaxRateRepo.findById(setTaxRateDTO.getId()).orElse(null);
			if (!setTaxRate.getChapter().equals(setTaxRateDTO.getChapter())) {
				if (setTaxRateRepo.existsByChapterAndOrgId(setTaxRateDTO.getChapter(), setTaxRateDTO.getOrgId())) {
					throw new ApplicationException("The given chapter already exists.");
				}
			}
			if (!setTaxRate.getHsnCode().equals(setTaxRateDTO.getHsnCode())) {
				if (setTaxRateRepo.existsByHsnCodeAndOrgId(setTaxRateDTO.getHsnCode(), setTaxRateDTO.getOrgId())) {
					throw new ApplicationException("The given Hsn Code already exists.");
				}
			}
		}

		return setTaxRateRepo.save(setTaxRateVO);
	}

	private void getSetTaxRateVOFromSetTaxRateDTO(@Valid SetTaxRateDTO setTaxRateDTO, SetTaxRateVO setTaxRateVO)
			throws Exception {
		setTaxRateVO.setOrgId(setTaxRateDTO.getOrgId());
		setTaxRateVO.setChapter(setTaxRateDTO.getChapter());
		setTaxRateVO.setSubChapter(setTaxRateDTO.getSubChapter());
		setTaxRateVO.setHsnCode(setTaxRateDTO.getHsnCode());
		setTaxRateVO.setBranch(setTaxRateDTO.getBranch());
		setTaxRateVO.setNewRate(setTaxRateDTO.getNewRate());
		setTaxRateVO.setExcepmted("e");
		setTaxRateVO.setActive(setTaxRateDTO.isActive());
	}

	@Override
	public List<SetTaxRateVO> getSetTaxRateByActive() {
		return setTaxRateRepo.findSetTaxRateByActive();
	}

	// TAXMASTER

	@Override

	public TaxMasterVO updateCreateTaxMaster(TaxMasterDTO taxMasterDTO) throws ApplicationException {
		TaxMasterVO taxMasterVO = new TaxMasterVO();
		if (ObjectUtils.isNotEmpty(taxMasterDTO.getId())) {
			taxMasterVO = taxMasterRepo.findById(taxMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Tax Master details"));
		}

		getTaxMasterVOFromTaxMasterDTO(taxMasterDTO, taxMasterVO);
		return taxMasterRepo.save(taxMasterVO);

	}

	private void getTaxMasterVOFromTaxMasterDTO(TaxMasterDTO taxMasterDTO, TaxMasterVO taxMasterVO) {
		taxMasterVO.setOrgId(taxMasterDTO.getOrgId());
		taxMasterVO.setTaxType(taxMasterDTO.getTaxType());
		taxMasterVO.setTaxPercentage(taxMasterDTO.getTaxPercentage());
		taxMasterVO.setTaxDescription(taxMasterDTO.getTaxDescription());
		taxMasterVO.setActive(taxMasterDTO.isActive());
		taxMasterVO.setInputAccount(taxMasterDTO.getInputAccount());
		taxMasterVO.setOutputAccount(taxMasterDTO.getOutputAccount());
		taxMasterVO.setSgstRcmPayable(taxMasterDTO.isActive());
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
	public List<TaxMasterVO> getTaxMasterByActive() {
		return taxMasterRepo.findTaxMasterByActive();

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
	public TcsMasterVO updateCreateTcsMaster(@Valid TcsMasterDTO tcsMasterDTO) throws ApplicationException {
		TcsMasterVO tcsMasterVO = new TcsMasterVO();
		if (ObjectUtils.isNotEmpty(tcsMasterDTO.getId())) {
			tcsMasterVO = tcsMasterRepo.findById(tcsMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Tcs Master details"));
		} else {
			if (tcsMasterRepo.existsBySectionNameAndOrgId(tcsMasterDTO.getSectionName(), tcsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given section name already exists.");
			}
			if (tcsMasterRepo.existsBySectionAndOrgId(tcsMasterDTO.getSection(), tcsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given Section already exists.");
			}
		}
		if (ObjectUtils.isNotEmpty(tcsMasterDTO.getId())) {
			TcsMasterVO tcsMaster = tcsMasterRepo.findById(tcsMasterDTO.getId()).orElse(null);
			if (!tcsMaster.getSection().equals(tcsMasterDTO.getSection())) {
				if (tcsMasterRepo.existsBySectionAndOrgId(tcsMasterDTO.getSection(), tcsMasterDTO.getOrgId())) {
					throw new ApplicationException("The given section already exists.");
				}
			}
			if (!tcsMaster.getSectionName().equals(tcsMasterDTO.getSectionName())) {
				if (tcsMasterRepo.existsBySectionNameAndOrgId(tcsMasterDTO.getSectionName(), tcsMasterDTO.getOrgId())) {
					throw new ApplicationException("The given Section name already exists.");
				}
			}
		}

		List<TcsMaster2VO> tcsMaster2VOs = new ArrayList<>();
		if (tcsMasterDTO.getTcsMaster2DTO() != null) {
			for (TcsMaster2DTO tcsMaster2DTO : tcsMasterDTO.getTcsMaster2DTO()) {
				if (tcsMaster2DTO.getId() != null & ObjectUtils.isNotEmpty(tcsMaster2DTO.getId())) {
					TcsMaster2VO tcsMaster2VO = tcsMaster2Repo.findById(tcsMaster2DTO.getId()).get();
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
		tcsMasterVO.setCreatedBy(tcsMasterDTO.getCreatedBy());
		tcsMasterVO.setUpdatedBy(tcsMasterDTO.getUpdatedBy());
	}

	@Override
	public List<TcsMasterVO> getTcsMasterByActive() {
		return tcsMasterRepo.findTcsMasterByActive();

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
	public TdsMasterVO updateCreateTdsMaster(@Valid TdsMasterDTO tdsMasterDTO) throws ApplicationException {
		TdsMasterVO tdsMasterVO = new TdsMasterVO();
		if (ObjectUtils.isNotEmpty(tdsMasterDTO.getId())) {
			tdsMasterVO = tdsMasterRepo.findById(tdsMasterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid Tds Master details"));
		} else {
			if (tdsMasterRepo.existsBySectionNameAndOrgId(tdsMasterDTO.getSectionName(), tdsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given section name already exists.");
			}
			if (tdsMasterRepo.existsBySectionAndOrgId(tdsMasterDTO.getSection(), tdsMasterDTO.getOrgId())) {
				throw new ApplicationException("The given Section already exists.");
			}
		}

		if (ObjectUtils.isNotEmpty(tdsMasterDTO.getId())) {
			TdsMasterVO tdsMaster = tdsMasterRepo.findById(tdsMasterDTO.getId()).orElse(null);
			if (!tdsMaster.getSection().equals(tdsMasterDTO.getSection())) {
				if (tdsMasterRepo.existsBySectionAndOrgId(tdsMasterDTO.getSection(), tdsMasterDTO.getOrgId())) {
					throw new ApplicationException("The given section already exists.");
				}
			}
			if (!tdsMaster.getSectionName().equals(tdsMasterDTO.getSectionName())) {
				if (tdsMasterRepo.existsBySectionNameAndOrgId(tdsMasterDTO.getSectionName(), tdsMasterDTO.getOrgId())) {
					throw new ApplicationException("The given Section name already exists.");
				}
			}
		}

		List<TdsMaster2VO> tdsMaster2VOs = new ArrayList<>();
		if (tdsMasterDTO.getTdsMaster2DTO() != null) {
			for (TdsMaster2DTO tdsMaster2DTO : tdsMasterDTO.getTdsMaster2DTO()) {
				if (tdsMaster2DTO.getId() != null & ObjectUtils.isNotEmpty(tdsMaster2DTO.getId())) {
					TdsMaster2VO tdsMaster2VO = tdsMaster2Repo.findById(tdsMaster2DTO.getId()).get();
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

	@Override
	public List<TdsMasterVO> getTdsMasterByActive() {
		return tdsMasterRepo.findTdsMasterByActive();

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
	public List<AccountVO> getAllAccountById(Long id) {
		List<AccountVO> accountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SetTaxRateInformation BY Id : {}", id);
			accountVO = accountRepo.getAllAccountById(id);
		} else {
			LOGGER.info("Successfully Received  SetTaxRateInformation For All Id.");
			accountVO = accountRepo.findAll();
		}
		return accountVO;
	}

	@Override
	public AccountVO updateCreateAccount(@Valid AccountDTO accountDTO) throws ApplicationException {
		AccountVO accountVO = new AccountVO();
		if (ObjectUtils.isNotEmpty(accountDTO.getId())) {
			accountVO = accountRepo.findById(accountDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid account details"));
		} else {
			if (accountRepo.existsByAccountNameAndOrgId(accountDTO.getAccountName(), accountDTO.getOrgId())) {
				throw new ApplicationException("The given Account name already exists.");
			}
			if (accountRepo.existsByAccountCodeAndOrgId(accountDTO.getAccountCode(), accountDTO.getOrgId())) {
				throw new ApplicationException("The given Account Code already exists.");
			}
		}
		if (ObjectUtils.isNotEmpty(accountDTO.getId())) {
			AccountVO account = accountRepo.findById(accountDTO.getId()).orElse(null);
			if (!account.getAccountName().equals(accountDTO.getAccountName())) {
				if (accountRepo.existsByAccountNameAndOrgId(accountDTO.getAccountName(), accountDTO.getOrgId())) {
					throw new ApplicationException("The given Account name already exists.");
				}
			}
			if (!account.getAccountCode().equals(accountDTO.getAccountCode())) {
				if (accountRepo.existsByAccountCodeAndOrgId(accountDTO.getAccountCode(), accountDTO.getOrgId())) {
					throw new ApplicationException("The given Account Code already exists.");
				}
			}
		}

		List<Account1VO> account1VOs = new ArrayList<>();
		if (accountDTO.getAccount1DTO() != null) {
			for (Account1DTO account1DTO : accountDTO.getAccount1DTO()) {
				if (account1DTO.getId() != null & ObjectUtils.isNotEmpty(account1DTO.getId())) {
					Account1VO account1VO = account1Repo.findById(account1DTO.getId()).get();
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
				if (account2DTO.getId() != null & ObjectUtils.isNotEmpty(account2DTO.getId())) {
					Account2VO account2VO = account2Repo.findById(account2DTO.getId()).get();
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
				if (account3DTO.getId() != null & ObjectUtils.isNotEmpty(account3DTO.getId())) {
					Account3VO account3VO = account3Repo.findById(account3DTO.getId()).get();
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
		accountVO.setAccountName(accountDTO.getAccountName());
		accountVO.setCurrency(accountDTO.getCurrency());
		accountVO.setCategory(accountDTO.getCategory());
		accountVO.setBlock(accountDTO.isBlock());
		accountVO.setItcApplicable(accountDTO.isItcApplicable());
		accountVO.setCompanyName(accountDTO.getCompanyName());
		accountVO.setACatCode(accountDTO.getACatCode());
		accountVO.setACategory(accountDTO.getACategory());
		accountVO.setAType(accountDTO.getAType());
		accountVO.setACurrency(accountDTO.getACurrency());
		accountVO.setTransId(accountDTO.getTransId());
		accountVO.setBp(accountDTO.getBp());
		accountVO.setGroupCode(accountDTO.getGroupCode());
		accountVO.setGst(accountDTO.getGst());
	}

	@Override
	public List<AccountVO> getAccountByActive() {
		return accountRepo.findAccountByActive();

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
		if (ObjectUtils.isNotEmpty(groupLedgerDTO.getId())) {
			groupLedgerVO = groupLedgerRepo.findById(groupLedgerDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid GroupLedger details"));
		} else {
			if (groupLedgerRepo.existsByAccountCodeAndOrgId(groupLedgerDTO.getAccountCode(),
					groupLedgerDTO.getOrgId())) {
				throw new ApplicationException("The given Account Code already exists.");
			}
			if (groupLedgerRepo.existsByAccountGroupNameAndOrgId(groupLedgerDTO.getAccountGroupName(),
					groupLedgerDTO.getOrgId())) {
				throw new ApplicationException("The given Account Group Name already exists.");
			}
		}
		if (ObjectUtils.isNotEmpty(groupLedgerDTO.getId())) {
			GroupLedgerVO groupLedger = groupLedgerRepo.findById(groupLedgerDTO.getId()).orElse(null);
			if (!groupLedger.getAccountCode().equals(groupLedgerDTO.getAccountCode())) {
				if (groupLedgerRepo.existsByAccountCodeAndOrgId(groupLedgerDTO.getAccountCode(),
						groupLedgerDTO.getOrgId())) {
					throw new ApplicationException("The given Account name already exists.");
				}
			}
			if (!groupLedger.getGroupName().equals(groupLedgerDTO.getGroupName())) {
				if (groupLedgerRepo.existsByAccountGroupNameAndOrgId(groupLedgerDTO.getAccountGroupName(),
						groupLedgerDTO.getOrgId())) {
					throw new ApplicationException("The given Account Code already exists.");
				}
			}
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

	@Override
	public List<GroupLedgerVO> getGroupLedgerByActive() {
		return groupLedgerRepo.findGroupLedgerByActive();

	}

	// HsnSacCode

	@Override
	public List<HsnSacCodeVO> getAllHsnSacCodeById(Long id) {
		List<HsnSacCodeVO> hsnSacCodeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  HsnSacCode Information BY Id : {}", id);
			hsnSacCodeVO = hsnSacCodeRepo.getAllHsnSacCodeById(id);
		} else {
			LOGGER.info("Successfully Received  HsnSacCode Information For All Id.");
			hsnSacCodeVO = hsnSacCodeRepo.findAll();
		}
		return hsnSacCodeVO;
	}

	@Override
	public List<HsnSacCodeVO> getAllHsnSacCodeByOrgId(Long orgId) {
		List<HsnSacCodeVO> hsnSacCodeVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  HsnSacCode Information BY OrgId : {}", orgId);
			hsnSacCodeVO = hsnSacCodeRepo.getAllHsnSacCodeByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  HsnSacCode Information For All OrgId.");
			hsnSacCodeVO = hsnSacCodeRepo.findAll();
		}
		return hsnSacCodeVO;
	}

	@Override
	public HsnSacCodeVO updateCreateHsnSacCode(@Valid HsnSacCodeDTO hsnSacCodeDTO) throws ApplicationException {
		HsnSacCodeVO hsnSacCodeVO = new HsnSacCodeVO();
		if (ObjectUtils.isNotEmpty(hsnSacCodeDTO.getId())) {
			hsnSacCodeVO = hsnSacCodeRepo.findById(hsnSacCodeDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid HsnSacCode details"));
		} else {

			if (hsnSacCodeRepo.existsByCodeAndOrgId(hsnSacCodeDTO.getCode(), hsnSacCodeDTO.getOrgId())) {
				throw new ApplicationException("The given code already exists.");
			}
			if (hsnSacCodeRepo.existsByDescriptionAndOrgId(hsnSacCodeDTO.getDescription(), hsnSacCodeDTO.getOrgId())) {
				throw new ApplicationException("The given Descipition exists.");
			}
		}

		if (ObjectUtils.isNotEmpty(hsnSacCodeDTO.getId())) {
			HsnSacCodeVO hsn = hsnSacCodeRepo.findById(hsnSacCodeDTO.getId()).orElse(null);
			if (!hsn.getCode().equals(hsnSacCodeDTO.getCode())) {
				if (hsnSacCodeRepo.existsByCodeAndOrgId(hsnSacCodeDTO.getCode(), hsnSacCodeDTO.getOrgId())) {
					throw new ApplicationException("The given code already exists.");
				}
			}
			if (!hsn.getDescription().equals(hsnSacCodeDTO.getDescription())) {
				if (hsnSacCodeRepo.existsByDescriptionAndOrgId(hsnSacCodeDTO.getDescription(),
						hsnSacCodeDTO.getOrgId())) {
					throw new ApplicationException("The given Descipition exists.");
				}
			}
		}

		getHsnSacCodeVOFromHsnSacCodeDTO(hsnSacCodeDTO, hsnSacCodeVO);
		return hsnSacCodeRepo.save(hsnSacCodeVO);
	}

	private void getHsnSacCodeVOFromHsnSacCodeDTO(@Valid HsnSacCodeDTO hsnSacCodeDTO, HsnSacCodeVO hsnSacCodeVO) {
		hsnSacCodeVO.setOrgId(hsnSacCodeDTO.getOrgId());
		hsnSacCodeVO.setType(hsnSacCodeDTO.getType());
		hsnSacCodeVO.setActive(hsnSacCodeDTO.isActive());
		hsnSacCodeVO.setCode(hsnSacCodeDTO.getCode());
		hsnSacCodeVO.setDescription(hsnSacCodeDTO.getDescription());
		hsnSacCodeVO.setChapter(hsnSacCodeDTO.getChapter());
		hsnSacCodeVO.setChapterCode(hsnSacCodeDTO.getChapterCode());
		hsnSacCodeVO.setSubChapter(hsnSacCodeDTO.getSubChapter());
		hsnSacCodeVO.setSubChapterCode(hsnSacCodeDTO.getSubChapterCode());
		hsnSacCodeVO.setRate(hsnSacCodeDTO.getRate());
		hsnSacCodeVO.setExcempted(hsnSacCodeDTO.isExcempted());
		hsnSacCodeVO.setCreatedBy(hsnSacCodeDTO.getCreatedBy());
		hsnSacCodeVO.setUpdatedBy(hsnSacCodeDTO.getUpdatedBy());
	}

//	@Override
//	public List<HsnSacCodeVO> getHsnSacCodeByActive() {
//		return hsnSacCodeRepo.findHsnSacCodeByActive();
//
//	}
	// ExRates

	@Override
	public List<ExRatesVO> getAllExRatesById(Long id) {
		List<ExRatesVO> exRatesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ExRates Information BY Id : {}", id);
			exRatesVO = exRatesRepo.getAllExRatesById(id);
		} else {
			LOGGER.info("Successfully Received  ExRates Information For All Id.");
			exRatesVO = exRatesRepo.findAll();
		}
		return exRatesVO;
	}

	@Override
	public List<ExRatesVO> getAllExRatesByOrgId(Long orgId) {
		List<ExRatesVO> exRatesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ExRates Information BY OrgId : {}", orgId);
			exRatesVO = exRatesRepo.getAllExRatesByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  ExRates Information For All OrgId.");
			exRatesVO = exRatesRepo.findAll();
		}
		return exRatesVO;
	}

	@Override
	public ExRatesVO updateCreateExRates(@Valid ExRatesDTO exRatesDTO) throws ApplicationException {
		ExRatesVO exRatesVO = new ExRatesVO();
		if (ObjectUtils.isNotEmpty(exRatesDTO.getId())) {
			exRatesVO = exRatesRepo.findById(exRatesDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ExRates details"));
		}
		getExRatesVOFromExRatesDTO(exRatesDTO, exRatesVO);
		return exRatesRepo.save(exRatesVO);
	}

	private void getExRatesVOFromExRatesDTO(@Valid ExRatesDTO exRatesDTO, ExRatesVO exRatesVO) {
		exRatesVO.setDocDate(exRatesDTO.getDocDate());
		exRatesVO.setDocMonth(exRatesDTO.getDocMonth());
		exRatesVO.setCurrency(exRatesDTO.getCurrency());
		exRatesVO.setSellRate(exRatesDTO.getSellRate());
		exRatesVO.setBuyRate(exRatesDTO.getBuyRate());
		exRatesVO.setAvgRate(exRatesDTO.getAvgRate());
		exRatesVO.setOrgId(exRatesDTO.getOrgId());
		exRatesVO.setActive(exRatesDTO.isActive());
		exRatesVO.setCreatedBy(exRatesDTO.getCreatedBy());
		exRatesVO.setUpdatedBy(exRatesDTO.getUpdatedBy());
	}

	@Override
	public List<ExRatesVO> getExRatesByActive() {
		return exRatesRepo.findExRatesByActive();

	}

	// SubLedgerAccount

	@Override
	public List<SubLedgerAccountVO> getAllSubLedgerAccountById(Long id) {
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  SubLedgerAccount Information BY Id : {}", id);
			subLedgerAccountVO = subLedgerAccountRepo.getAllSubLedgerAccountById(id);
		} else {
			LOGGER.info("Successfully Received  SubLedgerAccount Information For All Id.");
			subLedgerAccountVO = subLedgerAccountRepo.findAll();
		}
		return subLedgerAccountVO;
	}

	@Override
	public List<SubLedgerAccountVO> getAllSubLedgerAccountByOrgId(Long orgId) {
		List<SubLedgerAccountVO> subLedgerAccountVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  SubLedgerAccount Information BY OrgId : {}", orgId);
			subLedgerAccountVO = subLedgerAccountRepo.getAllSubLedgerAccountByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  SubLedgerAccount Information For All OrgId.");
			subLedgerAccountVO = subLedgerAccountRepo.findAll();
		}
		return subLedgerAccountVO;
	}

	@Override
	public SubLedgerAccountVO updateCreateSubLedgerAccount(@Valid SubLedgerAccountDTO subLedgerAccountDTO)
			throws ApplicationException {
		SubLedgerAccountVO subLedgerAccountVO = new SubLedgerAccountVO();
		if (ObjectUtils.isNotEmpty(subLedgerAccountDTO.getId())) {
			subLedgerAccountVO = subLedgerAccountRepo.findById(subLedgerAccountDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid SubLedgerAccount details"));
		} else {
			if (subLedgerAccountRepo.existsByNewCodeAndOrgId(subLedgerAccountDTO.getNewCode(),
					subLedgerAccountDTO.getOrgId())) {
				throw new ApplicationException("The given code already exists.");
			}
			if (subLedgerAccountRepo.existsByOldCodeAndOrgId(subLedgerAccountDTO.getOldCode(),
					subLedgerAccountDTO.getOrgId())) {
				throw new ApplicationException("The given old code exists.");
			}
			if (subLedgerAccountRepo.existsBySubLedgerNameAndOrgId(subLedgerAccountDTO.getSubLedgerName(),
					subLedgerAccountDTO.getOrgId())) {
				throw new ApplicationException("The given sub ledger name exists.");
			}
		}

		if (ObjectUtils.isNotEmpty(subLedgerAccountDTO.getId())) {
			SubLedgerAccountVO sub = subLedgerAccountRepo.findById(subLedgerAccountDTO.getId()).orElse(null);
			if (!sub.getNewCode().equals(subLedgerAccountDTO.getNewCode())) {
				if (subLedgerAccountRepo.existsByNewCodeAndOrgId(subLedgerAccountDTO.getNewCode(),
						subLedgerAccountDTO.getOrgId())) {
					throw new ApplicationException("The given new code already exists.");
				}
			}
			if (!sub.getOldCode().equals(subLedgerAccountDTO.getOldCode())) {
				if (subLedgerAccountRepo.existsByOldCodeAndOrgId(subLedgerAccountDTO.getOldCode(),
						subLedgerAccountDTO.getOrgId())) {
					throw new ApplicationException("The given old code exists.");
				}
			}
			if (!sub.getSubLedgerName().equals(subLedgerAccountDTO.getSubLedgerName())) {
				if (subLedgerAccountRepo.existsBySubLedgerNameAndOrgId(subLedgerAccountDTO.getSubLedgerName(),
						subLedgerAccountDTO.getOrgId())) {
					throw new ApplicationException("The given sub ledger name exists.");
				}
			}
		}
		getSubLedgerAccountVOFromSubLedgerAccountDTO(subLedgerAccountDTO, subLedgerAccountVO);
		return subLedgerAccountRepo.save(subLedgerAccountVO);
	}

	private void getSubLedgerAccountVOFromSubLedgerAccountDTO(@Valid SubLedgerAccountDTO subLedgerAccountDTO,
			SubLedgerAccountVO subLedgerAccountVO) {
		subLedgerAccountVO.setAccountsCategory(subLedgerAccountDTO.getAccountsCategory());
		subLedgerAccountVO.setSubLedgerType(subLedgerAccountDTO.getSubLedgerType());
		subLedgerAccountVO.setSubLedgerName(subLedgerAccountDTO.getSubLedgerName());
		subLedgerAccountVO.setNewCode(subLedgerAccountDTO.getNewCode());
		subLedgerAccountVO.setOldCode(subLedgerAccountDTO.getOldCode());
		subLedgerAccountVO.setControllAccount(subLedgerAccountDTO.getControllAccount());
		subLedgerAccountVO.setCurrency(subLedgerAccountDTO.getCurrency());
		subLedgerAccountVO.setCreditDays(subLedgerAccountDTO.getCreditDays());
		subLedgerAccountVO.setCreditLimit(subLedgerAccountDTO.getCreditLimit());
		subLedgerAccountVO.setVatno(subLedgerAccountDTO.getVatno());
		subLedgerAccountVO.setStateJutisiction(subLedgerAccountDTO.getStateJutisiction());
		subLedgerAccountVO.setInvoiceType(subLedgerAccountDTO.getInvoiceType());
		subLedgerAccountVO.setOrgId(subLedgerAccountDTO.getOrgId());
		subLedgerAccountVO.setActive(subLedgerAccountDTO.isActive());
		subLedgerAccountVO.setCreatedBy(subLedgerAccountDTO.getCreatedBy());
		subLedgerAccountVO.setUpdatedBy(subLedgerAccountDTO.getUpdatedBy());

	}

	@Override
	public List<SubLedgerAccountVO> getSubLedgerAccountByActive() {
		return subLedgerAccountRepo.findSubLedgerAccountByActive();

	}
	// CostCenter

	@Override
	public List<CostCenterVO> getAllCostCenterById(Long id) {
		List<CostCenterVO> costCenterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  CostCenter Information BY Id : {}", id);
			costCenterVO = costCenterRepo.getAllCostCenterById(id);
		} else {
			LOGGER.info("Successfully Received  CostCenter Information For All Id.");
			costCenterVO = costCenterRepo.findAll();
		}
		return costCenterVO;
	}

	@Override
	public List<CostCenterVO> getAllCostCenterByOrgId(Long orgId) {
		List<CostCenterVO> costCenterVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  CostCenter Information BY OrgId : {}", orgId);
			costCenterVO = costCenterRepo.getAllCostCenterByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  CostCenter Information For All OrgId.");
			costCenterVO = costCenterRepo.findAll();
		}
		return costCenterVO;
	}

	@Override
	public CostCenterVO updateCreateCostCenter(@Valid CostCenterDTO costCenterDTO) throws ApplicationException {
		CostCenterVO costCenterVO = new CostCenterVO();
		if (ObjectUtils.isNotEmpty(costCenterDTO.getId())) {
			costCenterVO = costCenterRepo.findById(costCenterDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid CostCenter details"));
		} else {
			if (costCenterRepo.existsByValueCodeAndOrgId(costCenterDTO.getValueCode(), costCenterDTO.getOrgId())) {
				throw new ApplicationException("The given value code already exists.");
			}
			if (costCenterRepo.existsByValueDescriptionAndOrgId(costCenterDTO.getValueDescription(),
					costCenterDTO.getOrgId())) {
				throw new ApplicationException("The given value descripition already exists.");
			}
		}
		if (ObjectUtils.isNotEmpty(costCenterDTO.getId())) {
			CostCenterVO cost = costCenterRepo.findById(costCenterDTO.getId()).orElse(null);
			if (!cost.getValueCode().equals(costCenterDTO.getValueCode())) {
				if (costCenterRepo.existsByValueCodeAndOrgId(costCenterDTO.getValueCode(), costCenterDTO.getOrgId())) {
					throw new ApplicationException("The given value code already exists.");
				}
			}
			if (!cost.getValueDescription().equals(costCenterDTO.getValueDescription())) {
				if (costCenterRepo.existsByValueDescriptionAndOrgId(costCenterDTO.getValueDescription(),
						costCenterDTO.getOrgId())) {
					throw new ApplicationException("The given value descripition already exists.");
				}
			}
		}
		getCostCenterVOFromCostCenterDTO(costCenterDTO, costCenterVO);
		return costCenterRepo.save(costCenterVO);
	}

	private void getCostCenterVOFromCostCenterDTO(@Valid CostCenterDTO costCenterDTO, CostCenterVO costCenterVO) {
		costCenterVO.setDimensionType(costCenterDTO.getDimensionType());
		costCenterVO.setValueCode(costCenterDTO.getValueCode());
		costCenterVO.setValueDescription(costCenterDTO.getValueDescription());
		costCenterVO.setOrgId(costCenterDTO.getOrgId());
		costCenterVO.setCreatedBy(costCenterDTO.getCreatedBy());
		costCenterVO.setUpdatedBy(costCenterDTO.getUpdatedBy());
		costCenterVO.setActive(costCenterDTO.isActive());

	}

	@Override
	public List<CostCenterVO> getCostCenterByActive() {
		return costCenterRepo.findCostCenterByActive();

	}

	// ChequeBook
	@Override
	public List<ChequeBookVO> getAllChequeBookById(Long id) {
		List<ChequeBookVO> chequeBookVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ChequeBook Information BY Id : {}", id);
			chequeBookVO = chequeBookRepo.getAllChequeBookById(id);
		} else {
			LOGGER.info("Successfully Received  ChequeBook Information For All Id.");
			chequeBookVO = chequeBookRepo.findAll();
		}
		return chequeBookVO;
	}

	@Override
	public List<ChequeBookVO> getAllChequeBookByOrgId(Long orgId) {
		List<ChequeBookVO> chequeBookVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ChequeBook Information BY OrgId : {}", orgId);
			chequeBookVO = chequeBookRepo.getAllChequeBookByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ChequeBook Information For All OrgId.");
			chequeBookVO = chequeBookRepo.findAll();
		}
		return chequeBookVO;
	}

//	@Override
//	public ChequeBookVO updateCreateChequeBook(@Valid ChequeBookDTO chequeBookDTO) throws ApplicationException {
//		ChequeBookVO chequeBookVO = new ChequeBookVO();
//		if (ObjectUtils.isNotEmpty(chequeBookDTO.getId())) {
//			chequeBookVO = chequeBookRepo.findById(chequeBookDTO.getId())
//					.orElseThrow(() -> new ApplicationException("Invalid ChequeBook details"));
//		}
//
//		List<ChequeBookDetailsVO> chequeBookDetailsVOs = new ArrayList<>();
//		if (chequeBookDTO.getChequeBookDetailsDTO() != null) {
//			for (ChequeBookDetailsDTO chequeBookDetailsDTO : chequeBookDTO.getChequeBookDetailsDTO()) 
//			{
//				ChequeBookDetailsVO chequeBookDetailsVO;
//				if (chequeBookDetailsDTO.getId() != null & ObjectUtils.isEmpty(chequeBookDetailsDTO.getId())) {
//					chequeBookDetailsVO = chequeBookDetailsRepo.findById(chequeBookDetailsDTO.getId()).orElse(new ChequeBookDetailsVO());
//				} else {
//					chequeBookDetailsVO = new ChequeBookDetailsVO();
//				}
//				chequeBookDetailsVO.setChequeNo(chequeBookDetailsDTO.getChequeNo());
//				chequeBookDetailsVO.setStatus(chequeBookDetailsDTO.getStatus());
//				chequeBookDetailsVO.setCancelled(chequeBookDetailsDTO.getCancelled());
//				chequeBookDetailsVO.setChequeBookVO(chequeBookVO);
//				chequeBookDetailsVOs.add(chequeBookDetailsVO);
//			}
//			chequeBookVO.setChequeBookDetailsVO(chequeBookDetailsVOs);
//		}
//		
//		getChequeBookVOFromChequeBookDTO(chequeBookDTO, chequeBookVO);
//		return chequeBookRepo.save(chequeBookVO);
//	}
//
//	private void getChequeBookVOFromChequeBookDTO(@Valid ChequeBookDTO chequeBookDTO, ChequeBookVO chequeBookVO) {
//		chequeBookVO.setBranch(chequeBookDTO.getBranch());
//		chequeBookVO.setChequeId(chequeBookDTO.getChequeId());
//		chequeBookVO.setBank(chequeBookDTO.getBank());
//		chequeBookVO.setCheckPrefix(chequeBookDTO.getCheckPrefix());
//		chequeBookVO.setCheckStartNo(chequeBookDTO.getCheckStartNo());
//		chequeBookVO.setNoOfChequeLeaves(chequeBookDTO.getNoOfChequeLeaves());
//		chequeBookVO.setOrgId(chequeBookDTO.getOrgId());
//		chequeBookVO.setActive(chequeBookDTO.isActive());
//		chequeBookVO.setCreatedBy(chequeBookDTO.getCreatedBy());
//		chequeBookVO.setUpdatedBy(chequeBookDTO.getUpdatedBy());
//
//	}
	
	@Override
	public ChequeBookVO updateCreateChequeBook(@Valid ChequeBookDTO chequeBookDTO) throws ApplicationException {
	    // Fetch existing ChequeBookVO if an ID is provided
	    ChequeBookVO chequeBookVO = Optional.ofNullable(chequeBookDTO.getId())
	            .flatMap(id -> chequeBookRepo.findById(id))
	            .orElseGet(ChequeBookVO::new);

	    // Process and convert ChequeBookDetailsDTO to ChequeBookDetailsVO
	    List<ChequeBookDetailsVO> chequeBookDetailsVOs = chequeBookDTO.getChequeBookDetailsDTO().stream()
	            .map(dto -> {
	                // Find existing ChequeBookDetailsVO if ID is provided, else create a new one
	                ChequeBookDetailsVO detailsVO = Optional.ofNullable(dto.getId())
	                        .flatMap(id -> chequeBookDetailsRepo.findById(id))
	                        .orElse(new ChequeBookDetailsVO());

	                // Update detailsVO properties
	                detailsVO.setChequeNo(dto.getChequeNo());
	                detailsVO.setStatus(dto.getStatus());
	                detailsVO.setCancelled(dto.getCancelled());
	                detailsVO.setChequeBookVO(chequeBookVO);

	                return detailsVO;
	            })
	            .collect(Collectors.toList());

	    // Update ChequeBookVO properties
	    getChequeBookVOFromChequeBookDTO(chequeBookDTO, chequeBookVO);
	    chequeBookVO.setChequeBookDetailsVO(chequeBookDetailsVOs);

	    // Save and return updated ChequeBookVO
	    return chequeBookRepo.save(chequeBookVO);
	}

	private void getChequeBookVOFromChequeBookDTO(@Valid ChequeBookDTO chequeBookDTO, ChequeBookVO chequeBookVO) {
	    chequeBookVO.setBranch(chequeBookDTO.getBranch());
	    chequeBookVO.setChequeId(chequeBookDTO.getChequeId());
	    chequeBookVO.setBank(chequeBookDTO.getBank());
	    chequeBookVO.setCheckPrefix(chequeBookDTO.getCheckPrefix());
	    chequeBookVO.setCheckStartNo(chequeBookDTO.getCheckStartNo());
	    chequeBookVO.setNoOfChequeLeaves(chequeBookDTO.getNoOfChequeLeaves());
	    chequeBookVO.setOrgId(chequeBookDTO.getOrgId());
	    chequeBookVO.setActive(chequeBookDTO.isActive());
	    chequeBookVO.setCreatedBy(chequeBookDTO.getCreatedBy());
	    chequeBookVO.setUpdatedBy(chequeBookDTO.getUpdatedBy());
	}



	@Override
	public List<ChequeBookVO> getChequeBookByActive() {
		return chequeBookRepo.findChequeBookByActive();

	}

	// ChargeTypeRequest
	@Override
	public List<ChargeTypeRequestVO> getAllChargeTypeRequestById(Long id) {
		List<ChargeTypeRequestVO> chargeTypeRequestVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ChargeTypeRequest Information BY Id : {}", id);
			chargeTypeRequestVO = chargeTypeRequestRepo.getAllChargeTypeRequestById(id);
		} else {
			LOGGER.info("Successfully Received  ChargeTypeRequest Information For All Id.");
			chargeTypeRequestVO = chargeTypeRequestRepo.findAll();
		}
		return chargeTypeRequestVO;
	}

	@Override
	public List<ChargeTypeRequestVO> getAllChargeTypeRequestByOrgId(Long orgId) {
		List<ChargeTypeRequestVO> chargeTypeRequestVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  ChargeTypeRequest Information BY OrgId : {}", orgId);
			chargeTypeRequestVO = chargeTypeRequestRepo.getAllChargeTypeRequestByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received ChargeTypeRequest Information For All OrgId.");
			chargeTypeRequestVO = chargeTypeRequestRepo.findAll();
		}
		return chargeTypeRequestVO;
	}

	@Override
	public ChargeTypeRequestVO updateCreateChargeTypeRequest(@Valid ChargeTypeRequestDTO chargeTypeRequestDTO)
			throws ApplicationException {
		ChargeTypeRequestVO chargeTypeRequestVO = new ChargeTypeRequestVO();
		if (ObjectUtils.isNotEmpty(chargeTypeRequestDTO.getId())) {
			chargeTypeRequestVO = chargeTypeRequestRepo.findById(chargeTypeRequestDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ChargeTypeRequest details"));
		} else {
			if (chargeTypeRequestRepo.existsByChargeDescriptionAndOrgId(chargeTypeRequestDTO.getChargeDescription(),
					chargeTypeRequestDTO.getOrgId())) {
				throw new ApplicationException("The given charge description already exists.");
			}
			if (chargeTypeRequestRepo.existsByChargeCodeAndOrgId(chargeTypeRequestDTO.getChargeCode(),
					chargeTypeRequestDTO.getOrgId())) {
				throw new ApplicationException("The given charge code already exists.");
			}
		}

		if (ObjectUtils.isNotEmpty(chargeTypeRequestDTO.getId())) {
			ChargeTypeRequestVO charge = chargeTypeRequestRepo.findById(chargeTypeRequestDTO.getId()).orElse(null);
			if (!charge.getChargeDescription().equals(chargeTypeRequestDTO.getChargeDescription())) {
				if (chargeTypeRequestRepo.existsByChargeDescriptionAndOrgId(chargeTypeRequestDTO.getChargeDescription(),
						chargeTypeRequestDTO.getOrgId())) {
					throw new ApplicationException("The given charge descripition already exists.");
				}
			}
			if (!charge.getChargeCode().equals(chargeTypeRequestDTO.getChargeCode())) {
				if (chargeTypeRequestRepo.existsByChargeCodeAndOrgId(chargeTypeRequestDTO.getChargeCode(),
						chargeTypeRequestDTO.getOrgId())) {
					throw new ApplicationException("The given charge code already exists.");
				}
			}
		}

		getChargeTypeRequestVOFromChargeTypeRequestDTO(chargeTypeRequestDTO, chargeTypeRequestVO);
		return chargeTypeRequestRepo.save(chargeTypeRequestVO);
	}

	private void getChargeTypeRequestVOFromChargeTypeRequestDTO(@Valid ChargeTypeRequestDTO chargeTypeRequestDTO,
			ChargeTypeRequestVO chargeTypeRequestVO) {
		chargeTypeRequestVO.setChargeType(chargeTypeRequestDTO.getChargeType());
		chargeTypeRequestVO.setChargeCode(chargeTypeRequestDTO.getChargeCode());
		chargeTypeRequestVO.setChargeDescription(chargeTypeRequestDTO.getChargeDescription());
		chargeTypeRequestVO.setProduct(chargeTypeRequestDTO.getProduct());
		chargeTypeRequestVO.setLocalChargeDescripition(chargeTypeRequestDTO.getLocalChargeDescripition());
		chargeTypeRequestVO.setServiceAccountCode(chargeTypeRequestDTO.getServiceAccountCode());
		chargeTypeRequestVO.setSacDescripition(chargeTypeRequestDTO.getSacDescripition());
		chargeTypeRequestVO.setSalesAccount(chargeTypeRequestDTO.getSalesAccount());
		chargeTypeRequestVO.setPurchaseAccount(chargeTypeRequestDTO.getPurchaseAccount());
		chargeTypeRequestVO.setTaxable(chargeTypeRequestDTO.getTaxable());
		chargeTypeRequestVO.setTaxType(chargeTypeRequestDTO.getTaxType());
		chargeTypeRequestVO.setCcFeeApplicable(chargeTypeRequestDTO.getCcFeeApplicable());
		chargeTypeRequestVO.setTaxablePercentage(chargeTypeRequestDTO.getTaxablePercentage());
		chargeTypeRequestVO.setCcJob(chargeTypeRequestDTO.getCcJob());
		chargeTypeRequestVO.setGovtSac(chargeTypeRequestDTO.getGovtSac());
		chargeTypeRequestVO.setExcempted(chargeTypeRequestDTO.getExcempted());
		chargeTypeRequestVO.setGstControl(chargeTypeRequestDTO.getGstControl());
		chargeTypeRequestVO.setGstTax(chargeTypeRequestDTO.getGstTax());
		chargeTypeRequestVO.setService(chargeTypeRequestDTO.getService());
		chargeTypeRequestVO.setType(chargeTypeRequestDTO.getType());
		chargeTypeRequestVO.setSalesLedger(chargeTypeRequestDTO.getSalesLedger());
		chargeTypeRequestVO.setPurchaseLedger(chargeTypeRequestDTO.getPurchaseLedger());
		chargeTypeRequestVO.setEffromDate(chargeTypeRequestDTO.getEffromDate());
		chargeTypeRequestVO.setEftoDate(chargeTypeRequestDTO.getEftoDate());
		chargeTypeRequestVO.setActive(chargeTypeRequestDTO.isActive());
		chargeTypeRequestVO.setOrgId(chargeTypeRequestDTO.getOrgId());
		chargeTypeRequestVO.setCreatedBy(chargeTypeRequestDTO.getCreatedBy());
		chargeTypeRequestVO.setUpdatedBy(chargeTypeRequestDTO.getUpdatedBy());

	}

	@Override
	public List<ChargeTypeRequestVO> getChargeTypeRequestByActive() {
		return chargeTypeRequestRepo.findChargeTypeRequestByActive();

	}

	// ListOfValues

	@Override
	public List<ListOfValuesVO> getListOfValuesById(Long id) {
		List<ListOfValuesVO> listOfValuesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  ListOfValues BY Id : {}", id);
			listOfValuesVO = listOfValuesRepo.getListOfValuesById(id);
		} else {
			LOGGER.info("Successfully Received  ListOfValues For All Id.");
			listOfValuesVO = listOfValuesRepo.findAll();
		}
		return listOfValuesVO;
	}

	@Override
	public List<ListOfValuesVO> getListOfValuesByOrgId(Long orgid) {
		List<ListOfValuesVO> listOfValuesVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgid)) {
			LOGGER.info("Successfully Received  ListOfValues BY OrgId : {}", orgid);
			listOfValuesVO = listOfValuesRepo.getListOfValuesByOrgId(orgid);
		} else {
			LOGGER.info("Successfully Received  ListOfValues For All OrgId.");
			listOfValuesVO = listOfValuesRepo.findAll();
		}
		return listOfValuesVO;
	}

	@Override
	public ListOfValuesVO updateCreateListOfValues(@Valid ListOfValuesDTO listOfValuesDTO) throws ApplicationException {
		ListOfValuesVO listOfValuesVO = new ListOfValuesVO();
		if (ObjectUtils.isNotEmpty(listOfValuesDTO.getId())) {
			listOfValuesVO = listOfValuesRepo.findById(listOfValuesDTO.getId())
					.orElseThrow(() -> new ApplicationException("Invalid ListOfValues details"));
		}

		else {
			if (listOfValuesRepo.existsByListCodeAndOrgId(listOfValuesDTO.getListCode(), listOfValuesDTO.getOrgId())) {
				throw new ApplicationException("ListCode already Exists");
			}
			if (listOfValuesRepo.existsByListDescriptionAndOrgId(listOfValuesDTO.getListDescription(),
					listOfValuesDTO.getOrgId())) {
				throw new ApplicationException("ListDescription already Exists");
			}

		}

		if (ObjectUtils.isNotEmpty(listOfValuesDTO.getId())) {
			ListOfValuesVO listOfValues = listOfValuesRepo.findById(listOfValuesDTO.getId()).orElse(null);
			if (!listOfValues.getListCode().equals(listOfValuesDTO.getListCode())) {
				if (listOfValuesRepo.existsByListCodeAndOrgId(listOfValuesDTO.getListCode(),
						listOfValuesDTO.getOrgId())) {
					throw new ApplicationException("ListCode already Exists");
				}
				if (!listOfValues.getListDescription().equals(listOfValuesDTO.getListDescription())) {
					if (listOfValuesRepo.existsByListDescriptionAndOrgId(listOfValuesDTO.getListDescription(),
							listOfValuesDTO.getOrgId())) {
						throw new ApplicationException("ListDescription already Exists");
					}

				}
			}
		}
		getListOfValuesVOFromTypesOfValuesDTO(listOfValuesDTO, listOfValuesVO);
		listOfValuesVO = listOfValuesRepo.save(listOfValuesVO);

		List<ListOfValues1VO> listOfValues1VOList = listOfValues1Repo.findBylistOfValuesVO(listOfValuesVO);
		listOfValues1Repo.deleteAll(listOfValues1VOList);

		List<ListOfValues1VO> listOfValues1VOs = new ArrayList<>();
		if (listOfValuesDTO.getListOfValues1DTO() != null) {
			for (ListOfValues1DTO listOfValues1DTO : listOfValuesDTO.getListOfValues1DTO()) {

				ListOfValues1VO listOfValues1VO = new ListOfValues1VO();
				listOfValues1VO.setValueCode(listOfValues1DTO.getValueCode());
				listOfValues1VO.setSNo(listOfValues1DTO.getSNo());
				listOfValues1VO.setValueDescription(listOfValues1DTO.getValueDescription());
				listOfValues1VO.setActive(listOfValues1DTO.isActive());
				listOfValues1VO.setListOfValuesVO(listOfValuesVO);
				listOfValues1VOs.add(listOfValues1VO);

			}
		}

		listOfValuesVO.setListOfValues1VO(listOfValues1VOs);
		return listOfValuesRepo.save(listOfValuesVO);

	}

	private void getListOfValuesVOFromTypesOfValuesDTO(@Valid ListOfValuesDTO listOfValuesDTO,
			ListOfValuesVO listOfValuesVO) {
		listOfValuesVO.setListCode(listOfValuesDTO.getListCode());
		listOfValuesVO.setOrgId(listOfValuesDTO.getOrgId());
		listOfValuesVO.setListDescription(listOfValuesDTO.getListDescription());
		listOfValuesVO.setActive(listOfValuesDTO.isActive());
		listOfValuesVO.setUpdatedBy(listOfValuesDTO.getUpdatedBy());

		listOfValuesVO.setCreatedBy(listOfValuesDTO.getCreatedBy());

	}

}
