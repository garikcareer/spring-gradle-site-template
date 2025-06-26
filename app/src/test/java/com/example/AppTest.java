package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AppTest {
  @Test
  void appStarts() {
    App app = new App();
    assertNotNull(app, "app should not be null");
  }
}
