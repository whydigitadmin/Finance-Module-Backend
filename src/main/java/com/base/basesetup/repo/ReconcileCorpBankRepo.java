package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReconcileCorpBankVO;
@Repository
public interface ReconcileCorpBankRepo extends JpaRepository<ReconcileCorpBankVO, Long> {

	@Query(nativeQuery = true, value = "select * from reconcilecorpbank where orgid=?1")
	List<ReconcileCorpBankVO> getAllReconcileCorpBankByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from reconcilecorpbank where reconcilecorpbankid=?1")
	List<ReconcileCorpBankVO> getAllReconcileCorpBankById(Long id);

	@Query(nativeQuery = true, value = "select * from reconcilecorpbank where active=1")
	List<ReconcileCorpBankVO> findReconcileCorpBankByActive();
}
