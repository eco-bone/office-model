package com.x.office.DAO;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.x.office.entities.HrPm;
import com.x.office.queryDTO.PmQueryDTO;

@Repository
public interface PmRepository extends JpaRepository<HrPm,UUID>{
	@Query(value = "SELECT NEW com.x.office.queryDTO.PmQueryDTO(e.uuid,e.address,e.name,e.dept,e.phoneNo,h.hrId,h.salary,h.team,hp.pmId,hp.designation,hp.peopleManaged)\r\n"
			+ "FROM Employee e\r\n"
			+ "JOIN Employee_HR_map ehm ON e.uuid = ehm.uuid\r\n"
			+ "JOIN HR h ON h.hrId = ehm.hrId\r\n"
			+ "JOIN HR_PM_map hpm ON h.hrId = hpm.hrId\r\n"
			+ "JOIN HrPm hp ON hpm.pmId = hp.pmId")
	List<PmQueryDTO> findAlLPmEmployees();
}
