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
│   │   │                            └── AuthorResource
│   │   │                └── books
│   │   │                            └── BookResource
│   │   │                └── loans
│   │   │                            └── LoanResource
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
│                           └── resources
│                                └── author
│                                      └── AuthorResourceTest
│                                └── books
│                                      └── BookResourceTest
│                                └── loans
│                                      └── LoanResourceTest
│                           └── LibraryApplicationTests
│                    └── resources
│                           └── application-test.properties
├── README.md
├── docker-compose.yml
├── local_environ.env
├── build.gradle.kts
├──gradlew
├──gradlew.bat
└── settings.gradle.kts

## passos:
- Fazer tests dos books e loans
- Fazer funcionar 
- Configurar o Swagger para documentar a API.
- Configurar o Docker para contêinerizar a aplicação e bd