package com.app.serviceImpl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.entities.ApiResponseStore;
import com.app.entities.ApiWrapper;
import com.app.repository.ApiResponseRepository;
import com.app.serviceInterface.ApiResponseInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiResponseImpl implements ApiResponseInterface {

	@Autowired
	private ApiResponseRepository apiResponseRepository;

	private final String url = "https://api.publicapis.org/entries";

	@Override
	public ApiWrapper addApiResponse() throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		ObjectMapper mapper = new ObjectMapper();
		ApiWrapper apiWrapper = mapper.readValue(responseEntity.getBody(), ApiWrapper.class);
		List<ApiResponseStore> response = apiWrapper.getEntries();

		this.apiResponseRepository.saveAll(response);
		return apiWrapper;
	}

	@Override
	public ApiResponseStore updateResponse(Long id, ApiResponseStore responseStore) {
		ApiResponseStore apiResponseStore = this.apiResponseRepository.findById(id).orElseThrow();

		apiResponseStore.setApi(responseStore.getApi());
		apiResponseStore.setAuth(apiResponseStore.getAuth());
		apiResponseStore.setCategory(responseStore.getCategory());
		apiResponseStore.setCors(responseStore.getCors());
		apiResponseStore.setDescription(responseStore.getDescription());
		apiResponseStore.setHttps(responseStore.getHttps());
		apiResponseStore.setLink(responseStore.getLink());
		apiResponseRepository.save(apiResponseStore);
		return responseStore;
	}

}
