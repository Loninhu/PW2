/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.hospital.model.repository;

import com.example.hospital.model.entity.Paciente;
import com.example.hospital.model.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author fagno
 */
@Repository
public class UsuarioRepository {

    @PersistenceContext
    private EntityManager em;

    public Usuario usuario(String login) {
        try {
            String jpql = "from Usuario u where u.login = :login";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("login", login);
            return query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
           // return em.find(Usuario.class, login);

    }

    public void save(Usuario usuario) {
        em.persist(usuario);
    }

}