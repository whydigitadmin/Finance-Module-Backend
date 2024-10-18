package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.ListOfValuesVO;
@Repository
public interface ListOfValuesRepo extends JpaRepository<ListOfValuesVO, Long> {

	@Query(nativeQuery = true, value = "select * from listofvalues where listofvaluesid=?1")
	List<ListOfValuesVO> getListOfValuesById(Long id);
	
	@Query(nativeQuery = true, value = "select * from listofvalues where orgid=?1")
	List<ListOfValuesVO> getListOfValuesByOrgId(Long orgid);

	boolean existsByListCodeAndOrgId(String listCode, Long orgId);

	boolean existsByListDescriptionAndOrgId(String listDescription, Long orgId);


        
}
