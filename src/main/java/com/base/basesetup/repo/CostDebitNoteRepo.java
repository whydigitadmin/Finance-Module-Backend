package com.base.basesetup.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.base.basesetup.entity.CostDebitNoteVO;
@Repository
public interface CostDebitNoteRepo extends JpaRepository<CostDebitNoteVO, Long>{
	
    @Query(value ="SELECT * FROM costdebitnote where orgid=?1",nativeQuery =true)
	List<CostDebitNoteVO> getByCostDebitNoteByOrgId(Long orgId);
    
    @Query(value ="SELECT * FROM costdebitnote where costdebitnoteid=?1",nativeQuery =true)
	List<CostDebitNoteVO> getByCostDebitNoteById(Long id);
    
    @Query(value ="SELECT * FROM costdebitnote where active=1",nativeQuery =true)
	List<CostDebitNoteVO> getActiveCostDebitNote();

    @Query(nativeQuery = true,value="select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getCostDebitNoteDocId(Long orgId, String finYear, String branchCode, String screenCode);

    @Query(nativeQuery = true, value = "select * from CostDebitNoteVO where orgId=?1 and docId=?2")
	CostDebitNoteVO findAllCostDebitNoteByDocId(Long orgId, String docId);

}
