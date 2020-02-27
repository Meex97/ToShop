package com.project.toshop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/*
The EmailService is a wrapper for JavaMailSender. We add the @Async annotation to the sendEmail method
so that the calling code doesn't have to wait for the
send operation to complete in order to continue.
 */


@Service
public class EmailService {

    private JavaMailSender mailSender;


    @Autowired
    public  EmailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }


    @Async
    public void sendEmail(SimpleMailMessage email){
        mailSender.send(email);
    }

}
