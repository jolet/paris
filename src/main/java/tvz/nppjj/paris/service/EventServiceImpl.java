package tvz.nppjj.paris.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.EventCommand;
import tvz.nppjj.paris.model.dto.EventDto;
import tvz.nppjj.paris.model.dto.PaginationDto;
import tvz.nppjj.paris.model.exception.ParisException;
import tvz.nppjj.paris.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
    private static final int ENTITIES_PER_PAGE = 5;

    @Autowired
    private EventRepository  eventRepository;

    @Autowired
    private UserService      userService;

    @Autowired
    private CategoryService  categoryService;

    @Override
    public List<EventDto> getAllEvents() {
        return transformEventListToDtoList(eventRepository.findByActiveTrue());
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);

    }

    @Override
    public List<EventDto> getFilteredEvents(String name, Long categoryId, Date date) {
        List<EventDto> allEventDto = transformEventListToDtoList(eventRepository.findByActiveTrue());

        List<EventDto> eventsFiltered = allEventDto.stream()
                .filter(eventDto -> name == null || eventDto.getName().contains(name))
                .filter(eventDto -> categoryId == null || eventDto.getCategory().getId().equals(categoryId))
                .filter(eventDto -> date == null || eventDto.getDate().after(date) || eventDto.getDate().equals(date))
                .collect(Collectors.toList());

        return eventsFiltered;

    }

    @Override
    public List<EventDto> getEventsByUserId(Long idUser) {

        return transformEventListToDtoList(eventRepository.findEventByUserId(idUser));
    }

    @Override
    public PaginationDto<EventDto> getAllEventsWithPagination(Integer pageIndex) {
        Page<Event> requestedPage = eventRepository.findAll(createPageRequest(pageIndex));
        List<EventDto> paginatedList = transformEventListToDtoList(requestedPage.getContent());

        PaginationDto<EventDto> paginationDto = new PaginationDto<>(paginatedList, requestedPage.getNumber() + 1,
                requestedPage.getNumberOfElements(), requestedPage.getTotalPages());
        return paginationDto;
    }

    @Override
    public EventDto getEventById(Long id) {
        Event event = eventRepository.findOne(id);
        event.incrementNumberOfViews();
        eventRepository.save(event);

        return transformEventToEventDto(event);
    }

    @Override
    public void deleteEvent(long id) {
        Event event = eventRepository.findOne(id);

        event.setId(id);
        event.setActive(false);

        eventRepository.save(event);

    }

    @Override
    public void saveEvent(EventCommand eventCommand) {

        if (eventCommand.getId() == null) {
            Event event = new Event();
            event.setName(eventCommand.getName());
            event.setLocation(eventCommand.getLocation());
            event.setCity(eventCommand.getCity());
            event.setDate(eventCommand.getDate());
            event.setDescription(eventCommand.getDescription());
            event.setPicture(eventCommand.getPicture());
            event.setPrice(eventCommand.getPrice());
            event.setUser(userService.getUserById(eventCommand.getIdUser()));

            event.setCategory(categoryService.getCategoryById(eventCommand.getIdCategory()));

            eventRepository.save(event);
        } else {
            Event event = eventRepository.findOne(eventCommand.getId());
            if (event == null) {
                throw new ParisException("Event id " + eventCommand.getId() + " ne postoji  u bazi!");
            } else {
                event.setId(eventCommand.getId());
                event.setName(eventCommand.getName());
                event.setLocation(eventCommand.getLocation());
                event.setCity(eventCommand.getCity());
                event.setDate(eventCommand.getDate());
                event.setDescription(eventCommand.getDescription());
                event.setPicture(eventCommand.getPicture());
                event.setPrice(eventCommand.getPrice());
                event.setUser(userService.getUserById(eventCommand.getIdUser()));

                event.setCategory(categoryService.getCategoryById(eventCommand.getIdCategory()));

                eventRepository.save(event);

            }

        }
    }

    private EventDto transformEventToEventDto(Event event) {

        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setName(event.getName());
        eventDto.setCity(event.getCity());
        eventDto.setLocation(event.getLocation());
        eventDto.setDate(event.getDate());
        eventDto.setPrice(event.getPrice());
        eventDto.setPicture(event.getPicture());
        eventDto.setDescription(event.getDescription());
        eventDto.setCategory(event.getCategory());
        eventDto.setNumberOfViews(event.getNumberOfViews());
        eventDto.setNumberOfTicketsBought(event.getNumberOfTicketsBought());
        return eventDto;

    }

    private List<EventDto> transformEventListToDtoList(List<Event> eventList) {
        List<EventDto> eventDtoList = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            eventDtoList.add(transformEventToEventDto(eventList.get(i)));
        }
        return eventDtoList;

    }

    private PageRequest createPageRequest(Integer pageIndex) {
        return new PageRequest(pageIndex - 1, ENTITIES_PER_PAGE, new Sort(Sort.Direction.ASC, "date", "name"));
    }

}
