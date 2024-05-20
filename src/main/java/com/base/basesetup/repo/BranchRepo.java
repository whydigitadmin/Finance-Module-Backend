package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.BranchVO;

@Repository
public interface BranchRepo extends JpaRepository<BranchVO, Long> {

	@Query(nativeQuery = true,value = "select * from branch where branchid=?1")
	List<BranchVO> findBranchById(Long id);

	@Query(nativeQuery = true,value = "select * from branch where orgid=?1")
	List<BranchVO> findBranchByOrgId(Long orgId);

	boolean existsByBranchCodeAndOrgId(String branchCode, Long orgId);

	boolean existsByBranchAndOrgId(String branch, Long orgId);

}
