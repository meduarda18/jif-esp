package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.Entity;

@Entity
public class ProfessorEntity extends UsuarioEntity {

    public ProfessorEntity(){} //construtor padrão

    public ProfessorEntity(String nome, int matricula) {
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
