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

    @ManyToOne
    @JoinColumn(name = "id_campeonato") // Chave estrangeira que referencia o campeonato
    private CampeonatoEntity campeonato;

    @ManyToOne
    @JoinColumn(name = "id_monitor") // Chave estrangeira para UsuarioEntity (árbitro)
    private MonitorEntity monitor;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "jogo_atleta", // Nome da tabela intermediária
            joinColumns = @JoinColumn(name = "id_jogo"), // Coluna da JogoEntity na tabela intermediária
            inverseJoinColumns = @JoinColumn(name = "id_atleta") // Coluna da AtletaEntity na tabela intermediária
    )
    private List<AtletaEntity> participantes;

    @Column(name = "placar") // Especifique o nome da coluna
    private String placar;

    @OneToOne(mappedBy = "jogo", cascade = CascadeType.ALL, orphanRemoval = true)
    private SumulaEntity sumula;

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public JogoEntity() {
    }

    public JogoEntity(CampeonatoEntity campeonato, MonitorEntity monitor, List<AtletaEntity> participantes, String placar, SumulaEntity sumula) {
        this.participantes = participantes;
        this.placar = placar;
        this.campeonato = campeonato;
        this.monitor = monitor;
        this.sumula = sumula;
    }

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
    }
}