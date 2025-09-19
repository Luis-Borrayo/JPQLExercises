package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;

public class Activity12_RangoFechasPublicacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            LocalDate inicio = LocalDate.now().minusDays(10);
            LocalDate fin = LocalDate.now();

            List<Libro> libros = entityManager.createQuery(
                            "SELECT l FROM Libro l WHERE l.fechaPublicacion BETWEEN :inicio AND :fin ORDER BY l.fechaPublicacion DESC",
                            Libro.class)
                    .setParameter("inicio", inicio)
                    .setParameter("fin", fin)
                    .getResultList();

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros en el rango de fechas.");
            } else {
                System.out.println("Libros publicados entre " + inicio + " y " + fin + ":");
                libros.forEach(System.out::println);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
