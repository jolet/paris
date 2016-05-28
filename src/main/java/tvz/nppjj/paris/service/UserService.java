package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.User;

public interface UserService {

	List<User> getAllUsers();

	void saveUser(User user);

	User getUserById(Long id);

	void registerUser(User user);
}
