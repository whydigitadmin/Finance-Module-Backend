package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.RoleVO;

public interface RoleRepo extends JpaRepository<RoleVO, Long> {

	@Query(nativeQuery = true,value = "select * from role where roleid=?1")
	List<RoleVO> findRoleById(Long id);

	@Query(nativeQuery = true,value = "select * from role where orgid=?1")
	List<RoleVO> findRoleByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from role where active=1")
	List<RoleVO> findRoleByActive();


}
