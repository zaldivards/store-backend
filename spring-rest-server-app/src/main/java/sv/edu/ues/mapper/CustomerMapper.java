package sv.edu.ues.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import sv.edu.ues.domain.Customer;
import sv.edu.ues.model.CustomerDTO;

@Mapper
public interface CustomerMapper {

	CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
	
	CustomerDTO customerToCustomerDTO(Customer customer);
	Customer customerDTOToCustomer(CustomerDTO dto);
}
