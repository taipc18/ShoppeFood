package Main.service;

import java.util.List;

import Main.entity.Account;

public interface AccountService {
	Account findById(String username);

	List<Account> getAdministrators();

	List<Account> findAll();
	
	List<Account> getAllAccount();
	
	Account findAccountsByUsername(String username);
	
	Account findByEmail(String email);
}
