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

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.dto.RegistrationCommand;
import tvz.nppjj.paris.repository.TicketRepository;
import tvz.nppjj.paris.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class RegistrationControllerTest {

    @Value("${local.server.port}")
    int port;

    private String       registrationApiUrl;
    private RestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    /**
     * This is run before every @Test method
     *
     * NOTE: @Transactional and @Rollback or @TransactionConfiguration(defaultRollback = true) will NOT work here; You
     * cannot create transaction based tests when making requests to the REST API, despite that you could autowire the
     * transaction manager in your test. The reason is that any transaction defined in your application, regardless of
     * where it is declared, will be committed before the server response is sent. Consequently, it will be too late for
     * a test to roll back any state using the @Transactional annotation when the test completes.
     */
    @Before
    public void setUp() {
        registrationApiUrl = "http://localhost:" + port + "/register/";

        // reset DB since transactions are not working
        resetDb();
    }

    @Test
    public void testRegisterUser_whenUserRegistrationIsSuccess_thenUserIsRegistered() throws Exception {
        // prepare
        long userCount = userRepository.count();
        HttpEntity<RegistrationCommand> registrationRequest = createRegistrationRequest();

        // act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(registrationApiUrl, registrationRequest, Void.class);

        // assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(userRepository.findAll()).isNotEmpty().hasSize((int) userCount + 1);
    }

    @Test
    public void testRegisterUser_whenUserAlreadyExists_thenBadRequestIsReturned() throws Exception {
        // prepare
        assertThat(userRepository.findByEmail("CAFE@BABE.COM")).isNull();
        HttpEntity<RegistrationCommand> registrationRequest = createRegistrationRequest();

        // act
        ResponseEntity<Void> firstResponseEntity = restTemplate.postForEntity(registrationApiUrl, registrationRequest, Void.class);
        ResponseEntity<Void> secondResponseEntity = restTemplate.postForEntity(registrationApiUrl, registrationRequest, Void.class);

        // assert
        assertThat(firstResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(secondResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testRegisterUser_whenRegistrationIsMissingRequiredData_thenBadRequestIsReturned() throws Exception {
        // prepare
        HttpEntity<RegistrationCommand> registrationRequest = createRegistrationRequest();
        registrationRequest.getBody().setEmail(null);

        // act
        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/register/", registrationRequest, Void.class);

        // assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    // +------------ HELPER METHODS ------------+
    private HttpEntity<RegistrationCommand> createRegistrationRequest() {
        RegistrationCommand registrationCommand = createRegistrationDto();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegistrationCommand> request = new HttpEntity<RegistrationCommand>(registrationCommand, headers);
        return request;
    }

    private RegistrationCommand createRegistrationDto() {
        RegistrationCommand registrationCommand = new RegistrationCommand();
        registrationCommand.setEmail("CAFE@BABE.COM");
        registrationCommand.setPassword("SuperSecurePassword");
        registrationCommand.setPhoneNumber("666 999");
        return registrationCommand;
    }

    private void resetDb() {
        ticketRepository.deleteAll();
        userRepository.deleteAll();
    }

}
