package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.NewEventDto;

public interface EventService {
	
	List<Event> getAllEvents();
	Event getEventById(Long id);
	void saveEvent(NewEventDto newEventDto);
}
