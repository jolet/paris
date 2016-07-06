package tvz.nppjj.paris.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import tvz.nppjj.paris.init.WebNppjjParisApplication;
import tvz.nppjj.paris.repository.CategoryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebNppjjParisApplication.class)
public class CategoryServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;
    
    @Test
    public void getAllCategory_ShouldReturnAllCategories(){
    	
    }
    
    @Test
    public void getCategoryById_ShouldReturnOneCategory(){
    	
    }
}
