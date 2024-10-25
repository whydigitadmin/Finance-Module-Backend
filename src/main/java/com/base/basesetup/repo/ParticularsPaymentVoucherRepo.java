package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.ParticularsPaymentVoucherVO;
import com.base.basesetup.entity.PaymentVoucherVO;

public interface ParticularsPaymentVoucherRepo extends JpaRepository<ParticularsPaymentVoucherVO, Long> {

	List<ParticularsPaymentVoucherVO> findByPaymentVoucherVO(PaymentVoucherVO paymentVoucherVO);

}
