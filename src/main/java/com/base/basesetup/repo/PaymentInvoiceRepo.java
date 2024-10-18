package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.PaymentInvoiceVO;

public interface PaymentInvoiceRepo extends JpaRepository<PaymentInvoiceVO, Long> {

}
