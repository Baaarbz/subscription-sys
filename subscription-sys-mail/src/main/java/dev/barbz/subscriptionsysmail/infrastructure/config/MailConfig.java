package dev.barbz.subscriptionsysmail.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

//        mailSender.setHost(mailServerHost);
//        mailSender.setPort(mailServerPort);
//
//        mailSender.setUsername(mailServerUsername);
//        mailSender.setPassword(mailServerPassword);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", mailServerAuth);
//        props.put("mail.smtp.starttls.enable", mailServerStartTls);
//        props.put("mail.debug", "true");

        return mailSender;
    }
}
