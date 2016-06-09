package tvz.nppjj.paris.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.repository.TicketRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
public class TicketServiceTest {
	
	@Mock
	private TicketRepository ticketRepository; 
	
	@Mock
	private EventService eventService;
	
	@Mock
	private UserService userService;
	
	
	@InjectMocks
	private TicketService ticketService;
	
	@Before
	public void setup(){
		ticketService= new TicketServiceImpl();
		MockitoAnnotations.initMocks(this);
	}

}
