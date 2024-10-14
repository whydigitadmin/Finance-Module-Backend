package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.ChargerCostInvoiceVO;
import com.base.basesetup.entity.CostInvoiceVO;

public interface ChargerCostInvoiceRepo extends JpaRepository<ChargerCostInvoiceVO, Long> {

	List<ChargerCostInvoiceVO> findByCostInvoiceVO(CostInvoiceVO costInvoiceVO);

}
