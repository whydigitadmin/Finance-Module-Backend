package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ChargerCostInvoiceDTO;
import com.base.basesetup.dto.CostInvSummaryDTO;
import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.dto.TdsCostInvoiceDTO;
import com.base.basesetup.entity.ChargerCostInvoiceVO;
import com.base.basesetup.entity.CostInvSummaryVO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.DocumentTypeMappingDetailsVO;
import com.base.basesetup.entity.TdsCostInvoiceVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.ChargerCostInvoiceRepo;
import com.base.basesetup.repo.CostInvSummaryRepo;
import com.base.basesetup.repo.CostInvoiceRepo;
import com.base.basesetup.repo.DocumentTypeMappingDetailsRepo;
import com.base.basesetup.repo.TdsCostInvoiceRepo;

@Service
public class CostInvoiceServiceImpl implements CostInvoiceService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CostInvoiceServiceImpl.class);

	@Autowired
	CostInvoiceRepo costInvoiceRepo;

	@Autowired
	TdsCostInvoiceRepo tdsCostInvoiceRepo;

	@Autowired
	ChargerCostInvoiceRepo chargerCostInvoiceRepo;

	@Autowired
	CostInvSummaryRepo costInvSummaryRepo;
	@Autowired
	DocumentTypeMappingDetailsRepo documentTypeMappingDetailsRepo;

	// costInvoice

	@Override
	public List<CostInvoiceVO> getAllCostInvoiceByOrgId(Long orgId) {
		List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(orgId)) {
			LOGGER.info("Successfully Received  CostInvoice BY OrgId : {}", orgId);
			costInvoiceVO = costInvoiceRepo.getAllCostInvoiceByOrgId(orgId);
		} else {
			LOGGER.info("Successfully Received  CostInvoice For All OrgId.");
			costInvoiceVO = costInvoiceRepo.findAll();
		}
		return costInvoiceVO;
	}

	@Override
	public List<CostInvoiceVO> getAllCostInvoiceById(Long id) {
		List<CostInvoiceVO> costInvoiceVO = new ArrayList<>();
		if (ObjectUtils.isNotEmpty(id)) {
			LOGGER.info("Successfully Received  CostInvoice BY Id : {}", id);
			costInvoiceVO = costInvoiceRepo.getAllCostInvoiceById(id);
		} else {
			LOGGER.info("Successfully Received CostInvoice For All Id.");
			costInvoiceVO = costInvoiceRepo.findAll();
		}
		return costInvoiceVO;
	}

	@Override
	public List<CostInvoiceVO> getCostInvoiceByActive() {
		return costInvoiceRepo.findCostInvoiceByActive();
	}

	@Override
	public Map<String, Object> updateCreateCostInvoice(@Valid CostInvoiceDTO costInvoiceDTO)
			throws ApplicationException {
		String screenCode = "cI";
		CostInvoiceVO costInvoiceVO;
		String message = null;

		if (ObjectUtils.isEmpty(costInvoiceDTO.getId())) {

			costInvoiceVO = new CostInvoiceVO();

			// GETDOCID API
			String docId = costInvoiceRepo.getCostInvoiceDocId(costInvoiceDTO.getOrgId(), costInvoiceDTO.getFinYear(),
					costInvoiceDTO.getBranchCode(), screenCode);
			costInvoiceVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(costInvoiceDTO.getOrgId(),
							costInvoiceDTO.getFinYear(), costInvoiceDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			costInvoiceVO.setCreatedBy(costInvoiceDTO.getCreatedBy());
			costInvoiceVO.setUpdatedBy(costInvoiceDTO.getCreatedBy());

			message = "CostInvoice Creation Successfully";
		}

		else {
			costInvoiceVO = costInvoiceRepo.findById(costInvoiceDTO.getId()).orElseThrow(
					() -> new ApplicationException("Cost Invoice Not Found with id: " + costInvoiceDTO.getId()));
			costInvoiceVO.setUpdatedBy(costInvoiceDTO.getCreatedBy());

			message = "CostInvoice Updation Successfully";
		}

		costInvoiceVO = getCostInvoiceVOFromCostInvoiceDTO(costInvoiceVO, costInvoiceDTO);
		costInvoiceRepo.save(costInvoiceVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("costInvoiceVO", costInvoiceVO);
		return response;

	}

	private CostInvoiceVO getCostInvoiceVOFromCostInvoiceDTO(CostInvoiceVO costInvoiceVO,
			@Valid CostInvoiceDTO costInvoiceDTO) {

		costInvoiceVO.setMode(costInvoiceDTO.getMode());
		costInvoiceVO.setProduct(costInvoiceDTO.getProduct());
		costInvoiceVO.setPurVoucherNo(costInvoiceDTO.getPurVoucherNo());
		costInvoiceVO.setPurVoucherDate(costInvoiceDTO.getPurVoucherDate());
		costInvoiceVO.setCostInvoiceNo(costInvoiceDTO.getCostInvoiceNo());
		costInvoiceVO.setCostInvoiceDate(costInvoiceDTO.getCostInvoiceDate());
		costInvoiceVO.setSupplierBillNo(costInvoiceDTO.getSupplierBillNo());
		costInvoiceVO.setSupplierType(costInvoiceDTO.getSupplierType());
		costInvoiceVO.setSupplierCode(costInvoiceDTO.getSupplierCode());
		costInvoiceVO.setCreditDays(costInvoiceDTO.getCreditDays());
		costInvoiceVO.setDueDate(costInvoiceDTO.getDueDate());
		costInvoiceVO.setSupplierName(costInvoiceDTO.getSupplierName());
		costInvoiceVO.setSupplierPlace(costInvoiceDTO.getSupplierPlace());
		costInvoiceVO.setCurrency(costInvoiceDTO.getCurrency());
		costInvoiceVO.setExRate(costInvoiceDTO.getExRate());
		costInvoiceVO.setSupplierGstIn(costInvoiceDTO.getSupplierGstIn());
		costInvoiceVO.setSupplierGstInCode(costInvoiceDTO.getSupplierGstInCode());
		costInvoiceVO.setRemarks(costInvoiceDTO.getRemarks());
		costInvoiceVO.setAddress(costInvoiceDTO.getAddress());
		costInvoiceVO.setOtherInfo(costInvoiceDTO.getOtherInfo());
		costInvoiceVO.setShipperRefNo(costInvoiceDTO.getShipperRefNo());
		costInvoiceVO.setGstType(costInvoiceDTO.getGstType());
		costInvoiceVO.setOrgId(costInvoiceDTO.getOrgId());
		costInvoiceVO.setCreatedBy(costInvoiceDTO.getCreatedBy());
		costInvoiceVO.setCancelRemarks(costInvoiceDTO.getCancelRemarks());
		costInvoiceVO.setBranch(costInvoiceDTO.getBranch());
		costInvoiceVO.setBranchCode(costInvoiceDTO.getBranchCode());
		costInvoiceVO.setCustomer(costInvoiceDTO.getCustomer());
		costInvoiceVO.setClient(costInvoiceDTO.getClient());
		costInvoiceVO.setFinYear(costInvoiceDTO.getFinYear());
		costInvoiceVO.setIpNo(costInvoiceDTO.getIpNo());
		costInvoiceVO.setLatitude(costInvoiceDTO.getLatitude());
		costInvoiceVO.setPayment(costInvoiceDTO.getPayment());
		costInvoiceVO.setAccuralid(costInvoiceDTO.getAccuralid());
		costInvoiceVO.setUtrRef(costInvoiceDTO.getUtrRef());
		costInvoiceVO.setCostType(costInvoiceDTO.getCostType());

		if (costInvoiceDTO.getId() != null) {

			List<ChargerCostInvoiceVO> chargerCostInvoiceVOs = chargerCostInvoiceRepo
					.findByCostInvoiceVO(costInvoiceVO);
			chargerCostInvoiceRepo.deleteAll(chargerCostInvoiceVOs);

			List<TdsCostInvoiceVO> tdsCostInvoiceVOs = tdsCostInvoiceRepo.findByCostInvoiceVO(costInvoiceVO);
			tdsCostInvoiceRepo.deleteAll(tdsCostInvoiceVOs);

			List<CostInvSummaryVO> costInvSummaryVOs = costInvSummaryRepo.findByCostInvoiceVO(costInvoiceVO);
			costInvSummaryRepo.deleteAll(costInvSummaryVOs);
		}

		List<ChargerCostInvoiceVO> chargerCostInvoiceVOs = new ArrayList<>();
		for (ChargerCostInvoiceDTO chargerCostInvoiceDTO : costInvoiceDTO.getChargerCostInvoiceDTO()) {
			ChargerCostInvoiceVO chargerCostInvoiceVO = new ChargerCostInvoiceVO();

			chargerCostInvoiceVO.setHouseNo(chargerCostInvoiceDTO.getHouseNo());
			chargerCostInvoiceVO.setJobNo(chargerCostInvoiceDTO.getJobNo());
			chargerCostInvoiceVO.setSubJobNo(chargerCostInvoiceDTO.getSubJobNo());
			chargerCostInvoiceVO.setChargeName(chargerCostInvoiceDTO.getChargeName());
			chargerCostInvoiceVO.setChargeCode(chargerCostInvoiceDTO.getChargeCode());
			chargerCostInvoiceVO.setChargeLedger(chargerCostInvoiceDTO.getChargeLedger());
			chargerCostInvoiceVO.setGsac(chargerCostInvoiceDTO.getGsac());
			chargerCostInvoiceVO.setContType(chargerCostInvoiceDTO.getContType());
			chargerCostInvoiceVO.setCurrency(chargerCostInvoiceDTO.getCurrency());
			chargerCostInvoiceVO.setExRate(chargerCostInvoiceDTO.getExRate());
			chargerCostInvoiceVO.setGst(chargerCostInvoiceDTO.getGst());
			chargerCostInvoiceVO.setFcAmt(chargerCostInvoiceDTO.getFcAmt());
			chargerCostInvoiceVO.setLcAmt(chargerCostInvoiceDTO.getLcAmt());
			chargerCostInvoiceVO.setBillAmt(chargerCostInvoiceDTO.getBillAmt());

			chargerCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
			chargerCostInvoiceVOs.add(chargerCostInvoiceVO);
		}
		costInvoiceVO.setChargerCostInvoiceVO(chargerCostInvoiceVOs);

		List<TdsCostInvoiceVO> tdsCostInvoiceVOs = new ArrayList<>();
		for (TdsCostInvoiceDTO tdsCostInvoiceDTO : costInvoiceDTO.getTdsCostInvoiceDTO()) {
			TdsCostInvoiceVO tdsCostInvoiceVO = new TdsCostInvoiceVO();

			tdsCostInvoiceVO.setTdsWithHolding(tdsCostInvoiceDTO.getTdsWithHolding());
			tdsCostInvoiceVO.setTdsWithHoldingPer(tdsCostInvoiceDTO.getTdsWithHoldingPer());
			tdsCostInvoiceVO.setSection(tdsCostInvoiceDTO.getSection());
			tdsCostInvoiceVO.setTotTdsWhAmnt(tdsCostInvoiceDTO.getTotTdsWhAmnt());

			tdsCostInvoiceVO.setCostInvoiceVO(costInvoiceVO);
			tdsCostInvoiceVOs.add(tdsCostInvoiceVO);
		}
		costInvoiceVO.setTdsCostInvoiceVO(tdsCostInvoiceVOs);

		List<CostInvSummaryVO> costInvSummaryVOs = new ArrayList<>();
		for (CostInvSummaryDTO costInvSummaryDTO : costInvoiceDTO.getCostInvSummaryDTO()) {
			CostInvSummaryVO costInvSummaryVO = new CostInvSummaryVO();

			costInvSummaryVO.setTotChargesBillCurrAmt(costInvSummaryDTO.getTotChargesBillCurrAmt());
			costInvSummaryVO.setTotChargesLcAmt(costInvSummaryDTO.getTotChargesLcAmt());
			costInvSummaryVO.setActBillCurrAmt(costInvSummaryDTO.getActBillCurrAmt());
			costInvSummaryVO.setActBillLcAmt(costInvSummaryDTO.getActBillLcAmt());
			costInvSummaryVO.setNetBillCurrAmt(costInvSummaryDTO.getNetBillCurrAmt());
			costInvSummaryVO.setNetBillLcAmt(costInvSummaryDTO.getNetBillLcAmt());
			costInvSummaryVO.setRoundOff(costInvSummaryDTO.getRoundOff());
			costInvSummaryVO.setGstInputLcAmt(costInvSummaryDTO.getGstInputLcAmt());

			costInvSummaryVO.setCostInvoiceVO(costInvoiceVO);
			costInvSummaryVOs.add(costInvSummaryVO);
		}
		costInvoiceVO.setCostInvSummaryVO(costInvSummaryVOs);

		return costInvoiceVO;

	}

	@Override
	public CostInvoiceVO getCostInvoiceByDocId(Long orgId, String docId) {
		return costInvoiceRepo.findAllCostInvoiceByDocId(orgId, docId);
	}

	@Override
	public String getCostInvoiceDocId(Long orgId, String finYear, String branch, String branchCode) {
		String ScreenCode = "CI";
		String result = costInvoiceRepo.getCostInvoiceDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

}
