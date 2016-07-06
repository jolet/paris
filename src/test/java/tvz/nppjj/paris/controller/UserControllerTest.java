package tvz.nppjj.paris.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
public class UserControllerTest {

	
	  @Autowired
	    private UserService userService;
	  
	  @Test
	  public void getAllUsers_ShouldReturnAllUsers(){
		  
	  }
	  
	  @Test
	  public void getUserById_ShouldReturnOneUser(){
		  
	  }
	  
	  
	  
}
