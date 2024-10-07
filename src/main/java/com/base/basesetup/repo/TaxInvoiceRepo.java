package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.TaxInvoiceDetailsVO;
import com.base.basesetup.entity.TaxInvoiceVO;

public interface TaxInvoiceRepo extends JpaRepository<TaxInvoiceVO, Long> {

	boolean existsByDocIdAndOrgId(String docId, Long orgId);

	boolean existsByInvoiceNoAndOrgId(String invoiceNo, Long orgId);

	boolean existsByDocIdAndOrgIdAndId(String docId, Long orgId, Long id);

	boolean existsByInvoiceNoAndOrgIdAndId(String invoiceNo, Long orgId, Long id);

	@Query(nativeQuery = true, value = "select * from taxinvoice where orgid=?1")
	List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from taxinvoice where taxinvoiceid=?1")
	List<TaxInvoiceVO> getAllTaxInvoiceById(Long id);
																																										
	@Query(nativeQuery = true, value = "select * from taxinvoice where active =1")
	List<TaxInvoiceVO> findTaxInvoiceByActive();

	@Query(nativeQuery = true, value = "SELECT RIGHT(\r\n" + "    IF(\r\n"
			+ "        DATE_FORMAT(CURDATE(), '%m%d') > '0331', \r\n" + "        DATE_FORMAT(CURDATE(), '%Y'), \r\n"
			+ "        DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 YEAR), '%Y')\r\n" + "    ), \r\n" + "    2\r\n"
			+ ") AS finyr")
	int findFinyr();

	@Query(nativeQuery = true, value = "CALL next_taxinvoice_sequence_value()")
	void nextSeq();

	@Query(nativeQuery = true, value = "select sequence_value from taxinvoiceseq")
	String findDocId();

	@Query(nativeQuery = true, value = "select * from taxinvoicenoseq")
	String findInvoiceNo();

	@Query(nativeQuery = true, value = "CALL next_taxinvoiceno_sequence_value()")
	void nextSeqInvoice();

	@Query(nativeQuery = true, value = "SELECT * FROM taxinvoice where orgid=?1")
	List<TaxInvoiceVO> findAllTaxInvoiceDocIdByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from taxinvoice where orgid=?1 and docid=?2")
	List<TaxInvoiceVO> findAllTaxInvoiceByDocId(Long orgId, String docId);


}
