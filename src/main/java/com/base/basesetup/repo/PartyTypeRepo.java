package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyTypeVO;

@Repository
public interface PartyTypeRepo extends JpaRepository<PartyTypeVO, Long>{

	boolean existsByPartyTypeAndOrgId(String partyType, Long orgId);

	boolean existsByPartyTypeCodeAndOrgId(String partyTypeCode, Long orgId);

}
