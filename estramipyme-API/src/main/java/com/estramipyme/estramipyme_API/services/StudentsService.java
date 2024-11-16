package com.estramipyme.estramipyme_API.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estramipyme.estramipyme_API.models.Students;
import com.estramipyme.estramipyme_API.Repositories.StudentRepository;;

@Service
public class StudentsService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Students> obtenerTodos() {
        return studentRepository.findAll();
    }

    // Obtener un estudiante por su ID
    public Optional<Students> obtenerPorId(Integer id) {
        return studentRepository.findById(id);
    }

    // Crear un nuevo estudiante
    public Students crearStudents(Students student) {
        return studentRepository.save(student);
    }

    // Actualizar un estudiante existente
    public Students actualizarStudents(Students student) {
        return studentRepository.save(student);
    }

    // Eliminar un estudiante por su ID
    public void eliminarStudents(Integer id) {
        studentRepository.deleteById(id);
    }
}
