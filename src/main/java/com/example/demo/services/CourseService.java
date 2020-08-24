package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.demo.Domain.Course;
import com.example.demo.exceptions.CourseNotFoundException;
import com.example.demo.persistence.CourseRepositroy;

@Service
public class CourseService {

	private CourseRepositroy courseRepositroy;
	
	public CourseService(CourseRepositroy courseRepositroy) {
		super();
		this.courseRepositroy = courseRepositroy;
	}

	public List<Course> getCourses (String topicId) {
		List<Course> courses = new ArrayList<>();
		courses.addAll(courseRepositroy.findByTopicId(topicId));
		return courses;
	}
	
	public Course getCourse (String topicId, String id) throws CourseNotFoundException {
		return courseRepositroy.findByTopicId(topicId).stream().filter(course-> course.getId().equals(id)).findFirst().
				orElseThrow(() -> new CourseNotFoundException("Course With the id:" + id +" was not found"));
	}
	
	public void createCourse (Course course) {
		courseRepositroy.save(course);
	}
	
	public void updateCourse (Course updatedCourse, String courseId) {
		Assert.isTrue(updatedCourse.getId().equals(courseId), "Course Ids doesn't match");
		courseRepositroy.save(updatedCourse);
	}
	
	public void deleteCourse (String topicId,String id) throws CourseNotFoundException {
		courseRepositroy.delete(getCourse (topicId, id));
	}
}
