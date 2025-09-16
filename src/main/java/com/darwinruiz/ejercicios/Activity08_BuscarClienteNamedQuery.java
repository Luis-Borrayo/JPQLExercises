package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para buscar un cliente por correo.
Imprime el resultado o un mensaje si no existe.
*/
public class Activity08_BuscarClienteNamedQuery {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            try {
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class)
                        .setParameter("email", "josue.garcia@mail.com")
                        .getSingleResult();
                System.out.println("Resultado: " + cliente);
            } catch (NoResultException e) {
                System.out.println("No se encontró ningún cliente con ese correo.");
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}