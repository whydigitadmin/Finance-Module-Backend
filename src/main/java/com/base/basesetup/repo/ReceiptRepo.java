package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReceiptVO;

@Repository
public interface ReceiptRepo extends JpaRepository<ReceiptVO, Long> {

	@Query(nativeQuery = true, value = "select * from receiptreceivable where orgid=?1")
	List<ReceiptVO> getAllReceiptReceivableByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from receiptreceivable where receiptreceivableid=?1")
	List<ReceiptVO> getAllReceiptReceivableById(Long id);

	@Query(nativeQuery = true, value = "select * from receiptreceivable where active=1")
	List<ReceiptVO> findReceiptReceivablesByActive();

	@Query(nativeQuery = true, value = "select partyname,partycode from partymaster where orgid=?1 and branch=?2 and branchcode=?3 and finyear =?4 and active=1 and partytype='CUSTOMER'")
	Set<Object[]> getCustomerNameAndCodeForReceipt(Long orgId, String branch, String branchCode, String finYear);

	@Query(nativeQuery = true, value = "select a.docid,a.docdate,a.customername,a.bankcashacc,a.receiptamt,a.bankchargeacc,a.taxamt,a.tdsamt,b.invno,b.invdate,b.refno,b.refdate,a.chequebank,a.chequeutino,b.amount,b.outstanding,b.settled,a.createdon,a.createdby from receiptreceivable a, receiptinvdetails b where a.receiptreceivableid=b.receiptreceivableid and a.orgid=?1 and a.branch=?2 and a.branchcode=?3 and a.finyear=?4 and b.fromdate=?5 and b.todate=?6 and a.customername =?7")
	Set<Object[]> findAllReceiptRegister(Long orgId, String branch, String branchCode, String finYear, String fromDate,
			String toDate, String subLedgerName);
}
