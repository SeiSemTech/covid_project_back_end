package com.app.covid.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.covid.domain.Usuario;
import com.app.covid.repository.IUserRepository;
import com.app.covid.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userDao;

	@Override
	public List<Usuario> getUsuarios() {
		return (List<Usuario>) userDao.findAll();
	}

	@Override
	public void createUsuario(Usuario user) {
		userDao.save(user);
	}

	@Override
	public Usuario updateUsuario(Usuario user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUsuario(Long id) {
		userDao.deleteById(id);

	}

	@Override
	public Optional<Usuario> findByIdUsuario(Long id) {
		return userDao.findById(id);
	}

	@Override
	public Usuario findByUser(String user) {
		return userDao.findByUser(user);
	}

	@Override
	public Usuario findByLogin(String user, String pass) {
		return userDao.findByLogin(user, pass);
	}

	@Override
	public Usuario findBy(Long id) {
		return userDao.findBy(id);
	}
	
	

}