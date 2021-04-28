package com.app.covid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.covid.domain.Usuario;

public interface IUserRepository extends CrudRepository<Usuario, Long> {
	
	@Query(value = "Select * FROM Usuario u WHERE u.cedula = ?1", nativeQuery = true)
	Usuario findByUser(String user);
	
	@Query(value = "Select * FROM Usuario u WHERE u.cedula = ? AND u.password = ?", nativeQuery = true)
	Usuario findByLogin(String user,String pss);

}
