package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The App class serves as the entry point of the application. It initializes the application and
 * logs its start-up process.
 */
public class App {
  Logger logger = LoggerFactory.getLogger(App.class);

  /**
   * Constructs a new instance of the App class. This constructor initializes the application and
   * prepares it for execution.
   */
  public App() {}

  /**
   * The main method serves as the entry point of the application. It initializes the application
   * and logs the start-up process.
   *
   * @param args an array of command-line arguments passed to the application
   */
  public static void main(String[] args) {
    Logger logger = LoggerFactory.getLogger(App.class);
    new App();
    logger.info("App started");
  }
}
