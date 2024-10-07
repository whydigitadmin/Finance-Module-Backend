package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.BrsOpeningVO;

public interface BrsOpeningRepo extends JpaRepository<BrsOpeningVO, Long> {

	@Query(nativeQuery = true,value = "Select * from brsopening where orgid=?1")
	List<BrsOpeningVO> getAllBrsOpeningByOrgId(Long orgId);

	@Query(nativeQuery = true,value = "select * from brsopening where brsopeningid=?1")
	List<BrsOpeningVO> getAllBrsOpeningById(Long id);

	@Query(nativeQuery = true,value = "select * from brsopening where active=1")
	List<BrsOpeningVO> findBrsOpeningByActive();

}
