
package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.AtletaEntity;
import br.edu.ifpb.aps.jifesp.entity.EquipeEntity;
import br.edu.ifpb.aps.jifesp.entity.JogoEntity;
import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import br.edu.ifpb.aps.jifesp.repository.AtletaRepository;
import br.edu.ifpb.aps.jifesp.repository.EquipeRepository;
import br.edu.ifpb.aps.jifesp.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoModalidadeService {

    private AtletaRepository atletarepository;
    private EquipeRepository equipeRepository;
    private JogoRepository jogoRepository;


    public void inscreveModalidade(AtletaEntity atleta, ModalidadeEntity modalidade) {
        if(atleta == null || modalidade == null){
            throw new IllegalArgumentException("Atleta e Modalidade devem ser informados");
        }
        if (atleta.getModalidades().contains(modalidade)) {
            throw new RuntimeException("Atleta já inscrito");
        }
        atleta.getModalidades().add(modalidade);
        atletarepository.save(atleta);
        
    }

    public void inscreveEquipe(AtletaEntity atleta, EquipeEntity equipe) {
        if(atleta == null || equipe == null){
            throw new IllegalArgumentException("Atleta e Modalidade devem ser informados");
        }
        if (atleta.getEquipe() != null) {
            throw new IllegalStateException("Atleta já participa de uma equipe");
        }

        equipe.getJogadores().add(atleta);
        atleta.setEquipe(equipe);

        atletarepository.save(atleta);
        equipeRepository.save(equipe);
        
    }

    public List<ModalidadeEntity> buscaModalidade(AtletaEntity atleta) {
        if (atleta == null) {
            throw new IllegalArgumentException("Atleta não localizado");
        }
        return atleta.getModalidades();
    }

    public JogoEntity buscarJogo(AtletaEntity atleta) {
        if (atleta == null) {
            throw new IllegalArgumentException("Atleta não localizado");
        }
//        return jogoRepository.findByAtletasContains(atleta).orElseThrow(()-> new NoSuchElementException());
        return null;
    }

    public String buscarRegulamento(ModalidadeEntity modalidade) {
        if (modalidade == null) {
            throw new IllegalArgumentException("Modalidade não pode ser localizada");
        }
        return modalidade.getRegulamento();
    }

}

