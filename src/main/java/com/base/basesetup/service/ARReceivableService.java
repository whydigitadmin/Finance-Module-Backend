package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ArApBillBalanceReceivableDTO;
import com.base.basesetup.dto.ReceiptReceivableDTO;
import com.base.basesetup.entity.ArApBillBalanceReceivableVO;
import com.base.basesetup.entity.ReceiptReceivableVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface ARReceivableService {

	// Receipt
	List<ReceiptReceivableVO> getAllReceiptReceivableByOrgId(Long orgId);

	ReceiptReceivableVO updateCreateReceiptReceivable(@Valid ReceiptReceivableDTO receiptReceivableDTO)
			throws ApplicationException;

	List<ReceiptReceivableVO> getAllReceiptReceivableById(Long id);

	List<ReceiptReceivableVO> getReceiptReceivableByActive();

//	ARApBillBalance
	List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableByOrgId(Long orgId);

	List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableById(Long id);

	ArApBillBalanceReceivableVO updateCreateArApBillBalanceReceivable(
			@Valid ArApBillBalanceReceivableDTO arApBillBalanceReceivableDTO) throws ApplicationException;

	List<ArApBillBalanceReceivableVO> getArApBillBalanceReceivableByActive();


}