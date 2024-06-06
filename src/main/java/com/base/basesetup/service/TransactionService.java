package com.base.basesetup.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.dto.TaxInvoiceDTO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.entity.TaxInvoiceVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface TransactionService {

//	TaxInvoice
	List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId);

	TaxInvoiceVO updateCreateTaxInvoice(@Valid TaxInvoiceDTO taxInvoiceDTO) throws ApplicationException;

	List<TaxInvoiceVO> getAllTaxInvoiceById(Long id);

	List<TaxInvoiceVO> getTaxInvoiceByActive();

	List<TaxInvoiceVO> getAllTaxInvoiceDocIdByOrgId(Long orgId);

	List<TaxInvoiceVO> getAllTaxInvoiceByDocId(Long orgId, String docId);

//	IrnCredit
	List<IrnCreditVO> getAllIrnCreditByOrgId(Long orgId);

	IrnCreditVO updateCreateIrnCredit(@Valid IrnCreditDTO irnCreditDTO) throws ApplicationException;

	List<IrnCreditVO> getAllIrnCreditById(Long id);

	List<IrnCreditVO> getIrnCreditByActive();

}
