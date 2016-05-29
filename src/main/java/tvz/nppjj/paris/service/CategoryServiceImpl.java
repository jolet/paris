package tvz.nppjj.paris.service;

import java.util.List;

import tvz.nppjj.paris.model.Category;
import tvz.nppjj.paris.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;
	
	
	@Override
	public List<Category> getAllCategory() {
		
		return categoryRepository.findAll();
	}

	@Override
	public Category getCategoryById(Long id) {
		
		return categoryRepository.findOne(id);
	}

}
