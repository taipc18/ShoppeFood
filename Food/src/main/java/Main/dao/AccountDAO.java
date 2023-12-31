package Main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Main.entity.Account;



public interface AccountDAO extends JpaRepository<Account, String>{

	
	@Query("SELECT DISTINCT ar.account FROM Authority ar WHERE ar.role.id IN ('DIRE', 'STAF')")
	List<Account> getAdministrators();
	
	@Query("SELECT u FROM Account u WHERE u.username =?1")
    Account findAccountsByUsername(String username);
	
	@Query("SELECT u FROM Account u WHERE u.email = ?1")
	   public Account findByEmail(String email);
}
