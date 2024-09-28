package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.GlOpeningBalanceVO;
@Repository
public interface GlOpeningBalanceRepo extends JpaRepository<GlOpeningBalanceVO,Long> {
	@Query(nativeQuery = true, value = "select * from glopeningbalance where orgid=?1")
	List<GlOpeningBalanceVO> getAllGlOpeningBalanceByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from glopeningbalance where glopeningbalanceid=?1")
	List<GlOpeningBalanceVO> getAllGlOpeningBalanceById(Long id);

	@Query(nativeQuery = true, value = "select * from glopeningbalance where active=1")
	List<GlOpeningBalanceVO> findGlOpeningBalanceByActive();
}
