package sv.edu.ues.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import sv.edu.ues.model.CategoryDTO;
import sv.edu.ues.services.CategoryService;

class CategoryControllerTest {
	
	@Mock
	CategoryService service;
	
	@InjectMocks
	CategoryController controller;
	
	MockMvc mvc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void findAll() throws Exception {
		when(this.service.getAllCategories()).thenReturn(List.of(new CategoryDTO(), new CategoryDTO()));
		mvc.perform(get("/api/v1/categories/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.categories", Matchers.hasSize(2)));
		
		verify(this.service, times(1)).getAllCategories();
	}
	
	@Test
	final void findByName() throws Exception {
			CategoryDTO category = new CategoryDTO();
			category.setName("TEST");
			when(this.service.getCategoryByname(Mockito.anyString())).thenReturn(category);
			mvc.perform(get("/api/v1/categories/some")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("TEST")));
	}

}
