package com.x.office.queryDTO;

import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BEQueryDTO {
	
	
//	public BEQueryDTO(UUID uuid, String address, String name, int phoneNo, UUID devId, long salary, String team,
//			UUID beId, String designation, int issuesLeft) {
//		super();
//		this.uuid = uuid;
//		this.address = address;
//		this.name = name;
//		this.phoneNo = phoneNo;
//		this.devId = devId;
//		this.salary = salary;
//		this.team = team;
//		this.beId = beId;
//		this.designation = designation;
//		this.issuesLeft = issuesLeft;
//	}
	
	private UUID uuid;
	private String address;
	private String name;
	private String dept;
	private int phoneNo;
	private UUID devId;
	private long salary;
	private String team;
	private UUID beId;
	private String designation;
	private int issuesLeft;
}

