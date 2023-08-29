package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.ApiResponseStore;
import com.app.entities.ApiWrapper;
import com.app.serviceInterface.ApiResponseInterface;

@RestController
public class ApiResponseController {

	@Autowired
	private ApiResponseInterface apiResponseInterface;

	@GetMapping("/fromOpenApi")
	public ResponseEntity<?> getResponse() {
		try {
			ApiWrapper addApiResponse = this.apiResponseInterface.addApiResponse();
			return new ResponseEntity<>(addApiResponse, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateResponse/{id}")
	public ResponseEntity<?> updateResponse(@PathVariable("id") Long id, @RequestBody ApiResponseStore responseStore) {
		try {

			ApiResponseStore api = this.apiResponseInterface.updateResponse(id, responseStore);
			return new ResponseEntity<>(api, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
