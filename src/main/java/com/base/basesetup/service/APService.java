package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.PaymentDTO;
import com.base.basesetup.entity.PaymentVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface APService {

	List<PaymentVO> getAllPaymentByOrgId(Long orgId);

	List<PaymentVO> getAllPaymentById(Long id);

	PaymentVO updateCreatePayment(@Valid PaymentDTO paymentDTO) throws ApplicationException;

// 	PaymentRegister
	List<Map<String, Object>> getAllPaymentRegister(Long orgId, String branch, String branchCode,
			String finYear,String fromDate,String toDate,String subLedgerName);

}
