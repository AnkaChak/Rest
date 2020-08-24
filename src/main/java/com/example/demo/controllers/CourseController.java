package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Domain.Course;
import com.example.demo.exceptions.CourseNotFoundException;
import com.example.demo.exceptions.TopicNotFoundException;
import com.example.demo.services.CourseService;
import com.example.demo.services.TopicService;
@RestController
@RequestMapping("/v1/Topic/{topicId}/Courses")
public class CourseController {
	
	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	CourseService courseService;
	@Autowired
	TopicService topicService;
	
	@GetMapping("/All")
	public ResponseEntity<List<Course>> getCourses (@PathVariable("topicId") String topicId) {
		return new ResponseEntity<List<Course>>(courseService.getCourses(topicId),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Course> getCourse (@PathVariable("topicId") String topicId,@PathVariable("id") String id) throws CourseNotFoundException {
		return new ResponseEntity<Course>(courseService.getCourse(topicId, id),HttpStatus.OK);
	}
	
	@PostMapping("/createCourse")
	public ResponseEntity< HttpStatus> createCourse (@PathVariable("topicId") String topicId,@RequestBody Course course) throws TopicNotFoundException {
		course.setTopic(topicService.getTopic(topicId));
		courseService.createCourse(course);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
	@PutMapping("/updateCourse/{id}")
	public ResponseEntity< HttpStatus> updateCourse (@PathVariable("topicId") String topicId,@PathVariable("id") String id, @RequestBody Course course) throws TopicNotFoundException {
		course.setTopic(topicService.getTopic(topicId));
		courseService.updateCourse(course,id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/deleteCourse/{id}")
	public ResponseEntity< HttpStatus> deleteCourse (@PathVariable("topicId") String topicId, @PathVariable("id") String id) throws CourseNotFoundException {
		courseService.deleteCourse(topicId,id);
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
	}
}
