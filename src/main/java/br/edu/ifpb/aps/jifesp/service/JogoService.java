package br.edu.ifpb.aps.jifesp.service;

import br.edu.ifpb.aps.jifesp.entity.JogoEntity;
import br.edu.ifpb.aps.jifesp.repository.JogoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService implements CrudService<JogoEntity, Long>{

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository){
        this.jogoRepository = jogoRepository;
    }


    @Transactional
    @Override
    public JogoEntity save(JogoEntity jogoEntity) {
        return jogoRepository.save(jogoEntity);
    }

    @Override
    public JogoEntity update(Long id, JogoEntity jogoEntity) {
         Optional<JogoEntity> jogoExistente = jogoRepository.findById(id);
        if (jogoExistente.isPresent()) {
            return jogoRepository.save(jogoEntity);
        }
        System.out.println("Jogo não encontrado.");
        return null;
    }

    @Override
    public void delete(Long id) {
        if (jogoRepository.existsById(id)) {
            jogoRepository.deleteById(id);
            System.out.println("Jogo removido com sucesso.");
        } else {
            System.out.println("Jogo não encontrado.");
        }
    }
    
    
    @Override
    public List<JogoEntity> findAll() {
        return jogoRepository.findAll();
    }
}
