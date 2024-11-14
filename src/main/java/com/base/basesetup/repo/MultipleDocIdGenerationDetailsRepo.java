package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.MultipleDocIdGenerationDetailsVO;

@Repository
public interface MultipleDocIdGenerationDetailsRepo extends JpaRepository<MultipleDocIdGenerationDetailsVO, Long> {

}
