
package com.example.dw.service;

import com.example.dw.model.Profesor;
import com.example.dw.repository.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    public Optional<Profesor> actualizarProfesor(Long id, Profesor nuevo) {
        return profesorRepository.findById(id).map(profesor -> {
            profesor.setNombre(nuevo.getNombre());
            profesor.setDepartamento(nuevo.getDepartamento());
            return profesorRepository.save(profesor);
        });
    }
}
