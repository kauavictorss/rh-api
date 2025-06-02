alter table funcionarios add ativo tinyint;
update funcionarios set ativo = 1 where ativo is null;