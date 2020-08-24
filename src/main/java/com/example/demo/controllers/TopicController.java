package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.Topic;
import com.example.demo.exceptions.TopicNotFoundException;
import com.example.demo.exceptions.handlers.CustomErrorResponse;
import com.example.demo.exceptions.handlers.ValidationErrorResponse;
import com.example.demo.services.TopicService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/v1/Topic")
@Validated
public class TopicController {

	public TopicController(TopicService topicService) {
		super();
		this.topicService = topicService;
	}

	TopicService topicService;

	@GetMapping("/All")
	public ResponseEntity<List<Topic>> getTopics() {
		return new ResponseEntity<List<Topic>>(topicService.getTopics(), HttpStatus.OK);
	}

	@Operation(summary = "Get a Topic by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the topic", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Topic.class, description = "Topic")) }),
			@ApiResponse(responseCode = "500", description = "Invalid id supplied", content = @Content(schema = @Schema(implementation=ValidationErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "Topic not found", content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))) })

	@GetMapping("/{id}")
	public ResponseEntity<Topic> getTopic(
			@Parameter(name = "id", description ="id of the tpic", schema = @Schema(description = "var 1", type = "String")) @Min(5) @PathVariable("id") String id)
			throws TopicNotFoundException {
		return new ResponseEntity<Topic>(topicService.getTopic(id), HttpStatus.OK);
	}
	@Operation(summary = "Add a new topic", description = "", tags = { "topics" })
	@ApiResponses(value = { 
	        @ApiResponse(responseCode = "201", description = "Topic created",
	                content = @Content(schema = @Schema(implementation = Topic.class))), 
	        @ApiResponse(responseCode = "400", description = "Invalid input", content = { @Content(schema=@Schema(implementation = ValidationErrorResponse.class))}), 
	        @ApiResponse(responseCode = "409", description = "Topic already exists") })	
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Topic Request body.", 
    content = @Content(schema = @Schema(implementation = Topic.class)), required = true)
	@PostMapping(value ="/createTopic", consumes = { "application/json", "application/xml" })
	public ResponseEntity<HttpStatus> createTopic(
				@Valid @RequestBody Topic topic) {
		topicService.createTopic(topic);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

	@PutMapping("/updateTopic")
	public ResponseEntity<HttpStatus> updateTopic(@Valid @RequestBody Topic topic) {
		topicService.updateTopic(topic);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/deleteTopic/{id}")
	public ResponseEntity<HttpStatus> deleteTopic(@PathVariable("id") String id) {
		topicService.deleteTopic(id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
