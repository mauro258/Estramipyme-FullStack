package com.estramipyme.estramipyme_API.controllers;

import com.estramipyme.estramipyme_API.Repositories.StudentRepository;
import com.estramipyme.estramipyme_API.Repositories.TypeUserRepository;
import com.estramipyme.estramipyme_API.Repositories.EmpresasRepository;
import com.estramipyme.estramipyme_API.models.Students;
import com.estramipyme.estramipyme_API.models.TypeUser;
import com.estramipyme.estramipyme_API.models.Empresas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TypeUserRepository typeUserRepository;

    @Autowired
    private EmpresasRepository empresasRepository;

    // Obtener todos los estudiantes
    @GetMapping
    public List<Students> getAllStudents() {
        return studentRepository.findAll();
    }

    // Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<Students> getStudentById(@PathVariable Integer id) {
        Optional<Students> student = studentRepository.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody Students student) {
        if (student.getTypeUser() == null || student.getEmpresa() == null) {
            return ResponseEntity.badRequest().body("El 'typeUser' y la 'empresa' son obligatorios.");
        }
    
        // Verificar existencia de typeUser y empresa en la base de datos
        Optional<TypeUser> typeUser = typeUserRepository.findById(student.getTypeUser().getId());
        Optional<Empresas> empresa = empresasRepository.findById(student.getEmpresa().getId());
    
        if (typeUser.isEmpty()) {
            return ResponseEntity.badRequest().body("El 'typeUser' con id " + student.getTypeUser().getId() + " no existe.");
        }
    
        if (empresa.isEmpty()) {
            return ResponseEntity.badRequest().body("La 'empresa' con id " + student.getEmpresa().getId() + " no existe.");
        }
    
        // Asignar las entidades y guardar el estudiante
        student.setTypeUser(typeUser.get());
        student.setEmpresa(empresa.get());
        Students savedStudent = studentRepository.save(student);
        return ResponseEntity.ok(savedStudent);
    }

    // Actualizar un estudiante
    @PutMapping("/{id}")
    public ResponseEntity<Students> updateStudent(@PathVariable Integer id, @RequestBody Students studentDetails) {
        Optional<Students> student = studentRepository.findById(id);

        if (student.isPresent()) {
            Students updatedStudent = student.get();
            updatedStudent.setNombre(studentDetails.getNombre());
            updatedStudent.setApellido(studentDetails.getApellido());

            // Gestionar el Optional de email
            if (studentDetails.getEmail() != null) {
                updatedStudent.setEmail(studentDetails.getEmail());
            }

            // Gestionar el Optional de telefono
            if (studentDetails.getTelefono() != null) {
                updatedStudent.setTelefono(studentDetails.getTelefono());
            }

            updatedStudent.setPassword(studentDetails.getPassword());

            // Verificar y asignar typeUser
            if (studentDetails.getTypeUser() != null) {
                Optional<TypeUser> typeUser = typeUserRepository.findById(studentDetails.getTypeUser().getId());
                typeUser.ifPresent(updatedStudent::setTypeUser);
            }

            // Verificar y asignar empresa
            if (studentDetails.getEmpresa() != null) {
                Optional<Empresas> empresa = empresasRepository.findById(studentDetails.getEmpresa().getId());
                empresa.ifPresent(updatedStudent::setEmpresa);
            }

            studentRepository.save(updatedStudent);
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer id) {
        Optional<Students> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
