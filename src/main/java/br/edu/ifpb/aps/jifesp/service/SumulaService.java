package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.SumulaEntity;
import br.edu.ifpb.aps.jifesp.repository.SumulaRepository;

import java.util.List;
import java.util.Optional;

public class SumulaService implements CrudService<SumulaEntity, Long>{
    private final SumulaRepository sumulaRepository;

    public SumulaService(SumulaRepository sumulaRepository){
        this.sumulaRepository = sumulaRepository;
    }
    @Override
    public SumulaEntity save(SumulaEntity sumulaEntity) {
        return sumulaRepository.save(sumulaEntity);
    }

    @Override
    public SumulaEntity update(Long id, SumulaEntity sumulaEntity) {
        Optional<SumulaEntity> sumulaExistente = sumulaRepository.findById(id);
        if (sumulaExistente.isPresent()) {
            return sumulaRepository.save(sumulaEntity);
        }
        System.out.println("Sumula não encontrada.");
        return null;
    }

    @Override
    public void delete(Long id) {
        if (sumulaRepository.existsById(id)) {
            sumulaRepository.deleteById(id);
            System.out.println("Sumula removida com sucesso.");
        } else {
            System.out.println("Sumula não encontrada.");
        }
    }

    @Override
    public List<SumulaEntity> findAll() {
        return sumulaRepository.findAll();
    }
    
}
