package sv.edu.ues.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sv.edu.ues.config.Bootstrap;
import sv.edu.ues.domain.Customer;
import sv.edu.ues.mapper.CustomerMapper;
import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.repositories.CategoryRepository;
import sv.edu.ues.repositories.CustomerRepository;
@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerServiceImplIT {
	
	private final String UPDATED_NAME = "TIGRE";
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	CustomerService service;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
		bootstrap.run();
		service = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void patchCustomerUpdateFirstName() {
	Long id = this.customerRepository.findAll().get(0).getId();
	
	Customer original = this.customerRepository.findById(id).get();
	assertNotNull(original);
	
	String originalFirstname = original.getFirstName();
	String originalLastname = original.getLastName();
	
	CustomerDTO customerDTO = new CustomerDTO();
	customerDTO.setFirstName(UPDATED_NAME);
	
	this.service.patchCustomer(id, customerDTO);
	
	Customer updatedCustomer = this.customerRepository.findById(id).get();
	
	System.out.println(updatedCustomer);
	
	assertNotNull(updatedCustomer);
	assertEquals(UPDATED_NAME, updatedCustomer.getFirstName());
	assertThat(originalFirstname).isNotEqualTo(updatedCustomer.getFirstName());
	assertThat(originalLastname).isEqualTo(updatedCustomer.getLastName());
	
	}
	

	

}













