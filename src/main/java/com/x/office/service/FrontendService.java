package com.x.office.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.x.office.dto.FrontEndDto;
import com.x.office.queryDTO.FEQueryDTO;

public interface FrontendService {
	ResponseEntity<String> createFrontend(FrontEndDto frontenddto);
	
	ResponseEntity<String> updateFrontend(FEQueryDTO fe);
	
	ResponseEntity<String> deleteFrontend(UUID uuid);
}
