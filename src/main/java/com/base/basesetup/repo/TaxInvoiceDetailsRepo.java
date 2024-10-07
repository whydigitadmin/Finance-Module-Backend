package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.TaxInvoiceDetailsVO;
import com.base.basesetup.entity.TaxInvoiceVO;

public interface TaxInvoiceDetailsRepo extends JpaRepository<TaxInvoiceDetailsVO,Long>{

	List<TaxInvoiceDetailsVO> findByTaxInvoiceVO(TaxInvoiceVO taxInvoiceVO);

}
