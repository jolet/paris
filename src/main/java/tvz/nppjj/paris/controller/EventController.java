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

import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.service.EventService;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    @CrossOrigin
    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    @CrossOrigin
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public EventDto getEventById(@PathVariable("id") Long id) {
        return eventService.getEventById(id);
    }

    @CrossOrigin // (origins = "http://localhost:8100")
    @RequestMapping(value = "/events/save", method = RequestMethod.POST)
    public void saveEvent(@Valid @RequestBody EventCommand eventCommand) {
        eventService.saveEvent(eventCommand);

    }

}
