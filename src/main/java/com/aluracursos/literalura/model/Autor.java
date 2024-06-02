package com.aluracursos.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Autor {
  private String nombre;
  private Integer fechaDeNacimiento;
  private Integer fechaDeMuerte;

  public Autor(DatosAutor datosAutor){
    this.nombre = datosAutor.nombre();
    this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
    this.fechaDeMuerte = datosAutor.fechaDeMuerte();
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

  @Override
  public String toString() {
    return
      "nombre='" + nombre + '\'' +
      ", fechaDeNacimiento=" + fechaDeNacimiento +
      ", fechaDeMuerte=" + fechaDeMuerte ;
  }
}
