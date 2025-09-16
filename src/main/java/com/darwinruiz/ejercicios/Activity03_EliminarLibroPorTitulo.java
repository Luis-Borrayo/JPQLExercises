package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Elimina un libro buscándolo por título exacto con JPQL.
Restricciones:
- Usa getSingleResult() o maneja lista vacía.
- Transacción obligatoria.
*/
public class Activity03_EliminarLibroPorTitulo {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: SELECT l FROM Libro l WHERE l.titulo = :titulo -> remove
            try {
                Libro libro = entityManager.createQuery(
                                "SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class).setParameter("titulo", "Cien Años de Soledad (Ed. 1)")
                        .getSingleResult();
                entityManager.remove(libro);
                System.out.println("Libro eliminado correctamente");

            } catch (NoResultException e) {
                System.out.println("No se encontró ningún libro con ese título");
            }

            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}