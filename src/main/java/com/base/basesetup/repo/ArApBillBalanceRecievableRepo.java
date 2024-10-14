package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ArApBillBalanceReceivableVO;

public interface ArApBillBalanceRecievableRepo extends JpaRepository<ArApBillBalanceReceivableVO, Long>{

	@Query(nativeQuery = true,value = "select * from arapbillbalancerecievable where orgid=?1")
	List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from arapbillbalancerecievable where arapbillbalancerecievableid=?1")
	List<ArApBillBalanceReceivableVO> getAllArApBillBalanceReceivableById(Long id);

	@Query(nativeQuery = true,value = "select * from arapbillbalancerecievable where active=1")
	List<ArApBillBalanceReceivableVO> findArApBillBalanceReceivableByActive();


}
