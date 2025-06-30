
package com.example.dw.service;

import com.example.dw.repository.CalificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final CalificacionRepository calificacionRepository;

    public List<Object[]> obtenerPromedioPorMateria() {
        return calificacionRepository.promedioPorMateria();
    }

    public List<Object[]> obtenerPromedioPorGrupo() {
        return calificacionRepository.promedioPorGrupo();
    }

    public List<Object[]> obtenerPromedioPorProfesor() {
        return calificacionRepository.promedioPorProfesor();
    }
}
