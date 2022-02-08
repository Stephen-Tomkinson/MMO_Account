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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // PK - Id

	@Column
	@NotBlank(message = "Please Enter a name")
	private String name; // Name

	@Min(0)
	@Max(3600)
	@Column
	private int level; // Level

	@Column
	@NotBlank(message = "Please specify a server region")
	private String region; // Region

	public Account() { // No Args Constructor

	}

	public Account(String name, int level, String region) { // All Args [No ID] Constructor
		this.name = name;
		this.level = level;
		this.region = region;
	}

	public Account(Long id, String name, int level, String region) { // All Args Constructor
		this.id = id;
		this.name = name;
		this.level = level;
		this.region = region;
	}

	public Long getId() { // Getters & Setters
		return id;
	}

	public void setId(Long id) { // Getters & Setters
		this.id = id;
	}

	public String getName() { // Getters & Setters
		return name;
	}

	public void setName(String name) { // Getters & Setters
		this.name = name;
	}

	public int getLevel() { // Getters & Setters
		return level;
	}

	public void setLevel(int level) { // Getters & Setters
		this.level = level;
	}

	public String getRegion() { // Getters & Setters
		return region;
	}

	public void setRegion(String region) { // Getters & Setters
		this.region = region;
	}

	@Override
	public int hashCode() { // HashCode
		return Objects.hash(level, name, region);
	}

	@Override
	public boolean equals(Object obj) { // Equals
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return level == other.level && Objects.equals(name, other.name) && Objects.equals(region, other.region);
	}

	@Override
	public String toString() { // ToString
		return "Account [id=" + id + ", name=" + name + ", level=" + level + ", region=" + region + "]";
	}
}
