package sv.edu.ues.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import sv.edu.ues.mapper.CategoryMapper;
import sv.edu.ues.model.CategoryDTO;
import sv.edu.ues.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private final CategoryMapper mapper;
	private final CategoryRepository repository;

	public CategoryServiceImpl(CategoryMapper mapper, CategoryRepository repository) {
		super();
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		return this.repository.findAll()
				.stream()
				.map(mapper::categoryToCategoryDTO)
				.collect(Collectors.toList());
	}

	@Override
	public CategoryDTO getCategoryByname(String name) throws NotFoundException {
		return this.repository.findByName(name)
				.map(mapper::categoryToCategoryDTO)
				.orElseThrow(()->new NotFoundException("Category does not exists"));
	}

}

















