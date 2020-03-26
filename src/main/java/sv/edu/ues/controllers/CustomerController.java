package sv.edu.ues.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.model.CustomerListDTO;
import sv.edu.ues.services.CustomerService;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

	private CustomerService service;

	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public CustomerListDTO getAllCustomers() {
		return new CustomerListDTO(service.getAllCustomer());
	}
	
	@GetMapping("/{id}")
	public CustomerDTO findById(@PathVariable Long id) throws NotFoundException {
		return this.service.getCustomer(id);
	}
	@PostMapping
	public CustomerDTO createCustomer(@RequestBody CustomerDTO dto) {
		CustomerDTO savedDTO = this.service.createCustomer(dto);
		return savedDTO;
	}
	
	@PutMapping("/{id}")
	public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
		CustomerDTO updatedDTO = this.service.updateCustomer(id, dto);
		return updatedDTO;
	}
	
	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable Long id) {
		this.service.deleteById(id);
	}
	
	
	
	
	
	

}
