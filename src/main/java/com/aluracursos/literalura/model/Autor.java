package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long Id;
  private String nombre;
  private Integer fechaDeNacimiento;
  private Integer fechaDeMuerte;
  @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Libro> libros = new ArrayList<>();

  public Autor(){}

  public Autor(DatosAutor datosAutor){
    this.nombre = datosAutor.nombre();
    this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
    this.fechaDeMuerte = datosAutor.fechaDeMuerte();
  }

  public Long getId() {
    return Id;
  }

  public void setId(Long id) {
    Id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Integer getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public Integer getFechaDeMuerte() {
    return fechaDeMuerte;
  }

  public void setFechaDeMuerte(Integer fechaDeMuerte) {
    this.fechaDeMuerte = fechaDeMuerte;
  }

  public List<Libro> getLibros() {
    return libros;
  }

  public void setLibros(List<Libro> libros) {
    this.libros = libros;
  }

  @Override
  public String toString() {
    return
      "nombre='" + nombre + '\'' +
      ", fechaDeNacimiento=" + fechaDeNacimiento +
      ", fechaDeMuerte=" + fechaDeMuerte ;
  }
}
