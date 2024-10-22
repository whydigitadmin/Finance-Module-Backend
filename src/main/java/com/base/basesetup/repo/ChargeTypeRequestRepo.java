package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ChargeTypeRequestVO;

public interface ChargeTypeRequestRepo extends JpaRepository<ChargeTypeRequestVO, Long> {

	@Query(nativeQuery = true, value = "select * from chargetyperequest where chargetyperequestid=?1")
	List<ChargeTypeRequestVO> getAllChargeTypeRequestById(Long id);

	@Query(nativeQuery = true, value = "select * from chargetyperequest where orgid=?1")
	List<ChargeTypeRequestVO> getAllChargeTypeRequestByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from chargetyperequest where active=1")
	List<ChargeTypeRequestVO> findChargeTypeRequestByActive();

	boolean existsByChargeCodeAndOrgId(String chargeCode, Long orgId);

	boolean existsByChargeDescriptionAndOrgId(String chargeDescription, Long orgId);

	@Query(nativeQuery = true, value = "select accountgroupname from groupledger where active=1 and category='RECEIVABLE' and orgid=?1 and branch=?2 and branchcode=?3 and finyear=?4")
	Set<Object[]> findSalesAccountFromGroup(Long orgId, String branch, String branchCode, String finYear);

	@Query(nativeQuery = true, value = "select accountgroupname from groupledger where active=1 and category='PAYABLE' and orgid=?1 and branch=?2 and branchcode=?3 and finyear=?4")
	Set<Object[]> findPaymentAccountFromGroup(Long orgId, String branch, String branchCode, String finYear);
}
