package tvz.nppjj.paris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tvz.nppjj.paris.model.Category;
import tvz.nppjj.paris.service.CategoryService;

@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@CrossOrigin(origins = "http://localhost:8100")
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public List<Category> getAllEvents() {
    	return categoryService.getAllCategory();
	}
}
