package com.github.ki3lmigu3l.user.producer;

import com.github.ki3lmigu3l.user.dto.EmailDTO;
import com.github.ki3lmigu3l.user.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(user.getUserId());
        emailDto.setEmailTo(user.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(user.getName() + ", Seja bem-vindo(a)! " +
                "\nAgradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
