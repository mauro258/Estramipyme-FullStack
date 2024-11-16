package com.estramipyme.estramipyme_API.services;

import com.estramipyme.estramipyme_API.Repositories.TipoPersonaRepository;
import com.estramipyme.estramipyme_API.models.TipoPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPersonaService {
    @Autowired
    private TipoPersonaRepository TipoPersonaRepository;

    // consulta todos
    public List<TipoPersona> getTipoPersona() {
        return TipoPersonaRepository.findAll();
    }

    // consulta por Id
    public Optional<TipoPersona> getTipoPersonaXId(Long id) {
        return TipoPersonaRepository.findById(id);
    }

    // crear
    public TipoPersona addTipoPersona(TipoPersona tipoPersona) {
        return TipoPersonaRepository.save(tipoPersona);
    }

    // Actualizar
    public TipoPersona updateTipoPersona(TipoPersona tipoPersona) {
        return TipoPersonaRepository.save(tipoPersona);
    }

    // Eliminar
    public void deleteTipoPersona(Long id) {
        TipoPersonaRepository.deleteById(id);
    }
}

