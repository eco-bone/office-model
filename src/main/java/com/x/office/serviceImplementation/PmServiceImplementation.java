package com.x.office.serviceImplementation;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.x.office.DAO.BackEndRepository;
import com.x.office.DAO.DevRepository;
import com.x.office.DAO.EmployeeRepository;
import com.x.office.DAO.FrontEndRepository;
import com.x.office.DAO.HrRepository;
import com.x.office.DAO.PmRepository;
import com.x.office.DAO.RecruitmentRepository;
import com.x.office.dto.PmDto;
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
import com.x.office.queryDTO.PmQueryDTO;
import com.x.office.service.PmService;

@Service
public class PmServiceImplementation implements PmService{
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
    
    @Override
    public ResponseEntity<String> updatePm(@RequestBody PmQueryDTO pm) {
		Employee employee = employeeRepository.findById(pm.getUuid()).orElse(null);
		if (employee == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Id invalid.");
		}
		if (!employee.getDept().equals(pm.getDept())) {
			pmRepository.delete(pmRepository.findById(pm.getPmId()).orElse(null));
			hrpmMaprepo.delete(hrpmMaprepo.findById(pm.getHrId()).orElse(null));
			employeehrmaprepo.delete(employeehrmaprepo.findById(pm.getUuid()).orElse(null));
			hrRepository.delete(hrRepository.findById(pm.getHrId()).orElse(null));

			Employee_Dev_map employeedevmap = new Employee_Dev_map();
			Dev dev = new Dev();
			employee.setDept("Dev");
			dev.setSalary(pm.getSalary());

			if (pm.getTeam().equals("FrontEnd")) {
				Dev_FrontEnd_map devfrontendmap = new Dev_FrontEnd_map();
				DevFrontEnd frontend = new DevFrontEnd();

				dev.setTeam("FrontEnd");
				frontend.setDesignation(pm.getDesignation());
				frontend.setIssuesLeft(0);

				employeeRepository.save(employee);
				devRepository.save(dev);
				frontendRepository.save(frontend);
				devfrontendmap.setDevId(dev.getDevId());
				devfrontendmap.setFeId(frontend.getFeId());
				devfrontendmaprepo.save(devfrontendmap);
				employeedevmap.setDevId(dev.getDevId());
				employeedevmap.setUuid(employee.getUuid());

				employeedevmaprepo.save(employeedevmap);

				return ResponseEntity.ok("Update Success 1.");
			} else {
				Dev_Backend_map devbackendmap = new Dev_Backend_map();
				DevBackend backend = new DevBackend();
				backend.setDesignation(pm.getDesignation());
				backend.setIssuesLeft(0);
				dev.setTeam("BackEnd");
				employeeRepository.save(employee);
				devRepository.save(dev);
				backendRepository.save(backend);
				devbackendmap.setDevId(dev.getDevId());
				devbackendmap.setBeId(backend.getBeId());
				devbackendmaprepo.save(devbackendmap);
				employeedevmap.setDevId(dev.getDevId());
				employeedevmap.setUuid(employee.getUuid());

				employeedevmaprepo.save(employeedevmap);
				return ResponseEntity.ok("Update Success 2.");
			}
		}

		HR hr = hrRepository.findById(pm.getHrId()).orElse(null);
		hr.setSalary(pm.getSalary());
		Employee_HR_map employeehrmap = employeehrmaprepo.findById(pm.getUuid()).orElse(null);
		if (!pm.getTeam().equals(hr.getTeam())) {
			
			HR_Recruitment_map hr_recruitment_map = new HR_Recruitment_map();
			hrpmMaprepo.delete(hrpmMaprepo.findById(pm.getHrId()).orElse(null));
			pmRepository.delete(pmRepository.findById(pm.getPmId()).orElse(null));
			HrRecruitment hrRecruitment = new HrRecruitment();
			hr.setTeam(pm.getTeam());
			hrRecruitment.setDesignation(pm.getDesignation());
			hrRecruitment.setRecruitments(0);
			
			employeeRepository.save(employee);
			hrRepository.save(hr);
			recruitmentRepository.save(hrRecruitment);
			hr_recruitment_map.setRecruiterId(hrRecruitment.getRecruiterId());
			hr_recruitment_map.setHrId(hr.getHrId());
			hrRecruitmentmaprepo.save(hr_recruitment_map);
			employeehrmap.setHrId(hr.getHrId());
			employeehrmap.setUuid(employee.getUuid());
			employeehrmaprepo.save(employeehrmap);

			// SAVE THE UPDATED ENTRY IN ALL REPOSITORIES
			return ResponseEntity.ok("Update Success.");
		} else {
			HrPm hrpm = pmRepository.findById(pm.getPmId()).orElse(null);
			HR_PM_map hr_PM_map = hrpmMaprepo.findById(pm.getHrId()).orElse(null);
			hrpm.setDesignation(pm.getDesignation());
			hrpm.setPeopleManaged(pm.getPeopleManaged());
			
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
		}
	}
    
    @Override
    public ResponseEntity<String> createPm(PmDto pmdto){
		HR_PM_map hr_PM_map = new HR_PM_map();
		Employee_HR_map employeehrmap = new Employee_HR_map();
		Employee employee = new Employee();
		HrPm hrpm = new HrPm();
		HR hr = new HR();
		
		if(pmdto.getName().isBlank()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cannot leave Name field empty.");
		}
		employee.setName(pmdto.getName());
		employee.setAddress(pmdto.getAddress());
		employee.setPhoneNo(pmdto.getPhoneNo());
		employee.setDept(pmdto.getDept());
		hr.setSalary(pmdto.getSalary());
		hr.setTeam(pmdto.getTeam());
		hrpm.setDesignation(pmdto.getDesignation());
		hrpm.setPeopleManaged(pmdto.getPeopleManaged());
		
		employeeRepository.save(employee);
		hrRepository.save(hr);
		pmRepository.save(hrpm);
		hr_PM_map.setPmId(hrpm.getPmId());
		hr_PM_map.setHrId(hr.getHrId());
		hrpmMaprepo.save(hr_PM_map);
		employeehrmap.setHrId(hr.getHrId());
		employeehrmap.setUuid(employee.getUuid());
		employeehrmaprepo.save(employeehrmap);
		
		return ResponseEntity.ok("Created entry successfully.");
	}
    
    @Override
    public ResponseEntity<String> deletePm(UUID uuid) {
		Employee emp = employeeRepository.findById(uuid).orElse(null);
		if(emp == null) {
			ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee Not Found");
		}
		Employee_HR_map employeehrmap = employeehrmaprepo.findById(uuid).orElse(null);
		HR hr = hrRepository.findById(employeehrmap.getHrId()).orElse(null);
		HR_PM_map hrpmmap = hrpmMaprepo.findById(hr.getHrId()).orElse(null);
		HrPm hrpm = pmRepository.findById(hrpmmap.getPmId()).orElse(null);
		
		employeeRepository.delete(emp);
		employeehrmaprepo.delete(employeehrmap);
		hrRepository.delete(hr);
		hrpmMaprepo.delete(hrpmmap);
		pmRepository.delete(hrpm);
		
		return ResponseEntity.ok("Employee Deleted.");
	}
}
