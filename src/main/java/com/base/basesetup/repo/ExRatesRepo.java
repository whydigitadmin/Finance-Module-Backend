package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ExRatesVO;

public interface ExRatesRepo extends JpaRepository<ExRatesVO, Long> {

	@Query(nativeQuery = true, value = "select * from exrates where exratesid=?1")
	List<ExRatesVO> getAllExRatesById(Long id);

	@Query(nativeQuery = true, value = "select * from exrates where orgid=?1")
	List<ExRatesVO> getAllExRatesByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from exrates where active=1")
	List<ExRatesVO> findExRatesByActive();

}
