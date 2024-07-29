package com.carepulse.carepulse_veins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.support.StandardServletEnvironment;

@SpringBootApplication
public class CarepulseVeinsApplication {

  static final Logger LOGGER = LoggerFactory.getLogger(CarepulseVeinsApplication.class);

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(CarepulseVeinsApplication.class);
    StandardServletEnvironment env = new StandardServletEnvironment();
    env.setDefaultProfiles("develop");
    app.setEnvironment(env);
    LOGGER.debug("Started logging for carepulse vein application");
    app.run(args);
  }

}
