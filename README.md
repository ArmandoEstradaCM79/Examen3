# 📊 Proyecto: Dashboard de Rendimiento Académico

Este proyecto consiste en el diseño e implementación de un Data Warehouse (DW) orientado al análisis del rendimiento académico de estudiantes universitarios, complementado con una aplicación web desarrollada en Spring Boot (backend) y HTML + JavaScript (frontend), y desplegada completamente usando Docker.

---

## ✅ FASE 1: Propuesta del Caso de Uso

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

## ✅ FASE 2: Diseño y Construcción del Data Warehouse

### Modelo Estrella

- Hecho: `calificacion`
- Dimensiones: `estudiante`, `materia`, `profesor`, `grupo`, `tiempo`

![Modelo Estrella](URL_AQUI_MODELO_ESTRELLA.png)

### Esquema SQL (resumen)

```sql
CREATE TABLE calificacion (
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

## ✅ FASE 3: Desarrollo de la Aplicación

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

![Interfaz Web](URL_AQUI_INTERFAZ_HTML.png)

---

## ✅ FASE 4: Despliegue con Docker

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
services:
  postgres:
    image: postgres:15
    ...
    volumes:
      - ./database/01-schema.sql:/docker-entrypoint-initdb.d/01-schema.sql
      - ./database/02-populate.sql:/docker-entrypoint-initdb.d/02-populate.sql

  backend:
    build: ./backend
    ports: ["8000:8080"]
    depends_on:
      postgres:
        condition: service_healthy

  frontend:
    build: ./frontend
    ports: ["3000:80"]
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

## 📌 Estado Final del Proyecto

✔ Modelo Estrella implementado  
✔ Backend funcional con endpoints analíticos  
✔ Frontend con visualización y formularios  
✔ Despliegue automatizado con Docker  

---

## 📝 Créditos

Proyecto realizado como parte del curso de Ingeniería de Software — ESCOM IPN  
Autor: [Tu nombre aquí]
