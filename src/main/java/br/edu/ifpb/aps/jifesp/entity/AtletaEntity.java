package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "atletas")
public class AtletaEntity extends UsuarioEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private Situacao situacao;

    @ManyToMany
    @JoinTable(
            name = "atleta_modalidade",
            joinColumns = @JoinColumn(name = "id_atleta"),
            inverseJoinColumns = @JoinColumn(name = "id_modalidade")
    )
    private List<ModalidadeEntity> modalidades;

    @ManyToOne
    @JoinColumn(name = "id_equipe", nullable = true) // Chave estrangeira para a tabela equipes
    private EquipeEntity equipe;

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public AtletaEntity() {
    }

    public AtletaEntity(String nome, int matricula) {
        super(nome, matricula);
    }

    public AtletaEntity(String nome, int matricula, Situacao situacao, List<ModalidadeEntity> modalidades) {
        super(nome, matricula);
        this.situacao = situacao;
        this.modalidades = modalidades;
    }

    public List<ModalidadeEntity> getModalidades() {
        return modalidades;
    }

    public void setModalidades(List<ModalidadeEntity> modalidades) {
        this.modalidades = modalidades;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public EquipeEntity getEquipe() {
        return equipe;
    }

    public void setEquipe(EquipeEntity equipe) {
        this.equipe = equipe;
    }

    @Override
    public String getTipoUsuario() {
        return "";
    }

    @Override
    public boolean validarUsuario() {
        return false;
    }
}