package com.example.demo.exceptions.handlers;

import java.time.LocalDateTime;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.CourseNotFoundException;

@RestControllerAdvice

public class ExceptionAdvice {
 /*@ExceptionHandler(value = TopicNotFoundException.class)
 @ResponseStatus(HttpStatus.NOT_FOUND)
 public ResponseEntity<CustomErrorResponse<TopicNotFoundException>> handleTopicNotFoundException(TopicNotFoundException e) {

        CustomErrorResponse<TopicNotFoundException> error = new CustomErrorResponse<TopicNotFoundException>("TOPIC_NOT_FOUND_ERROR", e.getMessage());

        error.setTimeStamp(LocalDateTime.now());

        error.setStatus((HttpStatus.NOT_FOUND.value()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }   */
 
 /*@ResponseStatus(HttpStatus.BAD_GATEWAY)
 public ResponseEntity<CustomErrorResponse> handleTopicNotFoundException(TopicNotFoundException e) {

        CustomErrorResponse error = new CustomErrorResponse("TOPIC_NOT_FOUND_ERROR", e.getMessage());

        error.setTimeStamp(LocalDateTime.now());

        error.setStatus((HttpStatus.NOT_FOUND.value()));

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }*/

 @ExceptionHandler(value = CourseNotFoundException.class)
 @ResponseStatus(HttpStatus.NOT_FOUND)
 @ResponseBody
 public ResponseEntity<CustomErrorResponse> handleTopicNotFoundException(CourseNotFoundException e) {

	 CustomErrorResponse error = new CustomErrorResponse("TOPIC_NOT_FOUND_ERROR", e.getMessage());

	 error.setTimeStamp(LocalDateTime.now());

	 error.setStatus((HttpStatus.NOT_FOUND.value()));

	 return new ResponseEntity<CustomErrorResponse>(error, HttpStatus.NOT_FOUND);

 }  
 
 @ExceptionHandler(ConstraintViolationException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 ValidationErrorResponse onConstraintValidationException(
     ConstraintViolationException e) {
   ValidationErrorResponse error = new ValidationErrorResponse();
   for (ConstraintViolation violation : e.getConstraintViolations()) {
     error.getViolations().add(
       new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
   }
   return error;
 }

 @ExceptionHandler(MethodArgumentNotValidException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 @ResponseBody
 ValidationErrorResponse onMethodArgumentNotValidException(
     MethodArgumentNotValidException e) {
   ValidationErrorResponse error = new ValidationErrorResponse();
   for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
     error.getViolations().add(
       new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
   }
   return error;
 }


}