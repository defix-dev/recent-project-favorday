package defix.favordayapp.db.repositories;

import defix.favordayapp.db.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
