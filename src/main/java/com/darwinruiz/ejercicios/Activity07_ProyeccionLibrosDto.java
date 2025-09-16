package com.darwinruiz.ejercicios;

import com.darwinruiz.dto.LibroResumenDto;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
Proyecta Libros a LibroResumenDto (id, titulo, precio) usando:
SELECT new com.example.jpql_exercises.dto.LibroResumenDto(l.id, l.titulo, l.precio)
Ordena por id ASC e imprime.
*/
public class Activity07_ProyeccionLibrosDto {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: crear query con SELECT new ... LibroResumenDto(...), getResultList, imprimir
            List<LibroResumenDto> listResumenDto = entityManager.createQuery(
                    "SELECT new com.darwinruiz.dto.LibroResumenDto(l.id, l.titulo, l.precio) " +
                            "FROM Libro l ORDER BY l.id", LibroResumenDto.class).getResultList();
            for (LibroResumenDto dto : listResumenDto) {
                System.out.println(dto);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}
