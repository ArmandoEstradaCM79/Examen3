
package com.example.dw.service;

import com.example.dw.model.*;
import com.example.dw.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CargaDatosService {

    private final CalificacionRepository calificacionRepository;

    public void guardarCalificaciones(List<Calificacion> calificaciones) {
        calificacionRepository.saveAll(calificaciones);
    }
}
