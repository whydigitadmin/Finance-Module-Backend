package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ArApOffSetInvoiceDetailsVO;
@Repository
public interface ArApOffSetInvoiceDetailsRepo extends JpaRepository<ArApOffSetInvoiceDetailsVO, Long>{

}
