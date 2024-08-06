package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.DocumentTypesMappingVO;

@Repository
public interface DocumentTypesMappingRepo extends JpaRepository<DocumentTypesMappingVO, Long> {

	@Query(nativeQuery = true, value = "select * from documenttypesmapping where documenttypesmappingid=?1")
	List<DocumentTypesMappingVO> findDocumentTypesMappingById(Long id);

	@Query(nativeQuery = true, value = "select * from documenttypesmapping where orgid=?1")
	List<DocumentTypesMappingVO> findDocumentTypesMappingByOrgId(Long orgid);

	@Query(nativeQuery = true, value = "SELECT d.screencode, d.screenname, f.finyr, f.finyrid, b.branch, b.branchcode, d.doccode, CONCAT(b.branchcode, f.finyrid, d.doccode) AS prefix " +
	        "FROM documenttype d " +
	        "JOIN financialyear f ON d.orgid = f.orgid " +
	        "JOIN branch b ON d.orgid = b.orgid " +
	        "WHERE b.branch = ?1 " +
	        "AND b.branchcode = ?2 " +
	        "AND f.finyr = ?3 " +
	        "AND f.finyrid = ?4 " +
	        "AND d.orgid=?5"+
	        "AND CONCAT(d.screencode, f.finyr, b.branch, b.branchcode, f.finyrid, d.orgid, d.doccode) NOT IN (" +
	        "SELECT CONCAT(m.screencode, m.finyear, m.branch, m.branchcode, m.finyearid, m.orgid, m.doccode) " +
	        "FROM documenttypesmappingdetails m) WHERE orgid=?5")
	Set<Object[]> findAllDocumentTypesMappingDetailsByDocumentType(String branch, String branchCode, String finYrId, String finYr, Long  orgId);


}
