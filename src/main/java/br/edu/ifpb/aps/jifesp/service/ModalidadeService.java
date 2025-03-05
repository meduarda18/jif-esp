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
        Optional<ModalidadeEntity> modalidadeExistenteOptional = modalidadeRepository.findById(id);
        if (modalidadeExistenteOptional.isPresent()) {
            ModalidadeEntity modalidadeExistente = modalidadeExistenteOptional.get();

            // Atualize os campos da entidade existente com os valores da nova entidade
            modalidadeExistente.setNome(modalidadeEntity.getNome());
            modalidadeExistente.setQuantidadeParticipantes(modalidadeEntity.getQuantidadeParticipantes());
            modalidadeExistente.setRegulamento(modalidadeEntity.getRegulamento());

            // Salve a entidade atualizada
            return modalidadeRepository.save(modalidadeExistente);
        } else {
            System.out.println("Modalidade não encontrada.");
            return null;
        }
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