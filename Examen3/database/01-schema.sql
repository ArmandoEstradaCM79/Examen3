-- Dimensiones
CREATE TABLE IF NOT EXISTS estudiante (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    genero VARCHAR(10),
    carrera VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS materia (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    creditos INT
);

CREATE TABLE IF NOT EXISTS profesor (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100),
    departamento VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS grupo (
    id SERIAL PRIMARY KEY,
    grupo VARCHAR(10),
    semestre VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS tiempo (
    id SERIAL PRIMARY KEY,
    anio INT,
    semestre VARCHAR(10)
);

-- Hechos
CREATE TABLE IF NOT EXISTS calificacion (
    id SERIAL PRIMARY KEY,
    estudiante_id INT REFERENCES estudiante(id),
    materia_id INT REFERENCES materia(id),
    profesor_id INT REFERENCES profesor(id),
    grupo_id INT REFERENCES grupo(id),
    tiempo_id INT REFERENCES tiempo(id),
    calificacion NUMERIC(4,2)
);
