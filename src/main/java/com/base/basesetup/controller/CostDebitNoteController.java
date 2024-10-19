package com.base.basesetup.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base.basesetup.common.CommonConstant;
import com.base.basesetup.common.UserConstants;
import com.base.basesetup.dto.CostDebitNoteDTO;
import com.base.basesetup.dto.ResponseDTO;
import com.base.basesetup.service.CostDebitNoteService;

@CrossOrigin
@RestController
@RequestMapping("/api/costdebitnote")
public class CostDebitNoteController extends BaseController{

	public static final Logger LOGGER = LoggerFactory.getLogger(CostDebitNoteController.class);
	
	@Autowired
	CostDebitNoteService costDebitNoteService;
	
	
	@PutMapping("/updateCreateCostDebitNote")
	public ResponseEntity<ResponseDTO> updateCreateCostDebitNote(@Valid @RequestBody CostDebitNoteDTO CostDebitNoteDTO) {
		String methodName = "updateCreateCostDebitNote()";
		LOGGER.debug(CommonConstant.STARTING_METHOD, methodName);
		String errorMsg = null;
		Map<String, Object> responseObjectsMap = new HashMap<>();
		ResponseDTO responseDTO = null;

		try {
	        Map<String, Object> costDebitNote = costDebitNoteService.updateCreateCostDebitNote(CostDebitNoteDTO);
	        responseObjectsMap.put(CommonConstant.STRING_MESSAGE, costDebitNote.get("message"));
	        responseObjectsMap.put("costDebitNote", costDebitNote.get("costDebitNoteVO")); // Corrected key
	        responseDTO = createServiceResponse(responseObjectsMap);
	    } catch (Exception e) {
	        errorMsg = e.getMessage();
	        LOGGER.error(UserConstants.ERROR_MSG_METHOD_NAME, methodName, errorMsg);
	        responseDTO = createServiceResponseError(responseObjectsMap, errorMsg, errorMsg);
	    }
	    LOGGER.debug(CommonConstant.ENDING_METHOD, methodName);
	    return ResponseEntity.ok().body(responseDTO);
	}
	
}
