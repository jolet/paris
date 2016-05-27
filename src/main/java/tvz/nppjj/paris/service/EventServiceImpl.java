package tvz.nppjj.paris.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.repository.EventRepository;


@Service
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
	@Override
	public List<Event> getAllEvents() {
		return (List<Event>) eventRepository.findAll();
	}
	
	@Override
	public Event getEventById(Long id){
		return eventRepository.findOne(id);
	}

	@Override
	public void saveEvent(Event event){
		eventRepository.saveAndFlush(event);
	}
}
