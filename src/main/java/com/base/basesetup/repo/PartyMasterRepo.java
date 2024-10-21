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

	@Query(nativeQuery = true, value = "select partyname from partymaster where orgid=?1")
	Set<Object[]> findPartyNameByOrgId(Long orgid); 


}

