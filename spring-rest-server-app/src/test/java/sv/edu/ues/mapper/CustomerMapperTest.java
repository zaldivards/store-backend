package sv.edu.ues.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sv.edu.ues.domain.Customer;
import sv.edu.ues.model.CustomerDTO;

class CustomerMapperTest {

	private CustomerMapper mapper;
	private final String NAME = "ZALDIVAR"; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		mapper = CustomerMapper.INSTANCE;
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void test() {

	Customer customer = new Customer(1L, "Christian", NAME);
	CustomerDTO dto = mapper.customerToCustomerDTO(customer);
	
	assertEquals(NAME, dto.getLastName());
	
	}

}
