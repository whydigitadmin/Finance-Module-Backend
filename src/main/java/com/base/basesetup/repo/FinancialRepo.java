package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.FinancialYearVO;

public interface  FinancialRepo extends JpaRepository<FinancialYearVO, Integer> {

}
