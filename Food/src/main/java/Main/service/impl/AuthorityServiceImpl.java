package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.AccountDAO;
import Main.dao.AuthorityDAO;
import Main.entity.Account;
import Main.entity.Authority;
import Main.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService{
	@Autowired
	AuthorityDAO dao;
	@Autowired
	AccountDAO adao;

	@Override
	public List<Authority> findAll() {
		return dao.findAll();
	}

	@Override
	public List<Authority> findAuthoritiesOfAdministrators() {
		List<Account> acc = adao.getAdministrators();
		return dao.authoritiesOf(acc);
	}

	@Override
	public Authority create(Authority auth) {
		return dao.save(auth);
	}

	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}

}
