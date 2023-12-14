package com.example.hospital.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
public class Paciente extends Pessoa {
    @NotNull
    @Size(min=8, max=8)
    private String telefone;
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Consulta> consultas;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}
