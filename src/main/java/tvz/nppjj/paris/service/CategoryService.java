package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.Category;

public interface CategoryService {

	List<Category> getAllCategory();
	
	Category getCategoryById(Long id);
}
