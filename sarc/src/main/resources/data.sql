-- Inserção de Professores
INSERT INTO professor (id, nome, email) VALUES 
(1, 'DANIELA RODRIGUES', 'daniela.rodrigues@universidade.com'),
(2, 'ISABEL CRISTINA MACHADO DE LARA', 'isabel.lara@universidade.com'),
(3, 'KARINA BENATO', 'karina.benato@universidade.com'),
(4, 'PEDRO SICA CARNEIRO', 'pedro.carneiro@universidade.com'),
(5, 'DAVID MARTIN JOHNSTON', 'david.johnston@universidade.com'),
(6, 'LUCAS BONACINA ROLDAN', 'lucas.roldan@universidade.com'),
(7, 'MARIO OSCAR STEFFEN', 'mario.steffen@universidade.com'),
(8, 'NAIRA MARIA LOBRAICO LIBERMANN', 'naira.libermann@universidade.com'),
(9, 'ADRIANA PAULA ZAMIN SCHERER', 'adriana.scherer@universidade.com'),
(10, 'ANA PAULA TERRA BACELO', 'ana.bacelo@universidade.com'),
(11, 'ANDREA APARECIDA KONZEN', 'andrea.konzen@universidade.com'),
(12, 'EDUARDA RODRIGUES MONTEIRO', 'eduarda.monteiro@universidade.com'),
(13, 'LUCIA MARIA MARTINS GIRAFFA', 'lucia.giraffa@universidade.com'),
(14, 'ALEXANDRE AGUSTINI', 'alexandre.agustini@universidade.com');



-- Inserção de Disciplinas
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES
('95300-04', 'CÁLCULO I', 60),
('254CF-02', 'FORMAÇÃO DO EMPREENDEDOR', 30),
('4611C-06', 'FUNDAMENTOS DE PROGRAMAÇÃO', 90),
('98705-02', 'INTRODUÇÃO À COMPUTAÇÃO', 30),
('98900-02', 'INTRODUÇÃO À ENGENHARIA DE SOFTWARE', 30),
('95303-04', 'MATEMÁTICA DISCRETA', 60);


-- Inserção do Semestre Atual
INSERT INTO semestre (id, data_inicio, data_fim, ativo) VALUES
(1, '2024-08-01', '2024-12-15', TRUE);


-- Inserção de Turmas
INSERT INTO turma (codigo, disciplina_codigo, professor_id, horario, dia_semana) VALUES
(101, '95300-04', 1, 'A', 'SEGUNDA'),          -- Turma de CÁLCULO I com DANIELA RODRIGUES
(102, '95300-04', 2, 'B', 'TERCA'),             -- Turma de CÁLCULO I com ISABEL LARA
(103, '254CF-02', 5, 'C', 'QUARTA'),            -- Turma de FORMAÇÃO DO EMPREENDEDOR com DAVID JOHNSTON
(104, '4611C-06', 9, 'D', 'QUINTA'),            -- Turma de FUNDAMENTOS DE PROGRAMAÇÃO com ADRIANA SCHERER
(105, '98705-02', 14, 'E', 'SEXTA'),            -- Turma de INTRODUÇÃO À COMPUTAÇÃO com ALEXANDRE AGUSTINI
(106, '98900-02', 10, 'F', 'SEGUNDA'),          -- Turma de INTRODUÇÃO À ENGENHARIA DE SOFTWARE com ANA PAULA
(107, '95303-04', 3, 'G', 'QUINTA');            -- Turma de MATEMÁTICA DISCRETA com KARINA BENATO


-- Inserção de Aulas
INSERT INTO aula (id, data, horario, dia_semana, turma_codigo) VALUES
(1, '2024-09-01', 'A', 'SEGUNDA', 101),         -- Aula de Cálculo I
(2, '2024-09-02', 'B', 'TERCA', 102),
(3, '2024-09-03', 'C', 'QUARTA', 103),          -- Aula de Formação do Empreendedor
(4, '2024-09-04', 'D', 'QUINTA', 104),          -- Aula de Fundamentos de Programação
(5, '2024-09-05', 'E', 'SEXTA', 105),           -- Aula de Introdução à Computação
(6, '2024-09-06', 'F', 'SEGUNDA', 106),         -- Aula de Introdução à Eng. de Software
(7, '2024-09-07', 'G', 'QUINTA', 107);          -- Aula de Matemática Discreta


-- Inserção de Recursos
INSERT INTO recurso (codigo, descricao) VALUES
(1, 'Sala 310'),
(2, 'Laboratório de Informática'),
(3, 'Sala de Reuniões'),
(4, 'Auditório A'),
(5, 'Laboratório de Eletrônica');


-- Inserção de Alocações de Recursos para Aulas
INSERT INTO alocacao (id, data, horario, aula_id, recurso_codigo) VALUES
(1, '2024-09-01', 'A', 1, 1),                    -- Alocação para Cálculo I
(2, '2024-09-02', 'B', 2, 2),                    -- Alocação para Cálculo I (Turma 2)
(3, '2024-09-03', 'C', 3, 3),                    -- Alocação para Formação do Empreendedor
(4, '2024-09-04', 'D', 4, 2),                    -- Alocação para Fundamentos de Programação
(5, '2024-09-05', 'E', 5, 1),                    -- Alocação para Introdução à Computação
(6, '2024-09-06', 'F', 6, 4),                    -- Alocação para Introdução à Eng. de Software
(7, '2024-09-07', 'G', 7, 5);                    -- Alocação para Matemática Discreta




