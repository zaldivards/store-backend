package sv.edu.ues.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javassist.NotFoundException;
import sv.edu.ues.domain.Category;
import sv.edu.ues.mapper.CategoryMapper;
import sv.edu.ues.model.CategoryDTO;
import sv.edu.ues.repositories.CategoryRepository;

class CategoryServiceTest {
	
	@Mock
	CategoryRepository repository;

	CategoryService service;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
		service = new CategoryServiceImpl(CategoryMapper.INSTANCE, repository);
	}
	
	@BeforeEach
	void setUp() throws Exception {
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void findall(){
		List<Category> lista = List.of(new Category(), new Category());
		when(repository.findAll()).thenReturn(lista);
		int size = this.service.getAllCategories().size();
		assertEquals(2, size);
	}
	
	@Test
	final void findOne() throws NotFoundException {
		Category category = new Category();
		category.setId(1L);
		category.setName("steven");
		when(this.repository.findByName(Mockito.anyString())).thenReturn(Optional.of(category));
		
		CategoryDTO dto = this.service.getCategoryByname("xd");
		
		
		
		assertEquals(dto.getName(), "steven");
	}
	
	@Test
	final void findOneUnexistent() throws NotFoundException {
		Category category = new Category();
		category.setId(1L);
		category.setName("steven");
		when(this.repository.findByName("steven")).thenReturn(Optional.of(category));
		
		
		
		assertThrows(NotFoundException.class, () -> this.service.getCategoryByname("xd"));
	}
	
	
	
	
	
	
	
	

}
