package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ResponsibilitiesVO;

@Repository
public interface ResponsibilitiesRepo extends JpaRepository<ResponsibilitiesVO, Long> {

	@Query(nativeQuery = true, value = "select * from responsibilities where Responsibilitiesid=?1")
	List<ResponsibilitiesVO> findResponsibilitiesById(Long id);

	@Query(nativeQuery = true, value = "select * from responsibilities where orgid=?1")
	List<ResponsibilitiesVO> findResponsibilitiesByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from responsibilities where active=1")
	List<ResponsibilitiesVO> findResponsibilitiesByActive();

}
