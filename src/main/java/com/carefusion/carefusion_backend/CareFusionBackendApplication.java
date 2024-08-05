package com.carefusion.carefusion_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.support.StandardServletEnvironment;

@SpringBootApplication
public class CareFusionBackendApplication {

  static final Logger LOGGER = LoggerFactory.getLogger(CareFusionBackendApplication.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(CareFusionBackendApplication.class);
    StandardServletEnvironment env = new StandardServletEnvironment();
    env.setDefaultProfiles("develop");
    app.setEnvironment(env);
    LOGGER.debug("Started logging for carefusion-backend application");
    app.run(args);
  }

}
