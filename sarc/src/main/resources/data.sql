-- Configuração de Semestre
INSERT INTO semestre (id, data_inicio, data_fim, ativo) VALUES (1, '2024-01-15', '2024-06-30', true);

-- Inserção de Professores
INSERT INTO professor (id, nome, email) VALUES 
(10088900, 'DANIELA RODRIGUES', 'daniela.rodrigues@universidade.com'),
(10088901, 'ISABEL CRISTINA MACHADO DE LARA', 'isabel.lara@universidade.com'),
(10088902, 'KARINA BENATO', 'karina.benato@universidade.com'),
(10088903, 'PEDRO SICA CARNEIRO', 'pedro.carneiro@universidade.com'),
(10088904, 'DAVID MARTIN JOHNSTON', 'david.johnston@universidade.com'),
(10088905, 'LUCAS BONACINA ROLDAN', 'lucas.roldan@universidade.com'),
(10088906, 'MARIO OSCAR STEFFEN', 'mario.steffen@universidade.com'),
(10088907, 'NAIRA MARIA LOBRAICO LIBERMANN', 'naira.libermann@universidade.com'),
(10088908, 'ADRIANA PAULA ZAMIN SCHERER', 'adriana.scherer@universidade.com'),
(10088909, 'ANA PAULA TERRA BACELO', 'ana.bacelo@universidade.com'),
(10088911, 'ANDREA APARECIDA KONZEN', 'andrea.konzen@universidade.com'),
(10088912, 'EDUARDA RODRIGUES MONTEIRO', 'eduarda.monteiro@universidade.com'),
(10088913, 'LUCIA MARIA MARTINS GIRAFFA', 'lucia.giraffa@universidade.com'),
(10088914, 'ALEXANDRE AGUSTINI', 'alexandre.agustini@universidade.com');



-- Inserção de Disciplinas
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('96589-04', 'Programacao I', 60);
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('99890-02', 'Estrutura de Dados', 60);
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('99876-02', 'Projeto e Arquitetura de Software', 60);
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('97750-02', 'Calculo II', 60);
INSERT INTO disciplina (codigo, nome, carga_horaria) VALUES ('99210-02', 'Matematica Discreta', 60);




-- Inserção de Recursos
INSERT INTO recurso (codigo, descricao) VALUES (309, 'Laboratório 309');
INSERT INTO recurso (codigo, descricao) VALUES (310, 'Laboratório 310');
INSERT INTO recurso (codigo, descricao) VALUES (311, 'Laboratório 311');
INSERT INTO recurso (codigo, descricao) VALUES (312, 'Laboratório 312');
INSERT INTO recurso (codigo, descricao) VALUES (101, 'Kit Notebook 1');
INSERT INTO recurso (codigo, descricao) VALUES (102, 'Kit Notebook 2');
INSERT INTO recurso (codigo, descricao) VALUES (103, 'Kit Notebook 3');
INSERT INTO recurso (codigo, descricao) VALUES (104, 'Kit Notebook 4');
INSERT INTO recurso (codigo, descricao) VALUES (105, 'Kit Notebook 5');


-- Inserção de Turmas
INSERT INTO turma (codigo, dia_semana, horario, disciplina_codigo, professor_id) VALUES 
(1001, 'SEGUNDA', 'A', '96589-04', 10088900), -- Programação I, Daniela Rodrigues
(1002, 'TERCA', 'B', '99890-02', 10088901), -- Estrutura de Dados, Isabel Cristina
(1003, 'QUARTA', 'C', '99876-02', 10088902), -- Projeto e Arquitetura de Software, Karina Benato
(1004, 'QUINTA', 'D', '97750-02', 10088903), -- Cálculo II, Pedro Sica Carneiro
(1005, 'SEXTA', 'E', '99210-02', 10088904); -- Matemática Discreta, David Martin Johnston



/*
   UTILIZAR O http://localhost:8080/h2-console/ 
    URL:jdbc:h2:mem:sarcdb
    UserName: sa


    INSERIR AS ALOCACOES:

-- Programação I (SEGUNDA)
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-15', 'A', 1, 309); -- Laboratório 309
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-15', 'A', 1, 101); -- Kit Notebook 1

-- Estrutura de Dados (TERÇA)
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-16', 'B', 2, 309); -- Laboratório 310
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-16', 'B', 2, 102); -- Kit Notebook 2

-- Projeto e Arquitetura de Software (QUARTA)
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-17', 'C', 3, 311); -- Laboratório 311
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-17', 'C', 3, 103); -- Kit Notebook 3

-- Cálculo II (QUINTA)
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-18', 'D', 4, 312); -- Laboratório 312
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-18', 'D', 4, 104); -- Kit Notebook 4

-- Matemática Discreta (SEXTA)
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-19', 'E', 5, 311); -- Laboratório 311
INSERT INTO alocacao (data, horario, aula_id, recurso_codigo) VALUES ('2024-01-19', 'E', 5, 105); -- Kit Notebook 5







CONSULTA LEGAL PARA VER TODOS OS DADOS INSERIDOS

   SELECT a.id AS aula_id, 
       a.data, 
       a.horario, 
       a.dia_semana, 
       t.codigo AS turma_codigo, 
       t.horario AS turma_horario, 
       t.dia_semana AS turma_dia_semana, 
       d.nome AS disciplina_nome, 
       p.nome AS professor_nome, 
       r.descricao AS recurso_descricao
FROM aula a
JOIN turma t ON a.turma_codigo = t.codigo
JOIN disciplina d ON t.disciplina_codigo = d.codigo
JOIN professor p ON t.professor_id = p.id
LEFT JOIN alocacao al ON a.id = al.aula_id
LEFT JOIN recurso r ON al.recurso_codigo = r.codigo
ORDER BY a.data, a.horario;

*/ 




