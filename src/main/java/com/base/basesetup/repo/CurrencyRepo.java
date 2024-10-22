package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.CurrencyVO; 

public interface CurrencyRepo extends JpaRepository<CurrencyVO, Long> {


	@Query(value="select * FROM currency where orgid=?1",nativeQuery =true)
	List<CurrencyVO> findAll(Long orgid);

	boolean existsByCurrencyAndOrgId(String currency, Long orgId);

	boolean existsBySubCurrencyAndOrgId(String subCurrency, Long orgId);

	boolean existsByCurrencyDescriptionAndOrgId(String currencyDescription, Long orgId);

	@Query(value ="SELECT currency,currencydesc FROM currency where orgid=?1 and active=1",nativeQuery =true)
	Set<Object[]> findCurrencyForFullGrid(Long orgId);





}
