package sv.edu.ues.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sv.edu.ues.domain.Customer;
import sv.edu.ues.mapper.CustomerMapper;
import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.repositories.CustomerRepository;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository repository;
	private CustomerMapper mapper;

	public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
		super();
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public List<CustomerDTO> getAllCustomer() {
		return this.repository.findAll().stream().map(mapper::customerToCustomerDTO).collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomer(Long id) throws ResourceNotFoundException{
		return this.repository.findById(id).map(mapper::customerToCustomerDTO).orElseThrow(ResourceNotFoundException::new);
	}

	@Override
	public void deleteById(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public CustomerDTO patchCustomer(Long id, CustomerDTO dto) {
		return this.repository.findById(id).map(customer -> {
			if (dto.getFirstName() != null) {
				customer.setFirstName(dto.getFirstName());
			}
			if (dto.getLastName() != null) {
				customer.setLastName(dto.getLastName());
			}
			
			return mapper.customerToCustomerDTO(this.repository.save(customer));
		}).orElseThrow(RuntimeException::new);
	}

	@Override
	public CustomerDTO createCustomer(CustomerDTO dto) {

		return saveAndReturn(mapper.customerDTOToCustomer(dto));
	}

	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO dto) {
		Customer customer = mapper.customerDTOToCustomer(dto);
		customer.setId(id);
		return saveAndReturn(customer);
	}

	private CustomerDTO saveAndReturn(Customer customer) {
		log.info("ID - " + customer.getId());
		Customer savedCustomer = this.repository.save(customer);
		return this.mapper.customerToCustomerDTO(savedCustomer);
	}

}
