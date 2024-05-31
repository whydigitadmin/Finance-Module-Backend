package com.base.basesetup.repo;

import java.util.List;

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

	boolean existsByChargeDescripitionAndOrgId(String chargeDescripiton, Long orgId);

	boolean existsByChargeCodeAndOrgId(String chargeCode, Long orgId);

	boolean existsByChargeDescripitionAndChargeCodeAndOrgIdAndId(String chargeDescripiton, String chargeCode, Long orgId,
			Long id);

}
