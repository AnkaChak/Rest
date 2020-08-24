package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Course;

public interface CourseRepositroy extends CrudRepository<Course, String> {
	List<Course> findByTopicId(String topicId);
}
