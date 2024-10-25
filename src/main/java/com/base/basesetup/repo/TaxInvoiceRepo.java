package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.TaxInvoiceVO;

public interface TaxInvoiceRepo extends JpaRepository<TaxInvoiceVO, Long> {

	boolean existsByDocIdAndOrgId(String docId, Long orgId);

	boolean existsByInvoiceNoAndOrgId(String invoiceNo, Long orgId);

	boolean existsByDocIdAndOrgIdAndId(String docId, Long orgId, Long id);

	boolean existsByInvoiceNoAndOrgIdAndId(String invoiceNo, Long orgId, Long id);

	@Query(value = "select a from TaxInvoiceVO a where a.orgId=?1 and a.finYear=?2 and a.branchCode=?3 order by a.docId desc")
	List<TaxInvoiceVO> getAllTaxInvoiceByOrgId(Long orgId, String finYear, String branchCode);																																										

	@Query(nativeQuery = true, value = "select * from taxinvoice where orgid=?1 and docid=?2")
	TaxInvoiceVO findAllTaxInvoiceByDocId(Long orgId, String docId);

	@Query(value = "select a from TaxInvoiceVO a where a.id=?1")
	TaxInvoiceVO getTaxInvoiceById(Long id);

	@Query(nativeQuery = true,value="select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getTaxInvoiceDocId(Long orgId, String finYear, String branchCode, String screenCode);

	@Query(nativeQuery = true,value="select currency,currencydescripition,buyingexrate,sellingexrate from vw_exrates where orgid=?1")
	Set<Object[]> getCurrencyAndExrateDetails(Long orgId);

	@Query(nativeQuery = true,value = "select a.statecode,a.gstin,concat(a.stateno,' - ',a.state)stateno from partystate a,partymaster b where a.partymasterid=b.partymasterid and b.orgid=?1 and b.partymasterid=?2\r\n"
			+ "group by a.statecode,a.gstin,concat(a.stateno,' - ',a.state)")
	Set<Object[]> getStateCodeDetails(Long orgId, Long id);


}
