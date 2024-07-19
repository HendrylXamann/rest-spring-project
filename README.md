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
- Criar DTO e mapeamento pros endpoints
- Como testar migrações (flyway)

- Configurar o Docker para contêinerizar a aplicação e bd