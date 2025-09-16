package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

/*
ENUNCIADO:
Usa la NamedQuery "Cliente.buscarPorEmail" para encontrar un cliente por email
y actualizar su nombre y ciudad.
Restricciones:
- Maneja transacción.
- Si no encuentra, muestra un mensaje.
*/
public class Activity02_ActualizarClientePorEmail {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            // TODO: obtener cliente por NamedQuery, modificar campos y merge
            try {
                Cliente cliente = entityManager.createNamedQuery("Cliente.buscarPorEmail", Cliente.class).setParameter("email", "luisfer@gmail.com").getSingleResult();
                cliente.setNombre("Angel David García López");
                cliente.setCiudad("Antigua Guatemala");
                entityManager.merge(cliente);


                System.out.println("Cliente actualizado correctamente" +  cliente);
            }catch (NoResultException e) {
                System.out.println("No se pudo encontrar cliente con email indicado");
            }
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw exception;
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}