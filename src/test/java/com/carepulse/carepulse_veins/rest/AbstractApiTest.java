package com.carepulse.carepulse_veins.rest;

import com.carepulse.carepulse_veins.config.ErrorConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class AbstractApiTest {

  protected MockMvc mockMvc;
  protected ObjectMapper objectMapper;

  protected abstract Object setupController();

  @BeforeEach
  void setUpMockMvc() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    mockMvc = MockMvcBuilders.standaloneSetup(setupController())
        .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
        .setControllerAdvice(new ErrorConfig())
        .setMessageConverters(new ByteArrayHttpMessageConverter(),
            new MappingJackson2HttpMessageConverter(objectMapper))
        .build();
  }
}
