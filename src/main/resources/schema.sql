CREATE TABLE nota(
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    tipo INT NOT NULL,
    texto VARCHAR(255)
);

CREATE TABLE usuario(
    id BIGSERIAL PRIMARY KEY,
    clave VARCHAR(255) NOT NULL,
    correo VARCHAR(255) NOT NULL
);

CREATE TABLE notasDeUsuario(
    usuario_id BIGSERIAL,
    nota_id BIGSERIAL,
    PRIMARY KEY(usuario_id, nota_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (nota_id) REFERENCES nota(id)
);

INSERT INTO nota (id, titulo, tipo, texto) VALUES (1, 'Nota 1', 1, 'Texto de la nota 1');
INSERT INTO nota (id, titulo, tipo, texto) VALUES (2, 'Nota 2', 2, 'Texto de la nota 2');
INSERT INTO nota (id, titulo, tipo, texto) VALUES (3, 'Nota 3', 1, 'Texto de la nota 3');
INSERT INTO nota (id, titulo, tipo, texto) VALUES (4, 'Nota 4', 3, 'Texto de la nota 4');
INSERT INTO nota (id, titulo, tipo, texto) VALUES (5, 'Nota 5', 2, 'Texto de la nota 5');

INSERT INTO usuario (id, clave, correo) VALUES (1, 'clave1', 'usuario1@example.com');
INSERT INTO usuario (id, clave, correo) VALUES (2, 'clave2', 'usuario2@example.com');
INSERT INTO usuario (id, clave, correo) VALUES (3, 'clave3', 'usuario3@example.com');

INSERT INTO notasDeUsuario (usuario_id, nota_id) VALUES (1, 1);
INSERT INTO notasDeUsuario (usuario_id, nota_id) VALUES (1, 2);
INSERT INTO notasDeUsuario (usuario_id, nota_id) VALUES (2, 3);
INSERT INTO notasDeUsuario (usuario_id, nota_id) VALUES (2, 4);
INSERT INTO notasDeUsuario (usuario_id, nota_id) VALUES (3, 5);