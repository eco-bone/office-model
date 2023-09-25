package com.x.office.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.x.office.DAO.BackEndRepository;
import com.x.office.DAO.FrontEndRepository;
import com.x.office.DAO.PmRepository;
import com.x.office.DAO.RecruitmentRepository;
import com.x.office.dto.BackEndDto;
import com.x.office.dto.FrontEndDto;
import com.x.office.dto.PmDto;
import com.x.office.dto.RecruitmentDto;
import com.x.office.queryDTO.BEQueryDTO;
import com.x.office.queryDTO.FEQueryDTO;
import com.x.office.queryDTO.PmQueryDTO;
import com.x.office.queryDTO.RecQueryDTO;
import com.x.office.service.BackendService;
import com.x.office.service.FrontendService;
import com.x.office.service.PmService;
import com.x.office.service.RecruitmentService;

@RestController
@RequestMapping("/office")
public class Controller {
	private final BackEndRepository backendRepository;
	private final FrontEndRepository frontendRepository;
	private final PmRepository pmRepository;
	private final RecruitmentRepository recruitmentRepository;
	
	@Autowired
	public Controller(BackEndRepository backendRepository, FrontEndRepository frontendRepository,
			PmRepository pmRepository, RecruitmentRepository recruitmentRepository,
			RecruitmentService recruitmentService, PmService pmService, BackendService backendService,
			FrontendService frontendService) {
		super();
		this.backendRepository = backendRepository;
		this.frontendRepository = frontendRepository;
		this.pmRepository = pmRepository;
		this.recruitmentRepository = recruitmentRepository;
		this.recruitmentService = recruitmentService;
		this.pmService = pmService;
		this.backendService = backendService;
		this.frontendService = frontendService;
	}

	@Autowired
	private RecruitmentService recruitmentService;
	
	@Autowired
	private PmService pmService;
	
	@Autowired
	private BackendService backendService;
	
	@Autowired
	private FrontendService frontendService;
	
	@PostMapping("/create/recruitment")
	public ResponseEntity<String> createRecruitment(@RequestBody RecruitmentDto recruitmentdto){
		return recruitmentService.createRecruitment(recruitmentdto);
	}
	
	@PostMapping("/create/pm")
	public ResponseEntity<String> createPm(@RequestBody PmDto pmdto){
		return pmService.createPm(pmdto);
	}
	
	@PostMapping("/create/frontend")
	public ResponseEntity<String> createFrontend(@RequestBody FrontEndDto frontenddto){
		return frontendService.createFrontend(frontenddto);
	}
	
	@PostMapping("/create/backend")
	public ResponseEntity<String> createBackend(@RequestBody BackEndDto backenddto) {
	    return backendService.createBackend(backenddto);
	}
	
	@GetMapping("/get/backend")
	public List<BEQueryDTO> getBackendEmployees(){
		List<BEQueryDTO> backendEmployees = backendRepository.findAllBackendEmployees();
		return backendEmployees;
	}
	
	@GetMapping("/get/frontend")
	public List<FEQueryDTO> getFrontendEmployees(){
		List<FEQueryDTO> frontendEmployees = frontendRepository.findAllFrontEndEmployees();
	return frontendEmployees;
	}
	
	@GetMapping("/get/pm")
	public List<PmQueryDTO> getPmEmployees(){
		List<PmQueryDTO> pmEmployees = pmRepository.findAlLPmEmployees();
		return pmEmployees;
	}
	
	@GetMapping("/get/recruitment")
	public List<RecQueryDTO> getRecruitmentEmployees(){
		List<RecQueryDTO> recruitmentEmployees = recruitmentRepository.findAllRecruitmentEmployees();
		return recruitmentEmployees;
	}
	
	@PutMapping("/update/recruitment")
    public ResponseEntity<String> updateRecruitment(@RequestBody RecQueryDTO rec) {
        return recruitmentService.updateRecruitment(rec);
	}

	@PutMapping("/update/pm")
	public ResponseEntity<String> updatePm(@RequestBody PmQueryDTO pm){
		return pmService.updatePm(pm);
	}

	@PutMapping("/update/backend")
	public ResponseEntity<String> updateBackend(@RequestBody BEQueryDTO be){
		return backendService.updateBackend(be);
	}
	
	@PutMapping("/update/frontend")
	public ResponseEntity<String> updateFrontend(@RequestBody FEQueryDTO fe){
		return frontendService.updateFrontend(fe);
	}
	
	@DeleteMapping("/delete/backend/{uuid}")
	ResponseEntity<String> deleteBackend(@PathVariable("uuid") UUID uuid){
		return backendService.deleteBackend(uuid);
	}
	
	@DeleteMapping("/delete/frontend/{uuid}")
	ResponseEntity<String> deleteFrontend(@PathVariable("uuid") UUID uuid){
		return frontendService.deleteFrontend(uuid);
	}
	@DeleteMapping("/delete/pm/{uuid}")
	ResponseEntity<String> deletePm(@PathVariable("uuid") UUID uuid){
		return pmService.deletePm(uuid);
	}
	@DeleteMapping("/delete/")
	ResponseEntity<String> deleteRecruitment(@PathVariable("uuid") UUID uuid){
		return recruitmentService.deleteRecruitment(uuid);
	}
}
