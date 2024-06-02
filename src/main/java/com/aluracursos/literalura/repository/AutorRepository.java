package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
  Autor findByNombreContainsIgnoreCase(String nombre);

  @Query("SELECT autor FROM Autor autor " +
    "WHERE autor.fechaDeNacimiento <= :anno " +
    "AND autor.fechaDeMuerte >= :anno")
  List<Autor> buscarAutorVivo(String anno);
}
