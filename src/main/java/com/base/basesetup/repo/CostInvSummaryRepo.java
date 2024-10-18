package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostInvSummaryVO;
import com.base.basesetup.entity.CostInvoiceVO;

@Repository 
public interface CostInvSummaryRepo extends JpaRepository<CostInvSummaryVO, Long>{

	List<CostInvSummaryVO> findByCostInvoiceVO(CostInvoiceVO costInvoiceVO);

}
