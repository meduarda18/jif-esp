package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.AtletaEntity;
import br.edu.ifpb.aps.jifesp.entity.EquipeEntity;
import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import br.edu.ifpb.aps.jifesp.entity.Situacao;
import br.edu.ifpb.aps.jifesp.repository.AtletaRepository;
import br.edu.ifpb.aps.jifesp.repository.EquipeRepository;
import br.edu.ifpb.aps.jifesp.repository.ModalidadeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AtletaService implements CrudService<AtletaEntity, Long> {

    private final AtletaRepository atletaRepository;

    private final ModalidadeRepository modalidadeRepository;

    private final EquipeRepository equipeRepository;

    public AtletaService(AtletaRepository atletaRepository, ModalidadeRepository modalidadeRepository,
            EquipeRepository equipeRepository) {
        this.atletaRepository = atletaRepository;
        this.modalidadeRepository = modalidadeRepository;
        this.equipeRepository = equipeRepository;
    }

    public AtletaEntity save(AtletaEntity atleta) {

        // associa equipe existente
        if (atleta.getEquipe() != null && atleta.getEquipe().getIdEquipe() != null) {
            EquipeEntity equipe = equipeRepository.findById(atleta.getEquipe().getIdEquipe())
                    .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));
            atleta.setEquipe(equipe);
        }

        // associa modalidades existentes
        List<ModalidadeEntity> modalidades = atleta.getModalidades().stream()
                .map(m -> modalidadeRepository.findById(m.getIdModalidade())
                        .orElseThrow(() -> new RuntimeException("Modalidade não encontrada")))
                .toList();

        atleta.setModalidades(modalidades);

        return atletaRepository.save(atleta);
    }

    @Override
    public AtletaEntity update(Long id, AtletaEntity atletaEntity) {
        AtletaEntity existente = atletaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Atleta não encontrado"));

        if (atletaEntity.getNome() != null) {
            existente.setNome(atletaEntity.getNome());
        }

        if (atletaEntity.getMatricula() != 0) {
            existente.setMatricula(atletaEntity.getMatricula());
        }

        if (atletaEntity.getSituacao() != null) {
            existente.setSituacao(atletaEntity.getSituacao());
        }

        if (atletaEntity.getModalidades() != null) {
            existente.setModalidades(
                    new ArrayList<>(atletaEntity.getModalidades()));
        }

        if (atletaEntity.getEquipe() != null) {
            EquipeEntity equipe = equipeRepository.findById(
                    atletaEntity.getEquipe().getIdEquipe())
                    .orElseThrow(() -> new RuntimeException("Equipe não encontrada"));

            existente.setEquipe(equipe);
        }

        return atletaRepository.save(existente);
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

    public List<AtletaEntity> filtrarPorSituacao(Situacao situacao) {
        return atletaRepository.findBySituacao(situacao);
    }

}
