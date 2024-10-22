package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitNoteSummaryVO;
import com.base.basesetup.entity.CostDebitNoteVO;
@Repository
public interface CostDebitNoteSummaryRepo extends JpaRepository<CostDebitNoteSummaryVO, Long>{

	List<CostDebitNoteSummaryVO> findByCostDebitNoteVO(CostDebitNoteVO costDebitNoteVO);

}
