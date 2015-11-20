/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unbosque.carrito.controlerjpa;

import com.unbosque.carrito.controlerjpa.exceptions.NonexistentEntityException;
import com.unbosque.carrito.controlerjpa.exceptions.PreexistingEntityException;
import com.unbosque.carrito.entidades.Clientepedido;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author scabrera
 */
public class ClientepedidoJpaController implements Serializable {

    public ClientepedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Clientepedido clientepedido) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(clientepedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClientepedido(clientepedido.getIdcliente()) != null) {
                throw new PreexistingEntityException("Clientepedido " + clientepedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Clientepedido clientepedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            clientepedido = em.merge(clientepedido);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = clientepedido.getIdcliente();
                if (findClientepedido(id) == null) {
                    throw new NonexistentEntityException("The clientepedido with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Clientepedido clientepedido;
            try {
                clientepedido = em.getReference(Clientepedido.class, id);
                clientepedido.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The clientepedido with id " + id + " no longer exists.", enfe);
            }
            em.remove(clientepedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Clientepedido> findClientepedidoEntities() {
        return findClientepedidoEntities(true, -1, -1);
    }

    public List<Clientepedido> findClientepedidoEntities(int maxResults, int firstResult) {
        return findClientepedidoEntities(false, maxResults, firstResult);
    }

    private List<Clientepedido> findClientepedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Clientepedido.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Clientepedido findClientepedido(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Clientepedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getClientepedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Clientepedido> rt = cq.from(Clientepedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
