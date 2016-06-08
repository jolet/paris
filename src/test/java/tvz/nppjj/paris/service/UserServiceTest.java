package tvz.nppjj.paris.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.Role;
import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.enums.RoleType;
import tvz.nppjj.paris.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

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
        User userToSave = createUser();
        when(userRepository.save(any(User.class))).thenReturn(userToSave);

        // act
        User registeredUser = userService.registerUser(userToSave);

        // assert
        // TODO: move mapper from controller to service layer
        // assertThat(registeredUser.getUsername()).isEqualTo(userToSave.getEmail());
        assertThat(registeredUser.getEmail()).isEqualTo(userToSave.getEmail());
        assertThat(registeredUser.getPhone_number()).isEqualTo(userToSave.getPhone_number());
        assertThat(registeredUser.getRole().getName()).isEqualTo(userToSave.getRole().getName());

        verify(userRepository, times(1)).save(any(User.class));
    }

    // +------------ HELPER METHODS ------------+
    private Role createMockRole() {
        Role mockRole = new Role();
        mockRole.setName("mockRole");
        return mockRole;
    }

    private User createUser() {
        User user = new User();
        user.setEmail("CAFE@BABE.COM");
        user.setPassword("SuperSecurePassword");
        user.setPhone_number("666 999");
        return user;
    }

}
