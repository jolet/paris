package tvz.nppjj.paris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.nppjj.paris.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

	User findByUsernameAndPassword(String username, String password);

}
