package com.app.covid.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.covid.domain.Role;
import com.app.covid.repository.RoleRepository;
import com.app.covid.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleDao;

	@Override
	public List<Role> getRoles() {
		return roleDao.findAll();
	}

}
