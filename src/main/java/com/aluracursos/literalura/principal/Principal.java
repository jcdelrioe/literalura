package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.DatosLibros;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
  private Scanner teclado = new Scanner(System.in);
  private static final String URL_BASE = "https://gutendex.com/books/";
  private ConsumoAPI consumoApi = new ConsumoAPI();
  private ConvierteDatos conversor = new ConvierteDatos();

//  private String json = consumoApi.obtenerDatos(URL_BASE);
//  private Datos datos = conversor.obtenerDatos(json, Datos.class);
  
  public void muestraElMenu(){
//    var json = consumoApi.obtenerDatos(URL_BASE);
//    var datos = conversor.obtenerDatos(json, Datos.class);
//    System.out.println(datos);

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
        case 0:
          System.out.println("Cerrando la aplicación");
          break;
        default:
          System.out.println("Opción invalida");
          break;
      }
    }
  }

  private void buscarLibroPorTitulo() {
    System.out.println("Ingrese el título del libro:");
    var tituloLibroBuscado = teclado.nextLine();

    String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibroBuscado.replace(" ", "%20"));
    Datos datos = conversor.obtenerDatos(json, Datos.class);

    List<DatosLibros> librosEncontrados = datos.resultados().stream()
      .filter(l -> l.titulo().toLowerCase().contains(tituloLibroBuscado.toLowerCase()))
      .collect(Collectors.toList());
    System.out.println(librosEncontrados);

  }
}
