package com.x.office.serviceImplementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.x.office.DAO.BackEndRepository;
import com.x.office.DAO.DevRepository;
import com.x.office.DAO.EmployeeRepository;
import com.x.office.DAO.FrontEndRepository;
import com.x.office.DAO.HrRepository;
import com.x.office.DAO.PmRepository;
import com.x.office.DAO.RecruitmentRepository;
import com.x.office.dto.FrontEndDto;
import com.x.office.entities.Dev;
import com.x.office.entities.DevBackend;
import com.x.office.entities.DevFrontEnd;
import com.x.office.entities.Employee;
import com.x.office.entities.HR;
import com.x.office.entities.HrPm;
import com.x.office.entities.HrRecruitment;
import com.x.office.mapDao.DevBackendMapRepository;
import com.x.office.mapDao.DevFrontendMapRepository;
import com.x.office.mapDao.EmployeeDevMapRepository;
import com.x.office.mapDao.EmployeeHRMapRepository;
import com.x.office.mapDao.HRPMMapRepository;
import com.x.office.mapDao.HRRecruitmentMapRepository;
import com.x.office.maps.Dev_Backend_map;
import com.x.office.maps.Dev_FrontEnd_map;
import com.x.office.maps.Employee_Dev_map;
import com.x.office.maps.Employee_HR_map;
import com.x.office.maps.HR_PM_map;
import com.x.office.maps.HR_Recruitment_map;
import com.x.office.queryDTO.FEQueryDTO;
import com.x.office.service.FrontendService;

@Service
public class FrontendServiceImplementation implements FrontendService{
	
	@Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RecruitmentRepository recruitmentRepository;

    @Autowired
    private HrRepository hrRepository;

    @Autowired
    private PmRepository pmRepository;

    @Autowired
    private EmployeeHRMapRepository employeehrmaprepo;

    @Autowired
    private DevRepository devRepository;

    @Autowired
    private FrontEndRepository frontendRepository;

    @Autowired
    private BackEndRepository backendRepository;

    @Autowired
    private DevFrontendMapRepository devfrontendmaprepo;

    @Autowired
    private DevBackendMapRepository devbackendmaprepo;

    @Autowired
    private HRRecruitmentMapRepository hrRecruitmentmaprepo;

    @Autowired
    private HRPMMapRepository hrpmMaprepo;
    
    @Autowired
    private EmployeeDevMapRepository employeedevmaprepo;
	
	public ResponseEntity<String> createFrontend(FrontEndDto frontenddto){
		Dev_FrontEnd_map devfrontendmap = new Dev_FrontEnd_map();
		Employee_Dev_map employeedevmap = new Employee_Dev_map();
		Employee employee = new Employee();
		DevFrontEnd frontend = new DevFrontEnd();
		
		Dev dev = new Dev();
		if(frontenddto.getName().isBlank()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot leave Name field empty");
		}
		employee.setName(frontenddto.getName());
		employee.setAddress(frontenddto.getAddress());
		employee.setPhoneNo(frontenddto.getPhoneNo());
		employee.setDept(frontenddto.getDept());
		dev.setSalary(frontenddto.getSalary());
		dev.setTeam(frontenddto.getTeam());
		frontend.setDesignation(frontenddto.getDesignation());
		frontend.setIssuesLeft(frontenddto.getIssuesLeft());
		
		employeeRepository.save(employee);
		devRepository.save(dev);
		frontendRepository.save(frontend);
		devfrontendmap.setDevId(dev.getDevId());
		devfrontendmap.setFeId(frontend.getFeId());
		devfrontendmaprepo.save(devfrontendmap);
		employeedevmap.setDevId(dev.getDevId());
		employeedevmap.setUuid(employee.getUuid());
		
		employeedevmaprepo.save(employeedevmap);
		
		return ResponseEntity.ok("Entry created successfully");
	}
	
    public ResponseEntity<String> updateFrontend(FEQueryDTO fe) {
		Employee employee = employeeRepository.findById(fe.getUuid()).orElse(null);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Id invalid.");
		}
		if (!employee.getDept().equals(fe.getDept())) {

			frontendRepository.delete(frontendRepository.findById(fe.getFeId()).orElse(null));
			devfrontendmaprepo.delete(devfrontendmaprepo.findById(fe.getDevId()).orElse(null));
			devRepository.delete(devRepository.findById(fe.getDevId()).orElse(null));
			employeedevmaprepo.delete(employeedevmaprepo.findById(fe.getUuid()).orElse(null));

			Employee_HR_map employeehrmap = new Employee_HR_map();
			HR hr = new HR();
			employee.setDept("HR");
			hr.setSalary(fe.getSalary());

			if (fe.getTeam().equals("PM")) {
				HR_PM_map hr_PM_map = new HR_PM_map();
				HrPm hrpm = new HrPm();
				hrpm.setDesignation(fe.getDesignation());
				hrpm.setPeopleManaged(0);
				hr.setTeam(fe.getTeam());

				employeeRepository.save(employee);
				hrRepository.save(hr);
				pmRepository.save(hrpm);
				hr_PM_map.setPmId(hrpm.getPmId());
				hr_PM_map.setHrId(hr.getHrId());
				hrpmMaprepo.save(hr_PM_map);
				employeehrmap.setHrId(hr.getHrId());
				employeehrmap.setUuid(employee.getUuid());
				employeehrmaprepo.save(employeehrmap);
				
				return ResponseEntity.ok("Update Success.");
			} else {
				HR_Recruitment_map hr_recruitment_map = new HR_Recruitment_map();
				HrRecruitment hrRecruitment = new HrRecruitment();
				hrRecruitment.setDesignation(fe.getDesignation());
				hrRecruitment.setRecruitments(0);
				hr.setTeam(fe.getTeam());
				
				employeeRepository.save(employee);
				hrRepository.save(hr);
				recruitmentRepository.save(hrRecruitment);
				hr_recruitment_map.setRecruiterId(hrRecruitment.getRecruiterId());
				hr_recruitment_map.setHrId(hr.getHrId());
				hrRecruitmentmaprepo.save(hr_recruitment_map);
				employeehrmap.setHrId(hr.getHrId());
				employeehrmap.setUuid(employee.getUuid());
				employeehrmaprepo.save(employeehrmap);
				
				return ResponseEntity.ok("Update Success.");
			}
		}
		
		Dev dev = devRepository.findById(fe.getDevId()).orElse(null);
		Employee_Dev_map employeedevmap = new Employee_Dev_map();
		dev.setSalary(fe.getSalary());
		if(!dev.getTeam().equals(fe.getTeam())) {
			Dev_Backend_map devbackendmap = new Dev_Backend_map();
			devfrontendmaprepo.delete(devfrontendmaprepo.findById(fe.getDevId()).orElse(null));
			frontendRepository.delete(frontendRepository.findById(fe.getFeId()).orElse(null));
			DevBackend backend = new DevBackend();
			dev.setTeam(fe.getTeam());
			backend.setDesignation(fe.getDesignation());
			backend.setIssuesLeft(0);
			
			devRepository.save(dev);
			backendRepository.save(backend);
			devbackendmap.setDevId(dev.getDevId());
			devbackendmap.setBeId(backend.getBeId());
			devbackendmaprepo.save(devbackendmap);
			employeedevmap.setDevId(dev.getDevId());
			employeedevmap.setUuid(employee.getUuid());
			employeedevmaprepo.save(employeedevmap);
			
			return ResponseEntity.ok("Update Success.");
			
		} else {
			Dev_FrontEnd_map devfrontendmap = devfrontendmaprepo.findById(fe.getDevId()).orElse(null);
			DevFrontEnd frontend = frontendRepository.findById(fe.getFeId()).orElse(null);
			frontend.setDesignation(fe.getDesignation());
			frontend.setIssuesLeft(fe.getIssuesLeft());
			
			devRepository.save(dev);
			frontendRepository.save(frontend);
			devfrontendmap.setDevId(dev.getDevId());
			devfrontendmap.setFeId(frontend.getFeId());
			devfrontendmaprepo.save(devfrontendmap);
			employeedevmap.setDevId(dev.getDevId());
			employeedevmap.setUuid(employee.getUuid());
			
			employeedevmaprepo.save(employeedevmap);
			return ResponseEntity.ok("Update Successful.");
		}
	}
    
    public ResponseEntity<String> deleteFrontend(UUID uuid) {
		Employee emp = employeeRepository.findById(uuid).orElse(null);
		if(emp == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
		}
		Employee_Dev_map employeedevmap = employeedevmaprepo.findById(uuid).orElse(null);
		Dev dev = devRepository.findById(employeedevmap.getDevId()).orElse(null);
		Dev_FrontEnd_map devfrontendmap = devfrontendmaprepo.findById(dev.getDevId()).orElse(null);
		DevFrontEnd frontend = frontendRepository.findById(devfrontendmap.getFeId()).orElse(null);
		
		employeeRepository.delete(emp);
		employeedevmaprepo.delete(employeedevmap);
		devRepository.delete(dev);
		devfrontendmaprepo.delete(devfrontendmap);
		frontendRepository.delete(frontend);
		
		return ResponseEntity.ok("Employee Deleted.");
	}
}
