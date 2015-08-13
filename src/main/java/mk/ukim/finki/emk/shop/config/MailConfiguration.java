package mk.ukim.finki.emk.shop.config;

/**
 * Created by Nadica-PC on 8/8/2015.
 */

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfiguration implements EnvironmentAware {

    static final String ENV_SPRING_MAIL = "mail.";
    static final String PARTNERS_ENV_SPRING_MAIL = "partners.mail.";
    static final String DEFAULT_HOST = "127.0.0.1";
    static final String PROP_HOST = "host";
    static final String DEFAULT_PROP_HOST = "localhost";
    static final String PROP_ACTIVE = "active";
    static final String PROP_PORT = "port";
    static final String PROP_USER = "username";
    static final String PROP_PASSWORD = "password";
    static final String PROP_PROTO = "protocol";
    static final String PROP_TLS = "tls";
    static final String PROP_AUTH = "auth";
    static final String PROP_SMTP_AUTH = "mail.smtp.auth";
    static final String PROP_STARTTLS = "mail.smtp.starttls.enable";
    static final String PROP_TRANSPORT_PROTO = "mail.transport.protocol";

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, ENV_SPRING_MAIL);
    }


    protected RelaxedPropertyResolver propertyResolver;

    @Bean
    public JavaMailSender javaMailSender() {
        System.out.println("Configuring mail sender");

        boolean active = propertyResolver.getProperty(PROP_ACTIVE, Boolean.class, false);
        if (!active){
            System.out.println("Using mock mail sender");
         JavaMailSender sender = new MockJavaMailSenderImpl();
            return sender;
        }
        String host = propertyResolver.getProperty(PROP_HOST, DEFAULT_PROP_HOST);
        int port = propertyResolver.getProperty(PROP_PORT, Integer.class, 0);
        String user = propertyResolver.getProperty(PROP_USER);
        String password = propertyResolver.getProperty(PROP_PASSWORD);
        String protocol = propertyResolver.getProperty(PROP_PROTO);
        Boolean tls = propertyResolver.getProperty(PROP_TLS, Boolean.class, false);
        Boolean auth = propertyResolver.getProperty(PROP_AUTH, Boolean.class, false);

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        if (host != null && !host.isEmpty()) {
            sender.setHost(host);
        } else {
            System.out.println("Warning! Your SMTP server is not configured. We will try to use one on localhost.");
            System.out.println("Did you configure your SMTP settings in your application.properties?");
            sender.setHost(DEFAULT_HOST);
        }
        sender.setPort(port);
        sender.setUsername(user);
        sender.setPassword(password);

        Properties sendProperties = new Properties();
        sendProperties.setProperty(PROP_SMTP_AUTH, auth.toString());
        sendProperties.setProperty(PROP_STARTTLS, tls.toString());
        sendProperties.setProperty(PROP_TRANSPORT_PROTO, protocol);
        sender.setJavaMailProperties(sendProperties);
        System.out.println("Sender created");
        return sender;
    }

}