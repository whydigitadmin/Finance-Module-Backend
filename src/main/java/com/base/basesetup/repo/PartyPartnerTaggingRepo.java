package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyMasterVO;
import com.base.basesetup.entity.PartyPartnerTaggingVO;

@Repository
public interface PartyPartnerTaggingRepo extends JpaRepository<PartyPartnerTaggingVO, Long> {

	List<PartyPartnerTaggingVO> findByPartyMasterVO(PartyMasterVO partyMasterVO);

}
