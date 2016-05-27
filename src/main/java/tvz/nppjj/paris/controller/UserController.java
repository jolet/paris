package tvz.nppjj.paris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.service.UserService;

public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public List<User> getAllUsers() {
    	return userService.getAllUsers();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User getUserById(@PathVariable("id") Long id) {
    	return userService.getUserById(id);
	}
	
	
	public String saveUser(User user){
		userService.saveUser(user);
		return "Saved";
	}
		
	
}
