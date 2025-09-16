package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/*
ENUNCIADO:
1) Inserta al menos 3 clientes y 3 libros con datos realistas.
2) Varía fechas (fechaRegistro y fechaPublicacion), genera 1 libro con descripcion = NULL y 1 con stock = 0.
3) Imprime los IDs generados.
Restricciones:
- Usa tipos explícitos (sin var).
- Maneja transacción (begin/commit/rollback).
*/
public class Activity01_InsertarClientesYLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: crear clientes y libros, settear campos y persistir
            // Cliente cliente = new Cliente(); cliente.setNombre("..."); ...
            Cliente clienteLuis = new Cliente();
            clienteLuis.setNombre("Luis");
            clienteLuis.setEmail("luisfer@gmail.com");
            clienteLuis.setCiudad("Guatemala");
            clienteLuis.setEdad(20);
            clienteLuis.setActivo(true);
            clienteLuis.setFechaRegistro(LocalDate.now().minusDays(3));
            entityManager.persist(clienteLuis);

            Cliente clienteoscar = new Cliente();
            clienteoscar.setNombre("Oscar");
            clienteoscar.setEmail("oscar@gmail.com");
            clienteoscar.setCiudad("Guatemala");
            clienteoscar.setEdad(19);
            clienteoscar.setActivo(true);
            clienteoscar.setFechaRegistro(LocalDate.of(2025, 02, 5));
            entityManager.persist(clienteoscar);

            Cliente clientemanuel = new Cliente();
            clientemanuel.setNombre("Manuel");
            clientemanuel.setEmail("manuel@gmail.com");
            clientemanuel.setCiudad("Guatemala");
            clientemanuel.setEdad(20);
            clientemanuel.setActivo(false);
            clientemanuel.setFechaRegistro(LocalDate.of(2024, 10, 22));
            entityManager.persist(clientemanuel);

            Libro libro = new Libro();
            libro.setTitulo("Alas de sangre");
            libro.setAutorNombre("Rebecca Yarros");
            libro.setGenero("Juvenil");
            libro.setPrecio(new BigDecimal("200.00"));
            libro.setStock(0);
            libro.setActivo(true);
            libro.setFechaPublicacion(LocalDate.of(2023, 8, 5));
            libro.setDescripcion("Vuela... o muere. El nuevo fenómeno de fantasía juvenil del que todo el mundo habla.");
            entityManager.persist(libro);

            Libro libro2 = new Libro();
            libro2.setTitulo("Cruel verdad. Un giro inesperado");
            libro2.setAutorNombre("JEN CALONITA");
            libro2.setGenero("Infantil");
            libro2.setPrecio(new BigDecimal("150.00"));
            libro2.setStock(10);
            libro2.setActivo(true);
            libro2.setFechaPublicacion(LocalDate.of(2025, 9, 3));
            libro2.setDescripcion("¿Qué pasaría sí Cruella y Anita fueran mejores amigas?");
            entityManager.persist(libro2);

            Libro libro3 = new Libro();
            libro3.setTitulo("El arte de ser nosotros");
            libro3.setAutorNombre("Inma Rubiales");
            libro3.setGenero("Juvenil");
            libro3.setPrecio(new BigDecimal("249.99"));
            libro3.setStock(6);
            libro3.setActivo(true);
            libro3.setFechaPublicacion(LocalDate.of(2024, 10, 23));
            libro3.setDescripcion(null);


            entityManager.getTransaction().commit();

            // TODO: imprimir IDs creados
            System.out.println("Clientes Creados: " + clienteLuis.getId() + ", " + clienteoscar.getId() + ", " + clientemanuel.getId());
            System.out.println("Libros Creados: " + libro.getId() + ", " + libro2.getId() + ", " + libro3.getId());
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}