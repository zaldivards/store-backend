package sv.edu.ues.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerListDTO {

	List<CustomerDTO> customers;
	
}
