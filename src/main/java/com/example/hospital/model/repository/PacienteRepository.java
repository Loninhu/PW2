package com.example.hospital.model.repository;

import com.example.hospital.model.entity.Medico;
import com.example.hospital.model.entity.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PacienteRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Paciente> buscarNome(String nome){
        String jpql = "SELECT m FROM Paciente m WHERE LOWER(m.nome) LIKE LOWER(:nome)";
        Query query = em.createQuery(jpql);
        query.setParameter("nome", "%" + nome.toLowerCase() + "%");
        return query.getResultList();
    }

    public List<Paciente> pacientes(){
        Query query = em.createQuery("from Paciente");
        return query.getResultList();
    }

    public void save(Paciente paciente){
        em.merge(paciente);
    }

    public Paciente paciente(Long id){
        return em.find(Paciente.class, id);
    }



    public void remove(Long id){
        Paciente p = em.find(Paciente.class, id);
        em.remove(p);
    }

    public void update(Paciente paciente){
        em.merge(paciente);
    }


}
