package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReconciliationSummaryVO;

@Repository
public interface ReconciliationSummaryRepo extends JpaRepository<ReconciliationSummaryVO, Long>{

	@Query(nativeQuery = true,value="select * from reconciliationsummary where reconciliationsummaryid=?1")
	List<ReconciliationSummaryVO> getAllReconciliationSummaryById(Long id);

	@Query(nativeQuery = true,value="select * from reconciliationsummary where orgid=?1")
	List<ReconciliationSummaryVO> getAllReconciliationSummaryByOrgId(Long orgId);

}



















