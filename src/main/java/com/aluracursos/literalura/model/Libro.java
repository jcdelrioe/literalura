package com.aluracursos.literalura.model;

import java.util.List;

public class Libro {
 private String titulo;
 private Autor autor;
 private List<String> idiomas;
 private Double numeroDeDescarga;

 public Libro(DatosLibros datosLibros){
  this.titulo = datosLibros.titulo();
  this.autor = new Autor((datosLibros.autor().get(0)));
  this.idiomas = datosLibros.idiomas();
  this.numeroDeDescarga = datosLibros.numeroDeDescargas();
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

 public List<String> getIdiomas() {
  return idiomas;
 }

 public void setIdiomas(List<String> idiomas) {
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
