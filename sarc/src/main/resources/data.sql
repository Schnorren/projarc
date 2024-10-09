-- Configuração de Semestre
INSERT INTO semestre (id, data_inicio, data_fim, ativo) VALUES (1, '2024-01-15', '2024-06-30', true);

-- Inserção de Professores
INSERT INTO professor (id, nome, email) VALUES (1, 'Professor A', 'professor.a@example.com');
INSERT INTO professor (id, nome, email) VALUES (2, 'Professor B', 'professor.b@example.com');

-- Inserção de Disciplinas
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('CS101', 'Programação I', 60);
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('CS102', 'Estrutura de Dados', 60);

-- Inserção de Recursos
INSERT INTO recurso (codigo, descricao) VALUES (1, 'Laboratório 101');
INSERT INTO recurso (codigo, descricao) VALUES (2, 'Sala 202');
INSERT INTO recurso (codigo, descricao) VALUES (3, 'Auditório');

-- Inserção de Turmas
INSERT INTO turma (codigo, dia_semana, horario, disciplina_codigo, professor_id) VALUES (1, 'SEGUNDA', 'A', 'CS101', 1);
INSERT INTO turma (codigo, dia_semana, horario, disciplina_codigo, professor_id) VALUES (2, 'TERCA', 'B', 'CS102', 2);
INSERT INTO turma (codigo, dia_semana, horario, disciplina_codigo, professor_id) VALUES (3, 'SEGUNDA', 'A', 'CS102', 1);

-- Inserção de Aulas
INSERT INTO aula (data, dia_semana, horario, turma_codigo) VALUES ('2024-01-15', 'SEGUNDA', 'A', 1);
INSERT INTO aula (data, dia_semana, horario, turma_codigo) VALUES ('2024-01-16', 'TERCA', 'B', 2);
INSERT INTO aula (data, dia_semana, horario, turma_codigo) VALUES ('2024-01-22', 'SEGUNDA', 'A', 3);

-- Inserção de Alocações
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-15', 'A', 1, 1);
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-16', 'B', 2, 2);
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-22', 'A', 3, 3);
