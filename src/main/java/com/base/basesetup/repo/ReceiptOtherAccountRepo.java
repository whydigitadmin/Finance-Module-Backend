package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReceiptOtherAccountVO;

@Repository
public interface ReceiptOtherAccountRepo extends JpaRepository<ReceiptOtherAccountVO, Long> {

}
