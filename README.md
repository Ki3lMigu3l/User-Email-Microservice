# Microservices: User and Email

Este projeto consiste em um microserviço que gerencia o cadastro de usuários e envia um e-mail de confirmação após o registro. Utilizando RabbitMQ para o envio assíncrono de mensagens, o serviço garante uma experiência fluida e eficiente para o usuário.

<h3 align="center">Tecnologias e Ferramentas</h3>

<p align="center">
  <a href="https://skillicons.dev">
    <img src="https://skillicons.dev/icons?i=git,java,spring,postman,postgres,rabbitmq" />
  </a>
</p>

### RabbitMQ
RabbitMQ é um sistema de gerenciamento de mensagens de código aberto que implementa o padrão AMQP (Advanced Message Queuing Protocol). Ele permite que aplicativos e serviços se comuniquem de forma assíncrona por meio de mensagens, facilitando a troca de informações entre sistemas diferentes.

## Estrutura do Projeto

### Endpoint
#### Controller: Cadastro de User

- POST /users

Este endpoint permite o cadastro de um novo usuário. Os dados do usuário são recebidos em formato JSON e validados antes de serem salvos no banco de dados. Após o cadastro, um e-mail de confirmação é enviado ao usuário.

<div align="center">
  <img src="https://github.com/Ki3lMigu3l/User-Email-Microservice/blob/main/readme/controller-user.png" alt="readme-init.png" width="984" height="378"/>
</div>

## Service User
O método save na camada de serviço salva o usuário e publica uma mensagem para o envio do e-mail.

<div align="center">
  <img src="https://github.com/Ki3lMigu3l/User-Email-Microservice/blob/main/readme/service-user.png" alt="readme-init.png" width="984" height="378"/>
</div>

A anotação @Transactional do Spring Framework garante que, se ocorrer um erro (ou uma exceção não verificada) durante a execução do método, todas as operações de banco de dados realizadas nesse método serão revertidas, ou seja, ocorrerá um rollback. 

## Configuração RabbitMQ
A configuração do RabbitMQ permite converter mensagens em formato JSON.

<div align="center">
  <img src="https://github.com/Ki3lMigu3l/User-Email-Microservice/blob/main/readme/messageConverter.png" alt="readme-init.png" width="772" height="378"/>
</div>

## Publicador de Mensagens de E-mail

A classe UserProducer é responsável por criar e enviar mensagens de e-mail após o cadastro do usuário. Ela utiliza RabbitTemplate para enviar uma mensagem para a fila RabbitMQ.

<div align="center">
  <img src="https://github.com/Ki3lMigu3l/User-Email-Microservice/blob/main/readme/user-producer.png" alt="readme-init.png" width="984" height="678"/>
</div>

<br>

### Conclusão

Este projeto exemplifica a implementação de um microserviço de cadastro de usuários que integra funcionalidades de persistência de dados e envio de e-mails. Utilizando as tecnologias Spring Boot, AMQP e PostgreSQL, o sistema garante não apenas a criação de usuários, mas também a notificação através de e-mails, proporcionando uma experiência de usuário aprimorada.

A configuração do RabbitMQ para o envio assíncrono de e-mails demonstra a importância de sistemas desacoplados e escaláveis, permitindo que a aplicação se mantenha responsiva mesmo durante operações que podem demandar tempo. A utilização de transações com @Transactional assegura a integridade dos dados, garantindo que, em caso de falhas, todas as alterações sejam revertidas, preservando o estado do banco de dados.

Esperamos que este projeto sirva como uma base sólida para futuras implementações e uma fonte de inspiração para criar soluções eficientes e escaláveis no ambiente de microserviços.
