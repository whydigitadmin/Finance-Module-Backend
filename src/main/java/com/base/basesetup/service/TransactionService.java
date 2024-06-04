package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface TransactionService {

//	TaxInvoiceVO
	List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId);

	TaxInvoiceVO updateCreateTaxInvoice(@Valid TaxInvoiceDTO taxInvoiceDTO) throws ApplicationException;

	List<TaxInvoiceVO> getAllTaxInvoiceById(Long id);

	List<TaxInvoiceVO> getTaxInvoiceByActive();
}
