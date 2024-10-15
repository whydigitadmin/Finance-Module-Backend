package com.base.basesetup.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.ArapAdjustmentsDTO;
import com.base.basesetup.entity.ArapAdjustmentsVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface ArapAdjustmentsService {

//	ArapAdjustments
	List<ArapAdjustmentsVO> getAllArapAdjustmentsByOrgId(Long orgId);

	List<ArapAdjustmentsVO> getAllArapAdjustmentsById(Long id);

	List<ArapAdjustmentsVO> getArapAdjustmentsByActive();

	Map<String, Object> createUpdateArapAdjustments(@Valid ArapAdjustmentsDTO arapAdjustmentsDTO) throws ApplicationException;
	
}
