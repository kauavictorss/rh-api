create table funcionarios (

    cpf varchar(11) not null primary key,
    nome varchar(255) not null,
    idade int not null,
    email varchar(255) not null unique,
    especialidade varchar(100) not null,
    num_conta varchar(20) not null,
    agencia varchar(20) not null,
    tipo_conta varchar(20) not null,
    salario decimal(10,2) not null,
    cep varchar(10) not null,
    logradouro varchar(255) not null,
    bairro varchar(100) not null,
    cidade varchar(100) not null,
    uf varchar(2) not null,
    numero varchar(10),
    complemento varchar(255)

);