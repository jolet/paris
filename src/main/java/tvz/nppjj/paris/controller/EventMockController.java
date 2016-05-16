package tvz.nppjj.paris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.EventMock;
import tvz.nppjj.paris.service.EventServiceMock;

@RestController
@RequestMapping(value = "/mock")
public class EventMockController {

	@Autowired
	private EventServiceMock eventService;
	
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<EventMock> getEvents() {
    	return eventService.getAllEvents();
    }
}
