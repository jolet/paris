package tvz.nppjj.paris.controller;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import tvz.nppjj.paris.model.Category;
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
    @RequestMapping(value = "/eventsFilter", params = { "name", "categoryId", "date" }, method = RequestMethod.GET)
    public @ResponseBody List<EventDto> getFilteredEvents(@RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "categoryId") Long categoryId,
            @RequestParam(required = false, value = "date") Date date) {

        if(name==null && categoryId==null && date == null){
            return eventService.getAllEvents();
        }
        else{
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
