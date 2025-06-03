DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255),
    ap_paterno VARCHAR(255),
    ap_materno VARCHAR(255),
    run VARCHAR(13) NOT NULL UNIQUE,
    mail VARCHAR(255),
    rol VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (nombre, ap_paterno, ap_materno, run, mail, rol) VALUES
('Juan', 'Pérez', 'González', '10000001-1', 'juan.perez@example.com', 'ADMIN'),
('María', 'López', 'Ramírez', '10000002-2', 'maria.lopez@example.com', 'USER'),
('Carlos', 'Soto', 'Fernández', '10000003-3', 'carlos.soto@example.com', 'USER'),
('Ana', 'Torres', 'Mendoza', '10000004-4', 'ana.torres@example.com', 'MODERATOR'),
('Pedro', 'García', 'Muñoz', '10000005-5', 'pedro.garcia@example.com', 'USER'),
('Lucía', 'Reyes', 'Navarro', '10000006-6', 'lucia.reyes@example.com', 'USER'),
('Diego', 'Morales', 'Castillo', '10000007-7', 'diego.morales@example.com', 'USER'),
('Valentina', 'Silva', 'Ortega', '10000008-8', 'valentina.silva@example.com', 'ADMIN'),
('Andrés', 'Fuentes', 'Salazar', '10000009-9', 'andres.fuentes@example.com', 'USER'),
('Camila', 'Vargas', 'Cáceres', '10000010-0', 'camila.vargas@example.com', 'USER'),
('Rodrigo', 'Campos', 'Leiva', '10000011-1', 'rodrigo.campos@example.com', 'MODERATOR'),
('Florencia', 'Molina', 'Uribe', '10000012-2', 'florencia.molina@example.com', 'USER'),
('Sebastián', 'Cruz', 'Peña', '10000013-3', 'sebastian.cruz@example.com', 'USER'),
('Isidora', 'Herrera', 'Araya', '10000014-4', 'isidora.herrera@example.com', 'USER'),
('Tomás', 'Saavedra', 'Pizarro', '10000015-5', 'tomas.saavedra@example.com', 'ADMIN'),
('Javiera', 'Ríos', 'Escobar', '10000016-6', 'javiera.rios@example.com', 'USER'),
('Francisco', 'Gallardo', 'Venegas', '10000017-7', 'francisco.gallardo@example.com', 'USER'),
('Catalina', 'Cortés', 'Zamora', '10000018-8', 'catalina.cortes@example.com', 'USER'),
('Matías', 'Aguilera', 'Vergara', '10000019-9', 'matias.aguilera@example.com', 'MODERATOR'),
('Fernanda', 'Espinoza', 'Olivares', '10000020-0', 'fernanda.espinoza@example.com', 'USER'),
('Benjamín', 'Delgado', 'Carrasco', '10000021-1', 'benjamin.delgado@example.com', 'USER'),
('Antonia', 'Tapia', 'Toro', '10000022-2', 'antonia.tapia@example.com', 'ADMIN'),
('Vicente', 'Aguirre', 'Barrientos', '10000023-3', 'vicente.aguirre@example.com', 'USER'),
('Daniela', 'Lagos', 'Valdés', '10000024-4', 'daniela.lagos@example.com', 'USER'),
('Felipe', 'Bravo', 'Medina', '10000025-5', 'felipe.bravo@example.com', 'MODERATOR');
