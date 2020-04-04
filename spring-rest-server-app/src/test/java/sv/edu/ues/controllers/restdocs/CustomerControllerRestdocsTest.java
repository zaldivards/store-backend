package sv.edu.ues.controllers.restdocs;

import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseBody;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.snippet.Attributes;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import sv.edu.ues.controllers.CustomerController;
import sv.edu.ues.controllers.TestUtil;
import sv.edu.ues.model.CustomerDTO;
import sv.edu.ues.services.CustomerService;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "zaldivar.io", uriPort = 80)
@WebMvcTest(CustomerController.class)
@ComponentScan("sv.edu.ues.mapper")
public class CustomerControllerRestdocsTest {
	
	private static final String NAME = "<nombre del cliente>";

	private static final String LASTNAME = "<apellido del cliente>";
	
	private static final String DOC_ID = "v1/customer";

	@Autowired
	MockMvc mvc;
	
	@MockBean
	CustomerService service;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@SuppressWarnings("unchecked")
	final Map<String, Object> toMap(CustomerDTO customer){
		return objectMapper.convertValue(customer, Map.class);
	}
	
	@Test
	final void findById() throws Exception {
		CustomerDTO customer = new CustomerDTO();
		customer.setFirstName(NAME);
		customer.setLastName(LASTNAME);
		given(service.getCustomer(Mockito.anyLong())).willReturn(customer);
		mvc.perform(get("/api/v1/customers/{customer_id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
//		.andReturn().getResponse().getContentAsString();
				.andExpect(status().isOk())
				.andDo(document(
						DOC_ID, 
						pathParameters(parameterWithName("customer_id").description("ID del cliente")),
//						,
//						requestParameters(parameterWithName("someName").description("description"))
						//Datos en el response
						responseFields(
								fieldWithPath("firstName").description("Nombre del cliente").type(String.class),
								fieldWithPath("lastName").description("Apelldo del cliente").type(String.class)),
						responseBody(toMap(customer))
						));
	}
	
	@Test
	final void createCustomer() throws Exception {
		CustomerDTO dto = new CustomerDTO();
		dto.setFirstName(NAME);
		dto.setLastName(LASTNAME);
		ConstraintFields fields = new ConstraintFields(CustomerDTO.class); 
		given(this.service.createCustomer(Mockito.any(CustomerDTO.class))).willReturn(dto);
		mvc.perform(post("/api/v1/customers/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.toJson(dto))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(document(
						DOC_ID.concat("-new"),
						requestFields(
								fields.withPath("firstName").description("Nombre del nuevo cliente").type(String.class),
								fields.withPath("lastName").description("Apellido del nuevo cliente").type(String.class)
						),
						responseFields(
								fieldWithPath("firstName").description("Nombre del nuevo cliente").type(String.class),
								fieldWithPath("lastName").description("Apellido del nuevo cliente").type(String.class)),
						responseBody(toMap(dto))
						));
	}
	
	private static class ConstraintFields {
		private final ConstraintDescriptions constraintDescriptions;
		ConstraintFields(Class<?> clazz) {
			this.constraintDescriptions = new ConstraintDescriptions(clazz);
		}
		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(Attributes.key("constraints").value(
					StringUtils.collectionToDelimitedString(this.constraintDescriptions
							.descriptionsForProperty(path),".")));
		}
	}

}








