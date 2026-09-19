CREATE TABLE aluno (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    sexo VARCHAR(1),
    ano_nascimento INTEGER,
    matricula VARCHAR(20) UNIQUE,
    turma VARCHAR(20)
);

CREATE TABLE categoria (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(255)
);

CREATE TABLE produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(500),
    preco NUMERIC(10,2) NOT NULL,
    estoque INTEGER NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    categoria_id BIGINT NOT NULL,
    CONSTRAINT fk_produto_categoria FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

CREATE TABLE venda (
    id BIGSERIAL PRIMARY KEY,
    aluno_id BIGINT NOT NULL,
    data_venda TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_venda_aluno FOREIGN KEY (aluno_id) REFERENCES aluno(id)
);

CREATE TABLE item_venda (
    id BIGSERIAL PRIMARY KEY,
    venda_id BIGINT NOT NULL,
    produto_id BIGINT NOT NULL,
    quantidade INTEGER NOT NULL,
    preco_unitario NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_item_venda_venda FOREIGN KEY (venda_id) REFERENCES venda(id),
    CONSTRAINT fk_item_venda_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);