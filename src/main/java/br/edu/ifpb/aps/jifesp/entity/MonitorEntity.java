package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "monitores")
@AttributeOverride(name = "nome", column = @Column(name = "nome_monitor"))
@AttributeOverride(name = "matricula", column = @Column(name = "matricula_monitor"))
public class MonitorEntity extends UsuarioEntity {

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public MonitorEntity() {
        super();
    }

    public MonitorEntity(String nome, int matricula) {
        super(nome, matricula);
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