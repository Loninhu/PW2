package com.example.hospital.model.repository;

import com.example.hospital.model.entity.Consulta;
import com.example.hospital.model.entity.Medico;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TemporalType;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Repository
public class ConsultaRepository {
    @PersistenceContext
    private EntityManager em;

    public Consulta consulta(Long id){
        return em.find(Consulta.class, id);
    }

    public List<Consulta> buscarData(LocalDate data) {
        String jpql = "SELECT c FROM Consulta c WHERE c.data >= :inicioDoDia AND c.data < :inicioDoDiaSeguinte";

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime inicioDoDiaSeguinte = inicioDoDia.plusDays(1);

        Query query = em.createQuery(jpql);
        query.setParameter("inicioDoDia", inicioDoDia);
        query.setParameter("inicioDoDiaSeguinte", inicioDoDiaSeguinte);

        return query.getResultList();
    }

    public List<Consulta> consultas(){
        Query query = em.createQuery("from Consulta");
        return query.getResultList();
    }
    public List<Consulta> consultasPaciente(Long id){
        Query query = em.createQuery("from Consulta c where c.paciente.id = "+id);
        return query.getResultList();
    }
    public List<Consulta> consultasMedico(Long id){
        Query query = em.createQuery("from Consulta c where c.medico.id = " + id);
        return query.getResultList();
    }

    public void save(Consulta consulta){
        em.persist(consulta);
    }
    public void update(Consulta consulta){
        em.merge(consulta);
    }

    public Consulta update(Long id){
        return em.find(Consulta.class, id);
    }



    public void remove(Long id){
        Consulta c = em.find(Consulta.class, id);
        em.remove(c);
    }


}
