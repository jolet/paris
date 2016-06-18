package tvz.nppjj.paris.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.command.UserLoginCommand;
import tvz.nppjj.paris.model.dto.UserDto;
import tvz.nppjj.paris.service.UserService;

@RestController
public class UserLoginController {

	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public UserDto mapFields(@Valid @RequestBody UserLoginCommand userLoginCommand) {
		return userService.loginUser(userLoginCommand.getUsername(), userLoginCommand.getPassword());
		
		
	}
	

}
