package com.app.covid.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.covid.domain.Usuario;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<Usuario, Long> {
	
	@Query("Select u FROM Usuario u WHERE u.username = :user")
	Usuario findByUser(@Param("user") String user);
	
	@Query("Select u FROM Usuario u WHERE u.document = :user")
	Usuario findByDocumento(@Param("user") String user);
	
	@Query("Select u FROM Usuario u WHERE u.username = :user AND u.password = :pss")
	Usuario findByLogin(@Param("user") String user, @Param("user") String pss);
	
	@Query("Select u FROM Usuario u WHERE u.id = :user")
	Usuario findBy(@Param("user") Long user);


}
