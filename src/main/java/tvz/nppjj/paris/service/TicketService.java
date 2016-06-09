package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.dto.TicketDto;



public interface TicketService {

	List<TicketDto> getAllTickets();
	TicketDto getTicketById(Long id);
	
	void saveTicket(TicketDto ticketDto);
	List<TicketDto> getTicketsByIdUserOrIdEvent(Long idUser, Long idEvent);
	
}
