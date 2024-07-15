package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.PaymentSummaryVO;

public interface PaymentSummaryRepo extends JpaRepository<PaymentSummaryVO, Long> {

}
