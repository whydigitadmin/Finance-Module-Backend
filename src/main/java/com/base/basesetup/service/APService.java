package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ApBillBalanceDTO;
import com.base.basesetup.dto.PaymentDTO;
import com.base.basesetup.entity.ApBillBalanceVO;
import com.base.basesetup.entity.PaymentVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface APService {

//	Payment
	List<PaymentVO> getAllPaymentByOrgId(Long orgId);

	List<PaymentVO> getAllPaymentById(Long id);

	PaymentVO updateCreatePayment(@Valid PaymentDTO paymentDTO) throws ApplicationException;

//	ARBillBalance
	List<ApBillBalanceVO> getAllApBillBalanceByOrgId(Long orgId);

	List<ApBillBalanceVO> getAllApBillBalanceById(Long id);

	ApBillBalanceVO updateCreateApBillBalance(@Valid ApBillBalanceDTO apBillBalanceDTO)
			throws ApplicationException;

	List<ApBillBalanceVO> getApBillBalanceByActive();

// 	PaymentRegister
	List<Map<String, Object>> getAllPaymentRegister(Long orgId, String branch, String branchCode, String finYear,
			String fromDate, String toDate, String subLedgerName);

}
