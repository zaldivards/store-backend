package sv.edu.ues.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {
	@ApiModelProperty(value = "Nombre del cliente", required = true)
	String firstName;
	@ApiModelProperty(value = "Apellido del cliente", required = true)
	String lastName;
}
