package com.app.covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.covid.domain.Role;



public interface RoleRepository extends JpaRepository<Role, Integer> {
	@Query(value = "SELECT * FROM ROLE WHERE name = ?1", nativeQuery = true)
	Role findByName(String name);
}
