package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitNoteGstVO;
@Repository
public interface CostDebitNoteGstRepo extends JpaRepository<CostDebitNoteGstVO, Long>{

}
