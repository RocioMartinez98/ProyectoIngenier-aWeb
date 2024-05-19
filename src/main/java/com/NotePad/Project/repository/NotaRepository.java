package com.NotePad.Project.repository;

import com.NotePad.Project.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota,Long> {
}
