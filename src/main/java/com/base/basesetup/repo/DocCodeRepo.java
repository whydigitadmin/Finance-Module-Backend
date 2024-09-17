package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.DocumentTypeVO;

public interface DocCodeRepo extends JpaRepository<DocumentTypeVO, Long>{

	@Query(nativeQuery = true,value = "select * from doccode where doccodeid=?1")
	List<DocumentTypeVO> findDocCodeById(Long id);

	@Query(nativeQuery = true,value = "select * from doccode where orgid=?1")
	List<DocumentTypeVO> findDocCodeByOrgId(Long orgId);

}
