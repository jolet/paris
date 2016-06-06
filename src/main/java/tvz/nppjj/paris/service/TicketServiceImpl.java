package tvz.nppjj.paris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.Ticket;
import tvz.nppjj.paris.model.dto.TicketDto;
import tvz.nppjj.paris.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<Ticket> getAllTickets() {
		
		return ticketRepository.findAll();
	}

	@Override
	public Ticket getTicketById(Long id) {
		
		return ticketRepository.findOne(id);
	}

	@Override
	public void saveTicket(TicketDto ticketDto) {
		
		Ticket ticket = new Ticket();
		
		ticket.setCode(ticketDto.getCode());
		ticket.setPrice(ticketDto.getPrice());
		ticket.setIsValidated(ticketDto.getIsValidated());
				
		ticket.setEvent(eventService.getEventById(ticketDto.getIdEvent()));
		
		ticket.setUser(userService.getUserById(ticketDto.getIdUser()));
		
		
		ticketRepository.save(ticket);		
	}

}
