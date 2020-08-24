package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Domain.Topic;
import com.example.demo.exceptions.TopicNotFoundException;
import com.example.demo.persistence.TopicRepositroy;

@Service
public class TopicService {

	//List<Topic> topics = new ArrayList<>();
	private TopicRepositroy topicRepositroy;
	
	public TopicService(TopicRepositroy topicRepositroy) {
		super();
		this.topicRepositroy = topicRepositroy;
	}

	public List<Topic> getTopics () {
		/*
		 * topics.add(new Topic("Spring Boot", "Micro Services" , "Topic1"));
		 * topics.add(new Topic("Spring Core", "All that you need" , "Topic2"));
		 * topics.add(new Topic("Spring Aspects", "Cross Cutting Concerns", "Topic3"));
		 */
		List<Topic> topics = new ArrayList<>();
		topicRepositroy.findAll().forEach(topics::add);
		return topics;
	}
	
	public Topic getTopic (String id) throws TopicNotFoundException {
		//return topics.stream().filter(topic-> topic.getId().equals(id)).findFirst().get();
		return topicRepositroy.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic With Id:" + id +" was not found"));
	}
	
	public void createTopic (Topic topic) {
		//topics.add(topic);
		topicRepositroy.save(topic);
	}
	
	public void updateTopic (Topic updatedTopic) {
		//topics = topics.stream().map(topic -> topic.getId().equals(updatedTopic.getId()) ? updatedTopic : topic).collect(Collectors.toList());
		topicRepositroy.save(updatedTopic);
	}
	
	public void deleteTopic (String id) {
		 //topics.removeIf(topic -> topic.getId().equals(id));
		topicRepositroy.deleteById(id);
	}
}
