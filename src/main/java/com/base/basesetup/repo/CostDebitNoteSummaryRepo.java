package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitNoteSummaryVO;
@Repository
public interface CostDebitNoteSummaryRepo extends JpaRepository<CostDebitNoteSummaryVO, Long>{

}
