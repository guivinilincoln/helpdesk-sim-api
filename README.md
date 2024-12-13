# Simulador de Help Desk

Bem-vindo ao Simulador de Help Desk, uma API desenvolvida em Java usando Spring Boot. Este projeto é um simulador básico de um sistema de help desk, desenvolvido para fins de aprendizado e demonstração.

## Funcionalidades

- Receber requisições "ping" e retornar "pong" como resposta, funcionando como um endpoint de saúde.
- [Adicione aqui outras funcionalidades quando implementadas]

## Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- MySQL
- Docker

## Requisitos

- Java JDK 17 ou superior
- Maven
- Docker (para execução do banco de dados MySQL)

## Configuração do Ambiente

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/simulador-helpdesk.git
   cd helpdesk-simulador-api
   ```
2. **Configurando o banco de dados:**
    - Certifique-se de que um contêiner MySQL está em execução usando o docker-compose.yml fornecido:

    ```bash
   docker-compose up -d
    ```
   - Verifique as credenciais e configurações do banco no arquivo application.properties.


3.  **Compile e execute o projeto:**
    - Se você estiver usando o IntelliJ como IDEA:
    ```bash
    No menu superir e clique no botão play verde,
    seu projeto deve ser complidado e dado start.
    ```
    - Se você estiver no terminal siga esse comando:
    ```bash
    ./mvnw spring-boot:run
    ```
4. **Acesse a API::**
    - O servidor estará executando em http://localhost:8080.
    - Teste o endpoint padrao com ping:
    ```bash
    curl http://localhost:8080/ping
    ```


## Contribuição
1. **Fork o projeto.**
2. **Crie sua branch de feature (git checkout -b feature/nova-feature).**
3. **Commit suas alterações (git commit -m 'Adiciona nova feature').**
4. **Push para a branch (git push origin feature/nova-feature).**
5. **Abra um Pull Request.**

## Contato

**Para mais informações, entre em contato pelo e-mail: gui.vini.lincoln@gmail.com**
