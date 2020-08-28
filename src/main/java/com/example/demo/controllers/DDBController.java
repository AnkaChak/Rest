package com.example.demo.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.DynamoDB.ProductInfo;
import com.example.demo.services.DynamoDBService;

@RestController
@RequestMapping("/v1/DDB")
@Validated
public class DDBController {
	
	private DynamoDBService ddbService;
	
	@PostMapping(value ="/createInfo", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<HttpStatus> createTopic(
				@Valid @RequestBody ProductInfo pi) {
		ddbService.createRecord(pi);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	
	public DDBController(DynamoDBService ddbService) {
		super();
		this.ddbService = ddbService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductInfo> getProductInfo (@PathVariable("id") String id)  {
		return new ResponseEntity<ProductInfo>(ddbService.findById(id),HttpStatus.OK);
	}

}
