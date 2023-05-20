select * from usuario_admin;

describe cliente;

describe servicos_disponiveis;

insert into servicos_disponiveis (descricao_servico, preco) values ("pintar unhas mão", 10.00);
insert into servicos_disponiveis (descricao_servico, preco) values ("pintar cabelo", 50.00);
insert into servicos_disponiveis (descricao_servico, preco) values ("pintar unhas pés", 15.00);
insert into servicos_disponiveis (descricao_servico, preco) values ("cortes", 60.00);

describe despesa;

insert into despesa (produto_descricao, preco_unitario, quantidade, data_saida) values ("tinta cabelo", 50.00, 2, "2023-05-19");
insert into despesa (produto_descricao, preco_unitario, quantidade, data_saida) values ("esmalte", 10.00, 10, "2023-05-18");
insert into despesa (produto_descricao, preco_unitario, quantidade, data_saida) values ("esmalte", 10.00, 50, "2023-05-19");