package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyDetailsOfDirectorsVO;
import com.base.basesetup.entity.PartyMasterVO;

@Repository
public interface PartyDetailsOfDirectorsRepo extends JpaRepository<PartyDetailsOfDirectorsVO, Long>{

	List<PartyDetailsOfDirectorsVO> findByPartyMasterVO(PartyMasterVO partyMasterVO);

}
