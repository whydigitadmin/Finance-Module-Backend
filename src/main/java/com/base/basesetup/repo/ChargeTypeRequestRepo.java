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


	@Query(nativeQuery = true, value = "select accountgroupname from groupledger where active=1 and category in('RECEIVABLE A/C','OTHERS') and type='ACCOUNT' and orgid=?1")
	Set<Object[]> findSalesAccountFromGroup(Long orgId);

	@Query(nativeQuery = true, value = "select accountgroupname from groupledger where active=1 and category in('PAYABLE A/C','OTHERS') and type='ACCOUNT' and orgid=?1")
	Set<Object[]> findPaymentAccountFromGroup(Long orgId);

	boolean existsByOrgIdAndChargeDescriptionIgnoreCase(Long orgId, String chargeDescription);

	boolean existsByOrgIdAndChargeCodeIgnoreCase(Long orgId, String chargeCode);
}
