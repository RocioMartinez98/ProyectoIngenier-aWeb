INSERT INTO NOTA (titulo, tipo, texto)
VALUES ('Nota 1', 1, 'Texto de la nota 1');
INSERT INTO NOTA (titulo, tipo, texto)
VALUES ('Nota 2', 2, 'Texto de la nota 2');
INSERT INTO NOTA (titulo, tipo, texto)
VALUES ('Nota 3', 1, 'Texto de la nota 3');
INSERT INTO NOTA (titulo, tipo, texto)
VALUES ('Nota 4', 3, 'Texto de la nota 4');
INSERT INTO NOTA (titulo, tipo, texto)
VALUES ('Nota 5', 2, 'Texto de la nota 5');

INSERT INTO USUARIO (clave, correo)
VALUES ('clave1', 'usuario1@example.com');
INSERT INTO USUARIO (clave, correo)
VALUES ('clave2', 'usuario2@example.com');
INSERT INTO USUARIO (clave, correo)
VALUES ('clave3', 'usuario3@example.com');

INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID)
VALUES (1, 1);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID)
VALUES (1, 2);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID)
VALUES (2, 3);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID)
VALUES (2, 4);
INSERT INTO USUARIO_NOTAS (USUARIO_ID, NOTAS_ID)
VALUES (3, 5);