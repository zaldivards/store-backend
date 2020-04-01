package sv.edu.ues.config;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sv.edu.ues.domain.Category;
import sv.edu.ues.domain.Customer;
import sv.edu.ues.repositories.CategoryRepository;
import sv.edu.ues.repositories.CustomerRepository;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	CategoryRepository repository;
	CustomerRepository repository2;

	

	public Bootstrap(CategoryRepository repository, CustomerRepository repository2) {
		super();
		this.repository = repository;
		this.repository2 = repository2;
	}
	
	public void run() {
		saveCustomers();
		saveCategories();
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		saveCustomers();
		saveCategories();
	}

	private void saveCustomers() {
		this.repository2.saveAll(List.of(
				new Customer(1L, "Steven", "Zaldivar"),
				new Customer(2L, "Christian", "Herrera")
				));
	}
	
	private void saveCategories() {
		this.repository.saveAll(List.of(
				new Category(1L, "Granos"),
				new Category(2L, "Frutas"),
				new Category(3L, "Carnes")
				));
	}
}