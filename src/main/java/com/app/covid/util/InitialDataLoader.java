package com.app.covid.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.app.covid.domain.*;
import com.app.covid.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.covid.security.BCryptPasswordEncoder;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PrivilegeRepository privilegeRepository;

	@Autowired
	private IEstado_loteRepository estadoRepository;

	@Autowired
	private ILaboratorioRepository laboratorioRepository;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ICentroSalud centroRepository;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;

		Laboratorio lab = createLaboIfNotFound(1L, "Pfizer");
		Laboratorio lab2 = createLaboIfNotFound(2L, "AstraZeneca");
		Laboratorio lab3 = createLaboIfNotFound(3L, "Johnson's & Johnson's");
		Estado_lote est = createEstadoIfNotFound(1L, "Registrado");
		Estado_lote est2 = createEstadoIfNotFound(2L, "Enviado");
		Estado_lote est3 = createEstadoIfNotFound(3L, "Recibido");

		Privilege readPrivilege = createPrivilegeIfNotFound("PERSONAL_PRIVILEGE");
		Privilege writePrivilege = createPrivilegeIfNotFound("ADMIN_PRIVILEGE");
		Privilege au = createPrivilegeIfNotFound("AUXILIAR_PRIVILEGE");
		Privilege coo = createPrivilegeIfNotFound("COORDINADOR_PRIVILEGE");

		List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege, au, coo);
		createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
		createRoleIfNotFound("ROLE_PERSONAL", Arrays.asList(readPrivilege));
		createRoleIfNotFound("ROLE_AUXILIAR", Arrays.asList(au));
		createRoleIfNotFound("ROLE_COORDINADOR", Arrays.asList(coo));

		Role adminRole = roleRepository.findByName("ROLE_ADMIN");
		Role auxiliarRole = roleRepository.findByName("ROLE_AUXILIAR");
		Role personalRole = roleRepository.findByName("ROLE_PERSONAL");
		Role coordinadorRole = roleRepository.findByName("ROLE_COORDINADOR");
		if (userRepository.findByUser("admin") == null) {
			Usuario user = new Usuario(0L, "USER", "NAME", "admin", passwordEncoder.encode("admin"), "12154214",
					new Date(), true, null);
			ArrayList<Role> roles = new ArrayList<Role>();
			roles.add(adminRole);
			roles.add(auxiliarRole);
			roles.add(personalRole);
			roles.add(coordinadorRole);
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
	private Laboratorio createLaboIfNotFound(Long id, String name) {

		Laboratorio lab = new Laboratorio(id, name);

		laboratorioRepository.save(lab);

		return lab;
	}

	@Transactional
	private Estado_lote createEstadoIfNotFound(Long id, String esta) {

		Estado_lote es = new Estado_lote(id, esta);

		estadoRepository.save(es);

		return es;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role(null, name, null, name, privileges);
			roleRepository.save(role);
		}
		return role;
	}

}
