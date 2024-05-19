package repository;

import models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "nota")
public interface NotaRepository extends JpaRepository<Nota,Long> {
}
