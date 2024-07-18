# TDD_spring
rest-spring-project
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── src
│   ├── main
│   │   ├── java
│   │   │   └── library_rest_spring_boot.library
│   │   │       └── config
│   │   │       └── domain
│   │   │                └── entity
│   │   │                         └── Author.java
│   │   │                         └── Books.java
│   │   │                         └── Loans.java
│   │   │       └── repositories
│   │   │                └── AuthorRepository
│   │   │                └── BookRepository
│   │   │                └── LoansRepository
│   │   │       └── resources
│   │   │                └── author
│   │   │                        └── AuthorResource
│   │   │                └── books
│   │   │                        └── BookResource
│   │   │                └── loans
│   │   │                        └── LoanResource
│   │   │       └── service
│   │   │                └── AuthorService
│   │   │                └── BookService
│   │   │                └── LoanService
│   │   │       └── LibraryApplication
│   │   └── resources
│   │       ├── db.migration
│   │       │       └── V1__create_tables.sql
│   │       └── static
│   │       └── templates
│   │       └── application.properties
│   └── test
│       └── java
│             └── library_rest_spring_boot.library
│                    └── LibraryApplicationTests
│                           └── author
│                                └── AuthorResourceTest
│                                └── AuthorServiceTest
│                           └── book
│                                └── BookResourceTest
│                                └── BookServiceTest
│                           └── loans
│                                └── LoanResourceTest
│                                └── LoanServiceTest
│                    └── resources
├── README.md
├── docker-compose.yml
├── local_environ.env
├── build.gradle.kts
├──gradlew
├──gradlew.bat
└── settings.gradle.kts

## passos:
- ADD Pacotes de Validações e Exceções
- DTOs e Mappers:Para separar as entidades do domínio dos dados que são expostos pela API, considere usar DTOs (Data Transfer Objects). Crie um pacote dto e mapeie as entidades para DTOs dentro dos recursos ou use mappers dedicados.
- como testar migrações (flyway)

- Configurar o Docker para contêinerizar a aplicação e bd