package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "jogos")
public class JogoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jogo")
    private Long idJogo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_campeonato")
    private CampeonatoEntity campeonato;

    @ManyToOne
    @JoinColumn(name = "id_monitor")
    private MonitorEntity monitor;

    @ManyToMany
    @JoinTable(
        name = "jogo_atleta",
        joinColumns = @JoinColumn(name = "id_jogo"),
        inverseJoinColumns = @JoinColumn(name = "id_atleta")
    )
    private List<AtletaEntity> participantes;

    @Column(name = "placar")
    private String placar;

    @OneToOne(mappedBy = "jogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private SumulaEntity sumula;

    public JogoEntity() {}

    public Long getIdJogo() {
        return idJogo;
    }

    public CampeonatoEntity getCampeonato() {
        return campeonato;
    }

    public void setCampeonato(CampeonatoEntity campeonato) {
        this.campeonato = campeonato;
    }

    public MonitorEntity getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorEntity monitor) {
        this.monitor = monitor;
    }

    public List<AtletaEntity> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<AtletaEntity> participantes) {
        this.participantes = participantes;
    }

    public String getPlacar() {
        return placar;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

    public SumulaEntity getSumula() {
        return sumula;
    }

    public void setSumula(SumulaEntity sumula) {
        this.sumula = sumula;
        if (sumula != null) {
            sumula.setJogo(this);
        }
    }
}
