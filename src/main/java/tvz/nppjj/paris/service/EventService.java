package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.Event;

public interface EventService {
	
	List<Event> getAllEvents();
	void saveEvent(Event event );
	Event getEventById(Long id);
}
