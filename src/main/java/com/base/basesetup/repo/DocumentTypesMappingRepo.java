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

	@Query(nativeQuery = true, value = "select screencode,screenname,doccode,?5,?3,?1,?2,concat(?2,?5,doccode)prefixField from documenttype where orgid=?4 and concat(?1,?2,?3,screencode,doccode,orgid) not in(    \r\n"
			+ " select concat(branch,branchcode,finyear,screencode,doccode,orgid) from documenttypesmappingdetails where orgid=?4)")
	Set<Object[]> findAllDocumentTypesMappingDetailsByDocumentType(String branch, String branchCode, String finYr,
			Long orgId, String finyrId);

}
