package sv.edu.ues.services;

import java.util.List;

import javassist.NotFoundException;
import sv.edu.ues.model.CustomerDTO;

public interface CustomerService {

	List<CustomerDTO> getAllCustomer();
	CustomerDTO getCustomer(Long id) throws NotFoundException;
	CustomerDTO createCustomer(CustomerDTO dto);
	CustomerDTO updateCustomer(Long id, CustomerDTO dto);
	void deleteById(Long id);
	CustomerDTO patchCustomer(Long id, CustomerDTO dto);
}
