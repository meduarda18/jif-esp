package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sumulas") // Corrigi o nome da tabela para "sumulas" (plural)
public class SumulaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sumula") // Especifique o nome da coluna
    private Long idSumula;  // Use Long para corresponder ao GenerationType.IDENTITY

    @OneToOne  // Relacionamento com JogoEntity
    @JoinColumn(name = "id_jogo") // Chave estrangeira para a tabela jogos
    private JogoEntity jogo;

    @Column(name = "fase", nullable = false) // Especifique o nome da coluna
    private String fase;

    @Column(name = "resultado") // Especifique o nome da coluna
    private String resultado;

    @Column(name = "observacoes", columnDefinition = "TEXT") // Especifique o nome da coluna
    private String observacoes;

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public SumulaEntity() {
    }

    public SumulaEntity(JogoEntity jogo, String fase, String resultado, String observacoes) {
        this.jogo = jogo;
        this.fase = fase;
        this.resultado = resultado;
        this.observacoes = observacoes;
    }

    public Long getIdSumula() {
        return idSumula;
    }

    public JogoEntity getJogo() {
        return jogo;
    }

    public void setJogo(JogoEntity jogo) {
        this.jogo = jogo;
    }

    public String getFase() {
        return fase;
    }

    public void setFase(String fase) {
        this.fase = fase;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}