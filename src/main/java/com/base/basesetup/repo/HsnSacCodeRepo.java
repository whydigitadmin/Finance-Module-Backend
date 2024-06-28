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

	boolean existsByCodeAndOrgId(String code, Long orgId);

	boolean existsByDescripitionAndOrgId(String descripition, Long orgId);

//	@Query(nativeQuery = true,name = "select * from hsnsaccode where active=1")
//	List<HsnSacCodeVO> findHsnSacCodeByActive();
}

