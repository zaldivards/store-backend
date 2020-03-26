package sv.edu.ues.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CategoryListDTO {
	List<CategoryDTO> categories;
}
