package com.app.serviceInterface;

import com.app.entities.ApiResponseStore;
import com.app.entities.ApiWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ApiResponseInterface {

	ApiWrapper addApiResponse() throws JsonMappingException, JsonProcessingException;

	ApiResponseStore updateResponse(Long id, ApiResponseStore responseStore);

}
