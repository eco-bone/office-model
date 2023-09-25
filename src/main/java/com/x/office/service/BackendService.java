package com.x.office.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.x.office.dto.BackEndDto;
import com.x.office.queryDTO.BEQueryDTO;

public interface BackendService {
	ResponseEntity<String> updateBackend(BEQueryDTO be);
	
	ResponseEntity<String> createBackend(BackEndDto backenddto);
	
	ResponseEntity<String> deleteBackend(UUID uuid);
}
