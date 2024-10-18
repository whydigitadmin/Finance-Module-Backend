package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ArApBillBalanceReceivableDTO;
import com.base.basesetup.dto.ReceiptDTO;
import com.base.basesetup.entity.ArApBillBalanceReceivableVO;
import com.base.basesetup.entity.ReceiptVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface ARService {

	// Receipt
	List<ReceiptVO> getAllReceiptReceivableByOrgId(Long orgId);

	ReceiptVO updateCreateReceiptReceivable(@Valid ReceiptDTO receiptReceivableDTO)
			throws ApplicationException;

	List<ReceiptVO> getAllReceiptReceivableById(Long id);

	List<ReceiptVO> getReceiptReceivableByActive();

	List<Map<String, Object>> getCustomerNameAndCodeForReceipt(Long orgId, String branch, String branchCode,
			String finYear);

//	ARApBillBalance
	List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableByOrgId(Long orgId);

	List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableById(Long id);

	ArApBillBalanceReceivableVO updateCreateArApBillBalanceReceivable(
			@Valid ArApBillBalanceReceivableDTO arApBillBalanceReceivableDTO) throws ApplicationException;

	List<ArApBillBalanceReceivableVO> getArApBillBalanceReceivableByActive();
	
// 	ReceiptRegister
	List<Map<String, Object>> getAllReceiptRegister(Long orgId, String branch, String branchCode,
			String finYear,String fromDate,String toDate,String subLedgerName);

}
