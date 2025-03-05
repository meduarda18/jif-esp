package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "equipes")
public class EquipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipe") // Especifique o nome da coluna
    private Long idEquipe;

    @Column(name = "nome_equipe", nullable = false) // Especifique o nome da coluna e nullable
    private String nomeEquipe;

    @OneToOne
    @JoinColumn(name = "id_capitao") // Nova coluna para armazenar o ID do capitão
    private AtletaEntity capitao;


    @ManyToMany
    @JoinTable(
            name = "equipe_atleta", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "id_equipe"), // Coluna da EquipeEntity na tabela intermediária
            inverseJoinColumns = @JoinColumn(name = "id_atleta") // Coluna da AtletaEntity na tabela intermediária
    )
    private List<AtletaEntity> jogadores;

    @ElementCollection // Para listas de tipos básicos (Integer, String, etc.)
    @CollectionTable(
            name = "equipe_matriculas", // Nome da tabela para armazenar as matrículas
            joinColumns = @JoinColumn(name = "id_equipe") // Coluna que referencia a EquipeEntity
    )
    @Column(name = "matricula") // Nome da coluna para a matrícula
    private List<Integer> matriculas;


    // **IMPORTANTE**: Adicione um construtor sem argumentos (default) para JPA
    public EquipeEntity() {
    }


    public EquipeEntity(String nomeEquipe, AtletaEntity capitao, List<AtletaEntity> jogadores, List<Integer> matriculas) {
        this.nomeEquipe = nomeEquipe;
        this.capitao = capitao;
        this.jogadores = jogadores;
        this.matriculas = matriculas;
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

    public AtletaEntity getCapitao() {
        return capitao;
    }

    public void setCapitao(AtletaEntity capitao) {
        if (jogadores != null && !jogadores.contains(capitao)) {
            throw new IllegalArgumentException("O capitão deve ser um dos jogadores da equipe!");
        }
        this.capitao = capitao;
    }


    public List<AtletaEntity> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<AtletaEntity> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Integer> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Integer> matriculas) {
        this.matriculas = matriculas;
    }
}