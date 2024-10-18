package com.base.basesetup.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.PartyTypeDTO;
import com.base.basesetup.entity.PartyTypeVO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface PartyTypeService {

	// PartyType

	PartyTypeVO createUpdatePartyType(@Valid PartyTypeDTO partyTypeDTO) throws ApplicationException;

}
