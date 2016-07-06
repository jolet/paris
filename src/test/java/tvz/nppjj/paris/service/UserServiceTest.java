package tvz.nppjj.paris.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.Role;
import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.RegistrationCommand;
import tvz.nppjj.paris.model.dto.UserDto;
import tvz.nppjj.paris.model.enums.RoleType;
import tvz.nppjj.paris.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService    roleService;

    @InjectMocks
    private UserService    userService;

    @Before
    public void setup() {
        userService = new UserServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterUser_whenValidRegistrationData_thenNoExceptionIsThrown() throws Exception {

        // prepare
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        when(roleService.findByRoleType(any(RoleType.class))).thenReturn(createMockRole());
        RegistrationCommand registrationCommand = createRegistrationCommand();
        when(userRepository.save(any(User.class))).thenAnswer(userRegistrationAnswer);

        // act
        User registeredUser = userService.registerUser(registrationCommand);

        // assert
        assertThat(registeredUser.getUsername()).isEqualTo(registrationCommand.getEmail());
        assertThat(registeredUser.getEmail()).isEqualTo(registrationCommand.getEmail());
        assertThat(registeredUser.getPhone_number()).isEqualTo(registrationCommand.getPhoneNumber());
        assertThat(registeredUser.getRole().getName()).isNotNull();

        verify(userRepository, times(1)).save(any(User.class));
    }
    
    @Test
    public void testLoginUser_whenValidLoginData_thenNoExceptionIsThrown() throws Exception{
    	 // prepare
    	Role role=new Role();
    	role.setName("User");
    	BigDecimal acc= new BigDecimal(10);
      User testUser=new User();
      testUser.setUsername("ejosip");
      testUser.setPassword("lozinka");
      testUser.setAccount(acc);
      testUser.setEmail("moje@moje.com");
      testUser.setId(569l);
      testUser.setPhone_number("123454");
      testUser.setRole(role);
      when(userRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(createMySuperUser());
      
    	
        // act
        UserDto loginUser = userService.loginUser("ejosip","lozinka");

        // assert
        assertThat(loginUser.getUsername()).isEqualTo(testUser.getUsername());
        //verify(userRepository, times(1)).save(any(User.class));
    }
    
    
    private User createMySuperUser() {
    	Role role=new Role();
    	role.setName("User");
    	BigDecimal acc= new BigDecimal(10);
      User testUser=new User();
      testUser.setUsername("ejosip");
      testUser.setPassword("lozinka");
      testUser.setAccount(acc);
      testUser.setEmail("moje@moje.com");
      testUser.setId(569l);
      testUser.setPhone_number("123454");
      testUser.setRole(role);
		return testUser;
	}

	// +------------ HELPER METHODS ------------+
    private Role createMockRole() {
        Role mockRole = new Role();
        mockRole.setName("mockRole");
        return mockRole;
    }
        
    private RegistrationCommand createRegistrationCommand() {
        RegistrationCommand command = new RegistrationCommand();
        command.setEmail("CAFE@BABE.COM");
        command.setPassword("SuperSecurePassword");
        command.setPhoneNumber("666 999");
        return command;
    }

    // mock response for userRepository.save
    private Answer<User> userRegistrationAnswer = new Answer<User>() {
        @Override
        public User answer(InvocationOnMock invocation) throws Throwable {
            User userArgument = invocation.getArgument(0);

            assertThat(userArgument.getRole()).isNotNull();
            return userArgument;
        }
    };

}
