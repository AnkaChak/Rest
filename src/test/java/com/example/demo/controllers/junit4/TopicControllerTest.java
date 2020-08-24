package com.example.demo.controllers.junit4;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.Domain.Topic;
import com.example.demo.controllers.TopicController;
import com.example.demo.services.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers =TopicController.class)
public class TopicControllerTest {
	@Autowired
	MockMvc mvc;
	@Autowired
	  private ObjectMapper objectMapper;
	@MockBean
	TopicService topicService;

	  @Test
	  public void whenInputIsInvalid_thenReturnsStatus400() throws Exception {
	    Topic input = invalidInput();
	    String body = objectMapper.writeValueAsString(input);

	    mvc.perform(post("/v1/Topic/createTopic")
	            .contentType("application/json")
	            .content(body))
	            .andExpect(status().isBadRequest());
	  }
	  
	  @Test
	  public void whenInputIsvalid_thenReturnsStatus200() throws Exception {
	    Topic input = validInput();
	    String body = objectMapper.writeValueAsString(input);

	    mvc.perform(post("/v1/Topic/createTopic")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(body))
	            .andExpect(status().isCreated());
	  }
	  
	  Topic invalidInput() {
		  return new Topic("Inv", "Description", "Topic1");
	  }
	  
	  Topic validInput() {
		  return new Topic("Topic Name", "email@email.com", "Topic1");
	  }
}
