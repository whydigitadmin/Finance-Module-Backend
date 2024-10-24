package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ParticularsReconcileVO;
import com.base.basesetup.entity.ReconcileBankVO;

@Repository
public interface ParticularsReconcileRepo extends JpaRepository<ParticularsReconcileVO, Long> {

	List<ParticularsReconcileVO> findByReconcileBankVO(ReconcileBankVO reconcileBankVO);

}
