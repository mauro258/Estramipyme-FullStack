package com.estramipyme.estramipyme_API.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "test")
@Data

public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTest;
    private String name_Test;
    @Lob // indica que description es un tipo de datos de Hibernate para texto, permite
         // almacenar texto de gran tamaño
    private String description;

    public Test() {
    }

    public Test(Long idTest, String name_Test, String description) {
        this.idTest = idTest;
        this.name_Test = name_Test;
        this.description = description;
    }
}
