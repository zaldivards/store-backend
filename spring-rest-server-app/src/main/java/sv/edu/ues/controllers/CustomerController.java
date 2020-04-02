package sv.edu.ues.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.model.CustomerListDTO;
import sv.edu.ues.services.CustomerService;

@Api("Controller para manejar las peticiones respecto a los clientes")
@RequestMapping(CustomerController.BASE_URL)
@RestController
public class CustomerController {
	
	public static final String BASE_URL = "/api/v1/customers";

	private CustomerService service; 

	public CustomerController(CustomerService service) {
		super();
		this.service = service;
	}
	
	@ApiOperation(value = "Obtiene todos los clientes activos")
	
	//@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE})
	@GetMapping
	public CustomerListDTO getAllCustomers() {
		CustomerListDTO listDTO = new CustomerListDTO();
		listDTO.getCustomers().addAll(this.service.getAllCustomer());
		return listDTO;
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
	@PatchMapping("/{id}")
	public CustomerDTO updatePortionCustomer(@PathVariable Long id, @RequestBody CustomerDTO dto) {
		return this.service.patchCustomer(id, dto);
	}
	
	
	
	
	
	

}
