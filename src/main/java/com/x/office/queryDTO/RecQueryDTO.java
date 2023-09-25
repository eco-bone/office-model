package com.x.office.queryDTO;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class RecQueryDTO {
	private UUID uuid;
	private String address;
	private String name;
	private String dept;
	private int phoneNo;
	private UUID hrId;
	private long salary;
	private String team;
	private UUID recruitmentId;
	private String designation;
	private int recruitments;
}

