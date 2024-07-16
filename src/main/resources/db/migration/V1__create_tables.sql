
CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         birth_date DATE,
                         nationality VARCHAR(255)
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       isbn VARCHAR(13) UNIQUE NOT NULL,
                       publication_date DATE,
                       number_of_pages INT,
                       author_id INT,
                       FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE loans (
                       id SERIAL PRIMARY KEY,
                       book_id INT,
                       user_id INT,
                       loan_date DATE NOT NULL,
                       return_date DATE,
                       FOREIGN KEY (book_id) REFERENCES books(id)
);
