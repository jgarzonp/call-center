package com.jgarzon.cc;

import com.jgarzon.cc.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Initialize the Spring Boot Application
 *
 * @author <a href="mailto:jgarzon@gptech.com.co">Jonathan Garz&oacute;n</a>
 * @version 1.0 - 2019-05-16 9:43 AM
 * @since call-center-1.0.0
 */
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties({ApplicationProperties.class})
public class InitApp {

    // -----------------------------------------------------------------------------------------------------------------
    // Logger
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(InitApp.class);

    // -----------------------------------------------------------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Spring context environment instance
     */
    private final Environment env;

    // -----------------------------------------------------------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Init application definition
     *
     * @param env The spring context environment instance to set
     */
    public InitApp(Environment env) {
        this.env = env;
    }

    /**
     * Initializes The Application
     * <p>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * </p>
     */
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        LOG.info("Active Profiles: {}", activeProfiles);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments
     * @throws UnknownHostException if the local host name could not be resolved into an address
     */
    public static void main(String[] args) throws UnknownHostException {

        SpringApplication app = new SpringApplication(InitApp.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("\n----------------------------------------------------------\n\t" +
                    "Application '{}' is running!:\n\t" +
                    "Local:\t\t127.0.0.1:{}\n\t" +
                    "External: \thttp://{}:{}\n\t" +
                    "Profile(s):\t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"), env.getProperty("server.port"),
            InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"), env.getActiveProfiles());
    }

}
