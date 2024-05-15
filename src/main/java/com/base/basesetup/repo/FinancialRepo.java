package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.FinancialYearVO;

public interface  FinancialRepo extends JpaRepository<FinancialYearVO, Long> {

	@Query(nativeQuery = true,value = "select * from financialyear where financialyearid=?1")
	List<FinancialYearVO> findFinancialYearById(Long id);

	@Query(nativeQuery = true,value = "select * from financialyear where orgid=?1")
	List<FinancialYearVO> findFinancialYearByOrgId(Long orgId);

}
