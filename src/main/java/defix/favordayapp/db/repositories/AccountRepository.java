package defix.favordayapp.db.repositories;

import defix.favordayapp.db.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AccountRepository extends JpaRepository<Account, BigInteger> {
    Account findByUsername(String username);
}
