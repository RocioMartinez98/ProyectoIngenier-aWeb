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