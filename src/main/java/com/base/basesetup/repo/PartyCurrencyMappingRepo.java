package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.PartyCurrencyMappingVO;
import com.base.basesetup.entity.PartyMasterVO;

@Repository
public interface PartyCurrencyMappingRepo extends JpaRepository<PartyCurrencyMappingVO, Long>{

	List<PartyCurrencyMappingVO> findByPartyMasterVO(PartyMasterVO partyMasterVO);

}