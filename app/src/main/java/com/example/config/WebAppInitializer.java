package com.example.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * WebAppInitializer is an implementation of the {@link WebApplicationInitializer} interface that is
 * used to configure a web application programmatically, instead of using a traditional `web.xml`
 * configuration file.
 *
 * <p>This class initializes the Spring application context and configures the ServletContext for
 * the application. It also registers the DispatcherServlet, which acts as the front controller for
 * the Spring MVC application.
 *
 * <p>The following operations are performed during startup: - Creates an instance of {@link
 * AnnotationConfigWebApplicationContext}. - Registers the application configuration class ({@code
 * AppConfig}) with the context. - Sets the {@code ServletContext} for the created application
 * context. - Refreshes the application context. - Registers a {@link DispatcherServlet} with the
 * {@code ServletContext}. - Configures initialization parameters for the {@link DispatcherServlet},
 * such as asynchronous request support and logging details.
 *
 * <p>This configuration ensures that all incoming requests are handled by the DispatcherServlet,
 * and Spring's web application context is properly set up to process web requests.
 */
public class WebAppInitializer implements WebApplicationInitializer {
  Logger logger = LoggerFactory.getLogger(WebAppInitializer.class);

  /**
   * Default constructor for the WebAppInitializer class.
   *
   * <p>Initializes an instance of the WebAppInitializer class. This constructor is automatically
   * invoked by the Spring Framework during application startup to create an instance of the
   * WebApplicationInitializer implementation.
   *
   * <p>Logs a message indicating the creation of the WebAppInitializer instance. This logging is
   * helpful for debugging purposes and to verify that the initializer has been properly
   * instantiated.
   */
  public WebAppInitializer() {
    logger.info("WebAppInitializer created");
  }

  @Override
  public void onStartup(@NonNull ServletContext servletContext) {
    AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
    context.register(AppConfig.class);
    context.setServletContext(servletContext);
    context.refresh();

    ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("dispatcher", new DispatcherServlet(context));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");
    dispatcher.setAsyncSupported(true);
    dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    dispatcher.setInitParameter("log", "true");
    dispatcher.setInitParameter("logLevel", "DEBUG");
    dispatcher.setInitParameter("logPrefix", "com.example");
    dispatcher.setInitParameter("dispatchOptionsRequest", "true");
    dispatcher.setInitParameter("dispatchTraceRequest", "true");
    dispatcher.setInitParameter("enableLoggingRequestDetails", "true");
    dispatcher.setInitParameter("enableLoggingResponseDetails", "true");
    dispatcher.setInitParameter("enableLoggingAccess", "true");
    dispatcher.setInitParameter("enableLoggingAccessForStatusCodes", "true");
    dispatcher.setInitParameter("enableLoggingAccessForUriPatterns", "true");
  }
}
