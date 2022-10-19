[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

[//]: # ([![LinkedIn][linkedin-shield]][linkedin-url])
<h1 align="center">Welcome to Subscription System 👋</h1>

## Author

👤 **Eduardo Barbosa Tarrío**

* Github: [@Baaarbz](https://github.com/Baaarbz)
* LinkedIn: [@eduardobarbosatarrio](https://linkedin.com/in/eduardobarbosatarrio)

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#runlocal">Run local</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

Newsletter subscription system.

<p align="right">(<a href="#top">back to top</a>)</p>

### Built With

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Docker](https://www.docker.com/)
* [RabbitMQ](https://www.rabbitmq.com/)

> RabbitMQ is used as intermediate (message broker) to realize the communication between the core and the mail service.
> This tool uses the AMQP (Advanced Message Queuing Protocol) which it is supported by Spring AMQP framework and
> provides features like persistent storage of messages, message filtering, and message transformation.
> This projects only use the queues because it is a simple application but AMQP provides a lot of useful features, to
> routes messages to a queue by matching a complete routing key etc.

* [MongoDB](https://www.mongodb.com/es)

> MongoDB was selected as the database by the simplicity of the data to persist, the speed and flexibility.
> It allows a control concurrency mechanism to serve concurrent client requests to multiple database servers.
> This way, it also reduces the amount of load on individual servers, ensures consistency in viewing data at any time,
> and aids in building scalable apps.

<p align="right">(<a href="#top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.

* [Java 17](https://openjdk.java.net/projects/jdk/17/)
* [Maven](https://maven.apache.org/install.html)
* [Docker](https://docs.docker.com/compose/install/)

### Installation

1. Define the environment properties in the docker-compose needed to realize the connection with the mail account.
   ```
   ENV_MAIL_HOST: smtp.gmail.com (or other smtp client)
   ENV_MAIL_PORT: 587 (relative smtp port)
   ENV_MAIL_USER: Mail account
   ENV_MAIL_PASSWORD: Mail password
   ENV_MAIL_STARTTLS_ENABLE: Start TLS - 'true/false'
   ENV_MAIL_SSL_ENABLE: SSL Enable - 'true/false'
   ```
2. Build the services defined in the docker compose.
   ```bash
   docker-compose build
   ```
3. Create the container of MongoDB and RabbitMQ to avoid connection issues between services.
   ```bash
   docker-compose up -d mongodb rabbitmq
   ```
4. Once the MongoDB and RabbitMQ is up, then we can do the same with the rest of the services.
   ```bash
   docker-compose up -d
   ```

<p align="right">(<a href="#top">back to top</a>)</p>

### Run local

1. Define the environment properties needed to realize the connection with the mail account. This can be done in the
   [application-local.properties of the mail service](https://github.com/Baaarbz/subscription-sys/blob/main/subscription-sys-mail/src/main/resources/application-local.properties)
   adding the next lines:
   ```
   env.mail.host= smtp.gmail.com (or other smtp client)
   env.mail.port= 587 (relative smtp port)
   env.mail.user= Mail account
   env.mail.password= Mail password
   env.mail.starttls.enable= Start TLS - 'true/false'
   env.mail.ssl.enable= SSL Enable - 'true/false'
   ```
   Or adding the environment to the IDE configuration or adding it into the system environment variables:
   ```
   ENV_MAIL_HOST: smtp.gmail.com (or other smtp client)
   ENV_MAIL_PORT: 587 (relative smtp port)
   ENV_MAIL_USER: Mail account
   ENV_MAIL_PASSWORD: Mail password
   ENV_MAIL_STARTTLS_ENABLE: Start TLS - 'true/false'
   ENV_MAIL_SSL_ENABLE: SSL Enable - 'true/false'
   ```
    
2. Create your configurations to run it, using the local profile!
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=local
   ```
3. Enjoy!

<p align="right">(<a href="#top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->

## Usage

[Postman Collection](https://github.com/Baaarbz/subscription-sys/tree/main/.postman) - In this folder you can find the 
Postman collection and its [environment](https://github.com/Baaarbz/subscription-sys/tree/main/.postman/environment) to test the application.

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- ROADMAP -->

## Roadmap

- [x] Add base configuration CI/CD pipelines
- [x] Add Sonar configuration to pipelines
- [ ] Configure pipeline to analyze all modules
- [ ] Add deploy configuration CI/CD pipelines
- [ ] Add Kubernetes integration
- [ ] Improve Dockerfiles
- [ ] Add mail templates with resources
- [ ] Simple way to add new mail templates to MongoDB
- [ ] Multi-language mail support
- [ ] Add Changelog

See the [open issues](https://github.com/Baaarbz/subscription-sys/issues) for a full list of proposed features (and
known issues).

<p align="right">(<a href="#top">back to top</a>)</p>

## Show your support

Give a ⭐️ if this project helped you!

## 📝 License

Copyright © 2022 [Eduardo Barbosa Tarrío](https://github.com/Baaarbz). <br/>
This project is [MIT](https://github.com/Baaarbz/subscription-sys/blob/main/LICENSE) licensed. <br/>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[stars-shield]: https://img.shields.io/github/stars/Baaarbz/subscription-sys.svg?style=for-the-badge

[stars-url]: https://github.com/Baaarbz/subscription-sys/stargazers

[issues-shield]: https://img.shields.io/github/issues/Baaarbz/subscription-sys.svg?style=for-the-badge

[issues-url]: https://github.com/Baaarbz/subscription-sys/issues

[license-shield]: https://img.shields.io/github/license/Baaarbz/subscription-sys.svg?style=for-the-badge

[license-url]: https://github.com/Baaarbz/subscription-sys/blob/main/LICENSE.txt

[linkedin-shield]: https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white

[linkedin-url]: https://linkedin.com/in/eduardobarbosatarrio
