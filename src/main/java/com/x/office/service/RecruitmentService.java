package com.x.office.service;

import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.x.office.dto.RecruitmentDto;
import com.x.office.queryDTO.RecQueryDTO;

public interface RecruitmentService {
	ResponseEntity<String> createRecruitment(RecruitmentDto recruitmentdto);
	
	ResponseEntity<String> updateRecruitment(RecQueryDTO rec);
	
	ResponseEntity<String> deleteRecruitment(UUID uuid);
}
