CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    nometarefa VARCHAR(255) NOT NULL,
    custo NUMERIC(25,2) DEFAULT 0.00,
    datalimite DATE,
    ordem INT UNIQUE
);
