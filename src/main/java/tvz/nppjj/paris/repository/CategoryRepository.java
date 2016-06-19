package tvz.nppjj.paris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.nppjj.paris.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);
}
