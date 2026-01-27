package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.CampeonatoEntity;
import br.edu.ifpb.aps.jifesp.entity.JogoEntity;
import br.edu.ifpb.aps.jifesp.entity.MonitorEntity;
import br.edu.ifpb.aps.jifesp.repository.CampeonatoRepository;
import br.edu.ifpb.aps.jifesp.repository.JogoRepository;
import br.edu.ifpb.aps.jifesp.repository.MonitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService implements CrudService<JogoEntity, Long> {

    private final JogoRepository jogoRepository;
    private final CampeonatoRepository campeonatoRepository;
    private final MonitorRepository monitorRepository;

    public JogoService(
            JogoRepository jogoRepository,
            CampeonatoRepository campeonatoRepository,
            MonitorRepository monitorRepository) {
        this.jogoRepository = jogoRepository;
        this.campeonatoRepository = campeonatoRepository;
        this.monitorRepository = monitorRepository;
    }

    @Override
    public JogoEntity save(JogoEntity jogoEntity) {
        if (jogoEntity.getCampeonato() == null) {
            throw new RuntimeException("Jogo deve estar associado a um campeonato");
        }

        // Garante que o campeonato existe
        CampeonatoEntity campeonato = campeonatoRepository
                .findById(jogoEntity.getCampeonato().getIdCampeonato())
                .orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

        jogoEntity.setCampeonato(campeonato);

        // Garante vínculo bidirecional da súmula
        if (jogoEntity.getSumula() != null) {
            jogoEntity.getSumula().setJogo(jogoEntity);
        }

        return jogoRepository.save(jogoEntity);
    }

    @Override
    public JogoEntity update(Long id, JogoEntity jogoEntity) {
        JogoEntity existente = jogoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogo não encontrado"));

        if (jogoEntity.getCampeonato() != null) {
            CampeonatoEntity campeonato = campeonatoRepository
                    .findById(jogoEntity.getCampeonato().getIdCampeonato())
                    .orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));
            existente.setCampeonato(campeonato);
        }

        if (jogoEntity.getMonitor() != null) {
            MonitorEntity monitor = monitorRepository
                    .findById(jogoEntity.getMonitor().getIdUsuario())
                    .orElseThrow(() -> new RuntimeException("Monitor não encontrado"));
            existente.setMonitor(monitor);
        }

        if (jogoEntity.getParticipantes() != null) {
            existente.setParticipantes(jogoEntity.getParticipantes());
        }

        if (jogoEntity.getPlacar() != null) {
            existente.setPlacar(jogoEntity.getPlacar());
        }

        if (jogoEntity.getSumula() != null) {
            existente.setSumula(jogoEntity.getSumula());
        }

        return jogoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        if (!jogoRepository.existsById(id)) {
            throw new RuntimeException("Jogo não encontrado");
        }
        jogoRepository.deleteById(id);
    }

    @Override
    public List<JogoEntity> findAll() {
        return jogoRepository.findAll();
    }
}
