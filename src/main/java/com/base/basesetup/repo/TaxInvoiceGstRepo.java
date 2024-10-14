package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.TaxInvoiceGstVO;
import com.base.basesetup.entity.TaxInvoiceVO;

public interface TaxInvoiceGstRepo extends JpaRepository<TaxInvoiceGstVO, Long>{

	List<TaxInvoiceGstVO> findByTaxInvoiceVO(TaxInvoiceVO taxVO);
	

}
