package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.GroupLedgerVO;

public interface GroupLedgerRepo extends JpaRepository<GroupLedgerVO, Long>{
	
@Query(nativeQuery =true,value = "select * from groupledger where orgid=?1")
	List<GroupLedgerVO> getAllGroupLedgerByOrgId(Long orgId);

@Query(nativeQuery =true,value = "select * from groupledger where groupledgerid=?1")
List<GroupLedgerVO> getAllGroupLedgerById(Long id);

@Query(nativeQuery = true,value = "select * from groupledger where active=1")
List<GroupLedgerVO> findGroupLedgerByActive();

boolean existsByAccountCodeAndOrgIdAndId(String accountCode, Long orgId,Long id);

boolean existsByAccountGroupNameAndOrgIdAndId(String accountGroupName, Long orgId,Long id);

boolean existsByAccountCodeAndOrgId(String accountCode, Long orgId);

boolean existsByAccountGroupNameAndOrgId(String accountGroupName, Long orgId);


}
