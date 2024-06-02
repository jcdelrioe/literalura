package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "libros")
public class Libro {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long Id;

 @Column(unique = true)
 private String titulo;

 private String idiomas;
 private Double numeroDeDescarga;

 @ManyToOne
 @JoinColumn(name = "autor_id", nullable = false)
 private Autor autor;


 public Libro(){}

 public Libro(DatosLibros datosLibros){
  this.titulo = datosLibros.titulo();
  this.autor = new Autor((datosLibros.autor().get(0)));
  this.idiomas = datosLibros.idiomas().get(0);
  this.numeroDeDescarga = datosLibros.numeroDeDescargas();
 }

 public Long getId() {
  return Id;
 }

 public void setId(Long id) {
  Id = id;
 }

 public String getTitulo() {
  return titulo;
 }

 public void setTitulo(String titulo) {
  this.titulo = titulo;
 }

 public Autor getAutor() {
  return autor;
 }

 public void setAutor(Autor autor) {
  this.autor = autor;
 }

 public String getIdiomas() {
  return idiomas;
 }

 public void setIdiomas(String idiomas) {
  this.idiomas = idiomas;
 }

 public Double getNumeroDeDescarga() {
  return numeroDeDescarga;
 }

 public void setNumeroDeDescarga(Double numeroDeDescarga) {
  this.numeroDeDescarga = numeroDeDescarga;
 }

 @Override
 public String toString() {
  return
    "titulo='" + titulo + '\'' +
    ", autor=" + autor +
    ", idiomas=" + idiomas +
    ", numeroDeDescarga=" + numeroDeDescarga;
 }
}
