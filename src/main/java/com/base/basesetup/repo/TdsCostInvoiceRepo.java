package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.TdsCostInvoiceVO;

public interface TdsCostInvoiceRepo extends JpaRepository<TdsCostInvoiceVO,Long> {

}
