package tvz.nppjj.paris.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.NewEventDto;
import tvz.nppjj.paris.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
		
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> getAllEvents() {
    	return eventService.getAllEvents();
	}
	
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
	public Event getEventById(@PathVariable("id") Long id) {
    	return eventService.getEventById(id);
	}
	
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/saveEvent", method = RequestMethod.POST)
	public void saveEvent(@Valid @RequestBody NewEventDto newEventDto){
		eventService.saveEvent(newEventDto);
		
	}
		
}
