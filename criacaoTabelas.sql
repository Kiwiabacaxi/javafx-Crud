-- Active: 1701190942105@@localhost@5432@crud
-- Pessoa
CREATE TABLE public.pessoa
(
    codigo serial NOT NULL,
    nome text,
    cpf text,
    dataNascimento date,
    situacao text,
    PRIMARY KEY (codigo)
);
-- Vacina
CREATE TABLE public.vacina
(
    codigo serial NOT NULL,
    nome text,
    descricao text,
    situacao text,
    PRIMARY KEY (codigo)
);

-- Aplicacao
CREATE TABLE public.aplicacao
(
    codigo serial NOT NULL,
    data date,
    codigo_pessoa int,
    codigo_vacina int,
    situacao text,
    PRIMARY KEY (codigo),
    FOREIGN KEY (codigo_pessoa) REFERENCES public.pessoa(codigo),
    FOREIGN KEY (codigo_vacina) REFERENCES public.vacina(codigo)
);