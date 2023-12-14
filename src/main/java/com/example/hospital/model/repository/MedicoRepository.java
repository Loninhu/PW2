package com.example.hospital.model.repository;

import com.example.hospital.model.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedicoRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Medico> buscarNome(String nome){
        String jpql = "SELECT m FROM Medico m WHERE LOWER(m.nome) LIKE LOWER(:nome)";
        Query query = em.createQuery(jpql);
        query.setParameter("nome", "%" + nome.toLowerCase() + "%");
        return query.getResultList();
    }
    public List<Medico> medicos(){
        Query query = em.createQuery("from Medico");
        return query.getResultList();
    }
    public void save(Medico medico){
        em.merge(medico);
    }

    public Medico medico(Long id){
        return em.find(Medico.class, id);
    }



    public void remove(Long id){
        Medico m = em.find(Medico.class, id);
        em.remove(m);
    }

    public void update(Medico medico){
        em.merge(medico);
    }


}
