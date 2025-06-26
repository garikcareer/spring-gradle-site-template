package com.example.service;

import org.springframework.stereotype.Service;

/**
 * Service class responsible for providing greeting messages. This class is marked as a Spring
 * Service component and can be injected into other Spring-managed beans.
 */
@Service
public class GreetingService {
  /**
   * Constructor for the GreetingService class. This is a package-private constructor intended for
   * instantiation within the same package. The class is designed to provide greeting messages and
   * is marked as a Spring Service component.
   */
  GreetingService() {}

  /**
   * Returns a greeting message. This method provides a static greeting string that can be accessed
   * by other components in the application.
   *
   * @return a greeting message as a string
   */
  public String getGreeting() {
    return "Hello from Spring 6.2.8 with no XML!";
  }
}
