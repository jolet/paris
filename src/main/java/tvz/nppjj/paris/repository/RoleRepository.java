package tvz.nppjj.paris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.nppjj.paris.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}
