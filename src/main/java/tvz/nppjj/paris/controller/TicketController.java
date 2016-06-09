package tvz.nppjj.paris.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.dto.TicketCommand;
import tvz.nppjj.paris.model.dto.TicketDto;
import tvz.nppjj.paris.service.TicketService;

@RestController
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
	public List<TicketDto> getAllTickets() {
    	return ticketService.getAllTickets();
	}
	
	@RequestMapping(value = "/tickets/{id}", method = RequestMethod.GET)
	public TicketDto getTicketById(@PathVariable("id") Long id) {
    	return ticketService.getTicketById(id);
	}
	
	
//	@RequestMapping(value = "/tickets", method = RequestMethod.GET)
//	public List<TicketDto> getAllTickets(@RequestParam("idEvent") Long idEvent, @RequestParam("idUser") Long idUser) {
//    	return ticketService.getTicketsByIdUserOrIdEvent(idUser, idEvent);
//	}
	
	
	@RequestMapping(value = "/tickets/save", method = RequestMethod.POST)
	public void saveTicket(@Valid @RequestBody TicketCommand ticketCommand){
		ticketService.saveTicket(ticketCommand);
	}

}
