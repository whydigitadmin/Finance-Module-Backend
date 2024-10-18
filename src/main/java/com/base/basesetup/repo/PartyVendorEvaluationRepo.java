package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyMasterVO;
import com.base.basesetup.entity.PartyVendorEvaluationVO;

@Repository
public interface PartyVendorEvaluationRepo extends JpaRepository<PartyVendorEvaluationVO, Long> {

	PartyVendorEvaluationVO findByPartyMasterVO(PartyMasterVO partyMasterVO);

}
