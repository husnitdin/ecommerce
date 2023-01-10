package com.micro.securityclient.service.imlp;

import com.micro.securityclient.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailServiceImpl.class);

    private final JavaMailSender mailSender;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

//    @Override
//    @Async
//    public void send(String to, String email) {
//        try {
//            MimeMessage mimeMessage = mailSender.createMimeMessage();
//            MimeMessageHelper helper =
//                    new MimeMessageHelper(mimeMessage, "utf-8");
//            helper.setText(email, true);
//            helper.setTo(to);
//            helper.setSubject("Confirm your email");
//            helper.setFrom("hello@amigoscode.com");
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            LOGGER.error("failed to send email", e);
//            throw new IllegalStateException("failed to send email");
//        }
//    }

    @Override
    @Async
    public void send(String to, String email) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom("ankaalish@gmail.com");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject("Confirm your email");
            simpleMailMessage.setText(email);

            this.mailSender.send(simpleMailMessage);

        } catch (Exception e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
