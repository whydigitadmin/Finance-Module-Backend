package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.IrnCreditVO;

public interface IrnCreditRepo extends JpaRepository<IrnCreditVO, Long> {

	@Query(nativeQuery = true, value = "select * from irncredit where orgid=?1")
	List<IrnCreditVO> getAllIrnCreditByOrgId(Long orgId);

	@Query(nativeQuery = true, value = "select * from irncredit where irncreditid=?1")
	List<IrnCreditVO> getAllIrnCreditById(Long id);

	@Query(nativeQuery = true, value = "select * from irncredit where active=1")
	List<IrnCreditVO> findIrnCreditByActive();

	@Query(nativeQuery = true, value = "select concat(prefixfield,lpad(lastno,5,0)) AS docid from documenttypemappingdetails where orgid=?1 and finyear=?2 and branchcode=?3 and screencode=?4")
	String getIrnCreditDocId(Long orgId, String finYear, String branchCode, String screenCode);

	@Query(nativeQuery = true, value = "SELECT partyname,partycode,partytype from partymaster where orgid=?1 and active=1 and finyear=?4 and branch=?2 AND branchcode=?3")
	Set<Object[]> findPartyNameAndPartyCodeAndPartyTypeForIrn(Long orgId, String branch, String branchCode,
			String finYear);

}
