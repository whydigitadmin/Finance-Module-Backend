package com.base.basesetup.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.BrsExcelUploadVO;
@Repository
public interface BrsExcelUploadRepo extends JpaRepository<BrsExcelUploadVO, Long>{

}
