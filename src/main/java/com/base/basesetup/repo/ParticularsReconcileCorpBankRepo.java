package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ParticularsReconcileCorpBankVO;
@Repository
public interface ParticularsReconcileCorpBankRepo extends JpaRepository<ParticularsReconcileCorpBankVO,Long>{

}
