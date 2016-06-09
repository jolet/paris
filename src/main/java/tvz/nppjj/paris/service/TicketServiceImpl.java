package tvz.nppjj.paris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.nppjj.paris.model.Ticket;

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
	public void saveTicket(TicketDto ticketDto) {
		
		Ticket ticket = new Ticket();
		
		ticket.setCode(ticketDto.getCode());
		ticket.setPrice(ticketDto.getPrice());
		ticket.setIsValidated(ticketDto.getIsValidated());
				
		ticket.setEvent(eventService.getEventById(ticketDto.getIdEvent()));
		
		ticket.setUser(userService.getUserById(ticketDto.getIdUser()));
		
		
		ticketRepository.save(ticket);		
	}
	
	
	
	@Override
	public List<TicketDto> getTicketsByIdUserOrIdEvent(Long idUser, Long idEvent) {
		
		return transformTicketListToDtoList(ticketRepository.findByIdUserOrIdEvent(idUser, idEvent));
	}
	
	
	
	
	
	
	
	public TicketDto transformTicketToTicketDto(Ticket ticket) {

	       TicketDto ticketDto=new TicketDto();
	       ticketDto.setCode(ticket.getCode());
	       ticketDto.setIdUser(ticket.getUser().getId());
	       ticketDto.setIsValidated(ticket.getIsValidated());
	       ticketDto.setPrice(ticket.getPrice());
	       ticketDto.setEvent(ticket.getEvent());
	       return ticketDto;
	    }
	
	
	public List<TicketDto> transformTicketListToDtoList(List<Ticket> ticketList){
   	 List<TicketDto> ticketDtoList=new ArrayList<>();
   	 for (int i=0;i<ticketList.size();i++) {
   		ticketDtoList.add(transformTicketToTicketDto(ticketList.get(i)));
		}
   	 return ticketDtoList;
   	 
    }

}
