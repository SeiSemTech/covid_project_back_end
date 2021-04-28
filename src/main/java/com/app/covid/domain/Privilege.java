package com.app.covid.domain;

import java.util.Collection;

import javax.persistence.*;

@Entity
@Table(name = "privilege")
public class Privilege {

	public Privilege(String name) {
		super();
		this.name = name;
	}
	
	public Privilege() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}