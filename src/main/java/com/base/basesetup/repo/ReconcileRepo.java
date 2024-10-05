package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReconcileVO;

@Repository
public interface ReconcileRepo extends JpaRepository<ReconcileVO, Long> {

	@Query(nativeQuery = true, value = "select * from reconcile where orgid=?1")
	List<ReconcileVO> getAllReconcileByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from reconcile where reconcileid=?1")
	List<ReconcileVO> getAllReconcileById(Long id);

	@Query(nativeQuery = true, value = "select * from reconcile where active=1")
	List<ReconcileVO> findReconcileByActive();
}
