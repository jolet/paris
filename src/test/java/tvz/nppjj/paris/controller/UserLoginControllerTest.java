package tvz.nppjj.paris.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import tvz.nppjj.paris.command.UserLoginCommand;
import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.dto.LoginResponseDto;
import tvz.nppjj.paris.model.dto.RegistrationCommand;
import tvz.nppjj.paris.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class UserLoginControllerTest {
	 @Value("${local.server.port}")
	    int	port;
	 
	 private String	loginApiUrl;
	 private RestTemplate restTemplate = new TestRestTemplate();
	
	  @Autowired
	    private UserService userService;
	  
	  @Test
	  public void testAuthenticateUser_whenUserExists_thenAuthenticateUser(){
		  // prepare
	       // long userCount = userRepository.count();
	        HttpEntity<UserLoginCommand> loginRequest = createLoginRequest();

	        // act
	        ResponseEntity<LoginResponseDto> responseEntity = restTemplate.postForEntity(loginApiUrl, loginRequest, LoginResponseDto.class);

	        // assert
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	        //assertThat(userRepository.findAll()).isNotEmpty().hasSize((int) userCount + 1);
	    }
		  
		  
	  
	  @Before
	    public void setUp() {
		  loginApiUrl = "http://localhost:" + port + "/login/";
	    }
	  
	// +------------ HELPER METHODS ------------+
	    private HttpEntity<UserLoginCommand> createLoginRequest() {
	    	UserLoginCommand loginCommand = createLoginDto();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        HttpEntity<UserLoginCommand> request = new HttpEntity<UserLoginCommand>(loginCommand, headers);
	        return request;
	    }
	    
	    private UserLoginCommand createLoginDto() {
	        UserLoginCommand loginCommand = new UserLoginCommand();
	        loginCommand.setUsername("ejosip");
	        loginCommand.setPassword("lozinka");
	        return loginCommand;
	    }
	

}
