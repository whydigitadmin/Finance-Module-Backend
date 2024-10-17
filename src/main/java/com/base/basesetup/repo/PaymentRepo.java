package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PaymentVO;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentVO, Long>{

	@Query(value="Select * from payment where paymentid=?1",nativeQuery = true)
	List<PaymentVO> getAllPaymentById(Long id);

	@Query(value = "select * from payment  where orgid=?1 ",nativeQuery = true)
	List<PaymentVO> getAllPaymentByOrgId(Long orgId);

//	@Query(nativeQuery = true,value="")
//	Set<Object[]> findAllPaymentRegister(Long orgId, String branch, String branchCode, String finYear, String fromDate,
//			String toDate, String subLedgerName);

}
