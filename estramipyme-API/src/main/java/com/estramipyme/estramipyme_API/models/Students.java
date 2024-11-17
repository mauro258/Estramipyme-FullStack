package com.estramipyme.estramipyme_API.models;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@NoArgsConstructor
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer student_id;

    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "type_user", nullable = false)
    private TypeUser typeUser;

    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresas empresa;


    // Constructor con todos los campos
    public Students(String nombre, String apellido, String email, String password, String telefono, TypeUser typeUser, Empresas empresa) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.typeUser = typeUser;
        this.empresa = empresa;
    }

    // Métodos getters y setters

    public Integer getStudentId() {
        return student_id;
    }

    public void setStudentId(Integer student_id) {
        this.student_id = student_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TypeUser getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(TypeUser typeUser) {
        this.typeUser = typeUser;
    }

    public Empresas getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresas empresa) {
        this.empresa = empresa;
    }
}
