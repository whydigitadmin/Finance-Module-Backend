package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.GeneralJournalVO;

public interface GeneralJournalRepo extends JpaRepository<GeneralJournalVO, Long> {

	@Query(nativeQuery = true, value = "select * from generaljournal where orgid=?1")
	List<GeneralJournalVO> getAllGeneralJournalByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from generaljournal where generaljournalid=?1")
	List<GeneralJournalVO> getAllGeneralJournalById(Long id);

	@Query(nativeQuery = true, value = "select * from generaljournal where active=1")
	List<GeneralJournalVO> findGeneralJournalByActive();

}
