-- Inserir Professores
INSERT INTO professor (id, nome, email) VALUES 
(1, 'Mauricio Krziminski', 'Mauricio.Krziminski@edu.pucrs.br'),
(2, 'Maria Maia', 'maria.maia@edu.pucrs.br'),
(3, 'Lucas Ramon', 'lucas.ramon@edu.pucrs.br');
-- Adicione mais professores conforme necessário...

-- Inserir Disciplinas
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES 
('9890604', 'Projeto e Arquitetura de Software', 60),
('9890605', 'Engenharia de Software', 45);
-- Adicione mais disciplinas conforme necessário...

-- Inserir Semestres
INSERT INTO semestre (id, data_inicio, data_fim, ativo) VALUES 
(1, '2024-08-01', '2024-12-15', TRUE),
(2, '2025-01-10', '2025-06-30', FALSE);
-- Adicione mais semestres conforme necessário...

-- Inserir Turmas
INSERT INTO turma (codigo, disciplina_codigo, professor_id, horario, dia_semana) VALUES 
(33, '9890604', 1, 'A', 'SEGUNDA'),
(34, '9890604', 2, 'B', 'TERCA'),
(35, '9890605', 3, 'C', 'QUARTA');
-- Adicione mais turmas conforme necessário...

-- Inserir Aulas
INSERT INTO aula (id, data, horario, dia_semana, turma_codigo) VALUES 
(1, '2024-10-03', 'A', 'SEGUNDA', 33),
(2, '2024-10-04', 'B', 'TERCA', 34),
(3, '2024-10-05', 'C', 'QUARTA', 35);
-- Adicione mais aulas conforme necessário...

-- Inserir Recursos
INSERT INTO recurso (codigo, descricao) VALUES 
(1, 'Sala 310'),
(2, 'Laboratório de Informática'),
(3, 'Sala de Reuniões');
-- Adicione mais recursos conforme necessário...

-- Inserir Alocações (Reservas)
INSERT INTO alocacao (id, data, horario, aula_id, recurso_codigo) VALUES 
(1, '2024-10-03', 'A', 1, 1),
(2, '2024-10-04', 'B', 2, 2),
(3, '2024-10-05', 'C', 3, 3);
-- Adicione mais alocações conforme necessário...
