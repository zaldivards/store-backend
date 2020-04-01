package sv.edu.ues.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javassist.NotFoundException;
import sv.edu.ues.domain.Customer;
import sv.edu.ues.mapper.CustomerMapper;
import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.repositories.CustomerRepository;

class CustomerServiceImplTest {


	@Mock
	private CustomerRepository repo;

	private CustomerService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new CustomerServiceImpl(repo, CustomerMapper.INSTANCE);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testGetAllCustomer() {
		when(this.repo.findAll()).thenReturn(List.of(new Customer(1L, "a", "b")));
		int size = this.service.getAllCustomer().size();
		assertEquals(1, size);
	}

	@Test
	final void testGetCustomer() throws NotFoundException {
		when(this.repo.findById(Mockito.anyLong())).thenReturn(Optional.of(new Customer(1L, "test", "ing")));
		CustomerDTO testObject = this.service.getCustomer(1L);
		assertEquals("test", testObject.getFirstName());
	}

	@Test
	final void deleteCustomer() {
		doNothing().when(this.repo).deleteById(Mockito.anyLong());
		ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
		this.service.deleteById(100L);
		verify(this.repo, times(1)).deleteById(captor.capture());
		assertEquals(100L, captor.getValue());
	}

	@Test
	final void createCustomer() {
		// given
		CustomerDTO dto = new CustomerDTO();
		dto.setFirstName("Z");
		Customer customer = new Customer();
		customer.setFirstName(dto.getFirstName());

		when(this.repo.save(Mockito.any(Customer.class))).thenReturn(customer);

		// when
		CustomerDTO savedDTO = this.service.createCustomer(dto);

		// then
		assertEquals(dto.getFirstName(), savedDTO.getFirstName());

	}

}
