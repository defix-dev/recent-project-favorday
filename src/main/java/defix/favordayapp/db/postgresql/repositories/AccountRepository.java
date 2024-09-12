package defix.favordayapp.db.postgresql.repositories;

import defix.favordayapp.db.postgresql.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface AccountRepository extends JpaRepository<Account, BigInteger> {
    Account findByUsername(String username);
}
