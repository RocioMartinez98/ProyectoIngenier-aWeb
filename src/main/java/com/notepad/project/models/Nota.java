package com.notepad.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="NOTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nota{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;
    private int tipo;
    private String texto;

    public Nota(Long id){
        this.id = id;
    }



}