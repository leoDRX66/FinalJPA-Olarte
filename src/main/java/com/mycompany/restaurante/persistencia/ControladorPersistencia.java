package com.mycompany.restaurante.persistencia;

import com.mycompany.restaurante.modelo.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ControladorPersistencia {
    
    private EntityManagerFactory emf = null;

    public ControladorPersistencia() {
        emf = Persistence.createEntityManagerFactory("FinalJPAPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void crear(Object objeto) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(objeto);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(ControladorPersistencia.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (em != null) em.close();
        }
    }

    public <T> void eliminar(Class<T> tipo, int id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            T objeto = em.find(tipo, id);
            if (objeto != null) {
                em.remove(objeto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) em.close();
        }
    }

    public List<Alimento> traerAlimentos() {
        return findEntities(Alimento.class);
    }

    public List<Persona> traerPersonasPorTipo(Class<? extends Persona> tipoClase) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT p FROM " + tipoClase.getSimpleName() + " p";
            Query q = em.createQuery(jpql);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Pedido> traerPedidos() {
        return findEntities(Pedido.class);
    }

    private <T> List<T> findEntities(Class<T> tipo) {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(tipo));
            return em.createQuery(cq).getResultList();
        } finally {
            em.close();
        }
    }
}