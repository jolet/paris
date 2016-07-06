package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.dto.PaginationDto;

public interface EventService {

    List<EventDto> getAllEvents();

    EventDto getEventById(Long id);

    void saveEvent(EventCommand eventCommand);

    PaginationDto<EventDto> getAllEventsWithPagination(Integer pageIndex);
}
