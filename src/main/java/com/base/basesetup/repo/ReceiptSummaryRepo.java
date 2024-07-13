package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReceiptSummaryVO;

@Repository
public interface ReceiptSummaryRepo extends JpaRepository<ReceiptSummaryVO, Long> {

}
