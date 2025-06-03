alter table funcionarios add column ativo tinyint;
update funcionarios set ativo = 1 where ativo is null;