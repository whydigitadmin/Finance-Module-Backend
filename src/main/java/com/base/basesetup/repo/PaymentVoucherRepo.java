package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.PaymentVoucherVO;

public interface PaymentVoucherRepo extends JpaRepository<PaymentVoucherVO, Long> {

	@Query(nativeQuery = true, value = "select * from paymentvoucher where orgid=?1")
	List<PaymentVoucherVO> getAllPaymentVoucherByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from paymentvoucher where paymentvoucherid=?1")
	List<PaymentVoucherVO> getAllPaymentVoucherById(Long id);

	@Query(nativeQuery = true, value = "select * from paymentvoucher where active=1")
	List<PaymentVoucherVO> findPaymentVoucherByActive();

	@Query(nativeQuery = true, value = "SELECT RIGHT(\r\n" + "    IF(\r\n"
			+ "        DATE_FORMAT(CURDATE(), '%m%d') > '0331', \r\n" + "        DATE_FORMAT(CURDATE(), '%Y'), \r\n"
			+ "        DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 YEAR), '%Y')\r\n" + "    ), \r\n" + "    2\r\n"
			+ ") AS finyr")
	int findFinyr();

	@Query(nativeQuery = true, value = "select sequence_value from paymentvoucherseq;")
	String findDocId();

	@Query(nativeQuery = true, value = "CALL next_paymentvoucher_sequence_value();")
 	void nextSeq();

}