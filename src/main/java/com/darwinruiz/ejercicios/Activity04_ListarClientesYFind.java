package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import com.darwinruiz.models.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

/*
ENUNCIADO:
1) Lista todos los clientes ordenados por nombre (JPQL).
2) Toma el primero de la lista y búscalo con entityManager.find() por ID.
3) Imprime ambos resultados.
*/
public class Activity04_ListarClientesYFind {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: SELECT c FROM Cliente c ORDER BY c.nombre
            List<Cliente> lista = entityManager.createQuery(
                    "SELECT c FROM  Cliente c ORDER BY c.nombre", Cliente.class
            ).getResultList();
            for (Cliente cliente : lista) {
                System.out.println(cliente);
            }
            // TODO: if (!lista.isEmpty()) -> find por id y print
            if (!lista.isEmpty()) {
                Long idcliente = lista.get(0).getId();
                Cliente findbyidcliente = entityManager.find(Cliente.class, idcliente);
                System.out.println("Cliente encontrado con Id" + findbyidcliente);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}