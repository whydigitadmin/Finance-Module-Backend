package com.base.basesetup.service;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.base.basesetup.dto.CostDebitNoteDTO;
import com.base.basesetup.exception.ApplicationException;

@Service
public interface CostDebitNoteService {

	Map<String, Object> updateCreateCostDebitNote(@Valid CostDebitNoteDTO costDebitNoteDTO) throws ApplicationException;

}
