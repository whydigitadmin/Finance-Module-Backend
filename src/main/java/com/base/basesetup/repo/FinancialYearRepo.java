package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.FinancialYearVO;

public interface FinancialYearRepo extends JpaRepository<FinancialYearVO, Long> {
	@Query(nativeQuery = true, value = "select * from financialyear where financialyearid=?1")
	List<FinancialYearVO> findFinancialYearById(Long id);

	@Query(nativeQuery = true, value = "select * from financialyear where orgid=?1")
	List<FinancialYearVO> findFinancialYearByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select finyr from  financialyear where orgid=?1 and closed=0 and  active=1")
	Set<Object[]> getFinyer(Long orgId);

	@Query(nativeQuery = true,value = "select finyr,finyrid from financialyear where closed = 0 and open = 1 and active = 1 and orgid=?1")
	Set<Object[]> findFinYrAndFinYrByOrgId(Long orgId);

}