package tvz.nppjj.paris.model.mapper;

import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.NewEventDto;

public class EventMapper {

	public static Event transformToEvent(NewEventDto newEventDto){
		Event event = new Event();
		event.setName(newEventDto.getName());
		event.setLocation(newEventDto.getLocation());
		event.setCity(newEventDto.getCity());
		event.setDate(newEventDto.getDate());
		event.setDescription(newEventDto.getDescription());
		event.setPicture(newEventDto.getPicture());
		event.setPrice(newEventDto.getPrice());
		return event;
	}
}
