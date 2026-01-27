package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.ModalidadeEntity;
import br.edu.ifpb.aps.jifesp.repository.ModalidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalidadeService implements CrudService<ModalidadeEntity, Long> {
    private final ModalidadeRepository modalidadeRepository;

    public ModalidadeService(ModalidadeRepository modalidadeRepository) {
        this.modalidadeRepository = modalidadeRepository;
    }

    @Override
    public ModalidadeEntity save(ModalidadeEntity modalidadeEntity) {
        return modalidadeRepository.save(modalidadeEntity);
    }

    @Override
    public ModalidadeEntity update(Long id, ModalidadeEntity modalidadeEntity) {
        ModalidadeEntity modalidadeExistente = modalidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modalidade não encontrada"));

        if (modalidadeEntity.getNome() != null) {
            modalidadeExistente.setNome(modalidadeEntity.getNome());
        }

        if (modalidadeEntity.getQuantidadeParticipantes() != 0) {
            modalidadeExistente.setQuantidadeParticipantes(
                    modalidadeEntity.getQuantidadeParticipantes());
        }

        if (modalidadeEntity.getRegulamento() != null) {
            modalidadeExistente.setRegulamento(modalidadeEntity.getRegulamento());
        }

        return modalidadeRepository.save(modalidadeExistente);
    }

    @Override
    public void delete(Long id) {
        if (modalidadeRepository.existsById(id)) {
            modalidadeRepository.deleteById(id);
            System.out.println("Modalidade removida com sucesso.");
        } else {
            System.out.println("Modalidade não encontrada.");
        }
    }

    @Override
    public List<ModalidadeEntity> findAll() {
        return modalidadeRepository.findAll();
    }
}