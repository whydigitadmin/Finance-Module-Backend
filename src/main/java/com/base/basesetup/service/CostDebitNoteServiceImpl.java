package com.base.basesetup.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CostDebitChargesDTO;
import com.base.basesetup.dto.CostDebitNoteDTO;
import com.base.basesetup.dto.CostDebitNoteGstDTO;
import com.base.basesetup.dto.CostDebitNoteSummaryDTO;
import com.base.basesetup.dto.CostDebitNoteTaxPrtculDTO;
import com.base.basesetup.entity.CostDebitChargesVO;
import com.base.basesetup.entity.CostDebitNoteGstVO;
import com.base.basesetup.entity.CostDebitNoteSummaryVO;
import com.base.basesetup.entity.CostDebitNoteTaxPrtculVO;
import com.base.basesetup.entity.CostDebitNoteVO;
import com.base.basesetup.entity.DocumentTypeMappingDetailsVO;
import com.base.basesetup.exception.ApplicationException;
import com.base.basesetup.repo.CostDebitChargesRepo;
import com.base.basesetup.repo.CostDebitNoteGstRepo;
import com.base.basesetup.repo.CostDebitNoteRepo;
import com.base.basesetup.repo.CostDebitNoteSummaryRepo;
import com.base.basesetup.repo.CostDebitNoteTaxPrtculRepo;
import com.base.basesetup.repo.DocumentTypeMappingDetailsRepo;

@Service
public class CostDebitNoteServiceImpl implements CostDebitNoteService {

	public static final Logger LOGGER = LoggerFactory.getLogger(CostDebitNoteServiceImpl.class);

	@Autowired
	CostDebitNoteRepo costDebitNoteRepo;

	@Autowired
	CostDebitChargesRepo costDebitChargesRepo;

	@Autowired
	CostDebitNoteGstRepo costDebitNoteGstRepo;

	@Autowired
	CostDebitNoteTaxPrtculRepo costDebitNoteTaxPrtculRepo;

	@Autowired
	CostDebitNoteSummaryRepo costDebitNoteSummaryRepo;

	@Autowired
	DocumentTypeMappingDetailsRepo documentTypeMappingDetailsRepo;

	@Override
	public Map<String, Object> updateCreateCostDebitNote(@Valid CostDebitNoteDTO costDebitNoteDTO)
			throws ApplicationException {

		String message = null;
		String screenCode = "CDN";
		CostDebitNoteVO costDebitNoteVO;

		if (ObjectUtils.isEmpty(costDebitNoteDTO.getId())) {
			costDebitNoteVO = new CostDebitNoteVO();

			// GETDOCID API
			String docId = costDebitNoteRepo.getCostDebitNoteDocId(costDebitNoteDTO.getOrgId(),
					costDebitNoteDTO.getFinYear(), costDebitNoteDTO.getBranchCode(), screenCode);
			costDebitNoteVO.setDocId(docId);

			// GETDOCID LASTNO +1
			DocumentTypeMappingDetailsVO documentTypeMappingDetailsVO = documentTypeMappingDetailsRepo
					.findByOrgIdAndFinYearAndBranchCodeAndScreenCode(costDebitNoteDTO.getOrgId(),
							costDebitNoteDTO.getFinYear(), costDebitNoteDTO.getBranchCode(), screenCode);
			documentTypeMappingDetailsVO.setLastno(documentTypeMappingDetailsVO.getLastno() + 1);
			documentTypeMappingDetailsRepo.save(documentTypeMappingDetailsVO);

			costDebitNoteVO.setCreatedBy(costDebitNoteDTO.getCreatedBy());
			costDebitNoteVO.setModifyBy(costDebitNoteDTO.getCreatedBy());

			message = "CostDebitNote Creation Successfully";

		} else {
			costDebitNoteVO = costDebitNoteRepo.findById(costDebitNoteDTO.getId()).orElseThrow(
					() -> new ApplicationException("Cost DebitNote Not Found with id: " + costDebitNoteDTO.getId()));
			costDebitNoteVO.setModifyBy(costDebitNoteDTO.getCreatedBy());

			message = "CostDebitNote Updation Successfully";
		}

		costDebitNoteVO = getCostDebitNoteVOFromCostDebitNoteDTO(costDebitNoteVO, costDebitNoteDTO);
		costDebitNoteRepo.save(costDebitNoteVO);

		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("costDebitNoteVO", costDebitNoteVO);
		return response;
	}

	private CostDebitNoteVO getCostDebitNoteVOFromCostDebitNoteDTO(CostDebitNoteVO costDebitNoteVO,
			@Valid CostDebitNoteDTO costDebitNoteDTO) {

		// Set individual fields from DTO to VO
		costDebitNoteVO.setBranch(costDebitNoteDTO.getBranch());
		costDebitNoteVO.setBranchCode(costDebitNoteDTO.getBranchCode());
		costDebitNoteVO.setCreatedBy(costDebitNoteDTO.getCreatedBy());
		costDebitNoteVO.setCancelRemarks(costDebitNoteDTO.getCancelRemarks());
		costDebitNoteVO.setFinYear(costDebitNoteDTO.getFinYear());
		costDebitNoteVO.setIpNo(costDebitNoteDTO.getIpNo());
		costDebitNoteVO.setLatitude(costDebitNoteDTO.getLatitude());
		costDebitNoteVO.setOrgId(costDebitNoteDTO.getOrgId());
		costDebitNoteVO.setDocNo(costDebitNoteDTO.getDocNo());
		costDebitNoteVO.setSubType(costDebitNoteDTO.getSubType());
		costDebitNoteVO.setProduct(costDebitNoteDTO.getProduct());
		costDebitNoteVO.setVohNo(costDebitNoteDTO.getVohNo());
		costDebitNoteVO.setVohDate(costDebitNoteDTO.getVohDate());
		costDebitNoteVO.setPartyType(costDebitNoteDTO.getPartyType());
		costDebitNoteVO.setSuppRefNo(costDebitNoteDTO.getSuppRefNo());
		costDebitNoteVO.setSuppDate(costDebitNoteDTO.getSuppDate());
		costDebitNoteVO.setPartyName(costDebitNoteDTO.getPartyName());
		costDebitNoteVO.setPartyCode(costDebitNoteDTO.getPartyCode());
		costDebitNoteVO.setCreditDays(costDebitNoteDTO.getCreditDays());
		costDebitNoteVO.setDueDate(costDebitNoteDTO.getDueDate());
		costDebitNoteVO.setAddress(costDebitNoteDTO.getAddress());
		costDebitNoteVO.setCurrency(costDebitNoteDTO.getCurrency());
		costDebitNoteVO.setExRate(costDebitNoteDTO.getExRate());
		costDebitNoteVO.setOtherInfo(costDebitNoteDTO.getOtherInfo());
		costDebitNoteVO.setRemarks(costDebitNoteDTO.getRemarks());
		costDebitNoteVO.setShipRefNo(costDebitNoteDTO.getShipRefNo());
		costDebitNoteVO.setStatus(costDebitNoteDTO.getStatus());
		costDebitNoteVO.setOrginBill(costDebitNoteDTO.getOrginBill());
		costDebitNoteVO.setGstType(costDebitNoteDTO.getGstType());
		costDebitNoteVO.setCurrentDate(costDebitNoteDTO.getCurrentDate());
		costDebitNoteVO.setCurrentDateValue(costDebitNoteDTO.getCurrentDateValue());

		// Deleting existing entries if updating
		if (costDebitNoteDTO.getId() != null) {
			List<CostDebitNoteGstVO> costDebitNoteGstVOs = costDebitNoteGstRepo.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteGstRepo.deleteAll(costDebitNoteGstVOs);

			List<CostDebitNoteTaxPrtculVO> costDebitNoteTaxPrtculVOs = costDebitNoteTaxPrtculRepo
					.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteTaxPrtculRepo.deleteAll(costDebitNoteTaxPrtculVOs);

			List<CostDebitNoteSummaryVO> costDebitNoteSummaryVOs = costDebitNoteSummaryRepo
					.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteSummaryRepo.deleteAll(costDebitNoteSummaryVOs);

			List<CostDebitChargesVO> costDebitChargesVOs = costDebitChargesRepo.findByCostDebitNoteVO(costDebitNoteVO);
			costDebitChargesRepo.deleteAll(costDebitChargesVOs);
		}

		// Adding Cost Debit Note GSTs
		List<CostDebitNoteGstVO> costDebitNoteGstVOs = new ArrayList<>();
		for (CostDebitNoteGstDTO costDebitNoteGstDTO : costDebitNoteDTO.getCostDebitNoteGstDTO()) {
			CostDebitNoteGstVO costDebitNoteGstVO = new CostDebitNoteGstVO();

			costDebitNoteGstVO.setChargeAcc(costDebitNoteGstDTO.getChargeAcc());
			costDebitNoteGstVO.setSubLodgerCode(costDebitNoteGstDTO.getSubLodgerCode());
			costDebitNoteGstVO.setDBillAmt(costDebitNoteGstDTO.getDBillAmt());
			costDebitNoteGstVO.setCrBillAmt(costDebitNoteGstDTO.getCrBillAmt());
			costDebitNoteGstVO.setDBLCAmt(costDebitNoteGstDTO.getDBLCAmt());
			costDebitNoteGstVO.setCrLCAmt(costDebitNoteGstDTO.getCrLCAmt());
			costDebitNoteGstVO.setRemarks(costDebitNoteGstDTO.getRemarks());

			costDebitNoteGstVO.setCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteGstVOs.add(costDebitNoteGstVO);
		}
		costDebitNoteVO.setCostDebitNoteGstVO(costDebitNoteGstVOs);

		// Adding Cost Debit Note Tax Particulars
		List<CostDebitNoteTaxPrtculVO> costDebitNoteTaxPrtculVOs = new ArrayList<>();
		for (CostDebitNoteTaxPrtculDTO costDebitNoteTaxPrtculDTO : costDebitNoteDTO.getCostDebitNoteTaxPrtculDTO()) {
			CostDebitNoteTaxPrtculVO costDebitNoteTaxPrtculVO = new CostDebitNoteTaxPrtculVO();

			costDebitNoteTaxPrtculVO.setTds(costDebitNoteTaxPrtculDTO.getTds());
			costDebitNoteTaxPrtculVO.setTdsPercentage(costDebitNoteTaxPrtculDTO.getTdsPercentage());
			costDebitNoteTaxPrtculVO.setSection(costDebitNoteTaxPrtculDTO.getSection());
			costDebitNoteTaxPrtculVO.setTotTDSAmt(costDebitNoteTaxPrtculDTO.getTotTDSAmt());

			costDebitNoteTaxPrtculVO.setCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteTaxPrtculVOs.add(costDebitNoteTaxPrtculVO);
		}
		costDebitNoteVO.setCostDebitNoteTaxPrtculVO(costDebitNoteTaxPrtculVOs);

		// Adding Cost Debit Note Summaries
		List<CostDebitNoteSummaryVO> costDebitNoteSummaryVOs = new ArrayList<>();
		for (CostDebitNoteSummaryDTO costDebitNoteSummaryDTO : costDebitNoteDTO.getCostDebitNoteSummaryDTO()) {
			CostDebitNoteSummaryVO costDebitNoteSummaryVO = new CostDebitNoteSummaryVO();

			costDebitNoteSummaryVO.setTotChargesBillCurrAmt(costDebitNoteSummaryDTO.getTotChargesBillCurrAmt());
			costDebitNoteSummaryVO.setTotChargesLCAmt(costDebitNoteSummaryDTO.getTotChargesLCAmt());
			costDebitNoteSummaryVO.setTotGrossBillAmt(costDebitNoteSummaryDTO.getTotGrossBillAmt());
			costDebitNoteSummaryVO.setTotGrossLCAmt(costDebitNoteSummaryDTO.getTotGrossLCAmt());
			costDebitNoteSummaryVO.setNetBillCurrAmt(costDebitNoteSummaryDTO.getNetBillCurrAmt());
			costDebitNoteSummaryVO.setNetLCAmt(costDebitNoteSummaryDTO.getNetLCAmt());
			costDebitNoteSummaryVO.setRoundOff(costDebitNoteSummaryDTO.getRoundOff());

			costDebitNoteSummaryVO.setCostDebitNoteVO(costDebitNoteVO);
			costDebitNoteSummaryVOs.add(costDebitNoteSummaryVO);
		}
		costDebitNoteVO.setCostDebitNoteSummaryVO(costDebitNoteSummaryVOs);

		// Adding Cost Debit Charges
		List<CostDebitChargesVO> costDebitChargesVOs = new ArrayList<>();
		for (CostDebitChargesDTO costDebitChargesDTO : costDebitNoteDTO.getCostDebitChargesDTO()) {
			CostDebitChargesVO costDebitChargesVO = new CostDebitChargesVO();

			costDebitChargesVO.setJobType(costDebitChargesDTO.getJobType());
			costDebitChargesVO.setJobNo(costDebitChargesDTO.getJobNo());
			costDebitChargesVO.setSubJobNo(costDebitChargesDTO.getSubJobNo());
			costDebitChargesVO.setHouseNo(costDebitChargesDTO.getHouseNo());
			costDebitChargesVO.setChargeCode(costDebitChargesDTO.getChargeCode());
			costDebitChargesVO.setGChargeCode(costDebitChargesDTO.getGChargeCode());
			costDebitChargesVO.setGSAC(costDebitChargesDTO.getGSAC());
			costDebitChargesVO.setChargeName(costDebitChargesDTO.getChargeName());
			costDebitChargesVO.setApplyOn(costDebitChargesDTO.getApplyOn());
			costDebitChargesVO.setCurrency(costDebitChargesDTO.getCurrency());
			costDebitChargesVO.setExRate(costDebitChargesDTO.getExRate());
			costDebitChargesVO.setRate(costDebitChargesDTO.getRate());
			costDebitChargesVO.setFcAmt(costDebitChargesDTO.getFcAmt());
			costDebitChargesVO.setLcAmt(costDebitChargesDTO.getLcAmt());
			costDebitChargesVO.setTaxPercentage(costDebitChargesDTO.getTaxPercentage());
			costDebitChargesVO.setTlcAmt(costDebitChargesDTO.getTlcAmt());
			costDebitChargesVO.setBillAmt(costDebitChargesDTO.getBillAmt());
			costDebitChargesVO.setGstPercentage(costDebitChargesDTO.getGstPercentage());
			costDebitChargesVO.setGst(costDebitChargesDTO.getGst());

			costDebitChargesVO.setCostDebitNoteVO(costDebitNoteVO);
			costDebitChargesVOs.add(costDebitChargesVO);
		}
		costDebitNoteVO.setCostDebitChargesVO(costDebitChargesVOs);

		return costDebitNoteVO;
	}

	@Override
	public List<CostDebitNoteVO> getCostDebitNoteByOrgId(Long orgId) {

		return costDebitNoteRepo.getByCostDebitNoteByOrgId(orgId);
	}

	@Override
	public List<CostDebitNoteVO> getCostDebitNoteById(Long id) {
		return costDebitNoteRepo.getByCostDebitNoteById(id);
	}

	@Override
	public List<CostDebitNoteVO> getActiveCostDebitNote() {
		return costDebitNoteRepo.getActiveCostDebitNote();
	}

	@Override
	public CostDebitNoteVO getAllCostDebitNoteByDocId(Long orgId, String docId) {
		return costDebitNoteRepo.findAllCostDebitNoteByDocId(orgId, docId);
	}

	@Override
	public String getCostDebitNoteDocId(Long orgId, String finYear, String branch, String branchCode) {
		String ScreenCode = "CDN";
		String result = costDebitNoteRepo.getCostDebitNoteDocId(orgId, finYear, branchCode, ScreenCode);
		return result;
	}

	@Override
	public List<Map<String, Object>> partyDetailsForCostDebitNote(Long orgId, String branch,String finYear) {
		Set<Object[]> getPartyMasterDetails = costDebitNoteRepo.getParty(orgId, branch,finYear);
		return getStockLow(getPartyMasterDetails);
	}

	private List<Map<String, Object>> getStockLow(Set<Object[]> getPartyMaster) {
		List<Map<String, Object>> gridDetails = new ArrayList<>();
		for (Object[] grid : getPartyMaster) {
			Map<String, Object> details = new HashMap<>();
			details.put("partyName", grid[0] != null ? grid[0].toString() : "");
			details.put("partyCode", grid[1] != null ? grid[1].toString() : "");
			details.put("partyType", grid[2] != null ? grid[2].toString() : "");
			details.put("addressType", grid[3] != null ? grid[3].toString() : "");
			
		}
		return gridDetails;
	}

	@Override
	public List<Map<String, Object>> chargeTypeDetailsForCostDebitNote(Long orgId) {
		Set<Object[]> getChargeTypeDetails = costDebitNoteRepo.getChareDetails(orgId);
		return getStockLow(getChargeTypeDetails);
	}

	private List<Map<String, Object>> getChargeType(Set<Object[]> getCharge) {
		List<Map<String, Object>> gridDetails = new ArrayList<>();
		for (Object[] grid : getCharge) {
			Map<String, Object> details = new HashMap<>();
			details.put("chargeType", grid[0] != null ? grid[0].toString() : "");
			details.put("chargeCode", grid[1] != null ? grid[1].toString() : "");
			details.put("cSac", grid[2] != null ? grid[2].toString() : "");
			details.put("cChargeCode", grid[3] != null ? grid[3].toString() : "");
			
			
			gridDetails.add(details);
		}
		return gridDetails;
	}


}
