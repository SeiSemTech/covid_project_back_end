package com.app.covid.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.covid.domain.Privilege;
import com.app.covid.domain.Role;
import com.app.covid.domain.Usuario;
import com.app.covid.repository.PrivilegeRepository;
import com.app.covid.repository.RoleRepository;
import com.app.covid.repository.IUserRepository;
import com.app.covid.security.BCryptPasswordEncoder;

@Component
public class InitialDataLoader implements
ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private IUserRepository userRepository;

	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		Privilege readPrivilege
		= createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege writePrivilege
		= createPrivilegeIfNotFound("WRITE_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(
				readPrivilege, writePrivilege);        
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_CLIENT", Arrays.asList(readPrivilege));

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		Role clientRole = roleRepository.findByName("ROLE_CLIENT");
		if(userRepository.findByUser("admin")==null) {
			Usuario user = new Usuario(0L, "USER", "NAME", "admin", passwordEncoder.encode("admin"), "12154214", 
					new Date(), true, null);
			ArrayList<Role> roles = new ArrayList<Role>();
			roles.add(adminRole);
			roles.add(clientRole);
			user.setRoles(roles);
			userRepository.save(user);
		}
		alreadySetup = true;
	}

	@Transactional
	private Privilege createPrivilegeIfNotFound(String name) {

		Privilege privilege = privilegeRepository.findByName(name);
		if (privilege == null) {
			privilege = new Privilege(null, name, null);
			privilegeRepository.save(privilege);
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(
			String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(null, name, null, privileges);
			roleRepository.save(role);
		}
		return role;
	}
}