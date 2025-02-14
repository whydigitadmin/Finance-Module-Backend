package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ReceiptInvDetailsVO;
import com.base.basesetup.entity.ReceiptVO;

@Repository
public interface ReceiptInvDetailsRepo extends JpaRepository<ReceiptInvDetailsVO, Long> {

	List<ReceiptInvDetailsVO> findByReceiptVO(ReceiptVO receiptVO);

}
