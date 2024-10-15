package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CostInvoiceDTO;
import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface CostInvoiceService {

	// CostInvoice
	List<CostInvoiceVO> getAllCostInvoiceByOrgId(Long orgId);

	Map<String, Object> updateCreateCostInvoice(@Valid CostInvoiceDTO costInvoiceDTO) throws ApplicationException;

	List<CostInvoiceVO> getAllCostInvoiceById(Long id);

	List<CostInvoiceVO> getCostInvoiceByActive();

}
