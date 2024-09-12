package defix.favordayapp.db.postgresql.repositories;

import defix.favordayapp.db.postgresql.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
