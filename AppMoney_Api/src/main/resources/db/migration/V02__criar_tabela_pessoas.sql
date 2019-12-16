CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(10),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(10),
	cidade VARCHAR(50),
	estado VARCHAR(50),
	ativo TINYINT(1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Marcelo', 'Rua B', '22', null , 'Boa Vista', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Joao', 'Rua c', '2', null , 'Bairro', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Maria', 'Rua f', '33', null , 'B Central', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Daniela', 'Rua g', '11', null , 'B Qualquer', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Lucas', 'Rua U', '111', null , 'B Primavera', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Davi', 'Rua m', '22', null , 'B Central', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Almeida', 'Rua A', '555', null , 'B Qualquer', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Danilo', 'Rua j', '5', null , 'B Qualquer', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Luiz', 'Rua h', '111', null , 'B Qualquer', 
'45000000', 'VCA', 'Bahia', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo) values ('Daniella', 'Rua t', '888', null , 'B Qualquer', 
'45000000', 'VCA', 'Bahia', true);
