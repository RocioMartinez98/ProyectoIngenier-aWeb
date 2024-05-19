package com.NotePad.Project.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String correo;
    private String clave;

    @OneToMany
    @JoinTable(name="notasDeUsuario", joinColumns = {@JoinColumn(name = "usuario_id")},inverseJoinColumns = {@JoinColumn(name="nota_id")})
    private List<Nota> notasDeUsuario = new ArrayList<>();

    public Usuario(){}

    public Usuario(long id, String correo, String clave) {
        this.id = id;
        this.correo = correo;
        this.clave = clave;
    }

    public List<Nota> getNotasDeUsuario(){
        return notasDeUsuario;
    }

    public void addNotasDeUsuario(Nota notaDeUsuario){
        notasDeUsuario.add(notaDeUsuario);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}

