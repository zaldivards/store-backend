package sv.edu.ues.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import sv.edu.ues.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
}
