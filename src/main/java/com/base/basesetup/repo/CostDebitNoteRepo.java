package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitNoteVO;
@Repository
public interface CostDebitNoteRepo extends JpaRepository<CostDebitNoteVO, Long>{

}
