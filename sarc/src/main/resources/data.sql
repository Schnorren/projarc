-- Inserir Professores
INSERT INTO professor (id, nome, email) VALUES (1, 'Mauricio Krziminski', 'Mauricio.Krziminski@edu.pucrs.br');
INSERT INTO professor (id, nome, email) VALUES (2, 'Maria Maia', 'maria.maia@edu.pucrs.br');
INSERT INTO professor (id, nome, email) VALUES (3, 'Lucas Ramon', 'lucas.ramon@edu.pucrs.br');

-- Adicione mais professores...

-- Inserir Disciplinas
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('9890604', 'Projeto e Arquitetura de Software', 60);
-- Adicione mais disciplinas...

-- Inserir Turmas
INSERT INTO turma (codigo, disciplina_codigo, professor_id, horario) VALUES (33, '9890604', 1, 'Segunda 08:00-10:00');
-- Adicione mais turmas...

-- Inserir Aulas
INSERT INTO aula (id, data, horario, turma_codigo) VALUES (1, '2024-10-03', '08:00-10:00', 33);
-- Adicione mais aulas...

-- Inserir Recursos
INSERT INTO recurso (codigo, descricao) VALUES (1, 'Sala 310');
-- Adicione mais recursos...

-- Inserir Alocações (Reservas)
INSERT INTO alocacao (id, data, horario, aula_id, recurso_codigo) VALUES (1, '2024-10-03', '08:00-10:00', 1, 1);
-- Adicione mais alocações...
