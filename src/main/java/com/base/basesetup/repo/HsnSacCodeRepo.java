package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.HsnSacCodeVO;

@Repository
public interface HsnSacCodeRepo extends JpaRepository<HsnSacCodeVO, Long> {

	@Query(nativeQuery = true, name = "select * from hsnsaccode where hsnsaccodeid=?1")
	List<HsnSacCodeVO> getAllHsnSacCodeById(Long id);

	@Query(nativeQuery = true, name = "select * from hsnsaccode where orgid=?1")
	List<HsnSacCodeVO> getAllHsnSacCodeByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from hsnsaccode where active=1")
	List<HsnSacCodeVO> findHsnSacCodeByActive();

	boolean existsByCodeAndOrgId(String code, Long orgId);

	boolean existsByCodeAndOrgIdAndId(String code, Long orgId, Long id);

	boolean existsByDescripitionAndOrgIdAndId(String descripition, Long orgId, Long id);

	boolean existsByDescripitionAndOrgId(String descripition, Long orgId);

}
