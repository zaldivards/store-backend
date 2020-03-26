package sv.edu.ues.services;
import java.util.List;

import javassist.NotFoundException;
import sv.edu.ues.model.CategoryDTO;

public interface CategoryService {

	List<CategoryDTO> getAllCategories();
	CategoryDTO getCategoryByname(String name) throws NotFoundException;
	
}
