package sv.edu.ues.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sv.edu.ues.domain.Category;
import sv.edu.ues.model.CategoryDTO;

class CategoryMapperTest {

	CategoryMapper mapper = CategoryMapper.INSTANCE;

	

	@BeforeEach
	void setUp() throws Exception {

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void test() {

		Category category = new Category();
		category.setId(1L);
		category.setName("Joe");
	
		CategoryDTO dto = mapper.categoryToCategoryDTO(category);
		assertEquals("Joe", dto.getName());
	}

}
