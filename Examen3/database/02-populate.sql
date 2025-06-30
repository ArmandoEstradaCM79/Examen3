-- Insertar estudiantes
INSERT INTO estudiante (nombre, genero, carrera) VALUES
('Ana Gómez', 'F', 'Computación'),
('Luis Pérez', 'M', 'Sistemas'),
('Marta Ruiz', 'F', 'Software'),
('Carlos Díaz', 'M', 'Sistemas');

-- Insertar materias
INSERT INTO materia (nombre, creditos) VALUES
('Matemáticas', 6),
('Bases de Datos', 5),
('Programación', 5);

-- Insertar profesores
INSERT INTO profesor (nombre, departamento) VALUES
('Dr. Juan Torres', 'Ciencias Computacionales'),
('Dra. Laura Vélez', 'Sistemas de Información');

-- Insertar grupos
INSERT INTO grupo (grupo, semestre) VALUES
('A1', '2024-1'),
('B1', '2024-1');

-- Insertar tiempos
INSERT INTO tiempo (anio, semestre) VALUES
(2024, 'Primavera'),
(2024, 'Otoño');

-- Insertar calificaciones
INSERT INTO calificacion (estudiante_id, materia_id, profesor_id, grupo_id, tiempo_id, calificacion) VALUES
(1, 1, 1, 1, 1, 8.5),
(2, 1, 1, 1, 1, 9.0),
(3, 2, 2, 2, 1, 7.5),
(4, 3, 2, 2, 1, 6.8),
(1, 2, 2, 1, 2, 9.1),
(2, 3, 1, 2, 2, 8.0);
