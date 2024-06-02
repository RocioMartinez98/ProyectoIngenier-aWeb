package com.notepad.project.repository;

import com.notepad.project.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long> {
    List<Nota> findByTituloContainingIgnoreCase(String titulo);
}
