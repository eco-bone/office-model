package com.x.office.DAO;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.x.office.entities.HrRecruitment;
import com.x.office.queryDTO.RecQueryDTO;

@Repository
public interface RecruitmentRepository extends JpaRepository<HrRecruitment,UUID>{
	@Query(value = "SELECT NEW com.x.office.queryDTO.RecQueryDTO(e.uuid,e.address,e.name,e.dept,e.phoneNo,h.hrId,h.salary,h.team,hr.recruiterId,hr.designation,hr.recruitments)\r\n"
			+ "FROM Employee e\r\n"
			+ "JOIN Employee_HR_map ehm ON e.uuid = ehm.uuid\r\n"
			+ "JOIN HR h ON h.hrId = ehm.hrId\r\n"
			+ "JOIN HR_Recruitment_map hrm ON h.hrId = hrm.hrId\r\n"
			+ "JOIN HrRecruitment hr ON hrm.recruiterId = hr.recruiterId")
	List<RecQueryDTO> findAllRecruitmentEmployees();
}
