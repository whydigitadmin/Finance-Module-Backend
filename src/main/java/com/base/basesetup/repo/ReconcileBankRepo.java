package com.base.basesetup.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReconcileBankVO;


@Repository
public interface ReconcileBankRepo extends JpaRepository<ReconcileBankVO, Long> {

	@Query(nativeQuery = true, value = "select * from reconcilebank where orgid=?1")
	List<ReconcileBankVO> getAllReconcileBankByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from reconcilebank where reconcilebankid=?1")
	List<ReconcileBankVO> getAllReconcileBankById(Long id);

	@Query(nativeQuery = true, value = "select * from reconcilebank where active=1")
	List<ReconcileBankVO> findReconcileBankByActive();
}
