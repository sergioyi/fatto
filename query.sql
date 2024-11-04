CREATE TABLE tarefas (
    id SERIAL PRIMARY KEY,
    nometarefa VARCHAR(255) NOT NULL,
    custo NUMERIC(10,2) DEFAULT 0.00,
    datalimite DATE,
    ordem INT NOT NULL UNIQUE
);
