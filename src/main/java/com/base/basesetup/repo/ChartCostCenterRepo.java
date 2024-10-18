package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ChartCostCenterVO;

public interface ChartCostCenterRepo extends JpaRepository<ChartCostCenterVO, Long> {

	@Query(nativeQuery = true, value = "select * from chartcostcenter where orgid=?1")
	List<ChartCostCenterVO> getAllChartCostCenterByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from chartcostcenter where chartcostcenterid=?1")
	List<ChartCostCenterVO> getAllChartCostCenterById(Long id);

	@Query(nativeQuery = true, value = "select * from chartcostcenter where active=1")
	List<ChartCostCenterVO> findChartCostCenterByActive();

}
