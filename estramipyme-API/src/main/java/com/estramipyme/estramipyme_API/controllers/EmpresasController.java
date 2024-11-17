package com.estramipyme.estramipyme_API.controllers;

import com.estramipyme.estramipyme_API.models.Empresas;
import com.estramipyme.estramipyme_API.models.Students;
import com.estramipyme.estramipyme_API.services.EmpresasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Empresas")
public class EmpresasController {

    @Autowired
    private EmpresasService empresasService;

    @GetMapping
    public List<Empresas> getAllEmpresas() {
        return empresasService.getAllEmpresas();
    }

    @GetMapping("/{id}")
    public Empresas getEmpresaById(@PathVariable Long id) {
        return empresasService.getEmpresaById(id);
    }

    @GetMapping("email/")
    public ResponseEntity<List<Empresas>> getStudentsByEmail(@RequestParam String email) {
        List<Empresas> empresas = empresasService.findByEmail(email);
        return ResponseEntity.ok(empresas);
    }

    @PostMapping
    public ResponseEntity<Empresas> createEmpresa(@RequestBody Empresas empresa) {
        Empresas savedEmpresa = empresasService.saveEmpresa(empresa);
        return new ResponseEntity<>(savedEmpresa, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Empresas updateEmpresa(@PathVariable Long id, @RequestBody Empresas empresaDetails) {
        return empresasService.updateEmpresa(id, empresaDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmpresa(@PathVariable Long id) {
        empresasService.deleteEmpresa(id);
    }

}
