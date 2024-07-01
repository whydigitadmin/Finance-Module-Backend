package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.GstTaxInvoiceVO;

public interface GstTaxInvoiceRepo extends JpaRepository<GstTaxInvoiceVO, Long>{

}
