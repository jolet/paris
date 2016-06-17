package tvz.nppjj.paris.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.exception.ParisException;
import tvz.nppjj.paris.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public List<EventDto> getAllEvents() {
		return transformEventListToDtoList(eventRepository.findAll());
	}
	
	@Override
	public EventDto getEventById(Long id){
		return transformEventToEventDto(eventRepository.findOne(id));
	}


	@Override
	public void saveEvent(EventCommand eventCommand){
		
		
		if(eventCommand.getId() == null){
			Event event = new Event();
			event.setName(eventCommand.getName());
			event.setLocation(eventCommand.getLocation());
			event.setCity(eventCommand.getCity());
			event.setDate(eventCommand.getDate());
			event.setDescription(eventCommand.getDescription());
			event.setPicture(eventCommand.getPicture());
			event.setPrice(eventCommand.getPrice());
			
			event.setCategory(categoryService.getCategoryById(eventCommand.getIdCategory()));
			
			eventRepository.save(event);
		}
		else{
			Event event = eventRepository.findOne(eventCommand.getId());
			if(event==null){
				throw new ParisException("Event id " + eventCommand.getId()  + " ne postoji  u bazi!");
			}
			else{
				event.setName(eventCommand.getName());
				event.setLocation(eventCommand.getLocation());
				event.setCity(eventCommand.getCity());
				event.setDate(eventCommand.getDate());
				event.setDescription(eventCommand.getDescription());
				event.setPicture(eventCommand.getPicture());
				event.setPrice(eventCommand.getPrice());
				
				event.setCategory(categoryService.getCategoryById(eventCommand.getIdCategory()));
				
				eventRepository.save(event);
			}
			
		}
	}
	
	
	private EventDto transformEventToEventDto(Event event) {
		
		EventDto eventDto=new EventDto();
		eventDto.setName(event.getName());
		eventDto.setCity(event.getCity());
		eventDto.setLocation(event.getLocation());
		eventDto.setDate(event.getDate());
		eventDto.setPrice(event.getPrice());
		eventDto.setPicture(event.getPicture());
		eventDto.setDescription(event.getDescription());
		eventDto.setCategory(event.getCategory());
	    return eventDto;
	    
	}
	
	
	private List<EventDto> transformEventListToDtoList(List<Event> eventList){
	 List<EventDto> eventDtoList=new ArrayList<>();
	 for (int i=0;i<eventList.size();i++) {
		 eventDtoList.add(transformEventToEventDto(eventList.get(i)));
		}
	 return eventDtoList;
	 
	}
	
}
