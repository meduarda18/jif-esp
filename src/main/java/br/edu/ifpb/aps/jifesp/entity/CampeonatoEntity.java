package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "campeonato")
public class CampeonatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campeonato") // Especifique o nome da coluna
    private Long idCampeonato;

    @Column(name = "nome", nullable = false) // Especifique o nome da coluna e nullable
    private String nome;

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public CampeonatoEntity() {
    }

    public CampeonatoEntity(String nome) {
        this.nome = nome;
    }

    public Long getIdCampeonato() {
        return idCampeonato;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}