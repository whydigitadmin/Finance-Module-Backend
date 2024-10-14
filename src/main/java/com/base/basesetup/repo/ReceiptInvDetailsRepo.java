package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReceiptInvDetailsVO;

@Repository
public interface ReceiptInvDetailsRepo extends JpaRepository<ReceiptInvDetailsVO, Long> {

}