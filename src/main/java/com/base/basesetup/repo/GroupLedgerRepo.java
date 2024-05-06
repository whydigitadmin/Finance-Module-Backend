package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.GroupLedgerVO;

public interface GroupLedgerRepo extends JpaRepository<GroupLedgerVO, Long>{
	
@Query(nativeQuery =true,value = "select * from groupledger where groupledgerid=?1")
	List<GroupLedgerVO> getAllGroupLedgerByOrgId(Long orgId);

}
