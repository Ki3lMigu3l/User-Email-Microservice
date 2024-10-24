package com.github.ki3lmigu3l.email.consumer;

import com.github.ki3lmigu3l.email.dto.EmailRecordDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO emailRecordDTO) {
        System.out.println(emailRecordDTO.emailTo());
    }

}
