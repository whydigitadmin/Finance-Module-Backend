package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.AccountVO;

public interface AccountRepo extends JpaRepository<AccountVO, Long>{
@Query(value = "select * from account where orgid=?1",nativeQuery = true)
	List<AccountVO> getAllAccountByOrgId(Long orgId);

@Query(value = "select * from account where accountid=?1",nativeQuery = true)
List<AccountVO> getAllAccountByaccountId(Long accountId);

}