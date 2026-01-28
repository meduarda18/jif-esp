package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "modalidade")
public class ModalidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modalidade")
    private Long idModalidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "nome", nullable = false)
    private NomeModalidade nome;

    @Column(name = "quantidade_participantes")
    private int quantidadeParticipantes;

    @Column(name = "regulamento", columnDefinition = "TEXT", nullable = false)
    private String regulamento;

    public ModalidadeEntity() {
    }

    public ModalidadeEntity(NomeModalidade nome, int quantidadeParticipantes, String regulamento) {
        this.nome = nome;
        this.quantidadeParticipantes = quantidadeParticipantes;
        this.regulamento = regulamento;
    }

    public Long getIdModalidade() {
        return idModalidade;
    }

    public NomeModalidade getNome() {
        return nome;
    }

    public void setNome(NomeModalidade nome) {
        this.nome = nome;
    }

    public int getQuantidadeParticipantes() {
        return quantidadeParticipantes;
    }

    public void setQuantidadeParticipantes(int quantidadeParticipantes) {
        this.quantidadeParticipantes = quantidadeParticipantes;
    }

    public String getRegulamento() {
        return regulamento;
    }

    public void setRegulamento(String regulamento) {
        this.regulamento = regulamento;
    }
}