package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.ProfessorEntity;
import br.edu.ifpb.aps.jifesp.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements CrudService<ProfessorEntity, Long>{
    
    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository){
        this.professorRepository = professorRepository;
    }

    @Override
    public ProfessorEntity save(ProfessorEntity professorEntity) {
        return professorRepository.save(professorEntity);
    }

     @Override
    public ProfessorEntity update(Long id, ProfessorEntity professorEntity) {
       Optional<ProfessorEntity> professorExistente = professorRepository.findById(id);
        if (professorExistente.isPresent()) {
            return professorRepository.save(professorEntity);
        }
        System.out.println("Professor não encontrado.");
        return null;
    }

    @Override
    public void delete(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            System.out.println("Professor removido com sucesso.");
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    @Override
    public List<ProfessorEntity> findAll() {
        return professorRepository.findAll();
    }
}
