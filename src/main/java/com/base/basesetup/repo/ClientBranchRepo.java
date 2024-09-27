package com.base.basesetup.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.base.basesetup.entity.ClientBranchVO;
import com.base.basesetup.entity.CustomerVO;


public interface ClientBranchRepo  extends JpaRepository<ClientBranchVO, Long> {
	@Query(value = "select b.branchcode from client c,clientbranch b, customer a where client =?1 and  c.customerid=b.customerid and a.customerid=b.customerid and a.orgid =?2 group by b.branchcode", nativeQuery = true)
	Set<Object[]> findAllBranchCodeAndBranchByOrgId(String client, Long orgid);

	@Query(nativeQuery = true, value = "select a.* from clientbranch a,customer b where a.customerid=b.customerid and b.orgid=?1 and b.customer=?2")
	List<ClientBranchVO> findAllBranchByCustomer(Long orgid, String customer);

	List<ClientBranchVO> findByCustomerVO(CustomerVO customerVO);

}