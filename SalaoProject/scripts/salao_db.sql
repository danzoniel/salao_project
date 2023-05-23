DROP DATABASE IF exists salao_db;

create database salao_db;

use salao_db;

CREATE TABLE cliente (
email VARCHAR(50) PRIMARY KEY,
nome VARCHAR(100) NOT NULL
);

CREATE TABLE servicos_disponiveis (
id_servico INT AUTO_INCREMENT PRIMARY KEY,
descricao_servico VARCHAR(100) NOT NULL,
preco DOUBLE NOT NULL
);

CREATE TABLE usuario_admin (
email varchar(50) PRIMARY KEY,
senha varchar(16) not null
);

CREATE TABLE despesa (
id_despesa int auto_increment,
produto_descricao varchar(100) not null,
preco_unitario double not null,
quantidade int not null,
data_saida date,
primary key(id_despesa)
);

create table agendamento (
email_cliente varchar(50) not null,
data_agendamento date not null,
hora_agendamento TIME not null,
PRIMARY KEY(email_cliente, data_agendamento),
FOREIGN KEY(email_cliente) REFERENCES cliente(email)
);

create table comparecimento (
id_comparecimento int auto_increment,
email_cliente varchar(50),
data_agendamento date,
primary key(id_comparecimento, email_cliente),
foreign key(email_cliente, data_agendamento) references agendamento(email_cliente, data_agendamento)
);

create table servicos_agendados(
id_servico_agendado int auto_increment,
id_servico int not null,
email_cliente varchar(50) not null,
data_agendamento date not null,
primary key(id_servico_agendado, id_servico),
foreign key(id_servico) references servicos_disponiveis (id_servico),
foreign key(email_cliente, data_agendamento) references agendamento (email_cliente, data_agendamento) 
);

CREATE TABLE fluxo_caixa (
id_fluxo INT AUTO_INCREMENT,
id_despesa INT,
id_comparecimento int,
email varchar(50),
valor_movimentado_saida DOUBLE,
valor_movimentado_entrada DOUBLE,
PRIMARY KEY (id_fluxo),
FOREIGN KEY (id_comparecimento, email) REFERENCES comparecimento (id_comparecimento, email_cliente),
FOREIGN KEY (id_despesa) REFERENCES despesa (id_despesa)
);

insert into usuario_admin(email, senha) values ("salao@gmail.com", "admin");

insert into cliente(email, nome) values ("fulana1@gmail.com","fulana1");
insert into cliente(email, nome) values ("fulana2@gmail.com","fulana2");
insert into cliente(email, nome) values ("fulana3@gmail.com","fulana3");
insert into cliente(email, nome) values ("fulana4@gmail.com","fulana4");

insert into despesa(id_despesa, produto_descricao, preco_unitario, quantidade, data_saida) values (1, "tinta cabelo", 50.00, 2, STR_TO_DATE('20/05/2023', '%d/%m/%Y'));
insert into despesa(id_despesa, produto_descricao, preco_unitario, quantidade, data_saida) values (2, "esmaltes", 20.00, 10, STR_TO_DATE('19/05/2023', '%d/%m/%Y'));
insert into despesa(id_despesa, produto_descricao, preco_unitario, quantidade, data_saida) values (3, "esmaltes", 20.00, 10, STR_TO_DATE('20/05/2023', '%d/%m/%Y'));
insert into despesa(id_despesa, produto_descricao, preco_unitario, quantidade, data_saida) values (4, "secador cabelo", 600.00, 1, STR_TO_DATE('18/05/2023', '%d/%m/%Y'));

insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana1@gmail.com", "2023-05-20", "12:30:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana1@gmail.com", "2023-05-21", "12:50:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana2@gmail.com", "2023-05-23", "13:00:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana3@gmail.com", "2023-05-25", "15:00:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana3@gmail.com", "2023-01-25", "15:00:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana3@gmail.com", "2023-02-21", "15:00:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana3@gmail.com", "2023-02-25", "15:00:00");
insert into agendamento(email_cliente, data_agendamento, hora_agendamento) values ("fulana3@gmail.com", "2023-03-25", "15:00:00");

insert into comparecimento(id_comparecimento, email_cliente, data_agendamento) values (1, "fulana1@gmail.com", "2023-05-20");
insert into comparecimento(id_comparecimento, email_cliente, data_agendamento) values (2, "fulana1@gmail.com", "2023-05-21");
insert into comparecimento(id_comparecimento, email_cliente, data_agendamento) values (3, "fulana3@gmail.com", "2023-01-25");
insert into comparecimento(id_comparecimento, email_cliente, data_agendamento) values (4, "fulana3@gmail.com", "2023-02-21");
insert into comparecimento(id_comparecimento, email_cliente, data_agendamento) values (5, "fulana3@gmail.com", "2023-02-25");

insert into servicos_disponiveis(id_servico, descricao_servico, preco) values (1, "pintar unhas mãos", 10.00);
insert into servicos_disponiveis(id_servico, descricao_servico, preco) values (2, "pintar unhas pés", 15.00);
insert into servicos_disponiveis(id_servico, descricao_servico, preco) values (3, "pintar cabelo", 80.00);
insert into servicos_disponiveis(id_servico, descricao_servico, preco) values (4, "cortar cabelo", 60.00);

insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (1, 1, "fulana1@gmail.com", "2023-05-20");
insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (1, 2, "fulana1@gmail.com", "2023-05-20");
insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (1, 3, "fulana1@gmail.com", "2023-05-20");
insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (2, 4, "fulana1@gmail.com", "2023-05-21");
insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (3, 3, "fulana2@gmail.com", "2023-05-23");
insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (4, 3, "fulana3@gmail.com", "2023-05-25");
insert into servicos_agendados(id_servico_agendado, id_servico, email_cliente, data_agendamento) values (4, 2, "fulana3@gmail.com", "2023-01-25");

INSERT INTO fluxo_caixa (id_fluxo, id_despesa, id_comparecimento, email, valor_movimentado_saida, valor_movimentado_entrada) VALUES (1, 1, NULL, NULL, 50.00, 0);
INSERT INTO fluxo_caixa (id_fluxo, id_despesa, id_comparecimento, email, valor_movimentado_saida, valor_movimentado_entrada) VALUES (2, NULL, 1, "fulana1@gmail.com", 0, 105.00);
