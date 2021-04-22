package com.app.covid.repository;

import org.springframework.data.repository.CrudRepository;

import com.app.covid.domain.Usuario;


public interface IUserRepository extends CrudRepository<Usuario, Long>  {

}
