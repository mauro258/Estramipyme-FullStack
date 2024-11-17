package com.estramipyme.estramipyme_API.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipo_personas")
@Data

public class TipoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPersona;

    private String description;

    public TipoPersona() {
    }

    public TipoPersona(Long idTipoPersona, String name_TipoPersona, String description) {
        this.idTipoPersona = idTipoPersona;
        this.description = description;
    }

    
}

