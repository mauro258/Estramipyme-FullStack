package com.estramipyme.estramipyme_API.controllers;

import com.estramipyme.estramipyme_API.models.TipoPersona;
import com.estramipyme.estramipyme_API.services.TipoPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipopersona")
public class TipoPersonaController {

    @Autowired
    private TipoPersonaService TipoPersonaService;

    @GetMapping
    public List<TipoPersona> getAllTipoPersonas() {
        return TipoPersonaService.getTipoPersona();
    }

    @GetMapping("/{id}")
    public Optional<TipoPersona> consultOneTipoPersona(@PathVariable Long id) {
        return TipoPersonaService.getTipoPersonaXId(id);
    }

    @PostMapping
    public TipoPersona createTipoPersona(@RequestBody TipoPersona tipopersona) {
        return TipoPersonaService.addTipoPersona(tipopersona);
    }

    @PutMapping("/{id}")
    public TipoPersona editTipoPersona(@RequestBody TipoPersona tipopersona, @PathVariable Long id) {
        tipopersona.setIdTipoPersona(id);
        return TipoPersonaService.updateTipoPersona(tipopersona);
    }

    @DeleteMapping("/{id}")
    public void removeTipoPersona(@PathVariable Long id) {
        TipoPersonaService.deleteTipoPersona(id);
    }
}
