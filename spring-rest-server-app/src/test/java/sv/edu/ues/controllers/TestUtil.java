package sv.edu.ues.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import sv.edu.ues.model.CustomerDTO;

public class TestUtil {
	
	public static String toJson(CustomerDTO dto) {
		try {
			return new ObjectMapper().writeValueAsString(dto);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}

}
