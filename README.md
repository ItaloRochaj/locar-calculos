# **Documentação do Projeto - Sistema de Locadora de Automoveis**

## 🛠️ **Tecnologias Utilizadas**

Este projeto foi desenvolvido utilizando as seguintes tecnologias:

### **Backend**
- **[Java](https://www.oracle.com/java/)**
  - Linguagem de programação principal.
- **[Spring Boot](https://spring.io/projects/spring-boot)**
  - Framework para simplificar a configuração e o desenvolvimento de aplicações Java.
  - Principais módulos utilizados:
    - **Spring Web**: Para criação de APIs REST.
    - **Spring Data JPA**: Para interação com o banco de dados.
    - **Hibernate**: Implementação do JPA para o mapeamento objeto-relacional (ORM).
    - **Spring Boot Starter Test**: Para testes unitários e de integração.

### **Banco de Dados**
- **[MySQL](https://www.mysql.com/)**
  - Banco de dados relacional utilizado para persistência de dados.

### **Ferramentas de Build**
- **[Maven](https://maven.apache.org/):**
  - Gerenciador de dependências e automação de build.

### **Teste de Requisições API**
- **[Insomnia](https://insomnia.rest/)**
  - Utilizado para testar os endpoints REST de forma prática e eficiente. Foram realizadas requisições HTTP para verificar as funcionalidades como salvar, buscar, atualizar e deletar registros no sistema.

### **IDE**
- **IntelliJ IDEA** ou **Eclipse**
  - Para desenvolvimento do código.

---

## 📂 **Estrutura do Projeto**

O projeto foi estruturado seguindo a arquitetura **MVC (Model-View-Controller)**:

```
src/
├── main/
│   ├── java/
│   │   └── br/com/calculos/calculos/
│   │       ├── app/
│   │       │   ├── controller/      # Controladores REST
│   │       │   ├── entity/          # Classes de entidades (banco de dados)
│   │       │   ├── repository/      # Interfaces para operações com o BD
│   │       │   ├── service/         # Camada de lógica de negócios
│   │       │   └── exceptions/      # Tratamento de exceções
│   │       ├── CalculosApplication.java  # Classe principal da aplicação
│   ├── resources/
│   │   ├── application.properties   # Configurações do banco de dados e JPA
│   │   ├── application-dev.properties
│   │   ├── application-prod.properties
├── test/
│   ├── java/
│   │   └── br/com/calculos/calculos/
│   │       ├── CarroServiceTests.java
│   │       └── CalculosApplicationTests.java
```

---

## 📋 **Passo a Passo da Implementação**

### 1. **Configuração do Ambiente**
1. **Banco de Dados MySQL**
   - Criação de um banco chamado `locar_calculos`.
   - Configuração no arquivo `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/locar_calculos
     spring.datasource.username=root
     spring.datasource.password=Japa@3024
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
     ```

2. **Configuração do Projeto Maven**
   - Foi criado um projeto Maven e adicionadas dependências no `pom.xml`:
     ```xml
     <dependencies>
         <!-- Spring Boot Starter Web -->
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-web</artifactId>
         </dependency>
         <!-- Spring Boot Starter JPA -->
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-data-jpa</artifactId>
         </dependency>
         <!-- MySQL Driver -->
         <dependency>
             <groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
         </dependency>
         <!-- Starter de Testes -->
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-starter-test</artifactId>
             <scope>test</scope>
         </dependency>
     </dependencies>
     ```

### 2. **Criação do Modelo de Dados (Entity)**
   - Foi criada a entidade `Carro` na pasta `entity`:
     ```java
     @Entity
     public class Carro {
         @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
         private Long id;

         private String marca;
         private String modelo;
         private int ano;

         // Getters e Setters
     }
     ```

### 3. **Criação do Repositório**
   - Interface `CarroRepository` criada para operações CRUD automáticas com o banco de dados:
     ```java
     public interface CarroRepository extends JpaRepository<Carro, Long> {
     }
     ```

### 4. **Implementação do Serviço**
   - Camada de lógica implementada na classe `CarroService`:
     ```java
     @Service
     public class CarroService {
         @Autowired
         private CarroRepository carroRepository;

         public String save(Carro carro) {
             carroRepository.save(carro);
             return "Carro salvo com sucesso!";
         }

         // Outros métodos: update, delete, findAll, findById
     }
     ```

### 5. **Criação do Controlador**
   - Controlador `CarroController` criado para expor as APIs REST:
     ```java
     @RestController
     @RequestMapping("/api/carro")
     public class CarroController {
         @Autowired
         private CarroService carroService;

         @PostMapping("/save")
         public ResponseEntity<String> save(@RequestBody Carro carro) {
             return new ResponseEntity<>(carroService.save(carro), HttpStatus.CREATED);
         }

         @GetMapping("/findAll")
         public ResponseEntity<List<Carro>> findAll() {
             return new ResponseEntity<>(carroService.findAll(), HttpStatus.OK);
         }

         // Outros endpoints: update, delete, findById
     }
     ```

### 6. **Testes de Requisições com Insomnia**
   - Para validar os endpoints da API, foi utilizado o **Insomnia**:
     1. Instale o **Insomnia** [aqui](https://insomnia.rest/download).
     2. Configure as requisições:
        - **POST**: `http://localhost:8080/api/carro/save`
          - **Body (JSON)**:
            ```json
            {
              "marca": "Toyota",
              "modelo": "Corolla",
              "ano": 2023
            }
            ```
        - **GET**: `http://localhost:8080/api/carro/findAll`
        - **GET**: `http://localhost:8080/api/carro/findById/1`
        - **PUT**: `http://localhost:8080/api/carro/update/1`
          - **Body (JSON)**:
            ```json
            {
              "marca": "Honda",
              "modelo": "Civic",
              "ano": 2022
            }
            ```
        - **DELETE**: `http://localhost:8080/api/carro/delete/1`

---

## 🚀 **Como Rodar o Projeto**

### Pré-requisitos
- **Java 17** ou superior.
- **MySQL** instalado e rodando.
- **Maven** configurado no ambiente.

### Passos
1. Clone o repositório:
   ```bash
   git clone <url-do-repositorio>
   cd sistema-calculos
   ```

2. Configure o banco de dados:
   - Certifique-se de que o banco `locar_calculos` foi criado no MySQL.
   - Ajuste o arquivo `application.properties` com as credenciais do banco.

3. Compile e rode o projeto:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Teste os endpoints usando o Insomnia:
   - **Exemplo:** `http://localhost:8080/api/carro/findAll`

---

