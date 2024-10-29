package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.IrnCreditDTO;
import com.base.basesetup.entity.IrnCreditVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface IrnCreditNoteService {
	
//	IrnCredit
	List<IrnCreditVO> getAllIrnCreditByOrgId(Long orgId);

	Map<String, Object> updateCreateIrnCredit(@Valid IrnCreditDTO irnCreditDTO) throws ApplicationException;

	List<IrnCreditVO> getAllIrnCreditById(Long id);

	List<IrnCreditVO> getIrnCreditByActive();
	
	List<Map<String,Object>> getPartyNameAndPartyCodeAndPartyTypeForIrn(Long orgId);
	
	String getIrnCreditNoteDocId(Long orgId, String finYear, String branch, String branchCode);
}
