package sv.edu.ues.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import sv.edu.ues.domain.Category;
import sv.edu.ues.model.CategoryDTO;

@Mapper
public interface CategoryMapper {

	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
	
	CategoryDTO categoryToCategoryDTO(Category category); 
	
}
