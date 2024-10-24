package com.github.ki3lmigu3l.email.consumer;

import com.github.ki3lmigu3l.email.dto.EmailRecordDTO;
import com.github.ki3lmigu3l.email.model.Email;
import com.github.ki3lmigu3l.email.service.EmailService;
import com.github.ki3lmigu3l.email.service.impl.EmailServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailServiceImpl emailService;

    public EmailConsumer(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO) {
        var email = new Email();
        BeanUtils.copyProperties(emailRecordDTO, email);
        emailService.sendEmail(email);
        System.out.println("E-mail enviado!");
    }

}
