package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.DocumentTypesMappingDetailsVO;
import com.base.basesetup.entity.DocumentTypesMappingVO;

@Repository
public interface DocumentTypesMappingDetailsRepo extends JpaRepository<DocumentTypesMappingDetailsVO, Long>{


	List<DocumentTypesMappingDetailsVO> findByDocumentTypesMappingVO(DocumentTypesMappingVO documentTypesMappingVO);

}
                          