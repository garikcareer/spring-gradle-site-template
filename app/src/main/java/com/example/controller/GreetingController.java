package com.example.controller;

import com.example.service.GreetingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * A Spring MVC Controller responsible for handling HTTP GET requests to the "/greeting" endpoint.
 * This controller interacts with the GreetingService to retrieve greeting messages and adds the
 * resulting message to the model for rendering with a view template.
 */
@Controller
public class GreetingController {
  private final GreetingService greetingService;

  /**
   * Constructs a new GreetingController instance, allowing for dependency injection of the
   * GreetingService to handle greeting-related logic.
   *
   * @param greetingService the service responsible for providing greeting messages
   */
  public GreetingController(GreetingService greetingService) {
    this.greetingService = greetingService;
  }

  /**
   * Handles HTTP GET requests to the "/greeting" endpoint. This method retrieves a greeting message
   * from the {@link GreetingService}, adds it to the provided {@link Model}, and returns the name
   * of the view template to render.
   *
   * @param model the {@link Model} to which the greeting message is added for rendering in the view
   * @return the name of the view template ("greeting") to be rendered
   */
  @GetMapping("/greeting")
  public String greeting(Model model) {
    model.addAttribute("message", greetingService.getGreeting());
    return "greeting";
  }
}
