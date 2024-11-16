package com.estramipyme.estramipyme_API.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tipopersona")
@Data

public class TipoPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoPersona;
    private String name_TipoPersona;
    @Lob 
    private String description;

    public TipoPersona() {
    }

    public TipoPersona(Long idTipoPersona, String name_TipoPersona, String description) {
        this.idTipoPersona = idTipoPersona;
        this.name_TipoPersona = name_TipoPersona;
        this.description = description;
    }
}

