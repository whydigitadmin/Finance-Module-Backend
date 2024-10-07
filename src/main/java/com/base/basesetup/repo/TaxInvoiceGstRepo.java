package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.TaxInvoiceGstVO;

public interface TaxInvoiceGstRepo extends JpaRepository<TaxInvoiceGstVO, Long>{
	

}
