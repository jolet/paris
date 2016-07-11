package tvz.nppjj.paris.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.service.TicketService;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
@WebAppConfiguration
public class TicketControllerTest {
	
	  @Autowired
	  TicketService ticketService;
	  
	  @Test
	  public void getAllTickets_ShouldReturnAllTickets() throws Exception{
		  
	  }
	  
	  @Test
	  public void getTicketById_ShouldReturnOneTicket() throws Exception{
		  
	  }
	  
	  @Test
	  public void getAllTicketsByUserId_ShouldReturnOneTicket() throws Exception{
		  
	  }
	  
	  @Test
	  public void saveTicket_ShouldReturnOneTicket() throws Exception{
		  
	  }
	  
	  
	  

}
