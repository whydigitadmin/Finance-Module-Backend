package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ArBillBalanceVO;

public interface ArBillBalanceRepo extends JpaRepository<ArBillBalanceVO, Long>{

	@Query(nativeQuery = true,value = "select * from arbillbalance where orgid=?1")
	List<ArBillBalanceVO> getAllArBillBalanceByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from arbillbalance where arbillbalanceid=?1")
	List<ArBillBalanceVO> getAllArBillBalanceById(Long id);

	@Query(nativeQuery = true,value = "select * from arbillbalance where active=1")
	List<ArBillBalanceVO> findArBillBalanceByActive();


}