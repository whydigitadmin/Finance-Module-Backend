package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitNoteTaxPrtculVO;
@Repository
public interface CostDebitNoteTaxPrtculRepo extends JpaRepository<CostDebitNoteTaxPrtculVO, Long>{

}
