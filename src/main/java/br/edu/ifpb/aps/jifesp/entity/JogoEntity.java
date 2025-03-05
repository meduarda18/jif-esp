package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "jogos")
public class JogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jogo") // Especifique o nome da coluna
    private Long idJogo;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "jogo_atleta", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "id_jogo"), // Coluna da JogoEntity na tabela intermediária
            inverseJoinColumns = @JoinColumn(name = "id_atleta") // Coluna da AtletaEntity na tabela intermediária
    )
    private List<AtletaEntity> participantes;

    @Column(name = "placar") // Especifique o nome da coluna
    private int placar;

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public JogoEntity() {
    }

    public JogoEntity(List<AtletaEntity> participantes, int placar) {
        this.participantes = participantes;
        this.placar = placar;
    }

    public Long getIdJogo() {
        return idJogo;
    }

    public List<AtletaEntity> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<AtletaEntity> participantes) {
        this.participantes = participantes;
    }

    public int getPlacar() {
        return placar;
    }

    public void setPlacar(int placar) {
        this.placar = placar;
    }
}