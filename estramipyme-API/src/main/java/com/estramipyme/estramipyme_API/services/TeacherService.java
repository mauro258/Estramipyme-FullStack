package com.estramipyme.estramipyme_API.services;

import com.estramipyme.estramipyme_API.Repositories.EmpresasRepository;
import com.estramipyme.estramipyme_API.Repositories.TeacherRepository;
import com.estramipyme.estramipyme_API.Repositories.TypeUserRepository;
import com.estramipyme.estramipyme_API.models.Empresas;
import com.estramipyme.estramipyme_API.models.Teacher;
import com.estramipyme.estramipyme_API.models.TypeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepo;
    @Autowired
    private EmpresasRepository empresasRepository;
    @Autowired
    private TypeUserRepository typeUserRepository;

    // consulta varios
    public List<Teacher> getTeachers() {
        return teacherRepo.findAll();
    }

    // consulta por Id
    public Teacher getTeacherId(Long id) {
        return teacherRepo.findById(id).orElse(null);
    }

    public List<Teacher> findByEmail(String email) {
        return teacherRepo.findByEmail(email);
    }

    // crea profesores
    public Teacher addTeacher(Teacher teacher) {
        Empresas empresa = empresasRepository.findById(teacher.getEmpresa().getId()).orElse(null);
        if (empresa == null) {
            throw  new RuntimeException("La empresa no es valida");
        }
        TypeUser typeUser = typeUserRepository.findById(teacher.getType_user().getId()).orElse(null);
        if (typeUser == null) {
            throw  new RuntimeException("TypeUser no es valido");
        }

        teacher.setEmpresa(empresa);
        teacher.setType_user(typeUser);

        return teacherRepo.save(teacher);
    }

    // Edita profesores
    public Teacher updateTeacher(Teacher teacher){
        Empresas empresa = empresasRepository.findById(teacher.getEmpresa().getId()).orElse(null);
        if  (empresa ==null) {
            throw  new RuntimeException("La empresa no es valida");
        }
        TypeUser typeUser = typeUserRepository.findById(teacher.getType_user().getId()).orElse(null);
        if  (typeUser ==null) {
            throw  new RuntimeException("TypeUser no es valida");
        }

        teacher.setEmpresa(empresa);
        teacher.setType_user(typeUser);

        return teacherRepo.save(teacher);
    }

    // Elimina profe
    public void deleteTeacherId(Long id) {
        teacherRepo.deleteById(id);
    }

}
