DROP DATABASE IF exists salao_db;

DROP TABLE IF EXISTS usuario_admin;
DROP TABLE IF EXISTS servicos_agendados;
DROP TABLE IF EXISTS servicos_disponiveis;
DROP TABLE IF EXISTS fluxo_caixa;
DROP TABLE IF EXISTS despesa;
DROP TABLE IF EXISTS comparecimento;
DROP TABLE IF EXISTS agendamento;
DROP TABLE IF EXISTS cliente;

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
senha varchar(16)
);

CREATE TABLE despesa (
id_despesa int auto_increment,
produto_descricao varchar(100) not null,
preco_unitario double not null,
quantidade int not null,
data_saida datetime,
primary key(id_despesa, data_saida)
);

create table agendamento (
id_agendamento int auto_increment,
email_cliente varchar(50) not null,
data_agendamento datetime not null,
PRIMARY KEY(id_agendamento, email_cliente, data_agendamento),
FOREIGN KEY(email_cliente) REFERENCES cliente(email)
);

create table comparecimento (
id_comparecimento int auto_increment,
id_agendamento int,
email_cliente varchar(50),
data_agendamento datetime,
primary key(id_comparecimento, id_agendamento, email_cliente, data_agendamento),
foreign key(id_agendamento, email_cliente, data_agendamento) references agendamento(id_agendamento, email_cliente, data_agendamento)
);

create table servicos_agendados(
id_servico_agendado int auto_increment primary key,
id_servico int,
id_agendamento int,
email_cliente varchar(50),
foreign key(id_servico) references servicos_disponiveis (id_servico),
foreign key(id_agendamento, email_cliente) references agendamento (id_agendamento, email_cliente) 
);

create table fluxo_caixa (
id_fluxo int auto_increment,
id_comparecimento int,
id_agendamento int,
email_cliente varchar(50),
data_agendamento datetime,
data_saida datetime,
id_despesa int,
valor_movimentado_saida double,
valor_movimentado_entrada double,
primary key(id_fluxo),
foreign key(id_despesa, data_saida) references despesa(id_despesa, data_saida),
foreign key(id_comparecimento, id_agendamento, email_cliente, data_agendamento) references comparecimento(id_comparecimento, id_agendamento, email_cliente, data_agendamento)
);

