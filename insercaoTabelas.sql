-- Inserindo Vacinas
INSERT INTO vacina (nome, descricao, situacao) VALUES
('CoronaVac', 'Vacina contra COVID-19 desenvolvida pela Sinovac', 'ATIVO'),
('AstraZeneca', 'Vacina contra COVID-19 desenvolvida pela Oxford/AstraZeneca', 'ATIVO'),
('Pfizer', 'Vacina contra COVID-19 desenvolvida pela Pfizer/BioNTech', 'ATIVO'),
('Moderna', 'Vacina contra COVID-19 desenvolvida pela Moderna', 'ATIVO'),
('Janssen', 'Vacina contra COVID-19 desenvolvida pela Johnson & Johnson', 'ATIVO'),
('BCG', 'Vacina contra a tuberculose', 'ATIVO'),
('Hepatite B', 'Vacina contra a hepatite B', 'ATIVO'),
('Poliomielite', 'Vacina contra a poliomielite', 'ATIVO'),
('DTP', 'Vacina contra difteria, tétano e coqueluche', 'ATIVO'),
('Haemophilus influenzae tipo b', 'Vacina contra a bactéria Haemophilus influenzae tipo b', 'ATIVO'),
('Pneumocócica', 'Vacina contra doenças pneumocócicas', 'ATIVO'),
('Meningocócica C', 'Vacina contra a meningite causada pelo meningococo C', 'ATIVO'),
('Febre Amarela', 'Vacina contra a febre amarela', 'ATIVO'),
('Tríplice Viral', 'Vacina contra sarampo, caxumba e rubéola', 'ATIVO'),
('HPV', 'Vacina contra o papilomavírus humano', 'ATIVO');

-- Inserindo Pessoas
INSERT INTO pessoa (nome, cpf, dataNascimento, situacao) VALUES
('João', '12345678901', '2000-01-01', 'ATIVO'),
('Maria', '12345678902', '2000-01-02', 'ATIVO'),
('José', '12345678903', '2000-01-03', 'ATIVO'),
('Ana', '12345678904', '2000-01-04', 'ATIVO'),
('Pedro', '12345678905', '2000-01-05', 'ATIVO'),
('Paula', '12345678906', '2000-01-06', 'ATIVO'),
('Carlos', '12345678907', '2000-01-07', 'ATIVO'),
('Camila', '12345678908', '2000-01-08', 'ATIVO'),
('Fernando', '12345678909', '2000-01-09', 'ATIVO'),
('Fernanda', '12345678910', '2000-01-10', 'ATIVO'),
('Rafael', '12345678911', '2000-01-11', 'ATIVO'),
('Rafaela', '12345678912', '2000-01-12', 'ATIVO'),
('Ricardo', '12345678913', '2000-01-13', 'ATIVO'),
('Ricarda', '12345678914', '2000-01-14', 'ATIVO'),
('Roberto', '12345678915', '2000-01-15', 'ATIVO'),
('Roberta', '12345678916', '2000-01-16', 'ATIVO'),
('Rodrigo', '12345678917', '2000-01-17', 'ATIVO'),
('Rodriga', '12345678918', '2000-01-18', 'ATIVO'),
('Ronaldo', '12345678919', '2000-01-19', 'ATIVO'),
('Ronalda', '12345678920', '2000-01-20', 'ATIVO'),
('Romeu', '12345678921', '2000-01-21', 'ATIVO');


-- Limpar tabelas
DELETE FROM aplicacao;
DELETE FROM vacina;
DELETE FROM pessoa;