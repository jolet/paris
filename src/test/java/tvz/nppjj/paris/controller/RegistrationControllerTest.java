package tvz.nppjj.paris.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.Role;
import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.RegistrationDto;
import tvz.nppjj.paris.model.enums.RoleType;
import tvz.nppjj.paris.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
// @IntegrationTest("randomPort = true")
@IntegrationTest()
public class RegistrationControllerTest {

    @Value("${local.server.port}")
    int port;

    private RestTemplate template = new TestRestTemplate();

    // private List<User> users;
    //
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() {
        // userRepository.deleteAll();
        // users = createUserList(5);
        // userRepository.save(users);

    }

    // @Test
    // public void test() {
    // Assertions.assertThat(userRepository.findAll()).isNotNull().hasSize(3);
    //
    // }

    @Test
    @Transactional
    public void testRegisterUser_whenUserRegistrationIsSuccess_thenUserIsRegistered() throws Exception {
        // prepare
        int userCount = (int) userRepository.count();
        HttpEntity<RegistrationDto> registrationRequest = createRegistrationRequest();

        // act
        ResponseEntity<Void> responseEntity = template.postForEntity("http://localhost:" + port + "/register/", registrationRequest, Void.class);

        // assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepository.findAll()).isNotEmpty().hasSize(userCount + 1);
    }

    @Test
    @Transactional()
    public void testRegisterUser_whenUserAlreadyExists_thenBadRequestIsReturned() throws Exception {
        // prepare
        assertThat(userRepository.findByEmail("CAFE@BABE.COM")).isNull();
        HttpEntity<RegistrationDto> registrationRequest = createRegistrationRequest();

        // act
        ResponseEntity<Void> responseEntityFirst = template.postForEntity("http://localhost:" + port + "/register/", registrationRequest, Void.class);
        ResponseEntity<Void> responseEntitySecond = template.postForEntity("http://localhost:" + port + "/register/", registrationRequest, Void.class);

        // assert
        assertThat(responseEntityFirst.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntitySecond.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testRegisterUser_whenRegistrationIsMissingRequiredData_thenBadRequestIsReturned() throws Exception {
        // prepare
        HttpEntity<RegistrationDto> registrationRequest = createRegistrationRequest();
        registrationRequest.getBody().setEmail(null);

        // act
        ResponseEntity<Void> responseEntity = template.postForEntity("http://localhost:" + port + "/register/", registrationRequest, Void.class);

        // assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    // +--------- HELPER METHODS ---------+
    private HttpEntity<RegistrationDto> createRegistrationRequest() {
        RegistrationDto registrationDto = createRegistrationDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationDto> request = new HttpEntity<RegistrationDto>(registrationDto, headers);
        return request;
    }

    private RegistrationDto createRegistrationDto() {
        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setEmail("CAFE@BABE.COM");
        registrationDto.setPassword("SuperSecurePassword");
        registrationDto.setPhoneNumber("666 999");
        return registrationDto;
    }

    private List<User> createUserList(int count) {
        List<User> userList = new ArrayList<>();
        while (count-- > 0) {
            userList.add(createUser());
        }
        return userList;
    }

    private User createUser() {
        User user = new User();
        user.setEmail(RandomStringUtils.random(10) + "@" + RandomStringUtils.random(5) + "." + RandomStringUtils.random(3));
        user.setUsername(user.getEmail());
        user.setPassword(RandomStringUtils.random(20));
        user.setPhone_number(RandomStringUtils.random(10, false, true));
        user.setRole(createUserRole(RoleType.USER));
        return user;
    }

    private Role createUserRole(RoleType user) {
        Role role = new Role();
        role.setName(user.getRoleName());
        return role;
    }

}
