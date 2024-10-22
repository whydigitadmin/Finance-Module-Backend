package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitChargesVO;
import com.base.basesetup.entity.CostDebitNoteVO;
@Repository
public interface CostDebitChargesRepo extends JpaRepository<CostDebitChargesVO, Long>{

	List<CostDebitChargesVO> findByCostDebitNoteVO(CostDebitNoteVO costDebitNoteVO);

}
