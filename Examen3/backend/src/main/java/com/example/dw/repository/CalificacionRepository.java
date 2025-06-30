
package com.example.dw.repository;

import com.example.dw.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long> {

    @Query("SELECT c.materia.nombre, AVG(c.calificacion) " +
           "FROM Calificacion c GROUP BY c.materia.nombre")
    List<Object[]> promedioPorMateria();

    @Query("SELECT c.grupo.grupo, c.tiempo.semestre, AVG(c.calificacion) " +
           "FROM Calificacion c GROUP BY c.grupo.grupo, c.tiempo.semestre")
    List<Object[]> promedioPorGrupo();

    @Query("SELECT c.profesor.nombre, AVG(c.calificacion) " +
           "FROM Calificacion c GROUP BY c.profesor.nombre")
    List<Object[]> promedioPorProfesor();
}
