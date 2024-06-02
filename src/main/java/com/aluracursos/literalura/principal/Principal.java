package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.DatosLibros;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
  private Scanner teclado = new Scanner(System.in);
  private static final String URL_BASE = "https://gutendex.com/books/";
  private ConsumoAPI consumoApi = new ConsumoAPI();
  private ConvierteDatos conversor = new ConvierteDatos();
  private List<Datos> datosLibrosBuscados = new ArrayList<>();

  public void muestraElMenu(){
    var opcion = -1;
    while(opcion != 0){
      var menu = """
        ================================================
        Elija la opción:
        1.- Buscar libro por título
        2.- Listar libros registrados
        3.- Listar autores registrados
        4.- Listar autores vivos en un determinado año
        5.- Listar libros por idioma
        ================================================
        """;
      System.out.println(menu);
      opcion = teclado.nextInt();
      teclado.nextLine();

      switch (opcion){
        case 1:
          buscarLibroPorTitulo();
          break;
        case 2:
          listarLibros();
          break;
        case 0:
          System.out.println("Cerrando la aplicación");
          break;
        default:
          System.out.println("Opción invalida");
          break;
      }
    }
  }

//  private Datos obtenerDatosLibros(){
//    System.out.println("Ingrese el título del libro:");
//    var tituloLibroBuscado = teclado.nextLine();
//
//    String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibroBuscado.replace(" ", "%20"));
//    System.out.println("json: " + json);
//
//    Datos libros = conversor.obtenerDatos(json, Datos.class);
//    System.out.println("DatosLibros: " + libros);
//
//    return libros;
//  }



  private void buscarLibroPorTitulo() {
//    Datos libros = obtenerDatosLibros();
//    datosLibrosBuscados.add(libros);

    System.out.println("Ingrese el título del libro:");
    var tituloLibroBuscado = teclado.nextLine();

    String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibroBuscado.replace(" ", "%20"));
    Datos libros = conversor.obtenerDatos(json, Datos.class);

    Optional<Libro> libroEncontrado = libros.resultados().stream()
      .map(Libro::new)
      .findFirst();

    if (libroEncontrado.isPresent()){
      Libro libro = libroEncontrado.get();
      System.out.println(libro);
    }else{
      System.out.println("El libro buscado no se encuentra");
    }

  }

  private void listarLibros() {
    System.out.println(datosLibrosBuscados);
  }
}
