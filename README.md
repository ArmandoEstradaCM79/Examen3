# Exámen 3 Página de Rendimiento Académico con Spring-Boot

Consiste en el diseño e implementación de un Data Warehouse (DW) orientado al análisis del rendimiento académico de estudiantes y profesores de una universidad, complementado con una aplicación web desarrollada en Spring Boot (backend) y HTML + JavaScript (frontend), y desplegada usando Docker.

---

## FASE 1: Propuesta del Caso de Uso

### 1. Identificar un Caso de Uso

**Título**: Análisis del Rendimiento Académico en una Universidad

**Descripción**: Un sistema que permita a los coordinadores académicos visualizar estadísticas de calificaciones por materia, grupo y profesor, para apoyar la toma de decisiones académicas.

---

### 2. Justificar la Elección

- La educación superior genera grandes volúmenes de datos históricos (materias, calificaciones, profesores, semestres).
- Es común necesitar reportes agregados y análisis comparativos por diferentes dimensiones (tiempo, grupo, docente, etc.).
- El enfoque de DW es ideal para explotar esta información de manera analítica sin afectar los sistemas transaccionales.

---

### 3. Definir Preguntas de Negocio

- ¿Cuál es la **calificación promedio por materia** en los últimos periodos?
- ¿Qué **grupo** tuvo el mejor rendimiento promedio por semestre?
- ¿Qué **profesores** presentan los mejores resultados en promedio?

---

### 4. Definir el Alcance del DW

- **Hecho principal**: Calificación obtenida por un estudiante
- **Dimensiones**:
  - Estudiante
  - Materia
  - Profesor
  - Grupo
  - Tiempo

---

## FASE 2: Diseño y Construcción del Data Warehouse

### Modelo Estrella

- Hecho: `calificacion`
- Dimensiones: `estudiante`, `materia`, `profesor`, `grupo`, `tiempo`

![image](https://github.com/user-attachments/assets/48ac0e0e-b1b4-4827-907f-d967458603df)


### Esquema SQL (resumen)

```sql
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

```

---

## FASE 3: Desarrollo de la Aplicación

### Backend - Spring Boot

Tecnologías:

- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok

#### Endpoints REST

- `POST /api/load-data`: Carga masiva de calificaciones
- `GET /api/analytics/promedio-materias`: Promedio por materia
- `GET /api/analytics/rendimiento-grupos`: Promedio por grupo y semestre
- `GET /api/analytics/rendimiento-profesores`: Promedio por profesor
- `PUT /api/dimension/profesor/{id}`: Actualización de profesor

---

### Frontend - HTML + JavaScript

Características:

- SPA simple
- Visualización de promedios por materia, grupo y profesor
- Formulario para cargar calificaciones nuevas
- Formulario para editar profesores

![image](https://github.com/user-attachments/assets/ca359755-52c1-475d-9509-963c35d6eb41)


---

## FASE 4: Despliegue con Docker

### Estructura del Proyecto

```
proyecto_dw_completo/
│
├── backend/
│   └── ... (Spring Boot App)
├── frontend/
│   └── index.html
├── database/
│   ├── 01-schema.sql
│   └── 02-populate.sql
└── docker-compose.yml
```

### docker-compose.yml (resumen)

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15
    container_name: dw_postgres
    environment:
      POSTGRES_DB: dw_escuela
      POSTGRES_USER: user
      POSTGRES_PASSWORD: pass
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data
      - ./database/01-schema.sql:/docker-entrypoint-initdb.d/01-schema.sql
      - ./database/02-populate.sql:/docker-entrypoint-initdb.d/02-populate.sql
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U user -d dw_escuela"]
      interval: 5s
      timeout: 5s
      retries: 5

  backend:
    build: ./backend
    container_name: dw_backend
    ports:
      - "8000:8080"
    depends_on:
      postgres:
        condition: service_healthy

  frontend:
    build: ./frontend
    container_name: dw_frontend
    ports:
      - "3000:80"
    depends_on:
      - backend

volumes:
  pgdata:

```

### Comandos de despliegue

```bash
# Construir y levantar contenedores
docker-compose up -d --build
```

Accede a la aplicación:

- Backend API: [http://localhost:8000/api](http://localhost:8000/api)
- Frontend Web: [http://localhost:3000](http://localhost:3000)

---
