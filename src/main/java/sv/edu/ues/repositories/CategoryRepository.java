package sv.edu.ues.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sv.edu.ues.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Optional<Category> findByName(String name);
	
}
