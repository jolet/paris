package tvz.nppjj.paris.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;

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
import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.repository.EventRepository;
import tvz.nppjj.paris.repository.TicketRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class EventControllerTest {

    @Value("${local.server.port}")
    int                  port;

    private String       registrationApiUrl;
    private RestTemplate restTemplate = new TestRestTemplate();

    @Autowired
    EventRepository      eventRepository;

    @Autowired
    TicketRepository     ticketRepository;

    @Before
    public void setup() {
        registrationApiUrl = "http://localhost:" + port + "/events/save";

        resetDb();
    }

    private void resetDb() {
        ticketRepository.deleteAll();
        eventRepository.deleteAll();
    }

    @Test
    public final void testSaveEvent_ifEvantCommandValid_thenNewEventIsSaved() throws ParseException {
        long eventCount = eventRepository.count();
        HttpEntity<EventCommand> registrationRequest = createEventSaveRequest();

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(registrationApiUrl, registrationRequest,
                Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(eventRepository.count()).isEqualTo(eventCount + 1);

    }

    @Test
    public final void testSaveEvent_ifEventCommandIsNotValid_thenBadRequestIsReturned() throws ParseException {
        long eventCount = eventRepository.count();
        HttpEntity<EventCommand> registrationRequest = createEventSaveRequest();
        registrationRequest.getBody().setName("BAD");

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity(registrationApiUrl, registrationRequest,
                Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(eventRepository.count()).isEqualTo(eventCount);
    }

    // +------------ HELPER METHODS ------------+
    private HttpEntity<EventCommand> createEventSaveRequest() throws ParseException {
        EventCommand eventCommand = createEventCommand();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EventCommand> request = new HttpEntity<EventCommand>(eventCommand, headers);
        return request;
    }

    private EventCommand createEventCommand() throws ParseException {

        @SuppressWarnings("deprecation")
        Date date = new Date(2016, 1, 12);

        BigDecimal price = new BigDecimal("456.23");

        EventCommand command = new EventCommand();

        command.setName("kjfdnkjdn");
        command.setDate(date);
        command.setCity("dcsdcsaa");
        command.setDescription("kdsnmsdkjn asknmjx asnmxn");
        command.setLocation("dsncjndc");
        command.setPicture("//");
        command.setPrice(price);
        command.setIdCategory(2L);

        return command;
    }

}
