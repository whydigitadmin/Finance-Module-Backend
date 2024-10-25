package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.GeneralJournalVO;
import com.base.basesetup.entity.ParticularsJournalVO;

public interface ParticularsJournalRepo extends JpaRepository<ParticularsJournalVO, Long>{

	List<ParticularsJournalVO> findByGeneralJournalVO(GeneralJournalVO generalJournalVO);

	List<ParticularsJournalVO> findAll(GeneralJournalVO generalJournalVO);

}
