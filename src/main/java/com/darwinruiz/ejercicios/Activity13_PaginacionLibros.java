package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Activity13_PaginacionLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            int pageSize = 5;
            int offset = 0;
            int page = 1;

            while (true) {
                List<Libro> libros = entityManager.createQuery(
                                "SELECT l FROM Libro l ORDER BY l.id ASC",
                                Libro.class)
                        .setFirstResult(offset)
                        .setMaxResults(pageSize)
                        .getResultList();

                if (libros.isEmpty()) {
                    break;
                }

                System.out.println("Página " + page + ":");
                libros.forEach(System.out::println);

                offset += pageSize;
                page++;
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
