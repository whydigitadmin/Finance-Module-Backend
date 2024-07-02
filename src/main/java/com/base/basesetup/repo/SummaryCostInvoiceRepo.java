package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.SummaryCostInvoiceVO;

public interface SummaryCostInvoiceRepo extends JpaRepository<SummaryCostInvoiceVO, Long> {

}
