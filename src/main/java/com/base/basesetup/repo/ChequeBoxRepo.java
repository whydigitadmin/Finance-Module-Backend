package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ChequeBookVO;

public interface ChequeBoxRepo extends JpaRepository<ChequeBookVO, Long> {

	@Query(nativeQuery = true, value = "select * from chequebox where chequeboxid=?1")
	List<ChequeBookVO> getAllChequeBoxById(Long id);

	@Query(nativeQuery = true, value = "select * from chequebox where orgid=?1")
	List<ChequeBookVO> getAllChequeBoxByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from chequebox where active=1")
	List<ChequeBookVO> findChequeBoxByActive();

}
