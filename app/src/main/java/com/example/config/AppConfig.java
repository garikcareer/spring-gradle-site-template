package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

/**
 * AppConfig is a configuration class for a Spring-based web application.
 *
 * <p>It is annotated with: - {@code @Configuration}: Indicates that the class contains
 * configuration information. - {@code @EnableWebMvc}: Enables Spring MVC features in the
 * application. - {@code @ComponentScan}: Specifies the base package(s) to scan for annotated
 * components. - {@code @Import}: Imports additional configuration classes, such as {@code
 * JdbcConfig}.
 *
 * <p>This class implements {@code WebMvcConfigurer} to provide additional configuration for the
 * Spring MVC setup.
 *
 * <p>The class defines several Spring beans: - {@code templateResolver}: Configures the template
 * resolver for Thymeleaf to handle HTML files. - {@code templateEngine}: Configures the template
 * engine for processing Thymeleaf templates. - {@code viewResolver}: Configures the view resolver
 * to render views using Thymeleaf.
 *
 * <p>Logging is enabled to track the creation of the beans for debugging purposes.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.example")
@Import(JdbcConfig.class)
public class AppConfig implements WebMvcConfigurer {
  Logger logger = LoggerFactory.getLogger(AppConfig.class);

  /**
   * Default constructor for the AppConfig class.
   *
   * <p>Initializes the configuration class for the Spring-based web application. This constructor
   * is automatically invoked by the Spring Framework to create an instance of the configuration
   * class.
   */
  AppConfig() {}

  /**
   * Creates and configures a {@link SpringResourceTemplateResolver} bean for resolving Thymeleaf
   * templates.
   *
   * <p>The method initializes a template resolver with predefined properties, such as the template
   * prefix, suffix, and character encoding. This resolver is responsible for locating and
   * processing the Thymeleaf templates used in the application.
   *
   * @return a configured {@link SpringResourceTemplateResolver} instance
   */
  @Bean
  public SpringResourceTemplateResolver templateResolver() {
    SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
    resolver.setPrefix("/WEB-INF/views/");
    resolver.setSuffix(".html");
    resolver.setCharacterEncoding("UTF-8");
    logger.info("templateResolver created");
    return resolver;
  }

  /**
   * Configures and creates a {@link SpringTemplateEngine} bean for processing Thymeleaf templates.
   *
   * <p>The method sets the template resolver to be used by the template engine for resolving and
   * rendering Thymeleaf templates. This is a critical part for enabling Thymeleaf-based view
   * rendering in a Spring MVC application. The template resolver is expected to be provided by the
   * {@code templateResolver} method, which defines
   *
   * @return a configured {@link SpringTemplateEngine} instance
   */
  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine engine = new SpringTemplateEngine();
    engine.setTemplateResolver(templateResolver());
    logger.info("templateEngine created");
    return engine;
  }

  /**
   * Configures and creates a {@link ThymeleafViewResolver} bean for resolving views using the
   * Thymeleaf template engine.
   *
   * <p>The resolver sets the template engine to be used for rendering views and specifies the
   * character encoding to "UTF-8". The {@link ThymeleafViewResolver} is responsible for resolving
   * logical
   *
   * @return a configured {@link ThymeleafViewResolver} instance
   */
  @Bean
  public ThymeleafViewResolver viewResolver() {
    ThymeleafViewResolver resolver = new ThymeleafViewResolver();
    resolver.setTemplateEngine(templateEngine());
    resolver.setCharacterEncoding("UTF-8");
    logger.info("viewResolver created");
    return resolver;
  }
}
