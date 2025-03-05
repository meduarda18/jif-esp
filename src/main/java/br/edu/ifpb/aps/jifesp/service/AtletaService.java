package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.AtletaEntity;
import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import br.edu.ifpb.aps.jifesp.repository.AtletaRepository;
import br.edu.ifpb.aps.jifesp.repository.EquipeRepository;
import br.edu.ifpb.aps.jifesp.repository.ModalidadeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AtletaService implements CrudService<AtletaEntity, Long> {

    private final AtletaRepository atletaRepository;

    private final ModalidadeRepository modalidadeRepository;

    private final EquipeRepository equipeRepository;

    public AtletaService(AtletaRepository atletaRepository, ModalidadeRepository modalidadeRepository, EquipeRepository equipeRepository){
        this.atletaRepository = atletaRepository;
        this.modalidadeRepository = modalidadeRepository;
        this.equipeRepository = equipeRepository;
    }

    @Override
    public AtletaEntity save(AtletaEntity atletaEntity) {
        // Verifica se a equipe já existe
        if (atletaEntity.getEquipe() != null && atletaEntity.getEquipe().getIdEquipe() == null) {
            equipeRepository.save(atletaEntity.getEquipe());
        }

        //Persiste modalidades
        List<ModalidadeEntity> modalidadesPersistidas = new ArrayList<>();
        for (ModalidadeEntity modalidade : atletaEntity.getModalidades()) {
            ModalidadeEntity modalidadeExistente = modalidadeRepository.findById(modalidade.getIdModalidade())
                    .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));
            modalidadesPersistidas.add(modalidadeExistente);
        }
        atletaEntity.setModalidades(modalidadesPersistidas);
        return atletaRepository.save(atletaEntity);
        //return atletaRepository.save(atletaEntity);
    }

    @Override
    public AtletaEntity update(Long id, AtletaEntity atletaEntity) {
        Optional<AtletaEntity> atletaExistente = atletaRepository.findById(id);
        if (atletaExistente.isPresent()) {
            return atletaRepository.save(atletaEntity);
        }
        System.out.println("Atleta não encontrado.");
        return null;
    }

    @Override
    public void delete(Long id) {
        if (atletaRepository.existsById(id)) {
            atletaRepository.deleteById(id);
            System.out.println("Atleta removido com sucesso.");
        } else {
            System.out.println("Atleta não encontrado.");
        }
    }

    @Override
    public List<AtletaEntity> findAll() {
        return atletaRepository.findAll();
    }

}
