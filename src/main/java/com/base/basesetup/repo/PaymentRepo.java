package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PaymentVO;

@Repository
public interface PaymentRepo extends JpaRepository<PaymentVO, Long> {

	@Query(value = "Select * from payment where paymentid=?1", nativeQuery = true)
	List<PaymentVO> getAllPaymentById(Long id);

	@Query(value = "select * from payment  where orgid=?1 ", nativeQuery = true)
	List<PaymentVO> getAllPaymentByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select a.docid,a.docdate,a.partyname,a.bankcashacc,a.receiptamt,a.bankcharges,a.tdsamt,a.chequebank,a.chequeno,b.invno,b.invdate,b.refno,b.refdate,b.amount,b.outstanding,b.settled,a.createdon,a.createdby from payment a, paymentinvdtls b where a.paymentid=b.paymentid and a.orgid=?1 and a.branch=?2 and a.branchcode=?3 and a.finyear=?4 and b.fromdate=?5 and b.todate=?6 and a.partyname =?7")
	Set<Object[]> findAllPaymentRegister(Long orgId, String branch, String branchCode, String finYear, String fromDate,
			String toDate, String subLedgerName);

}
