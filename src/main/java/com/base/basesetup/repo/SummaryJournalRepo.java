package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.SummaryJournalVO;

public interface SummaryJournalRepo extends JpaRepository<SummaryJournalVO, Long>{

}
