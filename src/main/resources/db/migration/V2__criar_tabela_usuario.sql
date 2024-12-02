CREATE TABLE IF NOT EXISTS public.usuario
(
    codigo BIGINT NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    nome character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default",
    cpf character varying(999) COLLATE pg_catalog."default",
    telefone character varying(999) COLLATE pg_catalog."default",
    senha character varying(999) COLLATE pg_catalog."default",
    permissao character varying(999) COLLATE pg_catalog."default"
    );
