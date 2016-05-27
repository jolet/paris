package tvz.nppjj.paris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void saveUser(User user) {
		userRepository.saveAndFlush(user);

	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findOne(id);
	}

}
