package com.estramipyme.estramipyme_API.controllers;

import com.estramipyme.estramipyme_API.models.Test;
import com.estramipyme.estramipyme_API.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping
    public List<Test> getAllTest() {
        return testService.getTests();
    }

    @GetMapping("/{id}")
    public Optional<Test> consultOneTest(@PathVariable Long id) {
        return testService.getTestXId(id);
    }

    @PostMapping
    public Test createTest(@RequestBody Test test) {
        return testService.addTest(test);
    }

    @PutMapping("/{id}")
    public Test editTest(@RequestBody Test test, @PathVariable Long id) {
        test.setIdTest(id);
        return testService.updateTest(test);
    }

    @DeleteMapping("/{id}")
    public void removeTest(@PathVariable Long id) {
        testService.deleteTest(id);
    }
}
