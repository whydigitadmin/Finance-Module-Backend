package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ChequeBoxVO;

public interface ChequeBoxRepo extends JpaRepository<ChequeBoxVO, Long> {

	@Query(nativeQuery = true, value = "select * from chequebox where chequeboxid=?1")
	List<ChequeBoxVO> getAllChequeBoxById(Long id);

	@Query(nativeQuery = true, value = "select * from chequebox where orgid=?1")
	List<ChequeBoxVO> getAllChequeBoxByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from chequebox where active=1")
	List<ChequeBoxVO> findChequeBoxByActive();

}
