package com.estramipyme.estramipyme_API.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teachers")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeacher;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private String password;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresas empresa;

    @ManyToOne
    @JoinColumn(name = "type_user_id")
    private TypeUser type_user;

}
