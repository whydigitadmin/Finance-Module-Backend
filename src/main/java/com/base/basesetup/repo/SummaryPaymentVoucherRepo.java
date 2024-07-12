package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.SummaryPaymentVoucherVO;

public interface SummaryPaymentVoucherRepo extends JpaRepository<SummaryPaymentVoucherVO, Long> {

}
