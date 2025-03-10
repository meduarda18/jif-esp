package br.edu.ifpb.aps.jifesp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "monitores")
//@AttributeOverride(name = "nome", column = @Column(name = "nome_monitor"))
//@AttributeOverride(name = "matricula", column = @Column(name = "matricula_monitor"))
public class MonitorEntity extends UsuarioEntity {

    @OneToMany(mappedBy = "monitor", cascade = CascadeType.ALL)
    private List<JogoEntity> jogosArbitrados = new ArrayList<>();

    // Construtor padrão (sem argumentos) - **ESSENCIAL**
    public MonitorEntity() {}

    public MonitorEntity(String nome, int matricula) {
        super(nome, matricula);
    }

    public List<JogoEntity> getJogosArbitrados() {
        return jogosArbitrados;
    }

    public void setJogosArbitrados(List<JogoEntity> jogosArbitrados) {
        this.jogosArbitrados = jogosArbitrados;
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