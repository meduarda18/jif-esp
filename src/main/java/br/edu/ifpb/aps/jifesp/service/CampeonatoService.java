package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.CampeonatoEntity;
import br.edu.ifpb.aps.jifesp.repository.CampeonatoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampeonatoService implements CrudService<CampeonatoEntity, Long> {

    private final CampeonatoRepository campeonatoRepository;

    public CampeonatoService(CampeonatoRepository campeonatoRepository) {
        this.campeonatoRepository = campeonatoRepository;
    }

    @Override
    public CampeonatoEntity save(CampeonatoEntity campeonatoEntity) {
        return campeonatoRepository.save(campeonatoEntity);
    }

    @Override
    public CampeonatoEntity update(Long id, CampeonatoEntity campeonatoEntity) {
        CampeonatoEntity existente = campeonatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

        if (campeonatoEntity.getNome() != null) {
            existente.setNome(campeonatoEntity.getNome());
        }

        return campeonatoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        if (!campeonatoRepository.existsById(id)) {
            throw new RuntimeException("Campeonato não encontrado");
        }
        campeonatoRepository.deleteById(id);
    }

    @Override
    public List<CampeonatoEntity> findAll() {
        return campeonatoRepository.findAll();
    }

}
