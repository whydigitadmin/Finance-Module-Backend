package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyMasterVO;
import com.base.basesetup.entity.PartyTdsExemptedVO;

@Repository
public interface PartyTdsExemptedRepo extends JpaRepository<PartyTdsExemptedVO, Long> {

	List<PartyTdsExemptedVO> findByPartyMasterVO(PartyMasterVO partyMasterVO);

}
