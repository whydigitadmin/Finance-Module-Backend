package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.CostInvoiceVO;
import com.base.basesetup.entity.TdsCostInvoiceVO;

public interface TdsCostInvoiceRepo extends JpaRepository<TdsCostInvoiceVO,Long> {

	List<TdsCostInvoiceVO> findByCostInvoiceVO(CostInvoiceVO costInvoiceVO);

}
