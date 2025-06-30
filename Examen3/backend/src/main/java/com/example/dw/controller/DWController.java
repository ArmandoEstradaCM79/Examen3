
package com.example.dw.controller;

import com.example.dw.model.Calificacion;
import com.example.dw.model.Profesor;
import com.example.dw.service.AnalyticsService;
import com.example.dw.service.CargaDatosService;
import com.example.dw.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DWController {

    private final CargaDatosService cargaDatosService;
    private final AnalyticsService analyticsService;
    private final ProfesorService profesorService;

    @PostMapping("/load-data")
    public ResponseEntity<String> loadData(@RequestBody List<Calificacion> calificaciones) {
        cargaDatosService.guardarCalificaciones(calificaciones);
        return ResponseEntity.ok("Datos cargados correctamente.");
    }

    @GetMapping("/analytics/promedio-materias")
    public ResponseEntity<List<Object[]>> promedioMaterias() {
        return ResponseEntity.ok(analyticsService.obtenerPromedioPorMateria());
    }

    @GetMapping("/analytics/rendimiento-grupos")
    public ResponseEntity<List<Object[]>> rendimientoGrupos() {
        return ResponseEntity.ok(analyticsService.obtenerPromedioPorGrupo());
    }

    @GetMapping("/analytics/rendimiento-profesores")
    public ResponseEntity<List<Object[]>> rendimientoProfesores() {
        return ResponseEntity.ok(analyticsService.obtenerPromedioPorProfesor());
    }

    @PutMapping("/dimension/profesor/{id}")
    public ResponseEntity<?> actualizarProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        return profesorService.actualizarProfesor(id, profesor)
                .map(updated -> ResponseEntity.ok("Profesor actualizado"))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
