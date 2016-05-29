package tvz.nppjj.paris.model.mapper;


import org.springframework.beans.factory.annotation.Autowired;

import tvz.nppjj.paris.model.Category;
import tvz.nppjj.paris.model.Event;
import tvz.nppjj.paris.model.dto.NewEventDto;
import tvz.nppjj.paris.service.CategoryService;

public class EventMapper {
	
	@Autowired
	private static CategoryService categoryService;
	
	

	public static Event transformToEvent(NewEventDto newEventDto){
		
		Category category = new Category();
		
		category = categoryService.getCategoryById(newEventDto.getIdCategory());
		
		Event event = new Event();
		event.setName(newEventDto.getName());
		event.setLocation(newEventDto.getLocation());
		event.setCity(newEventDto.getCity());
		event.setDate(newEventDto.getDate());
		event.setDescription(newEventDto.getDescription());
		event.setPicture(newEventDto.getPicture());
		event.setPrice(newEventDto.getPrice());
		event.setCategory(category);
		return event;
	}
}
