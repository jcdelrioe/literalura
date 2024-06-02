package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Datos;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
  private Scanner teclado = new Scanner(System.in);
  private static final String URL_BASE = "https://gutendex.com/books/";
  private ConsumoAPI consumoApi = new ConsumoAPI();
  private ConvierteDatos conversor = new ConvierteDatos();
  private List<Datos> datosLibrosBuscados = new ArrayList<>();
  private LibroRepository libroRepository;
  private AutorRepository autorRepository;

  public Principal(){}

  public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
    this.libroRepository = libroRepository;
    this.autorRepository = autorRepository;
  }

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
        0.- Salir de la aplicación
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
          listarLibrosRegistrados();
          break;
        case 3:
          listarAutoresRegistrados();
          break;
        case 4:
          listarAutoresVivos();
          break;
        case 5:
          listarLibrosPorIdioma();
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
    System.out.println("Ingrese el título del libro:");
    var tituloLibroBuscado = teclado.nextLine();

    String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibroBuscado.replace(" ", "%20"));
    Datos libros = conversor.obtenerDatos(json, Datos.class);

    Optional<Libro> libroEncontrado = libros.resultados().stream()
      .map(Libro::new)
      .findFirst();

    System.out.println(libroEncontrado.get().getAutor().getNombre());

    if (libroEncontrado.isPresent()){

      Autor autor = autorRepository.findByNombreContainsIgnoreCase(libroEncontrado.get().getAutor().getNombre());

      if (autor == null){
        Autor nuevoActor = libroEncontrado.get().getAutor();
        autor = autorRepository.save(nuevoActor);
      }

      Libro libro = libroEncontrado.get();

      try {
        libro.setAutor(autor);
        libroRepository.save(libro);
        System.out.println(libro);
      }catch (DataIntegrityViolationException ex){
        System.out.println("El libro ya existe en la Base de Datos");
      }

    }else{
      System.out.println("El libro no ha sido encontrado");
    }
  }

  private void listarLibrosRegistrados() {
    List<Libro> libros = libroRepository.findAll();
    libros.stream().forEach(System.out::println);
  }

  private void listarAutoresRegistrados(){
    List<Autor> autores = autorRepository.findAll();
    autores.stream().forEach(System.out::println);
  }

  private void listarAutoresVivos(){
    System.out.println("Ingrese el año: ");
    int annoBuscado;
    String anno;

    try{
      annoBuscado = teclado.nextInt();
      anno = String.valueOf(annoBuscado);

      List<Autor> autoresVivos = autorRepository.buscarAutorVivo(anno);

      if (autoresVivos.isEmpty()){
        System.out.println("No se encontraron autores vivos en ese año");
      }else{
        autoresVivos.stream().forEach(System.out::println);
      }

    }catch (Exception e){
      System.out.println("Escriba un año válido");
      teclado.nextLine();
    }
  }

  private void listarLibrosPorIdioma(){
    System.out.println("""
      Ingrese el idioma:
      es - español
      en - ingles
      fr - francés
      pt - portugues
      """);
    String idioma = teclado.nextLine();

    List<Libro> librosPorIdioma = libroRepository.findByIdiomas(idioma);
    if (librosPorIdioma.isEmpty()){
      System.out.println("No se encontraron libros en ese idioma");
    }else{
      librosPorIdioma.forEach(System.out::println);
    }
  }

}
