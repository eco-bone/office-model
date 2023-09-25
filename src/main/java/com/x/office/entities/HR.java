package com.x.office.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HR {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID hrId;
	private String team;
	private long salary;
}
