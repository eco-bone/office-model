package com.x.office.queryDTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FEQueryDTO {
	private UUID uuid;
	private String address;
	private String name;
	private String dept;
	private int phoneNo;
	private UUID devId;
	private long salary;
	private String team;
	private UUID feId;
	private String designation;
	private int issuesLeft;
}
