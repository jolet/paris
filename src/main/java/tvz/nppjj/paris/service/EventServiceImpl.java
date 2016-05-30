package tvz.nppjj.paris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.NewEventDto;
import tvz.nppjj.paris.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Override
	public List<Event> getAllEvents() {
		return (List<Event>) eventRepository.findAll();
	}
	
	@Override
	public Event getEventById(Long id){
		return eventRepository.findOne(id);
	}

	@Override
	public void saveEvent(NewEventDto newEventDto){
		
		Event event = new Event();
		event.setName(newEventDto.getName());
		event.setLocation(newEventDto.getLocation());
		event.setCity(newEventDto.getCity());
		event.setDate(newEventDto.getDate());
		event.setDescription(newEventDto.getDescription());
		event.setPicture(newEventDto.getPicture());
		event.setPrice(newEventDto.getPrice());
		
		event.setCategory(categoryService.getCategoryById(newEventDto.getIdCategory()));
		
		eventRepository.save(event);
	}
}
