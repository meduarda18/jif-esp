package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.EquipeEntity;
import br.edu.ifpb.aps.jifesp.repository.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService implements CrudService<EquipeEntity, Long> {

    private EquipeRepository equipeRepository;

    public EquipeService(EquipeRepository equipeRepository){
        this.equipeRepository = equipeRepository;
    }

    @Override
    public EquipeEntity save(EquipeEntity equipeEntity) {
        return equipeRepository.save(equipeEntity);
    }

    @Override
    public EquipeEntity update(Long id, EquipeEntity equipeEntity) {
        Optional<EquipeEntity> equipeExistente = equipeRepository.findById(id);
        if (equipeExistente.isPresent()) {
            return equipeRepository.save(equipeEntity);
        }
        System.out.println("Equipe não encontrado.");
        return null;
    }

    @Override
    public void delete(Long id) {
        if (equipeRepository.existsById(id)) {
            equipeRepository.deleteById(id);
            System.out.println("Equipe removida com sucesso.");
        } else {
            System.out.println("Equipe não encontrada.");
        }
    }

    @Override
    public List<EquipeEntity> findAll() {
        return equipeRepository.findAll();
    }
}
