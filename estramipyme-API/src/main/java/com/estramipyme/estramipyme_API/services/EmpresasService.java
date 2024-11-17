package com.estramipyme.estramipyme_API.services;

import java.util.List;

import com.estramipyme.estramipyme_API.Repositories.TestRepository;
import com.estramipyme.estramipyme_API.Repositories.TipoPersonaRepository;
import com.estramipyme.estramipyme_API.models.Students;
import com.estramipyme.estramipyme_API.models.Test;
import com.estramipyme.estramipyme_API.models.TipoPersona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estramipyme.estramipyme_API.Repositories.EmpresasRepository;
import com.estramipyme.estramipyme_API.models.Empresas;

@Service
public class EmpresasService {

    @Autowired
    private EmpresasRepository empresasRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TipoPersonaRepository tipoPersonaRepository;

    public List<Empresas> getAllEmpresas() {
        return empresasRepository.findAll();
    }

    public Empresas getEmpresaById(Long id) {
        return empresasRepository.findById(id).orElse(null);
    }
//consulta por correo
    public List<Empresas> findByEmail(String email) {
        return empresasRepository.findByEmail(email);
    }

    public Empresas saveEmpresa(Empresas empresa) {
        // Validación del Sector
        if (empresa.getSector() == null || empresa.getSector().getId() == null) {
            throw new RuntimeException("El sector no es válido.");
        }

        // Validación y recuperación del Test (si existe)
        if (empresa.getTest() != null) {
            Test test = testRepository.findById(empresa.getTest().getIdTest()).orElse(null);
            if (test == null) {
                throw new RuntimeException("El test no es válido.");
            }
            empresa.setTest(test);
        }

        // Validación y recuperación del TipoPersona
        if (empresa.getTipoPersona() != null) {
            TipoPersona tipoPersona = tipoPersonaRepository.findById(empresa.getTipoPersona().getIdTipoPersona())
                .orElseThrow(() -> new RuntimeException("El TipoPersona no es válido."));
            empresa.setTipoPersona(tipoPersona);
        }

        return empresasRepository.save(empresa);
    }


    public Empresas updateEmpresa(Long id, Empresas empresaDetails) {
        Empresas empresa = empresasRepository.findById(id).orElse(null);
        if (empresa != null) {
            if (empresaDetails.getSector() != null) {
                empresa.setSector(empresaDetails.getSector());
            }
            empresa.setNombreEmpresa(empresaDetails.getNombreEmpresa());
            empresa.setSizeCompany(empresaDetails.getSizeCompany());
            empresa.setTest(empresaDetails.getTest());
            empresa.setPassword(empresaDetails.getPassword());
            empresa.setEmail(empresaDetails.getEmail());
            empresa.setTipoPersona(empresaDetails.getTipoPersona());
            empresa.setIdentificacion(empresaDetails.getIdentificacion());
            return empresasRepository.save(empresa);
        }
        return null;
    }
    

    public void deleteEmpresa(Long id) {
        empresasRepository.deleteById(id);
    }
}