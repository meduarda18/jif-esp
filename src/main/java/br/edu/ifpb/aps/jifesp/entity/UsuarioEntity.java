package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class UsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String nome;
    private int matricula;
    
    protected UsuarioEntity(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    protected UsuarioEntity() {}

    public Long getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public abstract String getTipoUsuario();
    public abstract boolean validarUsuario();
}
