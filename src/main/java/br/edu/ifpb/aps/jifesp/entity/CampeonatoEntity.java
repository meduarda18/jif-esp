package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "campeonato")
public class CampeonatoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campeonato")
    private Long idCampeonato;

    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "campeonato", fetch = FetchType.LAZY)
    private List<JogoEntity> jogos;

    public CampeonatoEntity() {}

    public CampeonatoEntity(String nome, List<JogoEntity> jogos) {
        this.nome = nome;
        this.jogos = jogos;
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

    public List<JogoEntity> getJogos() {
        return jogos;
    }

    public void setJogos(List<JogoEntity> jogos) {
        this.jogos = jogos;
    }
}
