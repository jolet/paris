package tvz.nppjj.paris.service;

import java.sql.Date;
import java.util.List;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.dto.PaginationDto;

public interface EventService {

    List<EventDto> getAllEvents();

    EventDto getEventById(Long id);

    void saveEvent(Event event);

    void saveEvent(EventCommand eventCommand);

    PaginationDto<EventDto> getAllEventsWithPagination(Integer pageIndex);

    List<EventDto> getFilteredEvents(String name, Long categoryId, Date date);

    List<EventDto> getEventsByUserId(Long idUser);
}
