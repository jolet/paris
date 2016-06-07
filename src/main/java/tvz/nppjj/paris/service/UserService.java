package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.UserDto;

public interface UserService {

	List<User> getAllUsers();

	void saveUser(User user);

	User getUserById(Long id);

	void registerUser(User user);
	
	UserDto loginUser(String username, String password);
}
