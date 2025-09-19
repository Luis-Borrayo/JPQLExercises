package com.darwinruiz.ejercicios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Activity15_CoalesceDescripcionLibro {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            List<Object[]> resultados = entityManager.createQuery(
                            "SELECT l.titulo, COALESCE(l.descripcion, 'Sin descripción') " +
                                    "FROM Libro l ORDER BY l.titulo",
                            Object[].class)
                    .getResultList();

            if (resultados.isEmpty()) {
                System.out.println("No se encontraron libros.");
            } else {
                resultados.forEach(r ->
                        System.out.println(r[0] + " | " + r[1])
                );
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
