package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.Domain.Topic;

public interface TopicRepositroy extends CrudRepository<Topic, String> {

}
