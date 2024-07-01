package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.FundTransferVO;

@Repository
public interface FundTransferRepo extends JpaRepository<FundTransferVO, Long> {

	@Query(nativeQuery = true, value = "select * from fundtransfer where orgid=?1")
	List<FundTransferVO> getAllFundTransferByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from fundtransfer where fundtransferid=1?")
	List<FundTransferVO> getAllFundTransferById(Long id);

	@Query(nativeQuery = true, value = "select * from fundtransfer where active =1")
	List<FundTransferVO> findFundTransferByActive();

}