package com.x.office.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.x.office.dto.PmDto;
import com.x.office.queryDTO.PmQueryDTO;

public interface PmService {
	ResponseEntity<String> updatePm(PmQueryDTO pm);
	
	ResponseEntity<String> createPm(PmDto pmdto);
	
	ResponseEntity<String> deletePm(UUID uuid);
}
