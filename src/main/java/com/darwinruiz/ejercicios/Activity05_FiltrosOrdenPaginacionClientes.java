package com.darwinruiz.ejercicios;

import com.darwinruiz.models.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

/*
ENUNCIADO:
Consulta clientes cuyo nombre contenga 'a' (case-insensitive),
ordena por ciudad ASC, y devuelve la página 2 (tamaño 3).
Imprime los resultados.
*/
public class Activity05_FiltrosOrdenPaginacionClientes {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPQLExercisesPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            // TODO: crear TypedQuery con LOWER(nombre) LIKE :patron, ORDER BY ciudad
            TypedQuery<Cliente> query = entityManager.createQuery("SELECT c FROM Cliente c " + "WHERE LOWER(c.nombre) LIKE :patron "
                    + "ORDER BY c.ciudad ASC", Cliente.class);

            // TODO: setFirstResult(3) y setMaxResults(3)
            query.setParameter("patron", "%a%");
            query.setFirstResult(3);
            query.setMaxResults(3);

            // TODO: imprimir resultados
            List<Cliente> pagina = query.getResultList();
            for (Cliente cliente : pagina) {
                System.out.println("Página -- " + cliente);
            }
        } finally {
            entityManager.close();
            entityManagerFactory.close();
        }
    }
}