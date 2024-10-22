package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyMasterVO;



@Repository
public interface PartyMasterRepo extends JpaRepository<PartyMasterVO, Long> {

	@Query(nativeQuery = true, value = "select * from partymaster where partymasterid=?1")
	List<PartyMasterVO> findPartyMasterVOById(Long id);

	@Query(nativeQuery = true, value = "select * from partymaster where orgid=?1")
	List<PartyMasterVO> findPartyMasterVOByOrgId(Long orgid);

	@Query(nativeQuery = true,value="select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getPartyMasterDocId(Long orgId, String finYear, String branchCode, String screenCode);



}

