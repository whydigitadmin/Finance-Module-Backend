package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ApBillBalanceVO;

public interface ApBillBalanceRepo extends JpaRepository<ApBillBalanceVO, Long> {

	@Query(nativeQuery = true, value = "select * from apbillbalance where orgid=?1")
	List<ApBillBalanceVO> getAllApBillBalanceByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from apbillbalance where apbillbalanceid=?1")
	List<ApBillBalanceVO> getAllApBillBalanceById(Long id);

	@Query(nativeQuery = true, value = "select * from apbillbalance where active=1")
	List<ApBillBalanceVO> findApBillBalanceByActive();

	@Query(nativeQuery = true, value = "select * from apbillbalance where orgid=?1 and branch=?2 and branchcode=?3 and finyear=?4 and  active=1")
	List<ApBillBalanceVO> findAll(Long orgId, String branch, String branchCode, String finYear);

	@Query(nativeQuery = true, value = "select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getArBillBalanceDocId(Long orgId, String finYear, String branchCode, String screenCode);

	@Query(nativeQuery = true,value="select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getApBillBalanceDocId(Long orgId, String finYear, String branchCode, String screenCode);

}
