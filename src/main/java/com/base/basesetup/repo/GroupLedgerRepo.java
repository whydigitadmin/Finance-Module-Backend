package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

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

boolean existsByAccountGroupNameAndOrgId(String accountGroupName, Long orgId);

@Query(nativeQuery = true, value = "select accountgroupname from groupledger where orgid=?1 and type='GROUP' and active=1 group by accountgroupname")
Set<Object[]> getGroupDetails(Long orgId);

GroupLedgerVO findByAccountGroupName(String key);

@Query(nativeQuery = true, value = "select * from groupledger where orgid=?1 and gsttaxflag!='NA' and category='TAX' and gsttaxflag='OUTPUT TAX' and gsttype=?2 and gstpercentage=?3  order by gstpercentage desc")
List<GroupLedgerVO> getTaxLedgerDetails(Long orgId, String gstType, Double key);

boolean existsByAccountCodeAndOrgId(String accountCode, Long orgId);

@Query(nativeQuery =true,value ="select * from  groupledger where active=1 and orgid=?1 and accountgroupname=?2  and type='group' and groupname is null")
GroupLedgerVO getOrgIdAndMainAccountGroupName(Long orgId, String groupName);



@Query(nativeQuery =true,value ="select * from  groupledger where active=1 and orgid=?1 and accountgroupname=?2  and type='group' and groupname is not null")
GroupLedgerVO getOrgIdAndSubAccountGroupName(Long orgId, String groupName);

@Query(nativeQuery =true,value ="select * from  groupledger where active=1 and orgid=?1 and accountcode=?2  and type='group' and groupname is null")
GroupLedgerVO getOrgIdAndMainAccountCode(Long orgId, String parentCode);

@Query(nativeQuery =true,value ="select * from  groupledger where active=1 and orgid=?1 and accountcode=?2  and type='group' and groupname is not null")
GroupLedgerVO getOrgIdAndSubAccountCode(Long orgId, String parentCode);

}
