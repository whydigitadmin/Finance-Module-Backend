package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyTypeVO;

@Repository
public interface PartyTypeRepo extends JpaRepository<PartyTypeVO, Long>{

	boolean existsByPartyTypeAndOrgId(String partyType, Long orgId);

	boolean existsByPartyTypeCodeAndOrgId(String partyTypeCode, Long orgId);

	@Query(nativeQuery = true,value="select * from partytype where partytypeid=?1")
	List<PartyTypeVO> findPartyTypeVOById(Long id);

	@Query(nativeQuery = true,value="select * from partytype where orgid=?1")
	List<PartyTypeVO> findAllPartyTypeVOByOrgId(Long orgid);

	PartyTypeVO findByOrgIdAndPartyType(Long orgId, String partyType);

	@Query(nativeQuery = true,value="select concat(partytypecode,lpad(lastno,5,0)) AS docid from partytype where orgid=?1 and partytype=?2")
	String getPartyTypeDocId(Long orgId, String partyType);

	@Query(nativeQuery = true,value="select concat(partytypecode,lpad(lastno,5,0)) AS docid from partytype where orgid=?1 and partytype=?2")
	Set<Object[]> findPartyCodeByOrgIdAndPartyType(Long orgid, String partytype);

	@Query(nativeQuery = true,value="select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getPartyMasterDocId(Long orgId, String finYear, String branchCode, String screenCode);

}
