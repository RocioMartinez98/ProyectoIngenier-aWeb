INSERT INTO NOTA (id, titulo, tipo, texto) VALUES (1, 'Nota 1', 1, 'Texto de la nota 1');
INSERT INTO NOTA (id, titulo, tipo, texto) VALUES (2, 'Nota 2', 2, 'Texto de la nota 2');
INSERT INTO NOTA (id, titulo, tipo, texto) VALUES (3, 'Nota 3', 1, 'Texto de la nota 3');
INSERT INTO NOTA (id, titulo, tipo, texto) VALUES (4, 'Nota 4', 3, 'Texto de la nota 4');
INSERT INTO NOTA (id, titulo, tipo, texto) VALUES (5, 'Nota 5', 2, 'Texto de la nota 5');

INSERT INTO USUARIO (id, clave, correo) VALUES (1, 'clave1', 'usuario1@example.com');
INSERT INTO USUARIO (id, clave, correo) VALUES (2, 'clave2', 'usuario2@example.com');
INSERT INTO USUARIO (id, clave, correo) VALUES (3, 'clave3', 'usuario3@example.com');

INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID) VALUES (1, 1);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID) VALUES (1, 2);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID) VALUES (2, 3);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID) VALUES (2, 4);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID) VALUES (3, 5);