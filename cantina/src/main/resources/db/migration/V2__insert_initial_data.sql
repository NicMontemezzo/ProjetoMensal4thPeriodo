INSERT INTO categoria (nome, descricao) VALUES
('Lanches', 'Sanduíches e salgados'),
('Bebidas', 'Sucos, refrigerantes e água'),
('Doces', 'Chocolates, balas e sobremesas');

INSERT INTO produto (nome, descricao, preco, estoque, categoria_id) VALUES
('Coxinha', 'Salgado de frango', 6.50, 30, 1),
('Sanduíche Natural', 'Pão integral com frango desfiado', 8.00, 20, 1),
('Suco de Laranja', 'Suco natural 300ml', 5.00, 25, 2),
('Água Mineral', 'Garrafa 500ml', 3.00, 40, 2),
('Chocolate', 'Barra de chocolate ao leite', 4.50, 35, 3);

INSERT INTO aluno (nome, sexo, ano_nascimento, matricula, turma) VALUES
('João Silva', 'M', 2012, '2024001', '7A'),
('Maria Souza', 'F', 2013, '2024002', '6B'),
('Pedro Santos', 'M', 2011, '2024003', '8A');