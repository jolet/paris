package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.Ticket;
import tvz.nppjj.paris.model.dto.TicketDto;



public interface TicketService {

	List<Ticket> getAllTickets();
	Ticket getTicketById(Long id);
	
	void saveTicket(TicketDto ticketDto);
}
