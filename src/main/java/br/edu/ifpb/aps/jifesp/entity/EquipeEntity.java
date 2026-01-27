package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "equipes")
public class EquipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe") // Especifique o nome da coluna
    private Long idEquipe;

    @Column(name = "nome_equipe", nullable = false) // Especifique o nome da coluna e nullable
    private String nomeEquipe;

    @Column(name = "capitao")
    private String capitao;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AtletaEntity> jogadores;


    // **IMPORTANTE**: Adicione um construtor sem argumentos (default) para JPA
    public EquipeEntity() {
    }


    public EquipeEntity(String nomeEquipe, String capitao, List<AtletaEntity> jogadores) {
        this.nomeEquipe = nomeEquipe;
        this.capitao = capitao;
        this.jogadores = jogadores;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public String getNomeEquipe() {
        return nomeEquipe;
    }

    public void setNomeEquipe(String nomeEquipe) {
        this.nomeEquipe = nomeEquipe;
    }

    public String getCapitao() {
        return capitao;
    }

    public void setCapitao(String capitao) {
        this.capitao = capitao;
    }


    public List<AtletaEntity> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<AtletaEntity> jogadores) {
        this.jogadores = jogadores;
    }
}