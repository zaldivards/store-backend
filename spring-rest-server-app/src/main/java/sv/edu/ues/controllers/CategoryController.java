package sv.edu.ues.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import sv.edu.ues.model.CategoryDTO;
import sv.edu.ues.model.CategoryListDTO;
import sv.edu.ues.services.CategoryService;

@RequestMapping("/api/v1/categories")
@RestController()
public class CategoryController {

	CategoryService service;

	public CategoryController(CategoryService service) {
		super();
		this.service = service;
	}

	@GetMapping
	public CategoryListDTO getAll() {
		return new CategoryListDTO(this.service.getAllCategories());
	}

	@GetMapping("/{name}")
	public CategoryDTO getByName(@PathVariable String name) throws NotFoundException {

		return this.service.getCategoryByname(name);
	}

}
