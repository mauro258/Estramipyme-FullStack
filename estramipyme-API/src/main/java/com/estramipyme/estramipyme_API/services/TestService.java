package com.estramipyme.estramipyme_API.services;

import com.estramipyme.estramipyme_API.Repositories.TestRepository;
import com.estramipyme.estramipyme_API.models.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    // consulta todos
    public List<Test> getTests() {
        return testRepository.findAll();
    }

    // consulta por Id
    public Optional<Test> getTestXId(Long id) {
        return testRepository.findById(id);
    }

    // crear
    public Test addTest(Test test) {
        return testRepository.save(test);
    }

    // Actualizar
    public Test updateTest(Test test) {
        return testRepository.save(test);
    }

    // Eliminar
    public void deleteTest(Long id) {
        testRepository.deleteById(id);
    }
}
