package tvz.nppjj.paris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.service.EventService;

@RestController
public class EventController {
	
	@Autowired
	private EventService eventService;
	
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> getAllEvents() {
    	return eventService.getAllEvents();
	}
		
}
