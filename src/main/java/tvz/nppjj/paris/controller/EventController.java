package tvz.nppjj.paris.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.dto.PaginationDto;
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
    @RequestMapping(value = "/events/delete/{id}", method = RequestMethod.GET)
    public void deleteEvent(@PathVariable("id") long idEvent) {
        eventService.deleteEvent(idEvent);
    }

    @CrossOrigin
    @RequestMapping(value = "/eventsFilter", method = RequestMethod.GET)
    public @ResponseBody List<EventDto> getFilteredEvents(@RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "date", required = false) Date date) {

        if (name == null && categoryId == null && date == null) {
            return eventService.getAllEvents();
        } else {
            return eventService.getFilteredEvents(name, categoryId, date);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/eventspage", method = RequestMethod.GET)
    public PaginationDto<EventDto> getAllEventsWithPagination(
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex) {
        if (pageIndex < 1) {
            return new PaginationDto<>();
        }
        return eventService.getAllEventsWithPagination(pageIndex);
    }

    @CrossOrigin // (origins = "http://localhost:8100")
    @RequestMapping(value = "/events/update", method = RequestMethod.POST)
    public void updateEvent(@Valid @RequestBody EventCommand eventCommand) {
        eventService.saveEvent(eventCommand);

    }

    @CrossOrigin
    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    public EventDto getEventById(@PathVariable("id") Long id) {
        return eventService.getEventById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/events/user/{id}", method = RequestMethod.GET)
    public List<EventDto> getEventByUserId(@PathVariable("id") Long idUser) {
        return eventService.getEventsByUserId(idUser);
    }

    @CrossOrigin // (origins = "http://localhost:8100")
    @RequestMapping(value = "/events/save", method = RequestMethod.POST)
    public void saveEvent(@Valid @RequestBody EventCommand eventCommand) {
        eventService.saveEvent(eventCommand);

    }

}
