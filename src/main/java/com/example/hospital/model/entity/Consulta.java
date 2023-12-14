package com.example.hospital.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Consulta implements Serializable {
    @Id
    @GenericGenerator(name = "inc", strategy = "increment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME)
    @NotNull
    private LocalDateTime data;
    @NotNull
    @Min(100)
    private double valor;
    @NotNull
    @Size(min=2, max=500)
    private String observacao;
    @NotNull(message = "É necessário selecionar um paciente!")
    @ManyToOne
    private Paciente paciente;
    @NotNull(message = "É necessário selecionar um médico!")
    @ManyToOne
    private Medico medico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
}
