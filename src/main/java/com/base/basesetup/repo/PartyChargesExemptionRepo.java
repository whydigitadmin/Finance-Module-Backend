package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyChargesExemptionVO;
import com.base.basesetup.entity.PartyMasterVO;

@Repository
public interface PartyChargesExemptionRepo extends JpaRepository<PartyChargesExemptionVO, Long>{

	List<PartyChargesExemptionVO> findByPartyMasterVO(PartyMasterVO partyMasterVO);

}
