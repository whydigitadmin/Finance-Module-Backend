package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.CurrencyVO;

public interface CurrencyRepo extends JpaRepository<CurrencyVO, Long>{

	@Query(nativeQuery = true,value = "select * from currency where currencyid=?1")
	List<CurrencyVO> findCurrencyById(Long id);

	@Query(nativeQuery = true,value = "select * from currency where orgid=?1")
	List<CurrencyVO> findCurrencyByOrgId(Long orgId);

}
