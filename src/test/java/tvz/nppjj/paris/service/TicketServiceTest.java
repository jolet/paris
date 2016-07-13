package tvz.nppjj.paris.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.model.Category;
import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.Role;
import tvz.nppjj.paris.model.Ticket;
import tvz.nppjj.paris.model.User;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.dto.TicketCommand;
import tvz.nppjj.paris.repository.TicketRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
public class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private EventService     eventService;

    @Mock
    private UserService      userService;

    @InjectMocks
    private TicketService    ticketService;

    @Before
    public void setup() {
        ticketService = new TicketServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveTicket() {
        // prepare
        User user = new User();
        user.setAccount(new BigDecimal("500.00"));
        TicketCommand ticketCommand = createTicketcommand();
        when((eventService.getEventById(anyLong()))).thenReturn(Mockito.mock(EventDto.class));
        when(userService.getUserById(anyLong())).thenReturn(createMySuperUser());
        when(ticketRepository.save(any(Ticket.class))).thenAnswer(new Answer<Ticket>() {

            @Override
            public Ticket answer(InvocationOnMock invocation) throws Throwable {
                Ticket ticket = invocation.getArgument(0);
                // assert
                assertThat(ticket.getEvent()).isNotNull();
                assertThat(ticket.getUser()).isNotNull();
                assertThat(ticket.getIsValidated()).isEqualTo(ticketCommand.getIsValidated());
                assertThat(ticket.getPrice()).isEqualTo(ticketCommand.getPrice());
                return ticket;
            }
        });

        BigDecimal newAccount = user.getAccount().subtract(ticketCommand.getPrice());

        assertThat(newAccount).isGreaterThanOrEqualTo(BigDecimal.ZERO);

        // act
        ticketService.saveTicket(ticketCommand);

        // verify
        verify(user, times(1)).setAccount(newAccount);
        verify(userService, times(1)).saveUser(any(User.class));
        verify(ticketRepository, times(1)).save(any(Ticket.class));

    }

    private Event eventMock() {
        @SuppressWarnings("deprecation")
        Date date = new Date(2016, 1, 12);

        Category category = new Category();
        category.setId(1L);
        category.setName("idciddd");

        BigDecimal price = new BigDecimal("456.23");

        Event event = new Event();
        event.setName("kjfdnkjdn");
        event.setDate(date);
        event.setCity("dcsdcsaa");
        event.setDescription("kdsnmsdkjn asknmjx asnmxn");
        event.setLocation("dsncjndc");
        event.setPicture("//");
        event.setPrice(price);
        event.setCategory(category);
        return event;

    }

    private TicketCommand createTicketcommand() {
        TicketCommand command = new TicketCommand();
        command.setPrice(new BigDecimal("125.55"));
        command.setIsValidated(false);
        command.setIdEvent(1L);
        command.setIdUser(2L);
        command.setPrice(new BigDecimal("300"));
        return command;
    }

    private Event transformEventDtoToEvent(EventDto eventDto) {
        Event event = new Event();
        event.setName(eventDto.getName());
        event.setLocation(eventDto.getLocation());
        event.setCity(eventDto.getCity());
        event.setDate(eventDto.getDate());
        event.setDescription(eventDto.getDescription());
        event.setPicture(eventDto.getPicture());
        event.setPrice(eventDto.getPrice());

        event.setCategory(eventDto.getCategory());

        return event;
    }

    private User createMySuperUser() {
        Role role = new Role();
        role.setName("User");
        BigDecimal acc = new BigDecimal(10);
        User testUser = new User();
        testUser.setUsername("ejosip");
        testUser.setPassword("lozinka");
        testUser.setAccount(acc);
        testUser.setEmail("moje@moje.com");
        testUser.setId(569l);
        testUser.setPhone_number("123454");
        testUser.setRole(role);
        testUser.setAccount(new BigDecimal("500"));
        return testUser;
    }

    private Role createMockRole() {
        Role mockRole = new Role();
        mockRole.setName("mockRole");
        return mockRole;
    }

}
