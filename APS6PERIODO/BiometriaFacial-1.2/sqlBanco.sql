
-- drop database aps6;

-- create database aps6;
-- use aps6;

-- drop table tipo_usuario;
-- drop table usuario;
-- drop table imagens_treinamento;


create table tipo_usuario(
	id int not null auto_increment primary key,
    descricao varchar(120) not null,
    nivel_acesso int not null
);


create table usuario (
	id int not null auto_increment primary key,
    nome varchar(120) not null,
    senha varchar(100) not null,
    login varchar(100) not null,
    tipo_usuario_id int not null,
    constraint FK_TIPO_USUARIO foreign key(tipo_usuario_id) references tipo_usuario(id) 
);


create table imagens_treinamento (
	id int not null auto_increment primary key,
    caminho varchar(250) not null,
    tipo_imagem enum('raw','bitmap'),
    usuario_id int not null,
    constraint FK_USUARIO foreign key(usuario_id) references usuario(id)
);

insert into tipo_usuario(descricao,nivel_acesso) values('Usuário comum',1),('Diretor de divisão',2),('Ministro do meio Ambiente',3),('Administrador',3)
