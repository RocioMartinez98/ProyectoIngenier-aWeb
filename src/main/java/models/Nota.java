package models;

import jakarta.persistence.*;

@Entity
@Table(name="nota")
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private int tipo;
    private String texto;

    public Nota(){}

    public Nota(long id, String titulo, int tipo, String texto) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.texto = texto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
}
