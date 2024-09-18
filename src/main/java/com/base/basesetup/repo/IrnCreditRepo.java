package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.IrnCreditVO;

public interface IrnCreditRepo extends JpaRepository<IrnCreditVO, Long> {

	boolean existsByDocIdAndOrgId(String docId, Long orgId);

	boolean existsByInvoiceNoAndOrgId(String invoiceNo, Long orgId);

	boolean existsByInvoiceNoAndOrgIdAndId(String invoiceNo, Long orgId, Long id);

	boolean existsByDocIdAndOrgIdAndId(String docId, Long orgId, Long id);

	@Query(nativeQuery = true, value = "select * from irncredit where orgid=?1")
	List<IrnCreditVO> getAllIrnCreditByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from irncredit where irncreditid=?1")
	List<IrnCreditVO> getAllIrnCreditById(Long id);

	@Query(nativeQuery = true, value = "select * from irncredit where active=1")
	List<IrnCreditVO> findIrnCreditByActive();

	@Query(nativeQuery = true, value = "SELECT RIGHT(\r\n" + "    IF(\r\n"
			+ "        DATE_FORMAT(CURDATE(), '%m%d') > '0331', \r\n" + "        DATE_FORMAT(CURDATE(), '%Y'), \r\n"
			+ "        DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 YEAR), '%Y')\r\n" + "    ), \r\n" + "    2\r\n"
			+ ") AS finyr")
	int findFinyr();

	@Query(nativeQuery = true, value = "select sequence_value from irncreditseq")
	String findDocId();

	@Query(nativeQuery = true, value = "CALL next_irncredit_sequence_value()")
	void nextSeq();

	@Query(nativeQuery = true,value = "select sequence_value from irncreditnoseq")
	String findInvoiceNo();

	@Query(nativeQuery = true,value = "CALL next_irncreditno_sequence_value()")
	void nextSeqInvoice();

}
