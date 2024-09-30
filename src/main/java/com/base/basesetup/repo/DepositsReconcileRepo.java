package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.DepositsReconcileVO;
@Repository
public interface DepositsReconcileRepo extends JpaRepository<DepositsReconcileVO,Long>{

}
