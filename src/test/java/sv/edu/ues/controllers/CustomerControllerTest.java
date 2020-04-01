package sv.edu.ues.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.services.CustomerService;
import sv.edu.ues.services.ResourceNotFoundException;

class CustomerControllerTest {

	private final String NAME = "Steven";
	private final String LASTNAME = "Zaldivar";

	@Mock
	private CustomerService service;

	@InjectMocks
	private CustomerController controller;

	private MockMvc mvc;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).
				setControllerAdvice(new RestResponseExceptionHandler())
				.build();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void findAll() throws Exception {

		when(this.service.getAllCustomer()).thenReturn(List.of(new CustomerDTO(), new CustomerDTO()));
		mvc.perform(get("/api/v1/customers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customers", Matchers.hasSize(2)));
	}

	@Test
	final void findById() throws Exception {
		CustomerDTO dto = new CustomerDTO();
		dto.setFirstName(NAME);
		dto.setLastName(LASTNAME);
		when(this.service.getCustomer(Mockito.anyLong())).thenReturn(dto);
		mvc.perform(get("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON))
//		.andReturn().getResponse().getContentAsString();
				.andExpect(status().isOk()).andExpect(jsonPath("$.lastName", is(LASTNAME)));
	}

	@Test
	final void findByIdNotFound() throws Exception {
		when(this.service.getCustomer(Mockito.anyLong())).thenThrow(ResourceNotFoundException.class);
		mvc.perform(get(CustomerController.BASE_URL.concat("/23"))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());

	}

	@Test
	final void createCustomer() throws Exception {
		CustomerDTO dto = new CustomerDTO();
		dto.setFirstName(NAME);
		dto.setLastName(LASTNAME);
		when(this.service.createCustomer(Mockito.any(CustomerDTO.class))).thenReturn(dto);
		mvc.perform(post("/api/v1/customers/").contentType(MediaType.APPLICATION_JSON).content(TestUtil.toJson(dto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is(NAME)));
	}

	@Test
	final void deleteCustomer() throws Exception {
		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		mvc.perform(delete("/api/v1/customers/2")).andExpect(status().isOk());
		verify(this.service, times(1)).deleteById(captor.capture());
		assertEquals(2, captor.getValue());
	}

	@Test
	final void updateCustomer() throws Exception {
		CustomerDTO dto = new CustomerDTO();
		dto.setFirstName(NAME);
		dto.setLastName(LASTNAME);
		when(this.service.updateCustomer(Mockito.anyLong(), Mockito.any(CustomerDTO.class))).thenReturn(dto);
		mvc.perform(put("/api/v1/customers/1").contentType(MediaType.APPLICATION_JSON).content(TestUtil.toJson(dto)))
				.andExpect(status().isOk()).andExpect(jsonPath("$.firstName", is(NAME)));
	}

}
