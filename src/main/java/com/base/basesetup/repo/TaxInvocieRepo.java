package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.TaxInvoiceVO;

public interface TaxInvocieRepo extends JpaRepository<TaxInvoiceVO, Long>{

	boolean existsByDocIdAndOrgId(String docId, Long orgId);

	boolean existsByInvoiceNoAndOrgId(String invoiceNo, Long orgId);

	boolean existsByDocIdAndOrgIdAndId(String docId, Long orgId, Long id);

	boolean existsByInvoiceNoAndOrgIdAndId(String invoiceNo, Long orgId, Long id);

	@Query(nativeQuery = true,value = "select * from taxinvoice where orgid=?1")
	List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from taxinvoice where taxinvoiceid=>?1")
	List<TaxInvoiceVO> getAllTaxInvoiceById(Long id);

	@Query(nativeQuery = true,value = "select * from taxinvoice where active =1")
	List<TaxInvoiceVO> findTaxInvoiceByActive();

}
