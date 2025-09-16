package com.darwinruiz.ejercicios;


import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Trae los libros cuyo título termine en 'o' (insensible a mayúsculas)
y cuyo género empiece con 'fic' (ej. 'Ficción').
Ordena por titulo ASC.
*/
public class Activity10_LikeCaseInsensitiveLibros {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE :fin AND LOWER(l.genero) LIKE :inicio ORDER BY l.titulo
            List<Libro> listlibros = entityManager.createQuery(
                    "SELECT l FROM Libro l " + "WHERE LOWER(l.titulo) LIKE :fin " +
                            "AND LOWER(l.genero) LIKE :inicio " + "ORDER BY l.titulo", Libro.class)
                    .setParameter("inicio", "fic%").setParameter("fin", "%o").getResultList();
            // TODO: imprimir resultados
            for (Libro libro : listlibros) {
                System.out.println(libro);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}