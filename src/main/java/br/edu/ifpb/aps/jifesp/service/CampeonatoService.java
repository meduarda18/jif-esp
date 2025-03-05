package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.CampeonatoEntity;
import br.edu.ifpb.aps.jifesp.repository.CampeonatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampeonatoService implements CrudService <CampeonatoEntity, Long> {

    private final CampeonatoRepository campeonatoRepository;


    public CampeonatoService(CampeonatoRepository campeonatoRepository){
        this.campeonatoRepository = campeonatoRepository;
    }

    @Override
    public CampeonatoEntity save(CampeonatoEntity campeonatoEntity) {
        return campeonatoRepository.save(campeonatoEntity);
    }

    @Override
    public CampeonatoEntity update(Long id, CampeonatoEntity campeonatoEntity) {
         Optional<CampeonatoEntity> campeonatoExistente = campeonatoRepository.findById(id);
        if (campeonatoExistente.isPresent()) {
            return campeonatoRepository.save(campeonatoEntity);
        }
        System.out.println("Campeonato não encontrado.");
        return null;
    }

    @Override
    public void delete(Long id) { 
        if (campeonatoRepository.existsById(id)) {
        campeonatoRepository.deleteById(id);
        System.out.println("Campeonato removido com sucesso.");
    } else {
        System.out.println("Campeonato não encontrado.");
    }
    }

    @Override
    public List<CampeonatoEntity> findAll() {
        return campeonatoRepository.findAll();
    }
    
}
