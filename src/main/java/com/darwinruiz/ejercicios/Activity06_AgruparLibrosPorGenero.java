package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Agrupa libros por género y muestra:
- COUNT(*), AVG(precio), SUM(stock)
Ordena por género ASC.
*/
public class Activity06_AgruparLibrosPorGenero {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT l.genero, COUNT(l), AVG(l.precio), SUM(l.stock) FROM Libro l GROUP BY l.genero ORDER BY l.genero
            List<Object[]> fila = entityManager.createQuery(
                    "SELECT libro.genero, COUNT(libro), AVG(libro.precio), SUM(libro.stock) " +
                            "FROM Libro libro " + "GROUP BY libro.genero " + "ORDER BY libro.genero",
                    Object[].class
            ).getResultList();

            // TODO: imprimir filas
            for (Object[] f : fila) {
                System.out.println("Genero: " + f[0] + " | COUNT: " + f[1] + " | AVG Precio: " + f[2] + " | SUM Stock: " + f[3]);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}