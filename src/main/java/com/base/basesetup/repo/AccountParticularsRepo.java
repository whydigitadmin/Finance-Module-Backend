package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.AccountParticularsVO;
import com.base.basesetup.entity.PaymentVO;

@Repository
public interface AccountParticularsRepo extends JpaRepository<AccountParticularsVO, Long>{

	List<AccountParticularsVO> findByPaymentVO(PaymentVO paymentVO);

}
