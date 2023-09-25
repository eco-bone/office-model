package com.x.office.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class HrPm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID pmId;
	private String designation;
	private int peopleManaged;
}
