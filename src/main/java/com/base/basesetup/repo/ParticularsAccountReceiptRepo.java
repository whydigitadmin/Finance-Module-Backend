package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ParticularsAccountReceiptVO;

@Repository
public interface ParticularsAccountReceiptRepo extends JpaRepository<ParticularsAccountReceiptVO, Long> {

}
