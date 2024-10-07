package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ArapAdjustmentsVO;

@Repository
public interface ArapAdjustmentsRepo extends JpaRepository<ArapAdjustmentsVO, Long> {

	@Query(nativeQuery = true, value = "select * from arapadjustments where orgid=?1")
	List<ArapAdjustmentsVO> getAllArapAdjustmentsByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from arapadjustments where arapadjustmentsid=?1")
	List<ArapAdjustmentsVO> getAllArapAdjustmentsById(Long id);

	@Query(nativeQuery = true, value = "select * from arapadjustments where active=1")
	List<ArapAdjustmentsVO> findArapAdjustmentsByActive();

}
