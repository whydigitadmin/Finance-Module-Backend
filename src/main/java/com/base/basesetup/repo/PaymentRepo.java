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

	@Query(nativeQuery = true, value = "select a.docid,a.docdate,a.partyname,a.bankcashacc,a.receiptamt,a.bankcharges,a.tdsamt,a.chequebank,a.chequeno,b.invno,b.invdate,b.refno,b.refdate,b.amount,b.outstanding,b.settled,a.createdon,a.createdby from payment a, paymentinvdtls b where a.paymentid=b.paymentid and a.orgid=?1 and a.branch=?2 and a.branchcode=?3 and a.finyear=?4 and a.docdate BETWEEN ?5 AND ?6 and a.partyname =?7")
	Set<Object[]> findAllPaymentRegister(Long orgId, String branch, String branchCode, String finYear, String fromDate,
			String toDate, String subLedgerName);

	@Query(nativeQuery = true, value = "select partyname,partycode from partymaster where orgid=?1 and branch=?2 and branchcode=?3 and finyear =?4 and active=1 and partytype='VENDOR'")
	Set<Object[]> findPartyNameAndCodeForPayment(Long orgId, String branch, String branchCode, String finYear);

	@Query(nativeQuery = true, value = "SELECT a.currency, b.transcurrency FROM partymaster a JOIN partycurrencymapping b ON a.partymasterid = b.partymasterid WHERE a.orgid =?1 AND a.branch = ?2 AND a.branchcode =?3 AND a.finyear = ?4 AND a.partyname =?5 and a.active=1 ")
	Set<Object[]> findCurrencyAndTransCurrencyForPayment(Long orgId, String branch, String branchCode, String finYear,
			String partyName);

}
