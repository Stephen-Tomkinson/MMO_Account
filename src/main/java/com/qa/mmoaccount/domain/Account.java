package com.qa.mmoaccount.domain;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "account")
public class Account {

	// PK - Id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Name
	@Column
	@NotBlank(message = "Please Enter a name")
	private String name;

	// Level
	@Min(0)
	@Max(3600)
	@Column
	private int level;

	// Region
	@Column
	@NotBlank(message = "Please specify a server region")
	private String region;

	// Constructors[No Args, All Args[No ID], All Args
	public Account() { // No Args

	}

	public Account(String name, int level, String region) { // All Args[No ID]
		this.name = name;
		this.level = level;
		this.region = region;
	}

	public Account(Long id, String name, int level, String region) { // All Args
		this.id = id;
		this.name = name;
		this.level = level;
		this.region = region;
	}

	// Getters & Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	// HashCode
	@Override
	public int hashCode() {
		return Objects.hash(level, name, region);
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return level == other.level && Objects.equals(name, other.name) && Objects.equals(region, other.region);
	}

	// ToString
	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", level=" + level + ", region=" + region + "]";
	}
}
