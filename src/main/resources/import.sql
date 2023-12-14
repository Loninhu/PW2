INSERT INTO paciente (id, nome, telefone) VALUES (1,'Rogério Rocha', '12345678');
INSERT INTO paciente (id, nome, telefone) VALUES (2,'Pedro Pedreira', '09876543');

INSERT INTO medico (id, nome, crm) VALUES (3,'Luis Machado', '654321');
INSERT INTO medico (id, nome, crm) VALUES (4,'Paulo Wilson', '273455');

INSERT INTO consulta (data, valor, observacao, paciente_id, medico_id) VALUES (CURRENT_TIME, 1515.5,'Pneumonia',1,3);
INSERT INTO consulta (data, valor, observacao, paciente_id, medico_id) VALUES (CURRENT_TIME, 2256,'Bronquite',2,3);
INSERT INTO consulta (data, valor, observacao, paciente_id, medico_id) VALUES (CURRENT_TIME, 2256,'Bronquite',2,4);

INSERT INTO role (id, nome) VALUES (1,'ADMIN');
INSERT INTO role (id, nome) VALUES (2,'USER');
INSERT INTO role (id, nome) VALUES (3,'RECEPCIONISTA');
INSERT INTO role (id, nome) VALUES (4,'MEDICO');

INSERT INTO usuario (id, login, password) VALUES (1,'admin', '$2a$10$dDUUPemOuRd1uEAKcUBvEO6mIOsvfmBYRPhwSuZuMx6AmiRkgJ9/2');
INSERT INTO usuario (id, login, password) VALUES (2,'user', '$2a$10$mK/avJfOExUI4IHw/UIE1erjjyzWNYNOZs3JQf6dQHFHyE8/uXPRu');
INSERT INTO usuario (id, login, password) VALUES (3,'recepcionista', '$2a$10$mK/avJfOExUI4IHw/UIE1erjjyzWNYNOZs3JQf6dQHFHyE8/uXPRu');
INSERT INTO usuario (id, login, password) VALUES (4,'medico', '$2a$10$mK/avJfOExUI4IHw/UIE1erjjyzWNYNOZs3JQf6dQHFHyE8/uXPRu');

INSERT INTO usuario_roles (roles_id, usuarios_id) VALUES (1,1);
INSERT INTO usuario_roles (roles_id, usuarios_id) VALUES (2,2);
INSERT INTO usuario_roles (roles_id, usuarios_id) VALUES (3,3);
INSERT INTO usuario_roles (roles_id, usuarios_id) VALUES (4,4);

