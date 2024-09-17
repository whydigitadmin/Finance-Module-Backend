package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.base.basesetup.entity.GstDebitNoteVO;

public interface GstDebitNoteRepo extends JpaRepository<GstDebitNoteVO, Long>{

}
