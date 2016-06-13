package tvz.nppjj.paris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.Ticket;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.dto.TicketCommand;
import tvz.nppjj.paris.model.dto.TicketDto;

import tvz.nppjj.paris.repository.TicketRepository;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Override
	public List<TicketDto> getAllTickets() {
		
		return transformTicketListToDtoList(ticketRepository.findAll());
	}

	@Override
	public TicketDto getTicketById(Long id) {
		
		return transformTicketToTicketDto(ticketRepository.findOne(id));
	}

	@Override
	public void saveTicket(TicketCommand ticketCommand) {
		
		Ticket ticket = new Ticket();
		
		ticket.setCode(ticketCommand.getCode());
		ticket.setPrice(ticketCommand.getPrice());
		ticket.setIsValidated(ticketCommand.getIsValidated());
				
		ticket.setEvent(transformEventDtoToEvent(eventService.getEventById(ticketCommand.getIdEvent())));
		
		ticket.setUser(userService.getUserById(ticketCommand.getIdUser()));
		
		
		ticketRepository.save(ticket);		
	}
	
	
	
	@Override
	public List<TicketDto> getTicketsByIdUser(Long idUser) {
		
		return transformTicketListToDtoList(ticketRepository.findTicketByUserId(idUser));
	}
	
	
	
	
	private Event transformEventDtoToEvent(EventDto eventDto){
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
	
	
	
	
	private TicketDto transformTicketToTicketDto(Ticket ticket) {

	       TicketDto ticketDto=new TicketDto();
	       ticketDto.setCode(ticket.getCode());
	       ticketDto.setIdUser(ticket.getUser().getId());
	       ticketDto.setIsValidated(ticket.getIsValidated());
	       ticketDto.setPrice(ticket.getPrice());
	       ticketDto.setEvent(ticket.getEvent());
	       return ticketDto;
	    }
	
	
	private List<TicketDto> transformTicketListToDtoList(List<Ticket> ticketList){
   	 List<TicketDto> ticketDtoList=new ArrayList<>();
   	 for (int i=0;i<ticketList.size();i++) {
   		ticketDtoList.add(transformTicketToTicketDto(ticketList.get(i)));
		}
   	 return ticketDtoList;
   	 
    }

}
