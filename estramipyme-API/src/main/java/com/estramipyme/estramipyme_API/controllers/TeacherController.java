package com.estramipyme.estramipyme_API.controllers;

import com.estramipyme.estramipyme_API.models.Teacher;
import com.estramipyme.estramipyme_API.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // consulta todos
    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getTeachers();
    }

    // consul Id
    @GetMapping("id/{id}")
    public Teacher getId(@PathVariable Long id) {
        return teacherService.getTeacherId(id);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<List<Teacher>> getTeacherByEmail(@RequestParam String email) {
        List<Teacher> teachers = teacherService.findByEmail(email);
        return ResponseEntity.ok(teachers);
    }

    // crear teacher
    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody Teacher teacher) {
        Teacher createdTeacher = teacherService.addTeacher(teacher);
        return ResponseEntity.ok(createdTeacher);
    }

    // Actualizar
    @PutMapping("/{id}")
    public Teacher updateTeacher(@RequestBody Teacher teacher, @PathVariable Long id) {
        teacher.setIdTeacher(id);
        return teacherService.updateTeacher(teacher);
    }

    // delete
    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacherId(id);
    }
}
